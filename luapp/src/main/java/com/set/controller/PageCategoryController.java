package com.set.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.model.Drawing;
import com.set.model.LuMessage;
import com.set.model.PageCategory;
import com.set.model.PageCategoryDetails;
import com.set.service.PageCategoryService;
import com.set.service.TokenService;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;


@CrossOrigin
@RestController
public class PageCategoryController {
	
	@Autowired
	private PageCategoryService pageCategoryService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	ServletContext context;
	
	private Logger logger = Logger.getLogger("PageCategoryController.class");
	
	//Insert the PageCategory
	@PostMapping(value = "/AddPageCategory", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addPageCategory(@RequestBody PageCategory pageCategory, @RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
				
			pageCategoryService.save(pageCategory);
			message.setMessage("Page Category Inserted Successfully");
		}
		return message;
	}
	
	//edit the PageCategory by id
	@PutMapping(value = "/EditPageCategory/{id}", headers = "Accept=application/json")
	public @ResponseBody LuMessage editPageCategory(@PathVariable("id") int id, @RequestBody PageCategory pageCategory,
			@RequestHeader String token) {			
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			
		} else {
			
			pageCategory.setPageCategoryId(id);
			pageCategory.setPageCategoryTypeName(pageCategory.getPageCategoryTypeName());
			pageCategoryService.editPageCategory(pageCategory);
			message.setMessage("Page Category Updated successfully");
			
		}
		return message;
	}
	@GetMapping(value = "/getPageCategoryById/{id}", headers = "Accept=application/json")
	public @ResponseBody PageCategory getPageCategoryById(@PathVariable("id") String Id, @RequestHeader String token) {
		PageCategory pageCategory = new PageCategory();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			pageCategory.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			pageCategory = pageCategoryService.getPageCategoryById(Id);
		}
		return pageCategory;
	}

	//delete the PageCategory by id
	@PutMapping(value = "/DeletePageCategory/{id}", headers = "Accept=Application/json")
	public @ResponseBody LuMessage deletePageCategory(@PathVariable("id") int id,
			@RequestBody PageCategory pageCategory, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			
			pageCategory.setPageCategoryId(id);			
			pageCategoryService.deletePageCategory(pageCategory);
			message.setMessage("Page Category Deleted successfully");
		}
		return message;
	}
	
	//View the PageCategory list		
	@GetMapping(value = "/ListPageCategory", headers = "Accept=application/json")
	public @ResponseBody PageCategoryDetails listPageCategory(@RequestHeader String token) {		
		PageCategoryDetails pageCategoryDetails =new PageCategoryDetails();	
		Claims claims = TokenUtils.verifyToken(token);
		
		if (tokenService.tokenValidate(token)) {
			pageCategoryDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			pageCategoryDetails=pageCategoryService.listPageCategory();			
		}
		return pageCategoryDetails;
	}	
	@GetMapping(value = "/ListPageCategorySelect", headers = "Accept=application/json")
	public @ResponseBody List<PageCategory> listPageCategorySelect(@RequestHeader String token) {		
		List<PageCategory> pageCategory = new ArrayList<>();
		Claims claims = TokenUtils.verifyToken(token);
		
		if (tokenService.tokenValidate(token)) {
			PageCategory csm = new PageCategory();
			csm.setMessage("Invalid token");
			pageCategory.add(csm);
			logger.info("Invalid token");
		} else {
			pageCategory=pageCategoryService.listPageCategorySelect();			
		}
		return pageCategory;
	}
}