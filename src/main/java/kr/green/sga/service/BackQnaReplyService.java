package kr.green.sga.service;

import kr.green.sga.vo.BackQnaReplyVO;

public interface BackQnaReplyService {
	
	
	// 1:1 문의 답변등록
	void insertReply(BackQnaReplyVO backQnaReplyVO);
	// 1:1 문의 답변수정
	void updateReply(BackQnaReplyVO backQnaReplyVO);
	// 1:1 문의 답변삭제
	void deleteReply(BackQnaReplyVO backQnaReplyVO);
}
