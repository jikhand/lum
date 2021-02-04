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

import com.set.controller.helper.ExamControllerHelper;
import com.set.dto.ExamTypeDetailsDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ExamType;
import com.set.model.ExamTypeDetails;
import com.set.service.ExamTypeService;
import com.set.service.TokenService;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class ExamTypeController {
	@Autowired
	private ExamTypeService examTypeService;
	@Autowired
	private ExamControllerHelper examControllerHelper;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("ExamTypeController.class");

@PostMapping(value="/addExamType", headers="Accept=Application/json")
public @ResponseBody ExamType addExamType(@RequestBody ExamType examType,@RequestHeader String token) {
	
	Claims claims=TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		examType.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {	
		examTypeService.save(examType);
		examType.setMessage("Inserted successfully");
	}
	return examType;
}

@PutMapping(value="/editExamType/{id}",headers="Accept=Application/json")
public @ResponseBody ExamType editExamType(@PathVariable("id") String Id, @RequestBody ExamType examType,@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		examType.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {		
		examType.setExamType(Id);
		examTypeService.updateExamType(examType);
		examType.setMessage("Updated successfully");
	}
	return examType;
}


//@PutMapping(value="/deleteExamType/{id}",headers="Accept=Application/json")
//public @ResponseBody ExamType deleteExamType(@PathVariable("id") String Id, @RequestBody ExamType examType,@RequestHeader String token) {
//	Claims claims=TokenUtils.verifyToken(token);
//	if(claims==null) {
//		examType.setMessage("Invalid token");
//	}else {	
//		examType.setIs_soft_delete(1);
//		examTypeService.deleteExamType(examType);
//		examType.setMessage("Deleted successfully");
//	}
//	return examType;
//}


@SuppressWarnings("null")
@GetMapping(value = "/getExamTypeById/{id}", headers = "Accept=application/json")
public @ResponseBody ExamType getExamTypeById(@PathVariable("id") String Id,@RequestHeader String token) {
	ExamType examType = null;
	Claims claims = TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		examType.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {
		examType=examTypeService.getExamTypeById(Id);
	}
	return examType;
}

@GetMapping(value = "/getTotalExamType", headers = "Accept=application/json")
public @ResponseBody int getTotalExamType(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	int i=0;
	
	if (tokenService.tokenValidate(token)) {		
		logger.info("Invalid token");
	} else {
		i=examTypeService.totalExamType();
	}
	return i;
}


@GetMapping(value = "/getAllExamTypeSelect", headers = "Accept=application/json")
public @ResponseBody List<ExamType> getAllExamTypeSelect(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	List<ExamType> examType=new ArrayList<>();
	
	if (tokenService.tokenValidate(token)) {
		ExamType csm = new ExamType();
		csm.setMessage("Invalid token");
		examType.add(csm);
		logger.info("Invalid token");
	} else {
		examType=examTypeService.getAllExamTypeSelect();
	}
	return examType;
}


@GetMapping(value = "/GetAllExamType", headers = "Accept=application/json")
public @ResponseBody ExamTypeDetailsDto getGetExamType(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	ExamTypeDetailsDto examTypeDetailsDto = new ExamTypeDetailsDto();
	ExamTypeDetails examTypeDetails = new ExamTypeDetails();
	
	if (tokenService.tokenValidate(token)) {
		examTypeDetailsDto.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		examTypeDetails = examTypeService.getAllExamType();
		examTypeDetailsDto = examControllerHelper.setExamTypeDetailDto(examTypeDetails);
	}
	return examTypeDetailsDto;
}

}
