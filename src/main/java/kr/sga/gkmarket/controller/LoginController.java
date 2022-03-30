package kr.sga.gkmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/authenticate")
public class LoginController {
	
	@ApiOperation(value = "로그인 폼 이동", notes = "토큰 인증 로그인 폼으로 이동합니다.(POST)")
	@GetMapping(value = "/login")
    public String LoginPageGet() {
        return "/login-form";
	}
	
	@ApiOperation(value = "로그인 폼 이동", notes = "토큰 인증 로그인 폼으로 이동합니다.(POST)")
	@PostMapping(value = "/login")
    public String LoginPagePost() {
        return "/login-form";
	}
	
	@ApiOperation(value = "테스트 페이지", notes = "추후 삭제 예정")
	@GetMapping(value = "/test")
	public String testPage() {
		return "/index_test";
	}
}
