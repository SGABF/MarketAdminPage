package kr.sga.gkmarket.notice.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.notice.service.NoticeService;
import kr.sga.gkmarket.notice.vo.BackNoticeVO;


@Controller 

public class NoticeController {
	
    @Autowired
    private NoticeService noticeService; //서비스 연결

    @RequestMapping("/notice") //주소 지정
    @ResponseBody
    public String openNoticeList(@ModelAttribute BackNoticeVO backNoticeVO , Model model){
    	JsonObject jo = new JsonObject();
    	List<BackNoticeVO> list = noticeService.getNotice();
    	
    	jo.addProperty("notice", list.toString());
    	model.addAttribute(list);
    	return jo.toString();
    }
    @RequestMapping(value = "/notice/insertNotice", method=RequestMethod.POST)
	public String insertNotice(@RequestBody BackNoticeVO backNoticeVO , Model model) {
		noticeService.insertNotice(backNoticeVO); // DB에 저장
		model.addAttribute("vo",backNoticeVO);
	
		return "redirect:/notice";
	}
    @RequestMapping(value = "/notice/updateNotice" ,method=RequestMethod.POST)
    public String updateNotice(@RequestBody BackNoticeVO backNoticeVO , Model model) {
    	noticeService.updateNotice(backNoticeVO);
    	model.addAttribute("vo",backNoticeVO);
		return "redirect:/notice";
    	
    }
    @RequestMapping(value="/notice/deleteNotice" , method=RequestMethod.DELETE)
    public String deleteNotice(@RequestParam("backNotice") int backNotice){
        
        noticeService.deleteNotice(backNotice);
        return "redirect:/notice";
    }


    
    
}