package kr.sga.gkmarket.menuControl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.menuControl.dao.AdminMenuControlDAO;
import kr.sga.gkmarket.menuControl.vo.MenuDBVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminMenuControlServiceImpl implements AdminMenuControlService {

	@Autowired
	private AdminMenuControlDAO adminMenuControlDAO;
	
	@Override
	public List<MenuDBVO> selectListAll() {
		log.info("{}의 selectListAll() 호출", this.getClass().getName());
		List<MenuDBVO> list = null;
		
		list = adminMenuControlDAO.selectListAll();
		
		log.info("{}의 selectListAll() 리턴 : {}", this.getClass().getName(), list);
		return list;
	}

	@Override
	public MenuDBVO selectOne(int menuDB_idx) {
		log.info("{}의 selectOne() 호출 : {}", this.getClass().getName(), menuDB_idx);
		MenuDBVO vo = null;
		
		vo = adminMenuControlDAO.selectOne(menuDB_idx);
		
		log.info("{}의 selectOne() 리턴 : {}", this.getClass().getName(), vo);
		return vo;
	}

	@Override
	public void menuActive(int menuDB_idx) {
		log.info("{}의 menuActive() 호출 : {}", this.getClass().getName(), menuDB_idx);
		adminMenuControlDAO.menuActive(menuDB_idx);
	}

	@Override
	public void insertMenu(MenuDBVO menuDBVO) {
		log.info("{}의 insertMenu() 호출 : {}", this.getClass().getName(), menuDBVO);
		adminMenuControlDAO.insertMenu(menuDBVO);
	}

}
