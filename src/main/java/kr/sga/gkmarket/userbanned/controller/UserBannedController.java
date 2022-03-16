package kr.sga.gkmarket.userbanned.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping("/userBanned") //주소 지정
    @ResponseBody
    public String openUserBannedList(@RequestBody UserBannedVO userBannedVO){
    	JsonObject jo = new JsonObject();
    	List<UserBannedVO> list = userBannedService.getUserList();
    	jo.addProperty("userBanned", list.toString());
		return jo.toString();
	}
	 @RequestMapping(value = "/userBanned/activate", method=RequestMethod.POST)
	    public String activateCategory(@RequestParam(required = false) int user_Idx) {
	    	UserBannedVO dbvo = null;
	    	dbvo = userBannedService.getUser(user_Idx);
	    	userBannedService.userBannedActivate(dbvo);
	    	
			return "/userBanned/activate";
	    	
	    }
}
