package kr.green.sga.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import kr.green.sga.vo.BackQnaVO;

@Mapper
public interface BackQnaDAO {
	//	<!-- 저장 -->
	void insert(BackQnaVO backQnaVO) throws SQLException;
	//	<!-- 수정 -->
	void update(BackQnaVO backQnaVO) throws SQLException;
	//	<!-- 삭제 -->
	void deleteByIdx(int idx) throws SQLException;
}