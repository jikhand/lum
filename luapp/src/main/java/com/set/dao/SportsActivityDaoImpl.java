package com.set.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

//import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.dto.AttendenceDto;
import com.set.dto.AttendenceListDto;
import com.set.dto.SportsActivityDto;
import com.set.dto.SportsActivityListDto;
import com.set.dto.SportsActivityMessageDto;
import com.set.dto.SportsActivityTeacherDto;
import com.set.dto.SportsActivityTeacherListDto;
import com.set.dto.StudentActiDto;
import com.set.dto.StudentActivityDto;
import com.set.dto.StudentActivityDtoDetails;
import com.set.dto.StudentActivityListDto;
import com.set.dto.TextBookListDto;
import com.set.dto.TimeTableDayDto;
import com.set.dto.TimeTableDaysDto;
import com.set.dto.TimeTableDto;
import com.set.dto.TimeTableListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.SportsActivity;
import com.set.model.SportsActivityDetails;
import com.set.model.SportsAnnouncement;
import com.set.model.SportsAnnouncementDetails;
import com.set.model.StudentActivity;
import com.set.model.StudentActivityId;
import com.set.model.StudentMaster;
import com.set.model.StudentsActivityDetails;
import com.set.model.TextBookMaster;
import com.set.model.TimeTableId;

@Transactional
@Repository
public class SportsActivityDaoImpl implements SportsActivityDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("SportsActivityDaoImpl.class");

	@Override
	public void save(SportsActivity sportsActivity) {
		sessionFactory.getCurrentSession().save(sportsActivity);
	}

	@Override
	public List<SportsActivity> getAllSportsActivity() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsActivity.class);				
		return (List<SportsActivity>) criteria.list();
	}
	
	@Override
	public void editSportsActivity(SportsActivity sportsActivity) {					
		sessionFactory.getCurrentSession().update(sportsActivity);	
	}
	
	@Override
	public void deleteSportsActivity(SportsActivity sportsActivity) {
		sessionFactory.getCurrentSession().delete(sportsActivity);	
	}
	
	@Override
	public List<SportsActivity> getSportsActivityByActivityCode(int activityCode) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsActivity.class,"sa")
				.add(Restrictions.eq("sa.activityCode", activityCode));
		return (List<SportsActivity>) criteria.list();
	}
	
	@Override
	public List<SportsActivity> getSportsActivityByTeacherOrCoach(String teacherCoach) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsActivity.class,"tc")
				.add(Restrictions.eq("tc.coach", teacherCoach));
		return (List<SportsActivity>) criteria.list();
	}
	
//	@Override
//	public List<SportsActivity> getSportsActivityByStudentId(String studentId) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentActivity.class,"si")
//				.add(Restrictions.eq("si.studentActivityId.studentId", studentId));
//		return (List<SportsActivity>) criteria.list();
//	}
	
	public SportsActivityListDto getSportsActivityByStudentId(String studentId) {
		System.out.println("studentId: "+studentId);
		
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT sports.activityCode, sports.activityDescription,"
				+ " sports.coach, stu.studentActivityId.studentId, stu.enrolledDate FROM SportsActivity sports, StudentActivity stu"
				+ " WHERE sports.activityCode=stu.studentActivityId.activityCode"
				+ " and stu.studentActivityId.studentId='"+studentId+"'");
		
		List<Object[]> listOfStu = query.list();
		
		System.out.println("query: "+query);
		
		SportsActivityListDto sportsActivityListDto = null;
		//
		//List<SportsActivityDto> sportsActivityDtoList= new ArrayList<>();
		//List<SportsActivityListDto> sp = new ArrayList<>();
		List<SportsActivityDto> sports = new ArrayList<>();
		SportsActivityDto sportsActivityDetailsDto=null;
		sportsActivityListDto = new SportsActivityListDto();

		for (Object[] objArr : listOfStu) {
			//
			
			sportsActivityDetailsDto = new SportsActivityDto();
			sportsActivityDetailsDto.setActivityCode((int)objArr[0]);
			sportsActivityDetailsDto.setActivityDescription((String)objArr[1]);
			sportsActivityDetailsDto.setCoach((String)objArr[2]);
			sportsActivityDetailsDto.setStudentId((String)objArr[3]);
			sportsActivityDetailsDto.setEnrolledDate((Date)objArr[4]);
			
			
		//sportsActivityListDto.setSportsDatas(sportsActivityDetailsDto);
//		sportsActivityListDto.setMessage("Student Activity Data");
		//sportsActivityListDto.setCode("200");
		sports.add(sportsActivityDetailsDto);
		}
		sportsActivityListDto.setCode("200");
		sportsActivityListDto.setMessage("Student Activity Data");
		sportsActivityListDto.setSportsDatas(sports);
    	//sportsActivityListDto.setCount(sportsActivityDtoList.size());
		//sportsActivityListDto.setMessage("Student Activity Data");
		return sportsActivityListDto;	
	}	
	
	public SportsActivityTeacherListDto getSportsActivityByUserId(String userId) {
		System.out.println("studentId: "+userId);
		//Query query = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT distinct sports.actvty_code, sports.actvty_description,"
				+ " sports.coach ,sports.class_id, sports.section_id, cs.teacher_id, cs.teacher_name, sports.inserted_by, sports.inserted_time,"
				+ " sports.date FROM lutbl_sportsactvty sports, lutbl_class_subj cs WHERE sports.class_id = cs.class_id"
				+ " and sports.section_id = cs.section_id and cs.teacher_id='"+userId+"'");
		
		List<Object[]> listOfStu = query.list();
		
		SportsActivityTeacherListDto sportsActivityListDto = 	null;	//
		//List<SportsActivityDto> sportsActivityDtoList= new ArrayList<>();
		//List<SportsActivityTeacherListDto> sp = new ArrayList<>();
		List<SportsActivityTeacherDto> sportsActivityTeacherDto = new ArrayList<>();
		SportsActivityTeacherDto sportsActivityDetailsDto=null;
		//List<SportsActivityMessageDto> timeDtoList = new ArrayList<>();
		//SportsActivityMessageDto daysList = null;
		sportsActivityListDto = new SportsActivityTeacherListDto();
		for (Object[] objArr : listOfStu) {	
			//daysList = new SportsActivityMessageDto();
			
			sportsActivityDetailsDto = new SportsActivityTeacherDto();
			sportsActivityDetailsDto.setActivityCode((int)objArr[0]);
			sportsActivityDetailsDto.setActivityDescription((String)objArr[1]);
			sportsActivityDetailsDto.setCoach((String)objArr[2]);
			sportsActivityDetailsDto.setClassId((String)objArr[3]);			
			sportsActivityDetailsDto.setSectionId((String)objArr[4]);
			sportsActivityDetailsDto.setUserId((String)objArr[5]);
			sportsActivityDetailsDto.setUserName((String)objArr[6]);
			sportsActivityDetailsDto.setInsertedBy((String)objArr[7]);
			sportsActivityDetailsDto.setInsertedTime((Date)objArr[8]);
			sportsActivityDetailsDto.setDate((Date)objArr[9]);		
			
		//sportsActivityListDto.setSportsDatas(sportsActivityDetailsDto);
//		sportsActivityListDto.setMessage("Sports Activity Data");
//		sportsActivityListDto.setCode("200");
		
		//sp.add(sportsActivityListDto);
		//timeDtoList.add(daysList);
			sportsActivityTeacherDto.add(sportsActivityDetailsDto);
		}
		sportsActivityListDto.setMessage("Sports Activity Data");
		sportsActivityListDto.setCode("200");
		sportsActivityListDto.setSportsDatas(sportsActivityTeacherDto);
		//sportsActivityListDto.setMessage("Sports Activity Data");
		return sportsActivityListDto;	
	}
	
	@Override
	public SportsActivityDetails listSportsActivity() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsActivity.class);
		SportsActivityDetails sportsActivityDetails = new SportsActivityDetails();
		logger.info("total number of record="+criteria.list().size());
		sportsActivityDetails.setCount(criteria.list().size());
		sportsActivityDetails.setMessage("Sports And Activity Details");
		sportsActivityDetails.setSportsActivitys((List<SportsActivity>) criteria.list());
		
		return sportsActivityDetails;
	}
	
	@Override
	public SportsActivityDetails getSportsActivityByClassIDSectionID(String classId, String sectionId){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsActivity.class,"sad")
		
		.add(Restrictions.eq("sad.classId", classId))
		.add(Restrictions.eq("sad.sectionId", sectionId));
		
		SportsActivityDetails sportsActivityDetails = new SportsActivityDetails();
		logger.info("total number of record="+criteria.list().size());
		sportsActivityDetails.setCount(criteria.list().size());
		sportsActivityDetails.setMessage("Sports And Activity Details");
		sportsActivityDetails.setSportsActivitys((List<SportsActivity>) criteria.list());
		
		return sportsActivityDetails;
	}

	@Override
	public SportsActivityDetails listSportsActivitySearch(int pageNumber, String searchData) {
		int maxPageData=10;
		int start = pageNumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsActivity.class);		
		if (!searchData.equalsIgnoreCase("null")) {
			String matchString = "%"+searchData+"%";			
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("activityDescription", matchString, MatchMode.ANYWHERE))
			.add(Restrictions.like("coach", matchString, MatchMode.ANYWHERE));			
			criteria.add(or);
		}
		SportsActivityDetails sportsActivityDetails = new SportsActivityDetails();
		logger.info("total number of record="+criteria.list().size());
		sportsActivityDetails.setCount(criteria.list().size());
		sportsActivityDetails.setSportsActivitys((List<SportsActivity>) criteria.list());
		criteria.setFirstResult(pageNumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);		
		return sportsActivityDetails;
	}	
	
	//Student Activity Details ************************************************
	@Override
	public void updateStudentActivity(List <StudentActivity> studentActivity) {
		
		for (StudentActivity activity : studentActivity) {		
			StudentActivity stActivity = sessionFactory.getCurrentSession().load(StudentActivity.class, activity.getStudentActivityId());
			stActivity.setDeactivateDate(activity.getDeactivateDate());
			stActivity.setEnrolledDate(activity.getEnrolledDate());
			stActivity.setUpdatedBy(activity.getUpdatedBy());		
			sessionFactory.getCurrentSession().update(stActivity);
		}		
	}
	
	@Override
	public void saveStudentActivity(List <StudentActivity> studentActivity) {
		
		for (StudentActivity activity : studentActivity) {				
			sessionFactory.getCurrentSession().save(activity);
		}		
	}
	
	
	//Student List for drop down
	@Override
	public List<StudentActivity> getAllStudentActivity() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentActivity.class);				
		return (List<StudentActivity>) criteria.list();
	}	
	
	@Override
	public void deleteStudentActivity(StudentActivity studentActivity) {
		sessionFactory.getCurrentSession().delete(studentActivity);	
	}
	
	@Override
	public Map<String , String> getClassListSelect() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSectionMaster.class);	

		ProjectionList p1=Projections.projectionList();
		         p1.add(Projections.property("classSectionMasterId.classId"));
		         p1.add(Projections.property("className"));	
		         criteria.setProjection(p1);

		List l=criteria.list();
		Iterator it=l.iterator();		
		Map<String, String> classList = new HashMap<String, String>();
		while(it.hasNext())
		{
			Object ob[] = (Object[])it.next();
			System.out.println(ob[0]+"--------"+ob[1]);
			classList.put((String)ob[0], (String)ob[1]);
		}		
		return classList;
	}	
	
	@Override
	public Map<String , String> getSectionListSelect(String classId) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSectionMaster.class);
		criteria.add(Restrictions.eq("classSectionMasterId.classId",classId));
		ProjectionList p1=Projections.projectionList();
		         p1.add(Projections.property("classSectionMasterId.sectionId"));
		         p1.add(Projections.property("sectionName"));
		         criteria.setProjection(p1);	
		         
		List l=criteria.list();
		Iterator it=l.iterator();		
		Map<String, String> classList = new HashMap<String, String>();

		while(it.hasNext())
		{
			Object ob[] = (Object[])it.next();
			System.out.println(ob[0]+"--------"+ob[1]);
			classList.put((String)ob[0], (String)ob[1]);
		}		
		return classList;
	}	
	

	@Override
	public StudentActivityDtoDetails getStudentsByClassSectionId(String classId, String sectionId) {		
		StudentActivityDtoDetails studentActivityDtoDetails = new StudentActivityDtoDetails();
		//StudentActivityDto studentDetail = new StudentActivityDto();
		List<StudentActivityDto> studentDetail= new ArrayList<>();
		StudentActivityDto studentActivityDto=null;		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentMaster.class);
		criteria.add(Restrictions.eq("classId",classId));
		criteria.add(Restrictions.eq("sectionId",sectionId));
		List<StudentMaster> m= (List<StudentMaster>) criteria.list();		
		for (StudentMaster studentMaster : m) {
			studentActivityDto=new StudentActivityDto();
			studentActivityDto.setStudentId(studentMaster.getStudentId());
			studentActivityDto.setStudentFirstName(studentMaster.getFirstName());
			studentActivityDto.setStudentMiddleName(studentMaster.getMiddleName());
			studentActivityDto.setStudentLastName(studentMaster.getLastName());
			studentDetail.add(studentActivityDto);
		}		
		studentActivityDtoDetails.setStudentActivityDto(studentDetail);
		studentActivityDtoDetails.setCount(m.size());
		studentActivityDtoDetails.setMessage("List of sports and activity");
		return studentActivityDtoDetails;
	}
	
	//listStudentsByClassSecActivity
		@Override
		public StudentActivityListDto listStudentsByClassSecActivity(String activityCode, String classId, String sectionId){
		
			Query query = sessionFactory.getCurrentSession().createQuery("SELECT stu.studentActivityId.activityCode,"
					+ " stu.studentActivityId.studentId, sp.activityDescription, stu.deactivateDate, stu.enrolledDate, sp.coach"
					+ " FROM StudentActivity stu, SportsActivity sp"
					+ " where sp.activityCode=stu.studentActivityId.activityCode and sp.activityCode = '"+activityCode+"'"
					+ " and sp.classId = '"+classId+"' and sp.sectionId = '"+sectionId+"'");
			
			List<Object[]> listOfTextBooks = query.list();		
			
			StudentActivityListDto textBookListDto = new StudentActivityListDto();
			List<StudentActiDto> textBookList= new ArrayList<>();
			StudentActiDto textBookDetails=null;

			for (Object[] objArr : listOfTextBooks) {
				
				textBookDetails = new StudentActiDto();
				
				textBookDetails.setActivityCode((int)objArr[0]);
				textBookDetails.setStudentId((String)objArr[1]);
				textBookDetails.setActivityDescription((String)objArr[2]);
				textBookDetails.setDeactivateDate((Date)objArr[3]);
				textBookDetails.setEnrolledDate((Date)objArr[4]);	
				textBookDetails.setCoach((String)objArr[5]);
				textBookList.add(textBookDetails);
			}
			textBookListDto.setCount(textBookList.size());
			textBookListDto.setStudentActivitys(textBookList);
			textBookListDto.setMessage("Student Activity List");
			return textBookListDto;	
		}
	
	
	@Override
	public void saveSportsAnnouncement(SportsAnnouncement sportsAnnouncement) {
		sessionFactory.getCurrentSession().save(sportsAnnouncement);
	}
	
	@Override
	public void editSportsAnnouncement(SportsAnnouncement sportsAnnouncement) {				
		sessionFactory.getCurrentSession().update(sportsAnnouncement);	
	}
	
	@Override
	public void deleteSportsAnnouncement(SportsAnnouncement sportsAnnouncement) {
		sessionFactory.getCurrentSession().delete(sportsAnnouncement);	
	}
	
	@Override
	public SportsAnnouncementDetails listSportsAnnouncement() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsAnnouncement.class);
		criteria.addOrder( Order.desc("id") );
		SportsAnnouncementDetails sportsAnnouncementDetails = new SportsAnnouncementDetails();
		logger.info("total number of record="+criteria.list().size());
		sportsAnnouncementDetails.setCount(criteria.list().size());
		sportsAnnouncementDetails.setMessage("Sports Announcement Details");
		sportsAnnouncementDetails.setSportsAnnouncements((List<SportsAnnouncement>) criteria.list());
		
		return sportsAnnouncementDetails;
	}
	
	@Override
	public SportsAnnouncementDetails getLatestSportsAnnouncement(String classId, String sectionId){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SportsAnnouncement.class);
		criteria.addOrder( Order.desc("id") );
		criteria.setMaxResults(1);
		SportsAnnouncementDetails sportsAnnouncementDetails = new SportsAnnouncementDetails();
		logger.info("total number of record="+criteria.list().size());
		
		sportsAnnouncementDetails.setCount(criteria.list().size());
		sportsAnnouncementDetails.setMessage("Sports Announcement Details");
		sportsAnnouncementDetails.setSportsAnnouncements((List<SportsAnnouncement>) criteria.list());
		
		return sportsAnnouncementDetails;
	}
	
}