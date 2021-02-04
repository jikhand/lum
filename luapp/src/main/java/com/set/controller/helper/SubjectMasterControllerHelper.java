/**
 * 
 */
package com.set.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.set.dto.SubjectMasterDetailsDto;
import com.set.dto.SubjectMasterDto;
import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterDetils;

/**
 * @author Administrator
 *
 */
@Component
public class SubjectMasterControllerHelper {

	/**
	 * 
	 */
	public SubjectMasterControllerHelper() {
	}

	public SubjectMasterDetailsDto setMasterSubjectsDetailDto(SubjectMasterDetils subjectMasterDetils) {
		SubjectMasterDetailsDto subjectMasterDetailsDto = new SubjectMasterDetailsDto();
		SubjectMasterDto subjectMasterDto = new SubjectMasterDto();
		List<SubjectMasterDto> subjectMasterDtoList = new ArrayList<>();
		for(SubjectMaster eachSubjectMaster : subjectMasterDetils.getSubjectMaster()) {
			subjectMasterDto = new SubjectMasterDto();
			subjectMasterDto.setSubjectId(eachSubjectMaster.getSubjectMasterId().getSubjectId());
			subjectMasterDto.setTextBookISBN(eachSubjectMaster.getSubjectMasterId().getTextBookISBN());
			subjectMasterDto.setSubjectName(eachSubjectMaster.getSubjectName());
			subjectMasterDto.setTextBookLink(eachSubjectMaster.getTextBookLink());
			subjectMasterDto.setSubjectMaterialFiletype(eachSubjectMaster.getSubjectMaterialFiletype());
			subjectMasterDto.setTextBookName(eachSubjectMaster.getTextBookName());
			subjectMasterDto.setTextBookPath(eachSubjectMaster.getTextBookPath());
			subjectMasterDto.setInsertedBy(eachSubjectMaster.getInsertedBy());
			subjectMasterDto.setUpdatedBy(eachSubjectMaster.getUpdatedBy());
			subjectMasterDtoList.add(subjectMasterDto);
		}
		subjectMasterDetailsDto.setSubjects(subjectMasterDtoList);
		return subjectMasterDetailsDto;
	}

}
