package kr.green.sga.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import kr.green.sga.vo.MenuDBVO;
@Mapper
public interface BackMenuDAO {
	//전체 메뉴 리스트 가져오기
	List<MenuDBVO> getMenu(SqlSession sqlSession , MenuDBVO menuDBVO) throws SQLException;
	 
	

}
