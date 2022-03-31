package kr.sga.gkmarket.userbanned.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.backcategory.vo.BackCategoryVO;
import kr.sga.gkmarket.userbanned.service.UserBannedService;
import kr.sga.gkmarket.userbanned.vo.UserBannedVO;

@Controller

public class UserBannedController {
	
	@Autowired
	private UserBannedService userBannedService;
	
	
	@GetMapping(value = "/MainView/UserBanPage")
	public String UserBannedControlPage() {
		return "/UserBanPage";
	}
	
	@GetMapping("/userBanned/userBannedList") //주소 지정
    @ResponseBody
    public List<UserBannedVO> openUserBannedList(){
		
    	List<UserBannedVO> userlist = userBannedService.getUserList();
    	
		return userlist;
	}
	 @PostMapping(value = "/userBanned/activateBanned")
	 @ResponseBody
	    public void activateBanned(@RequestParam("user_Idx") int user_Idx) {
		 
	    	userBannedService.userBannedActivate(user_Idx);
	    	
	    }
}
