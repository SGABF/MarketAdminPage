package kr.sga.gkmarket.banner.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.sga.gkmarket.banner.service.BannerService;
import kr.sga.gkmarket.banner.vo.BannerVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BannerController {
	
	@Autowired
	private BannerService bannerService;
	
	// 관리자 페이지용 getList 프론트 전용으로 하나 더 만들어야 함
	@GetMapping(value = "/banner/getList")
	@ResponseBody
	public List<BannerVO> getBannerList() {
		log.info("getBannerList() 호출");
		List<BannerVO> list = bannerService.selectBannerList();
		
		// 파일 이름이랑, 이미지 경로를 보내 준다.
		
		
		log.info("getBannerList() 결과 : " + list);
		return list;
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/banner/addBanner")
	@ResponseBody
	public String addBanner( 
			@RequestPart(value = "fileUp") MultipartFile mfile,
			HttpServletRequest request,
			Model model
			) {
		log.info("{}의 addBanner 호출", this.getClass().getName());
		
		BannerVO bannerImageFile = new BannerVO();
		
		if(mfile != null) {
			try {
				String realPath = request.getRealPath("uploadBanner");
				String saveName = UUID.randomUUID() + "_" + mfile.getOriginalFilename();
				
				File target = new File(realPath, saveName);
				mfile.transferTo(target);
				
				bannerImageFile.setBanner_oriName(mfile.getOriginalFilename());
				bannerImageFile.setBanner_saveName(saveName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bannerImageFile.setBanner_show(1);
		bannerService.BannerAdd(bannerImageFile);
		
		return bannerImageFile.toString();
	}
	
	@GetMapping(value = "/MainView/BannerControl")
	public String BannerControlPage() {
		return "/BannerControl";
	}
}
