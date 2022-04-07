package kr.sga.gkmarket.main.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.sga.gkmarket.main.service.MainViewService;
import kr.sga.gkmarket.main.vo.MainViewVO;
import kr.sga.gkmarket.main.vo.UserAgeVO;
import kr.sga.gkmarket.qna.service.BackQnaService;
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
		model.addAttribute("mv", mainViewVO); // 보내기
		
		//월별 유저가입수 
		int userCount = 0; 
		int tempCount = 0;
		
		ArrayList<Integer> monthList = new ArrayList<Integer>();
		for(int i = 1; i <= 12; i++) {
			userCount = mainViewService.selectUserMonth(i);
			System.out.println(i+"월 : "+userCount +"명" );
			tempCount+= userCount;
			monthList.add(tempCount);
		}
		System.out.println("월별 합" + monthList);
		model.addAttribute("mList", monthList); // 보내기
		
		//월별 게시글 업로드수
		int boardCount = 0;
		int tempBoard = 0;

		ArrayList<Integer> boardList = new ArrayList<Integer>();
		for(int i = 1; i <= 12; i++) {
			boardCount = mainViewService.selectUploadBrd(i);
			System.out.println(i+"월 : "+boardCount +"개" );
			tempBoard+= boardCount;
			boardList.add(tempBoard);
		}
		System.out.println("월별 업로드된 게시물 합" + boardList);
		model.addAttribute("bList", boardList); // 보내기
		
		// 연령층 가입회원수 가져오기
		UserAgeVO age = mainViewService.selectUserAge();
		System.out.println("유저 나이" + age);
		model.addAttribute("age", age);
		
		// 모든 멤버수 가져오기
		int allUserCount = mainViewService.selectAllUser();
		model.addAttribute("allUser", allUserCount); // 보내기

		// 밴 당한 멤버수
		int allUserBanned = mainViewService.selectUserBanned();
		model.addAttribute("userBanned", allUserBanned); // 보내기

		
		return "/MainPageEx";
	}
	
	
}
