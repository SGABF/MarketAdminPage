package kr.sga.gkmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/authenticate")
public class LoginController {
	
	@RequestMapping(value = "/login")
    public String LoginPage() {
        return "/login-form";
	}
	
	@RequestMapping(value = "/test")
	public String testPage() {
		return "/index";
	}
}
