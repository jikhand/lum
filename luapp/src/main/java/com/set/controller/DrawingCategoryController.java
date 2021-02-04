package com.set.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

import com.set.model.ClassSectionMaster;
import com.set.model.DrawingCategory;
import com.set.model.DrawingCategoryDetails;
import com.set.service.DrawingCategoryService;
import com.set.service.TokenService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;



@CrossOrigin
@RestController
public class DrawingCategoryController {
	@Autowired
	private DrawingCategoryService drawingCategoryService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("DrawingCategoryController.class");

	@PostMapping(value = "/InsertDrawingCategory", headers = "Accept=Application/json")
	public @ResponseBody String addDrawingCategory(@RequestBody String drawingCategory) {
		DrawingCategory tempDrawingCategory = new DrawingCategory();
		boolean isExistCategoryName = drawingCategoryService.IsExist(drawingCategory);
		if (isExistCategoryName == true) {
			DrawingCategory category = drawingCategoryService.getDrawingCategoryByName(drawingCategory);
			String drawingCategoryId = category.getDrawingCategoryId();
			return drawingCategoryId;
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String drawingCategoryId = CommonUtils.generatePrimaryKeyId("lutbl_category_drawing");
			tempDrawingCategory.setDrawingCategoryId(drawingCategoryId);
			tempDrawingCategory.setDrawingCategoryName(drawingCategory);
			tempDrawingCategory.setCreatedAt(timestamp);
			tempDrawingCategory.setIsDeleted(false);
			drawingCategoryService.save(tempDrawingCategory);
			return drawingCategoryId;
		}
	}

	@PutMapping(value = "/editDrawingCategory/{id}", headers = "Accept=Application/json")
	public @ResponseBody DrawingCategory editDrawingCategory(@PathVariable("id") String Id,
			@RequestBody DrawingCategory drawingCategory, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			drawingCategory.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			drawingCategory.setDrawingCategoryId(Id);
			drawingCategory.setUpdatedAt(timestamp);
			drawingCategory.setIsDeleted(false);
			drawingCategoryService.updateDrawingCategory(drawingCategory);
			drawingCategory.setMessage("Updated successfully");
		}
		return drawingCategory;
	}

	@PutMapping(value = "/deleteDrawingCategory/{id}", headers = "Accept=Application/json")
	public @ResponseBody DrawingCategory deleteDrawingCategory(@PathVariable("id") String Id,
			@RequestBody DrawingCategory drawingCategory, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			drawingCategory.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			drawingCategory.setDrawingCategoryId(Id);
			drawingCategory.setUpdatedAt(timestamp);
			drawingCategory.setIsDeleted(true);
			drawingCategoryService.deleteDrawingCategory(drawingCategory);
			drawingCategory.setMessage("Deleted successfully");
		}
		return drawingCategory;
	}

	@GetMapping(value = "/getDrawingCategoryById/{id}", headers = "Accept=application/json")
	public @ResponseBody DrawingCategory getDrawingCategoryById(@PathVariable("id") String Id,
			@RequestHeader String token) {
		DrawingCategory drawingCategory = new DrawingCategory();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			drawingCategory.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			drawingCategory = drawingCategoryService.getDrawingCategoryById(Id);
		}
		return drawingCategory;
	}

	@GetMapping(value = "/getDrawingCategoryByName/{catName}", headers = "Accept=application/json")
	public @ResponseBody DrawingCategory getDrawingCategoryByName(@PathVariable("catName") String categoryName,
			@RequestHeader String token) {
		DrawingCategory drawingCategory = new DrawingCategory();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			drawingCategory.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			drawingCategory = drawingCategoryService.getDrawingCategoryByName(categoryName);
		}
		return drawingCategory;
	}

	@GetMapping(value = "/getTotalDrawingCategory", headers = "Accept=application/json")
	public @ResponseBody int getTotalDrawingCategory(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		int i = 0;
		
		if (tokenService.tokenValidate(token)) {			
			logger.info("Invalid token");
		} else {
			logger.info("list of DrawingCategory");
			i = drawingCategoryService.totalDrawingCategory();
		}
		return i;
	}

	@GetMapping(value = "/getAllDrawingCategory/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody DrawingCategoryDetails getAllDrawingCategory(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		DrawingCategoryDetails drawingCategoryDetails = new DrawingCategoryDetails();
		
		if (tokenService.tokenValidate(token)) {
			drawingCategoryDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			drawingCategoryDetails.setMessage("get All DrawingCategory");
			drawingCategoryDetails = drawingCategoryService.getAllDrawingCategory(id, searchdata);
		} 
		return drawingCategoryDetails;
	}

	@GetMapping(value = "/getAllDrawingCategorySelect", headers = "Accept=application/json")
	public @ResponseBody List<DrawingCategory> getAllDrawingCategorySelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<DrawingCategory> drawingCategory = new ArrayList<>();
		
		if (tokenService.tokenValidate(token)) {
			DrawingCategory csm = new DrawingCategory();
			csm.setMessage("Invalid token");
			drawingCategory.add(csm);
			logger.info("Invalid token");
		} else {
			drawingCategory = drawingCategoryService.getAllDrawingCategorySelect();
		}
		return drawingCategory;
	}
}
