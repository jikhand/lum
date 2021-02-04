package com.set.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.dto.TimeTableDayDto;
import com.set.dto.TimeTableDaysDto;
import com.set.dto.TimeTableDetailsDto;
import com.set.dto.TimeTableDetailsDtoSearch;
import com.set.dto.TimeTableDto;
import com.set.dto.TimeTableEditParamaters;
import com.set.dto.TimeTableListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSubject;
import com.set.model.Notification;
import com.set.model.StudentMaster;
import com.set.model.Teacher;
import com.set.model.TimeTable;
import com.set.model.TimeTableDetails;
import com.set.model.TimeTableId;
import com.set.model.TimeTableInput;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.model.WeekName;
import com.set.model.WorkHours;
import com.set.model.WorkHoursId;
import com.set.service.StudentMasterService;
import com.set.service.TimeTableService;
import com.set.service.TokenService;
import com.set.service.UserRegistrationService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import com.set.service.UserService;


@CrossOrigin
@RestController
public class TimeTableController {
	@Autowired
	private TimeTableService timeTableService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRegistrationService userRegistrationService;
	private Logger logger = Logger.getLogger("TimetableController.class");
	@Autowired
	private TokenService tokenService;
	@Autowired
	private StudentMasterService studentMasterService;
	

	// adding data to work hours table
	@PostMapping(value = "/AddWorkhours", headers = "Accept=Application/json")
	public @ResponseBody WorkHours addWorkhours(@RequestBody WorkHours workHours, @RequestHeader String token) {

		Claims claims = TokenUtils.verifyToken(token);
		if (claims == null) {
			workHours.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {

			WorkHoursId workHoursIdList[] = workHours.getWorkHoursIdList();

			for (int i = 0; i < workHoursIdList.length; i++) {

				String day = workHoursIdList[i].getDay();
				int noOfHours = workHoursIdList[i].getNoOfHours();

				Date startTime = workHours.getStartTime();
				Date endTime = workHours.getEndTime();
				float duration = workHours.getDuration();
				String workId = CommonUtils.generatePrimaryKeyId("lutbl_work_hours");

				WorkHoursId newWorkHoursId = new WorkHoursId(day, noOfHours);

				workHours.setStartTime(startTime);
				workHours.setEndTime(endTime);
			//	workHours.setWorkHoursId(newWorkHoursId);
				workHours.setDuration(duration);
				workHours.setWorkId(workId);

				timeTableService.save(workHours);
			}
			workHours.setMessage("Inserted successfully");
		}
		return workHours;
	}

	@GetMapping(value = "/getAllClassSectionSelect/{teacherId}/{subjectId}", headers = "Accept=application/json")
	public @ResponseBody List<Map<String, String>> getAllClassSectionSelect(
			@PathVariable("teacherId") String teacherId,
			@PathVariable("subjectId") String subjectId,
			@RequestHeader String token) {
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		Map<String, String> hm = new HashMap<String, String>();
		Claims claims = TokenUtils.verifyToken(token);
		if(!teacherId.equalsIgnoreCase("null") && !subjectId.equalsIgnoreCase("null")) {
		if (claims != null) {
			arrayList = timeTableService.getAllClassSectionSelect(teacherId,subjectId);
		} else {
			hm.put("Message", "invalid token");
			arrayList.add(hm);
		}
		}else {
			hm.put("Message", "invalid input");
			arrayList.add(hm);
		}
		return arrayList;
	}

	// Week Name
	@GetMapping(value = "/GetAllDaysOfWeekSelect", headers = "Accept=application/json")
	public @ResponseBody List<WeekName> getAllDaysOfWeekSelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<WeekName> weekName = null;
		if (claims != null) {
			weekName = timeTableService.getAllDaysOfWeekSelect();
		}
		return weekName;
	}

	// class sub
	@GetMapping(value = "/GetAllSubjectSelect/{teacherId}", headers = "Accept=application/json")
	public @ResponseBody List<Map<String, String>> getTmAllClassSubjectSelect(
			@PathVariable("teacherId") String teacherId, @RequestHeader String token) {
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		Claims claims = TokenUtils.verifyToken(token);
		if (claims != null) {
			arrayList = timeTableService.getAllClassSubjectSelect(teacherId);
		} else {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("Message", "invalid token");
			arrayList.add(hm);
		}
		return arrayList;
	}

	// emp select
	@GetMapping(value = "/GetAllUserRegSelect", headers = "Accept=application/json")
	public @ResponseBody List<UserRegistration> getAllUserRegSelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<UserRegistration> userRegistration = null;
		if (claims != null) {
			userRegistration = timeTableService.getAllUserRegSelect();
		}
		return userRegistration;
	}

	// work hrs select
	@GetMapping(value = "/GetAllWorkHoursSelect", headers = "Accept=application/json")
	public @ResponseBody List<WorkHours> getAllWorkHoursSelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<WorkHours> workHours = null;
		if (claims != null) {
			workHours = timeTableService.getAllWorkHoursSelect();
		}
		return workHours;
	}

	// insert to timetable
	@PostMapping(value = "/AddTimeTable", headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody TimeTable addTimeTable(
			@RequestPart(value = "timeTable", required = false) TimeTableId timeTableId,
			@RequestPart(value = "timeTable", required = false) WorkHours workHours,
			//@RequestPart(value = "timeTable", required = false) WorkHoursId workHoursId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		TimeTable timeTable = new TimeTable();
		if (claims == null) {
			timeTable.setMessage("Invalid token");
		}else {
//			String day = workHours.getWorkHoursId().getDay();
//			int noOfHours = workHours.getWorkHoursId().getNoOfHours();
			Date startTime = workHours.getStartTime();
			Date endTime = workHours.getEndTime();
			float duration = workHours.getDuration();
			String workId = CommonUtils.generatePrimaryKeyId("lutbl_work_hours");
			//WorkHoursId newWorkHoursId = new WorkHoursId(day, noOfHours);
			workHours.setStartTime(startTime);
			workHours.setEndTime(endTime);
			workHours.setDay(workHours.getDay());
			workHours.setNoOfHours(workHours.getNoOfHours());
			//workHours.setWorkHoursId(workHoursId);
			workHours.setDuration(duration);
			workHours.setWorkId(workId);
			
			timeTableService.save(workHours);

			// **********timeTable**********************************
			String[] arrOfStr = timeTableId.getClassId().split(",");
			String classId = arrOfStr[0];
			String sectionId = arrOfStr[1];

			TimeTableId newTimeTableId = new TimeTableId(classId, sectionId, workId, timeTableId.getSubjId(),timeTableId.getEmpId());
			timeTable.setScheduleId(CommonUtils.generatePrimaryKeyId("lutbl_timetable"));
			timeTable.setAcademicYear(Integer.parseInt(workHours.getMessage()));
			timeTable.setTimeTableId(newTimeTableId);
			timeTable.setIsDeleted(0);
			timeTableService.saveTimeTable(timeTable);
			timeTable.setMessage("Insert successfully");
		}
		return timeTable;
	}

	// update timetable
//	@PutMapping(value = "/EditTimeTable", headers = "Accept=Application/json")
//	public @ResponseBody TimeTable editTimeTable(@RequestBody TimeTable timeTable, @RequestHeader String token) {
//
//		Claims claims = TokenUtils.verifyToken(token);
//
//		if (claims == null) {
//			timeTable.setMessage("Invalid token");
//		}
//
//		else {
//			TimeTableId timetableList[] = timeTable.getTimetableList();
//
//			for (int i = 0; i < timetableList.length; i++) {
//
//				String classId = timetableList[i].getClassId();
//				String sectionId = timetableList[i].getSectionId();
//				String subId = timetableList[i].getSubjId();
//				String empId = timetableList[i].getEmpId();
//				String workId = timetableList[i].getWorkId();
//
//				TimeTableId newTimeTableId = new TimeTableId(classId, sectionId, workId, subId, empId);
//				timeTable.setScheduleId(timeTable.getScheduleId());
//				timeTable.setTimeTableId(newTimeTableId);
//				timeTableService.update(timeTable);
//
//			}
//			timeTable.setMessage("Update successfully");
//
//		}
//
//		return timeTable;
//	}
	@PutMapping(value = "/EditTimeTable", headers = { "content-type=multipart/mixed",
	"content-type=multipart/form-data" })
public @ResponseBody TimeTable editTimeTable(
	@RequestPart(value = "timeTable", required = false) TimeTableId timeTableId,
	@RequestPart(value = "timeTable", required = false) WorkHours workHours,
	//@RequestPart(value = "timeTable", required = false) WorkHoursId workHoursId,
	@RequestHeader String token) {

		Claims claims = TokenUtils.verifyToken(token);
		TimeTable timeTable = new TimeTable();
		if (claims == null) {
			timeTable.setMessage("Invalid token");
		}else {
			System.out.println("timeTableId: "+timeTableId);
			System.out.println("workHours: "+workHours);
			
			//String schId = timeTable.getScheduleId();// from postman
			String workId = workHours.getWorkId();
			
			//String classId = 
			String[] arrOfStr = timeTableId.getClassId().split(",");
			String classId = arrOfStr[0];
			String sectionId = arrOfStr[1];
			String subjId = timeTableId.getSubjId();
			String empId = timeTableId.getEmpId();
			System.out.println("workId*****"+workId);
			//timeTable = timeTableService.getTimeTableElementById(classId,sectionId,workId,subjId,empId);
			
//			System.out.println("work id:"+timeTable.getTimeTableId().getWorkId());
			
			//timeTable.setScheduleId(timeTable.getScheduleId());
			//String wkId = timeTable.getTimeTableId().getWorkId();
			
			workHours.setWorkId(workId);
			
			workHours.setStartTime(workHours.getStartTime());
			workHours.setEndTime(workHours.getStartTime());
			workHours.setNoOfHours(workHours.getNoOfHours());
			workHours.setDay(workHours.getDay());
			//workHours.setWorkHoursId(workHoursId);
			workHours.setDuration(workHours.getDuration());
			timeTableService.updateWorkHours(workHours);
			
			//workHours.setWorkId(workId);
//			err
//			timeTableService.updateWorkHours(workHours);
//			
//			String 
			TimeTableId newTimeTableId = new TimeTableId(classId, sectionId, workId, subjId, empId);
			//TimeTableId newTimeTableId = new TimeTableId(classId, sectionId, wkId, subId, empId);
			timeTable.setScheduleId(timeTable.getScheduleId());
			timeTable.setAcademicYear(Integer.parseInt(workHours.getMessage()));
			timeTable.setTimeTableId(newTimeTableId);		
			timeTableService.update(timeTable);
			
			
			
			
			//TimeTableId timetableList[] = timeTable.getTimetableList();

//			for (int i = 0; i < timetableList.length; i++) {
//
//				String classId = timetableList[i].getClassId();
//				String sectionId = timetableList[i].getSectionId();
//				String subId = timetableList[i].getSubjId();
//				String empId = timetableList[i].getEmpId();
//				String workId = timetableList[i].getWorkId();
//
//				TimeTableId newTimeTableId = new TimeTableId(classId, sectionId, workId, subId, empId);
//				timeTable.setScheduleId(timeTable.getScheduleId());
//				timeTable.setTimeTableId(newTimeTableId);
//				timeTableService.update(timeTable);
//
//			}
			timeTable.setMessage("Update successfully");

		}

		return timeTable;
	}

	// GetTimeTableByClassId
	@GetMapping(value = "/GetTimeTableByClassId/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody List<TimeTableDayDto> listTimeTableByClassId(@PathVariable("classId") String classId,
			@PathVariable("sectionId") String sectionId,
			@RequestHeader String token) {
		List<TimeTableDayDto> timeTableListDto = new ArrayList<>();
		Claims claims = TokenUtils.verifyToken(token);

		if (tokenService.tokenValidate(token)) {
			TimeTableDayDto tt = new TimeTableDayDto();
			tt.setMessage("Invalid Token");
			timeTableListDto.add(tt);
		} else {
			timeTableListDto = timeTableService.listTimeTableByClassId(classId,sectionId);
		}
		if (timeTableListDto.isEmpty()) {
			TimeTableDayDto tt = new TimeTableDayDto();
			tt.setCode("200");
			tt.setMessage("No Rows Found");
			tt.setDayId("");
			tt.setDayName("");
			tt.setTimeTableListData(null);
			timeTableListDto.add(tt);
		}
		return timeTableListDto;
	}

	// GetTodayTimeTableByClassId
	@PostMapping(value = "/GetTodayTimeTableByClassId", headers = "Accept=application/json")
	public @ResponseBody List<TimeTableDaysDto> listTodayTimeTableByClassId(@RequestBody TimeTableInput timeTableInput,
			@RequestHeader String token) {

		List<TimeTableDaysDto> timeTableListDto = new ArrayList<>();
		String day = timeTableInput.getDay();
		String classId = timeTableInput.getClassId();
		String sectionId = timeTableInput.getSectionId();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			TimeTableDaysDto tt = new TimeTableDaysDto();
			tt.setMessage("Invalid Token");
			timeTableListDto.add(tt);
		} else {
			timeTableListDto = timeTableService.listTodayTimeTableByClassId(day, classId, sectionId);
		}
		if (timeTableListDto.isEmpty()) {
			TimeTableDaysDto tt = new TimeTableDaysDto();
			tt.setCode("200");
			tt.setMessage("No Rows Found");
			tt.setDayID("");
			tt.setDayNAME("");
			tt.setTimeTableData(null);
			timeTableListDto.add(tt);
		}
		return timeTableListDto;
	}

	// GetTimeTableByTeacher
	@PostMapping(value = "/GetTimeTableByTeacher", headers = "Accept=application/json")
	public @ResponseBody List<TimeTableDayDto> listTimeTableByTeacher(@RequestBody Teacher teacher,
			@RequestHeader String token) {
		System.out.println(teacher.getTeacherId());
		List<TimeTableDayDto> timeTableListDto = new ArrayList<>();

		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			TimeTableDayDto tt = new TimeTableDayDto();
			tt.setMessage("Invalid Token");
			timeTableListDto.add(tt);
		} else {
			timeTableListDto = timeTableService.listTimeTableByTeacher(teacher.getTeacherId());
		}
		if (timeTableListDto.isEmpty()) {
			TimeTableDayDto tt = new TimeTableDayDto();
			tt.setCode("200");
			tt.setMessage("No Rows Found");
			tt.setDayId("");
			tt.setDayName("");
			tt.setTimeTableListData(null);
			timeTableListDto.add(tt);
		}
		return timeTableListDto;
	}

	// GetTodayTimeTableByTeacher
	@PostMapping(value = "/GetTodayTimeTableByTeacher", headers = "Accept=application/json")
	public @ResponseBody List<TimeTableDaysDto> listTodayTimeTableByTeacher(@RequestBody TimeTableInput timeTableInput,
			@RequestHeader String token) {
		String day = timeTableInput.getDay();
		String empId = timeTableInput.getEmpId();
		List<TimeTableDaysDto> timeTableListDto = new ArrayList<>();

		Claims claims = TokenUtils.verifyToken(token);

		if (tokenService.tokenValidate(token)) {
			TimeTableDaysDto tt = new TimeTableDaysDto();
			tt.setMessage("Invalid Token");
			timeTableListDto.add(tt);
		} else {
			timeTableListDto = timeTableService.listTodayTimeTableByTeacher(day, empId);
		}
		if (timeTableListDto.isEmpty()) {
			TimeTableDaysDto tt = new TimeTableDaysDto();
			tt.setMessage("No Rows Found");
			tt.setCode("200");
			tt.setDayID("");
			tt.setDayNAME("");
			tt.setTimeTableData(null);
			timeTableListDto.add(tt);
		}
		return timeTableListDto;
	}

	// TimeTable Search
	@GetMapping(value = "/GetAllTimeTableSearch/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody TimeTableDetails getAllTimeTable(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchData, @RequestHeader String token) {
		TimeTableDetails timeTableDetails = new TimeTableDetails();

		byte[] decodedBytes = Base64.getDecoder().decode(searchData);

		String decodedString = new String(decodedBytes);
		logger.info("decodedString: " + decodedString);

		Claims claims = TokenUtils.verifyToken(token);
		try {
			if (claims != null) {
				timeTableDetails = timeTableService.getAllTimeTable(id, decodedString);
			} else {
				timeTableDetails.setMessage("Invalid Token");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeTableDetails;
	}

	@GetMapping(value = "/ListAllTimeTable/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody TimeTableDetailsDtoSearch listAllTimeTable(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchData, @RequestHeader String token) {
		TimeTableDetailsDtoSearch timeTableDetailsDto = new TimeTableDetailsDtoSearch();
		Claims claims = TokenUtils.verifyToken(token);
		User userdata = userService.retrieveFromId(claims.getSubject());
		UserRegistration userRegistration = userRegistrationService.Login(userdata.getEmailId());
		
		String role = userRegistration.getRoleId();
		String email = userRegistration.getEmailId();
		String userId = userRegistration.getUserId();
		System.out.println("role id : "+role);
		System.out.println("email : "+email);
		System.out.println("userId: "+userId);
		byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
		String encodeString = new String(encodeBytes);
		System.out.println("encodeString: "+encodeString);
		
		byte[] decodedBytes = Base64.getDecoder().decode(searchData);

		String decodedString = new String(decodedBytes);
		//logger.info("decodedString: " + decodedString);
		System.out.println("decodedString: "+decodedString);
		
		try {
			if (tokenService.tokenValidate(token)) {
				
				timeTableDetailsDto.setMessage("Invalid Token");
				
			} else {			
					timeTableDetailsDto = timeTableService.listAllTimeTable(id, decodedString);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeTableDetailsDto;
	}
	
	@GetMapping(value = "/ListAllTeacherTimeTable/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody TimeTableDetailsDtoSearch listAllTeacherTimeTable(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchData, @RequestHeader String token) {
		TimeTableDetailsDtoSearch timeTableDetailsDto = new TimeTableDetailsDtoSearch();
		Claims claims = TokenUtils.verifyToken(token);
		User userdata = userService.retrieveFromId(claims.getSubject());
		UserRegistration userRegistration = userRegistrationService.Login(userdata.getEmailId());
		
		String role = userRegistration.getRoleId();
		String email = userRegistration.getEmailId();
		String userId = userRegistration.getUserId();
		System.out.println("role id : "+role);
		System.out.println("email : "+email);
		System.out.println("userId: "+userId);
		byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
		String encodeString = new String(encodeBytes);
		System.out.println("encodeString: "+encodeString);
		
		byte[] decodedBytes = Base64.getDecoder().decode(searchData);

		String decodedString = new String(decodedBytes);
		logger.info("decodedString: " + decodedString);
			
		
		try {
			if (tokenService.tokenValidate(token)) {
				
				timeTableDetailsDto.setMessage("Invalid Token");
				
			} else {			
					timeTableDetailsDto = timeTableService.listAllTeacherTimeTable(id, decodedString);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeTableDetailsDto;
	}
	
	@GetMapping(value = "/ListAllStudentTimeTable/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody TimeTableDetailsDtoSearch listAllStudentTimeTable(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchData, @RequestHeader String token) {
		TimeTableDetailsDtoSearch timeTableDetailsDto = new TimeTableDetailsDtoSearch();
		Claims claims = TokenUtils.verifyToken(token);
		User userdata = userService.retrieveFromId(claims.getSubject());
		StudentMaster studentMaster=new StudentMaster();
		studentMaster=studentMasterService.findUserById(userdata.getUserId());
		System.out.println("studentMaster=="+studentMaster.getClassId());
		byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
		String encodeString = new String(encodeBytes);
		byte[] decodedBytes = Base64.getDecoder().decode(searchData);
		String decodedString = new String(decodedBytes);
		try {
			if (tokenService.tokenValidate(token)) {		
				timeTableDetailsDto.setMessage("Invalid Token");
			} else {			
				System.out.println("in call");
					timeTableDetailsDto = timeTableService.listAllStudentTimeTable(id, decodedString,studentMaster.getClassId(),studentMaster.getSectionId());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeTableDetailsDto;
	}


	
	//view timetable by id	
	@PostMapping(value = "/getTimeTableElementById", headers = "Accept=application/json")
	public @ResponseBody TimeTableEditParamaters getTimeTableElementById(@RequestBody TimeTableEditParamaters timeTableEditParamaters, @RequestHeader String token) {
		
		TimeTableEditParamaters timeTable = null;	
		String classId = timeTableEditParamaters.getClassId();
		String sectionId = timeTableEditParamaters.getSectionId();
		String workId = timeTableEditParamaters.getWorkId();
		String subjId = timeTableEditParamaters.getSubjectId();
		String empId = timeTableEditParamaters.getEmpId();

		timeTable = timeTableService.getTimeTableElementById(classId,sectionId,workId,subjId,empId);    
		
		return timeTable;
	}
	@RequestMapping(value = "/deleteTimetable", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean DeleteResourceBank(@RequestBody TimeTableId timeTableId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		TimeTable timeTable = new TimeTable();
		if (tokenService.tokenValidate(token)) {
			timeTable.setMessage("Invalid token");
		} else {
			timeTableService.deleteNotification(timeTableId);//deleteTimeTable(timeTableId);//deleteSubjectResource(resourcebank);
		}
		return true;
	}
	
	
	
}