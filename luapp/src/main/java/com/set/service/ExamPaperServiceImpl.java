package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ExamPaperDao;
import com.set.model.ExamPaper;
import com.set.model.ExamPaperDetails;
import com.set.model.ExamPaperId;
@Service
public class ExamPaperServiceImpl implements ExamPaperService {
	
	@Autowired
	public ExamPaperDao examPaperDao;

	@Override
	public void save(ExamPaper examPaper) {
		examPaperDao.save(examPaper);
	}

	@Override
	public ExamPaperDetails getAllExamPaper(int pagenumber,String searchdata) {
		return examPaperDao.getAllExamPaper(pagenumber, searchdata);
	}

	@Override
	public List<ExamPaper> getAllExamPaperSelect() {
		return examPaperDao.getAllExamPaperSelect();
	}
	
	@Override
	public void updateExamPaper(ExamPaper examPaper) {
		examPaperDao.updateExamPaper(examPaper);
	}

	@Override
	public void deleteExamPaper(ExamPaper examPaper) {
		examPaperDao.deleteExamPaper(examPaper);
	}

	@Override
	public boolean IsExist(String examPaper) {
		// TODO Auto-generated method stub
		return examPaperDao.IsExist(examPaper);
	}

	@Override
	public int totalExamPaper() {
		return examPaperDao.totalExamPaper();
	}

	@Override
	public ExamPaper getExamPaperById(ExamPaperId examPaperId) {
		return examPaperDao.getExamPaperById(examPaperId);
	}

	@Override
	public List<Object[]> getAllQuestionTypeList(String examId) {
		return examPaperDao.getAllQuestionTypeList(examId);
	}

	@Override
	public List<Object[]> getAllQuestionList(String examId, String questionTypeId) {
		return examPaperDao.getAllQuestionList(examId,questionTypeId);
	}



}
