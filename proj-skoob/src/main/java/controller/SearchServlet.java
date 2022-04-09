package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.bean.ProductBean;
import model.service.SearchService;

@WebServlet(urlPatterns={"/index/search"})
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService;
	
	@Override
	public void init() throws ServletException {
		ApplicationContext context = (ApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		searchService = (SearchService)context.getBean("searchService");
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String keyword = request.getParameter("keyword");
		Integer count = null;
		System.out.println("keyword :"+keyword);
		List<ProductBean> product = searchService.search(keyword);
		System.out.println("product :"+product);
		count = product.size();
		System.out.println("count :"+count);
		
		
		session.setAttribute("count", count);
		session.setAttribute("keyword", keyword);
		request.setAttribute("search", product);
		request.getRequestDispatcher(
				"/pages/search.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
