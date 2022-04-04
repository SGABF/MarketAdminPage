package kr.sga.gkmarket.qna.service;


import java.util.List;

import kr.sga.gkmarket.qna.vo.BackQnaFileVO;
import kr.sga.gkmarket.qna.vo.BackQnaVO;
import kr.sga.gkmarket.qna.vo.QnaPagingVO;
import kr.sga.gkmarket.qna.vo.QnaUserNameVO;
import kr.sga.gkmarket.vo.CommVO;
import kr.sga.gkmarket.vo.PagingVO;



public interface BackQnaService {
	// qna 내용보기
	BackQnaVO selectByIdx(int idx);
	// qna 목록보기
	List<BackQnaVO> selectList();
	// qna 저장
	void insert(BackQnaVO backQnaVO);
	// qna 글수정,파일수정    
	void update(BackQnaVO backQnaVO, int delFiles, String realPath);
	// qna 글수정만
	void updateQna(BackQnaVO backQnaVO);
	// qna 질문 삭제     
	void delete(BackQnaVO backQnaVO,  String realPath);
	// 한글에 저장된 파일 가져오기
	BackQnaFileVO selectFiles(int idx);
	// 사용자 이름 가져오기
	List<QnaUserNameVO> selectUserName();
	// 마지막으로 저장된 유저 idx
	int selectSeq();
	// 답변완료
	void doneComment(int idx);
	// 답변미완료
	void notYetComment(int idx);
	// 유저번호 구하기
	int selectUserIdx(String userId);
	//  Qna 게시판 수
	int countList();
}
