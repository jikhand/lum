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

import com.set.model.EmployeeMaster;
import com.set.model.StudentDetails;
import com.set.model.StudentMaster;
import com.set.model.StudentMasterDetils;
import com.set.model.SubjectMaster;
import com.set.model.User;
@Transactional
@Repository
public class StudentMasterDaoImpl implements StudentMasterDao {
@Autowired
private SessionFactory sessionFactory;
	
	@Override
	public void save(StudentMaster studentMaster) {
		sessionFactory.getCurrentSession().saveOrUpdate(studentMaster);// TODO Auto-generated method stub

	}

public StudentDetails stdList(int pagenumber,String searchdata) {

		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentMaster.class,"user");

		StudentDetails studentDetails = new StudentDetails();
		System.out.println("total number of record="+criteria.list().size());
		studentDetails.setCount(criteria.list().size());
		studentDetails.setUser((List<StudentMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return studentDetails;
	}
	@Override
	public StudentMasterDetils list(int pagenumber, String searchdata) {
		 System.out.println("searchdata="+searchdata);
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentMaster.class);
		if (!searchdata.equalsIgnoreCase("null")) {
		Disjunction or = Restrictions.disjunction();
		 System.out.println("searchdata="+searchdata);	
		or.add(Restrictions.like("permanentCity", searchdata, MatchMode.ANYWHERE))
		.add(Restrictions.like("mobilePhone", searchdata, MatchMode.ANYWHERE))
		.add(Restrictions.like("firstName", searchdata, MatchMode.ANYWHERE));
		criteria.add(or);
		}
		System.out.println("permanentCity +permanentCity");
		System.out.println("mobilePhone +mobilePhone");
		System.out.println("firstName +firstName");	
		StudentMasterDetils studentMasterDetils = new StudentMasterDetils();
		studentMasterDetils.setCount(criteria.list().size());
		studentMasterDetils.setStudentMasterList((List<StudentMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return studentMasterDetils;
	}

	@Override
	public StudentMaster getStudentMasterById(User user) {
		 Criteria cr= sessionFactory.getCurrentSession()
					.createCriteria(StudentMaster.class);
		 StudentMaster studentMaster =(StudentMaster)cr.
				 add(Restrictions.eq("user",user)).uniqueResult();
			 return studentMaster;
	}

	@Override
	public void updateUser(StudentMaster employeeMaster) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String StudentMasterId) {
		StudentMaster studentMaster = sessionFactory.getCurrentSession().get(StudentMaster.class, StudentMasterId);
		sessionFactory.getCurrentSession().delete(studentMaster);

	}

	@Override
	public StudentMaster findUserById(String UserId) {
		User user=new User();
		user.setUserId(UserId);
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(StudentMaster.class);
	 StudentMaster studentMaster =(StudentMaster)cr.
			 add(Restrictions.eq("user",user)).uniqueResult();
		 return studentMaster;
	}

	@SuppressWarnings("unchecked")
	@Override
	public StudentMasterDetils getClassStudents(int pageNo, String classId, String sectionId) {
		int maxPageData=10;
		int start = pageNo * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentMaster.class);
		criteria.add(Restrictions.eq("classId", classId));
		criteria.add(Restrictions.eq("sectionId", sectionId));
		StudentMasterDetils studentMasterDetils = new StudentMasterDetils();
		studentMasterDetils.setCount(criteria.list().size());
		studentMasterDetils.setStudentMasterList((List<StudentMaster>) criteria.list());
		criteria.setFirstResult(pageNo * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return studentMasterDetils;
	}

	@Override
	public StudentMaster getStudentMasterByStudentId(String studentId) {
		 Criteria cr= sessionFactory.getCurrentSession()
					.createCriteria(StudentMaster.class);
		 StudentMaster studentMaster =(StudentMaster)cr.
				 add(Restrictions.eq("studentId",studentId)).uniqueResult();
			 return studentMaster;
	}

}
