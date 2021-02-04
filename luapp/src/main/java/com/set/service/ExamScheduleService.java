package com.set.service;

import java.util.List;

import com.set.model.ExamSchedule;
import com.set.model.ExamScheduleDetails;

public interface ExamScheduleService {
	  public void save(ExamSchedule examSchedule);
	  public ExamScheduleDetails getAllExamSchedule(int pagenumber,String searchdata);
	  public List<ExamSchedule> getAllExamScheduleSelect();
	  public List<Object[]> getAllStudentExamList(String classId, String sectionId);
	  public List<Object[]> getAllTeacherExamList(String teacherId);
	  public List<Object[]> getAllExamList(String examId);
	  public List<Object[]> getAllExamQuestionList(String examId);
	  public ExamSchedule getExamScheduleById(String examScheduleId);
	  public void updateExamSchedule(ExamSchedule examSchedule);
	  public void deleteExamSchedule(ExamSchedule examSchedule);
	  public boolean IsExist(String examSchedule, String examType);
	  public int totalExamSchedule();
}
