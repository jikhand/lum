package com.set.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.set.model.QuestionBank;
import com.set.model.QuestionBankDetails;
import com.set.model.User;
@Transactional
@Repository
public class QuestionBankDaoImpl implements QuestionBankDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("QuestionBankImp.class");

	@Override
	public void save(QuestionBank questionBank) {
		sessionFactory.getCurrentSession().save(questionBank);		
	}
	
	@Override
	public void updateQuestionBank(QuestionBank questionBank) {
		sessionFactory.getCurrentSession().update(questionBank);	
	}
	
	@Override
	public List<Object[]> getAllQuestionList(String classid, String subjectid, String questionTypeId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "question_id, ans, ans_mtfa, ans_mtfb, ans_mtfc, ans_mtfd, ans_mtfe, "
				+ "ans_path, class_id, lsoa, lsob, lsoc, lsod, lsoe, opta, optb, optc,"
				+ " optd, question, question_path, question_type_id, rso1, rso2, rso3,"
				+ " rso4, rso5, subject_id FROM lutbl_question_bank WHERE class_id ="
				+ " '"+classid+"' and subject_id = '"+subjectid+"' and "
				+ "question_type_id = '"+questionTypeId+"' and "
				+ "is_soft_delete = '0'");
		return (List<Object[]>) query.list();
	}
	
	@Override
	public List<Object[]> getAllQuestionListByDate(String classid, String subjectid, String questionTypeId, String questionDate) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "question_id, ans, ans_mtfa, ans_mtfb, ans_mtfc, ans_mtfd, ans_mtfe, "
				+ "ans_path, class_id, lsoa, lsob, lsoc, lsod, lsoe, opta, optb, optc,"
				+ " optd, question, question_path, question_type_id, rso1, rso2, rso3,"
				+ " rso4, rso5, subject_id FROM lutbl_question_bank WHERE class_id ="
				+ " '"+classid+"' and subject_id = '"+subjectid+"' and "
				+ "question_type_id = '"+questionTypeId+"' and  DATE(inserted_time) ="
				+ " '"+questionDate+"' or DATE(updated_time) = '"+questionDate+"' and "
				+ "is_soft_delete = '0'");
		return (List<Object[]>) query.list();
	}



}
