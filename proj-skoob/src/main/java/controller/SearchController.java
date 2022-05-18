package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.bean.ProductBean;
import model.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;

	@RequestMapping("/pages/search")
	public String get(String keyword, Model model) {
		System.out.println("keyword = " + keyword);
		Integer count = null;
		List<ProductBean> products = searchService.search(keyword);
		count = products.size();
		model.addAttribute("count", count);
		model.addAttribute("keyword", keyword);
		model.addAttribute("search", products);
		
		return "/pages/search";
	}
}
