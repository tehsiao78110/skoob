package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.service.CartService;
import model.service.MemberService;
import model.util.CartUtil;
import model.vo.CartData;

@WebServlet(urlPatterns = { "/pages/login.controller" })
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private CartService cartService;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext) this.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		memberService = (MemberService) context.getBean("memberService");
		cartService = context.getBean(CartService.class);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收資料
		String account = request.getParameter("useraccount");
		String password = request.getParameter("password");

		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		if (account == null || account.length() == 0) {
			errors.put("useraccount", "請輸入帳號");
		}
		if (password == null || password.length() == 0) {
			errors.put("userpassword", "請輸入密碼");
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		// 呼叫model
		MemberBean member = memberService.login(account, password);
		// 導向view
		if (member == null) {
//			isRepeatAccount()回傳false,但是要繼續跑if loop 所以加上驚嘆號
			if (!memberService.isRepeatAccount(account)) {
				errors.put("useraccount", "登入失敗，帳號錯誤");
			} else {
				errors.put("userpassword", "登入失敗，密碼錯誤");
			}
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
		} else {
			String birth = sFormat.format(member.getBirth());
			HttpSession session = request.getSession();
			session.setAttribute("user", member);
			session.setAttribute("birth", birth);
			// 登入成功後，要將該「會員購物車」裡面的商品，全部顯示出來
			List<CartBean> cart = cartService.selectAll(member.getMemberid());
			// 算出購物車裡面的必要資訊，儲存在 cartData 中
			Integer totalCost = CartUtil.getCartTotalCost(cart);
			Integer cartNum = CartUtil.getCartProductNum(cart);
			Integer cartAllNum = CartUtil.getCartProductAllNum(cart);
			CartData cartData = new CartData(totalCost, cartNum, cartAllNum);
			
			session.setAttribute("cartData", cartData);
			
			String path = request.getContextPath();
			response.sendRedirect(path + "/index.jsp");
		}

	}

}
