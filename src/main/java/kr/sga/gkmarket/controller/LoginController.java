package kr.sga.gkmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/authenticate")
public class LoginController {
	
	@GetMapping(value = "/login")
    public String LoginPageGet() {
        return "/login-form";
	}
	
	@PostMapping(value = "/login")
    public String LoginPagePost() {
        return "/login-form";
	}
	
	@GetMapping(value = "/test")
	public String testPage() {
		return "/index_test";
	}
}
