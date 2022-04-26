package controller;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.sql.Select;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.cj.xdevapi.Result;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.OrderBean;
import model.bean.OrderitemBean;
import model.bean.ProductBean;
import model.dto.CartDTO;
import model.service.OrderService;

//@WebServlet(urlPatterns = { "/pages/order.controllor" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext) this.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		orderService = (OrderService) context.getBean("orderService");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 確認是否登入
		HttpSession session = req.getSession();
		MemberBean member = (MemberBean) session.getAttribute("user");
	
		List<OrderBean> list = orderService.selectlist(member);
		System.out.println(list);
		for (OrderBean lists : list) {
			Set<OrderitemBean> item = lists.getOrderitems();
			for (OrderitemBean items : item) {
				System.out.println(items.getProduct().getProductname());
			}
		}
		req.setAttribute("lists", list);
		ServletRequest request;
		ServletResponse response;
		req.getRequestDispatcher("/pages/order.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderaction = request.getParameter("orderaction");
		String orderid = request.getParameter("orderid");
		System.out.println(orderaction);

		if (orderaction != null) {

			if (orderaction.equals("checkout")) {
				doPut(request, response);
				return;
			}

			if (orderaction.equals("cancel")) {
				if (orderid != null) {
					OrderBean bean1 = new OrderBean();
					bean1.setOrderid(orderid);
//					bean1.setState("已取消");
					bean1.setState((byte) 3);
					OrderBean update = orderService.update(bean1);
				}
			}
			if (orderaction.equals("done")) {
				OrderBean bean1 = new OrderBean();
				bean1.setOrderid(orderid);
				bean1.setState((byte) 2);
				OrderBean update = orderService.update(bean1);
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 建立訂單
		// --------------------------------------------------------------------

		// 確認是否登入
		Integer memberid = null;
		HttpSession session = req.getSession();
		Object userid = session.getAttribute("user");
		if (userid != null) {
			memberid = ((MemberBean) userid).getMemberid();
		}

		CartDTO cartDTO = (CartDTO) session.getAttribute("cartData");

		// 接收參數
		// 轉換參數
		String delivery = req.getParameter("delivery");
		String payment = req.getParameter("payment");

		System.out.println("delivery = " + delivery);
		System.out.println("payment = " + payment);

		OrderBean order = new OrderBean();
		order.setMemberid(memberid);
		order.setDelivery(delivery);
		order.setPayment(payment);
		order.setTotalprice(cartDTO.getTotalCost());
		order.setState((byte) 0);

		String orderid = orderService.insertOrder(order);

		// 2. 將購物車的資料放入訂單項目
		// --------------------------------------------------------------------
		List<CartBean> carts = orderService.selectAllCart(memberid);
		orderService.insertOrderitem(orderid, carts);

		// 3. 購物車清空
		// --------------------------------------------------------------------
		orderService.deleteCart(carts);
		session.setAttribute("cartData", null);
		
		resp.sendRedirect("/proj-skoob/pages/order.controllor");
	}

}