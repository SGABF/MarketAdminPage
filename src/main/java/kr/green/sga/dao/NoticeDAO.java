package kr.green.sga.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.green.sga.vo.BackNoticeVO;
@Mapper 
public interface NoticeDAO {
	// 공지사항 등록
	void insertNotice(BackNoticeVO backNoticeVO) throws SQLException;
	// 공지사항 수정
	void updateNotice(BackNoticeVO backNoticeVO) throws SQLException;
	// 공지사항 삭제
	void deleteNotice(BackNoticeVO backNoticeVO) throws SQLException;
	// 공지사항 가져오기
	List<BackNoticeVO> getNotice() throws SQLException;
}
