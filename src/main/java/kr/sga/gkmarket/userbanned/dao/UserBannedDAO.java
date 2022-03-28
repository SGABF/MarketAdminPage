package kr.sga.gkmarket.userbanned.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.userbanned.vo.UserBannedVO;

@Mapper
public interface UserBannedDAO {
	// 관리할 유저 1개 불러오기
	UserBannedVO getUser(int user_Idx);
	// 관리할 유저 리스트 
	List<UserBannedVO> getUserList();
	// 유저 밴 활성화/비활성화
	void userBannedActivate(int user_Idx);
}
