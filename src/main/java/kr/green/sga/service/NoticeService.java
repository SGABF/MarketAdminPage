package kr.green.sga.service;

import java.util.List;

import kr.green.sga.vo.BackNoticeVO;

public interface NoticeService {
	// 공지사항 등록
	void insertNotice(BackNoticeVO backNoticeVO);
	// 공지사항 수정
	void updateNotice(BackNoticeVO backNoticeVO);
	// 공지사항 삭제
	void deleteNotice(BackNoticeVO backNoticeVO);
	// 공지사항 목록 가져오기
    List<BackNoticeVO> getNotice() throws Exception;
    
}