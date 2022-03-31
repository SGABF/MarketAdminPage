package kr.sga.gkmarket.main.dao;

import org.apache.ibatis.annotations.Mapper;

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
}
