package kr.sga.gkmarket.notice.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.notice.service.NoticeFileService;
import kr.sga.gkmarket.notice.service.NoticeService;
import kr.sga.gkmarket.notice.vo.BackNoticeFileVO;
import kr.sga.gkmarket.notice.vo.BackNoticeVO;
import lombok.extern.slf4j.Slf4j;


@Controller 
@Slf4j
public class NoticeController {
	
    @Autowired
    private NoticeService noticeService; //서비스 연결
    @Autowired
    private NoticeFileService noticeFileService;

    private String os = System.getProperty("os.name").toLowerCase();
    
    @GetMapping(value = "/notice") //주소 지정
    @ResponseBody
    public List<BackNoticeVO> getNoticeList(){
    	log.info("NoticeController-openNoticeList 호출");
    	List<BackNoticeVO> list = noticeService.getNotice();
    	log.info("NoticeController-openNoticeList 리턴 : " +  list);
    	return list;
    }
    @PostMapping(value = "/notice/insertNotice")
    @ResponseBody
	public String insertNotice(@RequestBody BackNoticeVO backNoticeVO ) {
		noticeService.insertNotice(backNoticeVO); // DB에 저장
	
		return "redirect:/notice";
	}
    @PostMapping(value = "/notice/updateNotice")
    @ResponseBody
    public String updateNotice(@RequestBody BackNoticeVO backNoticeVO ) {
    	noticeService.updateNotice(backNoticeVO);
		return "redirect:/notice";
    	
    }
    @DeleteMapping(value = "/notice/deleteNotice")
    @ResponseBody
    public String deleteNotice(@RequestParam("backNotice") int backNotice){
        noticeService.deleteNotice(backNotice);
        return "redirect:/notice";
    }
  
//----------------------파일 업로드------------------------//
    
    @GetMapping(value = "/noticefile")
    @ResponseBody
    public List<BackNoticeFileVO> getNoticeFileList(){
    	
    	List<BackNoticeFileVO> list = noticeFileService.getNoticeFileList();
    	
    	return list;
    }
   
    @SuppressWarnings("deprecation")
    @PostMapping(value = "/noticefile/insertFile")
    @ResponseBody
    public String insertFile(@RequestPart(value = "fileUp") MultipartFile mfile) {
    	BackNoticeFileVO imageFile = new BackNoticeFileVO();
    	String realPath = "";
    	
    	if(mfile != null) {
			try {
				if (os.contains("win")) {
					realPath = "D:/image/";
				} else {
					realPath = "/resources/Back/";
				}
				String saveName = UUID.randomUUID() + "_" + mfile.getOriginalFilename();
				
				if(realPath != null && realPath != "") {
					File target = new File(realPath, saveName);
					mfile.transferTo(target);
					
					imageFile.setBack_Noticefile_OriName(mfile.getOriginalFilename());
					imageFile.setBack_Noticefile_SaveName(saveName);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		noticeFileService.insertFile(imageFile);
    	
    	return imageFile.toString();
    }
    
    @DeleteMapping(value = "noticefile/deleteFile")
    @ResponseBody
    public void deleteFile(@RequestParam int noticeFileId) {
    	String realPath = "";
    	if (os.contains("win")) {
			realPath = "D:/image/";
		} else {
			realPath = "/resources/Back/";
		}
		
    	noticeFileService.deleteFile(noticeFileId, realPath);
    }
    
}