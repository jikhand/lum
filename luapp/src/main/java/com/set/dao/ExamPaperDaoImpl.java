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

import com.set.model.ExamPaper;
import com.set.model.ExamPaperDetails;
import com.set.model.ExamPaperId;
import com.set.model.User;
@Transactional
@Repository
public class ExamPaperDaoImpl implements ExamPaperDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("ExamPaperImp.class");

	@Override
	public void save(ExamPaper examPaper) {
		sessionFactory.getCurrentSession().save(examPaper);		
	}
	@Override
	public ExamPaperDetails getAllExamPaper(int pagenumber, String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamPaper.class);
		criteria.add(Restrictions.eq("isSoftDelete",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("questionDescription", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("questionType", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("optionA", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("optionB", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("optionC", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("optionD", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("answer", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
		}
		ExamPaperDetails examPaperDetails = new ExamPaperDetails();
		logger.info("total number of record="+criteria.list().size());
		examPaperDetails.setCount(criteria.list().size());
		examPaperDetails.setExamPaper((List<ExamPaper>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return examPaperDetails;
	}
	@Override
	public List<ExamPaper> getAllExamPaperSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamPaper.class); 
		criteria.add(Restrictions.eq("isSoftDelete",0));
		return (List<ExamPaper>) criteria.list();
	}

	@Override
	public ExamPaper getExamPaperById(ExamPaperId examPaperId) {
		return sessionFactory.getCurrentSession().get(ExamPaper.class, examPaperId);
	}

	@Override
	public void updateExamPaper(ExamPaper examPaper) {
		sessionFactory.getCurrentSession().update(examPaper);	
	}

	@Override
	public void deleteExamPaper(ExamPaper examPaper) {
		sessionFactory.getCurrentSession().update(examPaper);	
	}

	@Override
	public boolean IsExist(String examPaper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int totalExamPaper() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamPaper.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}
	@Override
	public List<Object[]> getAllQuestionTypeList(String examId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " q.question_type, t.description, q.no_of_questions, e.marks_for_question FROM lutbl_exam_quest_Details q "
				+ "join lutbl_qustn_type t ON q.question_type = t.question_type "
				+ "join lutbl_exampaper e ON q.exam_id = e.exam_id and "
				+ "q.question_type = e.question_type "
				+ "WHERE q.exam_id = '"+examId+"' GROUP BY q.question_type");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllQuestionList(String examId, String questionTypeId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " exam_id, question_id, questn_no, question_type, "
				+ "question_desc, question_path, marks_for_question, "
				+ "opta, optb, optc, optd, lsoa, lsob, lsoc, lsod, "
				+ "lsoe, rso1, rso2, rso3, rso4, rso5,ans, ans_mtfa,"
				+ " ans_mtfb, ans_mtfc, ans_mtfd, ans_mtfe, ans_path "
				+ "FROM lutbl_exampaper WHERE "
				+ "exam_id = '"+examId+"' and question_type = '"+questionTypeId+"'");
		return (List<Object[]>) query.list();
	}



}
