package com.set.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.set.controller.helper.ClassSubjectControllerHelper;
import com.set.dto.ClassSectionMasterDetailsDto;
import com.set.dto.ClassSubjectDetailsDto;
import com.set.dto.ClassSubjectDto;
import com.set.dto.ClassSubjectMasterDetailsDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetail;
import com.set.model.ClassSubjectDetails;
import com.set.model.ClassSubjectId;
import com.set.model.LuMessage;
import com.set.model.User;
import com.set.service.ClassSubjectService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class ClassSubjectController {
	@Autowired
	private ClassSubjectService classSubjectService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private ClassSubjectControllerHelper classSubjectControllerHelper;

	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger("ClassSubjectController.class");

	/*
	 * @description addClassSubject with details
	 * 
	 * @param @token,ClassSubject
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 */
	@PostMapping(value = "/addClassSubject", headers = { "content-type=multipart/mixed",
	"content-type=multipart/form-data" })
public @ResponseBody ClassSubject addClassSubject(
	@RequestPart(value = "ClassSubject", required = false) ClassSubject classSubject,
	@RequestPart(value = "ClassSubjectId", required = false) ClassSubjectId classSubjectId,
	@RequestHeader String token) throws IOException{
		String[] arrOfStr = classSubject.getTempId().split(",");
		String[] StrTeaher = classSubjectId.getTeacherId().split(",");
		classSubject.getAcademicYear();
		classSubject.getTeacherName();
		System.out.println(arrOfStr[1]);
		System.out.println(arrOfStr[0]);
		if (tokenService.tokenValidate(token)) {
			classSubject.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Claims claims = TokenUtils.verifyToken(token);
			User user = userService.retrieveFromId(claims.getSubject());
			classSubject.setInsertedBy(user.getUserId());
			classSubject.setIsSoftDelete(false);
			classSubject.setInsertedTime(CommonUtils.getCurrentDateTime());
            ClassSubjectId newClassSubject = new ClassSubjectId(arrOfStr[0], arrOfStr[1],classSubjectId.getSubjectId(),StrTeaher[0]);
			classSubject.setClassSubjectId(newClassSubject);
			classSubjectService.save(classSubject);
			classSubject.setMessage("Insert successfully");
			logger.info("Insert successfully");
		}
		return classSubject;
	}

	@PostMapping(value = "/InsertClassSubject", headers = "Accept=Application/json")
	public @ResponseBody LuMessage insertClassSubject(@RequestBody ClassSubjectDto classSubjectDto,
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			ClassSubject classSubject = new ClassSubject();
			Claims claims = TokenUtils.verifyToken(token);
			User user = userService.retrieveFromId(claims.getSubject());
			classSubject.setInsertedBy(user.getUserId());
			classSubject.setIsSoftDelete(false);
			classSubject.setInsertedTime(CommonUtils.getCurrentDateTime());
			ClassSubjectId classSubjectId = new ClassSubjectId(classSubjectDto.getClassId(),
					classSubjectDto.getSectionId(),classSubjectDto.getSubjectId(),classSubjectDto.getTeacherId());
			classSubject.setClassSubjectId(classSubjectId);
			classSubject.setTeacherName(classSubjectDto.getTeacherFirstName() + " "
					+ classSubjectDto.getTeacherMiddleName() + " " + classSubjectDto.getTeacherLastName());
			classSubject.setSubjectName(classSubjectDto.getSubjectName());
			classSubjectService.save(classSubject);
			message.setMessage("Class Subject Inserted successfully");
			logger.info("Class Subject Inserted successfully");
		}
		return message;
	}

	@PutMapping(value = "/addClassSubject", headers = { "content-type=multipart/mixed",
	"content-type=multipart/form-data" })
public @ResponseBody ClassSubject UpdateClassSubject(
	@RequestPart(value = "ClassSubject", required = false) ClassSubject classSubject,
	@RequestPart(value = "ClassSubjectId", required = false) ClassSubjectId classSubjectId,
	@RequestHeader String token) {
		if (tokenService.tokenValidate(token)) {
			classSubject.setMessage("Invalid token");
			logger.info("Invalid token");
			return classSubject;
		} else {
			String[] arrOfStr = classSubject.getTempId().split(",");
			String[] StrTeaher = classSubjectId.getTeacherId().split(",");
			Claims claims = TokenUtils.verifyToken(token);
			User user = userService.retrieveFromId(claims.getSubject());
			ClassSubjectId newClassSubjectId = new ClassSubjectId(arrOfStr[0], arrOfStr[1],classSubjectId.getSubjectId(),StrTeaher[0]);
			classSubject.setClassSubjectId(newClassSubjectId);
			// classSubject.setSubjectId(SubjectId);
			ClassSubject newclassSubject = classSubjectService.getElementById(classSubject);
			classSubject.setUpdatedBy(user.getUserId());
			classSubject.setInsertedBy(newclassSubject.getInsertedBy());
			classSubject.setInsertedTime(newclassSubject.getInsertedTime());
			classSubject.setUpdatedTime(CommonUtils.getCurrentDateTime());
			classSubjectService.update(classSubject);
			classSubject.setMessage("update successfully");
			logger.info("Insert successfully");
			return classSubject;
		}
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
	// SubjectId ClassId SectionId
	@PutMapping(value = "/addClassSubject/{SubjectId}/{ClassId}/{SectionId}/{TeacherId}", headers = "Accept=application/json")
	public @ResponseBody ClassSubject updateClassSubject(@PathVariable("SubjectId") String SubjectId,
			@PathVariable("ClassId") String ClassId, @PathVariable("SectionId") String SectionId, @PathVariable("TeacherId") String TeacherId,
			@RequestBody ClassSubject classSubject, @RequestHeader String token) {
		if (tokenService.tokenValidate(token)) {
			classSubject.setMessage("Invalid token");
			logger.info("Invalid token");
			return classSubject;
		} else {
			Claims claims = TokenUtils.verifyToken(token);
			User user = userService.retrieveFromId(claims.getSubject());
			ClassSubjectId newClassSubjectId = new ClassSubjectId(ClassId, SectionId, SubjectId, TeacherId);
			classSubject.setClassSubjectId(newClassSubjectId);
			//classSubject.setSubjectId(SubjectId);
			classSubject = classSubjectService.getElementById(classSubject);
			classSubject.setUpdatedBy(user.getUserId());
			classSubject.setUpdatedTime(CommonUtils.getCurrentDateTime());
			classSubjectService.update(classSubject);
			classSubject.setMessage("update successfully");
			logger.info("Insert successfully");
			return classSubject;
		}
	}

	@PostMapping(value = "/getClassSubjectbyId", headers = "Accept=application/json")
	public @ResponseBody ClassSubject getClassSubject(@RequestBody ClassSubjectId classSubjectId, @RequestHeader String token) {
		ClassSubjectDetailsDto classSubjectDetails = new ClassSubjectDetailsDto();
		ClassSubject classSubject = new ClassSubject();
		System.out.println("tokenService" + tokenService.tokenValidate(token));
		if (tokenService.tokenValidate(token)) {
			classSubjectDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			//ClassSubjectId classSubjectId = new ClassSubjectId(ClassId, SectionId, classSubject.getClassSubjectId().getSubjectId(), classSubject.getClassSubjectId().getTeacherId());
			classSubject.setClassSubjectId(classSubjectId);
			classSubject = classSubjectService.getElementById(classSubject);
		}
		return classSubject;
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
	@GetMapping(value = "/getClassSubjectbyId/{SubjectId}/{ClassId}/{SectionId}/{TeacherId}", headers = "Accept=application/json")
	public @ResponseBody ClassSubjectDetailsDto getClassSubject(@PathVariable("SubjectId") String SubjectId,
			@PathVariable("ClassId") String ClassId, @PathVariable("SectionId") String SectionId, @PathVariable("TeacherId") String TeacherId,
			@RequestHeader String token) {
		ClassSubjectDetailsDto classSubjectDetails = new ClassSubjectDetailsDto();
		ClassSubject classSubject = new ClassSubject();
		System.out.println("tokenService" + tokenService.tokenValidate(token));
		if (tokenService.tokenValidate(token)) {
			classSubjectDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			ClassSubjectId classSubjectId = new ClassSubjectId(ClassId, SectionId,SubjectId,TeacherId);
			classSubject.setClassSubjectId(classSubjectId);
			classSubject = classSubjectService.getElementById(classSubject);
			classSubjectDetails.setId(classSubject.getClassSubjectId().getSubjectId());
			classSubjectDetails.setSubjectName(classSubject.getSubjectName());
			classSubjectDetails.setAcademicYear(classSubject.getAcademicYear());
			classSubjectDetails.setTeacherId(classSubject.getClassSubjectId().getTeacherId());
			classSubjectDetails.setTeacherName(classSubject.getTeacherName());
			classSubjectDetails.setTorUseField1(classSubject.getForUseField1());
			classSubjectDetails.setForUseField2(classSubject.getForUseField2());
			classSubjectDetails.setInsertedBy(classSubject.getInsertedBy());
			classSubjectDetails.setInsertedTime(classSubject.getInsertedTime());
			classSubjectDetails.setUpdatedBy(classSubject.getUpdatedBy());
			classSubjectDetails.setUpdatedTime(classSubject.getUpdatedTime());
			classSubjectDetails.setMessage(classSubject.getMessage());
		}
		return classSubjectDetails;
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
	@GetMapping(value = "/getAllClassSubjects", headers = "Accept=application/json")
	public @ResponseBody ClassSubjectMasterDetailsDto getAllClassSubjects(@RequestHeader String token) {
		ClassSubjectMasterDetailsDto classSubjectMasterDetailsDto = new ClassSubjectMasterDetailsDto();
		ClassSubjectDetails classSubjectDetails = new ClassSubjectDetails();
		if (tokenService.tokenValidate(token)) {
			classSubjectMasterDetailsDto.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			classSubjectDetails = classSubjectService.list(1, "");
			classSubjectMasterDetailsDto = classSubjectControllerHelper.setClassSubjectDetailsDto(classSubjectDetails);
		}
		return classSubjectMasterDetailsDto;
	}

	@GetMapping(value = "/listOfAllClassSubject", headers = "Accept=application/json")
	public @ResponseBody ClassSubjectDetails getListUser(@RequestHeader String token) {
		ClassSubjectDetails classSubjectDetails = new ClassSubjectDetails();
		if (tokenService.tokenValidate(token)) {
			classSubjectDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			classSubjectDetails = classSubjectService.list(1, "");
		}
		return classSubjectDetails;
	}

	/*
	 * @description Delete by Id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubject
	 *
	 */
	@RequestMapping(value = "/deleteClassSubject/{SubjectId}/{ClassId}/{SectionId}/{TeacherId}", method = RequestMethod.PUT)
	public @ResponseBody ClassSubject deleteClassSubjecttById(@PathVariable("SubjectId") String SubjectId,
			@PathVariable("ClassId") String ClassId, @PathVariable("SectionId") String SectionId, @PathVariable("TeacherId") String TeacherId,
			@RequestHeader String token) {
		ClassSubject classSubject = new ClassSubject();
		if (tokenService.tokenValidate(token)) {
			classSubject.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			ClassSubjectId newClassSubjectId = new ClassSubjectId(ClassId, SectionId,SubjectId,TeacherId);
			classSubject.setClassSubjectId(newClassSubjectId);
//			classSubject.setSubjectId(SubjectId);
			classSubject = classSubjectService.getElementById(classSubject);
			classSubject.setIsSoftDelete(true);
			classSubjectService.deleteElementById(classSubject);
			classSubject.setMessage("Deleted successfully");
			logger.info("Deleted successfully");
		}
		return classSubject;
	}

	@GetMapping(value = "/getAllClassSubjectSelect", headers = "Accept=application/json")
	public @ResponseBody List<ClassSubject> getAllClassSubjectSelect(@RequestHeader String token) throws IOException {
		// Claims claims = TokenUtils.verifyToken(token);
		List<ClassSubject> classSubject = new ArrayList<>();
		
		if (tokenService.tokenValidate(token)) {
			ClassSubject csm = new ClassSubject();
			csm.setMessage("Invalid token");
			classSubject.add(csm);
			logger.info("Invalid token");
		} else {			
			classSubject = classSubjectService.getAllClassSubjectSelect();// getAllUserSelect();
		}
		return classSubject;
	}

	@GetMapping(value = "/getClassSubjectbyTeacherId", headers = "Accept=application/json")
	public @ResponseBody ClassSubjectDetail getClassSubjectbyTeacherId(@RequestHeader String token) {
		ClassSubjectDetail classSubjectDetails = new ClassSubjectDetail();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			classSubjectDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			String teacherId = userdata.getUserId();
			classSubjectDetails = classSubjectService.getClassSubjectbyTeacherId(teacherId);
		}
		return classSubjectDetails;
	}

}
