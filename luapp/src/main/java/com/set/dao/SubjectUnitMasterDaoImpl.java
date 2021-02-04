package com.set.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.set.model.DrawingCategory;
import com.set.model.NotesMaster;
import com.set.model.StudentNotes;
import com.set.model.SubjectUnit;
import com.set.model.SubjectUnitDetails;
import com.set.model.SubjectUnitMaster;
import com.set.model.SubjectUnitMasterDetails;
import com.set.model.SubjectUnitMasterId;
@Transactional
@Repository
public class SubjectUnitMasterDaoImpl implements SubjectUnitMasterDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("NotesDaoImp.class");

	@Override
	public void save(SubjectUnitMaster subjectUnitMaster) {
		sessionFactory.getCurrentSession().save(subjectUnitMaster);		
	}
	
	@Override
	public SubjectUnitMasterDetails getAllSubjectUnitMaster(int pagenumber, String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectUnitMaster.class);
		criteria.add(Restrictions.eq("is_soft_delete",0));
     	if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("TopicName", searchdata, MatchMode.ANYWHERE))
			  .add(Restrictions.like("TopicMaterialFiletype", searchdata, MatchMode.ANYWHERE));
//			  .add(Restrictions.like("TypeOfMaterial", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
			}
		SubjectUnitMasterDetails subjectUnitMasterDetails = new SubjectUnitMasterDetails();
		logger.info("total number of record="+criteria.list().size());
		subjectUnitMasterDetails.setCount(criteria.list().size());
		subjectUnitMasterDetails.setSubjectUnitMaster((List<SubjectUnitMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
     	
		return subjectUnitMasterDetails;
	}
	
	@Override
	public List<SubjectUnitMaster> getAllSubjectUnitMasterSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectUnitMaster.class); 
		criteria.add(Restrictions.eq("is_soft_delete",0));
		return (List<SubjectUnitMaster>) criteria.list();
	}

	@Override
	public SubjectUnitMaster getSubjectUnitMasterById(SubjectUnitMasterId subjectUnitMasterId) {
		return sessionFactory.getCurrentSession().get(SubjectUnitMaster.class, subjectUnitMasterId);
	}

	@Override
	public void updateSubjectUnitMaster(SubjectUnitMaster subjectUnitMaster) {
		sessionFactory.getCurrentSession().update(subjectUnitMaster);	
	}

	@Override
	public void deleteSubjectUnitMaster(SubjectUnitMaster subjectUnitMaster) {
		sessionFactory.getCurrentSession().update(subjectUnitMaster);	
	}

	@Override
	public boolean IsExist(String notes) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(SubjectUnitMaster.class);
		 cr.add(Restrictions.eq("TopicName",notes));
		 int results = cr.list().size();
		if(results>0) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public int totalSubjectUnitMaster() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectUnitMaster.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

	@Override
	public List<Map<String, String>> getUnitBySubjectList(String SubjectId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "DISTINCT(u.unit_id), u.unit_name FROM lutbl_subj_unit_mstr n join "
						+ "lutbl_subj_units u ON n.unit_id = u.unit_id WHERE " + "n.subj_id ='" + SubjectId + "'");
		List<Object[]> totalrows = query.list();
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (!row[0].toString().equalsIgnoreCase("null")) {
				hm.put("UnitId", row[0].toString());
			} else {
				hm.put("UnitId", "");
			}
			if (!row[1].toString().equalsIgnoreCase("null")) {
				hm.put("UnitName", row[1].toString());
			} else {
				hm.put("UnitName", "");
			}
			arrayList.add(hm);
		}
		return arrayList;

	}

	@Override
	public List<Map<String, String>> getTopicByUnitList(String SubjectId, String UnitId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + " topic_id, topic_name FROM lutbl_subj_unit_mstr WHERE " + "subj_id ='"
						+ SubjectId + "' and unit_id ='" + UnitId + "'");
		List<Object[]> totalrows = query.list();
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (!row[0].toString().equalsIgnoreCase("null")) {
				hm.put("TopicId", row[0].toString());
			} else {
				hm.put("TopicId", "");
			}
			if (!row[1].toString().equalsIgnoreCase("null")) {
				hm.put("TopicName", row[1].toString());
			} else {
				hm.put("TopicName", "");
			}
			arrayList.add(hm);
		}
		return arrayList;

	}
}
