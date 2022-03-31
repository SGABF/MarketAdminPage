/**
 * 
 */
package kr.sga.gkmarket.userbanned.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.userbanned.dao.UserBannedDAO;
import kr.sga.gkmarket.userbanned.vo.UserBannedVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 *
 */
@Slf4j
@Service
public class UserBannedServiceImpl implements UserBannedService {
	@Autowired
	private UserBannedDAO userBannedDAO;
	
	
	@Override
	public UserBannedVO getUser(int user_Idx) {
		UserBannedVO vo = null;
		if(user_Idx!=0) {
			vo = userBannedDAO.getUser(user_Idx);
		}
		return vo;
	}

	@Override
	public List<UserBannedVO> getUserList() {
		
		return userBannedDAO.getUserList();
	}

	@Override
	public void userBannedActivate(int user_Idx) {
		
		userBannedDAO.userBannedActivate(user_Idx);

	}

}
