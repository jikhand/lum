package com.set.controller;

import java.sql.Timestamp;
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
import com.set.dto.QuestionTypeDetailsDto;
import com.set.model.QuestionTypeDetails;
import com.set.model.QuestionType;
import com.set.model.QuestionTypeDetails;
import com.set.service.QuestionTypeService;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class QuestionTypeController {
	@Autowired
	private QuestionTypeService questionTypeService;
	@Autowired
	private ExamControllerHelper examControllerHelper;
	private Logger logger = Logger.getLogger("QuestionTypeController.class");

@PostMapping(value="/addQuestionType", headers="Accept=Application/json")
public @ResponseBody QuestionType addQuestionType(@RequestBody QuestionType questionType,@RequestHeader String token) {
	
	Claims claims=TokenUtils.verifyToken(token);
	if(claims==null) {
		questionType.setMessage("Invalid token");
	}else {	
		questionTypeService.save(questionType);
		questionType.setMessage("Inserted successfully");
	}
	return questionType;
}

@PutMapping(value="/editQuestionType/{id}",headers="Accept=Application/json")
public @ResponseBody QuestionType editQuestionType(@PathVariable("id") String Id, @RequestBody QuestionType questionType,@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	if(claims==null) {
		questionType.setMessage("Invalid token");
	}else {		
		questionType.setQuestionType(Id);
		questionTypeService.updateQuestionType(questionType);
		questionType.setMessage("Updated successfully");
	}
	return questionType;
}


//@PutMapping(value="/deleteQuestionType/{id}",headers="Accept=Application/json")
//public @ResponseBody QuestionType deleteQuestionType(@PathVariable("id") String Id, @RequestBody QuestionType questionType,@RequestHeader String token) {
//	Claims claims=TokenUtils.verifyToken(token);
//	if(claims==null) {
//		questionType.setMessage("Invalid token");
//	}else {	
//		questionType.setIs_soft_delete(1);
//		questionTypeService.deleteQuestionType(questionType);
//		questionType.setMessage("Deleted successfully");
//	}
//	return questionType;
//}


@SuppressWarnings("null")
@GetMapping(value = "/getQuestionTypeById/{id}", headers = "Accept=application/json")
public @ResponseBody QuestionType getQuestionTypeById(@PathVariable("id") String Id,@RequestHeader String token) {
	QuestionType questionType = null;
	Claims claims = TokenUtils.verifyToken(token);
	if (claims == null) {
		questionType.setMessage("Invalid token");
	}else {
		questionType=questionTypeService.getQuestionTypeById(Id);
	}
	return questionType;
}

@GetMapping(value = "/getTotalQuestionType", headers = "Accept=application/json")
public @ResponseBody int getTotalQuestionType(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	int i=0;
	if (claims != null) {
		i=questionTypeService.totalQuestionType();
	}
	return i;
}


@GetMapping(value = "/getAllQuestionTypeSelect", headers = "Accept=application/json")
public @ResponseBody List<QuestionType> getAllQuestionTypeSelect(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	List<QuestionType> questionType=null;
	if (claims != null) {
		questionType=questionTypeService.getAllQuestionTypeSelect();
	}
	return questionType;
}



@GetMapping(value = "/GetAllQuestionType", headers = "Accept=application/json")
public @ResponseBody QuestionTypeDetailsDto getGetQuestionType(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	QuestionTypeDetailsDto questionTypeDetailsDto = new QuestionTypeDetailsDto();
	QuestionTypeDetails questionTypeDetails = new QuestionTypeDetails();
	if (claims != null) {
		questionTypeDetails = questionTypeService.getAllQuestionType();
		questionTypeDetailsDto = examControllerHelper.setQuestionTypeDetailDto(questionTypeDetails);
	}else {
		questionTypeDetailsDto.setMessage("Invalid Token");
	}
	return questionTypeDetailsDto;
}
}
