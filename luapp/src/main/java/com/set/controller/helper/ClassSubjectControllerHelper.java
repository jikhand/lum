/**
 * 
 */
package com.set.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.set.dto.ClassSubjectDetailsDto;
import com.set.dto.ClassSubjectMasterDetailsDto;
import com.set.dto.SubjectMasterDetailsDto;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetails;

/**
 * @author Administrator
 *
 */
@Component
public class ClassSubjectControllerHelper {

	/**
	 * 
	 */
	public ClassSubjectControllerHelper() {
	}

	public ClassSubjectMasterDetailsDto setClassSubjectDetailsDto(ClassSubjectDetails classSubjectDetails) {
		ClassSubjectMasterDetailsDto classSubjectMasterDetailsDto = new ClassSubjectMasterDetailsDto();
		ClassSubjectDetailsDto classSubjectDetailsDto = new ClassSubjectDetailsDto();
		List<ClassSubjectDetailsDto> classSubjectDetailsDtoList = new ArrayList<>();
		for (ClassSubject eachClassSubject : classSubjectDetails.getClassSubject()) {
			classSubjectDetailsDto.setId(eachClassSubject.getClassSubjectId().getSubjectId());
			classSubjectDetailsDto.setSubjectName(eachClassSubject.getSubjectName());
			classSubjectDetailsDto.setAcademicYear(eachClassSubject.getAcademicYear());
			classSubjectDetailsDto.setTeacherId(eachClassSubject.getClassSubjectId().getTeacherId());
			classSubjectDetailsDto.setTeacherName(eachClassSubject.getTeacherName());
			classSubjectDetailsDto.setTorUseField1(eachClassSubject.getForUseField1());
			classSubjectDetailsDto.setForUseField2(eachClassSubject.getForUseField2());
			classSubjectDetailsDto.setInsertedBy(eachClassSubject.getInsertedBy());
			classSubjectDetailsDto.setInsertedTime(eachClassSubject.getInsertedTime());
			classSubjectDetailsDto.setUpdatedBy(eachClassSubject.getUpdatedBy());
			classSubjectDetailsDto.setUpdatedTime(eachClassSubject.getUpdatedTime());
			classSubjectDetailsDtoList.add(classSubjectDetailsDto);
		}
		classSubjectMasterDetailsDto.setClassSubjectsDtoList(classSubjectDetailsDtoList);
		classSubjectMasterDetailsDto.setCount(classSubjectDetailsDtoList.size());
		return classSubjectMasterDetailsDto;
	}
	
	public SubjectMasterDetailsDto setMasterSubjectsDetailDto() {
		SubjectMasterDetailsDto subjectMasterDetailsDto = new SubjectMasterDetailsDto();
		
		return subjectMasterDetailsDto;
	}

}
