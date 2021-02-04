package com.set.service;

import java.util.List;

import com.set.model.ExamQuestions;
import com.set.model.ExamQuestionsId;

public interface ExamQuestionsService {
	  public void save(ExamQuestions examQuestions);
	  public List<ExamQuestions> getAllExamQuestionsSelect();
	  public ExamQuestions getExamQuestionsById(String examQuestionsId);
	  public void updateExamQuestions(ExamQuestions examQuestions);
	  public void deleteExamQuestions(ExamQuestionsId examQuestionsId);
	  public boolean IsExist(String examQuestions);
	  public int totalExamQuestions();
}
