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

import com.set.model.ExamPaper;
import com.set.model.ExamStudent;
import com.set.model.ExamStudentDetails;
import com.set.model.ExamStudentId;
import com.set.model.StudentNotes;
@Transactional
@Repository
public class ExamStudentDaoImpl implements ExamStudentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("ExamStudentImp.class");

	@Override
	public void save(ExamStudent examStudent) {
		sessionFactory.getCurrentSession().save(examStudent);		
	}
	@Override
	public ExamStudentDetails getAllExamStudent(int pagenumber, String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamStudent.class);
		criteria.add(Restrictions.eq("isSoftDelete",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("answer", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
		}
		ExamStudentDetails examStudentDetails = new ExamStudentDetails();
		logger.info("total number of record="+criteria.list().size());
		examStudentDetails.setCount(criteria.list().size());
		examStudentDetails.setExamStudent((List<ExamStudent>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return examStudentDetails;
	}
	@Override
	public List<ExamStudent> getAllExamStudentSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ExamStudent.class); 
		criteria.add(Restrictions.eq("isSoftDelete",0));
		return (List<ExamStudent>) criteria.list();
	}

	@Override
	public ExamStudent getExamStudentById(ExamStudentId examStudentId) {
		return sessionFactory.getCurrentSession().get(ExamStudent.class, examStudentId);
	}

	@Override
	public void updateExamStudent(ExamStudent examStudent) {
		sessionFactory.getCurrentSession().update(examStudent);	
	}

	@Override
	public void deleteExamStudent(ExamStudentId examStudentId) {
		ExamStudent examStudent = new ExamStudent();
		examStudent.setExamStudentId(examStudentId);
		sessionFactory.getCurrentSession().delete(examStudent);	
	}

	@Override
	public boolean IsExist(String examStudent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int totalExamStudent(String examid) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " * FROM lutbl_question_paper WHERE exam_id='"+examid+"'");
		int totalNumbers = query.list().size();
		return totalNumbers;
	}
	
	@Override
	public List<Object[]> getAllSubmitStudentList(String examid) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "DISTINCT(u.student_id), u.first_name, u.last_name, u.email_id FROM lutbl_stdnt_master u "
				+ "join lutbl_exam_stdnt e ON e.student_id=u.student_id "
				+ "JOIN lutbl_examsch s ON u.class_id = s.class_id and u.section_id = s.section_id"
				+ " and e.exam_id = s.exam_id WHERE s.exam_id='"+examid+"'");
		return (List<Object[]>) query.list();
	}
	
	@Override
	public List<Object[]> getAllAbsentStudentList(String examid) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "DISTINCT(u.student_id), u.first_name, u.last_name, u.email_id FROM lutbl_stdnt_master u "
				+ "join lutbl_exam_stdnt e ON e.student_id!=u.student_id "
				+ "JOIN lutbl_examsch s ON u.class_id = s.class_id and u.section_id = s.section_id"
				+ " and e.exam_id = s.exam_id WHERE s.exam_id='"+examid+"'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllQuestionTypeList(String examId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " DISTINCT(q.question_type), t.description, q.no_of_questions, e.marks_for_question"
				+ " FROM lutbl_exam_quest_Details q join lutbl_qustn_type t ON"
				+ " q.question_type = t.question_type join lutbl_question_bank b"
				+ " ON b.question_type_id = t.question_type join lutbl_question_paper e"
				+ " ON e.question_id = b.question_id "
				+ "WHERE q.exam_id = '"+examId+"' and b.is_soft_delete='0' and e.is_soft_delete='0'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllQuestionList(String examId, String questionTypeId, String studentId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " e.exam_id, e.question_id, e.questn_no, b.question_type_id, "
				+ "b.question, b.question_path, "
				+ "b.opta, b.optb, b.optc, b.optd, b.lsoa, b.lsob, b.lsoc, b.lsod, "
				+ "b.lsoe, b.rso1, b.rso2, b.rso3, b.rso4, b.rso5, s.ans_page_no, "
				+ "s.student_id, s.answer, s.answer_path, s.start_time, s.end_time, s.marks_obtained "				
				+ "FROM lutbl_question_paper e join lutbl_question_bank b ON "
				+ "e.question_id = b.question_id join lutbl_exam_stdnt s ON "
				+ "e.exam_id = s.exam_id and e.questn_no=s.questn_no WHERE s.student_id = '"+studentId+"' and "
				+ "e.exam_id = '"+examId+"' and b.question_type_id = '"+questionTypeId+"' and b.is_soft_delete='0' and e.is_soft_delete='0'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllReportStudentList(String studentId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "u.student_id, u.first_name, u.last_name, u.email_id, u.class_id, u.section_id,"
				+ "c.class_name,c.section_name FROM lutbl_stdnt_master u "
				+ "join lutbl_class_sec_master c ON u.class_id = c.class_id and u.section_id = c.section_id"
				+ " WHERE u.student_id='"+studentId+"' and u.is_soft_delete='0'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllReportMarksList(String studentId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "sub.subj_id, sub.subject_name, s.max_marks, sum(e.marks_obtained), s.exam_id "
				+ " FROM lutbl_examsch s join lutbl_stdnt_master u ON u.class_id = s.class_id and u.section_id = s.section_id "
				+ "join lutbl_exam_stdnt e ON e.student_id=u.student_id and s.exam_id = e.exam_id"
				+ " join lutbl_subject_master sub ON s.subj_id = sub.subj_id "
				+ " WHERE u.student_id='"+studentId+"' and u.is_soft_delete='0'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllReportAbsentList(String studentId) {
		SQLQuery query1 = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "sub.subj_id, sub.subject_name, s.max_marks, s.exam_id "
				+ " FROM lutbl_examsch s join lutbl_stdnt_master u ON u.class_id = s.class_id and u.section_id = s.section_id "
				+ "join lutbl_exam_stdnt e ON e.student_id!=u.student_id and s.exam_id != e.exam_id"
				+ " join lutbl_subject_master sub ON s.subj_id = sub.subj_id "
				+ "  WHERE u.student_id='"+studentId+"' and u.is_soft_delete='0'");
		return (List<Object[]>) query1.list();
	}

}
