package com.set.controller;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
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

import com.set.dto.SubmitStudentDto;
import com.set.dto.AbsentStudentDto;
import com.set.dto.ReportMarksDto;
import com.set.dto.ReportStudentDetailsDto;
import com.set.dto.StudentAnswerDetailsDto;
import com.set.dto.StudentAnswerDto;
import com.set.dto.StudentAnswerTypeDetailDto;
import com.set.dto.SubmitStudentDetailsDto;
import com.set.model.ClassSection;
import com.set.model.ClassSectionMaster;
import com.set.model.Exam;
import com.set.model.ExamId;
import com.set.model.ExamPaper;
import com.set.model.ExamPaperId;
import com.set.model.ExamStudent;
import com.set.model.ExamStudentDetails;
import com.set.model.ExamStudentId;
import com.set.model.LuMessage;
import com.set.model.Page;
import com.set.model.Student;
import com.set.model.StudentExamId;
import com.set.model.StudentNotesId;
import com.set.model.User;
import com.set.service.ExamScheduleService;
import com.set.service.ExamStudentService;
import com.set.service.TokenService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;
import com.set.utils.checkNullException;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class ExamStudentController {
	@Autowired
	private ExamStudentService examStudentService;
	@Autowired
	private ExamScheduleService examScheduleService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("ExamStudentController.class");

@PostMapping(value="/SubmitTest", headers="Accept=Application/json")
public @ResponseBody LuMessage submitStudentExam(
		@RequestBody ExamStudent examStudent,
		@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	LuMessage message = new LuMessage();
	ExamStudent tempExamStudent = new ExamStudent();
	if (tokenService.tokenValidate(token)) {
		message.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {	
		Exam e[] = examStudent.getExam();
		int i;
		int total=0;
		total=examStudentService.totalExamStudent(e[0].getExamId());
	    for(i = 0;i<=e.length-1;i++){
	    	String examId = e[i].getExamId();
	    	String studentId = e[i].getStudentId();
	    	String questionNo = e[i].getQuestionNo();
	    	float answerPageNo = e[i].getAnswerPageNo();
	    	String answer = e[i].getAnswer();
	    	String base64Image = e[i].getAnswerPath();
            String pathFile = "";
            if(base64Image!=null && !base64Image.isEmpty()){
    	    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
    	    	String fileName = "studentAnswerImage"+System.currentTimeMillis();
    	    	String extension = "png";
    	    	pathFile = Constant.EXAM_UPLOAD_DIRECTORY+fileName+"."+extension; 
    	    	ImageUpload.decoder(base64Image, pathFile);
            }
	    	ExamStudentId newExamStudentId = new ExamStudentId(examId,studentId,answerPageNo,questionNo);
	    	tempExamStudent.setAnswer(answer);
	    	tempExamStudent.setAnswerPath(pathFile);
	    	tempExamStudent.setStartTime(examStudent.getStartTime());
	    	tempExamStudent.setEndTime(examStudent.getEndTime());
	    	tempExamStudent.setIsSoftDelete(0);
	    	//examStudentService.deleteExamStudent(newExamStudentId);
	    	tempExamStudent.setExamStudentId(newExamStudentId);
	    	examStudentService.save(tempExamStudent);
	    }
		message.setMessage(i+" out of "+total+" Submitted Successfully.");
	}
	return message;
}

@PostMapping(value = "/GetReviewTestListing", headers = "Accept=application/json")
public @ResponseBody StudentAnswerTypeDetailDto GetReviewTestListing(@RequestBody StudentExamId studentExamId, @RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> studentAnswerDetailList = null;
	List<Object[]> studentAnswerTypeDetailList = null;
	List<Object[]> examTotalMark = null;
	List<StudentAnswerDetailsDto> studentAnswerDetailsDtoList = new ArrayList<>();
	StudentAnswerTypeDetailDto studentAnswerTypeDetailDto = new StudentAnswerTypeDetailDto();
	StudentAnswerDetailsDto studentAnswerDetailsDto;
	StudentAnswerDto studentAnswerDto;
	
	if (tokenService.tokenValidate(token)) {
		studentAnswerTypeDetailDto.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		String examid = studentExamId.getExamId();
		String studentid = studentExamId.getStudentId();
		studentAnswerTypeDetailList = examStudentService.getAllQuestionTypeList(examid);
		char alphabet = 'A';
		for(Object[] eachStudentAnswerTypeList : studentAnswerTypeDetailList) {
			List<StudentAnswerDto> studentAnswerDtoList = new ArrayList<>();
			studentAnswerDetailsDto = new StudentAnswerDetailsDto();
			String studentAnswerTypeId = eachStudentAnswerTypeList[0].toString();
			studentAnswerDetailsDto.setStudentAnswerTypeId(studentAnswerTypeId);
			studentAnswerDetailsDto.setStudentAnswerTypeName(checkNullException.firstNonNull(eachStudentAnswerTypeList[1]).toString());
			int NoOfStudentAnswers = eachStudentAnswerTypeList[2].hashCode();
			int mark = eachStudentAnswerTypeList[3].hashCode();
			int totalMark = NoOfStudentAnswers*mark; 
			studentAnswerDetailsDto.setMarks(NoOfStudentAnswers+" x "+ mark +" = "+totalMark);
			studentAnswerDetailsDto.setPartName("PART - "+alphabet);
			studentAnswerDetailList = examStudentService.getAllQuestionList(examid,studentAnswerTypeId,studentid);
			for(Object[] eachStudentAnswerList : studentAnswerDetailList) {
				studentAnswerDto = new StudentAnswerDto();
				String studentAnswerType = eachStudentAnswerList[3].toString(); 
                studentAnswerDto.setTestId(checkNullException.firstNonNull(eachStudentAnswerList[0]).toString());
                studentAnswerDto.setQuestionId(checkNullException.firstNonNull(eachStudentAnswerList[1]).toString());
                studentAnswerDto.setQuestionNumber(eachStudentAnswerList[2].toString());
                studentAnswerDto.setQuestionType(studentAnswerType);
                studentAnswerDto.setLeftSideOptiona(checkNullException.firstNonNull(eachStudentAnswerList[10]).toString());
                studentAnswerDto.setLeftSideOptionb(checkNullException.firstNonNull(eachStudentAnswerList[11]).toString());
                studentAnswerDto.setLeftSideOptionc(checkNullException.firstNonNull(eachStudentAnswerList[12]).toString());
                studentAnswerDto.setLeftSideOptiond(checkNullException.firstNonNull(eachStudentAnswerList[13]).toString());
                studentAnswerDto.setLeftSideOptione(checkNullException.firstNonNull(eachStudentAnswerList[14]).toString());
                studentAnswerDto.setRightSideOption1(checkNullException.firstNonNull(eachStudentAnswerList[15]).toString());
                studentAnswerDto.setRightSideOption2(checkNullException.firstNonNull(eachStudentAnswerList[16]).toString());
                studentAnswerDto.setRightSideOption3(checkNullException.firstNonNull(eachStudentAnswerList[17]).toString());
                studentAnswerDto.setRightSideOption4(checkNullException.firstNonNull(eachStudentAnswerList[18]).toString());
                studentAnswerDto.setRightSideOption5(checkNullException.firstNonNull(eachStudentAnswerList[19]).toString());                	
                studentAnswerDto.setQuestion(checkNullException.firstNonNull(eachStudentAnswerList[4]).toString());
                studentAnswerDto.setQuestionPath(checkNullException.firstNonNull(eachStudentAnswerList[5]).toString());
                studentAnswerDto.setOptiona(checkNullException.firstNonNull(eachStudentAnswerList[6]).toString());
                studentAnswerDto.setOptionb(checkNullException.firstNonNull(eachStudentAnswerList[7]).toString());
                studentAnswerDto.setOptionc(checkNullException.firstNonNull(eachStudentAnswerList[8]).toString());
                studentAnswerDto.setOptiond(checkNullException.firstNonNull(eachStudentAnswerList[9]).toString());                	
                studentAnswerDto.setAnswer(checkNullException.firstNonNull(eachStudentAnswerList[22]).toString());
                studentAnswerDto.setAnswerPath(checkNullException.firstNonNull(eachStudentAnswerList[23]).toString());
                studentAnswerDto.setStudentId(checkNullException.firstNonNull(eachStudentAnswerList[21]).toString());
                studentAnswerDto.setAnswerPageNo(eachStudentAnswerList[20].toString());
                studentAnswerDto.setObtainedMarks(eachStudentAnswerList[26].toString());
                studentAnswerDto.setStartTime(eachStudentAnswerList[24].toString());
                studentAnswerDto.setEndTime(eachStudentAnswerList[25].toString());
                studentAnswerDtoList.add(studentAnswerDto);
			}
			studentAnswerDetailsDto.setStudentAnswerList(studentAnswerDtoList);
			studentAnswerDetailsDtoList.add(studentAnswerDetailsDto);
			alphabet++;
			}
		examTotalMark = examScheduleService.getAllExamList(examid);		
		studentAnswerTypeDetailDto.setStudentAnswerDetailsDtoList(studentAnswerDetailsDtoList);
		studentAnswerTypeDetailDto.setCount(studentAnswerDetailsDtoList.size());
		for(Object[] eachExamList : examTotalMark) {
		studentAnswerTypeDetailDto.setTotalMarks(eachExamList[11].toString());
		}		
		studentAnswerTypeDetailDto.setMessage("list of StudentAnswers");
	}
	return studentAnswerTypeDetailDto;
}

@PostMapping(value="/EvaluateTest", headers="Accept=Application/json")
public @ResponseBody LuMessage EvaluateTest(
		@RequestBody ExamStudent examStudent,
		@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	LuMessage message = new LuMessage();
	ExamStudent tempExamStudent = new ExamStudent();
	if (tokenService.tokenValidate(token)) {
		message.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {	
		Exam e[] = examStudent.getExam();
		int i;
		int total=0;
		total=examStudentService.totalExamStudent(e[0].getExamId());
	    for(i = 0;i<=e.length-1;i++){
	    	String examId = e[i].getExamId();
	    	String studentId = e[i].getStudentId();
	    	String questionNo = e[i].getQuestionNo();
	    	float answerPageNo = e[i].getAnswerPageNo();
	    	float markObtained = e[i].getMarksObtained();
	    	String teacherId = e[i].getTeacherId();
	    	String answer = e[i].getAnswer();
	    	String base64Image = e[i].getAnswerPath();
            String pathFile = "";
            if(base64Image!=null && !base64Image.isEmpty()){
    	    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
    	    	String fileName = "studentAnswerImage"+System.currentTimeMillis();
    	    	String extension = "png";
    	    	pathFile = Constant.EXAM_UPLOAD_DIRECTORY+fileName+"."+extension; 
    	    	ImageUpload.decoder(base64Image, pathFile);
            }
	    	ExamStudentId newExamStudentId = new ExamStudentId(examId,studentId,answerPageNo,questionNo);
	    	tempExamStudent.setAnswer(answer);
	    	tempExamStudent.setAnswerPath(pathFile);
	    	tempExamStudent.setStartTime(examStudent.getStartTime());
	    	tempExamStudent.setEndTime(examStudent.getEndTime());
	    	tempExamStudent.setMarksObtained(markObtained);
//TODO	    	tempExamStudent.setTeacherId(teacherId);
	    	tempExamStudent.setIsSoftDelete(0);
	    	//examStudentService.deleteExamStudent(newExamStudentId);
	    	tempExamStudent.setExamStudentId(newExamStudentId);
	    	examStudentService.updateExamStudent(tempExamStudent);
	    }
		message.setMessage("Reviewed Successfully.");
	}
	return message;
}
@SuppressWarnings("null")
@GetMapping(value = "/getExamStudentById/{id}/{sid}/{pNo}/{qNo}", headers = "Accept=application/json")
public @ResponseBody ExamStudent getExamStudentById(@PathVariable("id") String Id, @PathVariable("sid") String sId, @PathVariable("pNo") int pNo, @PathVariable("qNo") String qNo,@RequestHeader String token) {
	ExamStudent examStudent = null;
	Claims claims = TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		examStudent.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {
		ExamStudentId newExamStudentId = new ExamStudentId(Id,sId,pNo,qNo);
		examStudent=examStudentService.getExamStudentById(newExamStudentId);
	}
	return examStudent;
}

@GetMapping(value = "/getAllExamStudent/{id}/{searchdata}", headers = "Accept=application/json")
public @ResponseBody ExamStudentDetails getAllExamStudent(@PathVariable("id") int id,@PathVariable("searchdata") String searchdata,@RequestHeader String token) {
//	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
//	String decodedString = new String(decodedBytes);
	Claims claims=TokenUtils.verifyToken(token);
	ExamStudentDetails examStudentDetails=new ExamStudentDetails();
	
	if (tokenService.tokenValidate(token)) {
		examStudentDetails.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		examStudentDetails=examStudentService.getAllExamStudent(id, searchdata);
	}
	return examStudentDetails;
}


@GetMapping(value = "/getAllExamStudentSelect", headers = "Accept=application/json")
public @ResponseBody List<ExamStudent> getAllExamStudentSelect(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	List<ExamStudent> examStudent=new ArrayList<>();
	
	if (tokenService.tokenValidate(token)) {
		ExamStudent csm = new ExamStudent();
		csm.setMessage("Invalid token");
		examStudent.add(csm);
		logger.info("Invalid token");
	} else {
		examStudent=examStudentService.getAllExamStudentSelect();
	}
	return examStudent;
}

//@PostMapping(value = "/TestSubmitStudentList", headers = "Accept=application/json")
//public @ResponseBody SubmitStudentDetailsDto getStudentTestListing(
//		@RequestBody ExamId examId, 
//		@RequestHeader String token) throws ParseException {
//	Claims claims = TokenUtils.verifyToken(token);
//	List<Object[]> submitStudentList = null;
//	List<SubmitStudentDto> submitStudentDtoList = new ArrayList<>();
//	SubmitStudentDetailsDto submitStudentDetailsDto = new SubmitStudentDetailsDto();
//	SubmitStudentDto submitStudentDto;
//	
//	if (tokenService.tokenValidate(token)) {
//		submitStudentDetailsDto.setMessage("Invalid token");
//		logger.info("Invalid token");
//	} else {
//		String examid = examId.getExamId();
//		submitStudentList = examStudentService.getAllSubmitStudentList(examid);
//		for(Object[] eachStudentList : submitStudentList) {
//			submitStudentDto = new SubmitStudentDto();
//			submitStudentDto.setStudentId(checkNullException.firstNonNull(eachStudentList[0]).toString());
//			submitStudentDto.setStudentName(checkNullException.firstNonNull(eachStudentList[1]).toString()+" "+eachStudentList[2].toString());
//			submitStudentDtoList.add(submitStudentDto);
//			}
//		submitStudentDetailsDto.setSubmitStudentDtoList(submitStudentDtoList);
//		submitStudentDetailsDto.setCount(submitStudentDtoList.size());
//		submitStudentDetailsDto.setMessage("Student Exam List");
//	}	
//	return submitStudentDetailsDto;
//}

@PostMapping(value = "/TestSubmitStudentList", headers = "Accept=application/json")
public @ResponseBody SubmitStudentDetailsDto getStudentTestListing(
		@RequestBody ExamId examId, 
		@RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> submitStudentList = null;
	List<Object[]> absentStudentList = null;
	List<SubmitStudentDto> submitStudentDtoList = new ArrayList<>();
	List<AbsentStudentDto> absentStudentDtoList = new ArrayList<>();
	SubmitStudentDetailsDto submitStudentDetailsDto = new SubmitStudentDetailsDto();
	SubmitStudentDto submitStudentDto;
	AbsentStudentDto absentStudentDto;
	if (tokenService.tokenValidate(token)) {
		submitStudentDetailsDto.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		String examid = examId.getExamId();
		submitStudentList = examStudentService.getAllSubmitStudentList(examid);
		for(Object[] eachStudentList : submitStudentList) {
			submitStudentDto = new SubmitStudentDto();
			submitStudentDto.setStudentId(checkNullException.firstNonNull(eachStudentList[0]).toString());
			submitStudentDto.setStudentName(checkNullException.firstNonNull(eachStudentList[1]).toString()+" "+eachStudentList[2].toString());
			submitStudentDto.setStudentEmail(checkNullException.firstNonNull(eachStudentList[3]).toString());
			submitStudentDtoList.add(submitStudentDto);
			}
		absentStudentList = examStudentService.getAllAbsentStudentList(examid);
		for(Object[] eachAbsentStudentList : absentStudentList) {
			absentStudentDto = new AbsentStudentDto();
			absentStudentDto.setStudentId(checkNullException.firstNonNull(eachAbsentStudentList[0]).toString());
			absentStudentDto.setStudentName(checkNullException.firstNonNull(eachAbsentStudentList[1]).toString()+" "+eachAbsentStudentList[2].toString());
			absentStudentDto.setStudentEmail(checkNullException.firstNonNull(eachAbsentStudentList[3]).toString());
			absentStudentDtoList.add(absentStudentDto);
			}
		submitStudentDetailsDto.setSubmitStudentsCount(submitStudentDtoList.size());
		submitStudentDetailsDto.setAbsentStudentsCount(absentStudentDtoList.size());
		submitStudentDetailsDto.setSubmitStudentDtoList(submitStudentDtoList);
		submitStudentDetailsDto.setAbsentStudentList(absentStudentDtoList);
		submitStudentDetailsDto.setCount(submitStudentDtoList.size());
		submitStudentDetailsDto.setMessage("Student Exam List");
	}	
	return submitStudentDetailsDto;
}

@PostMapping(value = "/StudentReport", headers = "Accept=application/json")
public @ResponseBody ReportStudentDetailsDto getStudentReport(
		@RequestBody Student studentId, 
		@RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> reportStudentDetailsList = null;
	List<Object[]> reportMarksDetailsList = null;
	List<Object[]> reportAbsentDetailsList = null;
	List<ReportMarksDto> reportMarksDtoList = new ArrayList<>();
	ReportStudentDetailsDto reportStudentDetailsDto = new ReportStudentDetailsDto();
	ReportMarksDto reportMarksDto;
	if (tokenService.tokenValidate(token)) {
		reportStudentDetailsDto.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		String studentid = studentId.getStudentId();
		reportMarksDetailsList = examStudentService.getAllReportMarksList(studentid);
		for(Object[] eachMarksList : reportMarksDetailsList) {
			reportMarksDto = new ReportMarksDto();
			reportMarksDto.setExamId(checkNullException.firstNonNull(eachMarksList[4]).toString());
			reportMarksDto.setSubjectId(checkNullException.firstNonNull(eachMarksList[0]).toString());
			reportMarksDto.setSubjectName(checkNullException.firstNonNull(eachMarksList[1]).toString());
			reportMarksDto.setTotalMarks(eachMarksList[2].toString());
			reportMarksDto.setMarksObtained(eachMarksList[3].toString());
			reportMarksDtoList.add(reportMarksDto);
			}
		reportAbsentDetailsList = examStudentService.getAllReportAbsentList(studentid);
		for(Object[] eachAbsentList : reportAbsentDetailsList) {
			reportMarksDto = new ReportMarksDto();
			reportMarksDto.setExamId(checkNullException.firstNonNull(eachAbsentList[3]).toString());
			reportMarksDto.setSubjectId(checkNullException.firstNonNull(eachAbsentList[0]).toString());
			reportMarksDto.setSubjectName(checkNullException.firstNonNull(eachAbsentList[1]).toString());
			reportMarksDto.setTotalMarks(eachAbsentList[2].toString());
			reportMarksDto.setMarksObtained(Constant.EXAM_ABSENT);
			reportMarksDtoList.add(reportMarksDto);
			}
		reportStudentDetailsList = examStudentService.getAllReportStudentList(studentid);
		for(Object[] eachStudentList : reportStudentDetailsList) {
			reportStudentDetailsDto = new ReportStudentDetailsDto();
			reportStudentDetailsDto.setClassId(checkNullException.firstNonNull(eachStudentList[4]).toString());
			reportStudentDetailsDto.setSectionId(checkNullException.firstNonNull(eachStudentList[5]).toString());
			reportStudentDetailsDto.setClassName(checkNullException.firstNonNull(eachStudentList[6]).toString());
			reportStudentDetailsDto.setSectionName(checkNullException.firstNonNull(eachStudentList[7]).toString());
			reportStudentDetailsDto.setStudentId(checkNullException.firstNonNull(eachStudentList[0]).toString());
			reportStudentDetailsDto.setStudentName(checkNullException.firstNonNull(eachStudentList[1]).toString()+" "+eachStudentList[2].toString());
			reportStudentDetailsDto.setStudentEmail(checkNullException.firstNonNull(eachStudentList[3]).toString());
		}
		reportStudentDetailsDto.setReportMarksList(reportMarksDtoList);
		reportStudentDetailsDto.setCount(reportMarksDtoList.size());
		reportStudentDetailsDto.setMessage("Student Exam Report List");
	}	
	return reportStudentDetailsDto;
}

}
