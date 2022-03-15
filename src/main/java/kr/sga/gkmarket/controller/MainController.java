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
	
	//----------------------------------------sitemesh적용을 위한 임시--------------------------------
	
	@RequestMapping(value = "/MainView/buttons")
	public String buttonsPage() {
		return "/buttons";
	}
	
	@RequestMapping(value = "/MainView/cards")
	public String cardsPage() {
		return "/cards";
	}
	
	@RequestMapping(value = "/MainView/utilities-color")
	public String utilitiesColorPage() {
		return "/utilities-color";
	}
	
	@RequestMapping(value = "/MainView/utilities-border")
	public String utilitiesBorderPage() {
		return "/utilities-border";
	}
	@RequestMapping(value = "/MainView/utilities-animation")
	public String utilitiesAnimationPage() {
		return "/utilities-animation";
	}
	@RequestMapping(value = "/MainView/utilities-other")
	public String utilitiesOtherPage() {
		return "/utilities-other";
	}
	@RequestMapping(value = "/MainView/register")
	public String registerPage() {
		return "/register";
	}
	@RequestMapping(value = "/MainView/login")
	public String MainViewloginPage() {
		return "/login";
	}
	@RequestMapping(value = "/MainView/forgot-password")
	public String forgotPasswordPage() {
		return "/forgot-password";
	}
	@RequestMapping(value = "/MainView/404")
	public String notFoundPage() {
		return "/404";
	}
	@RequestMapping(value = "/MainView/blank")
	public String blankPage() {
		return "/blank";
	}
	@RequestMapping(value = "/MainView/charts")
	public String chartsPage() {
		return "/charts";
	}
	@RequestMapping(value = "/MainView/tables")
	public String tablesPage() {
		return "/tables";
	}
	
	//---------------------------------------------------------------------------------------------------
	
}
