package com.set.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.set.model.Assignment;
import com.set.model.AssignmentDetails;

@Transactional
@Repository
public class AssignmentDaoImp implements AssignmentDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public AssignmentDetails getAllAssignment(int pagenumber, String searchdata) {
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Assignment.class,"Assi");
		criteria.add(Restrictions.eq("IsDeleted",false));
//		if (!searchdata.equalsIgnoreCase("null")) {
//			
////			criteria.createAlias("user.address","address");
////			criteria.createAlias("user.tblProfile","tblProfile");
////			criteria.createAlias("address.city","city");
////			criteria.createAlias("city.stateMaster","state");
////			criteria.createAlias("state.country","country");
//			Disjunction or = Restrictions.disjunction();
//			or.add(Restrictions.like("AssignmentNumber", searchdata, MatchMode.ANYWHERE))
//			  .add(Restrictions.like("AssignmentType", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("AssignmentSubject", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("AssignmentMarks", searchdata, MatchMode.ANYWHERE));
//			   criteria.add(or);
//		}
		AssignmentDetails assignmentDetails = new AssignmentDetails();
		assignmentDetails.setCount(criteria.list().size());
		assignmentDetails.setAssignment((List<Assignment>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return assignmentDetails;
	}

	@Override
	public Assignment getAssignmentById(String AssignmentId) {
		System.out.println("AssignmentId: "+AssignmentId);
		return sessionFactory.getCurrentSession().get(Assignment.class, AssignmentId);
	}

	@Override
	public void save(Assignment assignment) {
		sessionFactory.getCurrentSession().saveOrUpdate(assignment);

	}

	@Override
	public void deleteAssignment(String id) {
		Assignment assignment=this.getAssignmentById(id);
		assignment.setIsDeleted(true);
		sessionFactory.getCurrentSession().update(assignment);
	}

	@Override
	public boolean IsExist(String searchcontent, long id) {
		return false;
	}

}
