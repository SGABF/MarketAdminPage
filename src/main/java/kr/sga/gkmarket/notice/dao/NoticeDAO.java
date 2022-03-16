package kr.sga.gkmarket.notice.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.sga.gkmarket.notice.vo.BackNoticeVO;

@Mapper 
public interface NoticeDAO {
	// 공지사항 등록
	void insertNotice(BackNoticeVO backNoticeVO);
	// 공지사항 수정
	void updateNotice(BackNoticeVO backNoticeVO);
	// 공지사항 삭제
	void deleteNotice(int backNoticeVO);
	// 공지사항 가져오기
	List<BackNoticeVO> getNotice();
}
