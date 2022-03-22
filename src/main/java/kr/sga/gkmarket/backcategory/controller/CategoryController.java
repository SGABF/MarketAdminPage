package kr.sga.gkmarket.backcategory.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.sga.gkmarket.backcategory.dao.BackCategoryDAO;
import kr.sga.gkmarket.backcategory.service.CategoryService;
import kr.sga.gkmarket.backcategory.vo.BackCategoryVO;
import kr.sga.gkmarket.notice.service.NoticeService;
import kr.sga.gkmarket.notice.vo.BackNoticeVO;


@Controller 

public class CategoryController {
	
    @Autowired
    private CategoryService categoryService; //서비스 연결

    @RequestMapping("/category") //주소 지정
    @ResponseBody
    public List<BackCategoryVO> openCategoryList(){
    	List<BackCategoryVO> list = categoryService.getCategory();
    	return list;
    }
    @PostMapping(value = "/category/insert")
	public String insertCategory(@RequestBody BackCategoryVO backCategoryVO , Model model) {
    	categoryService.insertCategory(backCategoryVO);
    	model.addAttribute("vo",backCategoryVO);
	
		return "redirect:/category";
	}
    @PostMapping(value = "/category/activate")
    public String activateCategory(@RequestParam(required = false) int back_Category_Idx) {
    	BackCategoryVO dbvo = null;
    	dbvo = categoryService.selectByIdx(back_Category_Idx);
    	categoryService.activateCategory(dbvo);
    	
		return "/category/activate";
    	
    }
    
    
}