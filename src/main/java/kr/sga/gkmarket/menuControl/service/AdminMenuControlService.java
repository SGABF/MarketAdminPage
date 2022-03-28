package kr.sga.gkmarket.menuControl.service;

import java.util.List;

import kr.sga.gkmarket.menuControl.vo.MenuDBVO;

public interface AdminMenuControlService {
	// 1. 메뉴 목록 전체 얻기
	List<MenuDBVO> selectListAll();
	// 2. 메뉴 1개 얻기
	MenuDBVO selectOne(int menuDB_idx);
	// 3. 메뉴 활성 비활성
	void menuActive(int menuDB_idx);
	// 4. 메뉴 추가
	void insertMenu(MenuDBVO menuDBVO);
}
