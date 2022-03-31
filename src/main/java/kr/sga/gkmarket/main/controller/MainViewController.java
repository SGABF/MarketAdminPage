package kr.sga.gkmarket.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.main.service.MainViewService;
import kr.sga.gkmarket.main.vo.MainViewVO;
import kr.sga.gkmarket.qna.service.BackQnaService;
import kr.sga.gkmarket.qna.vo.BackQnaVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainViewController {
	
	@Autowired
	public MainViewService mainViewService;
	
	@Autowired
	public BackQnaService BackQnaService;
	
	@GetMapping(value = "/MainView/MainPageEx")
	public String MainPageEx(MainViewVO mainViewVO,Model model) {
		log.info("컨트롤러 실행 mainview ");
		mainViewVO.setBoardCount(mainViewService.selectAllBoard());
		mainViewVO.setForSale(mainViewService.selectForSale());
		mainViewVO.setReplyDone(mainViewService.selectReplyDone());
		mainViewVO.setSoldOut(mainViewService.selectSoldOut());
		System.out.println("mainViewService.selectAllBoard()     " + mainViewService.selectAllBoard()+ "\n");
		model.addAttribute("mv", mainViewVO);
		return "/MainPageEx";
	}
	
	
}
