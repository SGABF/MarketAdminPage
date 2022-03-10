package kr.green.sga.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

import kr.green.sga.vo.AdminVO;
@Mapper
public interface AdminDAO {
	// 권한 수정
	void updateAdmin(SqlSession sqlSession , AdminVO adminVO) throws SQLException;
	// 계정 삭제
	void deleteAdmin(SqlSession sqlSession , AdminVO adminVO) throws SQLException;
	// 관리자 계정 가져오기
	List<AdminVO> getAdmin(SqlSession sqlSession , AdminVO adminVO) throws SQLException;

}
