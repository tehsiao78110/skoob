package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.service.CartService;
import model.util.CartUtil;
import model.vo.CartData;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping(path = { "/pages/cart.controller" })
	public String get(Model model, HttpSession session) {
		// 取得登入的資訊
		MemberBean memeber = (MemberBean) session.getAttribute("user");

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		// 驗證是否登入
		if (memeber == null || memeber.getMemberid() == null || !cartService.checkMember(memeber.getMemberid())) {
			errors.put("cart", "要操作購物車，請先登入帳號");
			model.addAttribute("errors", errors);
			return "/pages/login";
		} else {
			List<CartBean> cart = cartService.selectAll(memeber.getMemberid());
			Integer totalCost = CartUtil.getCartTotalCost(cart);
			Integer cartNum = CartUtil.getCartProductNum(cart);
			Integer cartAllNum = CartUtil.getCartProductAllNum(cart);

			CartData cartData = new CartData(totalCost, cartNum, cartAllNum);

			// 根據model執行結果，導向view
			session.setAttribute("cart", cart);
			session.setAttribute("cartData", cartData);

			return "/pages/cart";
		}
		
	}

}
