package kr.sga.gkmarket.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.main.dao.MainViewDAO;
import kr.sga.gkmarket.main.vo.MainViewVO;
import kr.sga.gkmarket.main.vo.UserAgeVO;

@Service("mainViewService")
public class MainViewServiceImpl implements MainViewService{

	@Autowired
	public MainViewDAO mainViewDAO;
	
	@Override
	public int selectAllBoard() {
		int count = 0;
		count = mainViewDAO.selectBoardCount();
		return count;
	}

	@Override
	public int selectSoldOut() {
		int count = 0;
		count = mainViewDAO.selectSoldOut();
		return count;
	}

	@Override
	public int selectForSale() {
		int count = 0;
		count = mainViewDAO.selectForSale();
		return count;
	}

	@Override
	public int selectReplyDone() {
		int count = 0;
		count = mainViewDAO.selectReplyDone();
		return count;
	}

	@Override
	public int selectUserMonth(int month) {
		int userCount = 0;
		if(month > 0) {
			userCount = mainViewDAO.selectUserMonth(month);
		}
		return userCount;
	}

	@Override
	public UserAgeVO selectUserAge() {
		UserAgeVO userAge = new UserAgeVO();
		
		userAge = mainViewDAO.selectUserAge();
				
		System.out.println("메인서비스의 userAge : " + userAge);
		return userAge;
	}

}
