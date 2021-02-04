/**
 * 
 */
package com.set.controller.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.set.dto.ClassSubjectDetailsDto;
import com.set.dto.ClassSubjectMasterDetailsDto;
import com.set.dto.InsertDrawingDto;
import com.set.dto.SubjectMasterDetailsDto;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetails;
import com.set.model.Drawing;
import com.set.model.DrawingCategory;
import com.set.service.DrawingCategoryService;
import com.set.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
@Component
public class DrawingControllerHelper {
	
	public InsertDrawingDto setDrawingDto() {
		
		return null;
	}

	public InsertDrawingDto setDrawingDto(InsertDrawingDto insertDrawingDto, Drawing tempDrawing, String categoryName) {
		insertDrawingDto.setDrawingId(tempDrawing.getDrawingId());
		insertDrawingDto.setDrawingName(tempDrawing.getDrawingName());
		insertDrawingDto.setClassId(tempDrawing.getClassId());
		insertDrawingDto.setSectionId(tempDrawing.getSectionId());
		insertDrawingDto.setDeleted(tempDrawing.getIsDeleted());
		insertDrawingDto.setDrawingCategory(tempDrawing.getDrawingCategory());
		insertDrawingDto.setDrawingCategoryName(categoryName);
		insertDrawingDto.setDrawingCode(tempDrawing.getDrawingCode());
		insertDrawingDto.setDrawingType(tempDrawing.getDrawingType());
		insertDrawingDto.setImageUrl(tempDrawing.getImageUrl());
		insertDrawingDto.setStudentId(tempDrawing.getStudentId());
		insertDrawingDto.setCreatedAt(tempDrawing.getCreatedAt());
		insertDrawingDto.setUpdatedAt(tempDrawing.getUpdatedAt());
		return insertDrawingDto;
	}
	

}
