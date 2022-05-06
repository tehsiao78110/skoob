package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.OrderBean;
import model.bean.OrderitemBean;
import model.dto.CartDTO;
import model.service.MemberService;
import model.service.OrderService;

@Controller
@RequestMapping("/pages/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;

	// 正規表達式
	private final Pattern nameRegex = Pattern.compile("^[\\u4e00-\\u9fa5]+$|^[a-zA-Z\\s]+$");
	private final Pattern phoneRegex = Pattern.compile("[0-9]{10}");

	@GetMapping
	public String getList(Model model, HttpSession session) {
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");

		// 驗證是否登入
		if (member == null || member.getMemberid() == null || !memberService.checkAccountExist(member.getMemberid())) {
			return "redirect:/pages/login";
		} else {
			List<OrderBean> list = orderService.selectOrderList(member);
			model.addAttribute("lists", list);
			return "/pages/order_list";
		}
	}

	@GetMapping("/{orderid}")
	public String get(@PathVariable("orderid") String orderid, HttpSession session) {
		System.out.println("orderid = " + orderid);
		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");
		// 驗證是否登入
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			OrderBean order = orderService.getOrder(member, orderid);
			Set<OrderitemBean> orderitem = orderService.getOrderItem(order);
			if (order != null && orderitem != null) {
				session.setAttribute("order", order);
				session.setAttribute("orderitem", orderitem);
				return "/pages/order";
			} else {
				return "/index";
			}
		}
		return "redirect:/pages/login";
	}

	@PostMapping
	public String post(OrderBean order, Model model, HttpSession session) {
		// 接收資料
		// 轉換資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		String name = order.getName();
		String phone = order.getPhone();

		// 檢查「姓名」
		if (name == null || name.length() == 0) {
			errors.put("name", "請輸入姓名");
		} else if (!nameRegex.matcher(name).find()) {
			errors.put("name", "姓名必須是中英文");
		}

		// 檢查「電話」
		if (phone == null || phone.length() == 0) {
			errors.put("phone", "請輸入電話");
		} else if (!phoneRegex.matcher(phone).find()) {
			errors.put("phone", "電話格式必須是10個數字");
		}

		if (errors != null && !errors.isEmpty()) {
			return "/pages/checkout";
		}

		// 取得登入的資訊
		MemberBean member = (MemberBean) session.getAttribute("user");
		// 取得購物車的商品資料
		CartDTO cartDto = (CartDTO) session.getAttribute("cartDto");

		// 驗證是否登入
		if (member != null && member.getMemberid() != null && memberService.checkAccountExist(member.getMemberid())) {
			try {
				order.setMemberid(member.getMemberid());
				order.setTotalprice(cartDto.getTotalCost());
				order.setState((byte) 0);
				orderService.CreateOrder(order);
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("fail", "發生錯誤，建立訂單失敗");
				return "/pages/checkout";
			}

			session.setAttribute("cartDto", null);
			return "redirect:/pages/order";
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
				return ResponseEntity.status(HttpStatus.OK).body("訂單狀態更新成功");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("訂單出現異常的操作");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入帳號");
	}

}
