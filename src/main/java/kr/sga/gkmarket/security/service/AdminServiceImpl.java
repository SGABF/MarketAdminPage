package kr.sga.gkmarket.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.security.dao.AdminDAO;
import kr.sga.gkmarket.security.vo.AdminVO;



@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	public AdminVO getUser() {
		return adminDAO.getUser();
	}
}
