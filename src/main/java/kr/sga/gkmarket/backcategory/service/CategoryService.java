
package kr.sga.gkmarket.backcategory.service;

import java.util.List;

import kr.sga.gkmarket.backcategory.vo.BackCategoryVO;
import kr.sga.gkmarket.notice.vo.BackNoticeVO;

public interface CategoryService {
	// 등록
	void insertCategory(BackCategoryVO backCategoryVO);
	// 수정
	void activateCategory(int back_Category_Idx);
	// 목록 가져오기
    List<BackCategoryVO> getCategory();
    // 하나 가져오기
    BackCategoryVO selectByIdx(int idx);
    
}