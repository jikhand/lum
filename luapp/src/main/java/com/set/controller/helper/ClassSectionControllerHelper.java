/**
 * 
 */
package com.set.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.set.dto.ClassSectionMasterDetailsDto;
import com.set.dto.ClassSectionMasterDto;
import com.set.dto.ClassSubjectDetailsDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterDetails;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetails;

/**
 * @author Administrator
 *
 */
@Component
public class ClassSectionControllerHelper {

	/**
	 * 
	 */
	public ClassSectionControllerHelper() {
	}

	public ClassSectionMasterDetailsDto setClassSectionDetailsDto(ClassSectionMasterDetails classSectionDetails) {
		ClassSectionMasterDetailsDto classSubjectMasterDetailsDto = new ClassSectionMasterDetailsDto();
		List<ClassSectionMasterDto> classSectionMasterDtoList = new ArrayList<>();
		ClassSectionMasterDto classSectionMasterDto = new ClassSectionMasterDto();
		for (ClassSectionMaster eachClassSection : classSectionDetails.getClassSectionMaster()) {
			classSectionMasterDto = new ClassSectionMasterDto();
			classSectionMasterDto.setClassId(eachClassSection.getClassSectionMasterId().getClassId());
			classSectionMasterDto.setClassName(eachClassSection.getClassName());
			classSectionMasterDto.setAcademicYear(eachClassSection.getAcademicYear());
			classSectionMasterDto.setBuildBlockName(eachClassSection.getBuildBlockName());
			classSectionMasterDto.setNoOfStudents(eachClassSection.getNoOfStudents());
			classSectionMasterDto.setSectionId(eachClassSection.getClassSectionMasterId().getSectionId());
			classSectionMasterDto.setSectionName(eachClassSection.getSectionName());
			classSectionMasterDto.setClassKey(eachClassSection.getClassKey());
			classSectionMasterDtoList.add(classSectionMasterDto);
		}
		classSubjectMasterDetailsDto.setClassSectionMaster(classSectionMasterDtoList);
		classSubjectMasterDetailsDto.setCount(classSectionMasterDtoList.size());
		return classSubjectMasterDetailsDto;
	}

}
