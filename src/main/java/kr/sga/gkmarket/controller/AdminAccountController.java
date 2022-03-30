package kr.sga.gkmarket.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.security.service.AdminService;
import kr.sga.gkmarket.security.vo.AdminVO;

@RestController
public class AdminAccountController {
	
	@Autowired
	private AdminService adminService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@ApiOperation(value = "계정 정보 가져오기", notes = "계정 ID값과 Password를 이용하여 계정 정보를 가져옵니다.")
	@PostMapping("/adminAC/getAdminAccount")
	public AdminVO getAdminAccountPost(
			@RequestParam String admin_id,
			@RequestParam String admin_password
			) {
		AdminVO adminVO = null; 
		String checkPW = adminService.getAdminPassword(admin_id);
		
		if(admin_id != null && admin_id != "") {
			if(bCryptPasswordEncoder.matches(admin_password, checkPW)) {
				adminVO = adminService.getAdminAccount(admin_id);
			}
		}
		
		return adminVO;
	}
	
	@ApiOperation(value = "계정 추가", notes = "관리자 계정을 추가합니다.")
	@PostMapping("/adminAC/addAdminAccount")
	public void addAdminAccountPost(
			@RequestBody AdminVO adminVO
			) throws SQLException {
		if(adminVO != null) {
			adminVO.setAdmin_password(bCryptPasswordEncoder.encode(adminVO.getAdmin_password())); 
			
			if(adminService.getAdminAccount(adminVO.getAdmin_id()) == null) {
				adminService.addAdminAccount(adminVO);
			} else {
				throw new SQLException();
			}
		}
	}
	
	@ApiOperation(value = "계정 삭제", notes = "계정 IDX값을 이용하여 관리자 계정을 삭제합니다.")
	@PostMapping("/adminAC/deleteAdminAccount")
	public void deleteAdminAccountPost(
			@RequestParam int admin_idx
			) {
		adminService.deleteAdminAccount(admin_idx);
	}
	
	@ApiOperation(value = "비밀번호 변경", notes = "계정 IDX값과 변경할 Password만 입력하면 됩니다.")
	@PostMapping("/adminAC/ChangePassword")
	public void ChangePasswordPost(
			@RequestBody AdminVO adminVO 
			) {
		if(adminVO != null) {
			adminVO.setAdmin_password(bCryptPasswordEncoder.encode(adminVO.getAdmin_password())); 
			
			adminService.changePassword(adminVO);
		}
	}
	
	@ApiOperation(value = "모든 계정 출력", notes = "모든 계정 목록을 출력합니다.")
	@GetMapping("/adminAC/getAdminACList")
	public List<AdminVO> getAdminACListGet(){
		List<AdminVO> list = null;
		
		list = adminService.getAllAdminAccount();
		
		return list;
	}
}