package com.set.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.set.dto.QuestionDto;
import com.set.dto.QuestionAnswerDetailsDto;
import com.set.dto.QuestionAnswerDto;
import com.set.dto.QuestionAnswerTypeDetailDto;
import com.set.dto.QuestionDetailsDto;
import com.set.dto.QuestionTypeDetailDto;
import com.set.model.ExamDetail;
import com.set.model.ExamId;
import com.set.model.QuestionPaper;
import com.set.model.QuestionPaperDetails;
import com.set.model.QuestionPaperId;
import com.set.model.Questions;
import com.set.model.ExamQuestions;
import com.set.model.ExamQuestionsId;
import com.set.model.ExamSchedule;
import com.set.model.LuMessage;
import com.set.model.Page;
import com.set.model.User;
import com.set.service.QuestionPaperService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;
import com.set.utils.checkNullException;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class QuestionPaperController {
	@Autowired
	private QuestionPaperService questionPaperService;
	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger("QuestionPaperController.class");

//@PostMapping(value="/AddQuestion", headers="Accept=Application/json")
//public @ResponseBody LuMessage addExamQuestion(
//		@RequestBody QuestionPaper questionPaper,
//		@RequestHeader String token) {
//	Claims claims=TokenUtils.verifyToken(token);
//	LuMessage message = new LuMessage();
//	QuestionPaper tempQuestionPaper = new QuestionPaper();
//	if(claims==null) {
//		message.setMessage("Invalid token");
//	    message.setCode("402");      	
//	}else {	
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        String questionId = questionPaper.getQuestionPaperId().getQuestionId();
//        String examId = questionPaper.getQuestionPaperId().getExamId();
//        int questionNumber = questionPaper.getQuestionPaperId().getQuestionNumber();
//        tempQuestionPaper.setMarksForQuestion(questionPaper.getMarksForQuestion());
//        tempQuestionPaper.setIsSoftDelete(0);
//        User userdata = userService.retrieveFromId(claims.getSubject());      
//        QuestionPaperId questionPaperId = new QuestionPaperId(questionId, examId,questionNumber);
//	    tempQuestionPaper.setQuestionPaperId(questionPaperId);
//        tempQuestionPaper.setInsertedBy(userdata.getUserId());
//		tempQuestionPaper.setInsertedTime(timestamp);
//		boolean isQuestionAlreadyExist = questionPaperService.IsQuestionAlreadyExist(questionPaperId);
//		if (isQuestionAlreadyExist == true) {
//			message.setMessage("Question Already Exist. Please select different Question.");
//		} else {
//			questionPaperService.save(tempQuestionPaper);
//			message.setMessage("Inserted successfully"); 
//		    message.setCode("200 - Success");  
//		}
//	}
//	return message;
//}

@PostMapping(value="/AddQuestion", headers="Accept=Application/json")
public @ResponseBody LuMessage addExamQuestion(
		@RequestBody QuestionPaper questionPaper,
		@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	LuMessage message = new LuMessage();
	QuestionPaper tempQuestionPaper = new QuestionPaper();
	if(claims==null) {
		message.setMessage("Invalid token");
	    message.setCode("402");      	
	}else {	
		Questions e[] = questionPaper.getQuestions();
		for (int i = 0; i <= e.length - 1; i++) {
			String examId = questionPaper.getExamId();
			int questionNumber = e[i].getQuestionNumber();
			String questionId = e[i].getQuestionId();
			int marksForQuestion = e[i].getMarksForQuestion();
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        tempQuestionPaper.setMarksForQuestion(marksForQuestion);
	        tempQuestionPaper.setIsSoftDelete(0);
	        User userdata = userService.retrieveFromId(claims.getSubject());      
	        QuestionPaperId questionPaperId = new QuestionPaperId(questionId, examId, questionNumber);
		    tempQuestionPaper.setQuestionPaperId(questionPaperId);
	        tempQuestionPaper.setInsertedBy(userdata.getUserId());
			tempQuestionPaper.setInsertedTime(timestamp);
			boolean isQuestionAlreadyExist = questionPaperService.IsQuestionAlreadyExist(questionPaperId);
			if (isQuestionAlreadyExist == true) {
				message.setMessage("Question Already Exist. Please select different Question.");
			} else {
				questionPaperService.save(tempQuestionPaper);
				message.setMessage("Inserted successfully"); 
			    message.setCode("200 - Success");
			}
	    }
	}
	return message;
}

@PutMapping(value="/EditQuestion/{questionId}/{examId}/{questionNumber}", headers="Accept=Application/json")
public @ResponseBody LuMessage editExamQuestion(
		@PathVariable("questionId") String questionId,
		@PathVariable("examId") String examId,
		@PathVariable("questionNumber") int questionNumber,
		@RequestBody QuestionPaper questionPaper,
		@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	LuMessage message = new LuMessage();
	QuestionPaper tempQuestionPaper = new QuestionPaper();
	if(claims==null) {
		message.setMessage("Invalid token"); 
	    message.setCode("402");      	
	}else {	
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tempQuestionPaper.setMarksForQuestion(questionPaper.getMarksForQuestion());
        tempQuestionPaper.setIsSoftDelete(0);
        User userdata = userService.retrieveFromId(claims.getSubject());      
        QuestionPaperId questionPaperId = new QuestionPaperId(questionId, examId,questionNumber);
	    tempQuestionPaper.setQuestionPaperId(questionPaperId);
        tempQuestionPaper.setInsertedBy(userdata.getUserId());
		tempQuestionPaper.setInsertedTime(timestamp);
		questionPaperService.updateQuestionPaper(tempQuestionPaper);
		message.setMessage("Updated successfully");  
		message.setCode("200 - Success"); 
    }
	return message;
}

@PostMapping(value = "/GetQuestionListing", headers = "Accept=application/json")
public @ResponseBody QuestionTypeDetailDto getQuestionListing(@RequestBody ExamId examId, @RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> questionDetailList = null;
	List<Object[]> questionTypeDetailList = null;
	List<QuestionDetailsDto> questionDetailsDtoList = new ArrayList<>();
	QuestionTypeDetailDto questionTypeDetailDto = new QuestionTypeDetailDto();
	QuestionDetailsDto questionDetailsDto;
	QuestionDto questionDto;
	if (claims != null) {
		String examid = examId.getExamId();
		questionTypeDetailList = questionPaperService.getAllQuestionTypeList(examid);
		char alphabet = 'A';
		for(Object[] eachQuestionTypeList : questionTypeDetailList) {
			List<QuestionDto> questionDtoList = new ArrayList<>();
			questionDetailsDto = new QuestionDetailsDto();
			String questionTypeId = eachQuestionTypeList[0].toString();
			questionDetailsDto.setQuestionTypeId(questionTypeId);
			questionDetailsDto.setQuestionTypeName(checkNullException.firstNonNull(eachQuestionTypeList[1]).toString());
			int NoOfQuestions = eachQuestionTypeList[2].hashCode();
			int mark = eachQuestionTypeList[3].hashCode();
			int totalMark = NoOfQuestions*mark; 
			questionDetailsDto.setMarks(NoOfQuestions+" x "+ mark +" = "+totalMark);
			questionDetailsDto.setPartName("PART - "+alphabet);
			questionDetailList = questionPaperService.getAllQuestionList(examid,questionTypeId);
			for(Object[] eachQuestionList : questionDetailList) {
				questionDto = new QuestionDto();
				String questionType = eachQuestionList[3].toString(); 
                questionDto.setTestId(checkNullException.firstNonNull(eachQuestionList[0]).toString());
                questionDto.setQuestionId(checkNullException.firstNonNull(eachQuestionList[1]).toString());
                questionDto.setQuestionNumber(eachQuestionList[2].toString());
                questionDto.setQuestionType(questionType);
                questionDto.setLeftSideOptiona(checkNullException.firstNonNull(eachQuestionList[11]).toString());
                questionDto.setLeftSideOptionb(checkNullException.firstNonNull(eachQuestionList[12]).toString());
                questionDto.setLeftSideOptionc(checkNullException.firstNonNull(eachQuestionList[13]).toString());
                questionDto.setLeftSideOptiond(checkNullException.firstNonNull(eachQuestionList[14]).toString());
                questionDto.setLeftSideOptione(checkNullException.firstNonNull(eachQuestionList[15]).toString());
                questionDto.setRightSideOption1(checkNullException.firstNonNull(eachQuestionList[16]).toString());
                questionDto.setRightSideOption2(checkNullException.firstNonNull(eachQuestionList[17]).toString());
                questionDto.setRightSideOption3(checkNullException.firstNonNull(eachQuestionList[18]).toString());
                questionDto.setRightSideOption4(checkNullException.firstNonNull(eachQuestionList[19]).toString());
                questionDto.setRightSideOption5(checkNullException.firstNonNull(eachQuestionList[20]).toString());
                questionDto.setQuestion(checkNullException.firstNonNull(eachQuestionList[4]).toString());
                questionDto.setQuestionPath(checkNullException.firstNonNull(eachQuestionList[5]).toString());
                questionDto.setMark(eachQuestionList[6].toString());
                questionDto.setOptiona(checkNullException.firstNonNull(eachQuestionList[7]).toString());
                questionDto.setOptionb(checkNullException.firstNonNull(eachQuestionList[8]).toString());
                questionDto.setOptionc(checkNullException.firstNonNull(eachQuestionList[9]).toString());
                questionDto.setOptiond(checkNullException.firstNonNull(eachQuestionList[10]).toString()); 
                questionDtoList.add(questionDto);
			}
			questionDetailsDto.setQuestionList(questionDtoList);
			questionDetailsDtoList.add(questionDetailsDto);
			alphabet++;
			}
		questionTypeDetailDto.setQuestionDetailsDtoList(questionDetailsDtoList);
		questionTypeDetailDto.setCount(questionDetailsDtoList.size());
		questionTypeDetailDto.setMessage("list of Questions");
	}else {
		questionTypeDetailDto.setMessage("Invalid Token");
	}
	return questionTypeDetailDto;
}


@PostMapping(value = "/GetQuestionAnswer", headers = "Accept=application/json")
public @ResponseBody QuestionAnswerTypeDetailDto GetQuestionAnswer(@RequestBody ExamId examId, @RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> questionAnswerDetailList = null;
	List<Object[]> questionAnswerTypeDetailList = null;
	List<QuestionAnswerDetailsDto> questionAnswerDetailsDtoList = new ArrayList<>();
	QuestionAnswerTypeDetailDto questionAnswerTypeDetailDto = new QuestionAnswerTypeDetailDto();
	QuestionAnswerDetailsDto questionAnswerDetailsDto;
	QuestionAnswerDto questionAnswerDto;
	if (claims != null) {
		String examid = examId.getExamId();
		questionAnswerTypeDetailList = questionPaperService.getAllQuestionTypeList(examid);
		char alphabet = 'A';
		for(Object[] eachQuestionAnswerTypeList : questionAnswerTypeDetailList) {
			List<QuestionAnswerDto> questionAnswerDtoList = new ArrayList<>();
			questionAnswerDetailsDto = new QuestionAnswerDetailsDto();
			String questionAnswerTypeId = eachQuestionAnswerTypeList[0].toString();
			questionAnswerDetailsDto.setQuestionAnswerTypeId(questionAnswerTypeId);
			questionAnswerDetailsDto.setQuestionAnswerTypeName(checkNullException.firstNonNull(eachQuestionAnswerTypeList[1]).toString());
			int NoOfQuestionAnswers = eachQuestionAnswerTypeList[2].hashCode();
			int mark = eachQuestionAnswerTypeList[3].hashCode();
			int totalMark = NoOfQuestionAnswers*mark; 
			questionAnswerDetailsDto.setMarks(NoOfQuestionAnswers+" x "+ mark +" = "+totalMark);
			questionAnswerDetailsDto.setPartName("PART - "+alphabet);
			questionAnswerDetailList = questionPaperService.getAllQuestionList(examid,questionAnswerTypeId);
			for(Object[] eachQuestionAnswerList : questionAnswerDetailList) {
				questionAnswerDto = new QuestionAnswerDto();
				String questionAnswerType = eachQuestionAnswerList[3].toString(); 
                questionAnswerDto.setTestId(checkNullException.firstNonNull(eachQuestionAnswerList[0]).toString());
                questionAnswerDto.setQuestionId(checkNullException.firstNonNull(eachQuestionAnswerList[1]).toString());
                questionAnswerDto.setQuestionNumber(eachQuestionAnswerList[2].toString());
                questionAnswerDto.setQuestionType(questionAnswerType);
                questionAnswerDto.setLeftSideOptiona(checkNullException.firstNonNull(eachQuestionAnswerList[11]).toString());
                questionAnswerDto.setLeftSideOptionb(checkNullException.firstNonNull(eachQuestionAnswerList[12]).toString());
                questionAnswerDto.setLeftSideOptionc(checkNullException.firstNonNull(eachQuestionAnswerList[13]).toString());
                questionAnswerDto.setLeftSideOptiond(checkNullException.firstNonNull(eachQuestionAnswerList[14]).toString());
                questionAnswerDto.setLeftSideOptione(checkNullException.firstNonNull(eachQuestionAnswerList[15]).toString());
                questionAnswerDto.setRightSideOption1(checkNullException.firstNonNull(eachQuestionAnswerList[16]).toString());
                questionAnswerDto.setRightSideOption2(checkNullException.firstNonNull(eachQuestionAnswerList[17]).toString());
                questionAnswerDto.setRightSideOption3(checkNullException.firstNonNull(eachQuestionAnswerList[18]).toString());
                questionAnswerDto.setRightSideOption4(checkNullException.firstNonNull(eachQuestionAnswerList[19]).toString());
                questionAnswerDto.setRightSideOption5(checkNullException.firstNonNull(eachQuestionAnswerList[20]).toString());
                questionAnswerDto.setQuestion(checkNullException.firstNonNull(eachQuestionAnswerList[4]).toString());
                questionAnswerDto.setQuestionPath(checkNullException.firstNonNull(eachQuestionAnswerList[5]).toString());
                questionAnswerDto.setMark(eachQuestionAnswerList[6].toString());
                questionAnswerDto.setOptiona(checkNullException.firstNonNull(eachQuestionAnswerList[7]).toString());
                questionAnswerDto.setOptionb(checkNullException.firstNonNull(eachQuestionAnswerList[8]).toString());
                questionAnswerDto.setOptionc(checkNullException.firstNonNull(eachQuestionAnswerList[9]).toString());
                questionAnswerDto.setOptiond(checkNullException.firstNonNull(eachQuestionAnswerList[10]).toString());
                questionAnswerDto.setAnswer(checkNullException.firstNonNull(eachQuestionAnswerList[21]).toString());
                questionAnswerDto.setAnswerMatchForOptiona(checkNullException.firstNonNull(eachQuestionAnswerList[22]).toString());
                questionAnswerDto.setAnswerMatchForOptionb(checkNullException.firstNonNull(eachQuestionAnswerList[23]).toString());
                questionAnswerDto.setAnswerMatchForOptionc(checkNullException.firstNonNull(eachQuestionAnswerList[24]).toString());
                questionAnswerDto.setAnswerMatchForOptiond(checkNullException.firstNonNull(eachQuestionAnswerList[25]).toString());
                questionAnswerDto.setAnswerMatchForOptione(checkNullException.firstNonNull(eachQuestionAnswerList[26]).toString());
                questionAnswerDto.setAnswerPath(checkNullException.firstNonNull(eachQuestionAnswerList[27]).toString());                                    	
                questionAnswerDtoList.add(questionAnswerDto);
			}
			questionAnswerDetailsDto.setQuestionAnswerList(questionAnswerDtoList);
			questionAnswerDetailsDtoList.add(questionAnswerDetailsDto);
			alphabet++;
			}
		questionAnswerTypeDetailDto.setQuestionAnswerDetailsDtoList(questionAnswerDetailsDtoList);
		questionAnswerTypeDetailDto.setCount(questionAnswerDetailsDtoList.size());
		questionAnswerTypeDetailDto.setMessage("list of QuestionAnswers");
	}else {
		questionAnswerTypeDetailDto.setMessage("Invalid Token");
	}
	return questionAnswerTypeDetailDto;
}

@GetMapping(value = "/getTotalQuestionPaper", headers = "Accept=application/json")
public @ResponseBody int getTotalQuestionPaper(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	int i=0;
	if (claims != null) {
		logger.info("list of questionPaper");
		i=questionPaperService.totalQuestionPaper();
	}
	return i;
}

@GetMapping(value = "/getAllQuestionPaper/{id}/{searchdata}", headers = "Accept=application/json")
public @ResponseBody QuestionPaperDetails getAllQuestionPaper(@PathVariable("id") int id,@PathVariable("searchdata") String searchdata,@RequestHeader String token) {
//	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
//	String decodedString = new String(decodedBytes);
	Claims claims=TokenUtils.verifyToken(token);
	QuestionPaperDetails questionPaperDetails=new QuestionPaperDetails();
	if (claims != null) {
		questionPaperDetails=questionPaperService.getAllQuestionPaper(id, searchdata);
	}else {
		questionPaperDetails.setMessage("Invalide Token");
	}
	return questionPaperDetails;
}


@GetMapping(value = "/getAllQuestionPaperSelect", headers = "Accept=application/json")
public @ResponseBody List<QuestionPaper> getAllQuestionPaperSelect(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	List<QuestionPaper> questionPaper=null;
	if (claims != null) {
		questionPaper=questionPaperService.getAllQuestionPaperSelect();
	}
	return questionPaper;
}
}
