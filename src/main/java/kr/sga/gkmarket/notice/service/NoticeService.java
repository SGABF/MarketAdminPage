
package kr.sga.gkmarket.notice.service;

import java.util.List;

import kr.sga.gkmarket.notice.vo.BackNoticeVO;

public interface NoticeService {
	// 공지사항 등록
	void insertNotice(BackNoticeVO backNoticeVO);
	// 공지사항 수정
	void updateNotice(BackNoticeVO backNoticeVO);
	// 공지사항 삭제
	void deleteNotice(int backNoticeVO);
	// 공지사항 목록 가져오기
    List<BackNoticeVO> getNotice();
    // 현재저장한 idx 가져오기
    int selectSeq();
    // 1개 가져오기
    BackNoticeVO selectIdx(int idx);
    
    
}