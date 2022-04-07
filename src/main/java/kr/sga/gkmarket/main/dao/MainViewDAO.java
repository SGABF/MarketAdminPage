package kr.sga.gkmarket.main.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.main.vo.MainViewVO;
import kr.sga.gkmarket.main.vo.UserAgeVO;

@Mapper
public interface MainViewDAO {
	// 전체글수 가져오기
	int selectBoardCount();
	//  board_soldOut 3인 값의 개수 가져오기 (판매완료)
	int selectSoldOut();
	//  board_soldOut 1인 값의 개수 가져오기 (판매중)
	int selectForSale();
	//  해결된 질문
	int selectReplyDone();
	// 월별 가입회원수
	int selectUserMonth(int month);
	// 월별 업로드된 게시물
	int selectUploadBrd(int month);
	// 가입회원 연령대
	UserAgeVO selectUserAge();
	// 총 멤버수
	int selectAllUser();
	// 밴 당한 유저수
	int userBanned();
}
