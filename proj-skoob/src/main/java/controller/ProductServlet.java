package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.bean.AuthorBean;
import model.bean.MemberBean;
import model.bean.ProductBean;
import model.service.AuthorService;
import model.service.MyFavService;
import model.service.ProductService;


//@WebServlet(urlPatterns={"/pages/product.controller"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService;
    private AuthorService authorService;
    private MyFavService myFavService;
    String like = null ;
    String account = null;
    Integer prodid;
	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		productService = (ProductService)context.getBean("productService");
		authorService = (AuthorService)context.getBean("authorService");
		myFavService = (MyFavService)context.getBean("myFavService");
	}
	
//    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
//    	System.out.println("呼叫test方法");
//    	ProductBean bean = productService.selectpro();
//		//導向view
//		if(bean==null) {
////			if(!memberService.isRepeatAccount(account)) {
////				errors.put("useraccount", "登入失敗，帳號錯誤");
////			}else {
////				errors.put("userpassword", "登入失敗，密碼錯誤");				
////			}		
////			request.getRequestDispatcher(
////					"/member/login.jsp").forward(request, response);
//			System.out.println("Errrrrrrror");
//		} else {
//			HttpSession session = request.getSession();
//			session.setAttribute("pro", bean);
//			
//			String path = request.getContextPath();
//			response.sendRedirect(path+"/product.jsp");
//		}
//    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("呼叫doGet方法");
		//prodid = 2;
		//String sprodid = request.getParameter("prodid");
		System.out.println("String prodid: "+request.getParameter("prodid"));
		prodid = Integer.parseInt(request.getParameter("prodid"));
		System.out.println("prodid: "+prodid);
		//呼叫model
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean)session.getAttribute("user");
		
		System.out.println("before----selectpro");
		ProductBean bean = productService.select(prodid);
		System.out.println("after----selectpro");
		
		Integer ahtjor = bean.getAuthor();
		System.out.println(ahtjor);
		System.out.println("before----selectauthor");
		AuthorBean authorbean = authorService.selectauthor(ahtjor);
		System.out.println("after----selectauthor");
		
		System.out.println("member: "+member);
		if(member!=null) {
			Integer memberid = member.getMemberid();			
			account = member.getAccount();
			boolean isfav = myFavService.selectfav(memberid, bean.getProductid());
			System.out.println("isfav: "+isfav);
			if (isfav) {
				//true 表示該會員有將此本書納入收藏
				like = "like";
			}else {
				like = null;
				//false 表示該會員沒有收藏此書
			}
		}
		//導向view
		if(bean==null) {
			System.out.println("Errrrrrrror");
		} else {
			//HttpSession session = request.getSession();
			session.setAttribute("pro", bean);
			session.setAttribute("aut", authorbean);
			session.setAttribute("like", like);
			session.setAttribute("id", bean.getProductid());
			session.setAttribute("account", account);
			System.out.println("before----path");
			String path = request.getContextPath();
			//System.out.println("after----path : "+path);
			response.sendRedirect(path+"/pages/product.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
