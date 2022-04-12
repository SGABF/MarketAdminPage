package kr.sga.gkmarket.qna.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.qna.dao.BackQnaReplyDAO;
import kr.sga.gkmarket.qna.service.BackQnaFileService;
import kr.sga.gkmarket.qna.service.BackQnaReplyService;
import kr.sga.gkmarket.qna.service.BackQnaService;
import kr.sga.gkmarket.qna.vo.BackQnaFileVO;
import kr.sga.gkmarket.qna.vo.BackQnaReplyVO;
import kr.sga.gkmarket.qna.vo.BackQnaVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BackQnaController {

	@Autowired
	private BackQnaService backQnaService;

	@Autowired
	private BackQnaFileService backQnaFileService;

	@Autowired
	private BackQnaReplyService backQnaReplyService;
	

	private String os = System.getProperty("os.name").toLowerCase();

	
	 @GetMapping(value = "/MainView/Qna")
		public String Qna(Model model) {
			log.info("컨트롤러 실행 mainview ");
			List<BackQnaVO> qna = backQnaService.selectList();
			model.addAttribute("qna", qna);
			return "/Qna";
		}

	@PostMapping(value = "/MainView/qnaList")
	@ResponseBody
	// public String selectList(@ModelAttribute CommVO commVO, Model model) {
	// POST전송을 받기위한 방법
	public List<BackQnaVO> selectQnaList()throws JsonProcessingException {
		log.info("backQnaController selectQnaList 호출");
		List<BackQnaVO> list = backQnaService.selectList();
		log.info("backQnaController selectQnaList 가져오기 완료");
		return list;
	}

	// 내용보기 : 글 1개를 읽어서 보여준다 (댓글이 있으면 댓글도)
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping(value = "/MainView/qnaView")
	public BackQnaVO qnaView(@RequestParam (value = "idx")int idx) throws JsonProcessingException{
		log.info("backQnaController qnaView() 호출");
		BackQnaVO dbQnaVO = new BackQnaVO();
		if(idx >0) {
		dbQnaVO = backQnaService.selectByIdx(idx);
		dbQnaVO.setReply(backQnaReplyService.selectComment(idx));
		dbQnaVO.setUser_Name(backQnaService.selectUserName(dbQnaVO.getUser_Idx()));
		System.out.println("유저이름 : " + dbQnaVO.getUser_Name() + "\n");
		System.out.println("유저이름 : " + dbQnaVO.getUser_Idx() + "\n");
		System.out.println("유저이름 : " + backQnaService.selectUserName(dbQnaVO.getUser_Idx()) + "\n");
		System.out.println("유저이름 : " + backQnaService.selectUserName(5) + "\n");
		
		}
		return dbQnaVO;
	}

//	// 글쓰기 띄우기
//	@PostMapping(value = "/MainView/qnaInsertForm")
//	public String insertForm(@RequestBody CommVO commVO, Model model) {
//		model.addAttribute("cv", commVO);
//		return "qnaInsertForm";
//	}

//	// 저장하기
//	@GetMapping(value = "/MainView/qnaInsertOk")
//	public String insertOkGet() {
//		return "redirect:/qna/qnaList";
//	}

    @SuppressWarnings("deprecation")
    @PostMapping(value = "/MainView/qnaInsert")
	public void qnaInsert(@RequestPart(value = "BackQnaVO")BackQnaVO backQnaVO, @RequestPart(value = "file", required = false) MultipartFile file
			, @RequestHeader(value = "user_Id") String user_Id ) {
		log.info("여기는 VO" + backQnaVO + "여기는 파일" + file + "여기는 유저" + user_Id);
		
		if(backQnaVO != null) {
		int userIdx = backQnaService.selectUserIdx(user_Id); // 받은 user_Id로 userIdx에 넣어준다
		System.out.println("서비스의 userIdx : " + userIdx);
		backQnaVO.setUser_Idx(userIdx);
    	backQnaService.insert(backQnaVO); // DB에 저장
    	System.out.println(" insert 완료 " + "\n");
		}
		if(file != null ) {
			BackQnaVO vo =  new BackQnaVO();
			BackQnaFileVO uploadFile = new BackQnaFileVO();
			int ref = backQnaService.selectSeq();
			String realPath = "";
			try {
				if (os.contains("win")) {
					realPath = "D:/image/";
				} else {
					realPath = "/resources/Back/";
				}
				String saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
				
				if(realPath != null && realPath != "") {
					File target = new File(realPath, saveName);
					file.transferTo(target);
					uploadFile.setBack_Qna_Idx(ref);
					uploadFile.setBack_Qnafile_OriName(file.getOriginalFilename());
					uploadFile.setBack_Qnafile_SaveName(saveName);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			uploadFile.setBack_Qna_Idx(ref);
			backQnaFileService.insert(uploadFile);
		}
		
	}

//	@PostMapping(value = "/MainView/qnaUpdate")
//	public String qnaUpdate(@RequestBody BackQnaVO backQnaVO, @RequestPart(value = "fileUp", required = false) MultipartFile file, HttpServletRequest request) {
//		System.out.println("backQnaVo : " + backQnaVO + "\n");
//		
//		
//		//파일정보를 저장할 객체
//		int ref = backQnaService.selectSeq();
//		System.out.println("ref : "+ ref + "\n"); 
//		BackQnaFileVO fileVO = new BackQnaFileVO();
//		//넘어온 파일 
//		if(file!=null) {
//			BackQnaFileVO saveFile = new BackQnaFileVO();
//			// 파일 저장 
//			String path = "";
//			if (os.contains("win")) {
//				path = "D:/image/";
//			} else {
//				path = "/resources/Back/";
//			}
//			String saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//			//저장
//			if(path != null && path != "") {
//				File target = new File(path, saveName);
//				//vo 채우기
//				saveFile.setBack_Qna_Idx(ref);
//				saveFile.setBack_Qnafile_OriName(file.getOriginalFilename());
//				saveFile.setBack_Qnafile_SaveName(saveName);
//				
//				backQnaVO.setFile(saveFile);
//			}
//			// 삭제할 파일 글번호를 받아서 넘겨주기
//			int delFiles = backQnaVO.getBack_Qna_Idx();
//			System.out.println("delfiles : "+ delFiles);
//			String realPath = request.getRealPath("upload");
//			log.info("backQnaService.update 호출" + "\n");
//			backQnaService.update(backQnaVO, delFiles, realPath);
//			return "redirect:/qna/qnaView";
//		}
//		backQnaService.updateQna(backQnaVO);
//		return "redirect:/qna/qnaView";
//	}
	
//	// 섬머노트에서 이미지 업로드를 담당하는 메서드 : 파일을 업로드하고 이미지 이름을 리턴해주면된다.
//	@PostMapping(value = "/qnaImageUpload", produces = "text/plain;charset=UTF-8")
//	@ResponseBody
//	public String imageUpload(HttpServletRequest request, MultipartFile file) {
//		log.info("{}의 imageUpload 호출 : {}", this.getClass().getName(), request + "\n" + file);
//		String filePath = "";
//		@SuppressWarnings("deprecation")
//		String realPath = request.getRealPath("contentfile");
//		// 파일은 MultipartFile 객체가 받아준다.
//		if (file != null && file.getSize() > 0) { // 파일이 존재 한다면
//			try {
//				// 저장이름
//				String saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//				// 저장
//				File target = new File(realPath, saveName);
//				FileCopyUtils.copy(file.getBytes(), target);
//				filePath = request.getContextPath() + "\\contentfile\\" + saveName;
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		log.info("{}의 imageUpload 리턴 : {}", this.getClass().getName(), filePath);
//		return filePath;
//	}


	@PostMapping(value = "/MainView/qnaDeleteAdmin")
	@ResponseBody
	public void qnaDeleteAdmin(@RequestParam int idx, HttpServletRequest request, MultipartFile file) {
		log.info("{}의 qnaDelete 호출 : {}", this.getClass().getName(), request,"backQnaVO : " + idx +"\n");
		String realPath = request.getRealPath("win");
		BackQnaVO backQnaVO = null;
		backQnaVO = backQnaService.selectByIdx(idx);
		backQnaService.delete(backQnaVO, realPath);
		
	}
	
	@PostMapping(value = "/MainView/qnaDelete")
	@ResponseBody
	public void qnaDelete(@RequestParam BackQnaVO backQnaVO, HttpServletRequest request, MultipartFile file
			, @RequestHeader(value = "user_Id") String user_Id) {
		log.info("{}의 qnaDelete 호출 : {}", this.getClass().getName(), request,"backQnaVO : " + backQnaVO +"user_Id : " + user_Id);
		
		int userIdx = backQnaService.selectUserIdx(user_Id);
		int userIdxBoard = backQnaService.selectUserIdxBoard(backQnaVO.getBack_Qna_Idx());
		
		if(userIdx==userIdxBoard) {
		String realPath = request.getRealPath("win");
		backQnaService.delete(backQnaVO, realPath);
		}
	}
	
	@GetMapping(value = "/MainView/qnaDeleteOk")
	public String qnaDeleteOk(BackQnaReplyVO backQnaReplyVO) {
		if(backQnaReplyVO != null) {
			backQnaReplyService.delete(backQnaReplyVO.getBack_Qna_Idx());
		}
		return "/qnaDeleteOk";
	}
	
	// ------------------댓글-----------------------------
	@ResponseBody
	@PostMapping(value = "/qna/qndInsertComment")
	public void qndInsertComment(@RequestParam String back_Qna_Reply_Content, @RequestParam int back_Qna_Idx) {
		BackQnaReplyVO replyVO = new BackQnaReplyVO();
		replyVO.setBack_Qna_Idx(back_Qna_Idx);
		replyVO.setBack_Qna_Reply_Content(back_Qna_Reply_Content);
		if (replyVO != null) {
			backQnaReplyService.insert(replyVO);
			// 댓글 추가시 back_qna_question 값 1이됨
			log.info("backQnaService.doneComment 실행 replyVO.getBack_Qna_Idx() : " + replyVO.getBack_Qna_Idx() );
			backQnaService.doneComment(replyVO.getBack_Qna_Idx());
		}
	}
	@ResponseBody
	@PostMapping(value = "/qna/qnaUpdateComment")
	public void updateComment(BackQnaReplyVO replyVO) {
		log.info("{}의 updateComment 호출 : {}", this.getClass().getName(), replyVO);
		if (replyVO != null) {
			backQnaReplyService.update(replyVO);
		}
	}
	@ResponseBody
	@PostMapping(value = "/qna/qnaDeleteComment")
	public void qnaDeleteComment(@RequestParam int idx) {
		log.info("{}의 qnaDeleteComment 호출 : {}", this.getClass().getName(), idx);
		if (idx != 0) {
			backQnaReplyService.delete(idx);
			backQnaService.notYetComment(idx);
		}
	}
	
	@ResponseBody
	@PostMapping(value = "/qna/replyDelete")
	public void replyDelete(int idx) {
		backQnaReplyService.delete(idx);
		backQnaService.notYetComment(idx);
	}
	
}