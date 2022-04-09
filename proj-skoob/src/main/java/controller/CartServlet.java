package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.ProductBean;
import model.service.CartService;
import model.util.CartUtil;
import model.vo.CartData;

//@WebServlet(urlPatterns = { "/pages/cart.controller" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext) this.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		cartService = context.getBean(CartService.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 確認是否登入
		Integer memberid = null;
		HttpSession session = request.getSession();
		Object userid = session.getAttribute("user");
		if (userid != null) {
			memberid = ((MemberBean) userid).getMemberid();
		}

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		// 驗證是否登入
		if (memberid == null || !cartService.checkMember(memberid)) {
			errors.put("cart", "要操作購物車，請先登入帳號");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		} else {
			List<CartBean> cart = cartService.selectAll(memberid);
			Integer totalCost = CartUtil.getCartTotalCost(cart);
			Integer cartNum = CartUtil.getCartProductNum(cart);
			Integer cartAllNum = CartUtil.getCartProductAllNum(cart);

			CartData cartData = new CartData(totalCost, cartNum, cartAllNum);

			// 根據model執行結果，導向view
			session.setAttribute("cart", cart);
			session.setAttribute("cartData", cartData);

			request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查看前端的呼叫方法
		String cartAction = request.getParameter("cartAction");

		// 根據前端的方法，跳轉到不同的地方處理
		if (cartAction != null) {

			if (cartAction.equals("insert")) {
				// 確認是否登入
				HttpSession session = request.getSession();
				MemberBean member = (MemberBean) session.getAttribute("user");

				String temp = request.getParameter("productid");
				// 轉換資料
				Integer productid = null;
				if (temp != null && temp.length() != 0) {
					productid = Integer.parseInt(temp);
				}

				CartData cartData = null;
				// 呼叫model
				if (member != null && productid != null) {
					cartService.addCart(member, productid);
					List<CartBean> carts = cartService.selectAllHql(member.getMemberid());
					cartData = cartService.getCartData(carts);
				} else {
					response.sendError(response.SC_UNAUTHORIZED, temp);
					return;
				}
				session.setAttribute("cartData", cartData);

				// 跳轉頁面
				String page = request.getParameter("page");
				if (page != null) {
					if (page.equals("product")) {
						request.getRequestDispatcher("/pages/product.controller?prodid=" + productid).forward(request,
								response);
					}
					if (page.equals("myfav")) {
						request.getRequestDispatcher("/favorite/myFav.controller").forward(request, response);
					}
					if (page.equals("search")) {
						request.getRequestDispatcher("/index/search").forward(request, response);
					}
				} else {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}

			}

		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 取得使用者『登入』的資訊
		Integer memberid = null;
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("user");
		
		// 確認是否『登入』
		if (member != null) {
			memberid = ((MemberBean) member).getMemberid();

			// 接受資料 (request body)
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String data = br.readLine();

			// 轉換成 json 格式
			JSONObject json = new JSONObject(data);

			// 判斷前端的動作
			String cartAction = json.getString("cartAction");

			if (cartAction.equals("deleteMulti")) {
				// 解析 json 資料 (解析為陣列)，並轉換成 list
				JSONArray jsonArray = json.getJSONArray("checkid");
				System.out.println("jsonArray = " + jsonArray);
				ArrayList<Integer> prodIdList = new ArrayList();
				for (int i = 0; i < jsonArray.length(); i++) {
					prodIdList.add(jsonArray.getInt(i));
				}

				// 執行刪除的動作
				CartData cartData = null;
				if (memberid != null && prodIdList != null && !prodIdList.isEmpty()) {
					cartService.deleteMulti(memberid, prodIdList);
					List<CartBean> carts = cartService.selectAll(memberid);
					cartData = cartService.getCartData(carts);
				}
				session.setAttribute("cartData", cartData);
				response.setStatus(HttpServletResponse.SC_OK);

			} else if (cartAction.equals("delete")) {
				// 接收資料
				// 轉換資料
				Integer productid = json.getInt("checkid");

				// 執行刪除的動作
				CartData cartData = null;
				if (memberid != null && productid != null) {
					cartService.delete(memberid, productid);
					List<CartBean> carts = cartService.selectAll(memberid);
					cartData = cartService.getCartData(carts);
				}
				session.setAttribute("cartData", cartData);
				response.setStatus(HttpServletResponse.SC_OK);
			}

		} else {
			response.sendError(response.SC_UNAUTHORIZED, "請登入帳號");
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPut");

		// 取得使用者『登入』的資訊
		Integer memberid = null;
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("user");
		
		// 確認是否『登入』
		if (member != null && member.getMemberid() != null && member.getMemberid() != 0) {
			
			// 接受 request body
			BufferedReader br = request.getReader();
			String data = br.readLine();

			// 轉換成 json 格式
			JSONObject json = new JSONObject(data);
			
			// 解析/轉換 json 資料
			memberid = ((MemberBean) member).getMemberid();
			Integer productid = null;
			Integer number = null;
			Integer subtotal = null;
			
			try {
				productid = json.getInt("productid");
				number = json.getInt("number");
				subtotal = json.getInt("subtotal");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			// 呼叫Model
			CartBean cart = new CartBean();
			cart.setMemberid(memberid);
			cart.setProductid(productid);
			cart.setNumber(number);
			cart.setSubtotal(subtotal);

			// 根據 Model 執行結果導向 View
			if (productid != null && number != null && subtotal != null) {
				cartService.update(cart);
			} else {
				System.out.println("前端頁面，出現了無法預期的錯誤");
				response.sendError(response.SC_UNAUTHORIZED, "購物車出現異常的操錯");
			}

		}

	}

}
