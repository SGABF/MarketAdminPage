package kr.sga.gkmarket.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.security.dao.AdminDAO;
import kr.sga.gkmarket.security.vo.AdminVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	public AdminVO getAdminTest() {
		return adminDAO.getAdminTest();
	}

	@Override
	public AdminVO getAdminAccount(String admin_id){
		log.info("{}의 getAdminAccount 호출 : {}", this.getClass().getName(), admin_id);
		AdminVO vo = null;
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("admin_id", admin_id);
		//map.put("admin_password", admin_password);
		
		vo = adminDAO.getAdminAccount(map);
		
		log.info("{}의 getAdminAccount 리턴 : {}", this.getClass().getName(), vo);
		return vo;
	}

	@Override
	public List<AdminVO> getAllAdminAccount() {
		log.info("{}의 getAllAdminAccount 호출", this.getClass().getName());
		List<AdminVO> list = null;
		
		list = adminDAO.getAllAdminAccount();
		
		log.info("{}의 getAllAdminAccount 리턴 : {}", this.getClass().getName(), list);
		return list;
	}

	@Override
	public void addAdminAccount(AdminVO adminVO) {
		log.info("{}의 addAdminAccount 호출 : {}", this.getClass().getName(), adminVO);
		adminDAO.addAdminAccount(adminVO);
	}

	@Override
	public void deleteAdminAccount(int admin_idx) {
		log.info("{}의 deleteAdminAccount 호출 : {}", this.getClass().getName(), admin_idx);
		adminDAO.deleteAdminAccount(admin_idx);
	}

	@Override
	public void changePassword(AdminVO adminVO) {
		log.info("{}의 changePassword 호출 : {}", this.getClass().getName(), adminVO);
		adminDAO.changePassword(adminVO);
	}

	@Override
	public String getAdminPassword(String admin_id) {
		log.info("{}의 getAdminPassword 호출 : {}", this.getClass().getName(), admin_id);
		String adminPW = "";
		
		adminPW = adminDAO.getAdminPassword(admin_id);
		
		log.info("{}의 getAdminPassword 리턴 : {}", this.getClass().getName(), adminPW);
		return adminPW;
	}
}
