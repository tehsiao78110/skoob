package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import model.bean.AuthorBean;
import model.bean.MemberBean;
import model.bean.ProductBean;
import model.service.AuthorService;
import model.service.MyFavService;
import model.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private MyFavService myFavService;

	@GetMapping(path = "/pages/product.controller")
	public String get(int prodid, Model model, HttpSession session) {
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 取得要顯示的「商品資料」
		ProductBean product = productService.select(prodid);
		// 取得要顯示的「作者資料」
		AuthorBean authorbean = authorService.selectauthor(product.getAuthor());

		// 我的最愛資料，用來判斷「收藏」按鈕
		String like = null;
		model.addAttribute("like", like);

		// 驗證是否登入
		if (member != null && member.getMemberid() != null) {
			boolean isfav = myFavService.checkMyfav(member.getMemberid(), product.getProductid());
			// true 表示該會員有將此本書納入收藏
			if (isfav) {
				like = "like";
			}
		}

		// 導向view
		model.addAttribute("pro", product);
		model.addAttribute("aut", authorbean);
		model.addAttribute("like", like);
		return "/pages/product";
	}

}
