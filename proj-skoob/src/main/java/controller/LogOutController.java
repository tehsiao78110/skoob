package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/logout.controller")
public class LogOutController {

	@GetMapping
	public String get(HttpSession session) {
		System.out.println("LogOut OK!!!");
		if (session.getAttribute("user") != null) {
			session.invalidate();
		}
		return "/index";
	}
}
