package kr.sga.gkmarket.qna.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.sga.gkmarket.qna.service.BackQnaService;
import kr.sga.gkmarket.qna.vo.BackQnaVO;
import kr.sga.gkmarket.qna.vo.CommVO;
import kr.sga.gkmarket.qna.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/qna")
public class BackQnaController {

	@Autowired
	private BackQnaService backQnaService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qnaList")
	// public String selectList(@ModelAttribute CommVO commVO, Model model) {
	// POST전송을 받기위한 방법
	public String selectList(@RequestParam Map<String, String> params, HttpServletRequest request,@ModelAttribute CommVO commVO, Model model) throws SQLException {
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap!=null) {
			params = (Map<String, String>) flashMap.get("map");
			commVO.setP(Integer.parseInt(params.get("p")));
			commVO.setS(Integer.parseInt(params.get("s")));
			commVO.setB(Integer.parseInt(params.get("b")));
		}
		PagingVO<BackQnaVO> pv = backQnaService.selectList(commVO);
		model.addAttribute("pv", pv);
		System.out.println(pv);
		model.addAttribute("cv", commVO);
		return "qnaList";
	}
	
	// 내용보기 : 글 1개를 읽어서 보여준다
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/view")
		public String view(@RequestParam Map<String, String> params, HttpServletRequest request,@ModelAttribute CommVO commVO,Model model) {
			log.info("{}의 view호출 : {}", this.getClass().getName(), commVO);
			// POST전송된것을 받으려면 RequestContextUtils.getInputFlashMap(request)로 맵이 존재하는지 판단해서
			// 있으면 POST처리를 하고 없으면 GET으로 받아서 처리를 한다.
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
			if(flashMap!=null) {
				params = (Map<String, String>) flashMap.get("map");
				commVO.setP(Integer.parseInt(params.get("p")));
				commVO.setS(Integer.parseInt(params.get("s")));
				commVO.setB(Integer.parseInt(params.get("b")));
				commVO.setIdx(Integer.parseInt(params.get("idx")));
			}
			
			model.addAttribute("cv", commVO);
			return "view";
		}
}
