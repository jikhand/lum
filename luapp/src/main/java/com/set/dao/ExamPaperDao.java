package com.set.dao;

import java.util.List;

import com.set.model.ExamPaper;
import com.set.model.ExamPaperDetails;
import com.set.model.ExamPaperId;

public interface ExamPaperDao {
	  public void save(ExamPaper examPaper);
	  public ExamPaperDetails getAllExamPaper(int pagenumber,String searchdata);
	  public List<ExamPaper> getAllExamPaperSelect();
	  public List<Object[]> getAllQuestionTypeList(String examId);
	  public List<Object[]> getAllQuestionList(String examId, String questionTypeId);
	  public ExamPaper getExamPaperById(ExamPaperId examPaperId);
	  public void updateExamPaper(ExamPaper examPaper);
	  public void deleteExamPaper(ExamPaper examPaper);
	  public boolean IsExist(String examPaper);
	  public int totalExamPaper();
}
