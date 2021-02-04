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

import com.set.model.QuestionPaper;
import com.set.model.QuestionPaperDetails;
import com.set.model.QuestionPaperId;
import com.set.model.SubjectUnitMaster;
import com.set.model.User;
@Transactional
@Repository
public class QuestionPaperDaoImpl implements QuestionPaperDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("QuestionPaperImp.class");

	@Override
	public void save(QuestionPaper questionPaper) {
		sessionFactory.getCurrentSession().save(questionPaper);		
	}
	@Override
	public QuestionPaperDetails getAllQuestionPaper(int pagenumber, String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionPaper.class);
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
		QuestionPaperDetails questionPaperDetails = new QuestionPaperDetails();
		logger.info("total number of record="+criteria.list().size());
		questionPaperDetails.setCount(criteria.list().size());
		//questionPaperDetails.setQuestionPaper((List<QuestionPaper>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return questionPaperDetails;
	}
	@Override
	public List<QuestionPaper> getAllQuestionPaperSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionPaper.class); 
		criteria.add(Restrictions.eq("isSoftDelete",0));
		return (List<QuestionPaper>) criteria.list();
	}

	@Override
	public QuestionPaper getQuestionPaperById(QuestionPaperId questionPaperId) {
		return sessionFactory.getCurrentSession().get(QuestionPaper.class, questionPaperId);
	}

	@Override
	public void updateQuestionPaper(QuestionPaper questionPaper) {
		sessionFactory.getCurrentSession().update(questionPaper);	
	}

	@Override
	public void deleteQuestionPaper(QuestionPaper questionPaper) {
		sessionFactory.getCurrentSession().update(questionPaper);	
	}

	@Override
	public boolean IsQuestionAlreadyExist(QuestionPaperId questionPaperId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(QuestionPaper.class);
		 cr.add(Restrictions.eq("questionPaperId",questionPaperId));
		 int results = cr.list().size();
		if(results>0) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public int totalQuestionPaper() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionPaper.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}
	@Override
	public List<Object[]> getAllQuestionTypeList(String examId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " DISTINCT(q.question_type), t.description, q.no_of_questions, e.marks_for_question"
				+ " FROM lutbl_exam_quest_Details q join lutbl_qustn_type t ON"
				+ " q.question_type = t.question_type join lutbl_question_bank b"
				+ " ON b.question_type_id = t.question_type join lutbl_question_paper e"
				+ " ON e.question_id = b.question_id "
				+ "WHERE q.exam_id = '"+examId+"' and b.is_soft_delete='0' and e.is_soft_delete='0'");
		return (List<Object[]>) query.list();
	}
	
	@Override
	public List<Object[]> getAllQuestionList(String examId, String questionTypeId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " q.exam_id, q.question_id, q.questn_no, b.question_type_id, "
				+ "b.question, b.question_path, q.marks_for_question, "
				+ "b.opta, b.optb, b.optc, b.optd, b.lsoa, b.lsob, b.lsoc, b.lsod, "
				+ "b.lsoe, b.rso1, b.rso2, b.rso3, b.rso4, b.rso5,b.ans, b.ans_mtfa,"
				+ " b.ans_mtfb, b.ans_mtfc, b.ans_mtfd, b.ans_mtfe, b.ans_path "
				+ "FROM lutbl_question_paper q join lutbl_question_bank b ON "
				+ "q.question_id=b.question_id WHERE "
				+ "q.exam_id = '"+examId+"' and b.question_type_id = '"+questionTypeId+"'"
				+ " and b.is_soft_delete='0' and q.is_soft_delete='0'");
		return (List<Object[]>) query.list();
	}



}
