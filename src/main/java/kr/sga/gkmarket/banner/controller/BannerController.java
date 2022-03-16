package kr.sga.gkmarket.banner.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.sga.gkmarket.banner.service.BannerService;
import kr.sga.gkmarket.banner.vo.BannerVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BannerController {
	
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping(value = "/banner/getList")
	@ResponseBody
	public List<BannerVO> getBannerList() {
		log.info("getBannerList() 호출");
		List<BannerVO> list = bannerService.selectBannerList();
		
		log.info("getBannerList() 결과 : " + list);
		return list;
	}
	
	@RequestMapping(value = "/banner/addBanner", method = RequestMethod.POST)
	@ResponseBody
	public String addBanner(
			@ModelAttribute BannerVO bannerVO, 
			@RequestParam("file") MultipartFile mfile,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		log.info("{}의 addBanner 호출 : {}", this.getClass().getName(), bannerVO);
		
		BannerVO bannerImageFile = new BannerVO();
		
		if(bannerVO != null && mfile != null) {
			try {
				String realPath = request.getRealPath("uploadBanner");
				String saveName = UUID.randomUUID() + "_" + bannerVO.getBanner_oriName();
				
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
	
	@RequestMapping(value = "/MainView/BannerControl")
	public String BannerControlPage() {
		return "/BannerControl";
	}
}
