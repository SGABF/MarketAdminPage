package kr.green.sga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.green.sga.service.BackQnaService;
import kr.green.sga.service.BackQnaServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BackQnaController {

	@Autowired 
	private BackQnaServiceImpl backQnaServiceImpl;

	@RequestMapping(value = "/qnaForm")
	public String qnaForm() {
		
		return "qnaForm";
	}
}
