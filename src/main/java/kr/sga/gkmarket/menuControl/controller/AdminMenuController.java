package kr.sga.gkmarket.menuControl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.menuControl.service.AdminMenuControlService;
import kr.sga.gkmarket.menuControl.vo.MenuDBVO;

@RestController
public class AdminMenuController {
	
	@Autowired
	private AdminMenuControlService adminMenuControlService;
	
	@ApiOperation(value = "메뉴 목록 가져오기", notes = "메뉴 목록을 가져옵니다.")
	@GetMapping(value = "/menuCtl/selectListAll")
	public List<MenuDBVO> selectListAll() {
		List<MenuDBVO> menuList = null;
		
		menuList = adminMenuControlService.selectListAll();
		
		return menuList;
	}
	
	@ApiOperation(value = "메뉴 추가", notes = "메뉴를 추가합니다. 메뉴 이름, URL만 입력하면 나머지는 자동으로 등록됩니다.")
	@PostMapping(value = "/menuCtl/insertMenu")
	public void insertMenu(
			@RequestBody MenuDBVO menuDBVO
			) {
		adminMenuControlService.insertMenu(menuDBVO);
	}
	
	@ApiOperation(value = "메뉴 활성 비활성", notes = "메뉴를 활성화 하거나 비활성화 하는 기능입니다. IDX값만 필요합니다.")
	@PostMapping(value = "/menuCtl/menuActive")
	public void menuActive(
			@RequestBody MenuDBVO menuDBVO
			) {
		adminMenuControlService.menuActive(menuDBVO.getMenuDB_idx());
	}
}
