package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import model.service.MemberService;

@Controller
public class LogInController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/pages/login.controller")
	public String get() {
		return "/pages/login";
	}
	
}
