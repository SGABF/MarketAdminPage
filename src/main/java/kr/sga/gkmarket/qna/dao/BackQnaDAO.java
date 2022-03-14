package kr.sga.gkmarket.qna.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.qna.vo.BackQnaVO;


@Mapper
public interface BackQnaDAO {
	// 개수얻기 
	int selectCount();
	// 한개 글 가져오기
	BackQnaVO selectByIdx(int idx) throws SQLException;
	// 한페이지 글 가져오기
	List<BackQnaVO> selectList(HashMap<String, Integer> map)throws SQLException;
	// 저장
	void insert(BackQnaVO backQnaVO) throws SQLException;
	// 수정 
	void update(BackQnaVO backQnaVO) throws SQLException;
	// 삭제 
	void deleteByIdx(int idx) throws SQLException;
}