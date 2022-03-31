package kr.sga.gkmarket.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.main.service.MainViewService;
import kr.sga.gkmarket.main.vo.MainViewVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainViewController {
	
	@Autowired
	public MainViewService mainViewService;
	
	@PostMapping(value = "/MainView")
	public String MainPage(@RequestBody Model model) throws JsonProcessingException {
		MainViewVO mainViewVO = null;
		mainViewVO.setBoardCount(mainViewService.selectAllBoard());
		mainViewVO.setForSale(mainViewService.selectForSale());
		mainViewVO.setReplyDone(mainViewService.selectReplyDone());
		mainViewVO.setSoldOut(mainViewService.selectSoldOut());
		
		model.addAttribute("mv", mainViewVO);
		return "MainPage";
		
	}
	
}
