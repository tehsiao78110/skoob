package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.bean.*;
import model.service.*;

@WebServlet(urlPatterns = { "/favorite/myFav.controller" })
public class MyFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyFavService myFavService;
	private ProductService productService; // 可以有兩個service? 超連結
	private CartService cartService;
	private Integer memberid;
//	private Integer memberId; 寫在外面有執行緒問題  

	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext) this.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		myFavService = context.getBean(MyFavService.class);
		productService = context.getBean(ProductService.class);
		cartService = context.getBean(CartService.class);

	}

//	doGet method   
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

// 呼叫model(ProductService)
		System.out.println("selectpro before");
		
// 呼叫MyFavService的selectFav方法，資料庫全部顯示   在這裡呼叫service    

		HttpSession session = request.getSession();
		MemberBean member = (MemberBean)session.getAttribute("user");

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		// 驗證是否登入
		if (member==null ) {
			errors.put("cart", "要操作購物車，請先登入帳號");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
		} else {
			memberid = member.getMemberid();

			List<MyFavBean> favs = myFavService.selectMyFavs(memberid);
			
			for(MyFavBean myFav : favs) {
				System.out.printf("from Servlet: member = %s,  productimg = %s,  product name = %s,  product price= %d%n",
						myFav.getMember().getName(), myFav.getProduct().getProductpic(), myFav.getProduct().getProductname(),
						myFav.getProduct().getPrice());
			}

			

			request.setAttribute("favs", favs); // 設定參數 這個是Set<MyFavBean>  

			request.getRequestDispatcher("/pages/myFav.jsp").forward(request, response);
		}
		
// 透MemberId呼叫MyFavService的selectFavs方法，全部顯示   在這裡呼叫service  剛剛以會員ID3登入
		
		//
	}

// 刪除收藏 呼叫 Service的deletefav，傳送參數:MemberId  
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean)session.getAttribute("user");
		Integer memberId = member.getMemberid();
		System.out.println("Myfav_do_post_memberId: "+memberId);

		System.out.println("Servlet delete");
		String favProductid = request.getParameter("productid");
		String favDelete = request.getParameter("favDelete");
		Integer productid = Integer.parseInt(favProductid);
		
		myFavService.deletefav(memberId, productid);
		
	}
	
}
