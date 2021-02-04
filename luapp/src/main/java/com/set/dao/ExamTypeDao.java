package com.set.dao;

import java.util.List;

import com.set.model.ExamType;
import com.set.model.ExamTypeDetails;

public interface ExamTypeDao {
	  public void save(ExamType examType);
	  public ExamTypeDetails getAllExamType();
	  public List<ExamType> getAllExamTypeSelect();
	  public ExamType getExamTypeById(String examTypeId);
	  public void updateExamType(ExamType examType);
	  public void deleteExamType(ExamType examType);
	  public boolean IsExist(String examType);
	  public int totalExamType();
}
