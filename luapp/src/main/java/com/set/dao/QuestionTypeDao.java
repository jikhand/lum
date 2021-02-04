package com.set.dao;

import java.util.List;

import com.set.model.QuestionType;
import com.set.model.QuestionTypeDetails;

public interface QuestionTypeDao {
	  public void save(QuestionType questionType);
	  public QuestionTypeDetails getAllQuestionType();
	  public List<QuestionType> getAllQuestionTypeSelect();
	  public QuestionType getQuestionTypeById(String questionTypeId);
	  public void updateQuestionType(QuestionType questionType);
	  public void deleteQuestionType(QuestionType questionType);
	  public boolean IsExist(String questionType);
	  public int totalQuestionType();
}
