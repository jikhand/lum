package com.set.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.dto.NotesMasterTopicDto;
import com.set.dto.NotesMasterTopicListDto;
import com.set.model.Drawing;
import com.set.model.DrawingDetails;
import com.set.model.NotesMaster;
import com.set.model.NotesMasterDetails;
import com.set.model.SubjectUnit;
import com.set.model.SubjectUnitMaster;
import com.set.model.SubjectUnitMasterDetails;
import com.set.model.SubjectUnitMasterId;
import com.set.model.TextBookMaster;
import com.set.model.TimeTableId;
@Transactional
@Repository
public class NotesMasterDaoImpl implements NotesMasterDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("NotesDaoImp.class");

	@Override
	public void save(NotesMaster notesMaster) {
		sessionFactory.getCurrentSession().save(notesMaster);		
	}
	@Override
	public List<Map<String , String>> getAllNotesMaster(int pagenumber,String searchdata) {
		System.out.println("searchdata="+searchdata);
		String searchString="";
		LocalDateTime datetime=null;
		if (!searchdata.equalsIgnoreCase("null")) {
//			System.out.println("valid date");
////			if(UtilDate.isValidDate(searchdata)) {
////				System.out.println("new datetime nre "+datetime);
////				datetime = LocalDateTime.parse(searchdata,DateTimeFormatter.ofPattern("yyyy-MM-dd"));	//DATE_FORMAT('2009-10-04 22:23:00', '%Y/%m/%d')
////			}
		searchString=" where unit_name LIKE '%"+searchdata+"%' or email_id LIKE '%"+searchdata +"%' or subject_name LIKE '%"+searchdata+"%' "
				+ "or topic_name LIKE '%"+searchdata+"%'"+ "or page_type LIKE '%"+searchdata+"%'";
		
		
		System.out.println("searchString="+searchString);
		}
		String StrQuery="Select any_value(email_id) AS email_id " + 
				", any_value(unit_name) AS unit_name" + 
				" , any_value(subject_name) AS subject_name, " + 
				" any_value(topic_name) AS topic_name " + 
				" , any_value(page_type) AS page_type " + 
				" , any_value(cover_photo) as cover_photo " + 
				" , any_value(nm.notes_id) as notes_id from "
				+ "lutbl_notes_master as nm join lutbl_user_reg as ur on "
				+ "ur.user_id=nm.teacher_id join lutbl_subj_units as "
				+ "su on su.unit_id=nm.unit_id join "
				+ "lutbl_subject_master as sm on sm.subj_id=nm.subj_id join " + 
				"lutbl_subj_unit_mstr as um on um.topic_id=nm.topic_id "+searchString+" group by nm.notes_id";
		System.out.println("StrQuery= "+StrQuery);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
		List<Object[]> totalrows = query.list();
		int totalNumbers=totalrows.size();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String,String>();
			if(null!=row[0]) {
				hm.put("email_id", row[0].toString());
			}else {
				hm.put("email_id", "");
			}
			if(null!=row[1]) {
				hm.put("unit_name", row[1].toString());
			}else {
				hm.put("unit_name", "");
			}
			if(null!=row[2]) {
				hm.put("subject_name", row[2].toString());
			}else {
				hm.put("subject_name", "");
			}
			if(null!=row[3]) {
				hm.put("topic_name", row[3].toString());
			}else {
				hm.put("topic_name", "");
			}
			if(null!=row[4]) {
				hm.put("page_type", row[4].toString());
			}else {
				hm.put("page_type", "");
			}
			if(null!=row[5]) {
				hm.put("cover_photo", row[5].toString());
			}else {
				hm.put("cover_photo", "");
			}
			if(null!=row[6].toString()) {
				hm.put("notes_id", row[6].toString());
			}else {
				hm.put("notes_id", "");
			}	
				hm.put("RecordTotal",String.valueOf(totalNumbers));
				arrayList.add(hm);
			}
			return arrayList;
	}
	@Override
	public List<NotesMaster> getAllNotesMasterSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotesMaster.class); 
		//criteria.add(Restrictions.eq("isDeleted",0));
		return (List<NotesMaster>) criteria.list();
	}

	@Override
	public NotesMaster getNotesMasterById(String notesId) {
		return sessionFactory.getCurrentSession().get(NotesMaster.class, notesId);
	}

	@Override
	public void updateNotesMaster(NotesMaster notesMaster) {
		sessionFactory.getCurrentSession().update(notesMaster);	
	}

	@Override
	public void deleteNotesMaster(NotesMaster notesMaster) {
		sessionFactory.getCurrentSession().update(notesMaster);	
	}

	@Override
	public boolean IsExist(String SubjectId, String TeacherId, String UnitId, String TopicId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(NotesMaster.class);
		 cr.add(Restrictions.eq("SubjectId",SubjectId));
		 cr.add(Restrictions.eq("TeacherId",TeacherId));
		 cr.add(Restrictions.eq("UnitId",UnitId));
		 cr.add(Restrictions.eq("TopicId",TopicId));
		 int results = cr.list().size();
		if(results>0) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public int totalNotesMaster() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotesMaster.class);
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}
	@Override
	public List<Object[]> getAllNotesSubjectSelect(String classId, String sectionId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "DISTINCT(subj_id), subject_name FROM "
				+ "lutbl_class_subj WHERE class_id ='"+classId+"' and "
				+ "is_soft_delete=0 and section_id = '"+sectionId+"'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllNotesUnitSelect(String subjectId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ "DISTINCT(u.unit_id), u.unit_name FROM lutbl_subj_unit_mstr n join "
				+ "lutbl_subj_units u ON n.unit_id = u.unit_id WHERE "
				+ "n.subj_id ='"+subjectId+"'");
		return (List<Object[]>) query.list();
	}
	@Override
	public List<Object[]> getAllNotesCategorySelect(String subjectId, String unitId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT "
				+ " n.notes_id, n.cover_photo, n.page_type, t.topic_id, t.topic_name"
				+ " FROM lutbl_notes_master n join "
				+ "lutbl_subj_unit_mstr t ON n.topic_id = t.topic_id WHERE "
				+ "n.subj_id ='"+subjectId+"' and n.unit_id ='"+unitId+"'");
		return (List<Object[]>) query.list();
	}
	
//	@Override
	public NotesMasterTopicListDto getAllTopicSelect() {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT SubjectUnitMasterId.TopicId, TopicName from SubjectUnitMaster");
		
		List<Object[]> listOfTopic = query.list();		

		NotesMasterTopicListDto notesMasterTopicListDto = new NotesMasterTopicListDto();
		List<NotesMasterTopicDto> topicList= new ArrayList<>();
		NotesMasterTopicDto notesMasterTopicDto=null;
		for (Object[] objArr : listOfTopic) {
			
			notesMasterTopicDto = new NotesMasterTopicDto();
			notesMasterTopicDto.setTopicId((String)objArr[0]);
			notesMasterTopicDto.setTopicName((String)objArr[1]);
				
			topicList.add(notesMasterTopicDto);
		}
		notesMasterTopicListDto.setCount(topicList.size());
		notesMasterTopicListDto.setNotesMasterTopicDtoData(topicList);
		notesMasterTopicListDto.setMessage("Topic List");
		return notesMasterTopicListDto;		
			
	}

}
