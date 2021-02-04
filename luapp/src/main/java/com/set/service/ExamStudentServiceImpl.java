package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ExamStudentDao;
import com.set.model.ExamStudent;
import com.set.model.ExamStudentDetails;
import com.set.model.ExamStudentId;
@Service
public class ExamStudentServiceImpl implements ExamStudentService {
	
	@Autowired
	public ExamStudentDao examStudentDao;

	@Override
	public void save(ExamStudent examStudent) {
		examStudentDao.save(examStudent);
	}

	@Override
	public ExamStudentDetails getAllExamStudent(int pagenumber,String searchdata) {
		return examStudentDao.getAllExamStudent(pagenumber, searchdata);
	}

	@Override
	public List<ExamStudent> getAllExamStudentSelect() {
		return examStudentDao.getAllExamStudentSelect();
	}
	
	@Override
	public void updateExamStudent(ExamStudent examStudent) {
		examStudentDao.updateExamStudent(examStudent);
	}

	@Override
	public void deleteExamStudent(ExamStudentId examStudentId) {
		examStudentDao.deleteExamStudent(examStudentId);
	}

	@Override
	public boolean IsExist(String examStudent) {
		// TODO Auto-generated method stub
		return examStudentDao.IsExist(examStudent);
	}

	@Override
	public int totalExamStudent(String examid) {
		return examStudentDao.totalExamStudent(examid);
	}

	@Override
	public ExamStudent getExamStudentById(ExamStudentId examStudentId) {
		return examStudentDao.getExamStudentById(examStudentId);
	}

	@Override
	public List<Object[]> getAllSubmitStudentList(String examid) {
		return examStudentDao.getAllSubmitStudentList(examid);
	}

	@Override
	public List<Object[]> getAllAbsentStudentList(String examid) {
		return examStudentDao.getAllAbsentStudentList(examid);
	}

	@Override
	public List<Object[]> getAllQuestionTypeList(String examId) {
		return examStudentDao.getAllQuestionTypeList(examId);
	}

	@Override
	public List<Object[]> getAllQuestionList(String examId, String questionTypeId, String studentId) {
		return examStudentDao.getAllQuestionList(examId,questionTypeId,studentId);
	}

	@Override
	public List<Object[]> getAllReportStudentList(String studentId) {
		return examStudentDao.getAllReportStudentList(studentId);
	}

	@Override
	public List<Object[]> getAllReportMarksList(String studentId) {
		return examStudentDao.getAllReportMarksList(studentId);
	}

	@Override
	public List<Object[]> getAllReportAbsentList(String studentId) {
		return examStudentDao.getAllReportAbsentList(studentId);
	}



}
