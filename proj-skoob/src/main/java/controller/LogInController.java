package controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.dto.CartDTO;
import model.service.CartService;
import model.service.MemberService;
import util.CartUtil;

@Controller
public class LogInController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private CartService cartService;
	
	@GetMapping("/pages/login.controller")
	public String get() {
		return "/pages/login";
	}

	@PostMapping("/pages/login.controller")
	public String post(String account, String password, Model model,HttpSession session) {
		System.out.println("account = " + account);
		System.out.println("password = " + password);

		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		if (account == null || account.length() == 0) {
			errors.put("useraccount", "請輸入帳號");
		}
		if (password == null || password.length() == 0) {
			errors.put("userpassword", "請輸入密碼");
		}

		if (errors != null && !errors.isEmpty()) {
			return "/pages/login";
		}
		// 呼叫 model
		MemberBean member = memberService.login(account, password);

		// 如果登入失敗
		if (member == null) {
			// 查看帳號是否在，如果存在，就是密碼錯誤
			if (memberService.checkAccountExist(account)) {
				errors.put("userpassword", "登入失敗，密碼錯誤");
			} else {
				errors.put("useraccount", "登入失敗，帳號並不存在");
			}
			return "/pages/login";
		} else {
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			String birth = sFormat.format(member.getBirth());
			session.setAttribute("user", member);
			session.setAttribute("birth", birth);
			
			// 登入成功後，要將該「會員購物車」裡面的商品，全部顯示出來
			List<CartBean> cart = cartService.selectAll(member.getMemberid());
			// 算出購物車裡面的必要資訊，儲存在 cartDto 中
			CartDTO cartDto = CartUtil.toCartDto(cart);
			
			session.setAttribute("cartDto", cartDto);
			
			return "redirect:/index";
		}
	}

}
