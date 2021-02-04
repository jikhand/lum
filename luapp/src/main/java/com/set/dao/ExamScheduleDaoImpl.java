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

import com.set.model.Drawing;
import com.set.model.ExamSchedule;
import com.set.model.ExamScheduleDetails;
import com.set.model.UserRegistration;
@Transactional
@Repository
public class ExamScheduleDaoImpl implements ExamScheduleDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("ExamScheduleImp.class");

	@Override
	public void save(ExamSchedule examSchedule) {
		sessionFactory.getCurrentSession().save(examSchedule);		
	}
	@Override
	public ExamScheduleDetails getAllExamSchedule(int pagenumber, String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamSchedule.class);
		criteria.add(Restrictions.eq("isSoftDelete",false));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("ExamDescription", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("SubjectId", searchdata, MatchMode.ANYWHERE));;
			criteria.add(or);
			System.out.println("ExamDescription + ExamDescription");
			System.out.println("SubjectId + SubjectId");
		}
		ExamScheduleDetails examScheduleDetails = new ExamScheduleDetails();
		logger.info("total number of record="+criteria.list().size());
		examScheduleDetails.setCount(criteria.list().size());
		examScheduleDetails.setExamSchedule((List<ExamSchedule>) criteria.list());
	//	criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return examScheduleDetails;
	}
					
		
	//	int maxPageData=10;
	//	int start = pagenumber * maxPageData - maxPageData;
	//	int end =10;
	//	String searchString="";
	//	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamSchedule.class);
	//	criteria.add(Restrictions.eq("isSoftDelete",false));
	//	if (!searchdata.equalsIgnoreCase("null")) {
	//		Disjunction or = Restrictions.disjunction();
	//		or.add(Restrictions.like("examDescription", searchdata, MatchMode.ANYWHERE));
	//		criteria.add(or);
	//	}
	//	ExamScheduleDetails examScheduleDetails = new ExamScheduleDetails();
	//	logger.info("total number of record="+criteria.list().size());
	//	examScheduleDetails.setCount(criteria.list().size());
	//	examScheduleDetails.setExamSchedule((List<ExamSchedule>) criteria.list());
	//	criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
	//	criteria.setMaxResults(end);	
	//	return examScheduleDetails;
	// }
	@Override
	public List<ExamSchedule> getAllExamScheduleSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamSchedule.class); 
		criteria.add(Restrictions.eq("isSoftDelete",false));
		return (List<ExamSchedule>) criteria.list();
	}

	@Override
	public ExamSchedule getExamScheduleById(String examScheduleId) {
		return sessionFactory.getCurrentSession().get(ExamSchedule.class, examScheduleId);
	}

	@Override
	public void updateExamSchedule(ExamSchedule examSchedule) {
		sessionFactory.getCurrentSession().update(examSchedule);	
	}

	@Override
	public void deleteExamSchedule(ExamSchedule examSchedule) {
		sessionFactory.getCurrentSession().update(examSchedule);	
	}

	@Override
	public boolean IsExist(String examSchedule, String examType) {
	if(examType.equals("E")) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(UserRegistration.class);
		 cr.add(Restrictions.eq("userId",examSchedule));
		 UserRegistration userRegistration = (UserRegistration) cr.uniqueResult();
		 String roleId = userRegistration.getRoleId();
			System.out.println("roleId"+roleId);
		if(roleId.equals("A")) {
			return true;
		}else {
			return false;	
		}
	}else {
		return true;		
	}
	}

	@Override
	public int totalExamSchedule() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamSchedule.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}
	@Override
	public List<Object[]> getAllExamList(String examId) {
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
						+ " DISTINCT(s.subj_id),e.exam_id, e.class_id, e.duration, e.exam_date, e.exam_desc, "
						+ "e.exam_start_time, e.exam_type, e.section_id,  "
						+ "e.teacher_id, s.subject_name, e.max_marks FROM lutbl_examsch e join "
						+ "lutbl_class_subj s ON e.subj_id = s.subj_id WHERE"
						+ " e.exam_id = '"+examId+"' and s.is_soft_delete='0' and e.is_soft_delete='0'");
				return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllExamQuestionList(String examId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " question_type, no_of_questions FROM lutbl_exam_quest_Details "
				+ "WHERE exam_id = '"+examId+"'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllStudentExamList(String classId, String sectionId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " DISTINCT(s.subj_id),e.exam_id, e.class_id, e.duration, e.exam_date, "
				+ "e.exam_desc, e.exam_start_time, e.exam_type, e.section_id,  "
				+ "e.teacher_id, s.subject_name, e.max_marks FROM lutbl_examsch e join "
				+ "lutbl_class_subj s ON e.subj_id = s.subj_id WHERE "
				+ " e.class_id = '"+classId+"' and e.section_id = '"+sectionId+"' "
				+ "and s.is_soft_delete='0' and e.is_soft_delete='0'"
				+ " and e.exam_date <= DATE_ADD(CURDATE(), INTERVAL 1 MONTH) and e.exam_date >=CURDATE() ORDER BY exam_date ASC");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllTeacherExamList(String teacherId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " DISTINCT(s.subj_id),e.exam_id, e.class_id, e.duration, e.exam_date, "
				+ "e.exam_desc, e.exam_start_time, e.exam_type, e.section_id,  "
				+ "e.teacher_id, s.subject_name, e.max_marks FROM lutbl_examsch e join "
				+ "lutbl_class_subj s ON e.subj_id = s.subj_id WHERE "
				+ " e.teacher_id = '"+teacherId+"'  "
				+ "and s.is_soft_delete='0' and e.is_soft_delete='0'"
				+ " and e.exam_date <= DATE_ADD(CURDATE(), INTERVAL 1 MONTH) and e.exam_date >=CURDATE() ORDER BY exam_date ASC");
		return (List<Object[]>) query.list();
	}

}
