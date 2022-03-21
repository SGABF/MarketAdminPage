package kr.sga.gkmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	// '/'지점 지정
	@GetMapping(value = "/")
    public String rootPageGet() {
		return "/login-form";
	}
	
	@PostMapping(value = "/")
    public String rootPagePost() {
		return "/login-form";
	}
	
	//RestFul API 테스트용 추후 삭제 예정
	@GetMapping(value = "/hello")
	@ResponseBody
    public String restTestPage() {
        return "Hello. you have valid JWT (JSon Web Token)!";
	}
	
	
	@GetMapping(value = "/MainView/MainPage")
	public String MainPage() {
		return "/MainPage";
	}
	
	//----------------------------------------sitemesh적용을 위한 임시--------------------------------
	
	@GetMapping(value = "/MainView/buttons")
	public String buttonsPage() {
		return "/buttons";
	}
	
	@GetMapping(value = "/MainView/cards")
	public String cardsPage() {
		return "/cards";
	}
	
	@GetMapping(value = "/MainView/utilities-color")
	public String utilitiesColorPage() {
		return "/utilities-color";
	}
	
	@GetMapping(value = "/MainView/utilities-border")
	public String utilitiesBorderPage() {
		return "/utilities-border";
	}
	@GetMapping(value = "/MainView/utilities-animation")
	public String utilitiesAnimationPage() {
		return "/utilities-animation";
	}
	@GetMapping(value = "/MainView/utilities-other")
	public String utilitiesOtherPage() {
		return "/utilities-other";
	}
	@GetMapping(value = "/MainView/register")
	public String registerPage() {
		return "/register";
	}
	@GetMapping(value = "/MainView/login")
	public String MainViewloginPage() {
		return "/login";
	}
	@GetMapping(value = "/MainView/forgot-password")
	public String forgotPasswordPage() {
		return "/forgot-password";
	}
	@GetMapping(value = "/MainView/404")
	public String notFoundPage() {
		return "/404";
	}
	@GetMapping(value = "/MainView/blank")
	public String blankPage() {
		return "/blank";
	}
	@GetMapping(value = "/MainView/charts")
	public String chartsPage() {
		return "/charts";
	}
	@GetMapping(value = "/MainView/tables")
	public String tablesPage() {
		return "/tables";
	}
	@GetMapping(value = "/MainView/adminAccount")
	public String adminAccount() {
		return "/adminAccount";
	}
	
	//---------------------------------------------------------------------------------------------------
	
}
