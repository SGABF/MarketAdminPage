package kr.sga.gkmarket.backcategory.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping(value = "/MainView/BackCategoryControl")
	public String BackCategoryControlPage() {
		return "/BackCategoryControl";
	}
    
    @GetMapping("/category/categoryList") //주소 지정
    @ResponseBody
    public List<BackCategoryVO> openCategoryList(){
    	
    	List<BackCategoryVO> categoryList = categoryService.getCategory();
    	
    	return categoryList;
    }
    @PostMapping(value = "/category/insertCategory")
	public String insertCategory(@RequestBody BackCategoryVO backCategoryVO , Model model) {
    	
    	categoryService.insertCategory(backCategoryVO);
	
		return "/category/categoryList";
	}
    @PostMapping(value = "/category/activateCategory")
    @ResponseBody
    public void activateCategory(@RequestParam("back_Category_Idx")int back_Category_Idx) {
    	
    	categoryService.activateCategory(back_Category_Idx);
    }
    
    
}