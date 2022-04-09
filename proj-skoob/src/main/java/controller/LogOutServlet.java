package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.service.MemberService;


@WebServlet(urlPatterns={"/pages/logout.controller"})
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService memberService;
	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		memberService = (MemberService)context.getBean("memberService");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null) {
			request.getSession().invalidate();
		}
		String path = request.getContextPath();
		response.sendRedirect(path+"/index.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null) {
			request.getSession().invalidate();
		}
		String path = request.getContextPath();
		response.sendRedirect(path+"/index.jsp");
	}
	
	

}
