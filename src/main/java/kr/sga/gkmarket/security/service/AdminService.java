package kr.sga.gkmarket.security.service;

import java.util.List;
import java.util.Map;

import kr.sga.gkmarket.security.vo.AdminVO;

public interface AdminService {
	AdminVO getAdminTest();
	
	// 1. 1개의 계정 정보 얻기
	AdminVO getAdminAccount(String admin_id);
	
	// 2. 전체 계정 정보 얻기
	List<AdminVO> getAllAdminAccount();
	
	// 3. 계정 추가
	void addAdminAccount(AdminVO adminVO);
	
	// 4. 계정 삭제
	void deleteAdminAccount(int admin_idx);
	
	// 5. 비밀번호 수정
	void changePassword(AdminVO adminVO);
	
	// 6. 비밀번호 얻기
	String getAdminPassword(String admin_id);
}
