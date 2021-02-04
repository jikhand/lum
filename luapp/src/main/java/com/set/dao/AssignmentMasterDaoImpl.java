package com.set.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.set.model.AssignmentMaster;
import com.set.model.AssignmentMasterDetails;
import com.set.model.AssignmentMasterId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.ClassSectionMasterId;
import java.util.Map;

@Transactional
@Repository
public class AssignmentMasterDaoImpl implements AssignmentMasterDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(AssignmentMaster assignmentMaster) {
		sessionFactory.getCurrentSession().saveOrUpdate(assignmentMaster);
	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@Override
	public AssignmentMasterDetails list(int pagenumber, String searchdata) {
	 System.out.println("searchdata="+searchdata);
		int maxPageData = 10;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentMaster.class);
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			 System.out.println("searchdata="+searchdata);	
			or.add(Restrictions.like("assignType", searchdata, MatchMode.ANYWHERE));	
			or.add(Restrictions.like("isDeleted", 0));
			criteria.add(or);
		}
		System.out.println("assignType +assignType");
		AssignmentMasterDetails assignmentMasterDetails = new AssignmentMasterDetails();
		assignmentMasterDetails.setCount(criteria.list().size());
		assignmentMasterDetails.setAssignmentMaster((List<AssignmentMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);
		return assignmentMasterDetails;
	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@Override
	public AssignmentMaster getElementById(AssignmentMaster UID) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(AssignmentMaster.class);
		cr.add(Restrictions.eq("assignmentMasterId", UID.getAssignmentMasterId()));
		cr.add(Restrictions.eq("isDeleted", 0));
		
		List results = cr.list();
		AssignmentMaster assignmentMaster = (AssignmentMaster) cr.uniqueResult();
		return assignmentMaster;
	}
	
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	public List<Map<String , String>> getStudentView(AssignmentMasterId UID) {
        String searchString="";
        LocalDateTime datetime=null;
//        if (!searchdata.equalsIgnoreCase("null")) {
//            System.out.println("valid date");
////            if(UtilDate.isValidDate(searchdata)) {
////                System.out.println("new datetime nre "+datetime);
////                datetime = LocalDateTime.parse(searchdata,DateTimeFormatter.ofPattern("yyyy-MM-dd"));    //DATE_FORMAT('2009-10-04 22:23:00', '%Y/%m/%d')
////            }
//            searchString=" where ActionType LIKE '%"+searchdata+"%' or Email LIKE '%"+searchdata +"%' or ActionElement LIKE '%"+searchdata+"%' or CAST(l.CreatedAt AS char) LIKE '%"+searchdata+"%'";
//        }
        String StrQuery="SELECT DISTINCT(s.subj_id), assgn_id, a.class_id, "
        		+ "a.section_id, assgn_due_date, assgn_path, "
        		+ "assgn_start_date, assgn_type,  max_marks, "
        		+ "description, assgn_subject, "
        		+ "p.page_category_type_name, s.subject_name, "
        		+ "c.class_name FROM lutbl_assgnmnt_master as a"
        		+ " JOIN lutbl_page_category as p ON "
        		+ "a.page_category_id = p.page_category_id JOIN"
        		+ " lutbl_class_subj as s ON a.subj_id = s.subj_id "
        		+ "JOIN lutbl_class_sec_master as c ON "
        		+ "a.class_id = c.class_id and a.section_id = c.section_id"
        		+ " WHERE a.assgn_id = '"+UID.getAssignmentId()+"' and a.asgn_active = '1' and a.is_deleted = '0'"
        		+ " and s.is_soft_delete = '0'";
        System.out.println("StrQuery= "+StrQuery);
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
        List<Object[]> totalrows = query.list();
        int totalNumbers=totalrows.size();
//        int maxPageData=10;
//        int start = pagenumber * maxPageData - maxPageData;
//        int end =10;
//        query.setFirstResult(start);
//        query.setMaxResults(end);
        List<Object[]> rows = query.list();
        List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
        for(Object[] row : rows){
            Map<String,String> hm = new HashMap<String,String>();
            if(!row[0].toString().equalsIgnoreCase("null")) {
                hm.put("Id", row[1].toString());
            }else {
                hm.put("Id", "");
            }
            if(!row[1].toString().equalsIgnoreCase("null")) {
                hm.put("ClassId", row[2].toString());
            }else {
                hm.put("ClassId", "");
            }
            if(!row[2].toString().equalsIgnoreCase("null")) {
                hm.put("SectionId", row[3].toString());
            }else {
                hm.put("SectionId", "");
            }
            if(!row[3].toString().equalsIgnoreCase("null")) {
                hm.put("AssignmentDueDate", row[4].toString());
            }else {
                hm.put("AssignmentDueDate", "");
            }
            if(!row[4].toString().equalsIgnoreCase("null")) {
                hm.put("AssignmentAttachment", row[5].toString());
            }else {
                hm.put("AssignmentAttachment", "");
            }
            if(!row[5].toString().equalsIgnoreCase("null")) {
                hm.put("AssignmentCreatedDate", row[6].toString());
            }else {
                hm.put("AssignmentCreatedDate", "");
            }
            if(!row[6].toString().equalsIgnoreCase("null")) {
                hm.put("AssignmentType", row[7].toString());
            }else {
                hm.put("AssignmentType", "");
            }
            if(!row[7].toString().equalsIgnoreCase("null")) {
                hm.put("AssignmentMark", row[8].toString());
            }else {
                hm.put("AssignmentMark", "");
            }
            if(!row[8].toString().equalsIgnoreCase("null")) {
                hm.put("AssignmentDescription", row[9].toString());
            }else {
                hm.put("AssignmentDescription", "");
            }
            if(!row[9].toString().equalsIgnoreCase("null")) {
                hm.put("AssignmentSubject", row[10].toString());
            }else {
                hm.put("AssignmentSubject", "");
            }
            if(!row[10].toString().equalsIgnoreCase("null")) {
                hm.put("PageTypeName", row[11].toString());
            }else {
                hm.put("PageTypeName", "");
            }
            if(!row[11].toString().equalsIgnoreCase("null")) {
                hm.put("SubjectName", row[12].toString());
            }else {
                hm.put("SubjectName", "");
            }
            if(!row[12].toString().equalsIgnoreCase("null")) {
                hm.put("ClassName", row[13].toString());
            }else {
                hm.put("ClassName", "");
            }    
                hm.put("RecordTotal",String.valueOf(totalNumbers));
                arrayList.add(hm);
            }
            return arrayList;
    }

	@Override
	public void update(AssignmentMaster assignmentMaster) {
		sessionFactory.getCurrentSession().update(assignmentMaster);
	}

	@Override
	public void deleteElementById(AssignmentMaster assignmentMaster) {
		sessionFactory.getCurrentSession().update(assignmentMaster);

	}

	@Override
	public boolean IsExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int gettotalcount() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentMaster.class);
		// criteria.add(Restrictions.eq("IsDeleted",false));
		int totalNumbers = criteria.list().size();
		return totalNumbers;
	}

	@Override
	public void AssignmentActive(AssignmentMaster assignmentMaster) {
		sessionFactory.getCurrentSession().update(assignmentMaster);

	}

	@Override
	public void AssignmentInactive(AssignmentMaster assignmentMaster) {
		sessionFactory.getCurrentSession().update(assignmentMaster);

	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@Override
	public AssignmentMasterDetails getUserAssignments(int pagenumber, String userId) {
		int maxPageData = 20;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentMaster.class);
		criteria.add(Restrictions.eq("associateTeacherId", userId));
		criteria.add(Restrictions.eq("isDeleted", 0));
		AssignmentMasterDetails assignmentMasterDetails = new AssignmentMasterDetails();

		assignmentMasterDetails.setAssignmentMaster((List<AssignmentMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		assignmentMasterDetails.setCount(criteria.list().size());
		criteria.setMaxResults(end);
		return assignmentMasterDetails;
	}

	@Override
	public void deleteAssignment(AssignmentMaster assignmentMaster) {
		sessionFactory.getCurrentSession().update(assignmentMaster);		
	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@Override
	public AssignmentMasterDetails getClassSectionAssignments(int pagenumber,
			ClassSectionMasterId classSectionMasterId) {
		int maxPageData = 20;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssignmentMaster.class);
		criteria.add(Restrictions.eq("assignmentMasterId.classId", classSectionMasterId.getClassId()));
		criteria.add(Restrictions.eq("assignmentMasterId.sectionId", classSectionMasterId.getSectionId()));
		criteria.add(Restrictions.eq("isDeleted", 0));
		AssignmentMasterDetails assignmentMasterDetails = new AssignmentMasterDetails();

		assignmentMasterDetails.setAssignmentMaster((List<AssignmentMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		assignmentMasterDetails.setCount(criteria.list().size());
		return assignmentMasterDetails;
	}
	//@ saigeeta : Modified as per the MOM on 3rd oct 2018,created field in db is_deleted, added in model also isDeleted.
	@Override
	public AssignmentMaster getAssignmentId(AssignmentMasterId assignmentMasterId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(AssignmentMaster.class);
		cr.add(Restrictions.eq("assignmentMasterId", assignmentMasterId));
		cr.add(Restrictions.eq("isDeleted", 0));
		AssignmentMaster assignmentMaster = (AssignmentMaster) cr.uniqueResult();
		return assignmentMaster;
	}
	
	public int deleteAssignmentMaster(String assignmentId) {
		System.out.println("assignmentId daoimpl: "+assignmentId);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("update lutbl_assgnmnt_master t1, lutbl_assgn_eval t2"
				+ " ,lutbl_assgn_pages t3 set t1.is_deleted = '1', t2.is_soft_delete='1', t3.is_deleted ='1'"
				+ " where t1.assgn_id=t2.assgn_id and t2.assgn_id=t3.assgn_id and t2.student_id=t3.student_id"
				+ " and t1.assgn_id= '"+assignmentId+"' and t2.assgn_id= '"+assignmentId+"' and t3.assgn_id= '"+assignmentId+"'");
		
		return query.executeUpdate();
		
	}
}
