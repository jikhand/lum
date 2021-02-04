/**
 * 
 */
package com.set.controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.set.dto.ExamTypeDetailsDto;
import com.set.dto.ExamTypeDto;
import com.set.dto.QuestionTypeDetailsDto;
import com.set.dto.QuestionTypeDto;
import com.set.dto.SubjectMasterDetailsDto;
import com.set.dto.SubjectMasterDto;
import com.set.model.ExamType;
import com.set.model.ExamTypeDetails;
import com.set.model.QuestionType;
import com.set.model.QuestionTypeDetails;
import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterDetils;

/**
 * @author Administrator
 *
 */
@Component
public class ExamControllerHelper {

	/**
	 * 
	 */
	public ExamControllerHelper() {
	}

	public ExamTypeDetailsDto setExamTypeDetailDto(ExamTypeDetails examTypeDetails) {
		ExamTypeDetailsDto examTypeDetailsDto = new ExamTypeDetailsDto();
		ExamTypeDto examTypeDto = new ExamTypeDto();
		List<ExamTypeDto> examTypeDtoList = new ArrayList<>();
		for(ExamType eachExamType : examTypeDetails.getExamType()) {
			examTypeDto = new ExamTypeDto();
			examTypeDto.setExamType(eachExamType.getExamType());
			examTypeDto.setExamTypeDescription(eachExamType.getExamTypeDescription());
			examTypeDtoList.add(examTypeDto);
		}
		examTypeDetailsDto.setExamTypeList(examTypeDtoList);
		examTypeDetailsDto.setCount(examTypeDtoList.size());
		examTypeDetailsDto.setMessage("Exam Type List");
		return examTypeDetailsDto;
	}

	public QuestionTypeDetailsDto setQuestionTypeDetailDto(QuestionTypeDetails questionTypeDetails) {
		QuestionTypeDetailsDto questionTypeDetailsDto = new QuestionTypeDetailsDto();
		QuestionTypeDto questionTypeDto = new QuestionTypeDto();
		List<QuestionTypeDto> questionTypeDtoList = new ArrayList<>();
		for(QuestionType eachQuestionType : questionTypeDetails.getQuestionType()) {
			questionTypeDto = new QuestionTypeDto();
			questionTypeDto.setQuestionType(eachQuestionType.getQuestionType());
			questionTypeDto.setDescription(eachQuestionType.getDescription());
			questionTypeDtoList.add(questionTypeDto);
		}
		questionTypeDetailsDto.setQuestionTypeList(questionTypeDtoList);
		questionTypeDetailsDto.setCount(questionTypeDtoList.size());
		questionTypeDetailsDto.setMessage("Question Type List");
		return questionTypeDetailsDto;
	}

}
