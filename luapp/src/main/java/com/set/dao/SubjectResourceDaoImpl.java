package com.set.dao;

import java.time.LocalDateTime;
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
import com.set.model.SubjectResource;
import com.set.model.SubjectResourceDetails;
import com.set.model.SubjectResourceId;

@Transactional
@Repository
public class SubjectResourceDaoImpl implements SubjectResourceDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("NotesDaoImp.class");

	@Override
	public void save(SubjectResource subjectResource) {
		sessionFactory.getCurrentSession().save(subjectResource);
	}

	@Override
	public SubjectResourceDetails getAllSubjectResource(int pagenumber, String searchdata) {
		String searchString = "";
		if (!searchdata.equalsIgnoreCase("null")) {
			searchString = " and s.subject_name LIKE '%" + searchdata +
					"%' or r.resource_title LIKE '%" + searchdata
					+ "%' or u.unit_name LIKE '%"+ searchdata+
					"%' or r.resource_type LIKE '%"+searchdata+"%'";
			
			
//			searchString = " and s.subject_name LIKE '%" + searchdata + "%'"
//					+ "%' or r.resource_title LIKE '%" + searchdata
//					+ "%' or r.resource_type LIKE '%" + searchdata
//					+ "%' or s.subject_name LIKE '%" + searchdata
//					+ "%' or u.unit_name LIKE '%" + searchdata+ "%'";
			
		}
		SubjectResourceDetails subjectResourceDetails=new SubjectResourceDetails();
		String strQuery = "SELECT " + "r.resource_id, r.subj_id, r.unit_id, "
				+ "r.resource_extension, r.resource_link, r.resource_path, "
				+ "r.resource_title, r.resource_type, r.thumbnail_image, " + "u.unit_name, "
				+ "s.subject_name FROM lutbl_subject_resource as r "
				+ "join lutbl_subject_master s ON r.subj_id = s.subj_id "
				+ "join lutbl_subj_units u ON r.unit_id = u.unit_id WHERE "
				+ "r.is_soft_delete='0' and s.is_soft_delete='0' " + searchString;
		System.out.println("strQuery" + strQuery);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery);
		List<Object[]> totalrows = query.list();
		int totalNumbers = totalrows.size();
		int maxPageData = 10;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (null != row[0]) {
				hm.put("ResourceId", row[0].toString());
			} else {
				hm.put("ResourceId", "");
			}

			if (null != row[1]) {
				hm.put("SubjectId", row[1].toString());
			} else {
				hm.put("SubjectId", "");
			}
			if (null != row[2]) {
				hm.put("UnitId", row[2].toString());
			} else {
				hm.put("UnitId", row[2].toString());
			}
			if (null != row[3]) {
				hm.put("ResourceExtension", row[3].toString());
			} else {
				hm.put("ResourceExtension", "");
			}
			if (null != row[4]) {
				hm.put("ResourceLink", row[4].toString());
			} else {
				hm.put("ResourceLink", "");
			}
			if (null != row[5]) {
				hm.put("ResourcePath", row[5].toString());
			} else {
				hm.put("ResourcePath", "");
			}
			if (null != row[6]) {
				hm.put("ResourceTitle", row[6].toString());
			} else {
				hm.put("ResourceTitle", "");
			}
			if (null != row[7]) {
				hm.put("ResourceType", row[7].toString());
			} else {
				hm.put("ResourceType", "");
			}
			if (null != row[8]) {
				hm.put("ThumbnailImage", row[8].toString());
			} else {
				hm.put("ThumbnailImage", "");
			}
			if (null != row[9]) {
				hm.put("UnitName", row[9].toString());
			} else {
				hm.put("UnitName", "");
			}
			if (null != row[10]) {
				hm.put("SubjectName", row[10].toString());
			} else {
				hm.put("SubjectName", "");
			}
			arrayList.add(hm);
		}
		subjectResourceDetails.setSubjectResource(arrayList);
		subjectResourceDetails.setCount(totalNumbers);
		return subjectResourceDetails;
	}

	@Override
	public List<SubjectResource> getAllSubjectResourceSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectResource.class);
		criteria.add(Restrictions.eq("is_soft_delete", 0));
		return (List<SubjectResource>) criteria.list();
	}

	@Override
	public SubjectResource getSubjectResourceById(SubjectResourceId subjectResourceId) {
		return sessionFactory.getCurrentSession().get(SubjectResource.class, subjectResourceId);
	}

	@Override
	public void updateSubjectResource(SubjectResource subjectResource) {
		subjectResource.getSubjectResourceId().getResourceId();
		subjectResource.getSubjectResourceId().getUnitId();
		subjectResource.getSubjectResourceId().getSubjectId();
		sessionFactory.getCurrentSession().update(subjectResource);
	}

	@Override
	public void deleteSubjectResource(SubjectResource subjectResource) {
		sessionFactory.getCurrentSession().update(subjectResource);
	}

	@Override
	public boolean IsExist(String notes, String resourceId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(SubjectResource.class);
		System.out.println("notes" + notes);
		cr.add(Restrictions.eq("ResourceTitle", notes));
		int strlen=resourceId.length();
		if (strlen>0) {
			cr.add(Restrictions.not(Restrictions.eq("SubjectResourceId.ResourceId", resourceId)));
		}
		cr.add(Restrictions.eq("is_soft_delete", 0));
		int results = cr.list().size();
		System.out.println("results=" + results);
		if (results > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int totalSubjectResource() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubjectResource.class);
		int totalNumbers = criteria.list().size();
		return totalNumbers;
	}

	@Override
	public List<Object[]> getAllResourceBankSubjectSelect(String classId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "DISTINCT(r.subj_id), s.subject_name FROM lutbl_subject_resource r "
						+ "join lutbl_class_subj s ON s.subj_id = r.subj_id WHERE s.class_id " + "= '" + classId
						+ "' and r.is_soft_delete = 0 and s.is_soft_delete = 0");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllResourceBankUnitSelect(String subjectId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "DISTINCT(r.unit_id), u.unit_name FROM lutbl_subject_resource r"
						+ " join lutbl_subj_units u ON u.unit_id = r.unit_id WHERE " + "r.subj_id = '" + subjectId
						+ "' and r.is_soft_delete = 0");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllResourceBankSelect(String unitId, String SubjectId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "resource_id, resource_extension, resource_link, "
						+ "resource_path, resource_title, resource_type, "
						+ "thumbnail_image, subj_id, unit_id FROM lutbl_subject_resource WHERE" + " unit_id='" + unitId
						+ "' and subj_id = '" + SubjectId + "' and " + "is_soft_delete = 0");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankClassSelect(String teacherId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "DISTINCT(sub.class_id), sec.class_name FROM "
						+ "lutbl_class_subj sub join lutbl_class_sec_master sec ON sub.class_id"
						+ " = sec.class_id WHERE is_soft_delete=0 and " + "teacher_id = '" + teacherId + "'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankSubjectSelect(String teacherId, String classId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + " subj_id, subject_name FROM lutbl_class_subj WHERE " + "class_id= '"
						+ classId + "' and " + "is_soft_delete=0 and teacher_id = '" + teacherId + "'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankUnitSelect(String subjectId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "DISTINCT(u.unit_id), u.unit_name FROM "
						+ "lutbl_subj_unit_mstr s join lutbl_subj_units u ON s.unit_id" + " = u.unit_id WHERE "
						+ "s.is_soft_delete=0 and s.subj_id = '" + subjectId + "'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankSelect(String unitId, String subjectId) {
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "resource_id, resource_extension, resource_link, "
						+ "resource_path, resource_title, resource_type, "
						+ "thumbnail_image, subj_id, unit_id FROM lutbl_subject_resource WHERE" + " unit_id='" + unitId
						+ "' and subj_id = '" + subjectId + "' and " + "is_soft_delete = 0");
		return (List<Object[]>) query.list();
	}

}
