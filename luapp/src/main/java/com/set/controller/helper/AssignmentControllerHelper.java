/**
 * 
 */
package com.set.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.set.dto.AssignmentEvaluationDetailsDto;
import com.set.dto.AssignmentEvaluationDto;
import com.set.dto.AssignmentMasterDetailsDto;
import com.set.dto.AssignmentMasterDto;
import com.set.dto.AssignmentSummaryDetailsDto;
import com.set.dto.AssignmentSummaryDto;
import com.set.dto.StudentAssignmentMasterDetailsDto;
import com.set.dto.StudentAssignmentMasterDto;
import com.set.dto.SubmittedAssignmentDto;
import com.set.dto.SubmittedAssignmentEvaluationDto;
import com.set.dto.SubmittedAssignmentPageDto;
import com.set.model.AssignmentEvaluation;
import com.set.model.AssignmentEvaluationDetails;
import com.set.model.AssignmentEvaluationId;
import com.set.model.AssignmentMaster;
import com.set.model.AssignmentMasterDetails;
import com.set.model.AssignmentMasterId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.AsstSubmissionDescription;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectId;
import com.set.model.StudentClassSectionId;
import com.set.model.StudentMaster;
import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterId;
import com.set.service.AssignmentEvaluationService;
import com.set.service.AssignmentMasterService;
import com.set.service.ClassSectionMasterService;
import com.set.service.ClassSubjectService;
import com.set.service.StudentMasterService;
import com.set.service.SubjectMasterService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;

/**
 * @author Administrator
 *
 */
@Component
public class AssignmentControllerHelper {

	/**
	 * 
	 */
	public AssignmentControllerHelper() {
	}

	public AssignmentEvaluationDto setAssignmentEvaluationDto(AssignmentEvaluation assignmentEvaluation,
			AssignmentMasterService assignmentMasterService, StudentMasterService studentMasterService,
			ClassSectionMasterService classSectionMasterService) {
		AssignmentEvaluationDto assignmentEvaluationDto = new AssignmentEvaluationDto();
		assignmentEvaluationDto.setId(assignmentEvaluation.getAssignmentEvaluationId().getAssignId());
		assignmentEvaluationDto.setAssignmentCreatedDate(assignmentEvaluation.getAssignStartDate());
		assignmentEvaluationDto.setAssignmentSubmissionMark(String.valueOf(assignmentEvaluation.getMarksObtained()));
		assignmentEvaluationDto.setAssignmentDueDate(assignmentEvaluation.getAssignEndDate());
		assignmentEvaluationDto.setStatus(assignmentEvaluation.getStatus());
		assignmentEvaluationDto.setAssignmentReviewComment(assignmentEvaluation.getReviewComments());

		StudentMaster studentMaster = studentMasterService
				.getStudentMasterByStudentId(assignmentEvaluation.getAssignmentEvaluationId().getStudentId());

		ClassSectionMaster classSectionMaster = new ClassSectionMaster();
		ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(studentMaster.getClassId(),
				studentMaster.getSectionId());
		classSectionMaster.setClassSectionMasterId(classSectionMasterId);
		classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
		if (classSectionMaster != null) {
			assignmentEvaluationDto.setClassName(classSectionMaster.getClassName());
		}
		AssignmentMasterId assignmentMasterId = new AssignmentMasterId(
				assignmentEvaluation.getAssignmentEvaluationId().getAssignId(),
				classSectionMaster.getClassSectionMasterId().getClassId(),
				classSectionMaster.getClassSectionMasterId().getSectionId());
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		assignmentMaster.setAssignmentMasterId(assignmentMasterId);
		assignmentMaster = assignmentMasterService.getElementById(assignmentMaster);
		assignmentEvaluationDto.setAssignmentSubject(assignmentMaster.getSubjectId());
		return assignmentEvaluationDto;
	}

	public AssignmentMasterDetailsDto setAssignmentMasterDto(ClassSubjectService classSubjectService,
			SubjectMasterService subjectMasterService, ClassSectionMasterService classSectionMasterService,
			AssignmentMasterDetails assignmentMasterDetails) {
		AssignmentMasterDetailsDto assignmentMasterDetailsDto = new AssignmentMasterDetailsDto();
		AssignmentMasterDto assignmentMasterDto = new AssignmentMasterDto();
		List<AssignmentMasterDto> assignmentMasterDtoList = new ArrayList<>();
		SubjectMaster subjectMaster = new SubjectMaster();
		for (AssignmentMaster eachAssignmentMaster : assignmentMasterDetails.getAssignmentMaster()) {
			assignmentMasterDto = new AssignmentMasterDto();
			assignmentMasterDto.setAssignmentAttachment(eachAssignmentMaster.getAssignPath());
			assignmentMasterDto.setAssignmentDescription(eachAssignmentMaster.getDescription());
			assignmentMasterDto.setAssignmentCreatedDate(eachAssignmentMaster.getAssignStartDate());
			assignmentMasterDto.setAssignmentDueDate(eachAssignmentMaster.getAssignDueDate());
			assignmentMasterDto.setPageTypeName(eachAssignmentMaster.getPageTypeName());
			assignmentMasterDto.setAssignmentMark(eachAssignmentMaster.getMaxMarks());
			assignmentMasterDto.setAssignmentType(eachAssignmentMaster.getAssignType());
			assignmentMasterDto.setId(eachAssignmentMaster.getAssignmentMasterId().getAssignmentId());
			assignmentMasterDto.setSubjectId(eachAssignmentMaster.getSubjectId());
			ClassSubject tempClassSubject = new ClassSubject();
			tempClassSubject
					.setClassSubjectId(new ClassSubjectId(eachAssignmentMaster.getAssignmentMasterId().getClassId(),
							eachAssignmentMaster.getAssignmentMasterId().getSectionId(),eachAssignmentMaster.getSubjectId(),eachAssignmentMaster.getAssociateTeacherId()));
			ClassSubject classSubject = classSubjectService.getElementById(tempClassSubject);
			if (classSubject != null) {
				assignmentMasterDto.setAssignmentSubject(classSubject.getSubjectName());
			}
			ClassSectionMaster classSectionMaster = new ClassSectionMaster();
			ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(
					eachAssignmentMaster.getAssignmentMasterId().getClassId(),
					eachAssignmentMaster.getAssignmentMasterId().getSectionId());
			classSectionMaster.setClassSectionMasterId(classSectionMasterId);
			classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
			if (classSectionMaster != null) {
				assignmentMasterDto.setClassName(classSectionMaster.getClassName());
				assignmentMasterDto.setClassId(classSectionMaster.getClassSectionMasterId().getClassId());
				assignmentMasterDto.setSectionId(classSectionMaster.getClassSectionMasterId().getSectionId());
			}
			subjectMaster = new SubjectMaster();
			subjectMaster.setSubjectMasterId(new SubjectMasterId(eachAssignmentMaster.getSubjectId(), Constant.COMMON_ISBN_NO));
			subjectMaster = subjectMasterService.getSubjectMasterById(subjectMaster);
			if (null != subjectMaster) {
				assignmentMasterDto.setSubjectName(subjectMaster.getSubjectName());
				assignmentMasterDto.setAssignmentSubject(eachAssignmentMaster.getAssignmentSubject());
			}
			assignmentMasterDtoList.add(assignmentMasterDto);
		}
		assignmentMasterDetailsDto.setAssignments(assignmentMasterDtoList);
		assignmentMasterDetailsDto.setCount(assignmentMasterDtoList.size());
		return assignmentMasterDetailsDto;
	}

	public AssignmentEvaluationDetailsDto setAssignmentEvaluationDto(ClassSectionMasterId classSectionMasterId,
			ClassSubjectService classSubjectService, SubjectMasterService subjectMasterService,
			ClassSectionMasterService classSectionMasterService,
			AssignmentEvaluationDetails assignmentEvaluationDetails) {
		AssignmentEvaluationDetailsDto assignmentEvalationDetailsDto = new AssignmentEvaluationDetailsDto();
		AssignmentEvaluationDto assignmentEvaluationDto = new AssignmentEvaluationDto();
		List<AssignmentEvaluationDto> assignmentMasterDtoList = new ArrayList<>();
		for (AssignmentEvaluation eachAssignmentEvaluation : assignmentEvaluationDetails.getAssignmentEvaluation()) {
			assignmentEvaluationDto = new AssignmentEvaluationDto();
			assignmentEvaluationDto.setAssignmentAttachment(eachAssignmentEvaluation.getAssignPath());
			assignmentEvaluationDto.setAssignmentCreatedDate(eachAssignmentEvaluation.getAssignStartDate());
			assignmentEvaluationDto.setAssignmentDueDate(eachAssignmentEvaluation.getAssignEndDate());
			assignmentEvaluationDto
					.setAssignmentSubmissionMark(String.valueOf(eachAssignmentEvaluation.getMarksObtained()));
			assignmentEvaluationDto.setId(eachAssignmentEvaluation.getAssignmentEvaluationId().getAssignId());
			assignmentEvaluationDto.setAssignmentReviewComment(eachAssignmentEvaluation.getReviewComments());
			assignmentEvaluationDto.setId(eachAssignmentEvaluation.getAssignmentEvaluationId().getAssignId());
			assignmentEvaluationDto.setNameOfReviewer(eachAssignmentEvaluation.getNameOfReviewer());
			assignmentEvaluationDto.setStatus(eachAssignmentEvaluation.getStatus());
			assignmentEvaluationDto.setClassName(getClassSection(classSectionMasterId.getClassId(),
					classSectionMasterId.getSectionId(), classSectionMasterService).getClassName());
			assignmentEvaluationDto.setSectionName(getClassSection(classSectionMasterId.getClassId(),
					classSectionMasterId.getSectionId(), classSectionMasterService).getSectionName());
			assignmentEvaluationDto.setStudentId(eachAssignmentEvaluation.getAssignmentEvaluationId().getStudentId());
			// assignmentEvaluationDto.setSubjectName(getSubjectName(eachAssignmentEvaluation.getS,
			// isbn, subjectMasterService));
			assignmentMasterDtoList.add(assignmentEvaluationDto);
		}
		// TODO assignmentEvalationDetailsDto.setAssignments(assignmentMasterDtoList);
		assignmentEvalationDetailsDto.setCount(assignmentMasterDtoList.size());
		return assignmentEvalationDetailsDto;
	}

	public StudentAssignmentMasterDetailsDto addStatusAssignmentDetails(
			String studentId,
			StudentClassSectionId studentClassSectionId, 
			AssignmentMasterDetailsDto assignmentMasterDetailsDto,
			AssignmentEvaluationService assignmentEvaluationService
			) {
		StudentAssignmentMasterDetailsDto studentAssignmentMasterDetailsDto = new StudentAssignmentMasterDetailsDto();
		StudentAssignmentMasterDto studentAssignmentMasterDto;
		AssignmentEvaluation assignmentEvaluation = new AssignmentEvaluation();
		AsstSubmissionDescription asstSubmissionDescription = new AsstSubmissionDescription();
		List<StudentAssignmentMasterDto> studentAssignmentMasterDtoList = new ArrayList<StudentAssignmentMasterDto>();
		for (AssignmentMasterDto assignmentMasterDto : assignmentMasterDetailsDto.getAssignments()) {
			studentAssignmentMasterDto = new StudentAssignmentMasterDto();
			studentAssignmentMasterDto
					.setAssignmentAttachment("");
			studentAssignmentMasterDto.setAssignmentCreatedDate(assignmentMasterDto.getAssignmentCreatedDate());
			studentAssignmentMasterDto
					.setAssignmentDescription(CommonUtils.checkNull(assignmentMasterDto.getAssignmentDescription()));
			studentAssignmentMasterDto.setAssignmentDueDate(assignmentMasterDto.getAssignmentDueDate());
			studentAssignmentMasterDto.setAssignmentMark(assignmentMasterDto.getAssignmentMark());
			studentAssignmentMasterDto.setAssignmentSubject(assignmentMasterDto.getAssignmentSubject());
			studentAssignmentMasterDto.setAssignmentType(assignmentMasterDto.getAssignmentType());	
			studentAssignmentMasterDto.setClassId(assignmentMasterDto.getClassId());
			studentAssignmentMasterDto.setClassName(assignmentMasterDto.getClassName());
			studentAssignmentMasterDto.setId(assignmentMasterDto.getId());
			studentAssignmentMasterDto.setSubjectId(assignmentMasterDto.getSubjectId());
			studentAssignmentMasterDto.setSubjectName(assignmentMasterDto.getSubjectName());
			studentAssignmentMasterDto.setPageTypeName(assignmentMasterDto.getPageTypeName());
			studentAssignmentMasterDto.setUserId(CommonUtils.checkNull(studentClassSectionId.getStudentId()));
			String status = Constant.STATUS_NA;
			assignmentEvaluation = assignmentEvaluationService
					.getAssignmentEvaluaitonById(new AssignmentEvaluationId(studentId, assignmentMasterDto.getId()));
			if (null != assignmentEvaluation) {
				System.out.println("assignmentEvaluation.getStatus()="+assignmentEvaluation.getStatus());
				if (assignmentEvaluation.getStatus().equals(Constant.STATUS_NOT_SUBMITTED)) {
					status = Constant.STATUS_NA;
				} else if (assignmentEvaluation.getStatus().equals(Constant.STATUS_SUBMITTED)) {
					status = Constant.STATUS_SUBMITTED_LABEL;
				} else if (assignmentEvaluation.getStatus().equals(Constant.STATUS_REJECTED)) {
					status = Constant.STATUS_REJECTED_LABEL;
				}else if (assignmentEvaluation.getStatus().equals(Constant.STATUS_REDO)) {
					status = Constant.STATUS_REDO_LABEL;
				}else if (assignmentEvaluation.getStatus().equals(Constant.STATUS_APPROVED)) {
					status = Constant.STATUS_APPROVED_LABEL;
				}
				if(assignmentEvaluation.getAssignPath()!=null) {
				studentAssignmentMasterDto.setAssignmentAttachment(assignmentEvaluation.getAssignPath());
				}
			}
			
			
			studentAssignmentMasterDto.setStatus(status);
			//studentAssignmentMasterDto.setAssignmentAttachment(assignmentEvaluation.getAssignPath());
			AssignmentPageId assignmentPageId = new AssignmentPageId();
			assignmentPageId.setAssignmentId(assignmentMasterDto.getId());
			assignmentPageId.setStudentId(studentClassSectionId.getStudentId());
			AssignmentPagesDetails assignmentPagesDetails = assignmentEvaluationService
					.getAssignmentPages(assignmentPageId);
			List<AsstSubmissionDescription> asstSubmissionDescriptionList = new ArrayList();
			for(AssignmentPages eachAssignmentPage : assignmentPagesDetails.getAssignmentPages()) {
				asstSubmissionDescription = new AsstSubmissionDescription();
				asstSubmissionDescription.setPageNumber(eachAssignmentPage.getAssignmentPageId().getPageNo());
				asstSubmissionDescription.setAssignmentSubmissionDescription(eachAssignmentPage.getPagePath());
				asstSubmissionDescriptionList.add(asstSubmissionDescription);
			}
			studentAssignmentMasterDto.setAssignemnts(asstSubmissionDescriptionList);
			studentAssignmentMasterDto.setSectionId(studentClassSectionId.getClassSectionMasterId().getSectionId());
			studentAssignmentMasterDtoList.add(studentAssignmentMasterDto);
		}
		studentAssignmentMasterDetailsDto.setAssignments(studentAssignmentMasterDtoList);
		studentAssignmentMasterDetailsDto.setCount(studentAssignmentMasterDtoList.size());
		return studentAssignmentMasterDetailsDto;
	}

	public ClassSectionMaster getClassSection(String classId, String sectionId,
			ClassSectionMasterService classSectionMasterService) {
		ClassSectionMaster classSectionMaster = new ClassSectionMaster();
		ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(classId, sectionId);
		classSectionMaster.setClassSectionMasterId(classSectionMasterId);
		classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
		if (classSectionMaster != null) {
			return classSectionMaster;
		} else {
			return null;
		}
	}

//	public ClassSubject getClassSubject(String classId, String sectionId, ClassSubjectService classSubjectService) {
//		ClassSubject tempClassSubject = new ClassSubject();
//		tempClassSubject.setClassSubjectId(new ClassSubjectId(classId, sectionId));
//		ClassSubject classSubject = classSubjectService.getElementById(tempClassSubject);
//		if (classSubject != null) {
//			return classSubject;
//		} else {
//			return null;
//		}
//	}

	public String getSubjectName(String subjectId, String isbn, SubjectMasterService subjectMasterService) {
		SubjectMaster subjectMaster = new SubjectMaster();
		subjectMaster.setSubjectMasterId(new SubjectMasterId(subjectId, isbn));
		subjectMaster = subjectMasterService.getSubjectMasterById(subjectMaster);
		if (null != subjectMaster) {
			return subjectMaster.getSubjectName();
		} else {
			return null;
		}
	}

	public AssignmentSummaryDetailsDto setAssignmentSummaryDto(AssignmentMasterId assignmentMasterId,
			AssignmentEvaluationDetails assignmentEvaluationDetails, AssignmentMasterService assignmentMasterService,
			StudentMasterService studentMasterService, ClassSectionMasterService classSectionMasterService,
			AssignmentEvaluationService assignmentEvaluationService) {
		int submittedCount = 0;
		int redoCount = 0;
		int approvedCount = 0;
		int rejectedCount = 0;
		AssignmentSummaryDetailsDto assignmentSummaryDetailsDto = new AssignmentSummaryDetailsDto();
		AssignmentSummaryDto assignmentSummaryDto = new AssignmentSummaryDto();
		List<SubmittedAssignmentDto> studentSubmittedAssignments = new ArrayList<>();
		SubmittedAssignmentDto submittedAssignmentDto = new SubmittedAssignmentDto();
		AssignmentMaster assignmentMaster = new AssignmentMaster();
		SubmittedAssignmentPageDto submittedAssignmentPageDto = new SubmittedAssignmentPageDto();
		List<SubmittedAssignmentPageDto> submittedAssignmentPageDtoList = new ArrayList<>();
		SubmittedAssignmentEvaluationDto submittedAssignmentEvaluationDto = new SubmittedAssignmentEvaluationDto();
		StudentMaster studentMaster = new StudentMaster();
		for (AssignmentEvaluation eachAssignmentEvaluation : assignmentEvaluationDetails.getAssignmentEvaluation()) {
			submittedAssignmentDto = new SubmittedAssignmentDto();
			submittedAssignmentPageDtoList =  new ArrayList<>();
			submittedAssignmentEvaluationDto = new SubmittedAssignmentEvaluationDto();
			submittedAssignmentEvaluationDto
					.setAssignmentId(eachAssignmentEvaluation.getAssignmentEvaluationId().getAssignId());
			submittedAssignmentEvaluationDto.setAssignmentDueDate(eachAssignmentEvaluation.getAssignEndDate());
			submittedAssignmentEvaluationDto.setAssignmentReviewComment(eachAssignmentEvaluation.getReviewComments());
			submittedAssignmentEvaluationDto
					.setAssignmentSubmissionAttachment(eachAssignmentEvaluation.getAssignPath());
			System.out.println("path setAssignmentSummaryDto: "+submittedAssignmentEvaluationDto.getAssignmentSubmissionAttachment());
			submittedAssignmentEvaluationDto.setAssignmentSubmittedDate(eachAssignmentEvaluation.getAssignStartDate());
			if(eachAssignmentEvaluation.getStatus().equals(Constant.STATUS_SUBMITTED)) {
				submittedCount++;
			}else if(eachAssignmentEvaluation.getStatus().equals(Constant.STATUS_REJECTED)) {
				rejectedCount++;
			}else if(eachAssignmentEvaluation.getStatus().equals(Constant.STATUS_APPROVED)) {
				approvedCount++;
			}else if(eachAssignmentEvaluation.getStatus().equals(Constant.STATUS_REDO)) {
				redoCount++;
			}
			assignmentMaster = assignmentMasterService.getAssignmentById(assignmentMasterId);
			submittedAssignmentEvaluationDto.setAssignmentDueDate(assignmentMaster.getAssignDueDate());
			submittedAssignmentEvaluationDto.setStatus(eachAssignmentEvaluation.getStatus());
			submittedAssignmentEvaluationDto.setClassId(assignmentMaster.getAssignmentMasterId().getClassId());
			submittedAssignmentEvaluationDto.setClassId(assignmentMaster.getAssignmentMasterId().getSectionId());
			submittedAssignmentEvaluationDto
					.setAssignmentSubmissionMark(String.valueOf(eachAssignmentEvaluation.getMarksObtained()));
			submittedAssignmentEvaluationDto.setId(assignmentMaster.getAssignmentMasterId().getAssignmentId());
			submittedAssignmentEvaluationDto.setSectionId(assignmentMaster.getAssignmentMasterId().getSectionId());
			studentMaster = studentMasterService.getStudentMasterByStudentId(eachAssignmentEvaluation.getAssignmentEvaluationId().getStudentId());
			if(studentMaster != null) {
				submittedAssignmentEvaluationDto.setStudentFirstName(studentMaster.getFirstName());
				submittedAssignmentEvaluationDto.setStudentMiddleName(studentMaster.getMiddleName());
				submittedAssignmentEvaluationDto.setStudentLastName(studentMaster.getLastName());
				submittedAssignmentEvaluationDto.setStudentId(studentMaster.getStudentId());
				submittedAssignmentEvaluationDto.setStudentProfileImage(studentMaster.getProfilePicture());
			}
			submittedAssignmentEvaluationDto.setAssignmentSubject(assignmentMaster.getAssignmentSubject());
			AssignmentPageId assignmentPageId = new AssignmentPageId();
			assignmentPageId.setAssignmentId(eachAssignmentEvaluation.getAssignmentEvaluationId().getAssignId());
			assignmentPageId.setStudentId(eachAssignmentEvaluation.getAssignmentEvaluationId().getStudentId());
			AssignmentPagesDetails assignmentPagesDetails = assignmentEvaluationService
					.getAssignmentPages(assignmentPageId);
			for (AssignmentPages eachAssignmentPage : assignmentPagesDetails.getAssignmentPages()) {
				submittedAssignmentPageDto = new SubmittedAssignmentPageDto();
				submittedAssignmentPageDto.setPageNumber(eachAssignmentPage.getAssignmentPageId().getPageNo());
				submittedAssignmentPageDto.setAssignmentSubmissionDescription(eachAssignmentPage.getPagePath());
				submittedAssignmentPageDtoList.add(submittedAssignmentPageDto);
			}
			submittedAssignmentDto.setPages(submittedAssignmentPageDtoList);
			submittedAssignmentDto.setAssignmentDetail(submittedAssignmentEvaluationDto);
			studentSubmittedAssignments.add(submittedAssignmentDto);
		}
		assignmentSummaryDto.setApprovedAssignmentsCount(String.valueOf(approvedCount));
		assignmentSummaryDto.setRedoAssignmentsCount(String.valueOf(redoCount));
		assignmentSummaryDto.setRejectedAssignmentsCount(String.valueOf(rejectedCount));
		assignmentSummaryDto.setSubmittedAssignmentsCount(String.valueOf(submittedCount));
		
		assignmentSummaryDto.setStudentSubmittedAssignments(studentSubmittedAssignments);
		assignmentSummaryDetailsDto.setAssignments(assignmentSummaryDto);
		return assignmentSummaryDetailsDto;
	}
}
