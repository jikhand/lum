package com.set.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.QuestionType;
import com.set.model.QuestionTypeDetails;
@Transactional
@Repository
public class QuestionTypeDaoImpl implements QuestionTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("QuestionTypeImp.class");

	@Override
	public void save(QuestionType questionType) {
		sessionFactory.getCurrentSession().save(questionType);		
	}
	@Override
	public QuestionTypeDetails getAllQuestionType() {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionType.class);
		
		QuestionTypeDetails questionTypeDetails = new QuestionTypeDetails();
		logger.info("total number of record="+criteria.list().size());
		questionTypeDetails.setCount(criteria.list().size());
		questionTypeDetails.setQuestionType((List<QuestionType>) criteria.list());
		return questionTypeDetails;
	}
	@Override
	public List<QuestionType> getAllQuestionTypeSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionType.class); 
		return (List<QuestionType>) criteria.list();
	}

	@Override
	public QuestionType getQuestionTypeById(String questionTypeId) {
		return sessionFactory.getCurrentSession().get(QuestionType.class, questionTypeId);
	}

	@Override
	public void updateQuestionType(QuestionType questionType) {
		sessionFactory.getCurrentSession().update(questionType);	
	}

	@Override
	public void deleteQuestionType(QuestionType questionType) {
		sessionFactory.getCurrentSession().update(questionType);	
	}

	@Override
	public boolean IsExist(String questionType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int totalQuestionType() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionType.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}


}
