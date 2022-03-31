package kr.sga.gkmarket.main.service;

public interface MainViewService {

	// 전체글 수 불러오기
	int selectAllBoard();
	// 판매완료 수 불러오기
	int selectSoldOut();
	// 판매중 수 불러오기
	int selectForSale();
	// 답변완료 수 불러오기
	int selectReplyDone();
}
