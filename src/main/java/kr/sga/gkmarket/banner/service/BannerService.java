package kr.sga.gkmarket.banner.service;

import java.util.List;

import kr.sga.gkmarket.banner.vo.BannerVO;

public interface BannerService {
	// 1. 파일 목록 가져오기(이미지까지)
	List<BannerVO> selectBannerList();
	// 2. 배너 추가
	void BannerAdd(BannerVO bannerVO);
	// 3. 배너 삭제
	void BannerDelete(int bannerId, String realPath);
	// 4. 활성 배너 얻기
	public List<BannerVO> canUseBannerList();
	// 5. 배너 활성 비활성 스위칭
	public void bannerSwitching(int idx);
}
