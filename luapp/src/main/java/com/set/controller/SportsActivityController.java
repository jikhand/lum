package com.set.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.dto.AttendenceListDto;
import com.set.dto.SportsActivityListDto;
import com.set.dto.SportsActivityMessageDto;
import com.set.dto.SportsActivityTeacherListDto;
import com.set.dto.StudentActivityDto;
import com.set.dto.StudentActivityDtoDetails;
import com.set.dto.StudentActivityListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.LuMessage;
import com.set.model.SportsActivity;
import com.set.model.SportsActivityDetails;
import com.set.model.SportsAnnouncement;
import com.set.model.SportsAnnouncementDetails;
import com.set.model.StudentActivity;
import com.set.model.StudentActivityId;
import com.set.model.StudentMaster;
import com.set.model.StudentsActivityDetails;
import com.set.model.User;
import com.set.service.ClassSectionMasterService;
import com.set.service.SportsActivityService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;


@CrossOrigin
@RestController
public class SportsActivityController {
	
	@Autowired
	private SportsActivityService sportsActivityService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	ServletContext context;
	@Autowired
	private UserService userService;
	@Autowired
	ClassSectionMasterService classSectionMasterService;
	
	private Logger logger = Logger.getLogger("SportsActivityController.class");
	
	//Insert the activity
	@PostMapping(value = "/addSportsActivity", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addSportsActivity(@RequestBody SportsActivity sportsActivity, @RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());                
            User userdata = userService.retrieveFromId(claims.getSubject());
            sportsActivity.setInsertedBy(userdata.getUserId());
            sportsActivity.setInsertedTime(timestamp);
			sportsActivityService.save(sportsActivity);
			message.setMessage("Sports And Activity Inserted successfully");
		}
		return message;
	}
	
	//view all the activities
	@GetMapping(value = "/getAllSportsActivitySelect", headers = "Accept=application/json")
	public @ResponseBody List<SportsActivity> getAllSportsActivity(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<SportsActivity> sportsActivity = new ArrayList<>();
		
		if (tokenService.tokenValidate(token)) {
			SportsActivity sa = new SportsActivity();
			sa.setMessage("Invalid token");
			sportsActivity.add(sa);
			logger.info("Invalid token");
		} else {
			sportsActivity = sportsActivityService.getAllSportsActivity();
		}
		return sportsActivity;
	}
	
	//edit the activity by activityCode
	@PutMapping(value = "/editSportsActivity/{activityCode}", headers = "Accept=application/json")
	public @ResponseBody SportsActivity editSportsActivity(@PathVariable("activityCode") int activityCode, @RequestBody SportsActivity sportsActivity,
			@RequestHeader String token) {
		//System.out.println("activityCode: "+activityCode);
		System.out.println("sportsA ctivityactivityCode"+sportsActivity.getActivityCode());
		System.out.println("sportsA getActivityDescription"+sportsActivity.getActivityDescription());
		
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			sportsActivity.setMessage("Invalid token");
			return sportsActivity;
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			sportsActivity.setActivityCode(activityCode);
			sportsActivity.setActivityDescription(sportsActivity.getActivityDescription());
			sportsActivity.setCoach(sportsActivity.getCoach());
			sportsActivity.setUpdatedBy(sportsActivity.getUpdatedBy());
			sportsActivity.setUpdatedTime(timestamp);
			sportsActivityService.editSportsActivity(sportsActivity);
			sportsActivity.setMessage("Update successfully");
			return sportsActivity;
		}
	}
	
	//delete the activity by activityCode
	@PutMapping(value = "/deleteSportsActivity/{activityCode}", headers = "Accept=Application/json")
	public @ResponseBody SportsActivity deleteSportsActivity(@PathVariable("activityCode") int activityCode,
			@RequestBody SportsActivity sportsActivity, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			sportsActivity.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			sportsActivity.setActivityCode(activityCode);
			sportsActivity.setUpdatedTime(timestamp);
			
			sportsActivityService.deleteSportsActivity(sportsActivity);
			sportsActivity.setMessage("Deleted successfully");
		}
		return sportsActivity;
	}
	
	//Based on the activity code view the activities
	@GetMapping(value = "/getSportsActivityByActivityCode/{activityCode}", headers = "Accept=application/json")
	public @ResponseBody List<SportsActivity> getSportsActivityByActivityCode(@PathVariable("activityCode") int activityCode, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<SportsActivity> sportsActivity = new ArrayList<>();
		if (tokenService.tokenValidate(token)) {
			SportsActivity sa = new SportsActivity();
			sa.setMessage("Invalid token");
			sportsActivity.add(sa);
			logger.info("Invalid token");
		} else {
			sportsActivity = sportsActivityService.getSportsActivityByActivityCode(activityCode);
		}
		return sportsActivity;
	}
	
	//view the activities for teacher/coach(passing the name from postman)
	@GetMapping(value = "/getSportsActivityByTeacherOrCoach/{TeacherCoach}", headers = "Accept=application/json")
	public @ResponseBody List<SportsActivity> getSportsActivityByTeacherOrCoach(@PathVariable("TeacherCoach") String teacherCoach, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<SportsActivity> sportsActivity = new ArrayList<>();
		if (tokenService.tokenValidate(token)) {
			SportsActivity sa = new SportsActivity();
			sa.setMessage("Invalid token");
			sportsActivity.add(sa);
			logger.info("Invalid token");
		} else {
			sportsActivity = sportsActivityService.getSportsActivityByTeacherOrCoach(teacherCoach);
		}
		return sportsActivity;
	}
	
	//view the activities for student by studentId
		@GetMapping(value = "/getSportsActivityByStudentId/{studentId}", headers = "Accept=application/json")
		public @ResponseBody SportsActivityListDto getSportsActivityByStudentId(@PathVariable("studentId") String studentId, @RequestHeader String token) {
			Claims claims = TokenUtils.verifyToken(token);
			SportsActivityListDto sportsActivity = new SportsActivityListDto();
			if (tokenService.tokenValidate(token)) {
				SportsActivityListDto sa = new SportsActivityListDto();
				sportsActivity.setMessage("Invalid token");
				//portsActivity.add(sa);
				logger.info("Invalid token");
			} else {
				sportsActivity = sportsActivityService.getSportsActivityByStudentId(studentId);
			}
			if(sportsActivity.getSportsDatas().isEmpty()) {
				sportsActivity.setMessage("No data found");
				sportsActivity.setCode("200");
			}
			return sportsActivity;
		}
		
		//View the Sports Activity list		
		@GetMapping(value = "/ListSportsActivity", headers = "Accept=application/json")
		public @ResponseBody SportsActivityDetails listSportsActivity(@RequestHeader String token) {
			
			SportsActivityDetails sportsActivityDetails =new SportsActivityDetails();
		
			Claims claims = TokenUtils.verifyToken(token);
			if (tokenService.tokenValidate(token)) {
			sportsActivityDetails.setMessage("Invalid token");
			logger.info("Invalid token");
			} else {
			sportsActivityDetails=sportsActivityService.listSportsActivity();			
			}
			return sportsActivityDetails;
		}
		
		//View the Sports Activity ByClassIdSectionId		
		@GetMapping(value = "/ListSportsActivityByClassIdSectionId/{classId}/{sectionId}", headers = "Accept=application/json")
		public @ResponseBody SportsActivityDetails getSportsActivityByClassIDSectionID(@PathVariable("classId") String classId, 
				@PathVariable("sectionId") String sectionId, @RequestHeader String token) {
			
			SportsActivityDetails sportsActivityDetails =new SportsActivityDetails();
		
			Claims claims = TokenUtils.verifyToken(token);
			
			if (tokenService.tokenValidate(token)) {
				sportsActivityDetails.setMessage("Invalid token");
				logger.info("Invalid token");
			} else {
			sportsActivityDetails=sportsActivityService.getSportsActivityByClassIDSectionID(classId, sectionId);			
		    }
			return sportsActivityDetails;
		}
		
		
		//Sports Activity Search 
		@GetMapping(value = "/ListSportsActivitySearch/{id}/{searchdata}", headers = "Accept=application/json")
		public @ResponseBody SportsActivityDetails listSportsActivitySearch(@PathVariable("id") int id,@PathVariable("searchdata") String searchData,
				@RequestHeader String token) {
		SportsActivityDetails sportsActivityDetails =new SportsActivityDetails();		
			
			byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
			String encodeString = new String(encodeBytes);
			System.out.println("encodeString: "+encodeString);
			
			byte[] decodedBytes = Base64.getDecoder().decode(searchData);		
			
			String decodedString = new String(decodedBytes);
			//System.out.println("decodedString: "+decodedString);
			
			Claims claims = TokenUtils.verifyToken(token);
			try {
				if (tokenService.tokenValidate(token)) {
					sportsActivityDetails.setMessage("Invalid token");
					logger.info("Invalid token");
				} else {		
					sportsActivityDetails=sportsActivityService.listSportsActivitySearch(id,decodedString);			
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sportsActivityDetails;
		}
	//ListSportsActivity by userid - for teacher
		
		@GetMapping(value = "/getSportsActivityByUserId/{userId}", headers = "Accept=application/json")
		public @ResponseBody SportsActivityTeacherListDto getSportsActivityByUserId(@PathVariable("userId") String userId, @RequestHeader String token) {
			Claims claims = TokenUtils.verifyToken(token);
			System.out.println("getSportsActivityByUserId*************************");
			SportsActivityTeacherListDto sportsActivity = new SportsActivityTeacherListDto();
			if (tokenService.tokenValidate(token)) {
				SportsActivityMessageDto sa = new SportsActivityMessageDto();
				sportsActivity.setMessage("Invalid token");
//				sportsActivity.add(sa);
				logger.info("Invalid token");
			} else {
				System.out.println("userId******************");
				sportsActivity = sportsActivityService.getSportsActivityByUserId(userId);
			}
			if(sportsActivity.getSportsDatas().isEmpty()) {
				sportsActivity.setMessage("No data found");
				sportsActivity.setCode("200");
			}
			return sportsActivity;
		}
		
		
		
	//Student Activity Details ************************************************************************************
	//Insert student in student activity table
	@PostMapping(value = "/addStudentActivity", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addStudentActivity(@RequestBody StudentActivityDto studentActivityDto, @RequestHeader String token) {
		LuMessage message = new LuMessage();
		StudentActivity studentData = null;
		Claims claims = TokenUtils.verifyToken(token);
		
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");				
			logger.info("Invalid token");
		} else {	
			StudentActivityId student = null;
			User userdata = userService.retrieveFromId(claims.getSubject());
			//taken the dto students and added one by one the activities to it.
			List <StudentActivity> studentActivityList = new ArrayList<StudentActivity>();
			System.out.println("studentActivityDto **************"+studentActivityDto.getEnrolledDate());
			System.out.println("studentActivityDto **************"+studentActivityDto.getDeactivateDate());
			System.out.println("studentActivityDto: list : "+studentActivityDto.getStudentList());
			System.out.println("StudentActivityDto: "+studentActivityDto.getActivityCode());
			for (String studentId : studentActivityDto.getStudentList()) {
				//studentData.setStudentActivityId();
				//StudentActivityId student = new StudentActivityId();
				studentData = new StudentActivity();
				System.out.println("studentId:"+studentId);
				int activityCode = studentActivityDto.getActivityCode();
				
				student = new StudentActivityId(studentId, activityCode);
				
				System.out.println("id: "+student.getActivityCode());
				System.out.println("id: "+student.getStudentId());
				
				studentData.setStudentActivityId(student);
				studentData.setEnrolledDate(studentActivityDto.getEnrolledDate());
				studentData.setDeactivateDate(studentActivityDto.getDeactivateDate());
				studentData.setInsertedBy(userdata.getUserId());
				studentActivityList.add(studentData);
			}
				
			sportsActivityService.saveStudentActivity(studentActivityList);
			message.setMessage("Student Activity Inserted successfully");
		}	
		return message;
	}

	//View all student activity
	@GetMapping(value = "/getAllStudentActivitySelect", headers = "Accept=application/json")
	public @ResponseBody List<StudentActivity> getAllStudentActivity(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<StudentActivity> studentActivity = new ArrayList<>();
		if (tokenService.tokenValidate(token)) {
			StudentActivity sa = new StudentActivity();
			sa.setMessage("Invalid token");
			studentActivity.add(sa);
			logger.info("Invalid token");
		} else {
			studentActivity = sportsActivityService.getAllStudentActivity();
		}
		return studentActivity;
	}
	
	//update the student activity
	@PostMapping(value = "/editStudentActivity", headers = "Accept=Application/json")
	public @ResponseBody LuMessage editStudentActivity(@RequestBody StudentActivityDto studentActivityDto, @RequestHeader String token) {
		LuMessage message = new LuMessage();
		StudentActivity studentData = null;
		Claims claims = TokenUtils.verifyToken(token);
		
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			StudentActivityId student = null;
			User userdata = userService.retrieveFromId(claims.getSubject());
			//taken the dto students and added one by one the activities to it.
			List <StudentActivity> studentActivityList = new ArrayList<StudentActivity>();
			System.out.println("studentActivityDto **************"+studentActivityDto.getEnrolledDate());
			System.out.println("studentActivityDto **************"+studentActivityDto.getDeactivateDate());
			System.out.println("studentActivityDto: list : "+studentActivityDto.getStudentList());
			System.out.println("StudentActivityDto: "+studentActivityDto.getActivityCode());
			for (String studentId : studentActivityDto.getStudentList()) {
				//studentData.setStudentActivityId();
				//StudentActivityId student = new StudentActivityId();
				studentData = new StudentActivity();
				System.out.println("studentId:"+studentId);
				int activityCode = studentActivityDto.getActivityCode();
				
				student = new StudentActivityId(studentId, activityCode);
				
				System.out.println("id: "+student.getActivityCode());
				System.out.println("id: "+student.getStudentId());
				
				studentData.setStudentActivityId(student);
				studentData.setEnrolledDate(studentActivityDto.getEnrolledDate());
				studentData.setDeactivateDate(studentActivityDto.getDeactivateDate());
				studentData.setUpdatedBy(userdata.getUserId());		
				studentActivityList.add(studentData);
			}
				
			sportsActivityService.updateStudentActivity(studentActivityList);
			message.setMessage("Student Activity Updated successfully");
		}	
		return message;
	}
	
	//delete the student activity
	@RequestMapping(value = "/deleteStudentActivity/{activityCode}/{studentId}", method = RequestMethod.PUT)
	public @ResponseBody StudentActivity deleteStudentActivity(@PathVariable("activityCode") int activityCode, 
			@PathVariable("studentId") String studentId, @RequestHeader String token) {
		StudentActivity studentActivity = new StudentActivity();
		if (tokenService.tokenValidate(token)) {
			studentActivity.setMessage("Invalid Token");
		} else {
			StudentActivityId studentDetails = new StudentActivityId(studentId, activityCode);
			studentActivity.setStudentActivityId(studentDetails);			
			sportsActivityService.deleteStudentActivity(studentActivity);
			studentActivity.setMessage("Student Activity Deleted successfully");
		}
		return studentActivity;
	}	
	
	//For student Activity Screen - Class drop down	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getClassListSelect", headers = "Accept=application/json")
	public @ResponseBody Map<String, String> getClassListSelect(@RequestHeader String token) {
		
		Claims claims = TokenUtils.verifyToken(token);
		Map<String, String> classList = new HashMap<String, String>();
		if (tokenService.tokenValidate(token)) {
			logger.info("logs invalid token");
			classList.put("Message", "Invalid Token");
			return (Map<String, String>) classList;
		} else {
			Map<String, String> hm = sportsActivityService.getClassListSelect();
			return hm;
		}
	}
	
	//BaSed on the class selection - section drop down 
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getSectionListSelect/{classId}", headers = "Accept=application/json")
	public @ResponseBody Map<String, String> getSectionListSelect(@PathVariable("classId") String classId,
			@RequestHeader String token) {
		
		Claims claims = TokenUtils.verifyToken(token);
		Map<String, String> sectionList = new HashMap<String, String>();
		if (tokenService.tokenValidate(token)) {
			logger.info("logs invalid token");
			sectionList.put("Message", "Invalid Token");
			return (Map<String, String>) sectionList;
		} else {
			Map<String, String> hm = sportsActivityService.getSectionListSelect(classId);
			return hm;
		}
	}
	
	//Based on the class and section - students
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getStudentsByClassSectionId/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody StudentActivityDtoDetails getStudentsByClassSectionId(@PathVariable("classId") String classId, 
			@PathVariable("sectionId") String sectionId, @RequestHeader String token) {
		
		Claims claims = TokenUtils.verifyToken(token);
		//List<StudentMaster> studentMaster = null;
		StudentActivityDtoDetails studentActivityDto = new StudentActivityDtoDetails();
		if (tokenService.tokenValidate(token)) {
			studentActivityDto.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			studentActivityDto = sportsActivityService.getStudentsByClassSectionId(classId, sectionId);
		}
		return studentActivityDto;
	}
	
	
	//View the students - Sports Activity By activityCode ClassId SectionId		
	@GetMapping(value = "/ListStudentsByClassSecActivity/{activityCode}/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody StudentActivityListDto listStudentsByClassSecActivity(@PathVariable("activityCode") String activityCode, @PathVariable("classId") String classId, 
			@PathVariable("sectionId") String sectionId, @RequestHeader String token) {
		
		StudentActivityListDto studentsActivityDetails =new StudentActivityListDto();
	
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			studentsActivityDetails.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			studentsActivityDetails=sportsActivityService.listStudentsByClassSecActivity(activityCode, classId, sectionId);			
		}
		return studentsActivityDetails;
	}
	
	
	
	//***********************************************************************************************
	//Insert the SportsAnnouncement
		@PostMapping(value = "/addSportsAnnouncement", headers = "Accept=Application/json")
		public @ResponseBody LuMessage addSportsAnnouncement(@RequestBody SportsAnnouncement sportsAnnouncement, @RequestHeader String token) {
			LuMessage message = new LuMessage();
			Claims claims = TokenUtils.verifyToken(token);
			if (tokenService.tokenValidate(token)) {
				message.setMessage("Invalid token");
				logger.error("Invalid token");
			} else {
	            Timestamp timestamp = new Timestamp(System.currentTimeMillis());                
	            User userdata = userService.retrieveFromId(claims.getSubject());
	           
	            sportsAnnouncement.setCreatedAt(timestamp);
	            sportsAnnouncement.setUpdatedAt(timestamp);
				sportsActivityService.saveSportsAnnouncement(sportsAnnouncement);
				message.setMessage("Sports Announcement Inserted successfully");
			}
			return message;
		}
		
		//edit the activity by activityCode
		@PutMapping(value = "/editSportsAnnouncement/{id}", headers = "Accept=application/json")
		public @ResponseBody LuMessage editSportsAnnouncement(@PathVariable("id") int id, @RequestBody SportsAnnouncement sportsAnnouncement,
				@RequestHeader String token) {
			//System.out.println("activityCode: "+activityCode);
			LuMessage message = new LuMessage();
			
			Claims claims = TokenUtils.verifyToken(token);
			if (tokenService.tokenValidate(token)) {
				message.setMessage("Invalid token");
				logger.error("Invalid token");
			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				sportsAnnouncement.setId(id);
				sportsAnnouncement.setContactEmail(sportsAnnouncement.getContactEmail());
				sportsAnnouncement.setContactPhone(sportsAnnouncement.getContactPhone());
				sportsAnnouncement.setDescription(sportsAnnouncement.getDescription());
				sportsAnnouncement.setEventSponcered(sportsAnnouncement.getEventSponcered());
				sportsAnnouncement.setLastDateForEntry(sportsAnnouncement.getLastDateForEntry());
				sportsAnnouncement.setMatchTimings(sportsAnnouncement.getMatchTimings());
				sportsAnnouncement.setTitle(sportsAnnouncement.getTitle());
				sportsAnnouncement.setUpdatedAt(timestamp);
		
				sportsActivityService.editSportsAnnouncement(sportsAnnouncement);
				message.setMessage("Sports Announcement Updated Successfully");
				
			}return message;
		}
		
		//delete the activity by activityCode
		@PutMapping(value = "/deleteSportsAnnouncement/{id}", headers = "Accept=Application/json")
		public @ResponseBody LuMessage deleteSportsAnnouncement(@PathVariable("id") int id,
				@RequestBody SportsAnnouncement sportsAnnouncement, @RequestHeader String token) {
			LuMessage message = new LuMessage();
			Claims claims = TokenUtils.verifyToken(token);
			if (tokenService.tokenValidate(token)) {
				message.setMessage("Invalid token");
				logger.error("Invalid token");
			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				sportsAnnouncement.setId(id);
				sportsAnnouncement.setUpdatedAt(timestamp);				
				sportsActivityService.deleteSportsAnnouncement(sportsAnnouncement);
				message.setMessage("Sports Announcement Deleted Successfully");
			}
			return message;
		}
		
		//View the Sports Activity list		
		@GetMapping(value = "/ListSportsAnnouncement", headers = "Accept=application/json")
		public @ResponseBody SportsAnnouncementDetails listSportsAnnouncement(@RequestHeader String token) {
			
			SportsAnnouncementDetails sportsAnnouncementDetails =new SportsAnnouncementDetails();
		
			Claims claims = TokenUtils.verifyToken(token);
			if (tokenService.tokenValidate(token)) {
				sportsAnnouncementDetails.setMessage("Invalid token");
				logger.error("Invalid token");
			} else {
				sportsAnnouncementDetails=sportsActivityService.listSportsAnnouncement();			
			}
			return sportsAnnouncementDetails;
		}
		
		//View the Sports Activity list		
		@GetMapping(value = "/GetLatestSportsAnnouncement/{classId}/{sectionId}", headers = "Accept=application/json")
		public @ResponseBody SportsAnnouncementDetails getLatestSportsAnnouncement(@PathVariable("classId") String classId, 
				@PathVariable("sectionId") String sectionId, @RequestHeader String token) {
			
			SportsAnnouncementDetails sportsAnnouncementDetails =new SportsAnnouncementDetails();
		
			Claims claims = TokenUtils.verifyToken(token);
			if (tokenService.tokenValidate(token)) {
				sportsAnnouncementDetails.setMessage("Invalid token");
				logger.error("Invalid token");
			} else {			
				sportsAnnouncementDetails=sportsActivityService.getLatestSportsAnnouncement(classId, sectionId);			
			}
			return sportsAnnouncementDetails;
		}
}