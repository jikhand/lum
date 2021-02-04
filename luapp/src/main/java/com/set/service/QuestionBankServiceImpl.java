package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.QuestionBankDao;
import com.set.model.QuestionBank;
import com.set.model.QuestionBankDetails;
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
	
	@Autowired
	public QuestionBankDao questionBankDao;

	@Override
	public void save(QuestionBank questionBank) {
		questionBankDao.save(questionBank);
    }
	
	@Override
	public void updateQuestionBank(QuestionBank questionBank) {
		questionBankDao.updateQuestionBank(questionBank);
	}


	@Override
	public List<Object[]> getAllQuestionList(String classid, String subjectid, String questionTypeId) {
		return questionBankDao.getAllQuestionList(classid, subjectid, questionTypeId);
	}

	@Override
	public List<Object[]> getAllQuestionListByDate(String classid, String subjectid, String questionTypeId, String questionDate) {
		return questionBankDao.getAllQuestionListByDate(classid, subjectid, questionTypeId,questionDate);
	}



}
