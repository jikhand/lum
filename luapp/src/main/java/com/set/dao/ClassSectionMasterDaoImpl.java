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

import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterDetails;
import com.set.model.ClassSubject;
import com.set.model.SchoolClass;
import com.set.model.SubjectMaster;

@Transactional
@Repository
public class ClassSectionMasterDaoImpl implements ClassSectionMasterDao{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void save(ClassSectionMaster classSectionMaster) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(classSectionMaster);
	}

	@Override
	public ClassSectionMasterDetails list(int pagenumber, String searchdata) {
	   System.out.println("searchdata="+searchdata);
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSectionMaster.class);
	//	criteria.add(Restrictions.eq("IsDelete",false));
		if (!searchdata.equalsIgnoreCase("null")) {
		
			
//			
//			criteria.createAlias("user.address","address");
//			criteria.createAlias("user.tblProfile","tblProfile");
//			criteria.createAlias("address.city","city");
//			criteria.createAlias("city.stateMaster","state");
//			criteria.createAlias("state.country","country");
 			Disjunction or = Restrictions.disjunction();
 			 System.out.println("searchdata="+searchdata);
 		//	System.out.println("className +className");
 			or.add(Restrictions.like("className", searchdata,MatchMode.ANYWHERE));
//			or.add(Restrictions.like("city.cityName", searchdata, MatchMode.ANYWHERE))
//			  .add(Restrictions.like("state.stateName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("country.countryName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.FirstName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.MiddleName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.LastName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.Email", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.Mobile", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("tblProfile.ProfileType", searchdata, MatchMode.ANYWHERE));
 			   criteria.add(or);
		}
		
		ClassSectionMasterDetails classSectionMasterDetails = new ClassSectionMasterDetails();
		classSectionMasterDetails.setCount(criteria.list().size());
		classSectionMasterDetails.setClassSectionMaster((List<ClassSectionMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return classSectionMasterDetails;
	}

	@Override
	public ClassSectionMaster getElementById(ClassSectionMaster classSectionMaster) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(ClassSectionMaster.class);
		 cr.add(Restrictions.eq("classSectionMasterId",classSectionMaster.getClassSectionMasterId()));
		 List results = cr.list();
		 ClassSectionMaster newclassSectionMaster = (ClassSectionMaster)cr.uniqueResult(); 
		 return newclassSectionMaster;
	}

	@Override
	public void update(ClassSectionMaster classSectionMaster) {
		sessionFactory.getCurrentSession().update(classSectionMaster);
	}

	@Override
	public void deleteElementById(ClassSectionMaster classSectionMaster) {
		sessionFactory.getCurrentSession().update(classSectionMaster);
		
	}

	@Override
	public boolean IsExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int gettotalcount() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSectionMaster.class);
		//criteria.add(Restrictions.eq("IsDeleted",false));
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

	@Override
	public List<ClassSectionMaster> getAllClassSectionMasterSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSectionMaster.class); 
		return (List<ClassSectionMaster>) criteria.list();
	}
	
	@Override
	public List<SchoolClass> getSchoolClass()  {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SchoolClass.class);
		return (List<SchoolClass>)criteria.list();
	}

}
