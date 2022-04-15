package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.dto.CartDTO;
import model.service.CartService;
import util.CartUtil;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping(path = { "/pages/cart.controller" })
	public String get(Model model, HttpSession session) {
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		// 驗證是否登入
		if (member == null || member.getMemberid() == null || !cartService.checkMember(member.getMemberid())) {
			errors.put("cart", "要操作購物車，請先登入帳號");
			model.addAttribute("errors", errors);
			return "/pages/login";
		} else {
			List<CartBean> carts = cartService.selectAll(member.getMemberid());
			CartDTO cartDto = CartUtil.toCartDto(carts);

			// 根據model執行結果，導向view
			session.setAttribute("carts", carts);
			session.setAttribute("cartDto", cartDto);

			return "/pages/cart";
		}

	}

	@PostMapping(path = { "/pages/cart.controller" })
	public ResponseEntity post(String cartAction, Integer productid, String page, HttpSession session) {
		System.out.println("cartAction = " + cartAction);
		System.out.println("productid = " + productid);
		System.out.println("page = " + page);
		if (cartAction != null && cartAction.equals("insert") && page != null) {
			// 確認是否登入
			MemberBean member = (MemberBean) session.getAttribute("user");

			// 呼叫model
			if (member != null && productid != null) {
				cartService.addCart(member, productid);
				List<CartBean> carts = cartService.selectAllHql(member.getMemberid());
				CartDTO cartDTO = CartUtil.toCartDto(carts);
				session.setAttribute("cartDto", cartDTO);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@PutMapping(path = { "/pages/cart.controller" })
	public void put(HttpSession session, CartBean bean) {
		System.out.println("productid =" + bean.getProductid());
		System.out.println("subtotal =" + bean.getSubtotal());
		System.out.println("number =" + bean.getNumber());
		
		// 轉換成 json 格式
//		JSONObject json = new JSONObject(bean);
//		System.out.println("json =" + json);
		
		// 取得使用者『登入』的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");
		Integer memberid = null;
		
		// 確認是否『登入』
		if (member != null && member.getMemberid() != null && member.getMemberid() != 0) {
			
		}
	}
	
}
