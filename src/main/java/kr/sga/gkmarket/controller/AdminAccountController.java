package kr.sga.gkmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.security.service.AdminService;
import kr.sga.gkmarket.security.vo.AdminVO;

@RestController
public class AdminAccountController {
	
	@Autowired
	private AdminService adminService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
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
	
	@PostMapping("/adminAC/addAdminAccount")
	public void addAdminAccountPost(
			@RequestBody AdminVO adminVO
			) {
		if(adminVO != null) {
			adminVO.setAdmin_password(bCryptPasswordEncoder.encode(adminVO.getAdmin_password())); 
			
			adminService.addAdminAccount(adminVO);
		}
	}
	
	@PostMapping("/adminAC/deleteAdminAccount")
	public void deleteAdminAccountPost(
			@RequestParam int admin_idx
			) {
		adminService.deleteAdminAccount(admin_idx);
	}
	
	@PostMapping("/adminAC/ChangePassword")
	public void ChangePasswordPost(
			@RequestBody AdminVO adminVO 
			) {
		if(adminVO != null) {
			adminVO.setAdmin_password(bCryptPasswordEncoder.encode(adminVO.getAdmin_password())); 
			
			adminService.changePassword(adminVO);
		}
	}
	
	@GetMapping("/adminAC/getAdminACList")
	public List<AdminVO> getAdminACListGet(){
		List<AdminVO> list = null;
		
		list = adminService.getAllAdminAccount();
		
		return list;
	}
}