package com.set.controller;
import java.sql.Timestamp;
import java.util.Base64;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.controller.helper.AssignmentControllerHelper;
import com.set.controller.helper.ServiceRequestHelper;
import com.set.dto.ServiceRequestCategoryDto;
import com.set.model.IsExist;
import com.set.model.LuMessage;
import com.set.model.ServiceRequest;
import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestCategoryDetails;
import com.set.model.ServiceRequestSubCategoryDetails;
import com.set.model.SubjectUnit;
import com.set.model.User;
import com.set.service.ServiceRequestCategoryService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;
@CrossOrigin
@RestController
public class ServiceRequestCategoryController {
	@Autowired
	private ServiceRequestCategoryService serviceRequestCategoryService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	ServiceRequestHelper serviceRequestControllerHelper;
	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger("ServiceRequestCategoryController.class");
	/*
	 * @description addClassSubject with details
	 * 
	 * @param @token,ClassSubject
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 */
	@PostMapping(value = "/addServiceRequestCategory", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addServiceRequestCategory(
			@RequestBody ServiceRequestCategory serviceRequestCategory, 
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
		    String parent_category="";   
		    String category_type="M";
		    String assigned_to="";		 
		    serviceRequestCategory.setAssignedTo(assigned_to);
		    serviceRequestCategory.setCategoryType(category_type);
		    serviceRequestCategory.setParentCategory(parent_category);
		    serviceRequestCategory.setCategoryId(CommonUtils.generatePrimaryKeyId("lutbl_category"));
		    serviceRequestCategory.setIsSoftDelete(0);
			Timestamp timestamp =new Timestamp(System.currentTimeMillis());
		    serviceRequestCategory.setInsertedTime(timestamp);
			User user=userService.retrieveFromId(claims.getSubject());
			serviceRequestCategory.setInsertedBy(user.getUserId());
			serviceRequestCategoryService.save(serviceRequestCategory);
			message.setMessage("Insert successfully");
		}
		return message;
	}

	@PutMapping(value = "/addServiceRequestSubCategory/{categoryId}", headers = "Accept=application/json")
	public @ResponseBody LuMessage addServiceRequestSubCategory(
			@PathVariable("categoryId") String categoryId,
			@RequestBody ServiceRequestCategory serviceRequestCategory, 
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp =new Timestamp(System.currentTimeMillis());
		    String category_type="S";
		    serviceRequestCategory.setCategoryType(category_type);
		    serviceRequestCategory.setParentCategory(categoryId);
		    serviceRequestCategory.setCategoryId(CommonUtils.generatePrimaryKeyId("lutbl_subcategory"));
		    serviceRequestCategory.setIsSoftDelete(0);
		    serviceRequestCategory.setInsertedTime(timestamp);
			User user=userService.retrieveFromId(claims.getSubject());
			serviceRequestCategory.setInsertedBy(user.getUserId());
			serviceRequestCategoryService.save(serviceRequestCategory);
			message.setMessage("Insert successfully");
		}
		return message;
	}

	/*
	 * 
	 * @description Update ClassSubject by id
	 * 
	 * @param @Id, @token,classSubject object
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 *
	 */
	@PutMapping(value = "/editServiceRequestCategory/{categoryId}", headers = "Accept=application/json")
	public @ResponseBody LuMessage updateServiceRequestCategory(
			@PathVariable("categoryId") String categoryId,
			@RequestBody ServiceRequestCategory serviceRequestCategory, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
//		 byte[] decodedBytes = Base64.getDecoder().decode(categoryId);
//		 String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp =new Timestamp(System.currentTimeMillis());
		    String parent_category="";
		    String category_type="M";
		    String assigned_to="";		 
		    serviceRequestCategory.setAssignedTo(assigned_to);
		    serviceRequestCategory.setCategoryType(category_type);
		    serviceRequestCategory.setParentCategory(parent_category);
		    serviceRequestCategory.setCategoryId(categoryId);
		    serviceRequestCategory.setIsSoftDelete(0);
		    serviceRequestCategory.setUpdatedTime(timestamp);
			User user=userService.retrieveFromId(claims.getSubject());
			serviceRequestCategory.setUpdatedBy(user.getUserId());
			serviceRequestCategoryService.update(serviceRequestCategory);
			message.setMessage("update successfully");
		}
		return message;
	}

	@PutMapping(value = "/editServiceRequestSubCategory/{categoryId}/{subCategoryId}", headers = "Accept=application/json")
	public @ResponseBody LuMessage updateServiceRequestSubCategory(
			@PathVariable("categoryId") String categoryId,
			@PathVariable("subCategoryId") String subCategoryId,
			@RequestBody ServiceRequestCategory serviceRequestCategory, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp =new Timestamp(System.currentTimeMillis());
		    String category_type="S";
		    serviceRequestCategory.setCategoryType(category_type);
		    serviceRequestCategory.setParentCategory(categoryId);
		    serviceRequestCategory.setCategoryId(subCategoryId);
		    serviceRequestCategory.setIsSoftDelete(0);
		    serviceRequestCategory.setUpdatedTime(timestamp);
			User user=userService.retrieveFromId(claims.getSubject());
			serviceRequestCategory.setUpdatedBy(user.getUserId());
			serviceRequestCategoryService.update(serviceRequestCategory);
			message.setMessage("update successfully");
		}
		return message;
	}

	/*
	 * @description List of ClassSubject
	 * 
	 * @param @token
	 * 
	 * @return List of ClassSubject
	 * 
	 * @ Author Parnay
	 *
	 */
	@GetMapping(value = "/getListServiceRequestCategory", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestCategoryDetails getListServiceRequestCategory(@RequestHeader String token) {
		ServiceRequestCategoryDetails serviceRequestCategoryDetails = new ServiceRequestCategoryDetails();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			serviceRequestCategoryDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			serviceRequestCategoryDetails = serviceRequestCategoryService.list("M");
		}
		return serviceRequestCategoryDetails;
	}
	
	@GetMapping(value = "/getListServiceRequestSubCategory/{categoryId}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestCategoryDetails getListServiceRequestSubCategory(
			@PathVariable("categoryId") String categoryId,
			@RequestHeader String token) {
		ServiceRequestCategoryDetails serviceRequestCategoryDetails = new ServiceRequestCategoryDetails();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			serviceRequestCategoryDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			serviceRequestCategoryDetails = serviceRequestCategoryService.list(categoryId);
		}
		return serviceRequestCategoryDetails;
	}

	/*
	 * @description ClassSubjectby details based on id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubjectbyId
	 * 
	 * @ Author Parnay
	 *
	 */

	@SuppressWarnings("null")
	@GetMapping(value = "/getCategoryById/{categoryId}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestCategory getCategoryById(@PathVariable("categoryId") String categoryId, @RequestHeader String token) {
		ServiceRequestCategory serviceRequestCategory = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			serviceRequestCategory.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			serviceRequestCategory = serviceRequestCategoryService.getCategoryById(categoryId);
		}
		return serviceRequestCategory;
	}
	

	@GetMapping(value = "/getListCategory/{pageNumber}/{searchData}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestCategoryDetails getListCategory(@PathVariable("pageNumber") int pageNumber,
			@PathVariable("searchData") String searchData, @RequestHeader String token) {
		ServiceRequestCategoryDetails serviceRequestCategoryDetails = new ServiceRequestCategoryDetails();
		 byte[] decodedBytes = Base64.getDecoder().decode(searchData);
		 String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			serviceRequestCategoryDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			serviceRequestCategoryDetails = serviceRequestCategoryService.getListCategory("M",pageNumber,decodedString);
		}
		return serviceRequestCategoryDetails;
	}
	

	@GetMapping(value = "/getListSubCategory/{pageNumber}/{searchData}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestSubCategoryDetails getListSubCategory(@PathVariable("pageNumber") int pageNumber,
			@PathVariable("searchData") String searchData, @RequestHeader String token) {
		ServiceRequestSubCategoryDetails serviceRequestSubCategoryDetails = new ServiceRequestSubCategoryDetails();
		 byte[] decodedBytes = Base64.getDecoder().decode(searchData);
		 String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			serviceRequestSubCategoryDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			serviceRequestSubCategoryDetails = serviceRequestCategoryService.getListSubCategory("S",pageNumber,decodedString);
		}
		return serviceRequestSubCategoryDetails;
	}
	
	@GetMapping(value = "/deleteServiceRequestCategory/{categoryId}", headers = "Accept=application/json")
	public @ResponseBody LuMessage deleteServiceRequestCategory(
			@PathVariable("categoryId") String categoryId, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
//		 byte[] decodedBytes = Base64.getDecoder().decode(categoryId);
//		 String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp =new Timestamp(System.currentTimeMillis());
			ServiceRequestCategory newServiceRequest = getCategoryById(categoryId,token);
			newServiceRequest.setIsSoftDelete(1);
			newServiceRequest.setCategoryId(categoryId);
			newServiceRequest.setUpdatedTime(timestamp);
			User user=userService.retrieveFromId(claims.getSubject());
			newServiceRequest.setUpdatedBy(user.getUserId());
			serviceRequestCategoryService.update(newServiceRequest);
			message.setMessage("update successfully");
		}
		return message;
	}


	@RequestMapping(value = "/categoryIsExist", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean resourceExist(@RequestBody LuMessage luMessage,
			@RequestHeader String token) {		
		if (tokenService.tokenValidate(token)) {
			return false;
		} else {
			System.out.println("in query");
			if (serviceRequestCategoryService.IsExist(luMessage.getMessage(),luMessage.getCode())) {
				return true;
			} else {
				return false;
			}
		}
	}


	@RequestMapping(value = "/subCategoryIsExist", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean resourceExist(@RequestBody IsExist luMessage,
			@RequestHeader String token) {		
		if (tokenService.tokenValidate(token)) {
			return false;
		} else {
			System.out.println("in query");
			if (serviceRequestCategoryService.SubCategoryIsExist(luMessage.getMessage(),luMessage.getCode(),luMessage.getExistId())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
}
