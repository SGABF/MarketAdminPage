package kr.green.sga.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import kr.green.sga.vo.BackQnaReplyVO;
@Mapper
public interface BackQnaReplyDAO {
	// 저장 (1:1 문의 답변등록)
	void insert(BackQnaReplyVO backQnaReplyVO) throws SQLException;
	// 수정 (1:1 문의 답변수정) 
	void update(BackQnaReplyVO backQnaReplyVO) throws SQLException;
	// 삭제 (1:1 문의 답변삭제)
	void delete(int idx) throws SQLException;
}
