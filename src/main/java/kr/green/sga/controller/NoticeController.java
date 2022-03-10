package kr.green.sga.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.green.sga.service.NoticeService;
import kr.green.sga.vo.BackNoticeVO;


@Controller 

public class NoticeController {
	
    @Autowired
    private NoticeService noticeService; //서비스 연결

    @RequestMapping("/notice") //주소 지정
    @ResponseBody
    public String openNoticeList() throws Exception {
    	JsonObject jo = new JsonObject();
    	List<BackNoticeVO> list = noticeService.getNotice();
    	
    	jo.addProperty("notice", list.toString());
    	return jo.toString();
    }
    

    
}