package com.set.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.controller.helper.AssignmentControllerHelper;
import com.set.dto.AssignmentEvaluationDto;
import com.set.dto.AssignmentReviewDto;
import com.set.dto.AssignmentSummaryDetailsDto;
import com.set.dto.AssignmentSummaryDto;
import com.set.model.AssignmentEvaluation;
import com.set.model.AssignmentEvaluationDetails;
import com.set.model.AssignmentEvaluationId;
import com.set.model.AssignmentMasterId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.AssignmentSubmission;
import com.set.model.AsstSubmissionDescription;
import com.set.model.LuMessage;
import com.set.model.Page;
import com.set.model.StudentNotesId;
import com.set.model.User;
import com.set.service.AssignmentEvaluationService;
import com.set.service.AssignmentMasterService;
import com.set.service.ClassSectionMasterService;
import com.set.service.StudentMasterService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class AssignmentEvaluationController {
	@Autowired
	private AssignmentEvaluationService assignmentEvaluationService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	AssignmentControllerHelper assignmentControllerHelper;

	@Autowired
	private UserService userService;
	@Autowired
	private AssignmentMasterService assignmentMasterService;
	@Autowired
	private StudentMasterService studentMasterService;
	@Autowired
	private ClassSectionMasterService classSectionMasterService;
	private Logger logger = Logger.getLogger("AssignmentEvaluationController.class");

	/*
	 * @description addClassSubject with details
	 * 
	 * @param @token,ClassSubject
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 */
	@PostMapping(value = "/addAssignmentEvaluation", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addAssignmentEvaluation(@RequestBody AssignmentSubmission assignmentSubmission,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
		} else {
			AssignmentEvaluation assignmentEvaluation = new AssignmentEvaluation();
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentEvaluation.setInsertedBy(user.getUserId());
			assignmentEvaluation.setSoftDelete(true);
			assignmentEvaluation.setInsertedTime(CommonUtils.getCurrentDateTime());
			AssignmentEvaluationId assignmentEvaluationId = new AssignmentEvaluationId(
					assignmentSubmission.getStudentId(), assignmentSubmission.getAssignmentId());
			assignmentEvaluation.setAssignmentEvaluationId(assignmentEvaluationId);
			assignmentEvaluation.setAssignPath(assignmentSubmission.getAssignmentSubmissionAttachment());
			// assignmentEvaluation.setForUseField2(assignmentSubmission.getAssignmentSubmissionDescription());
			assignmentEvaluationService.save(assignmentEvaluation);
			message.setMessage("Insert successfully");
		}
		return message;
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
	@PutMapping(value = "/addAssignmentEvaluation/{studentId}/{assignId}", headers = "Accept=application/json")
	public @ResponseBody AssignmentEvaluation updateAssignmentEvaluation(@PathVariable("studentId") String studentId,
			@PathVariable("assignId") String assignId, @RequestBody AssignmentEvaluation assignmentEvaluation,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			assignmentEvaluation.setMessage("Invalid token");
			return assignmentEvaluation;
		} else {
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentEvaluation.setUpdatedBy(user.getUserId());
			assignmentEvaluation.setUpdatedTime(CommonUtils.getCurrentDateTime());
			AssignmentEvaluationId assignmentEvaluationId = new AssignmentEvaluationId(studentId, assignId);
			assignmentEvaluation.setAssignmentEvaluationId(assignmentEvaluationId);
			assignmentEvaluationService.update(assignmentEvaluation);
			assignmentEvaluation.setMessage("Update successfully");
			return assignmentEvaluation;
		}
	}

	@PostMapping(value = "/AddAssignmentPage", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addAssignmentPage(@RequestBody AssignmentPages assignmentPage,
			@RequestHeader String token) {
		LuMessage luMessage = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (!tokenService.tokenValidate(token)) {
			luMessage.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			AssignmentPages assignmentPages = new AssignmentPages();
			User user = userService.retrieveFromId(claims.getSubject());
			assignmentPages.setInsertedBy(user.getUserId());
			assignmentPages.setIsDeleted(0);
			assignmentPages.setInsertedTime(CommonUtils.getCurrentDateTime());
			assignmentPages.setUpdatedTime(CommonUtils.getCurrentDateTime());
			assignmentPages.setUpdatedBy(user.getUserId());
			assignmentEvaluationService.savePage(assignmentPages);
			luMessage.setMessage("Assignment Page Inserted successfully");
		}
		return luMessage;
	}

	@PostMapping(value = "/ListAssignmentPages", headers = "Accept=application/json")
	public @ResponseBody LuMessage assignmentPages(@RequestBody AssignmentPageId assignmentPageId,
			@RequestHeader String token) {

		LuMessage message = new LuMessage();
		int pagenumber = 1;
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid Token");
		} else {
			new AssignmentPagesDetails();
			assignmentEvaluationService.getPagesByAssignmentId(pagenumber, assignmentPageId);
			message.setMessage("Assignment deleted successfully");
		}
		return message;
	}

	@PostMapping(value = "/GetAssignmentPages", headers = "Accept=application/json")
	public @ResponseBody AssignmentPagesDetails getAssignmentPages(@RequestBody AssignmentPageId assignmentPageId,
			@RequestHeader String token) {
		AssignmentPagesDetails assignmentPagesDetails = new AssignmentPagesDetails();
		if (tokenService.tokenValidate(token)) {
			assignmentPagesDetails.setMessage("Invalid Token");
		} else {
			assignmentPagesDetails = assignmentEvaluationService.getAssignmentPages(assignmentPageId);
			assignmentPagesDetails.setMessage("Pages fetched Successfully");
		}
		return assignmentPagesDetails;
	}

	@PostMapping(value = "/StudentSubmitAssignment", headers = "Accept=application/json")
	public @ResponseBody AssignmentEvaluation submitAssignmentEvaluation(@RequestBody AssignmentSubmission assignmentSubmission,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		AssignmentEvaluation assignmentEvaluation = new AssignmentEvaluation();
		if (tokenService.tokenValidate(token)) {
			assignmentEvaluation.setMessage("Invalide token");
			return assignmentEvaluation;
		} else {
			User user = userService.retrieveFromId(claims.getSubject());
			AssignmentPages assignmentPages = new AssignmentPages();
			AssignmentPageId assignmentPageId = new AssignmentPageId();
			assignmentPageId.setAssignmentId(assignmentSubmission.getAssignmentId());
			assignmentPageId.setStudentId(assignmentSubmission.getStudentId());
			assignmentEvaluation.setAssignmentEvaluationId(new AssignmentEvaluationId(
					assignmentSubmission.getStudentId(), assignmentSubmission.getAssignmentId()));
			assignmentEvaluation.setUpdatedBy(user.getUserId());
			assignmentEvaluation.setAssignStartDate(CommonUtils.getCurrentDateTime());
			assignmentEvaluation.setUpdatedTime(CommonUtils.getCurrentDateTime());
			assignmentEvaluation.setStatus(Constant.STATUS_SUBMITTED);
			assignmentEvaluation.setInsertedBy(user.getUserId());
			assignmentEvaluation.setAssignPath(assignmentSubmission.getAssignmentSubmissionAttachment());
			int myext=assignmentSubmission.getExtension().length();
			if(myext>0) {
			String base64Imageatt = assignmentSubmission.getSubmissionAttachment();		
	    	String newfileName = "attassignment"+System.currentTimeMillis();
	    	String attextension = assignmentSubmission.getExtension();
	    	String attpathFile = Constant.ASSIGNMENT_UPLOAD_DIRECTORY+newfileName+"."+attextension; 
	    	ImageUpload.decoder(base64Imageatt, attpathFile);
	    	assignmentEvaluation.setAssignPath(newfileName+"."+attextension);
			}else {
				assignmentSubmission.setSubmissionAttachment("");
			}
			
			assignmentEvaluationService.save(assignmentEvaluation);
			AsstSubmissionDescription e[] = assignmentSubmission.getAsstSubmissionDescription();
			for (int i = 0; i <= e.length - 1; i++) {
				assignmentPages = new AssignmentPages();
				String pageNo = e[i].getPageNumber();
				assignmentPageId.setPageNo(pageNo);
				assignmentPages.setAssignmentPageId(assignmentPageId);
				String base64Image = e[i].getAssignmentImageUrl();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		    	String fileName = "assignmentPage"+System.currentTimeMillis();
		    	String extension = "png";
		    	String pathFile = Constant.ASSIGNMENT_UPLOAD_DIRECTORY+fileName+"."+extension; 
		    	ImageUpload.decoder(base64Image, pathFile);
				assignmentPages.setPagePath(fileName+"."+extension);
				assignmentPages.setInsertedBy(user.getUserId());
				assignmentPages.setInsertedTime(CommonUtils.getCurrentDateTime());
				assignmentPages.setUpdatedBy(user.getUserId());
				assignmentPages.setUpdatedTime(CommonUtils.getCurrentDateTime());
				assignmentPages.setIsDeleted(0);
				assignmentEvaluationService.savePage(assignmentPages);
			}

			assignmentEvaluation.setMessage("Assignment submitted successfully");
			return assignmentEvaluation;
		}
	}

//	 else {
//			Page e[] = studentNotes.getPage();
//			for (int i = 0; i <= e.length - 1; i++) {
//				String notesId = studentNotes.getNotesId();
//				int pageNo = e[i].getPageNo();
//				String base64Image = e[i].getPagePath();
//				String studentId = studentNotes.getStudentId();
//				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//				String fileName = "notesImage" + System.currentTimeMillis();
//				String extension = "png";
//				String pathFile = Constant.NOTES_UPLOAD_DIRECTORY + fileName + "." + extension;
//				ImageUpload.decoder(base64Image, pathFile);
//				tempStudentNotes.setPagePath(fileName.replace(" ", "") + "." + extension);
//				//tempStudentNotes.setNotesTitle(studentNotes.getNotesTitle());
//				//tempStudentNotes.setNotesDescription(studentNotes.getNotesDescription());
//				tempStudentNotes.setPageCreateDate(timestamp);
//				StudentNotesId newStudentNotesId = new StudentNotesId(notesId, pageNo, studentId);
//				tempStudentNotes.setInsertedTime(timestamp);
//				tempStudentNotes.setUpdatedTime(timestamp);
//				tempStudentNotes.setStatus('1');
//				studentNotesService.deleteStudentNotes(newStudentNotesId);
//				tempStudentNotes.setStudentNotesId(newStudentNotesId);
//				studentNotesService.save(tempStudentNotes);
//			}
//			message.setMessage("Inserted successfully");
//			message.setCode("200");
//		}
	
	
	@PostMapping(value = "/TeacherReviewAssignment", headers = "Accept=application/json")
	public @ResponseBody LuMessage reviewAssignmentEvaluation(@RequestBody AssignmentReviewDto assignmentReviewDto,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
		} else {
			User user = userService.retrieveFromId(claims.getSubject());
			AssignmentEvaluation assignmentEvaluation = new AssignmentEvaluation();
			assignmentEvaluation.setUpdatedBy(user.getUserId());
			assignmentEvaluation.setUpdatedTime(CommonUtils.getCurrentDateTime());
			AssignmentEvaluationId assignmentEvaluationId = new AssignmentEvaluationId(
					assignmentReviewDto.getStudentId(), assignmentReviewDto.getAssignmentId());
			assignmentEvaluation.setAssignmentEvaluationId(assignmentEvaluationId);
			assignmentEvaluation.setStatus(assignmentReviewDto.getStatus());
			assignmentEvaluation.setMarksObtained(Integer.parseInt(assignmentReviewDto.getAssignmentSubmissionMark()));
			assignmentEvaluationService.update(assignmentEvaluation);
			message.setMessage("Update successfully");
		}
		return message;
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
	@GetMapping(value = "/getAssignmentEvaluationbyId/{studentId}/{assignId}", headers = "Accept=application/json")
	public @ResponseBody AssignmentEvaluationDto getClassSubject(@PathVariable("studentId") String studentId,
			@PathVariable("assignId") String assignId, @RequestHeader String token) {
		AssignmentEvaluation assignmentEvaluation = new AssignmentEvaluation();
		AssignmentEvaluationDto assignmentEvaluationDto = new AssignmentEvaluationDto();

		if (tokenService.tokenValidate(token)) {
			logger.error("Invalid token");
			assignmentEvaluationDto.setMessage("Invalide token");
		} else {
			AssignmentEvaluationId assignmentEvaluationId = new AssignmentEvaluationId(studentId, assignId);
			assignmentEvaluation.setAssignmentEvaluationId(assignmentEvaluationId);
			assignmentEvaluation = assignmentEvaluationService.getElementById(assignmentEvaluation);
			assignmentEvaluationDto = assignmentControllerHelper.setAssignmentEvaluationDto(assignmentEvaluation,
					assignmentMasterService, studentMasterService, classSectionMasterService);
			assignmentEvaluation.setMessage("Assignment Evaluation details");
		}
		return assignmentEvaluationDto;
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
	@GetMapping(value = "/SubmittedAssignments", headers = "Accept=application/json")
	public @ResponseBody AssignmentEvaluationDetails getListAssignmentEvaluation(@RequestHeader String token) {
		AssignmentEvaluationDetails assignmentEvaluationDetails = new AssignmentEvaluationDetails();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			assignmentEvaluationDetails.setMessage("Invalid Token");
		} else {
			assignmentEvaluationDetails = assignmentEvaluationService.list(1, "");
			assignmentEvaluationDetails.setMessage("Listing Submitted Assignments");
		}
		return assignmentEvaluationDetails;
	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@PostMapping(value = "/TeacherSubmittedStudentAssignmentList", headers = "Accept=application/json")
	public @ResponseBody AssignmentSummaryDetailsDto getSubmittedAssignmentsList(@RequestHeader String token,
			@RequestBody AssignmentMasterId assignmentMasterId) {
		AssignmentSummaryDetailsDto assignmentSummaryDetailsDto = new AssignmentSummaryDetailsDto();
		AssignmentEvaluationId assignmentEvaluationId = new AssignmentEvaluationId("",
				assignmentMasterId.getAssignmentId());
		if (!tokenService.tokenValidate(token)) {
			AssignmentEvaluationDetails assignmentEvaluationDetails = assignmentEvaluationService
					.studentSubmittedAssignments(assignmentEvaluationId);
			assignmentSummaryDetailsDto = assignmentControllerHelper.setAssignmentSummaryDto(assignmentMasterId, assignmentEvaluationDetails,
					assignmentMasterService, studentMasterService, classSectionMasterService, assignmentEvaluationService);
			assignmentSummaryDetailsDto.setMessage("Student Listing Assignment");
		} else {
			System.out.println("Invalid Token");
			assignmentSummaryDetailsDto.setMessage("Invalid Token");
		}
		return assignmentSummaryDetailsDto;
	}

	@GetMapping(value = "/getListAssignmentEvaluation", headers = "Accept=application/json")
	public @ResponseBody AssignmentEvaluationDetails getAllAssignmentEvaluation(@RequestHeader String token) {
		AssignmentEvaluationDetails assignmentEvaluationDetails = new AssignmentEvaluationDetails();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			assignmentEvaluationDetails.setMessage("Invalid Token");
		} else {
			assignmentEvaluationDetails = assignmentEvaluationService.list(1, "");
		}
		return assignmentEvaluationDetails;
	}

	/*
	 * @description Delete by Id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubject
	 *
	 */
	@RequestMapping(value = "/deleteAssignmentEvaluation/{studentId}/{assignId}", method = RequestMethod.PUT)
	public @ResponseBody AssignmentEvaluation deleteAssignmentEvaluationById(
			@PathVariable("studentId") String studentId, @PathVariable("assignId") String assignId,
			@RequestHeader String token) {
		AssignmentEvaluation assignmentEvaluation = new AssignmentEvaluation();
		if (tokenService.tokenValidate(token)) {
			assignmentEvaluation.setMessage("Invalid Token");
		} else {
			AssignmentEvaluationId newClassSubjectId = new AssignmentEvaluationId(studentId, assignId);
			assignmentEvaluation.setAssignmentEvaluationId(newClassSubjectId);
			assignmentEvaluation = assignmentEvaluationService.getElementById(assignmentEvaluation);
			assignmentEvaluation.setSoftDelete(true);// setIsSoftDelete(true);//setIsSoftDelete(true);//setIsSoftDelete(true);
			assignmentEvaluation.setMessage("Deleted successfully");
			assignmentEvaluationService.deleteElementById(assignmentEvaluation);
		}
		return assignmentEvaluation;
	}
}
