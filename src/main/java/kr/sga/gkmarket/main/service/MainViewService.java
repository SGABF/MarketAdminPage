package kr.sga.gkmarket.main.service;

import kr.sga.gkmarket.main.vo.UserAgeVO;

public interface MainViewService {

	// 전체글 수 불러오기
	int selectAllBoard();
	// 판매완료 수 불러오기
	int selectSoldOut();
	// 판매중 수 불러오기
	int selectForSale();
	// 답변완료 수 불러오기
	int selectReplyDone();
	// 월별 가입회원수
	int selectUserMonth(int month);
	// 월별 업로드된 게시물
	int selectUploadBrd(int month);
	// 연령층 가져오기
	UserAgeVO selectUserAge();
	// 총 유저 가져오기
	int selectAllUser();
	// 밴당한 유저 수
	int selectUserBanned();
}
