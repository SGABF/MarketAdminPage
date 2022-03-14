package kr.sga.gkmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping(value = "/")
    public String rootPage() {
		return "/login-form";
	}
	
	@RequestMapping(value = "/hello")
	@ResponseBody
    public String restTestPage() {
        return "Hello. you have valid JWT (JSon Web Token)!";
	}
	
	
	@RequestMapping(value = "/MainPage")
	public String ainPage() {
		return "/MainPage";
	}
	
}
