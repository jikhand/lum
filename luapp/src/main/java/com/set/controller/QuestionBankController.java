package com.set.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.hql.internal.ast.tree.IsNullLogicOperatorNode;
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
import com.set.dto.QuestionBankDetailDto;
import com.set.dto.QuestionBankDto;
import com.set.dto.QuestionDetailsDto;
import com.set.dto.QuestionTypeDetailDto;
import com.set.model.ClassSubId;
import com.set.model.ExamDetail;
import com.set.model.ExamId;
import com.set.model.QuestionBank;
import com.set.model.QuestionBankDetails;
import com.set.model.ExamQuestions;
import com.set.model.ExamQuestionsId;
import com.set.model.ExamSchedule;
import com.set.model.LuMessage;
import com.set.model.User;
import com.set.service.QuestionBankService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;
import com.set.utils.checkNullException;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class QuestionBankController {
	@Autowired
	private QuestionBankService questionBankService;
	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger("QuestionBankController.class");

@PostMapping(value="/AddQuestionBank", headers="Accept=Application/json")
public @ResponseBody LuMessage addQuestionBank(
		@RequestBody QuestionBank questionBank,
		@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	LuMessage message = new LuMessage();
	QuestionBank tempQuestionBank = new QuestionBank();
	if(claims==null) {
		message.setMessage("Invalid token");
	    message.setCode("401");
	}else {	
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String questionsId = questionBank.getQuestionId();
        String questionType = questionBank.getQuestionTypeId();
        tempQuestionBank.setQuestionTypeId(questionType);  
        tempQuestionBank.setIsSoftDelete(0);
        tempQuestionBank.setClassId(questionBank.getClassId());
        tempQuestionBank.setSubjectId(questionBank.getSubjectId());
        tempQuestionBank.setAnswer(questionBank.getAnswer());
        String base64Image = questionBank.getAnswerPath();
        String answerPath = "";
        String questionPath = "";
        if ( base64Image != null && !base64Image.isEmpty () ){
            String fileName = "answerImage"+System.currentTimeMillis();
        	String extension = "png";
        	answerPath = Constant.EXAM_UPLOAD_DIRECTORY+fileName+"."+extension; 
        	ImageUpload.decoder(base64Image, answerPath);
        }
        String base64Image1 = questionBank.getQuestionPath();
        if ( base64Image != null && !base64Image.isEmpty () ){
        	String fileName1 = "questionImage"+System.currentTimeMillis();
        	String extension1 = "png";
        	questionPath = Constant.EXAM_UPLOAD_DIRECTORY+fileName1+"."+extension1; 
        	ImageUpload.decoder(base64Image1, questionPath);        	
        }
        tempQuestionBank.setQuestionPath(questionPath);
        tempQuestionBank.setAnswerPath(answerPath);
        tempQuestionBank.setOptionA(questionBank.getOptionA());
        tempQuestionBank.setOptionB(questionBank.getOptionB());
        tempQuestionBank.setOptionC(questionBank.getOptionC());
        tempQuestionBank.setOptionD(questionBank.getOptionD());
        tempQuestionBank.setQuestion(questionBank.getQuestion());
        User userdata = userService.retrieveFromId(claims.getSubject());   
        tempQuestionBank.setLeftSideOptiona(questionBank.getLeftSideOptiona());
        tempQuestionBank.setLeftSideOptionb(questionBank.getLeftSideOptionb());
        tempQuestionBank.setLeftSideOptionc(questionBank.getLeftSideOptionc());
        tempQuestionBank.setLeftSideOptiond(questionBank.getLeftSideOptiond());
        tempQuestionBank.setLeftSideOptione(questionBank.getLeftSideOptione());
        tempQuestionBank.setRightSideOption1(questionBank.getRightSideOption1());
        tempQuestionBank.setRightSideOption2(questionBank.getRightSideOption2());
        tempQuestionBank.setRightSideOption3(questionBank.getRightSideOption3());
        tempQuestionBank.setRightSideOption4(questionBank.getRightSideOption4());
        tempQuestionBank.setRightSideOption5(questionBank.getRightSideOption5());
        tempQuestionBank.setAnswerMatchForOptiona(questionBank.getAnswerMatchForOptiona());
        tempQuestionBank.setAnswerMatchForOptionb(questionBank.getAnswerMatchForOptionb());
        tempQuestionBank.setAnswerMatchForOptionc(questionBank.getAnswerMatchForOptionc());
        tempQuestionBank.setAnswerMatchForOptiond(questionBank.getAnswerMatchForOptiond());
        tempQuestionBank.setAnswerMatchForOptione(questionBank.getAnswerMatchForOptione());
        if((questionsId.isEmpty())||(questionsId.equalsIgnoreCase("null"))) {
	    	String questionId = CommonUtils.generatePrimaryKeyId("lutbl_question_bank");
	    	tempQuestionBank.setQuestionId(questionId);
            tempQuestionBank.setInsertedBy(userdata.getUserId());
		    tempQuestionBank.setInsertedTime(timestamp);
		    questionBankService.save(tempQuestionBank);
		    message.setMessage("Inserted successfully");
		    message.setCode("200 - Success");
	    }else {
        	tempQuestionBank.setUpdatedTime(timestamp);
        	tempQuestionBank.setUpdatedBy(userdata.getUserId());
        	tempQuestionBank.setQuestionId(questionsId);	
		    questionBankService.updateQuestionBank(tempQuestionBank);
		    message.setMessage("Updated successfully");  
		    message.setCode("200 - Success");      	
        }
	}
	return message;
}

@PostMapping(value = "/GetQuestionBankListing", headers = "Accept=application/json")
public @ResponseBody QuestionBankDetailDto getQuestionBankListing(@RequestBody ClassSubId classSubjectId, @RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> questionDetailList = null;
	QuestionBankDetailDto questionBankDetailDto = new QuestionBankDetailDto();
	List<QuestionBankDto> questionDetailsDtoList = new ArrayList<>();
	QuestionBankDto questionDto;
	if (claims != null) {
		String classid = classSubjectId.getClassId();
		String subjectid = classSubjectId.getSubjectId();
		String questionTypeId = classSubjectId.getQuestionTypeId();
			questionDetailList = questionBankService.getAllQuestionList(classid,subjectid,questionTypeId);
			for(Object[] eachQuestionList : questionDetailList) {
				questionDto = new QuestionBankDto();
				questionDto.setQuestionId(checkNullException.firstNonNull(eachQuestionList[0]).toString());
                questionDto.setQuestionType(checkNullException.firstNonNull(eachQuestionList[20]).toString());
                questionDto.setLeftSideOptiona(checkNullException.firstNonNull(eachQuestionList[9]).toString());
                questionDto.setLeftSideOptionb(checkNullException.firstNonNull(eachQuestionList[10]).toString());
                questionDto.setLeftSideOptionc(checkNullException.firstNonNull(eachQuestionList[11]).toString());
                questionDto.setLeftSideOptiond(checkNullException.firstNonNull(eachQuestionList[12]).toString());
                questionDto.setLeftSideOptione(checkNullException.firstNonNull(eachQuestionList[13]).toString());
                questionDto.setRightSideOption1(checkNullException.firstNonNull(eachQuestionList[21]).toString());
                questionDto.setRightSideOption2(checkNullException.firstNonNull(eachQuestionList[22]).toString());
                questionDto.setRightSideOption3(checkNullException.firstNonNull(eachQuestionList[23]).toString());
                questionDto.setRightSideOption4(checkNullException.firstNonNull(eachQuestionList[24]).toString());
                questionDto.setRightSideOption5(checkNullException.firstNonNull(eachQuestionList[25]).toString());                	
                questionDto.setQuestion(checkNullException.firstNonNull(eachQuestionList[18]).toString());
                questionDto.setQuestionPath(checkNullException.firstNonNull(eachQuestionList[19]).toString());
                questionDto.setOptiona(checkNullException.firstNonNull(eachQuestionList[14]).toString());
                questionDto.setOptionb(checkNullException.firstNonNull(eachQuestionList[15]).toString());
                questionDto.setOptionc(checkNullException.firstNonNull(eachQuestionList[16]).toString());
                questionDto.setOptiond(checkNullException.firstNonNull(eachQuestionList[17]).toString());                	
                questionDetailsDtoList.add(questionDto);
			}
		questionBankDetailDto.setQuestionList(questionDetailsDtoList);
		questionBankDetailDto.setCount(questionDetailsDtoList.size());
		questionBankDetailDto.setMessage("list of Questions");
	}else {
		questionBankDetailDto.setMessage("Invalid Token");
	}
	return questionBankDetailDto;
}

@PostMapping(value = "/GetQuestionBankListingByDate", headers = "Accept=application/json")
public @ResponseBody QuestionBankDetailDto GetQuestionBankListingByDate(@RequestBody ClassSubId classSubjectId, @RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> questionDetailList = null;
	QuestionBankDetailDto questionBankDetailDto = new QuestionBankDetailDto();
	List<QuestionBankDto> questionDetailsDtoList = new ArrayList<>();
	QuestionBankDto questionDto;
	if (claims != null) {
		String classid = classSubjectId.getClassId();
		String subjectid = classSubjectId.getSubjectId();
		String questionTypeId = classSubjectId.getQuestionTypeId();
		String questionDate = classSubjectId.getQuestionDate();
			questionDetailList = questionBankService.getAllQuestionListByDate(classid,subjectid,questionTypeId,questionDate);
			for(Object[] eachQuestionList : questionDetailList) {
				questionDto = new QuestionBankDto();
				questionDto.setQuestionId(checkNullException.firstNonNull(eachQuestionList[0]).toString());
                questionDto.setQuestionType(checkNullException.firstNonNull(eachQuestionList[20]).toString());
                questionDto.setLeftSideOptiona(checkNullException.firstNonNull(eachQuestionList[9]).toString());
                questionDto.setLeftSideOptionb(checkNullException.firstNonNull(eachQuestionList[10]).toString());
                questionDto.setLeftSideOptionc(checkNullException.firstNonNull(eachQuestionList[11]).toString());
                questionDto.setLeftSideOptiond(checkNullException.firstNonNull(eachQuestionList[12]).toString());
                questionDto.setLeftSideOptione(checkNullException.firstNonNull(eachQuestionList[13]).toString());
                questionDto.setRightSideOption1(checkNullException.firstNonNull(eachQuestionList[21]).toString());
                questionDto.setRightSideOption2(checkNullException.firstNonNull(eachQuestionList[22]).toString());
                questionDto.setRightSideOption3(checkNullException.firstNonNull(eachQuestionList[23]).toString());
                questionDto.setRightSideOption4(checkNullException.firstNonNull(eachQuestionList[24]).toString());
                questionDto.setRightSideOption5(checkNullException.firstNonNull(eachQuestionList[25]).toString());                	
                questionDto.setQuestion(checkNullException.firstNonNull(eachQuestionList[18]).toString());
                questionDto.setQuestionPath(checkNullException.firstNonNull(eachQuestionList[19]).toString());
                questionDto.setOptiona(checkNullException.firstNonNull(eachQuestionList[14]).toString());
                questionDto.setOptionb(checkNullException.firstNonNull(eachQuestionList[15]).toString());
                questionDto.setOptionc(checkNullException.firstNonNull(eachQuestionList[16]).toString());
                questionDto.setOptiond(checkNullException.firstNonNull(eachQuestionList[17]).toString());                	
                questionDetailsDtoList.add(questionDto);
			}
		questionBankDetailDto.setQuestionList(questionDetailsDtoList);
		questionBankDetailDto.setCount(questionDetailsDtoList.size());
		questionBankDetailDto.setMessage("list of Questions");
	}else {
		questionBankDetailDto.setMessage("Invalid Token");
	}
	return questionBankDetailDto;
}


}
