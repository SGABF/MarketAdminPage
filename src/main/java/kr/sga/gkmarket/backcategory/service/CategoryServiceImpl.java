/**
 * 
 */
package kr.sga.gkmarket.backcategory.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sga.gkmarket.backcategory.dao.BackCategoryDAO;
import kr.sga.gkmarket.backcategory.vo.BackCategoryVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 *
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private BackCategoryDAO backCategoryDAO;
	
	@Override
	public void insertCategory(BackCategoryVO backCategoryVO) {
		backCategoryDAO.insertCategory(backCategoryVO);
	}

	@Override
	public void activateCategory(int back_Category_Idx) {
		log.info("idx 호출 :" +  back_Category_Idx);
		backCategoryDAO.activateCategory(back_Category_Idx);
		log.info("idx 리턴 :" +  back_Category_Idx);
	}

	@Override
	public List<BackCategoryVO> getCategory() {
		
		return backCategoryDAO.getCategory();
	}
	@Override
	public BackCategoryVO selectByIdx(int idx) {
		BackCategoryVO backCategoryVO = null;
		if(idx!=0) {
			backCategoryVO = backCategoryDAO.selectByIdx(idx); 
		}
	    return backCategoryVO;
	}

}
