package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.QuestionPaperDao;
import com.set.model.QuestionPaper;
import com.set.model.QuestionPaperDetails;
import com.set.model.QuestionPaperId;
@Service
public class QuestionPaperServiceImpl implements QuestionPaperService {
	
	@Autowired
	public QuestionPaperDao questionPaperDao;

	@Override
	public void save(QuestionPaper questionPaper) {
		questionPaperDao.save(questionPaper);
	}

	@Override
	public QuestionPaperDetails getAllQuestionPaper(int pagenumber,String searchdata) {
		return questionPaperDao.getAllQuestionPaper(pagenumber, searchdata);
	}

	@Override
	public List<QuestionPaper> getAllQuestionPaperSelect() {
		return questionPaperDao.getAllQuestionPaperSelect();
	}
	
	@Override
	public void updateQuestionPaper(QuestionPaper questionPaper) {
		questionPaperDao.updateQuestionPaper(questionPaper);
	}

	@Override
	public void deleteQuestionPaper(QuestionPaper questionPaper) {
		questionPaperDao.deleteQuestionPaper(questionPaper);
	}

	@Override
	public boolean IsQuestionAlreadyExist(QuestionPaperId questionPaperId) {
		// TODO Auto-generated method stub
		return questionPaperDao.IsQuestionAlreadyExist(questionPaperId);
	}

	@Override
	public int totalQuestionPaper() {
		return questionPaperDao.totalQuestionPaper();
	}

	@Override
	public QuestionPaper getQuestionPaperById(QuestionPaperId questionPaperId) {
		return questionPaperDao.getQuestionPaperById(questionPaperId);
	}

	@Override
	public List<Object[]> getAllQuestionTypeList(String examId) {
		return questionPaperDao.getAllQuestionTypeList(examId);
	}

	@Override
	public List<Object[]> getAllQuestionList(String examId, String questionTypeId) {
		return questionPaperDao.getAllQuestionList(examId,questionTypeId);
	}



}
