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
}
