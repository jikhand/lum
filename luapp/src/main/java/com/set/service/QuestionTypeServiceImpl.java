package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.QuestionTypeDao;
import com.set.model.QuestionType;
import com.set.model.QuestionTypeDetails;
@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
	
	@Autowired
	public QuestionTypeDao questionTypeDao;

	@Override
	public void save(QuestionType questionType) {
		questionTypeDao.save(questionType);
	}

	@Override
	public QuestionTypeDetails getAllQuestionType() {
		return questionTypeDao.getAllQuestionType();
	}

	@Override
	public List<QuestionType> getAllQuestionTypeSelect() {
		return questionTypeDao.getAllQuestionTypeSelect();
	}
	
	@Override
	public void updateQuestionType(QuestionType questionType) {
		questionTypeDao.updateQuestionType(questionType);
	}

	@Override
	public void deleteQuestionType(QuestionType questionType) {
		questionTypeDao.deleteQuestionType(questionType);
	}

	@Override
	public boolean IsExist(String questionType) {
		// TODO Auto-generated method stub
		return questionTypeDao.IsExist(questionType);
	}

	@Override
	public int totalQuestionType() {
		return questionTypeDao.totalQuestionType();
	}

	@Override
	public QuestionType getQuestionTypeById(String questionTypeId) {
		return questionTypeDao.getQuestionTypeById(questionTypeId);
	}



}
