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

import com.set.model.ExamType;
import com.set.model.ExamTypeDetails;
@Transactional
@Repository
public class ExamTypeDaoImpl implements ExamTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("ExamTypeImp.class");

	@Override
	public void save(ExamType examType) {
		sessionFactory.getCurrentSession().save(examType);		
	}
	@Override
	public ExamTypeDetails getAllExamType() {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamType.class);
		ExamTypeDetails examTypeDetails = new ExamTypeDetails();
		logger.info("total number of record="+criteria.list().size());
		examTypeDetails.setCount(criteria.list().size());
		examTypeDetails.setExamType((List<ExamType>) criteria.list());
		return examTypeDetails;
	}
	
	@Override
	public List<ExamType> getAllExamTypeSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamType.class); 
		return (List<ExamType>) criteria.list();
	}

	@Override
	public ExamType getExamTypeById(String examTypeId) {
		return sessionFactory.getCurrentSession().get(ExamType.class, examTypeId);
	}

	@Override
	public void updateExamType(ExamType examType) {
		sessionFactory.getCurrentSession().update(examType);	
	}

	@Override
	public void deleteExamType(ExamType examType) {
		sessionFactory.getCurrentSession().update(examType);	
	}

	@Override
	public boolean IsExist(String examType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int totalExamType() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamType.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}


}
