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
import com.set.model.ClassSubject;
import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterDetils;
import com.set.model.UserRegistration;
@Transactional
@Repository
public class SubjectMasterDaoImpl implements SubjectMasterDao {
    @Autowired
    private SessionFactory sessionFactory;
	@Override
	public void save(SubjectMaster subjectMaster) {
		sessionFactory.getCurrentSession().save(subjectMaster);
	}

	@Override
	public SubjectMasterDetils list(int pagenumber, String searchdata) {
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectMaster.class);
		criteria.add(Restrictions.eq("is_soft_delete",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("subjectName", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("textBookName", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
			}
			System.out.println("subjectName +subjectName");
			System.out.println("textBookName +textBookName");
			SubjectMasterDetils subjectMasterDetils = new SubjectMasterDetils();
			subjectMasterDetils.setCount(criteria.list().size());
			subjectMasterDetils.setSubjectMaster((List<SubjectMaster>) criteria.list());
			criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
			criteria.setMaxResults(end);	
			
			
			return subjectMasterDetils;
	}
	//	int maxPageData=10;
	//	int start = pagenumber * maxPageData - maxPageData;
	//	int end =10;
	//	String searchString="";
	//	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectMaster.class);
		//criteria.add(Restrictions.eq("IsDelete",false));
//		if (!searchdata.equalsIgnoreCase("null")) {
//			
//			criteria.createAlias("user.address","address");
//			criteria.createAlias("user.tblProfile","tblProfile");
//			criteria.createAlias("address.city","city");
//			criteria.createAlias("city.stateMaster","state");
//			criteria.createAlias("state.country","country");
//			Disjunction or = Restrictions.disjunction();
//			or.add(Restrictions.like("city.cityName", searchdata, MatchMode.ANYWHERE))
//			  .add(Restrictions.like("state.stateName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("country.countryName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.FirstName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.MiddleName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.LastName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.Email", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.Mobile", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("tblProfile.ProfileType", searchdata, MatchMode.ANYWHERE));
//			   criteria.add(or);
//		}
	//	SubjectMasterDetils subjectMasterDetils = new SubjectMasterDetils();
	//	subjectMasterDetils.setCount(criteria.list().size());
	//	subjectMasterDetils.setSubjectMaster((List<SubjectMaster>) criteria.list());
	//	criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
	//	criteria.setMaxResults(end);	
	//	return subjectMasterDetils;
	//}

	@Override
	public SubjectMaster getSubjectMasterById(SubjectMaster UID) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(SubjectMaster.class);
		 cr.add(Restrictions.eq("subjectMasterId",UID.getSubjectMasterId()));
		 List results = cr.list();
		 SubjectMaster subjectMaster = (SubjectMaster)cr.uniqueResult(); 
		 return subjectMaster;
	}

	@Override
	public void updateSubjectMaster(SubjectMaster subjectMaster) {
		sessionFactory.getCurrentSession().update(subjectMaster);
	}

	@Override
	public void deleteSubjectMaster(SubjectMaster SubjectMasterId) {
		// TODO Auto-generated method stub
      sessionFactory.getCurrentSession().update(SubjectMasterId);
	}

	@Override
	public boolean IsExist(String SubjectName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int totalSubjectMaster() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectMaster.class);
		//criteria.add(Restrictions.eq("IsDeleted",false));
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

	@Override
	public List<SubjectMaster> getAllSubjectMasterSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectMaster.class); 
		return (List<SubjectMaster>) criteria.list();
	}

}
