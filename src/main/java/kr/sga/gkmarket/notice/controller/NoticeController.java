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
import lombok.extern.slf4j.Slf4j;


@Controller 
@Slf4j
public class NoticeController {
	
    @Autowired
    private NoticeService noticeService; //서비스 연결

    @RequestMapping("/blank") //주소 지정
    @ResponseBody
    public String openNoticeList(@ModelAttribute BackNoticeVO backNoticeVO , Model model){
    	log.info("NoticeController-openNoticeList 호출 : " + backNoticeVO);
    	if(backNoticeVO!=null) {
    		List<BackNoticeVO> list = noticeService.getNotice();
    		model.addAttribute("vo", list);
    	}
    	log.info("NoticeController-openNoticeList 리턴 : " +  model.toString());
    	return model.toString();
    }
    @RequestMapping(value = "/blank/insertNotice", method=RequestMethod.POST)
	public String insertNotice(@RequestBody BackNoticeVO backNoticeVO , Model model) {
		noticeService.insertNotice(backNoticeVO); // DB에 저장
		model.addAttribute("vo",backNoticeVO);
	
		return "redirect:/notice";
	}
    @RequestMapping(value = "/blank/updateNotice" ,method=RequestMethod.POST)
    public String updateNotice(@RequestBody BackNoticeVO backNoticeVO , Model model) {
    	noticeService.updateNotice(backNoticeVO);
    	model.addAttribute("vo",backNoticeVO);
		return "redirect:/notice";
    	
    }
    @RequestMapping(value="/blank/deleteNotice" , method=RequestMethod.DELETE)
    public String deleteNotice(@RequestParam("backNotice") int backNotice){
        
        noticeService.deleteNotice(backNotice);
        return "redirect:/notice";
    }


    
    
}