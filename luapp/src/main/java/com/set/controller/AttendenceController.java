package com.set.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.dto.AttendanceStatusDto;
import com.set.dto.AttendenceListDto;
import com.set.dto.StudentAttendance;
import com.set.model.Attendance;
import com.set.model.AttendanceListAll;
import com.set.model.LuMessage;
import com.set.model.WeekName;
import com.set.service.AttendenceService;
import com.set.service.ClassSectionMasterService;
import com.set.service.TokenService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class AttendenceController {

	@Autowired
	private AttendenceService attendenceService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	ServletContext context;

	@Autowired
	ClassSectionMasterService classSectionMasterService;

	private Logger logger = Logger.getLogger("AttendenceController.class");

	@PostMapping(value = "/AddAttendence", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addAttendence(@RequestBody Attendance attendance, @RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			try {
				attendance.setAttendanceId(CommonUtils.generatePrimaryKeyId("lutbl_attendence"));
				attendenceService.save(attendance);
				message.setMessage("Attendence Inserted successfully");
			} catch (DataIntegrityViolationException e) {				
				message.setMessage("Duplicate Entry For Same Attendance Date");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e) {
				message.setMessage("Add Attendance Failed");
				e.printStackTrace();
				
			}
		}
		return message;
	}

	@PostMapping(value = "/AttendanceStatus", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addAttendanceStatus(@RequestBody AttendanceStatusDto attendanceStatusDto,
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			
			
			try {
				Attendance attendance = new Attendance();
				for(StudentAttendance studentAttendance : attendanceStatusDto.getAttendanceStatus()) {
					attendance = new Attendance();
					attendance.setAttendanceId(CommonUtils.generatePrimaryKeyId("lutbl_attendence"));
					attendance.setClassId(attendanceStatusDto.getClassId());
					attendance.setAttendanceDate(attendanceStatusDto.getAttendanceDate());
					attendance.setSectionId(attendanceStatusDto.getSectionId());
					attendance.setInsertedTime(new Date());
					attendance.setIsDeleted(0);
					attendance.setStudentId(studentAttendance.getStudentId());
					attendance.setStatus(Integer.parseInt(studentAttendance.getStatus()));
					attendenceService.save(attendance);
				}
				message.setMessage("Attendance added successfully");
			} catch (DataIntegrityViolationException e) {				
				message.setMessage("Duplicate Entry For Same Attendance Date");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e) {
				message.setMessage("Add Attendance Failed");
				e.printStackTrace();
				
			}
		}
		return message;
	}

	@GetMapping(value = "/getAllAttendenceByClassId/{classId}", headers = "Accept=application/json")
	public @ResponseBody List<Attendance> getAllAttendenceByClassId(@PathVariable("classId") String classId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Attendance> attendence = new ArrayList<>();
		Attendance att = new Attendance();
		if (tokenService.tokenValidate(token)) {
			att.setMessage("Invalid token");
			attendence.add(att);
			logger.info("Invalid token");
		} else {		
			attendence = attendenceService.getAllAttendenceByClassId(classId);
			if(attendence.size()<1) {
				att.setMessage("record not found");
				attendence.add(att);
			}
		}
		return attendence;
	}

	@PostMapping(value = "/AttendanceList", headers = "Accept=application/json")
	public @ResponseBody AttendenceListDto getAllAttendenceByClassId(@RequestBody AttendanceListAll attedanceCriteria,
			@RequestHeader String token) {
		String classId = attedanceCriteria.getClassId();
		String sectionId = attedanceCriteria.getSectionId();
		Date attendanceDate = attedanceCriteria.getAttendanceDate();

		Claims claims = TokenUtils.verifyToken(token);
		AttendenceListDto attendenceListDto = new AttendenceListDto();
		
		if (tokenService.tokenValidate(token)) {
			attendenceListDto.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {		
			attendenceListDto = attendenceService.getAttendenceByClass(classId, sectionId, attendanceDate);
		}
		return attendenceListDto;
	}

}
