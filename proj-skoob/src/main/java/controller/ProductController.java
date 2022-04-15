package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import model.bean.MemberBean;
import model.bean.ProductBean;
import model.service.AuthorService;
import model.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private AuthorService authorService;
	

	@GetMapping(path = "/pages/product.controller")
	public void get(int prodid, HttpSession session) {
		System.out.println("prodid = " + prodid);
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");
		System.out.println("member = " + member);
		
		ProductBean bean = productService.select(prodid);
	}
	
}
