package kr.sga.gkmarket.banner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.banner.vo.BannerVO;

@Mapper
public interface BannerDAO {
	// <!-- 1. 배너 1개 얻기 -->
	BannerVO selectByIdx(int idx);
	// <!-- 2. 배너리스트 얻기 -->
	List<BannerVO> selectBannerList();
	// <!-- 3. 저장하기 -->
	void insert(BannerVO vo);
	// <!-- 4. 삭제하기 -->
	void delete(int idx);
}
