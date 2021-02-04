package com.set.service;

import java.util.List;

import com.set.model.QuestionBank;
import com.set.model.QuestionBankDetails;

public interface QuestionBankService {
	  public void save(QuestionBank questionBank);
	  public List<Object[]> getAllQuestionList(String classid, String subjectid, String questionTypeId);
	  public List<Object[]> getAllQuestionListByDate(String classid, String subjectid, String questionTypeId, String questionDate);
	  public void updateQuestionBank(QuestionBank questionBank);
}
