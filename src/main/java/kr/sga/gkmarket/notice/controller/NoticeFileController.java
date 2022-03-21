package kr.sga.gkmarket.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.sga.gkmarket.notice.service.NoticeFileService;
import kr.sga.gkmarket.notice.vo.BackNoticeFileVO;

@Controller

public class NoticeFileController {
	
	@Autowired
    private NoticeFileService noticeFileService;
    
    private String os = System.getProperty("os.name").toLowerCase();
	
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
