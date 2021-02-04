package com.set.service;

import java.util.List;

import com.set.model.QuestionPaper;
import com.set.model.QuestionPaperDetails;
import com.set.model.QuestionPaperId;

public interface QuestionPaperService {
	  public void save(QuestionPaper questionPaper);
	  public QuestionPaperDetails getAllQuestionPaper(int pagenumber,String searchdata);
	  public List<QuestionPaper> getAllQuestionPaperSelect();
	  public List<Object[]> getAllQuestionTypeList(String examId);
	  public List<Object[]> getAllQuestionList(String examId, String questionTypeId);
	  public QuestionPaper getQuestionPaperById(QuestionPaperId questionPaperId);
	  public void updateQuestionPaper(QuestionPaper questionPaper);
	  public void deleteQuestionPaper(QuestionPaper questionPaper);
	  public boolean IsQuestionAlreadyExist(QuestionPaperId questionPaperId);
	  public int totalQuestionPaper();
}
