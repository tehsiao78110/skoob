package controller;

import java.io.IOException;
import java.util.HashMap;
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

import model.bean.MemberBean;
import model.bean.MyFavBean;
import model.bean.ProductBean;
import model.service.MyFavService;

//favorite是空的


@WebServlet(urlPatterns = { "/pages/Fav.controller" })
public class FavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyFavService myFavService;
	private Integer productId;
	private Integer memberId;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext) this.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		myFavService = context.getBean(MyFavService.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//20210831
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean)session.getAttribute("user");
		System.out.println("Favmember : "+member);
		
		ProductBean product = (ProductBean)session.getAttribute("pro");
		System.out.println("Favproduct : "+product);
		
		String prodaction = request.getParameter("prodaction");
		System.out.println("Favprodaction : "+prodaction);
		//20210831
		//20210907
		String proAction = request.getParameter("proaction");
		System.out.println("proAction: "+proAction);
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		// 驗證是否登入
		if (member==null ) {
		//if (memberId == null || !myFavService.checkMember(memberId)|| member==null ) {
			errors.put("cart","要操作購物車，請先登入帳號");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}else { //20210831
			memberId = member.getMemberid();
			System.out.println("FavmemberId : "+memberId);
			productId = product.getProductid();
			System.out.println("FavproductId : "+productId);
		} //20210831
		//20210831
		//先判斷資料有沒有存在在收藏表中 如果有在表中要delete
		boolean isexist = myFavService.checkMyfav(memberId,productId);
		String like = null;
		if(isexist) {
			//執行delete
			System.out.println("Fav執行delete");
			myFavService.deletefav(memberId, productId);
			System.out.println("delete後的: "+like);
			//request.setAttribute("like", like);
		}else {
			MyFavBean bean = new MyFavBean();
			bean.setProductId(productId); //傳入Service
			bean.setMemberId(memberId);
			MyFavBean myFavbean = myFavService.insertA(bean);
			like = "like";
			System.out.println("Favinsert後的: "+like);
		}
		request.setAttribute("like", like);
		String path = request.getContextPath();
		//response.sendRedirect(path+"/product/product.jsp"); 這收藏沒成功
		request.getRequestDispatcher("/product/product.jsp").forward(request, response); //這OK
		//request.getRequestDispatcher("/product/product.controller?proid="+productId).forward(request, response);
		//20210831
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
