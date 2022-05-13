package controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.bean.MemberBean;
import model.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	// 正規表達式
	private final Pattern accountRegex = Pattern.compile("[A-Za-z0-9]{6,}");
	private final Pattern passwordRegex = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
	private final Pattern phoneRegex = Pattern.compile("^09[0-9]{8}$");
	private final Pattern telRegex = Pattern.compile("^0\\d{9}$");
	private final Pattern emailRegex = Pattern
			.compile("[a-z0-9A-Z_-]+([.][a-z0-9A-Z_-]+)*@[a-z0-9A-Z]+([.][a-z0-9A-Z_-]+)*$");

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
		webDataBinder.registerCustomEditor(java.util.Date.class, dateEditor);
	}

	@GetMapping("/pages/user/register")
	public String getRegister() {
		return "/pages/register";
	}

	@GetMapping("/pages/user/edit")
	public String getEdit(HttpSession session) {
		// 取得登入的資訊
		MemberBean user = (MemberBean) session.getAttribute("user");
		// 驗證是否登入
		if (user != null && user.getMemberid() != null && memberService.checkAccountExist(user.getMemberid())) {
			return "/pages/edit";
		}
		return "/pages/login";
	}

	@PostMapping("/pages/user/register")
	public String post(Model model, String confirmation, MemberBean member, BindingResult bindingResult) {
		// 接收資料
		// 轉換資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		// 檢查帳號
		if (!accountRegex.matcher(member.getAccount()).find()) {
			errors.put("account", "帳號必須是6個以上的英文和數字");
		} else if (memberService.checkAccountExist(member.getAccount())) {
			errors.put("account", "此帳號已被註冊");
		}

		// 檢查密碼
		if (!passwordRegex.matcher(member.getPassword()).find()) {
			errors.put("password", "密碼長度至少為8，至少要有1個英文和數字");
		}

		// 確認密碼
		if (!confirmation.equals(member.getPassword())) {
			errors.put("confirmation", "與密碼必須要一致");
		}

		// 確認email
		if (!emailRegex.matcher(member.getEmail()).find()) {
			errors.put("email", "請確認email格式正確");
		} else if (memberService.isRepeatEmail(member)) {
			errors.put("email", "此電子信箱已被註冊");
		}

		if (errors != null && !errors.isEmpty()) {
			return "/pages/register";
		}

		MemberBean result = memberService.insert(member);

		if (result != null) {
			return "/index";
		} else {
			errors.put("action", "註冊失敗");
			return "/pages/register";
		}

	}

	@PostMapping("/pages/user/edit")
	public String put(MemberBean member, BindingResult bindingResult, Model model, HttpSession session) {
		System.out.println("member = " + member);
		// 取得登入的資訊
		MemberBean user = (MemberBean) session.getAttribute("user");
		System.out.println("user = " + user);
		
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		// 驗證是否登入
		if (user != null && user.getMemberid() != null && memberService.checkAccountExist(user.getMemberid())) {
			// 接收資料
			// 轉換資料
			if (bindingResult != null && bindingResult.hasFieldErrors()) {
				if (bindingResult.hasFieldErrors("birth")) {
					errors.put("birth", "生日必須是日期，並且符合YYYY-MM-DD格式");
				}
			}

			// 檢查資料
			if (!phoneRegex.matcher(member.getPhone()).find()) {
				errors.put("phone", "手機格式必須是09開頭的10位數");
			}

			if (!telRegex.matcher(member.getTel()).find()) {
				errors.put("tel", "市話格式必須是0開頭的10位數");
			}
			
			if (errors != null && !errors.isEmpty()) {
				System.out.println("errors = " + errors);
				return "/pages/edit";
			}
			
			// 呼叫Model
			member.setMemberid(user.getMemberid());
			MemberBean result = memberService.edit(member);
			
			Map<String, String> edit = new HashMap<String, String>();
			model.addAttribute("edit", edit);
			
			// 根據Model執行結果導向View
			if (result == null) {
				edit.put("action", "發生錯誤，修改失敗!");
			} else {
				edit.put("action", "修改成功。");
				model.addAttribute("user", result);
			}
			
			return "/pages/edit";
		} else {
			return "redirect:/pages/login.controller";
		}

	}

}
