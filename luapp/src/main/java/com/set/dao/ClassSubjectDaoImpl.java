package com.set.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetail;
import com.set.model.ClassSubjectDetails;
import com.set.model.ClassSubjectId;
import com.set.model.ServiceRequestDetails;
import com.set.model.StudentMaster;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.service.StudentMasterService;
import com.set.utils.Constant;

@Transactional
@Repository
public class ClassSubjectDaoImpl implements ClassSubjectDao{

	private Logger logger = Logger.getLogger("ClassSubjectDaoImpl.class");

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private StudentMasterService studentMasterService;

	@Override
	public void save(ClassSubject ClassSubject) {
		sessionFactory.getCurrentSession().saveOrUpdate(ClassSubject);		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public ClassSubjectDetails list(int pagenumber, String searchdata) {
		int maxPageData = 10;
		int end = 10;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSubject.class);
		ClassSubjectDetails classSubjectDetails = new ClassSubjectDetails();
		System.out.println("total number of record="+criteria.list().size());
		classSubjectDetails.setCount(criteria.list().size());
		classSubjectDetails.setClassSubject((List<ClassSubject>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return classSubjectDetails;
	}
	/*
	 * @description List of Users
	 * @param @token
	 * @return List of User
	 *
	 */

	@SuppressWarnings("deprecation")
	@Override
	public ClassSubject getElementById(ClassSubject UID) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(ClassSubject.class);
		cr.add(Restrictions.eq("classSubjectId", UID.getClassSubjectId()));
		List<?> results = cr.list();
		System.out.println("results size" + results.size());
		ClassSubject classSubject = (ClassSubject) cr.uniqueResult();
		return classSubject;
	}

	@Override
	public void update(ClassSubject classSubject) {
		sessionFactory.getCurrentSession().update(classSubject);
		
	}

	@Override
	public void deleteElementById(ClassSubject classSubject) {
		sessionFactory.getCurrentSession().update(classSubject);
	}
	

	@Override
	public boolean IsExist(String name) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getTotalcount() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSubject.class);
		//criteria.add(Restrictions.eq("IsDeleted",false));
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ClassSubjectDetails getUserSubjects(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("userId", userId));
		List<UserRegistration> results = criteria.list();
		logger.info("results size" + results.size());
		UserRegistration userRegistration = (UserRegistration) criteria.uniqueResult();
		String userRole = userRegistration.getRoleId();
		Criteria lpCriteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		lpCriteria.add(Restrictions.eq("userId", userId));
		User user = (User) lpCriteria.uniqueResult();
		
		String classId = "";
		String sectionId = "";
		ClassSubjectDetails classSubjectDetails = new ClassSubjectDetails();
		if (userRole.equals(Constant.STUDENT_PREFIX)) {
			
			StudentMaster studentMaster = studentMasterService.getStudentMasterById(user);
			classId = studentMaster.getClassId();
			sectionId = studentMaster.getSectionId();
			ClassSectionMasterId classSubjectId = new ClassSectionMasterId(classId, sectionId);
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
					+ "subj_id, subject_name, teacher_name, teacher_id FROM "
					+ "lutbl_class_subj WHERE class_id ='"+classId+"' and "
					+ "is_soft_delete=0 and section_id = '"+sectionId+"'");
			List<Object[]> classSubjList = (List<Object[]>) query.list();
//			Criteria usCriteria = sessionFactory.getCurrentSession().createCriteria(ClassSubject.class);
//			usCriteria.add(Restrictions.eq("classSubjectId", classSubjectId));
//			List<ClassSubject> userSubjects = usCriteria.list();
			List<ClassSubject> userSubjects = new ArrayList<>();
			ClassSubject classSubj = new ClassSubject();
			for(Object[] objArray : classSubjList) {
				classSubj = new ClassSubject();
			    ClassSubjectId newClassSubjectId = new ClassSubjectId(classId,sectionId,objArray[0].toString(),objArray[3].toString());
				classSubj.setClassSubjectId(newClassSubjectId);
				classSubj.setSubjectName(objArray[1].toString());
				classSubj.setTeacherName(objArray[2].toString());
				userSubjects.add(classSubj);
			}
			classSubjectDetails.setClassSubject(userSubjects);
		} else if (userRole.equals(Constant.TEACHER_PREFIX)) {
			Criteria csCriteria = sessionFactory.getCurrentSession().createCriteria(ClassSubject.class);
			csCriteria.add(Restrictions.eq("teacherId", userId));
			List<ClassSubject> srResults = csCriteria.list();
			classSubjectDetails.setClassSubject(srResults);
		}

		return classSubjectDetails;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ClassSubject> getAllClassSubjectSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSubject.class); 
		return (List<ClassSubject>) criteria.list();
	}

	@Override
	public List<ClassSubject> getTeacherSubjects(String teacherId) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSubject.class);
//		criteria.add(Restrictions.eq("teacherId", teacherId));
//		List<ClassSubject> teacherSubjects = criteria.list();
//		logger.info("teacherSubjects size" + teacherSubjects.size());
//		return teacherSubjects;
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "class_id, section_id, subj_id, subject_name, teacher_id FROM lutbl_class_subj "
				+ " WHERE is_soft_delete=0 and teacher_id = '"+teacherId+"'");
		List<Object[]> classSubjList = (List<Object[]>) query.list();
		List<ClassSubject> teacherSubjects = new ArrayList<>();
		ClassSubject classSubj = new ClassSubject();
		for(Object[] objArray : classSubjList) {
			classSubj = new ClassSubject();
			ClassSubjectId classSubjectId = new ClassSubjectId(objArray[0].toString(), objArray[1].toString(),objArray[2].toString(), objArray[4].toString());
			classSubj.setClassSubjectId(classSubjectId);
			//classSubj.setSubjectId(objArray[2].toString());
			classSubj.setSubjectName(objArray[3].toString());
			teacherSubjects.add(classSubj);
		}
		return teacherSubjects;
	}

	@Override
	public ClassSubjectDetail getClassSubjectbyTeacherId(String teacherId) {
		ClassSubjectDetail classSubjectDetails=new ClassSubjectDetail();
		String strQuery="SELECT s.class_id, s.section_id, s.subj_id, s.subject_name,"
				+ " s.teacher_id, s.teacher_name, c.class_name, c.section_name"
				+ " FROM lutbl_class_subj as s JOIN lutbl_class_sec_master as c "
				+ "ON c.class_id = s.class_id and c.section_id = s.section_id WHERE"
				+ " s.is_soft_delete='0' and s.teacher_id = '"+teacherId+"'";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery);
		List<Object[]> totalrows = query.list();
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String, String>();			
			if (null != row[0]) {
				hm.put("ClassId", row[0].toString());
			} else {
				hm.put("ClassId", "");
			}
			if (null != row[1]) {
				hm.put("SectionId", row[1].toString());
			} else {
				hm.put("SectionId", "");
			}
			if (null != row[2]) {
				hm.put("SubjectId", row[2].toString());
			} else {
				hm.put("SubjectId", row[2].toString());
			}
			if (null != row[3]) {
				hm.put("SubjectName", row[3].toString());
			} else {
				hm.put("SubjectName", "");
			}
			if (null != row[4]) {
				hm.put("TeacherId", row[4].toString());
			} else {
				hm.put("TeacherId", "");
			}
			if (null != row[5]) {
				hm.put("TeacherName", row[5].toString());
			} else {
				hm.put("TeacherName", "");
			}
			if (null != row[6]) {
				hm.put("ClassName", row[6].toString());
			} else {
				hm.put("ClassName", "");
			}
			if (null != row[7]) {
				hm.put("SectionName", row[7].toString());
			} else {
				hm.put("SectionName", "");
			}
			arrayList.add(hm);
		}	
		classSubjectDetails.setClassSubject(arrayList);
		classSubjectDetails.setCount(totalrows.size());
		classSubjectDetails.setMessage("");
		if(totalrows.size()>0) {
			classSubjectDetails.setMessage("List Of Class Subject");
		}else {
			classSubjectDetails.setMessage("Class Subject Record not found");				
		}
		return classSubjectDetails; 
	}

}
