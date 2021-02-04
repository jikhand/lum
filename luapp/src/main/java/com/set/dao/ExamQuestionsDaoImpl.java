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

import com.set.model.ExamQuestions;
import com.set.model.ExamQuestionsId;
import com.set.model.Notification;
import com.set.model.StudentNotes;
import com.set.model.User;
@Transactional
@Repository
public class ExamQuestionsDaoImpl implements ExamQuestionsDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("ExamQuestionsImp.class");

	@Override
	public void save(ExamQuestions examQuestions) {
		sessionFactory.getCurrentSession().save(examQuestions);		
	}
	@Override
	public List<ExamQuestions> getAllExamQuestionsSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamQuestions.class); 
		criteria.add(Restrictions.eq("isDeleted",false));
		return (List<ExamQuestions>) criteria.list();
	}

	@Override
	public ExamQuestions getExamQuestionsById(String examQuestionsId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(ExamQuestions.class);
		 cr.add(Restrictions.eq("examQuestionsName",examQuestionsId));
		 ExamQuestions examQuestions = (ExamQuestions)cr.uniqueResult();
		  return examQuestions;
	}

	@Override
	public void updateExamQuestions(ExamQuestions examQuestions) {
		sessionFactory.getCurrentSession().update(examQuestions);	
	}

	@Override
	public void deleteExamQuestions(ExamQuestionsId examQuestionsId) {
		ExamQuestions examQuestions = new ExamQuestions();
		examQuestions.setExamQuestionsId(examQuestionsId);
		sessionFactory.getCurrentSession().delete(examQuestions);	
	}

	@Override
	public boolean IsExist(String examQuestions) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(ExamQuestions.class);
		 cr.add(Restrictions.eq("examQuestionsName",examQuestions));
		 int results = cr.list().size();
		if(results>0) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public int totalExamQuestions() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamQuestions.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

}
