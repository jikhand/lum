package com.set.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.AssignmentEvaluation;
import com.set.model.AssignmentEvaluationDetails;
import com.set.model.AssignmentEvaluationId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.ClassSectionMasterId;

@Transactional
@Repository
public class AssignmentEvaluationDaoImpl implements AssignmentEvaluationDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(AssignmentEvaluation assignmentEvaluation) {
		sessionFactory.getCurrentSession().saveOrUpdate(assignmentEvaluation);
	}

	@Override
	public AssignmentEvaluationDetails list(int pagenumber, String searchdata) {
		System.out.println("searchdata="+searchdata);
		int maxPageData = 10;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentEvaluation.class);
		// criteria.add(Restrictions.eq("IsDelete",false));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			 System.out.println("searchdata="+searchdata);
		//
		// criteria.createAlias("user.address","address");
		// criteria.createAlias("user.tblProfile","tblProfile");
		// criteria.createAlias("address.city","city");
		// criteria.createAlias("city.stateMaster","state");
		// criteria.createAlias("state.country","country");
		// Disjunction or = Restrictions.disjunction();
		 or.add(Restrictions.like("assignPath", searchdata, MatchMode.ANYWHERE));
		// .add(Restrictions.like("marksObtained", searchdata, MatchMode.ANYWHERE));
		// add(Restrictions.like("country.countryName", searchdata,
		// MatchMode.ANYWHERE)).
		// add(Restrictions.like("user.FirstName", searchdata, MatchMode.ANYWHERE)).
		// add(Restrictions.like("user.MiddleName", searchdata, MatchMode.ANYWHERE)).
		// add(Restrictions.like("user.LastName", searchdata, MatchMode.ANYWHERE)).
		// add(Restrictions.like("user.Email", searchdata, MatchMode.ANYWHERE)).
		// add(Restrictions.like("user.Mobile", searchdata, MatchMode.ANYWHERE)).
		// add(Restrictions.like("tblProfile.ProfileType", searchdata,
		// MatchMode.ANYWHERE));
		 criteria.add(or);
		 }
		System.out.println("assignPath +assignPath");
	//	System.out.println("marksObtained +marksObtained");
		AssignmentEvaluationDetails assignmentEvaluationDetails = new AssignmentEvaluationDetails();
		assignmentEvaluationDetails.setCount(criteria.list().size());
		assignmentEvaluationDetails.setAssignmentEvaluation((List<AssignmentEvaluation>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);
		return assignmentEvaluationDetails;
	}

	@Override
	public AssignmentEvaluation getElementById(AssignmentEvaluation UID) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(AssignmentEvaluation.class);
		cr.add(Restrictions.eq("assignmentEvaluationId", UID.getAssignmentEvaluationId()));
		AssignmentEvaluation assignmentEvaluation = (AssignmentEvaluation) cr.uniqueResult();
		return assignmentEvaluation;
	}

	@Override
	public void update(AssignmentEvaluation assignmentEvaluation) {
		sessionFactory.getCurrentSession().update(assignmentEvaluation);
	}

	@Override
	public void deleteElementById(AssignmentEvaluation assignmentEvaluation) {
		sessionFactory.getCurrentSession().update(assignmentEvaluation);
	}

	@Override
	public boolean IsExist(AssignmentEvaluationId assignmentEvaluationId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(AssignmentEvaluation.class);
		cr.add(Restrictions.eq("assignmentEvaluationId.studentId", assignmentEvaluationId.getStudentId()));
		cr.add(Restrictions.eq("assignmentEvaluationId.assignId", assignmentEvaluationId.getAssignId()));
		AssignmentEvaluation assignmentEvaluation = (AssignmentEvaluation) cr.uniqueResult();
		if (null != assignmentEvaluation) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int gettotalcount() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentEvaluation.class);
		// criteria.add(Restrictions.eq("IsDeleted",false));
		int totalNumbers = criteria.list().size();
		return totalNumbers;
	}

	@Override
	public AssignmentEvaluationDetails getClassSectionAssignments(int pagenumber,
			ClassSectionMasterId classSectionMasterId) {
		AssignmentEvaluationDetails assignmentEvaluationDetails = new AssignmentEvaluationDetails();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentEvaluation.class);
		criteria.add(Restrictions.eq("assignmentEvaluationId.classId", classSectionMasterId.getClassId()));
		criteria.add(Restrictions.eq("assignmentEvaluationId.sectionId", classSectionMasterId.getSectionId()));
		List<AssignmentEvaluation> aeList = criteria.list();
		assignmentEvaluationDetails.setAssignmentEvaluation(aeList);
		assignmentEvaluationDetails.setCount(aeList.size());
		return assignmentEvaluationDetails;
	}

	@Override
	public void savePage(AssignmentPages assignmentPages) {
		sessionFactory.getCurrentSession().saveOrUpdate(assignmentPages);
	}

	@Override
	public AssignmentPagesDetails getPagesByAssignmentId(int pagenumber, AssignmentPageId assignmentPageId) {
		int maxPageData = 20;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		AssignmentPagesDetails assignmentPagesDetails = new AssignmentPagesDetails();
		String searchString = "";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentPages.class);
		criteria.add(Restrictions.eq("AssignmentPageId.AssignmentId", assignmentPageId.getAssignmentId()));
		criteria.add(Restrictions.eq("AssignmentPageId.StudentId", assignmentPageId.getStudentId()));
		assignmentPagesDetails.setAssignmentPages((List<AssignmentPages>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		assignmentPagesDetails.setCount(criteria.list().size());
		return assignmentPagesDetails;
	}

	@Override
	public AssignmentPagesDetails getAssignmentPages(AssignmentPageId assignmentPageId) {
		AssignmentPagesDetails assignmentPagesDetails = new AssignmentPagesDetails();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentPages.class);
//		Disjunction or = Restrictions.disjunction();
//		or.add(Restrictions.eq("AssignmentPageId.Pagenumber", assignmentPageId.getPageNo()));
//		criteria.add(or);
		criteria.add(Restrictions.eq("AssignmentPageId.AssignmentId", assignmentPageId.getAssignmentId()));
		criteria.add(Restrictions.eq("AssignmentPageId.StudentId", assignmentPageId.getStudentId()));
		assignmentPagesDetails.setAssignmentPages((List<AssignmentPages>) criteria.list());
		assignmentPagesDetails.setCount(criteria.list().size());
		return assignmentPagesDetails;
	}

	@Override
	public AssignmentEvaluation getAssignmentEvaluaitonById(AssignmentEvaluationId assignmentEvaluationId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(AssignmentEvaluation.class);
		cr.add(Restrictions.eq("assignmentEvaluationId", assignmentEvaluationId));
		AssignmentEvaluation assignmentEvaluation = (AssignmentEvaluation) cr.uniqueResult();
		return assignmentEvaluation;
	}

	@Override
	public AssignmentEvaluationDetails studentSubmittedAssignments(AssignmentEvaluationId assignmentEvaluationId) {
		AssignmentEvaluationDetails assignmentEvaluationDetails = new AssignmentEvaluationDetails();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentEvaluation.class);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.eq("assignmentEvaluationId.studentId", assignmentEvaluationId.getStudentId()))
				.add(Restrictions.eq("assignmentEvaluationId.assignId", assignmentEvaluationId.getAssignId()));
		criteria.add(or);
		List<AssignmentEvaluation> aeList = criteria.list();
		assignmentEvaluationDetails.setAssignmentEvaluation(aeList);
		assignmentEvaluationDetails.setCount(aeList.size());
		return assignmentEvaluationDetails;
	}
}
