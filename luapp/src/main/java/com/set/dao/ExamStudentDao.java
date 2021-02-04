package com.set.dao;

import java.util.List;


import com.set.model.ExamStudent;
import com.set.model.ExamStudentDetails;
import com.set.model.ExamStudentId;

public interface ExamStudentDao {
	  public void save(ExamStudent examStudent);
	  public ExamStudentDetails getAllExamStudent(int pagenumber,String searchdata);
	  public List<ExamStudent> getAllExamStudentSelect();
	  public List<Object[]> getAllSubmitStudentList(String examid);
	  public List<Object[]> getAllAbsentStudentList(String examid);
	  public List<Object[]> getAllQuestionTypeList(String examId);
	  public List<Object[]> getAllQuestionList(String examId, String questionTypeId, String studentId);
	  public ExamStudent getExamStudentById(ExamStudentId examStudentId);
	  public void updateExamStudent(ExamStudent examStudent);
	  public void deleteExamStudent(ExamStudentId examStudentId);
	  public boolean IsExist(String examStudent);
	  public int totalExamStudent(String examid);
	  public List<Object[]> getAllReportStudentList(String studentId);
	  public List<Object[]> getAllReportMarksList(String studentId);
	  public List<Object[]> getAllReportAbsentList(String studentId);
}
