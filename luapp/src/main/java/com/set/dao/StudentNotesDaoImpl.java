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

import com.set.model.NotesMaster;
import com.set.model.NotesMasterDetails;
import com.set.model.StudentNotes;
import com.set.model.StudentNotesDetails;
import com.set.model.StudentNotesId;
import com.set.model.User;
@Transactional
@Repository
public class StudentNotesDaoImpl implements StudentNotesDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("NotesDaoImp.class");

	@Override
	public void save(StudentNotes studentNotes) {
		sessionFactory.getCurrentSession().save(studentNotes);		
	}
	
	@Override
	public StudentNotesDetails getAllStudentNotes(int pagenumber, String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentNotes.class);
		//criteria.add(Restrictions.eq("isDeleted",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("subjectName", searchdata, MatchMode.ANYWHERE))
				.add(Restrictions.like("notesTitle", searchdata, MatchMode.ANYWHERE))
				.add(Restrictions.like("notesDescription", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
		}
		StudentNotesDetails studentNotesDetails = new StudentNotesDetails();
		logger.info("total number of record="+criteria.list().size());
		studentNotesDetails.setCount(criteria.list().size());
		studentNotesDetails.setStudentNotes((List<StudentNotes>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return studentNotesDetails;
	}
	@Override
	public List<StudentNotes> getAllStudentNotesSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentNotes.class); 
		//criteria.add(Restrictions.eq("isDeleted",0));
		return (List<StudentNotes>) criteria.list();
	}

	@Override
	public StudentNotes getStudentNotesById(StudentNotesId studentNotesId) {
		return sessionFactory.getCurrentSession().get(StudentNotes.class, studentNotesId);
	}

	@Override
	public void updateStudentNotes(StudentNotes studentNotes) {
		sessionFactory.getCurrentSession().update(studentNotes);	
	}

	@Override
	public void deleteStudentNotes(StudentNotesId studentNotesId) {
		StudentNotes studentNotes = new StudentNotes();
		studentNotes.setStudentNotesId(studentNotesId);
		sessionFactory.getCurrentSession().delete(studentNotes);	
	}

	@Override
	public boolean IsExist(String notes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int totalStudentNotes() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentNotes.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

	@Override
	public List<Object[]> getAllStudentNotesSubjectSelect(String studentId) {
		String queryString = "select DISTINCT(sbj.subj_id), sbj.subject_name from lutbl_class_subj csb, lutbl_subject_master sbj, lutbl_stdnt_master sm " 
				+ "where csb.class_id = sm.class_id "
			    + "and csb.section_id = sm.section_id " 
			    + "and sbj.subj_id = csb.subj_id "
			    + "and sm.student_id = '"+studentId+"'";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
		
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllStudentNotesUnitTopicSelect(String subjectId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "n.notes_id, n.cover_photo, n.page_type, n.teacher_id, "
				+ "t.topic_name, u.unit_name FROM lutbl_notes_master n join "
				+ "lutbl_subj_units u ON n.unit_id = u.unit_id join "
				+ "lutbl_subj_unit_mstr t ON n.topic_id = t.topic_id "
				+ "WHERE n.subj_id = '"+subjectId+"' and t.is_soft_delete = 0");
		return (List<Object[]>) query.list();
	}
	
	@Override
	public List<Object[]> getAllStudentNotesSelect(String notesId, String studentId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " page_path, page_no FROM lutbl_student_notes"
				+ " WHERE notes_id = '"+notesId+"' and student_id='"+studentId+"'");
		return (List<Object[]>) query.list();
	}

}
