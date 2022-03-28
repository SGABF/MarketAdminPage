package kr.sga.gkmarket.menuControl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.menuControl.service.AdminMenuControlService;
import kr.sga.gkmarket.menuControl.vo.MenuDBVO;

@RestController
public class AdminMenuController {
	
	@Autowired
	private AdminMenuControlService adminMenuControlService;
	
	@GetMapping(value = "/menuCtl/selectListAll")
	public List<MenuDBVO> selectListAll() {
		List<MenuDBVO> menuList = null;
		
		menuList = adminMenuControlService.selectListAll();
		
		return menuList;
	}
	
	@PostMapping(value = "/menuCtl/insertMenu")
	public void insertMenu(
			@RequestBody MenuDBVO menuDBVO
			) {
		adminMenuControlService.insertMenu(menuDBVO);
	}
	
	@PostMapping(value = "/menuCtl/menuActive")
	public void menuActive(
			@RequestBody MenuDBVO menuDBVO
			) {
		adminMenuControlService.menuActive(menuDBVO.getMenuDB_idx());
	}
}
