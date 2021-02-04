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
import com.set.dto.StudentMasterListDto;
import com.set.dto.SubjectIdDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.EmployeeMaster;
import com.set.model.StudentMaster;
import com.set.model.StudentMasterDetils;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.model.UserToken;
import com.set.service.ClassSectionMasterService;
import com.set.service.EmployeeMasterService;
import com.set.service.TokenService;
import com.set.utils.TokenUtils;

/**
 * @author Administrator
 *
 */
@Component
public class StudentControllerHelper {
	private Logger logger = Logger.getLogger("StudentControllerHelper.class");
	Date today = new Date();
	Date tomorrow = new Date(today.getTime() + (5000 * 60 * 60 * 24 * 30));

	/**
	 * 
	 */
	public StudentControllerHelper() {
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
			// if (!eachClassSubject.getClassSubjectId().getClassId().equals(currentCsId)) {
			// currentCsId = eachClassSubject.getClassSubjectId().getClassId();
			// } else {
			classSubjectDataDto = new ClassSubjectDataDto();
			sectionDataDto = new SectionDataDto();
			SubjectIdDto subjectIdDto = new SubjectIdDto();
			List<SubjectIdDto> subjectresult = new ArrayList<>();

			ClassSectionMaster classSectionMaster = new ClassSectionMaster();
			ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(
					eachClassSubject.getClassSubjectId().getClassId(),
					eachClassSubject.getClassSubjectId().getSectionId());
			classSectionMaster.setClassSectionMasterId(classSectionMasterId);
			classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
			classSubjectDataDto.setClassId(classSectionMaster.getClassSectionMasterId().getClassId());
			classSubjectDataDto.setClassName(classSectionMaster.getClassName());

			sectionDataDto.setSectionId(classSectionMaster.getClassSectionMasterId().getSectionId());
			sectionDataDto.setSectionName(classSectionMaster.getSectionName());
			subjectIdDto.setSubjectId(eachClassSubject.getClassSubjectId().getSubjectId());
			subjectIdDto.setSubjectName(eachClassSubject.getSubjectName());
			subjectresult.add(subjectIdDto);
			sectionDataDto.setSubjectresult(subjectresult);
			sectiondata.add(sectionDataDto);
			classSubjectDataDto.setSectiondata(sectiondata);
			classSubjectDataList.add(classSubjectDataDto);
			currentCsId = eachClassSubject.getClassSubjectId().getClassId();
			// }
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
			employeeMasterDetailsDto.setUserProfileImage(employeeMaster.getProfilePic());

			employeeMasterDetailsDto.setDesignationData(employeeMaster.getRole().getRoleDescription());
			employeeMasterDetailsDto.setLastLogin(user.getPasswordLastChangeDate());
			employeeMasterDetailsDto.setUserId(employeeMaster.getUser().getUserId());
			// employeeMasterDetailsDto.setStatus(status);
			// employeeMasterDetailsDto.setUpdatedAt(updatedAt);

			tokenService.save(userToken);
			employeeMasterDetailsDto.setToken(genarateUserToken(user));
			tokenService.tokenUpdate(userToken.getId(), user.getUserId());
			logger.info("Login successfully");
		} else {
			logger.error("No user found");
		}
		return employeeMasterDetailsDto;
	}

	public StudentMasterListDto setStudentMasterListDto(StudentMasterDetils studentMasterDetils,
			ClassSectionMasterService classSectionMasterService) {
		StudentMasterListDto studentMasterListDto = new StudentMasterListDto();
		StudentMasterDetailsDto studentMasterDetailsDto = new StudentMasterDetailsDto();
		List<StudentMasterDetailsDto> studentMasterDtoList = new ArrayList<>();
		for (StudentMaster eachStudent : studentMasterDetils.getStudentMasterList()) {
			studentMasterDetailsDto = new StudentMasterDetailsDto();
			studentMasterDetailsDto.setAdmissionDate(eachStudent.getAdmissionDate());
			studentMasterDetailsDto.setGender(eachStudent.getGender());
			studentMasterDetailsDto.setDateOfBirth(eachStudent.getDateOfBirth());
			studentMasterDetailsDto.setCityName(eachStudent.getCorrespondenceCity());
			studentMasterDetailsDto.setClassId(eachStudent.getClassId());
			ClassSectionMaster classSectionMaster = new ClassSectionMaster();
			ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(eachStudent.getClassId(),
					eachStudent.getSectionId());
			classSectionMaster.setClassSectionMasterId(classSectionMasterId);
			classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
			studentMasterDetailsDto.setClassName(classSectionMaster.getClassName());
			studentMasterDetailsDto.setSectionName(classSectionMaster.getSectionName());
			studentMasterDetailsDto.setEmergencyContactNumber(String.valueOf(eachStudent.getFatherContact()));
			studentMasterDetailsDto.setStudentEmail(eachStudent.getEmailId());
			studentMasterDetailsDto.setPreviousSchool(eachStudent.getPreviousSchool());
			studentMasterDetailsDto.setTelephoneNumber(String.valueOf(eachStudent.getMobilePhone()));
			studentMasterDetailsDto.setRoleId(eachStudent.getRole().getRoleId());
			studentMasterDetailsDto.setStudentProfileImage(eachStudent.getProfilePicture());
			studentMasterDetailsDto.setSectionId(eachStudent.getSectionId());
			studentMasterDetailsDto.setStudentId(eachStudent.getStudentId());
			studentMasterDetailsDto.setStateName(eachStudent.getPermanentState());
			studentMasterDetailsDto.setStudentFirstName(eachStudent.getFirstName());
			studentMasterDetailsDto.setStudentMiddleName(eachStudent.getMiddleName());
			studentMasterDetailsDto.setStudentLastName(eachStudent.getLastName());
			studentMasterDetailsDto.setDocumentsImage(eachStudent.getProfilePicture());
			studentMasterDetailsDto.setStudentAddress(eachStudent.getCorrespondenceAddress1() + ", "
					+ eachStudent.getCorrespondenceAddress2() + ", " + eachStudent.getCorrespondenceAddress3());
			studentMasterDetailsDto.setStudentZipCode(String.valueOf(eachStudent.getCorrespondenceZip()));
			studentMasterDetailsDto.setTransportId("0");
			//TODO bellow fields need to be set
			// studentMasterDetailsDto.setCountryName(eachStudent.getCountryName());
			// studentMasterDetailsDto.setLibraryId(eachStudent.getLibraryId);
			studentMasterDtoList.add(studentMasterDetailsDto);
		}
		studentMasterListDto.setCount(studentMasterDtoList.size());
		studentMasterListDto.setStudentMasterList(studentMasterDtoList);
		studentMasterListDto.setMessage("Students fetched successfully");
		return studentMasterListDto;
	}

}
