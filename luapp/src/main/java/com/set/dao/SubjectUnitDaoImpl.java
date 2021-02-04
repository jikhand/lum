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

import com.set.model.ClassSubject;
import com.set.model.DrawingCategory;
import com.set.model.StudentNotes;
import com.set.model.StudentNotesDetails;
import com.set.model.SubjectUnit;
import com.set.model.SubjectUnitDetails;
@Transactional
@Repository
public class SubjectUnitDaoImpl implements SubjectUnitDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("NotesDaoImp.class");

	@Override
	public void save(SubjectUnit subjectUnit) {
		sessionFactory.getCurrentSession().save(subjectUnit);		
	}
	
	@Override
	public SubjectUnitDetails getAllSubjectUnit(int pagenumber, String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectUnit.class);
		//criteria.add(Restrictions.eq("isDeleted",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("UnitName", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
			}
		SubjectUnitDetails subjectUnitDetails = new SubjectUnitDetails();
		logger.info("total number of record="+criteria.list().size());
		subjectUnitDetails.setCount(criteria.list().size());
		subjectUnitDetails.setSubjectUnit((List<SubjectUnit>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return subjectUnitDetails;
	}

	@Override
	public SubjectUnit getSubjectUnitById(String notesId) {
//		System.out.println("UID.getSubjectUnitId="+notesId);;
//		Criteria cr= sessionFactory.getCurrentSession()
//				.createCriteria(SubjectUnit.class);
//		 cr.add(Restrictions.eq("SubjectUnitId",notesId.getUnitId()));
//		 List results = cr.list();
//		 System.out.println("results size"+results.size());
//		 SubjectUnit subjectUnit = (SubjectUnit)cr.uniqueResult(); 
//		 return subjectUnit;
		return sessionFactory.getCurrentSession().get(SubjectUnit.class, notesId);
	}

	@Override
	public void updateSubjectUnit(SubjectUnit subjectUnit) {
		sessionFactory.getCurrentSession().update(subjectUnit);	
	}

	@Override
	public void deleteSubjectUnit(SubjectUnit subjectUnit) {
		sessionFactory.getCurrentSession().update(subjectUnit);	
	}

	@Override
	public boolean IsExist(String notes) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(SubjectUnit.class);
		 cr.add(Restrictions.eq("UnitName",notes));
		 int results = cr.list().size();
		if(results>0) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public int totalSubjectUnit() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectUnit.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

	@Override
	public List<Object[]> getAllUnitSubjectClassSelect(String teacherId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "DISTINCT(sub.section_id), sub.class_id, sec.class_name, sec.section_name FROM "
				+ "lutbl_class_subj sub join lutbl_class_sec_master sec ON sub.class_id"
				+ " = sec.class_id and sub.section_id = sec.section_id WHERE "
				+ "is_soft_delete=0 and teacher_id = '"+teacherId+"'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllUnitSubjectSelect(String classId, String sectionId, String teacherId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ " subj_id, subject_name FROM lutbl_class_subj WHERE "
				+ "class_id= '"+classId+"' and section_id= '"+sectionId+"' and "
				+ "is_soft_delete=0 and teacher_id = '"+teacherId+"'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllUnitSelect(String subjectId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "DISTINCT(u.unit_id), u.unit_name FROM "
				+ "lutbl_subj_unit_mstr s join lutbl_subj_units u ON s.unit_id"
				+ " = u.unit_id WHERE "
				+ "s.is_soft_delete=0 and s.subj_id = '"+subjectId+"'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllTopicSelect(String subjectId, String unitId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "topic_id, topic_name FROM "
				+ "lutbl_subj_unit_mstr WHERE unit_id ='"+unitId+"' and "
				+ "is_soft_delete=0 and subj_id = '"+subjectId+"'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<SubjectUnit> getAllSubjectUnit() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectUnit.class);
		List<SubjectUnit> subjectUnit=criteria.list();
		return subjectUnit;
	}
	

}
