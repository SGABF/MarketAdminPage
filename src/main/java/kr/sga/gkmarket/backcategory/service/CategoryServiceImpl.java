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
	public void activateCategory(BackCategoryVO backCategoryVO) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		 log.info("호출 : " + backCategoryVO);
	      if (backCategoryVO.getBack_Category_Use()==0) {
	         map.put("back_Category_Use",1);
	         map.put("back_Category_Idx", backCategoryVO.getBack_Category_Idx());
	         backCategoryDAO.activateCategory(map);
	      } else {
	         map.put("back_Category_Use",0);
	         map.put("back_Category_Idx", backCategoryVO.getBack_Category_Idx());
	         backCategoryDAO.activateCategory(map);
	      }
	      log.info("호출 : " + map);
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
