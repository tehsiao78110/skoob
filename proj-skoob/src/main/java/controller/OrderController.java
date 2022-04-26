package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.OrderBean;
import model.dto.CartDTO;
import model.service.MemberService;
import model.service.OrderService;

@Controller
@RequestMapping("/pages/order.controllor")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;

	@GetMapping
	public String get(Model model, HttpSession session) {
		System.out.println("order get OK!!");
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 驗證是否登入
		if (member == null || member.getMemberid() == null || !memberService.checkAccountExist(member.getMemberid())) {
			return "redirect:/pages/login";
		} else {
			List<OrderBean> list = orderService.selectlist(member);
			model.addAttribute("lists", list);
			return "/pages/order";
		}
	}

	@PostMapping
	public String post(String payment, String delivery, Model model, HttpSession session) {
		System.out.println("payment = " + payment);
		System.out.println("delivery = " + delivery);
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");
		// 取得購物車的商品資料
		CartDTO cartDto = (CartDTO) session.getAttribute("cartDto");

		// 驗證是否登入
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			// 1. 建立訂單
			// --------------------------------------------------------------------
			OrderBean order = new OrderBean();
			order.setMemberid(member.getMemberid());
			order.setDelivery(delivery);
			order.setPayment(payment);
			order.setTotalprice(cartDto.getTotalCost());
			order.setState((byte) 0);

			String orderid = orderService.insertOrder(order);
			// 2. 將購物車的資料放入訂單項目
			// --------------------------------------------------------------------
			List<CartBean> carts = orderService.selectAllCart(member.getMemberid());
			orderService.insertOrderitem(orderid, carts);

			// 3. 購物車清空
			// --------------------------------------------------------------------
			orderService.deleteCart(carts);
			session.setAttribute("cartDto", null);

			return "redirect:/pages/order.controllor";
		}
		return "redirect:/pages/login";
	}

	@PutMapping
	public ResponseEntity put(@RequestBody String body, HttpSession session) {
		// 取得使用者『登入』的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 確認是否『登入』
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			// 轉換成 json 格式
			JSONObject json = new JSONObject(body);

			// 解析/轉換 json 資料
			String orderid = null;
			String orderAction = null;

			try {
				orderid = json.getString("orderid");
				orderAction = json.getString("orderAction");
			} catch (JSONException e) {
				e.printStackTrace();
			}

			if (orderAction != null && orderid != null) {
				// 判斷執行的動作
				if (orderAction.equals("cancel")) {
					OrderBean bean1 = new OrderBean();
					bean1.setOrderid(orderid);
					bean1.setState((byte) 3);
					OrderBean update = orderService.update(bean1);
				} else if (orderAction.equals("done")) {
					OrderBean bean1 = new OrderBean();
					bean1.setOrderid(orderid);
					bean1.setState((byte) 2);
					OrderBean update = orderService.update(bean1);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("未知的操作指令");
				}
				return ResponseEntity.status(HttpStatus.OK).body(null);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("訂單出現異常的操作");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入帳號");
	}

}
