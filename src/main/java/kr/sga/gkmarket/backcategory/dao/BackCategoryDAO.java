package kr.sga.gkmarket.backcategory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.sga.gkmarket.backcategory.vo.BackCategoryVO;

@Mapper 
public interface BackCategoryDAO {
	// 카테고리 등록
	void insertCategory(BackCategoryVO backCategoryVO);
	// 카테고리 활성화/비활성화 
	void activateCategory(int back_Category_Idx);
	// 카테고리 가져오기
	List<BackCategoryVO> getCategory();
	// 카테고리 하나 가져오기
	BackCategoryVO selectByIdx(int idx);
}
