package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ExamScheduleDao;
import com.set.model.ExamSchedule;
import com.set.model.ExamScheduleDetails;
@Service
public class ExamScheduleServiceImpl implements ExamScheduleService {
	
	@Autowired
	public ExamScheduleDao examScheduleDao;

	@Override
	public void save(ExamSchedule examSchedule) {
		examScheduleDao.save(examSchedule);
	}

	@Override
	public ExamScheduleDetails getAllExamSchedule(int pagenumber,String searchdata) {
		return examScheduleDao.getAllExamSchedule(pagenumber, searchdata);
	}

	@Override
	public List<ExamSchedule> getAllExamScheduleSelect() {
		return examScheduleDao.getAllExamScheduleSelect();
	}
	
	@Override
	public void updateExamSchedule(ExamSchedule examSchedule) {
		examScheduleDao.updateExamSchedule(examSchedule);
	}

	@Override
	public void deleteExamSchedule(ExamSchedule examSchedule) {
		examScheduleDao.deleteExamSchedule(examSchedule);
	}

	@Override
	public boolean IsExist(String examSchedule, String examType) {
		// TODO Auto-generated method stub
		return examScheduleDao.IsExist(examSchedule, examType);
	}

	@Override
	public int totalExamSchedule() {
		return examScheduleDao.totalExamSchedule();
	}

	@Override
	public ExamSchedule getExamScheduleById(String examScheduleId) {
		return examScheduleDao.getExamScheduleById(examScheduleId);
	}

	@Override
	public List<Object[]> getAllExamList(String examId) {
		return examScheduleDao.getAllExamList(examId);
	}

	@Override
	public List<Object[]> getAllExamQuestionList(String examId) {
		return examScheduleDao.getAllExamQuestionList(examId);
	}

	@Override
	public List<Object[]> getAllStudentExamList(String classId, String sectionId) {
		return examScheduleDao.getAllStudentExamList(classId,sectionId);
	}

	@Override
	public List<Object[]> getAllTeacherExamList(String teacherId) {
		return examScheduleDao.getAllTeacherExamList(teacherId);
	}



}
