package kr.sga.gkmarket.qna.service;


import kr.sga.gkmarket.qna.vo.BackQnaFileVO;
import kr.sga.gkmarket.qna.vo.BackQnaVO;
import kr.sga.gkmarket.vo.CommVO;
import kr.sga.gkmarket.vo.PagingVO;



public interface BackQnaService {
	// qna 내용보기
	BackQnaVO selectByIdx(int idx);
	// qna 목록보기
	PagingVO<BackQnaVO> selectList(CommVO commVO);
	// qna 저장
	void insert(BackQnaVO backQnaVO);
	// qna 수정    
	void update(BackQnaVO backQnaVO);
	// qna 삭제     
	void delete(BackQnaVO backQnaVO);
	// 한글에 저장된 파일 가져오기
	BackQnaFileVO selectFiles(int idx);
	
}
