package kr.sga.gkmarket.qna.service;

import java.sql.SQLException;

import kr.sga.gkmarket.qna.vo.BackQnaVO;
import kr.sga.gkmarket.qna.vo.CommVO;
import kr.sga.gkmarket.qna.vo.PagingVO;



public interface BackQnaService {
	// qna 내용보기
	BackQnaVO selectByIdx(int idx)throws SQLException;
	// qna 목록보기
	PagingVO<BackQnaVO> selectList(CommVO commVO)throws SQLException;
	// qna 저장
	void insert(BackQnaVO backQnaVO)throws SQLException;
	// qna 수정    
	void update(BackQnaVO backQnaVO)throws SQLException;
	// qna 삭제     
	void delete(BackQnaVO backQnaVO)throws SQLException;
}
