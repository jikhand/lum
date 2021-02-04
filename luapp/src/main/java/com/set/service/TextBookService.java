package com.set.service;

import com.set.dto.TextBookListDto;
import com.set.model.TextBookDetails;
import com.set.model.TextBookMaster;

public interface TextBookService {
	public void save(TextBookMaster textBookMaster);
	public TextBookListDto getAllTextBooksByClassId(String classId);
	public TextBookListDto getAllTextBooksByClassIdSectionId(String classId, String sectionId);
	public TextBookDetails textBookSearch(int id, String searchData);
	public TextBookListDto getAllTextBooksByTeacherId(String teacherId);
}
