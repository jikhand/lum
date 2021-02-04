package com.set.controller;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
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

import com.set.dto.ExamDetailsDto;
import com.set.dto.ExamTypeDetailsDto;
import com.set.dto.ExamTypeDto;
import com.set.dto.StudentExamDetailListDto;
import com.set.dto.StudentExamDetailsDto;
import com.set.dto.TeacherExamDetailListDto;
import com.set.dto.TeacherExamDetailsDto;
import com.set.dto.ExamDetailQuestionListDto;
import com.set.controller.helper.ExamControllerHelper;
import com.set.dto.ExamDetailListDto;
import com.set.model.AssignmentEvaluationDetails;
import com.set.model.ClassSection;
import com.set.model.ClassSectionMaster;
import com.set.model.ExamDetail;
import com.set.model.ExamId;
import com.set.model.ExamQuestions;
import com.set.model.ExamQuestionsId;
import com.set.model.ExamSchedule;
import com.set.model.ExamScheduleDetails;
import com.set.model.ExamType;
import com.set.model.ExamTypeDetails;
import com.set.model.LuMessage;
import com.set.model.Teacher;
import com.set.model.User;
import com.set.service.ExamQuestionsService;
import com.set.service.ExamScheduleService;
import com.set.service.ExamTypeService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class ExamScheduleController {
	@Autowired
	private ExamScheduleService examScheduleService;
	@Autowired
	private ExamQuestionsService examQuestionsService;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("ExamScheduleController.class");

	
@PostMapping(value="/AddTestDetails", headers="Accept=Application/json")
public @ResponseBody LuMessage addExamSchedule(
		@RequestBody ExamSchedule examSchedule,
		@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	LuMessage message = new LuMessage();
	ExamSchedule tempExamSchedule = new ExamSchedule();
	ExamQuestions tempExamQuestions = new ExamQuestions();	
	
	if (tokenService.tokenValidate(token)) {
		message.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {	
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tempExamSchedule.setClassId(examSchedule.getClassId());
        tempExamSchedule.setDuration(examSchedule.getDuration());
        tempExamSchedule.setExamDate(examSchedule.getExamDate());
        tempExamSchedule.setExamDescription(examSchedule.getExamDescription());
        tempExamSchedule.setExamStartTime(examSchedule.getExamStartTime());
        tempExamSchedule.setSectionId(examSchedule.getSectionId());
        tempExamSchedule.setSubjectId(examSchedule.getSubjectId());
        tempExamSchedule.setTeacherId(examSchedule.getTeacherId());
        tempExamSchedule.setMaxMarks(examSchedule.getMaxMarks());
        String examType = examSchedule.getExamType();  
        tempExamSchedule.setExamType(examType);
	    tempExamSchedule.setIsSoftDelete(false);
        User userdata = userService.retrieveFromId(claims.getSubject());
		String examsId = examSchedule.getExamId();
	    Boolean eType = examScheduleService.IsExist(userdata.getUserId(),examType);
	    if(eType==true) {
	            if((examsId.isEmpty())||(examsId.equalsIgnoreCase("null"))) {
	    		    String examId = CommonUtils.generatePrimaryKeyId("lutbl_schedule_exam");
	                tempExamSchedule.setExamId(examId);
	        		ExamDetail e[] = examSchedule.getExamDetail();
	        	    for(int i = 0;i<=e.length-1;i++){
	        	    	int noOfQuestions = e[i].getNoQuestion();
	                    String questionType = e[i].getQuestionType();
	                    ExamQuestionsId examQuestionsId = new ExamQuestionsId(examId,questionType);
	                    tempExamQuestions.setExamQuestionsId(examQuestionsId);
                        tempExamQuestions.setNoOfQuestions(noOfQuestions);
	                    examQuestionsService.save(tempExamQuestions);
	        	    }	                
	                tempExamSchedule.setInsertedBy(userdata.getUserId());
	    		    tempExamSchedule.setInsertedTime(timestamp);
	    		    examScheduleService.save(tempExamSchedule);
	    		    message.setMessage("Inserted successfully");
	            }else {
	            	tempExamSchedule.setUpdatedTime(timestamp);
	            	tempExamSchedule.setUpdatedBy(userdata.getUserId());
	            	tempExamSchedule.setIsSoftDelete(false);
	            	tempExamSchedule.setExamId(examsId);	
	        		ExamDetail e[] = examSchedule.getExamDetail();
	        	    for(int i = 0;i<=e.length-1;i++){
	        	    	int noOfQuestions = e[i].getNoQuestion();
	                    String questionType = e[i].getQuestionType();
                        tempExamQuestions.setNoOfQuestions(noOfQuestions);
                        ExamQuestionsId examQuestionsId = new ExamQuestionsId(examsId,questionType);
	                    tempExamQuestions.setExamQuestionsId(examQuestionsId);
                        tempExamQuestions.setNoOfQuestions(noOfQuestions);
                        examQuestionsService.deleteExamQuestions(examQuestionsId);
	                    examQuestionsService.save(tempExamQuestions);
	        	    }	
	    		    examScheduleService.updateExamSchedule(tempExamSchedule);
	    		    message.setMessage("Updated successfully");        	
	            }	        	
	    }else {
		    message.setMessage("You don't have access to create exams for externals");        	
		}
	}
	return message;
}

@PutMapping(value="/editExamSchedule/{id}",headers="Accept=Application/json")
public @ResponseBody ExamSchedule editExamSchedule(@PathVariable("id") String Id, @RequestBody ExamSchedule examSchedule,@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		examSchedule.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		examSchedule.setUpdatedTime(timestamp);
		examSchedule.setIsSoftDelete(false);
		examSchedule.setExamId(Id);		
		examScheduleService.updateExamSchedule(examSchedule);
		examSchedule.setMessage("Updated successfully");
	}
	return examSchedule;
}


@PutMapping(value="/deleteExamSchedule/{id}",headers="Accept=Application/json")
public @ResponseBody ExamSchedule deleteExamSchedule(@PathVariable("id") String Id, @RequestBody ExamSchedule examSchedule,@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		examSchedule.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		examSchedule.setUpdatedTime(timestamp);
		examSchedule.setIsSoftDelete(true);
		examSchedule.setExamId(Id);		
		examScheduleService.deleteExamSchedule(examSchedule);
		examSchedule.setMessage("Deleted successfully");
	}
	return examSchedule;
}


@SuppressWarnings("null")
@GetMapping(value = "/getExamScheduleById/{id}", headers = "Accept=application/json")
public @ResponseBody ExamSchedule getExamScheduleById(@PathVariable("id") String Id,@RequestHeader String token) {
	ExamSchedule examSchedule = null;
	Claims claims = TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		examSchedule.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {
		examSchedule=examScheduleService.getExamScheduleById(Id);
	}
	return examSchedule;
}

@GetMapping(value = "/getTotalExamSchedule", headers = "Accept=application/json")
public @ResponseBody int getTotalExamSchedule(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	int i=0;
	if (tokenService.tokenValidate(token)) {		
		logger.info("Invalid token");
	} else {
		logger.info("list of examSchedule");
		i=examScheduleService.totalExamSchedule();
	}
	return i;
}
@GetMapping(value = "/getAllExamSchedule/{id}/{searchdata}", headers = "Accept=application/json")
public @ResponseBody ExamScheduleDetails getAllExamSchedule(@PathVariable("id") int id,@PathVariable("searchdata") String searchdata,@RequestHeader String token) {
	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
	String decodedString = new String(decodedBytes);
	System.out.println("ExamDescription + ExamDescription");
	System.out.println("SubjectId + SubjectId");
	ExamScheduleDetails examScheduleDetails=new ExamScheduleDetails();
	examScheduleDetails=examScheduleService.getAllExamSchedule(id, decodedString);
	return examScheduleDetails;
}
	//byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
	//String decodedString = new String(decodedBytes);
//	Claims claims=TokenUtils.verifyToken(token);
//	ExamScheduleDetails examScheduleDetails=new ExamScheduleDetails();
	
//	if (tokenService.tokenValidate(token)) {
//		examScheduleDetails.setMessage("Invalid token");
//		logger.info("Invalid token");
//	} else {
//		examScheduleDetails=examScheduleService.getAllExamSchedule(id, decodedString);
//	}
//	return examScheduleDetails;
// }


@GetMapping(value = "/getAllExamScheduleSelect", headers = "Accept=application/json")
public @ResponseBody List<ExamSchedule> getAllExamScheduleSelect(@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	List<ExamSchedule> examSchedule=new ArrayList<>();
	
	if (tokenService.tokenValidate(token)) {
		ExamSchedule csm = new ExamSchedule();
		csm.setMessage("Invalid token");
		examSchedule.add(csm);
		logger.info("Invalid token");
	} else {
		examSchedule=examScheduleService.getAllExamScheduleSelect();
	}
	return examSchedule;
}


@PostMapping(value = "/GetTestListing", headers = "Accept=application/json")
public @ResponseBody StudentExamDetailsDto getStudentTestListing(
		@RequestBody ClassSection classSection, 
		@RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> studentExamDetailList = null;
	List<StudentExamDetailListDto> studentExamDetailListDtoList = new ArrayList<>();
	StudentExamDetailsDto studentExamDetailsDto = new StudentExamDetailsDto();
	StudentExamDetailListDto studentExamDetailListDto;
	
	if (tokenService.tokenValidate(token)) {
		studentExamDetailsDto.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		String classId = classSection.getClassId();
		String sectionId = classSection.getSectionId();
		studentExamDetailList = examScheduleService.getAllStudentExamList(classId, sectionId);
		for(Object[] eachExamList : studentExamDetailList) {
			studentExamDetailListDto = new StudentExamDetailListDto();
			studentExamDetailListDto.setExamId(eachExamList[1].toString());
			studentExamDetailListDto.setClassId(eachExamList[2].toString());
			studentExamDetailListDto.setSectionId(eachExamList[8].toString());
			studentExamDetailListDto.setSubjectId(eachExamList[0].toString());
			studentExamDetailListDto.setTeacherId(eachExamList[9].toString());
			studentExamDetailListDto.setSubjectName(eachExamList[10].toString());
			studentExamDetailListDto.setExamDescription(eachExamList[5].toString());
			studentExamDetailListDto.setExamType(eachExamList[7].toString());
			studentExamDetailListDto.setTestDate(eachExamList[4].toString());
			String startTime = eachExamList[6].toString();
			studentExamDetailListDto.setTestStartTime(startTime);
			studentExamDetailListDto.setMaxMarks(eachExamList[11].toString());
			int duration = eachExamList[3].hashCode();
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			java.util.Date d = df.parse(startTime); 
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(d);
			cal.add(Calendar.MINUTE, duration);
			String endTime = df.format(cal.getTime());
			studentExamDetailListDto.setTestDuration(duration);
			studentExamDetailListDto.setTestEndTime(endTime);
			studentExamDetailListDtoList.add(studentExamDetailListDto);
			}
		studentExamDetailsDto.setStudentExamDetailList(studentExamDetailListDtoList);
		studentExamDetailsDto.setCount(studentExamDetailListDtoList.size());
		studentExamDetailsDto.setMessage("Student Exam List");
	}
	return studentExamDetailsDto;
}


@PostMapping(value = "/TeacherTestListDetails", headers = "Accept=application/json")
public @ResponseBody TeacherExamDetailsDto getTeacherTestListDetails(
		@RequestBody Teacher teacher, 
		@RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> teacherExamDetailList = null;
	List<TeacherExamDetailListDto> teacherExamDetailListDtoList = new ArrayList<>();
	TeacherExamDetailsDto teacherExamDetailsDto = new TeacherExamDetailsDto();
	TeacherExamDetailListDto teacherExamDetailListDto;
	
	if (tokenService.tokenValidate(token)) {
		teacherExamDetailsDto.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		String teacherId = teacher.getTeacherId();
		teacherExamDetailList = examScheduleService.getAllTeacherExamList(teacherId);
		for(Object[] eachExamList : teacherExamDetailList) {
			teacherExamDetailListDto = new TeacherExamDetailListDto();
			teacherExamDetailListDto.setExamId(eachExamList[1].toString());
			teacherExamDetailListDto.setClassId(eachExamList[2].toString());
			teacherExamDetailListDto.setSectionId(eachExamList[8].toString());
			teacherExamDetailListDto.setSubjectId(eachExamList[0].toString());
			teacherExamDetailListDto.setTeacherId(eachExamList[9].toString());
			teacherExamDetailListDto.setSubjectName(eachExamList[10].toString());
			teacherExamDetailListDto.setExamDescription(eachExamList[5].toString());
			teacherExamDetailListDto.setExamType(eachExamList[7].toString());
			teacherExamDetailListDto.setTestDate(eachExamList[4].toString());
			String startTime = eachExamList[6].toString();
			teacherExamDetailListDto.setTestStartTime(startTime);
			teacherExamDetailListDto.setTotalMarks(eachExamList[11].toString());
			int duration = eachExamList[3].hashCode();
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			java.util.Date d = df.parse(startTime); 
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(d);
			cal.add(Calendar.MINUTE, duration);
			String endTime = df.format(cal.getTime());
			teacherExamDetailListDto.setTestDuration(duration);
			teacherExamDetailListDto.setTestEndTime(endTime);
			teacherExamDetailListDtoList.add(teacherExamDetailListDto);
			}
		teacherExamDetailsDto.setTeacherExamDetailList(teacherExamDetailListDtoList);
		teacherExamDetailsDto.setCount(teacherExamDetailListDtoList.size());
		teacherExamDetailsDto.setMessage("Teacher Exam List");
	}
	return teacherExamDetailsDto;
}


@PostMapping(value = "/ViewTestDetails", headers = "Accept=application/json")
public @ResponseBody ExamDetailsDto getViewTestDetails(@RequestBody ExamId examId, @RequestHeader String token) throws ParseException {
	Claims claims = TokenUtils.verifyToken(token);
	List<Object[]> examDetailQuestionList = null;
	List<Object[]> examDetailList = null;
	List<ExamDetailListDto> examDetailListDtoList = new ArrayList<>();
	ExamDetailsDto examDetailsDto = new ExamDetailsDto();
	ExamDetailQuestionListDto examDetailQuestionListDto;
	ExamDetailListDto examDetailListDto;
	
	if (tokenService.tokenValidate(token)) {
		examDetailsDto.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		String examid = examId.getExamId();
		examDetailList = examScheduleService.getAllExamList(examid);
		for(Object[] eachExamList : examDetailList) {
			List<ExamDetailQuestionListDto> examDetailQuestionListDtoList = new ArrayList<>();
			examDetailListDto = new ExamDetailListDto();
			examDetailListDto.setExamId(eachExamList[1].toString());
			examDetailListDto.setClassId(eachExamList[2].toString());
			examDetailListDto.setSectionId(eachExamList[8].toString());
			examDetailListDto.setSubjectId(eachExamList[0].toString());
			examDetailListDto.setTeacherId(eachExamList[9].toString());
			examDetailListDto.setSubjectName(eachExamList[10].toString());
			examDetailListDto.setExamDescription(eachExamList[5].toString());
			examDetailListDto.setExamType(eachExamList[7].toString());
			examDetailListDto.setTestDate(eachExamList[4].toString());
			String startTime = eachExamList[6].toString();
			examDetailListDto.setTestStartTime(startTime);
			examDetailListDto.setMaxMarks(eachExamList[11].toString());
			int duration = eachExamList[3].hashCode();
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			java.util.Date d = df.parse(startTime); 
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(d);
			cal.add(Calendar.MINUTE, duration);
			String endTime = df.format(cal.getTime());
			examDetailListDto.setTestDuration(duration);
			examDetailListDto.setTestEndTime(endTime);
			examDetailQuestionList = examScheduleService.getAllExamQuestionList(examid);
			for(Object[] eachExamQuestionList : examDetailQuestionList) {
				examDetailQuestionListDto = new ExamDetailQuestionListDto();
				examDetailQuestionListDto.setNoOfQuestions(eachExamQuestionList[1].toString());
				examDetailQuestionListDto.setQuestionTypeId(eachExamQuestionList[0].toString());
				examDetailQuestionListDtoList.add(examDetailQuestionListDto);
			}
			examDetailListDto.setExamDetailQuestionList(examDetailQuestionListDtoList);
			examDetailListDtoList.add(examDetailListDto);
			}
		examDetailsDto.setExamDetailList(examDetailListDtoList);
		examDetailsDto.setCount(examDetailListDtoList.size());
		examDetailsDto.setMessage("Test Details");
	}
	return examDetailsDto;
}


}
