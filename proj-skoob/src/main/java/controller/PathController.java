package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PathController {

	@GetMapping({"/","/index"})
	public String name() {
		return "/index";
	}
	
	@GetMapping("/pages/checkout.controller")
	public String checkout() {
		return "/pages/checkout";
	}
}
