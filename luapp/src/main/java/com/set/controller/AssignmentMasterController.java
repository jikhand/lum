package com.set.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
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
import org.springframework.web.multipart.MultipartFile;
import com.set.controller.helper.AssignmentControllerHelper;
import com.set.dto.AssignmentDto;
import com.set.dto.AssignmentEvaluationDto;
import com.set.dto.AssignmentMasterDetailsDto;
import com.set.dto.AssignmentMasterDto;
import com.set.dto.StudentAssignmentMasterDetailsDto;
import com.set.model.AssignmentEvaluation;
import com.set.model.AssignmentMaster;
import com.set.model.AssignmentMasterDetails;
import com.set.model.AssignmentMasterId;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectId;
import com.set.model.LuMessage;
import com.set.model.PageCategory;
import com.set.model.StudentClassSectionId;
import com.set.model.StudentMaster;
import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterId;
import com.set.model.User;
import com.set.model.UserId;
import com.set.service.AssignmentEvaluationService;
import com.set.service.AssignmentMasterService;
import com.set.service.ClassSectionMasterService;
import com.set.service.ClassSubjectService;
import com.set.service.PageCategoryService;
import com.set.service.StudentMasterService;
import com.set.service.SubjectMasterService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class AssignmentMasterController {
	@Autowired
	private AssignmentMasterService assignmentMasterService;
	@Autowired
	private AssignmentEvaluationService assignmentEvaluationService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private SubjectMasterService subjectMasterService;
	@Autowired
	private ClassSubjectService classSubjectService;
	@Autowired
	private ClassSectionMasterService classSectionMasterService;
	@Autowired
	private StudentMasterService studentMasterService;
	@Autowired
	private PageCategoryService pageCategoryService;
	@Autowired
	AssignmentControllerHelper assignmentControllerHelper;

	private Logger logger = Logger.getLogger("AssignmentMasterController.class");

	/*
	 * @description addClassSubject with details
	 * 
	 * @param @token,ClassSubject
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 * @ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	 */
	@PostMapping(value = "/InsertTeacherAssignment", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addAssignmentMaster(@RequestBody AssignmentDto assignment,
			@RequestHeader String token) {
		LuMessage luMessage = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			luMessage.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			AssignmentMaster assignmentMaster = new AssignmentMaster();
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentMaster.setInsertedBy(user.getUserId());
			assignmentMaster.setAssignActive(true);
			assignmentMaster.setInsertedTime(CommonUtils.getCurrentDateTime());
			AssignmentMasterId assignmentMasterId = new AssignmentMasterId(
					CommonUtils.generatePrimaryKeyId("lutbl_assgnmnt_master"), assignment.getClassId(),
					assignment.getSectionId());
			assignmentMaster.setAssignmentMasterId(assignmentMasterId);
			assignmentMaster.setDescription(assignment.getAssignmentDescription());
			assignmentMaster.setAssignStartDate(CommonUtils.getCurrentDateTime());
			assignmentMaster.setAssignDueDate(assignment.getAssignmentDueDate());
			assignmentMaster.setAssociateTeacherId(assignment.getTeacherId());
			assignmentMaster.setAssignPath(assignment.getAssignmentAttachment());
			assignmentMaster.setAssignmentSubject(assignment.getAssignmentSubject());
			assignmentMaster.setIsDeleted(0);
			System.out.println("assignment: "+assignment.getAssignmentType());
			String assignmentTypeName = Constant.WRITE;
			
			if (assignment.getAssignmentType().equals("2")) {
				assignmentTypeName = Constant.DRAW;
			}
			assignmentMaster.setAssignType(assignmentTypeName);
			assignmentMaster.setSubjectId(assignment.getSubjectId());
			assignmentMaster.setMaxMarks(assignment.getAssignmentMark());
			assignmentMaster.setAssignCreatedBy(assignment.getTeacherId());
			assignmentMaster.setPageCategoryId(assignment.getPageTypeId());
			PageCategory pageCategory = pageCategoryService.getPageCategoryById(assignment.getPageTypeId());
			assignmentMaster.setPageTypeName(pageCategory.getPageCategoryTypeName());
			assignmentMasterService.save(assignmentMaster);
			luMessage.setMessage("Assignment Inserted successfully");
		}
		return luMessage;
	}
	// @ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@PostMapping(value = "/addAssignmentMaster", headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody AssignmentMaster addAssignmentMaster(
			@RequestPart(value = "assignmentMaster", required = false) AssignmentMaster assignmentMaster,
			@RequestPart(value = "assignmentAttachment", required = false) MultipartFile file,
			@RequestHeader String token) throws IOException {
		String filepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			assignmentMaster.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			String[] arrOfStr = assignmentMaster.getTempId().split(",");
//			if (file != null && !file.isEmpty()) {
//				StringJoiner sj = new StringJoiner(" , ");
//				byte[] bytes = file.getBytes();
//				filepath = Constant.ASSIGNMENT_UPLOAD_DIRECTORY + file.getOriginalFilename();
//				Path path = Paths.get(filepath);
//				Files.write(path, bytes);
//				sj.add(file.getOriginalFilename());
//				assignmentMaster.setAssignPath(filepath);// setSubjectCoverpage(file);
//			}*******************************************************************************************************************SAI
			if (file != null && !file.isEmpty()) {
				long currentTime=System.currentTimeMillis();
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = file.getBytes();
				filepath = Constant.ASSIGNMENT_UPLOAD_DIRECTORY + currentTime+file.getOriginalFilename();
				Path path = Paths.get(filepath);
				Files.write(path, bytes);
				sj.add(file.getOriginalFilename());
				assignmentMaster.setAssignPath(currentTime+file.getOriginalFilename());// setResourcePath(file);
			}
			
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentMaster.setInsertedBy(user.getUserId());
			assignmentMaster.setAssignActive(true);
			assignmentMaster.setInsertedTime(CommonUtils.getCurrentDateTime());
			// String assignmentId, String classId, String sectionId
			AssignmentMasterId newAssignmentMaster = new AssignmentMasterId(
					CommonUtils.generatePrimaryKeyId("lutbl_assgnmnt_master"), arrOfStr[0], arrOfStr[1]);
			assignmentMaster.setAssignmentMasterId(newAssignmentMaster);
			assignmentMaster.setIsDeleted(0);
			assignmentMasterService.save(assignmentMaster);
			assignmentMaster.setMessage("Insert successfully");
		}
		return assignmentMaster;
	}

	@PutMapping(value = "/addAssignmentMaster/{assignmentId}/{classId}/{sectionId}", headers = {
			"content-type=multipart/mixed", "content-type=multipart/form-data" })
	public @ResponseBody AssignmentMaster updateAssignmentMaster(@PathVariable("assignmentId") String assignmentId,
			@PathVariable("classId") String classId, @PathVariable("sectionId") String sectionId,
			@RequestPart(value = "assignmentMaster", required = false) AssignmentMaster assignmentMaster,
			@RequestPart(value = "assignmentAttachment", required = false) MultipartFile file,
			@RequestHeader String token) throws IOException {
		String filepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		String[] arrOfStr = assignmentMaster.getTempId().split(",");
		if (tokenService.tokenValidate(token)) {
			assignmentMaster.setMessage("Invalid token");
			logger.error("Invalid token");
			return assignmentMaster;
		} else {
//			if (file != null && !file.isEmpty()) {
//				StringJoiner sj = new StringJoiner(" , ");
//				byte[] bytes = file.getBytes();
//				filepath = Constant.ASSIGNMENT_UPLOAD_DIRECTORY + file.getOriginalFilename();
//				Path path = Paths.get(filepath);
//				Files.write(path, bytes);
//				sj.add(file.getOriginalFilename());
//				assignmentMaster.setAssignPath(filepath);
//			}
			if (file != null && !file.isEmpty()) {
				long currentTime=System.currentTimeMillis();
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = file.getBytes();
				filepath = Constant.ASSIGNMENT_UPLOAD_DIRECTORY + currentTime+file.getOriginalFilename();
				Path path = Paths.get(filepath);
				Files.write(path, bytes);
				sj.add(file.getOriginalFilename());
				assignmentMaster.setAssignPath(currentTime+file.getOriginalFilename());// setResourcePath(file);
			}
			
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentMaster.setUpdatedBy(user.getUserId());
			if (filepath != null && !filepath.isEmpty()) {
				assignmentMaster.setAssignPath(filepath);
			}
			// assignmentMaster.setAssignActive(true);
			assignmentMaster.setUpdatedTime(CommonUtils.getCurrentDateTime());
			// String assignmentId, String classId, String sectionId
			AssignmentMasterId assignmentMasterId = new AssignmentMasterId(assignmentId, arrOfStr[0], arrOfStr[1]);
			assignmentMaster.setAssignmentMasterId(assignmentMasterId);
			assignmentMasterService.update(assignmentMaster);
			assignmentMaster.setMessage("update successfully");
			return assignmentMaster;
		}
	}

	@PutMapping(value = "/updateAssignment/{assignmentId}/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody AssignmentMaster updateAssignmentMaster(@PathVariable("assignmentId") String assignmentId,
			@PathVariable("classId") String classId, @PathVariable("sectionId") String sectionId,
			@RequestBody AssignmentMaster assignmentMaster, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			assignmentMaster.setMessage("Invalid token");
			logger.error("Invalid token");
			return assignmentMaster;
		} else {
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentMaster.setUpdatedBy(user.getUserId());
			// assignmentMaster.setAssignActive(true);
			assignmentMaster.setUpdatedTime(CommonUtils.getCurrentDateTime());
			// String assignmentId, String classId, String sectionId
			AssignmentMasterId assignmentMasterId = new AssignmentMasterId(assignmentId, classId, sectionId);
			assignmentMaster.setAssignmentMasterId(assignmentMasterId);
			assignmentMasterService.update(assignmentMaster);
			assignmentMaster.setMessage("update successfully");
			return assignmentMaster;
		}
	}

	@PostMapping(value = "/TeacherEditAssignment", headers = "Accept=application/json")
	public @ResponseBody LuMessage editAssignmentMaster(@RequestBody AssignmentMaster assignmentMaster,
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
			return message;
		} else {
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentMaster.setUpdatedBy(user.getUserId());
			assignmentMaster.setUpdatedTime(CommonUtils.getCurrentDateTime());
			assignmentMasterService.update(assignmentMaster);
			message.setMessage("Assignment update successfully");
			return message;
		}
	}
	// @ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@PostMapping(value = "/StudentListAssignments", headers = "Accept=application/json")
	public @ResponseBody StudentAssignmentMasterDetailsDto getStudentAssignments(@RequestHeader String token,
			@RequestBody StudentClassSectionId studentClassSectionId) {
		Claims claims = TokenUtils.verifyToken(token);
		AssignmentMasterDetails assignmentMasterDetails = new AssignmentMasterDetails();
		AssignmentMasterDetailsDto assignmentMasterDetailsDto = new AssignmentMasterDetailsDto();
		StudentAssignmentMasterDetailsDto studentAssignmentMasterDetailsDto = new StudentAssignmentMasterDetailsDto();
		int pagenumber = 1;
		if (tokenService.tokenValidate(token)) {
			assignmentMasterDetails.setMessage("Invalid Token");
			studentAssignmentMasterDetailsDto.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			assignmentMasterDetails = assignmentMasterService.getClassSectionAssignments(pagenumber,
					studentClassSectionId.getClassSectionMasterId());
			assignmentMasterDetailsDto = assignmentControllerHelper.setAssignmentMasterDto(classSubjectService,
					subjectMasterService, classSectionMasterService, assignmentMasterDetails);
			studentAssignmentMasterDetailsDto = assignmentControllerHelper.addStatusAssignmentDetails(studentClassSectionId.getStudentId(),
					studentClassSectionId, assignmentMasterDetailsDto, assignmentEvaluationService);
		}
		return studentAssignmentMasterDetailsDto;
	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@GetMapping(value = "/getAssignmentMasterbyId/{assignmentId}/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody AssignmentMaster getAssignmentMaster(@PathVariable("assignmentId") String assignmentId,
			@PathVariable("classId") String classId, @PathVariable("sectionId") String sectionId,
			@RequestHeader String token) {
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		if (tokenService.tokenValidate(token)) {
			
			assignmentMaster.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			AssignmentMasterId assignmentMasterId = new AssignmentMasterId(assignmentId, classId, sectionId);
			assignmentMaster.setAssignmentMasterId(assignmentMasterId);
			assignmentMaster = assignmentMasterService.getElementById(assignmentMaster);
			assignmentMaster.setMessage("assignment Master details");
		}
		return assignmentMaster;
	}

	@PostMapping(value = "/GetAssignmentMasterbyId", headers = "Accept=application/json")
	public @ResponseBody AssignmentMasterDto getAssignmentMasterById(@RequestBody AssignmentMasterId assignmentMasterId,
			@RequestHeader String token) {
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		AssignmentMasterDto assignmentMasterDto = new AssignmentMasterDto();
		if (tokenService.tokenValidate(token)) {
			assignmentMasterDto.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			assignmentMaster.setAssignmentMasterId(assignmentMasterId);
			assignmentMaster = assignmentMasterService.getElementById(assignmentMaster);
			if(null==assignmentMaster) {
				assignmentMasterDto.setMessage("Record not found");
				return assignmentMasterDto;
			}
			assignmentMaster.setMessage("assignment Master details");
			assignmentMasterDto.setAssignmentAttachment(assignmentMaster.getAssignPath());
			assignmentMasterDto.setAssignmentCreatedDate(assignmentMaster.getAssignStartDate());
			assignmentMasterDto.setAssignmentDueDate(assignmentMaster.getAssignDueDate());
			assignmentMasterDto.setAssignmentDescription(assignmentMaster.getDescription());
			assignmentMasterDto.setId(assignmentMaster.getAssignmentMasterId().getAssignmentId());
			assignmentMasterDto.setAssignmentMark(assignmentMaster.getMaxMarks());
			assignmentMasterDto.setAssignmentSubject(assignmentMaster.getAssignmentSubject());
			assignmentMasterDto.setClassId(assignmentMaster.getAssignmentMasterId().getClassId());
			assignmentMasterDto.setSubjectId(assignmentMaster.getSubjectId());
			assignmentMasterDto.setSectionId(assignmentMaster.getAssignmentMasterId().getSectionId());
			assignmentMasterDto.setAssignmentType(assignmentMaster.getAssignType());
			assignmentMasterDto.setPageTypeName(assignmentMaster.getPageTypeName());
			SubjectMaster subjectMaster = new SubjectMaster();
			subjectMaster.setSubjectMasterId(new SubjectMasterId(assignmentMaster.getSubjectId(), ""));
			subjectMaster = subjectMasterService.getSubjectMasterById(subjectMaster);
			ClassSubject classSubject = new ClassSubject();
			System.out.println("getSubjectId"+assignmentMaster.getSubjectId());
			System.out.println("getAssociateTeacherId"+assignmentMaster.getAssociateTeacherId());
			classSubject.setClassSubjectId(new ClassSubjectId(assignmentMaster.getAssignmentMasterId().getClassId(),
					assignmentMaster.getAssignmentMasterId().getSectionId(),assignmentMaster.getSubjectId(),assignmentMaster.getAssociateTeacherId()));
			classSubject = classSubjectService.getElementById(classSubject);
			
			 assignmentMasterDto.setSubjectName(classSubject.getSubjectName());
			 ClassSectionMaster classSectionMaster = new ClassSectionMaster();
			 classSectionMaster.setClassSectionMasterId(new ClassSectionMasterId(assignmentMaster.getAssignmentMasterId().getClassId(),
						assignmentMaster.getAssignmentMasterId().getSectionId()));
			 classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
			 assignmentMasterDto.setClassName(classSectionMaster.getClassName());
		}
		return assignmentMasterDto;
	}

	/*
	 * @description ClassSubjectby details based on id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubjectbyId
	 * 
	 * @ Author Parnay
	 * @ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	 *
	 */
	@PostMapping(value = "/StudentViewAssignment", headers = "Accept=application/json")
	public @ResponseBody List<Map<String , String>> getAssignmentMaster(@RequestBody AssignmentMasterId assignmentMasterId,
			@RequestHeader String token) {
		TokenUtils.verifyToken(token);
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		//AssignmentMasterDto assignmentMasterDto = new AssignmentMasterDto();
		if (tokenService.tokenValidate(token)) {
			System.out.println("************************************tokennnnnnnnnnnnnnnnnnnnn");
			Map<String, String> mhm = new HashMap<String, String>();			
				logger.info("logs invalid token");
				mhm.put("Message", "Invalid Token");
				List<Map<String, String>> list= new ArrayList<>() ;
				list.add(mhm);	
				return list;
		} else {
			//assignmentMaster.setAssignmentMasterId(assignmentMasterId);
			arrayList = assignmentMasterService.getStudentView(assignmentMasterId);
//			assignmentMasterDto.setAssignmentAttachment(assignmentMaster.getAssignPath());
//			assignmentMasterDto.setId(assignmentMaster.getAssignmentMasterId().getAssignmentId());
//			assignmentMasterDto.setAssignmentDueDate(assignmentMaster.getAssignDueDate());
//			assignmentMasterDto.setAssignmentMark(assignmentMaster.getMaxMarks());
//			assignmentMaster.setMessage("assignment Master details");
		}
		return arrayList;
	}

	
	/*
	 * @description Get all Assignments
	 * 
	 * @param @token
	 * 
	 * @return List of ClassSubject
	 * 
	 * @ Author Parnay
	 * @ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	 */
	@GetMapping(value = "/getAllAssignments", headers = "Accept=application/json")
	public @ResponseBody AssignmentMasterDetailsDto getListAssignmentMaster(@RequestHeader String token) {
		AssignmentMasterDetails assignmentMasterDetails = new AssignmentMasterDetails();
		AssignmentMasterDetailsDto assignmentMasterDetailsDto = new AssignmentMasterDetailsDto();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);

		if (tokenService.tokenValidate(token)) {
			assignmentMasterDetails.setMessage("Invalid Token");
			assignmentMasterDetailsDto.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			assignmentMasterDetails = assignmentMasterService.list(1, "");
			assignmentMasterDetailsDto = assignmentControllerHelper.setAssignmentMasterDto(classSubjectService,
					subjectMasterService, classSectionMasterService, assignmentMasterDetails);
		}
		return assignmentMasterDetailsDto;
	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@PostMapping(value = "/ListUserAssignments", headers = "Accept=application/json")
	public @ResponseBody AssignmentMasterDetailsDto getUserAssignments(@RequestHeader String token,
			@RequestBody UserId userId) {
		AssignmentMasterDetails assignmentMasterDetails = new AssignmentMasterDetails();
		AssignmentMasterDetailsDto assignmentMasterDetailsDto = new AssignmentMasterDetailsDto();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);

		if (tokenService.tokenValidate(token)) {
			assignmentMasterDetails.setMessage("Invalid Token");
			assignmentMasterDetailsDto.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			assignmentMasterDetails = assignmentMasterService.getUserAssignments(1, userId.getUserId());
			assignmentMasterDetailsDto = assignmentControllerHelper.setAssignmentMasterDto(classSubjectService,
					subjectMasterService, classSectionMasterService, assignmentMasterDetails);
		}
		return assignmentMasterDetailsDto;
	}

	/*
	 * @description Delete by Id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubject
	 *
	 */
	@RequestMapping(value = "/assignmentInactive/{assignmentId}/{classId}/{sectionId}", method = RequestMethod.PUT)
	public @ResponseBody AssignmentMaster AssignmentInactive(@PathVariable("assignmentId") String assignmentId,
			@PathVariable("classId") String classId, @PathVariable("sectionId") String sectionId,
			@RequestHeader String token) {
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		if (tokenService.tokenValidate(token)) {
			assignmentMaster.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			AssignmentMasterId newAssignmentMasterId = new AssignmentMasterId(assignmentId, classId, sectionId);
			assignmentMaster.setAssignmentMasterId(newAssignmentMasterId);
			assignmentMaster = assignmentMasterService.getElementById(assignmentMaster);
			assignmentMaster.setAssignActive(false);
			assignmentMasterService.AssignmentInactive(assignmentMaster);
			assignmentMaster.setMessage("assignment inactive successfully");
		}
		return assignmentMaster;
	}

	@PostMapping(value = "/TeacherDeleteAssignment", headers = "Accept=application/json")
	public @ResponseBody LuMessage assignmentInactive(@RequestBody AssignmentMasterId assignmentMasterId,
			@RequestHeader String token) {
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			assignmentMaster.setAssignmentMasterId(assignmentMasterId);
			assignmentMaster = assignmentMasterService.getElementById(assignmentMaster);
			if(null==assignmentMaster) {
				message.setMessage("Please enter valid id");
				return message;
			}
			assignmentMaster.setAssignActive(false);
			assignmentMaster.setIsDeleted(1);
			assignmentMasterService.deleteAssignement(assignmentMaster);
			message.setMessage("Assignment deleted successfully");
		}
		return message;
	}

	@RequestMapping(value = "/assignmentActive/{assignmentId}/{classId}/{sectionId}", method = RequestMethod.PUT)
	public @ResponseBody AssignmentMaster AssignmentActive(@PathVariable("assignmentId") String assignmentId,
			@PathVariable("classId") String classId, @PathVariable("sectionId") String sectionId,
			@RequestHeader String token) {
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		if (tokenService.tokenValidate(token)) {
			assignmentMaster.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			AssignmentMasterId newAssignmentMasterId = new AssignmentMasterId(assignmentId, classId, sectionId);
			assignmentMaster.setAssignmentMasterId(newAssignmentMasterId);
			assignmentMaster = assignmentMasterService.getElementById(assignmentMaster);
			assignmentMaster.setAssignActive(true);
			assignmentMasterService.AssignmentActive(assignmentMaster);
			assignmentMaster.setMessage("assignment active successfully");
		}
		return assignmentMaster;
	}
	@GetMapping(value = "/listAllAssignments/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody AssignmentMasterDetailsDto listAllAssignmentMaster(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata,@RequestHeader String token) {
		AssignmentMasterDetails assignmentMasterDetails = new AssignmentMasterDetails();
		AssignmentMasterDetailsDto assignmentMasterDetailsDto = new AssignmentMasterDetailsDto();
		 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		 System.out.println("decodedString="+decodedString);
		if (tokenService.tokenValidate(token)) {
			assignmentMasterDetails.setMessage("Invalid Token");
			assignmentMasterDetailsDto.setMessage("Invalid Token");
            logger.error("Invalid token");
		   } else {
			assignmentMasterDetails = assignmentMasterService.list(id, decodedString);
			assignmentMasterDetailsDto = assignmentControllerHelper.setAssignmentMasterDto(classSubjectService,
	 		subjectMasterService, classSectionMasterService, assignmentMasterDetails);
		}
		return assignmentMasterDetailsDto;
	}
		@RequestMapping(value = "/deleteAssignmentMaster/{assignmentId}", method = RequestMethod.PUT, headers = "Accept=application/json")
		public @ResponseBody boolean deleteAssignmentMaster(@PathVariable("assignmentId") String assignmentId,
				@RequestHeader String token) {
			Claims claims = TokenUtils.verifyToken(token);
			AssignmentMaster assignmentMaster = new AssignmentMaster();
			if (tokenService.tokenValidate(token)) {
				assignmentMaster.setMessage("Invalid token");
			} else {
				System.out.println("assignmentId: "+assignmentId);
				assignmentMasterService.deleteAssignmentMaster(assignmentId);
			}
			return true;
		}
}
