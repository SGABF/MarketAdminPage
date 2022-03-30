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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import kr.sga.gkmarket.banner.service.BannerService;
import kr.sga.gkmarket.banner.vo.BannerVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BannerController {
	
	@Autowired
	private BannerService bannerService;
	
	private String os = System.getProperty("os.name").toLowerCase();
	
	// 관리자 페이지용 getList 프론트 전용으로 하나 더 만들어야 함
	@ApiOperation(value = "배너 가져오기(admin)", notes = "모든 배너 리스트를 가져옵니다.(토큰 필요)")
	@GetMapping(value = "/banner/getList")
	@ResponseBody
	public List<BannerVO> getBannerList() {
		log.info("getBannerList() 호출");
		List<BannerVO> list = bannerService.selectBannerList();
		
		log.info("getBannerList() 결과 : " + list);
		return list;
	}
	
	// 프론트 전용
	@ApiOperation(value = "배너 가져오기(front)", notes = "활성화 된 배너 리스트를 가져옵니다.(토큰 불필요)")
	@GetMapping(value = "/MainView/getCanUseList")
	@ResponseBody
	public List<BannerVO> getCanUseBannerList() {
		log.info("getCanUseBannerList() 호출");
		List<BannerVO> list = bannerService.canUseBannerList();
		
		log.info("getCanUseBannerList() 결과 : " + list);
		return list;
	}
	
	@ApiOperation(value = "배너 가져오기(admin)", notes = "모든 배너 리스트를 가져옵니다.(토큰 불필요)")
	@GetMapping(value = "/MainView/getList")
	@ResponseBody
	public List<BannerVO> getBannerListUserSide() {
		log.info("getBannerList() 호출");
		List<BannerVO> list = bannerService.selectBannerList();
		
		log.info("getBannerList() 결과 : " + list);
		return list;
	}
	
	@SuppressWarnings("deprecation")
	@ApiOperation(value = "배너 추가하기", notes = "배너를 추가합니다.")
	@PostMapping(value = "/banner/addBanner")
	@ResponseBody
	public String addBanner( 
			@RequestPart(value = "fileUp") MultipartFile mfile
			) {
		log.info("{}의 addBanner 호출", this.getClass().getName());
		
		BannerVO bannerImageFile = new BannerVO();
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
					
					bannerImageFile.setBanner_oriName(mfile.getOriginalFilename());
					bannerImageFile.setBanner_saveName(saveName);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bannerImageFile.setBanner_show(1);
		bannerService.BannerAdd(bannerImageFile);
		
		return bannerImageFile.toString();
	}
	
	@ApiOperation(value = "배너 페이지", notes = "배너 페이지로 이동합니다.")
	@GetMapping(value = "/MainView/BannerControl")
	public String BannerControlPage() {
		return "/BannerControl";
	}
	
	@ApiOperation(value = "배너 삭제", notes = "배너를 idx값으로 삭제합니다.")
	@PostMapping(value = "/banner/deleteBanner")
	@ResponseBody
	public void deleteBanner(
			@RequestParam int banner_id
			) {
		log.info("{}의 deleteBanner 호출 : {}", this.getClass().getName(), banner_id);
		
		String realPath = "";
		if (os.contains("win")) {
			realPath = "D:/image/";
		} else {
			realPath = "/resources/Back/";
		}
		
		bannerService.BannerDelete(banner_id, realPath);
		
	}
	
	@ApiOperation(value = "배너 활성 비활성", notes = "배너를 idx값으로 활성 비활성 합니다.")
	@PostMapping(value = "/banner/bannerSwitching")
	@ResponseBody
	public void bannerSwitching(
			@RequestParam int banner_id
			) {
		log.info("{}의 bannerSwitching 호출 : {}", this.getClass().getName(), banner_id);
		
		bannerService.bannerSwitching(banner_id);
	}
}
