package kr.sga.gkmarket.security.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.security.vo.AdminVO;

@Mapper
public interface AdminDAO {
	AdminVO getUser();
}
