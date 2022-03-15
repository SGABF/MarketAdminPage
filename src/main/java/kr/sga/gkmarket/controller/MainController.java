package kr.sga.gkmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	// '/'지점 지정
	@RequestMapping(value = "/")
    public String rootPage() {
		return "/login-form";
	}
	
	//RestFul API 테스트용 추후 삭제 예정
	@RequestMapping(value = "/hello")
	@ResponseBody
    public String restTestPage() {
        return "Hello. you have valid JWT (JSon Web Token)!";
	}
	
	
	@RequestMapping(value = "/MainView/MainPage")
	public String MainPage() {
		return "/MainPage";
	}
}
