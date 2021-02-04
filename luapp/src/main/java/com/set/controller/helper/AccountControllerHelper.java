/**
 * 
 */
package com.set.controller.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.set.dto.ClassSubjectDataDto;
import com.set.dto.EmployeeMasterDetailsDto;
import com.set.dto.QualificationDataDto;
import com.set.dto.SectionDataDto;
import com.set.dto.StudentMasterDetailsDto;
import com.set.dto.SubjectIdDto;
import com.set.dto.UserSubjectDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetails;
import com.set.model.EmployeeMaster;
import com.set.model.StudentEnrollment;
import com.set.model.StudentMaster;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.model.UserToken;
import com.set.service.ClassSectionMasterService;
import com.set.service.ClassSubjectService;
import com.set.service.EmployeeMasterService;
import com.set.service.StudentMasterService;
import com.set.service.TokenService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

/**
 * @author Administrator
 *
 */
@Component
public class AccountControllerHelper {
	private Logger logger = Logger.getLogger("AccountControllerHelper.class");
	Date today = new Date();
	Date tomorrow = new Date(today.getTime() + (5000 * 60 * 60 * 24 * 30));

	/**
	 * 
	 */
	public AccountControllerHelper() {
	}

	/**
	 * Helper method to generate token for the user logged in user
	 * 
	 * @param user
	 * @return String
	 */
	public String genarateUserToken(User user) {
		String jwtToken = TokenUtils.generateToken(user.getEmailId(), tomorrow);
		return jwtToken;
	}

	/**
	 * Helper method to get UserToken object fo the loged in user
	 * 
	 * @param user
	 * @return UserToken
	 */
	public UserToken getUserToken(User user) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		UserToken userToken = new UserToken();
		userToken.setIsdeleted(false);
		userToken.setToken(genarateUserToken(user));
		userToken.setExpireat(tomorrow);
		userToken.setCreatedat(timestamp);
		return userToken;
	}

	/**
	 * Helper method to set employee(eg. Teacher) master details while login
	 * 
	 * @param employeeMaster
	 * @param tokenService
	 * @param employeeMasterService
	 * @param userRegistration
	 * @param user
	 * @param teacherSubjects
	 * @param classSectionMasterService
	 * @return EmployeeMasterDetailsDto
	 */
	public EmployeeMasterDetailsDto setEmployeeMasterDto(EmployeeMaster employeeMaster, TokenService tokenService,
			EmployeeMasterService employeeMasterService, UserRegistration userRegistration, User user,
			List<ClassSubject> teacherSubjects, ClassSectionMasterService classSectionMasterService) {
		EmployeeMasterDetailsDto employeeMasterDetailsDto = new EmployeeMasterDetailsDto();
		List<ClassSubjectDataDto> classSubjectDataList = new ArrayList<>();
		ClassSubjectDataDto classSubjectDataDto = new ClassSubjectDataDto();
		SectionDataDto sectionDataDto = new SectionDataDto();
		List<SectionDataDto> sectiondata = new ArrayList<>();
		String currentCsId = "";

		for (ClassSubject eachClassSubject : teacherSubjects) {
			//currentCsId = "";
			
			classSubjectDataDto = new ClassSubjectDataDto();
			sectionDataDto = new SectionDataDto();
			SubjectIdDto subjectIdDto = new SubjectIdDto();
			List<SubjectIdDto> subjectresult = new ArrayList<>();
			if(!eachClassSubject.getClassSubjectId().getClassId().equals(currentCsId)) {
				classSubjectDataList.add(classSubjectDataDto);
				currentCsId = eachClassSubject.getClassSubjectId().getClassId();
				ClassSectionMaster classSectionMaster = new ClassSectionMaster();
				ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(
						eachClassSubject.getClassSubjectId().getClassId(),
						eachClassSubject.getClassSubjectId().getSectionId());
	
				classSectionMaster.setClassSectionMasterId(classSectionMasterId);
				classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
				//if (null != classSectionMaster) {
					classSubjectDataDto.setClassId(classSectionMaster.getClassSectionMasterId().getClassId());
					classSubjectDataDto.setClassName(classSectionMaster.getClassName());
					sectionDataDto.setSectionId(classSectionMaster.getClassSectionMasterId().getSectionId());
					sectionDataDto.setSectionName(classSectionMaster.getSectionName());
				//}
				subjectIdDto.setSubjectId(eachClassSubject.getClassSubjectId().getSubjectId());
				subjectIdDto.setSubjectName(eachClassSubject.getSubjectName());
				subjectresult.add(subjectIdDto);
				sectionDataDto.setSubjectresult(subjectresult);
				sectiondata.add(sectionDataDto);
				classSubjectDataDto.setSectiondata(sectiondata);
				
			}else {
				sectionDataDto.setSectionId(eachClassSubject.getClassSubjectId().getSectionId());
				ClassSectionMaster classSectionMaster = new ClassSectionMaster();
				ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(
						eachClassSubject.getClassSubjectId().getClassId(),
						eachClassSubject.getClassSubjectId().getSectionId());
	
				classSectionMaster.setClassSectionMasterId(classSectionMasterId);
				classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
				sectionDataDto.setSectionName(classSectionMaster.getSectionName());
				subjectIdDto.setSubjectId(eachClassSubject.getClassSubjectId().getSubjectId());
				subjectIdDto.setSubjectName(eachClassSubject.getSubjectName());
				subjectresult.add(subjectIdDto);
				sectionDataDto.setSubjectresult(subjectresult);
				sectiondata.add(sectionDataDto);
				classSubjectDataDto.setSectiondata(sectiondata);
				//classSubjectDataList.add(classSubjectDataDto);
			}
			
			
		}
		QualificationDataDto qualificationDataDto = new QualificationDataDto();
		// TODO classSubjectDataDto values

		UserToken userToken = getUserToken(user);
		userToken.setUserid(user.getUserId());
		if (employeeMaster != null) {
			employeeMasterDetailsDto.setClassSubjectData(classSubjectDataList);
			employeeMasterDetailsDto.setUserFirstName(employeeMaster.getFirstName());
			employeeMasterDetailsDto.setUserLastName(employeeMaster.getLastName());
			employeeMasterDetailsDto.setUserMiddleName(employeeMaster.getMiddleName());
			employeeMasterDetailsDto.setUserMobileNumber(employeeMaster.getMobilePhone());
			employeeMasterDetailsDto.setUserEmail(userRegistration.getEmailId());
			employeeMasterDetailsDto.setCountryName(employeeMaster.getCountry());
			employeeMasterDetailsDto.setUserDateOfBirth(userRegistration.getDateOfBirth());
			employeeMasterDetailsDto.setStateName(employeeMaster.getCrspState());

			employeeMasterDetailsDto.setUserGender(employeeMaster.getGender());
			employeeMasterDetailsDto.setRoleId(userRegistration.getRoleId());
			employeeMasterDetailsDto.setRoleName(employeeMaster.getRole().getRoleDescription());
			employeeMasterDetailsDto.setToken(user.getToken());
			employeeMasterDetailsDto.setCityName(employeeMaster.getPerCity());
			employeeMasterDetailsDto.setCreatedAt(employeeMaster.getCrspCity());
			employeeMasterDetailsDto.setId(employeeMaster.getEmployeeId());
			employeeMasterDetailsDto.setToken(userToken.getToken());
			employeeMasterDetailsDto.setUserZipCode(employeeMaster.getCrspZip());
			employeeMasterDetailsDto.setUserAddress(employeeMaster.getAddressLine1() + ","
					+ employeeMaster.getAddressLine2() + "," + employeeMaster.getAddressLine3());
			employeeMasterDetailsDto.setClassSubjectData(classSubjectDataList);
			employeeMasterDetailsDto.setQualificationData(qualificationDataDto);
			if (null != employeeMaster.getProfilePic()) {
				employeeMasterDetailsDto.setUserProfileImage(employeeMaster.getProfilePic());
			} else {
				employeeMasterDetailsDto.setUserProfileImage("download.jpeg");
			}
			employeeMasterDetailsDto.setDesignationData(employeeMaster.getRole().getRoleDescription());
			employeeMasterDetailsDto.setLastLogin(user.getPasswordLastChangeDate());
			employeeMasterDetailsDto.setUserId(employeeMaster.getUser().getUserId());
			// employeeMasterDetailsDto.setStatus(status);
			// employeeMasterDetailsDto.setUpdatedAt(updatedAt);
			tokenService.save(userToken);
			//employeeMasterDetailsDto.setToken(genarateUserToken(user));
			tokenService.tokenUpdate(userToken.getId(), user.getUserId());
			logger.info("Login successfully");
		} else {
			logger.error("No user found");
		}
		return employeeMasterDetailsDto;
	}

	/**
	 * Helper method to set student master details while login
	 * 
	 * @param tokenService
	 * @param studentMasterService
	 * @param studentEnfollment
	 * @param userRegistration
	 * @param user
	 * @param studentMaster
	 * @param classSectionMaster
	 * @return StudentMasterDetailsDto
	 */
	public StudentMasterDetailsDto setStudentMasterDto(TokenService tokenService,
			StudentMasterService studentMasterService, StudentEnrollment studentEnfollment,
			UserRegistration userRegistration, User user, StudentMaster studentMaster,
			ClassSectionMaster classSectionMaster) {
		StudentMasterDetailsDto studentMasterDetailsDto = new StudentMasterDetailsDto();
		UserToken userToken = getUserToken(user);
		userToken.setUserid(user.getUserId());
		if (studentMaster != null) {
			studentMasterDetailsDto.setAdmissionDate(studentMaster.getAdmissionDate());
			studentMasterDetailsDto.setId(studentMaster.getStudentId());
			studentMasterDetailsDto.setStudentId(studentMaster.getStudentId());
			studentMasterDetailsDto.setClassId(studentMaster.getClassId());
			studentMasterDetailsDto.setSectionId(studentMaster.getSectionId());
			if (classSectionMaster != null) {
				studentMasterDetailsDto.setClassName(classSectionMaster.getClassName());
				studentMasterDetailsDto.setSectionName(classSectionMaster.getSectionName());
			}
			studentMasterDetailsDto.setStudentFirstName(studentMaster.getFirstName());
			studentMasterDetailsDto.setStudentLastName(studentMaster.getLastName());
			studentMasterDetailsDto.setStudentMiddleName(CommonUtils.checkNull(studentMaster.getMiddleName()));
			studentMasterDetailsDto.setStudentMobileNumber(String.valueOf(studentMaster.getMobilePhone()));
			studentMasterDetailsDto.setStudentEmail(userRegistration.getEmailId());
			studentMasterDetailsDto.setCityName(studentMaster.getCorrespondenceCity());
			studentMasterDetailsDto.setDateOfBirth(userRegistration.getDateOfBirth());
			studentMasterDetailsDto.setStateName(studentMaster.getCorrespondenceState());
			studentMasterDetailsDto.setStatus("1");
			studentMasterDetailsDto.setEmergencyContactNumber(String.valueOf(studentMaster.getFatherContact()));
			studentMasterDetailsDto.setGender(studentMaster.getGender());
			studentMasterDetailsDto.setRoleId(userRegistration.getRoleId());
			studentMasterDetailsDto.setRoleName(studentMaster.getRole().getRoleDescription());
			studentMasterDetailsDto.setStudentAddress(studentMaster.getCorrespondenceAddress1() + " , "
					+ studentMaster.getCorrespondenceAddress3() + " , " + studentMaster.getCorrespondenceAddress2());
			studentMasterDetailsDto.setToken(userToken.getToken());
			studentMasterDetailsDto.setTelephoneNumber(String.valueOf(studentMaster.getMobilePhone()));
			if (null != studentMaster.getProfilePicture()) {
				studentMasterDetailsDto.setStudentProfileImage(studentMaster.getProfilePicture());
			} else {
				studentMasterDetailsDto.setStudentProfileImage("download.jpeg");
			}
			studentMasterDetailsDto.setPreviousSchool(studentMaster.getPreviousSchool());
			studentMasterDetailsDto.setCityId(CommonUtils.checkNull(studentMaster.getCorrespondenceCity()));
			studentMasterDetailsDto.setStudentZipCode(String.valueOf(studentMaster.getCorrespondenceZip()));
			studentMasterDetailsDto.setStudentRollNumber(studentMaster.getStudentId());
			studentMasterDetailsDto.setGradeCompleted(studentMaster.getForUseField5());
			studentMasterDetailsDto.setStudentPassword(studentMaster.getForUseField6());
			// userMasterDetailsDto.setDocumentsImage(studentMaster.getDocumentImage());
			studentMasterDetailsDto.setCountryId(CommonUtils.checkNull(studentMaster.getPermanentCity()));
			studentMasterDetailsDto.setLibraryId(studentMaster.getForUseField3());
			studentMasterDetailsDto.setHostelId(studentMaster.getForUseField4());
			studentMasterDetailsDto.setLastLogin(user.getPasswordLastChangeDate());
			studentMasterDetailsDto.setStateId(CommonUtils.checkNull(studentMaster.getPermanentState()));
			studentMasterDetailsDto.setCreatedAt(studentMaster.getUpdatedTime());
			studentMasterDetailsDto.setTransportId(studentMaster.getForUseField5());
			studentMasterDetailsDto.setUserId(studentMaster.getUser().getUserId());
			tokenService.save(userToken);
			//studentMasterDetailsDto.setToken(genarateUserToken(user));
			tokenService.tokenUpdate(userToken.getId(), user.getUserId());
			logger.info("Login successfully");
		} else {
			logger.error("No user found");
		}
		return studentMasterDetailsDto;
	}

	/**
	 * Helper method to convert class subjects for a specific student to List of Dto
	 * 
	 * @param classSubjectService
	 * @param classSubjectDetails
	 * @return List<UserSubjectDto>
	 */
	public List<UserSubjectDto> getStudentSubjects(ClassSubjectService classSubjectService,
			ClassSubjectDetails classSubjectDetails) {
		List<UserSubjectDto> userSubjectDtoList = new ArrayList<>();
		UserSubjectDto userSubjectDto;
		for (ClassSubject classSubject : classSubjectDetails.getClassSubject()) {
			userSubjectDto = new UserSubjectDto();
			//classSubject = classSubjectService.getElementById(classSubject);
			userSubjectDto.setSubjectCode(classSubject.getClassSubjectId().getSubjectId());
			userSubjectDto.setSubjectName(classSubject.getSubjectName());
			userSubjectDto.setTeacherFirstName(classSubject.getTeacherName());
			// userSubjectDto.setTeacherMiddleName(userMname);
			// userSubjectDto.setTeacherLastName(userLname);
			// userSubjectDto.setTeacherEmail(classSubject.getT);
			userSubjectDtoList.add(userSubjectDto);
		}
		return userSubjectDtoList;
	}
}
