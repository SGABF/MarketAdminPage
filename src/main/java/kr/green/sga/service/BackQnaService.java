package kr.green.sga.service;

import org.apache.ibatis.session.SqlSessionException;

import kr.green.sga.vo.BackQnaVO;

public interface BackQnaService {
	
	// qna 저장
	void insert(BackQnaVO backQnaVO)throws SqlSessionException;
	// qna 수정    
	void update(BackQnaVO backQnaVO)throws SqlSessionException;
	// qna 삭제     
	void delete(BackQnaVO backQnaVO)throws SqlSessionException;
}
