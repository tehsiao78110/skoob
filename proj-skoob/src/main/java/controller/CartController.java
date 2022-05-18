package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import model.service.MemberService;
import util.CartUtil;

@Controller
@RequestMapping("/pages/cart.controller")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private MemberService memberService;

	@GetMapping
	public String get(Model model, HttpSession session) {
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		// 驗證是否登入
		if (member == null || member.getMemberid() == null || !memberService.checkAccountExist(member.getMemberid())) {
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

	@PostMapping
	public ResponseEntity post(String cartAction, Integer productid, HttpSession session) {
		if (cartAction != null && cartAction.equals("insert")) {
			// 確認是否登入
			MemberBean member = (MemberBean) session.getAttribute("user");

			// 呼叫model
			if (member != null && productid != null) {
				cartService.addCart(member, productid);
				// 更新「購物車顯示資料」
				List<CartBean> carts = cartService.selectAll(member.getMemberid());
				CartDTO cartDTO = CartUtil.toCartDto(carts);
				session.setAttribute("cartDto", cartDTO);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@PutMapping
	public ResponseEntity put(HttpSession session, @RequestBody String body) {

		// 取得使用者『登入』的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 確認是否『登入』
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			// 轉換成 json 格式
			JSONObject json = new JSONObject(body);

			// 解析/轉換 json 資料
			Integer productid = null;
			Integer number = null;
			Integer subtotal = null;

			try {
				productid = json.getInt("productid");
				number = json.getInt("number");
				subtotal = json.getInt("subtotal");
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 呼叫Model
			CartBean cart = new CartBean();
			cart.setMemberid(member.getMemberid());
			cart.setProductid(productid);
			cart.setNumber(number);
			cart.setSubtotal(subtotal);

			// 根據 Model 執行結果導向 View
			if (productid != null && number != null && subtotal != null) {
				cartService.update(cart);
				return ResponseEntity.status(HttpStatus.OK).body("購物車資料更新成功");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("購物車出現異常的操作");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入帳號");
	}

	@DeleteMapping
	public ResponseEntity delete(HttpSession session, @RequestBody String body) {
		// 取得使用者『登入』的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 確認是否『登入』
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			Integer memberid = member.getMemberid();

			// 轉換成 json 格式
			JSONObject json = new JSONObject(body);

			// 判斷前端的動作
			String cartAction = json.getString("cartAction");

			if (cartAction.equals("deleteMulti")) {
				// 解析 json 資料 (解析為陣列)，並轉換成 list
				JSONArray jsonArray = json.getJSONArray("checkid");
				ArrayList<Integer> prodIdList = new ArrayList();
				for (int i = 0; i < jsonArray.length(); i++) {
					prodIdList.add(jsonArray.getInt(i));
				}

				// 執行刪除的動作
				CartDTO cartDTO = null;
				if (prodIdList != null && !prodIdList.isEmpty()) {
					cartService.deleteMulti(memberid, prodIdList);
					List<CartBean> carts = cartService.selectAll(memberid);
					cartDTO = CartUtil.toCartDto(carts);
				}
				session.setAttribute("cartData", cartDTO);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			} else if (cartAction.equals("delete")) {
				// 接收資料
				// 轉換資料
				Integer productid = json.getInt("checkid");

				// 執行刪除的動作
				CartDTO cartDTO = null;
				if (productid != null) {
					cartService.delete(memberid, productid);
					List<CartBean> carts = cartService.selectAll(memberid);
					cartDTO = CartUtil.toCartDto(carts);
				}
				session.setAttribute("cartData", cartDTO);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

}
