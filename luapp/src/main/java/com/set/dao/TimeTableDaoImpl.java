package com.set.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.dto.TimeTableDayDto;
import com.set.dto.TimeTableDaysDto;
import com.set.dto.TimeTableDetailsDto;
import com.set.dto.TimeTableDetailsDtoSearch;
import com.set.dto.TimeTableDto;
import com.set.dto.TimeTableDtoSearch;
import com.set.dto.TimeTableEditParamaters;
import com.set.dto.TimeTableListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectId;
import com.set.model.SubjectMaster;
import com.set.model.TimeTable;
import com.set.model.TimeTableDetails;
import com.set.model.TimeTableId;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.model.WeekName;
import com.set.model.WorkHours;
import com.set.service.UserRegistrationService;
import com.set.service.UserService;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@Transactional
@Repository
public class TimeTableDaoImpl implements TimeTableDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	private Logger logger = Logger.getLogger("TimeTableDaoImpl.class");

	@Override
	public void save(WorkHours workHours) {
		sessionFactory.getCurrentSession().save(workHours);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String , String>> getAllClassSectionSelect(String teacherId,String subjectId) {
		String StrQuery = "SELECT sm.class_id,sm.section_id,sm.class_name,sm.section_name FROM lutbl_class_subj as cs join lutbl_class_sec_master as sm on sm.class_id=cs.class_id and sm.section_id=cs.section_id WHERE cs.teacher_id='"
				+ teacherId + "' and subj_id='"+subjectId+"'";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (null != row[0]) {
				hm.put("class_id", row[0].toString());
			} else {
				hm.put("class_id", "");
			}
			if (null != row[1]) {
				hm.put("section_id", row[1].toString());
			} else {
				hm.put("section_id", "");
			}
			
			if (null != row[2]) {
				hm.put("class_name", row[2].toString());
			} else {
				hm.put("class_name", "");
			}
			if (null != row[3]) {
				hm.put("section_name", row[3].toString());
			} else {
				hm.put("section_name", "");
			}
			arrayList.add(hm);
		}
		return arrayList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeekName> getAllDaysOfWeekSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WeekName.class);
		// criteria.add(Restrictions.eq("isDeleted",0));
		return (List<WeekName>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllClassSubjectSelect(String teacherId) {
		String StrQuery = "SELECT sm.subj_id,sm.subject_name FROM lutbl_class_subj as cs join lutbl_subject_master as sm on sm.subj_id=cs.subj_id WHERE cs.teacher_id='"
				+ teacherId + "' group by  sm.subj_id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (null != row[0]) {
				hm.put("subj_id", row[0].toString());
			} else {
				hm.put("subj_id", "");
			}
			if (null != row[1]) {
				hm.put("subject_name", row[1].toString());
			} else {
				hm.put("subject_name", "");
			}
			arrayList.add(hm);
		}
		return arrayList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRegistration> getAllUserRegSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserRegistration.class);
		return (List<UserRegistration>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkHours> getAllWorkHoursSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WorkHours.class);
		return (List<WorkHours>) criteria.list();
	}

	@Override
	public void saveTimeTable(TimeTable timeTable) {
		sessionFactory.getCurrentSession().save(timeTable);
	}

	@Override
	public int update(TimeTable timeTable) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("update TimeTable" + " set timeTableId.classId = :classId,"
						+ " timeTableId.sectionId = :sectionId," + " scheduleId = :scheduleId,"
						+ " timeTableId.subjId = :subjId," + " timeTableId.empId = :empId"
						+ " where timeTableId.workId = :workId");
		query.setParameter("classId", timeTable.getTimeTableId().getClassId());
		query.setParameter("sectionId", timeTable.getTimeTableId().getSectionId());
		query.setParameter("workId", timeTable.getTimeTableId().getWorkId());
		query.setParameter("subjId", timeTable.getTimeTableId().getSubjId());
		query.setParameter("empId", timeTable.getTimeTableId().getEmpId());
		query.setParameter("scheduleId", timeTable.getScheduleId());
		return query.executeUpdate();
	}

	@Override
	public List<TimeTableDayDto> listTimeTableByClassId(String classId, String sectionId) { 
	String StrQuery = "SELECT day, day_name FROM lutbl_week_name";
	SQLQuery daySql = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
	List<Object[]> listOfDays = daySql.list();
	List<TimeTableDayDto> timeDtoList = new ArrayList<>();
	TimeTableDayDto daysList = null;
	TimeTableDto timeTableDetailsDto = null;
	for (Object[] daylist : listOfDays) {
	daysList = new TimeTableDayDto();
	String dayId = daylist[0].toString();
	daysList.setDayId(dayId);
	daysList.setDayName(daylist[1].toString()); 
	List<TimeTableDto> timeTableDtoList = new ArrayList<>();
	String strQuery = "SELECT wh.duration, wh.day, wn.dayName, tt.timeTableId"
	+ " ,tt.timeTableId.subjId, sm.subjectName, tt.timeTableId.classId, tt.timeTableId.sectionId, csm.className"
	+ " ,wh.startTime, wh.endTime, csm.sectionName, st.schoolStartDate, st.schoolEndDate"
	+ " FROM TimeTable tt , WorkHours wh, WeekName wn, ClassSectionMaster csm, SubjectMaster sm, SchoolTimings st"
	+ " WHERE tt.timeTableId.workId=wh.workId and wh.day=wn.weekNameId.day"
	+ " and tt.timeTableId.classId=csm.classSectionMasterId.classId"
	+ " and tt.timeTableId.subjId=sm.subjectMasterId.subjectId" + " and tt.timeTableId.classId='" + classId +"'"
	+ " and tt.timeTableId.sectionId='" + sectionId +"' and wn.weekNameId.day='"+dayId+"'"
	+ " and tt.academicYear=st.academicYear order by wh.noOfHours ASC";
	Query query = sessionFactory.getCurrentSession().createQuery(strQuery);
	System.out.println("query"+query);
	List<Object[]> listOfTimeTable = query.list();
	for (Object[] objArr : listOfTimeTable) {
	timeTableDetailsDto = new TimeTableDto();
	timeTableDetailsDto.setDuration((float) objArr[0]);
	timeTableDetailsDto.setDayId((String) objArr[1]);
	timeTableDetailsDto.setDayName((String) objArr[2]);
	TimeTableId timeTableId = (TimeTableId) objArr[3];
	timeTableDetailsDto.setTimeTableId(timeTableId.getWorkId());
	timeTableDetailsDto.setSubjectId((String) objArr[4]);
	timeTableDetailsDto.setSubjectName((String) objArr[5]);
	timeTableDetailsDto.setClassId((String) objArr[6]);
	timeTableDetailsDto.setSectionId((String) objArr[7]);
	timeTableDetailsDto.setClassName((String) objArr[8]); 
	timeTableDetailsDto.setSectionName((String) objArr[11]);
	
	String stDat = objArr[9].toString();
	String edDat = objArr[10].toString();
	String startDate = objArr[12].toString();
	String endDate = objArr[13].toString();
	String datSplit[] = stDat.split(" ");
	int posit = ordinalIndexOf(datSplit[1], ":", 2);
	String hrFormat = datSplit[1].substring(0, posit);
	String edDatSplit[] = edDat.split(" ");
	int post = ordinalIndexOf(edDatSplit[1], ":", 2);
	String hrFormatEndTime = edDatSplit[1].substring(0, post);
	String stDate[] = startDate.split(" ");
	String enDate[] = endDate.split(" ");
	timeTableDetailsDto.setStartDate(stDate[0]);
	timeTableDetailsDto.setEndDate(enDate[0]);
	timeTableDetailsDto.setScheduleFromTime(hrFormat);
	timeTableDetailsDto.setScheduleToTime(hrFormatEndTime); 
	timeTableDtoList.add(timeTableDetailsDto);
	}
	daysList.setMessage("Timetable By Class Id");
	daysList.setCode("200");
	daysList.setTimeTableListData(timeTableDtoList);
	timeDtoList.add(daysList);
	}
	return timeDtoList;
	}



	
	// GetTodayTimeTableByClassId

	@Override
	public List<TimeTableDaysDto> listTodayTimeTableByClassId(String day, String classId, String sectionId) {

		Query query = sessionFactory.getCurrentSession()
				.createQuery("SELECT wh.duration, wh.day, wn.dayName, tt.timeTableId"
						+ " ,tt.timeTableId.subjId, sm.subjectName, tt.timeTableId.classId, tt.timeTableId.sectionId, csm.className, wh.startTime"
						+ " ,wh.endTime, csm.sectionName, st.schoolStartDate, st.schoolEndDate"
						+ " FROM TimeTable tt , WorkHours wh, WeekName wn, ClassSectionMaster csm, SubjectMaster sm, SchoolTimings st"
						+ " WHERE tt.timeTableId.workId=wh.workId and wh.day=wn.weekNameId.day"
						+ " and tt.timeTableId.classId=csm.classSectionMasterId.classId"
						+ " and tt.timeTableId.sectionId=csm.classSectionMasterId.sectionId"
						+ " and tt.timeTableId.subjId=sm.subjectMasterId.subjectId" + " and tt.timeTableId.classId='"
						+ classId + "' and tt.timeTableId.sectionId='"+sectionId+"' and wh.day='" + day + "'and tt.academicYear=st.academicYear order by wh.noOfHours ASC");
		
//		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT wh.duration, wh.day, wn.day_name, tt.subj_id"
//				+ " ,sm.subject_name, tt.class_id, tt.section_id, csm.class_name, wh.start_time, wh.end_time, csm.section_name"
//				+ " FROM lutbl_timetable tt , lutbl_work_hours wh, lutbl_week_name wn, lutbl_class_sec_master csm"
//				+ " ,lutbl_subject_master sm WHERE tt.work_id=wh.work_id and wh.day=wn.day and tt.class_id=csm.class_id"
//				+ " and tt.subj_id=sm.subj_id and tt.class_id='"+classId+"' and wh.day='"+day+"'");
		
		List<Object[]> listOfTimeTable = query.list();

		logger.info("listOfTimeTable size : " + listOfTimeTable.size());
		Calendar tzCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));		

		TimeTableListDto timeTableListDto = new TimeTableListDto();
		List<TimeTableDaysDto> timeDtoList = new ArrayList<>();
		TimeTableDaysDto daysList = null;
		TimeTableDto timeTableDetailsDto = null;

		for (Object[] objArr : listOfTimeTable) {
			daysList = new TimeTableDaysDto();

			timeTableDetailsDto = new TimeTableDto();
			timeTableDetailsDto.setDuration((float) objArr[0]);
			timeTableDetailsDto.setDayId((String) objArr[1]);
			timeTableDetailsDto.setDayName((String) objArr[2]);
			TimeTableId timeTableId = (TimeTableId) objArr[3];
			timeTableDetailsDto.setTimeTableId(timeTableId.getEmpId());
			timeTableDetailsDto.setSubjectId((String) objArr[4]);
			timeTableDetailsDto.setSubjectName((String) objArr[5]);
			timeTableDetailsDto.setClassId((String) objArr[6]);
			timeTableDetailsDto.setSectionId((String) objArr[7]);
			timeTableDetailsDto.setClassName((String) objArr[8]);			
			timeTableDetailsDto.setSectionName((String) objArr[11]);


			String stDat = objArr[9].toString();
			String edDat = objArr[10].toString();
			String startDate = objArr[12].toString();
			String endDate = objArr[13].toString();
			String datSplit[] = stDat.split(" ");
			int posit = ordinalIndexOf(datSplit[1], ":", 2);
			String hrFormat = datSplit[1].substring(0, posit);
			String edDatSplit[] = edDat.split(" ");
			int post = ordinalIndexOf(edDatSplit[1], ":", 2);
			String hrFormatEndTime = edDatSplit[1].substring(0, post);
			String stDate[] = startDate.split(" ");
			String enDate[] = endDate.split(" ");
			timeTableDetailsDto.setStartDate(stDate[0]);
			timeTableDetailsDto.setEndDate(enDate[0]);
			timeTableDetailsDto.setScheduleFromTime(hrFormat);
			timeTableDetailsDto.setScheduleToTime(hrFormatEndTime); 
			daysList.setDayID((String) objArr[1]);
			daysList.setDayNAME((String) objArr[2]);
			daysList.setTimeTableData(timeTableDetailsDto);
			daysList.setMessage("Time Table List");
			daysList.setCode("200");
			timeDtoList.add(daysList);
		}
		timeTableListDto.setMessage("TimeTable Data");
		return timeDtoList;
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}


	// GetTimeTableByTeacher
@Override
	public List<TimeTableDayDto> listTimeTableByTeacher(String empId) {

	String StrQuery = "SELECT day, day_name FROM lutbl_week_name";
	SQLQuery daySql = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
	List<Object[]> listOfDays = daySql.list();
	List<TimeTableDayDto> timeDtoList = new ArrayList<>();
	TimeTableDayDto daysList = null;
	TimeTableDto timeTableDetailsDto = null;
	for (Object[] daylist : listOfDays) {
		daysList = new TimeTableDayDto();
		String dayId = daylist[0].toString();
		daysList.setDayId(dayId);
		daysList.setDayName(daylist[1].toString());	
		List<TimeTableDto> timeTableDtoList = new ArrayList<>();
		Query query = sessionFactory.getCurrentSession()
				.createQuery("SELECT wh.duration, wh.day, wn.dayName, tt.timeTableId"
						+ " ,tt.timeTableId.subjId, sm.subjectName, tt.timeTableId.classId, tt.timeTableId.sectionId, csm.className, wh.startTime"
						+ " ,wh.endTime, csm.sectionName, st.schoolStartDate, st.schoolEndDate"
						+ " FROM TimeTable tt , WorkHours wh, WeekName wn, ClassSectionMaster csm, SubjectMaster sm, EmployeeMaster emp, SchoolTimings st"
						+ " WHERE tt.timeTableId.empId=emp.employeeId and tt.timeTableId.workId=wh.workId and wh.day=wn.weekNameId.day"
						+ " and tt.timeTableId.classId=csm.classSectionMasterId.classId"
						+ " and tt.timeTableId.sectionId=csm.classSectionMasterId.sectionId"
						+ " and tt.timeTableId.subjId=sm.subjectMasterId.subjectId" + " and tt.timeTableId.empId='"
						+ empId + "' and wn.weekNameId.day='"+dayId+"'" 
								+ " and tt.academicYear=st.academicYear order by wh.noOfHours ASC");
		List<Object[]> listOfTimeTable = query.list();
	
		for (Object[] objArr : listOfTimeTable) {
			
			timeTableDetailsDto = new TimeTableDto();
			timeTableDetailsDto.setDuration((float) objArr[0]);
			timeTableDetailsDto.setDayId((String) objArr[1]);
			timeTableDetailsDto.setDayName((String) objArr[2]);
			TimeTableId timeTableId = (TimeTableId) objArr[3];
			timeTableDetailsDto.setTimeTableId(timeTableId.getWorkId());
			timeTableDetailsDto.setSubjectId((String) objArr[4]);
			timeTableDetailsDto.setSubjectName((String) objArr[5]);
			timeTableDetailsDto.setClassId((String) objArr[6]);
			timeTableDetailsDto.setSectionId((String) objArr[7]);
			timeTableDetailsDto.setClassName((String) objArr[8]);			
			timeTableDetailsDto.setSectionName((String) objArr[11]);


			String stDat = objArr[9].toString();
			String edDat = objArr[10].toString();
			String startDate = objArr[12].toString();
			String endDate = objArr[13].toString();
			String datSplit[] = stDat.split(" ");
			int posit = ordinalIndexOf(datSplit[1], ":", 2);
			String hrFormat = datSplit[1].substring(0, posit);
			String edDatSplit[] = edDat.split(" ");
			int post = ordinalIndexOf(edDatSplit[1], ":", 2);
			String hrFormatEndTime = edDatSplit[1].substring(0, post);
			String stDate[] = startDate.split(" ");
			String enDate[] = endDate.split(" ");
			timeTableDetailsDto.setStartDate(stDate[0]);
			timeTableDetailsDto.setEndDate(enDate[0]);
			timeTableDetailsDto.setScheduleFromTime(hrFormat);
			timeTableDetailsDto.setScheduleToTime(hrFormatEndTime); 
			timeTableDtoList.add(timeTableDetailsDto);
		}
		daysList.setMessage("Timetable By Teacher Id");
		daysList.setCode("200");
		daysList.setTimeTableListData(timeTableDtoList);
		timeDtoList.add(daysList);
	}
		return timeDtoList;
	}



	// GetTodayTimeTableByTeacher
	@Override
	public List<TimeTableDaysDto> listTodayTimeTableByTeacher(String day, String empId) {

		Query query = sessionFactory.getCurrentSession()
				.createQuery("SELECT wh.duration, wh.day, wn.dayName, tt.timeTableId"
						+ " ,tt.timeTableId.subjId, sm.subjectName, tt.timeTableId.classId, tt.timeTableId.sectionId, csm.className, wh.startTime"
						+ " ,wh.endTime, csm.sectionName, st.schoolStartDate, st.schoolEndDate"
						+ " FROM TimeTable tt , WorkHours wh, WeekName wn, ClassSectionMaster csm, SubjectMaster sm, EmployeeMaster emp, SchoolTimings st"
						+ " WHERE tt.timeTableId.empId=emp.employeeId and tt.timeTableId.workId=wh.workId and wh.day=wn.weekNameId.day"
						+ " and tt.timeTableId.classId=csm.classSectionMasterId.classId"
						+ " and tt.timeTableId.sectionId=csm.classSectionMasterId.sectionId"
						+ " and tt.timeTableId.subjId=sm.subjectMasterId.subjectId" + " and tt.timeTableId.empId='"
						+ empId + "' and wh.day='" + day + "' and tt.academicYear=st.academicYear order by wh.noOfHours ASC");

		List<Object[]> listOfTimeTable = query.list();

		System.out.println("listOfTimeTable size : " + listOfTimeTable.size());

		TimeTableListDto timeTableListDto = new TimeTableListDto();
		List<TimeTableDaysDto> timeDtoList = new ArrayList<>();
		TimeTableDaysDto daysList = null;
		TimeTableDto timeTableDetailsDto = null;

		for (Object[] objArr : listOfTimeTable) {
			daysList = new TimeTableDaysDto();

			timeTableDetailsDto = new TimeTableDto();
			timeTableDetailsDto.setDuration((float) objArr[0]);
			timeTableDetailsDto.setDayId((String) objArr[1]);
			timeTableDetailsDto.setDayName((String) objArr[2]);
			TimeTableId timeTableId = (TimeTableId) objArr[3];
			timeTableDetailsDto.setTimeTableId(timeTableId.getEmpId());
			timeTableDetailsDto.setSubjectId((String) objArr[4]);
			timeTableDetailsDto.setSubjectName((String) objArr[5]);
			timeTableDetailsDto.setClassId((String) objArr[6]);
			timeTableDetailsDto.setSectionId((String) objArr[7]);
			timeTableDetailsDto.setClassName((String) objArr[8]);			
			timeTableDetailsDto.setSectionName((String) objArr[11]);


			String stDat = objArr[9].toString();
			String edDat = objArr[10].toString();
			String startDate = objArr[12].toString();
			String endDate = objArr[13].toString();
			String datSplit[] = stDat.split(" ");
			int posit = ordinalIndexOf(datSplit[1], ":", 2);
			String hrFormat = datSplit[1].substring(0, posit);
			String edDatSplit[] = edDat.split(" ");
			int post = ordinalIndexOf(edDatSplit[1], ":", 2);
			String hrFormatEndTime = edDatSplit[1].substring(0, post);
			String stDate[] = startDate.split(" ");
			String enDate[] = endDate.split(" ");
			timeTableDetailsDto.setStartDate(stDate[0]);
			timeTableDetailsDto.setEndDate(enDate[0]);
			timeTableDetailsDto.setScheduleFromTime(hrFormat);
			timeTableDetailsDto.setScheduleToTime(hrFormatEndTime); 
			
			daysList.setDayID((String) objArr[1]);
			daysList.setDayNAME((String) objArr[2]);
			daysList.setTimeTableData(timeTableDetailsDto);
			daysList.setMessage("Time Table List");
			daysList.setCode("200");
			timeDtoList.add(daysList);
		}
		timeTableListDto.setMessage("TimeTable Data");
		return timeDtoList;
	}


	@Override
	public TimeTableDetails getAllTimeTable(int pageNumber, String searchData) {
		int maxPageData = 10;
		int start = pageNumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTable.class);
		if (!searchData.equalsIgnoreCase("null")) {
			String matchString = "%" + searchData + "%";
			logger.info("matchString: " + matchString);

			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("timeTableId.classId", matchString, MatchMode.ANYWHERE))
					.add(Restrictions.like("timeTableId.sectionId", matchString, MatchMode.ANYWHERE))
					.add(Restrictions.like("timeTableId.sectionId", matchString, MatchMode.ANYWHERE))
					.add(Restrictions.like("timeTableId.workId", matchString, MatchMode.ANYWHERE))
					.add(Restrictions.like("timeTableId.subjId", matchString, MatchMode.ANYWHERE))
					.add(Restrictions.like("timeTableId.empId", matchString, MatchMode.ANYWHERE));

			criteria.add(or);
		}
		TimeTableDetails timeTableDetails = new TimeTableDetails();
		logger.info("total number of record=" + criteria.list().size());
		timeTableDetails.setCount(criteria.list().size());
		timeTableDetails.setTimeTables((List<TimeTable>) criteria.list());
		criteria.setFirstResult(pageNumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);
		return timeTableDetails;
	}

	@Override
	public TimeTableDetailsDtoSearch listAllTimeTable(int pageNumber, String searchData) {
		int maxPageData = 10;
		int start = pageNumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		if (!searchData.equalsIgnoreCase("null")) {
			searchString=" and sm.subject_name LIKE '%"+searchData+"%' or wn.day_name LIKE '%"+searchData +"%' or csm.section_name LIKE '%"+searchData+"%' "
					+ "or csm.class_name LIKE '%"+searchData+"%'"+ 
					" or tt.academic_year LIKE '%"+searchData+"%' or wh.duration LIKE '%"+searchData+"%'";
		}
		String StrQuery="SELECT any_value(wh.duration) as duration,"
				+ "any_value(wh.day),"
				+ "any_value(wn.day_name)"
				+ ",any_value(tt.subj_id),"
				+ "any_value(sm.subject_name)"
				+ ",any_value(tt.class_id),"
				+ "any_value(tt.section_id)"
				+ ",any_value(csm.class_name),"
				+ "any_value(csm.section_name),"
				+ "any_value(wh.start_time),"
				+ "any_value(wh.end_time),"
				+ "any_value(wh.work_id)"
				+ ",any_value(tt.emp_id),"
				+ "any_value(sm.teacher_name)"
				+ ",any_value(tt.academic_year) "
				+ "FROM lutbl_timetable as tt join lutbl_work_hours as wh on wh.work_id=tt.work_id join lutbl_week_name as wn on wn.day=wh.day join lutbl_class_sec_master as csm on csm.class_id=tt.class_id and csm.section_id=tt.section_id JOIN lutbl_class_subj as sm on sm.class_id=tt.class_id and sm.section_id=tt.section_id and sm.subj_id=tt.subj_id and sm.teacher_id=tt.emp_id "
				+ " WHERE tt.is_deleted='0' "+searchString+" GROUP BY wh.work_id";
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(StrQuery);
		System.out.println("StrQuery="+StrQuery);
		List<Object[]> listOfTimeTable = query.list();
		TimeTableDetailsDtoSearch timeTableDetailsDto = new TimeTableDetailsDtoSearch();
		List<TimeTableDaysDto> timeDtoList = new ArrayList<>();
		List<TimeTableDtoSearch> timeTableDtoList = new ArrayList<>();
		TimeTableDaysDto daysList = null;
		TimeTableDtoSearch timeTableDto = null;
		for (Object[] objArr : listOfTimeTable) {
			daysList = new TimeTableDaysDto();
			timeTableDto = new TimeTableDtoSearch();
			timeTableDto.setDuration(Float.valueOf(objArr[0].toString()));
			timeTableDto.setDayId((String) objArr[1]);
			timeTableDto.setDayName((String) objArr[2]);
			timeTableDto.setSubjectId((String) objArr[3]);
			timeTableDto.setSubjectName((String) objArr[4]);
			timeTableDto.setClassId((String) objArr[5]);
			timeTableDto.setSectionId((String) objArr[6]);
			timeTableDto.setClassName((String) objArr[7]);
			timeTableDto.setSectionName((String) objArr[8]);
			timeTableDto.setStartDate((Date) objArr[9]);
			timeTableDto.setEndDate((Date) objArr[10]);
			timeTableDto.setWorkId((String) objArr[11]);
			timeTableDto.setEmpId((String) objArr[12]);			
			timeTableDto.setFirstName((String) objArr[13]);
			timeTableDto.setAcademicYear(Integer.parseInt(objArr[14].toString()));
			timeTableDtoList.add(timeTableDto);
		}
		timeTableDetailsDto.setTimeTableList(timeTableDtoList);
		timeTableDetailsDto.setCount(timeTableDtoList.size());
		timeTableDetailsDto.setMessage("TimeTable Data");
		return timeTableDetailsDto;
	}
	
	@Override
	public TimeTableDetailsDtoSearch listAllTeacherTimeTable(int pageNumber, String searchData) {
		int maxPageData = 10;
		int start = pageNumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		if (!searchData.equalsIgnoreCase("null")) {
			searchString=" and sm.subject_name LIKE '%"+searchData+"%' or wn.day_name LIKE '%"+searchData +"%' or csm.section_name LIKE '%"+searchData+"%' "
					+ "or csm.class_name LIKE '%"+searchData+"%'"+ 
					" or tt.academic_year LIKE '%"+searchData+"%' or wh.duration LIKE '%"+searchData+"%'";
			//searchString = " or wn.dayName LIKE '%" + searchData + "%' or csm.className LIKE '%" + searchData
				//	+ "%'";
			System.out.println("searchString="+searchString);
			System.out.println("workId "+searchData);
			System.out.println("subjId ");
			
		}
		String StrQuery="SELECT any_value(wh.duration),"
				+ "any_value(wh.day),"
				+ "any_value(wn.day_name)"
				+ ",any_value(tt.subj_id),"
				+ "any_value(sm.subject_name)"
				+ ",any_value(tt.class_id),"
				+ "any_value(tt.section_id)"
				+ ",any_value(csm.class_name),"
				+ "any_value(csm.section_name),"
				+ "any_value(wh.start_time),"
				+ "any_value(wh.end_time),"
				+ "any_value(wh.work_id)"
				+ ",any_value(tt.emp_id),"
				+ "any_value(sm.teacher_name)"
				+ ",any_value(tt.academic_year) "
				+ "FROM lutbl_timetable as tt join lutbl_work_hours as wh on wh.work_id=tt.work_id join lutbl_week_name as wn on wn.day=wh.day join lutbl_class_sec_master as csm on csm.class_id=tt.class_id and csm.section_id=tt.section_id JOIN lutbl_class_subj as sm on sm.class_id=tt.class_id and sm.section_id=tt.section_id and sm.subj_id=tt.subj_id and sm.teacher_id=tt.emp_id join lutbl_emp_master as em on em.emp_id=tt.emp_id "
				+ " WHERE tt.is_deleted='0' and em.role_id='T'"+searchString+" GROUP BY wh.work_id ";
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(StrQuery);
		List<Object[]> listOfTimeTable = query.list();
		TimeTableDetailsDtoSearch timeTableDetailsDto = new TimeTableDetailsDtoSearch();
		List<TimeTableDaysDto> timeDtoList = new ArrayList<>();
		List<TimeTableDtoSearch> timeTableDtoList = new ArrayList<>();
		TimeTableDaysDto daysList = null;
		TimeTableDtoSearch timeTableDto = null;
		for (Object[] objArr : listOfTimeTable) {
			daysList = new TimeTableDaysDto();
			timeTableDto = new TimeTableDtoSearch();
			timeTableDto.setDuration(Float.valueOf(objArr[0].toString()));
			timeTableDto.setDayId((String) objArr[1]);
			timeTableDto.setDayName((String) objArr[2]);
			timeTableDto.setSubjectId((String) objArr[3]);
			timeTableDto.setSubjectName((String) objArr[4]);
			timeTableDto.setClassId((String) objArr[5]);
			timeTableDto.setSectionId((String) objArr[6]);
			timeTableDto.setClassName((String) objArr[7]);
			timeTableDto.setSectionName((String) objArr[8]);
			timeTableDto.setStartDate((Date) objArr[9]);
			timeTableDto.setEndDate((Date) objArr[10]);
			timeTableDto.setWorkId((String) objArr[11]);
			timeTableDto.setEmpId((String) objArr[12]);			
			timeTableDto.setFirstName((String) objArr[13]);
			timeTableDto.setAcademicYear(Integer.parseInt(objArr[14].toString()));
			timeTableDtoList.add(timeTableDto);
		}
		timeTableDetailsDto.setTimeTableList(timeTableDtoList);
		timeTableDetailsDto.setCount(timeTableDtoList.size());
		timeTableDetailsDto.setMessage("TimeTable Data");
		return timeTableDetailsDto;
	}

	@Override
	public TimeTableDetailsDtoSearch listAllStudentTimeTable(int pageNumber, String searchData,String ClassId,String SectionId) {
		int maxPageData = 10;
		int start = pageNumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		if (!searchData.equalsIgnoreCase("null")) {
			searchString=" and sm.subject_name LIKE '%"+searchData+"%' or wn.day_name LIKE '%"+searchData +"%' or csm.section_name LIKE '%"+searchData+"%' "
					+ "or csm.class_name LIKE '%"+searchData+"%'"+ 
					" or tt.academic_year LIKE '%"+searchData+"%' or wh.duration LIKE '%"+searchData+"%'";		
		}
		String StrQuery="SELECT any_value(wh.duration),"
				+ "any_value(wh.day),"
				+ "any_value(wn.day_name)"
				+ ",any_value(tt.subj_id),"
				+ "any_value(sm.subject_name)"
				+ ",any_value(tt.class_id),"
				+ "any_value(tt.section_id)"
				+ ",any_value(csm.class_name),"
				+ "any_value(csm.section_name),"
				+ "any_value(wh.start_time),"
				+ "any_value(wh.end_time),"
				+ "any_value(wh.work_id)"
				+ ",any_value(tt.emp_id),"
				+ "any_value(sm.teacher_name)"
				+ ",any_value(tt.academic_year) "
				+ "FROM lutbl_timetable as tt join lutbl_work_hours as wh on wh.work_id=tt.work_id join lutbl_week_name as wn on wn.day=wh.day join lutbl_class_sec_master as csm on csm.class_id=tt.class_id and csm.section_id=tt.section_id JOIN lutbl_class_subj as sm on sm.class_id=tt.class_id and sm.section_id=tt.section_id and sm.subj_id=tt.subj_id and sm.teacher_id=tt.emp_id"
				+ " WHERE tt.is_deleted='0' and tt.class_id='"+ClassId+"' and tt.section_id='"+SectionId+"'  "+searchString+" GROUP BY wh.work_id ";
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(StrQuery);
		System.out.println("StrQuery="+StrQuery);
		List<Object[]> listOfTimeTable = query.list();
		TimeTableDetailsDtoSearch timeTableDetailsDto = new TimeTableDetailsDtoSearch();
		List<TimeTableDaysDto> timeDtoList = new ArrayList<>();
		List<TimeTableDtoSearch> timeTableDtoList = new ArrayList<>();
		TimeTableDaysDto daysList = null;
		TimeTableDtoSearch timeTableDto = null;
		for (Object[] objArr : listOfTimeTable) {
			daysList = new TimeTableDaysDto();
			timeTableDto = new TimeTableDtoSearch();
			timeTableDto.setDuration(Float.valueOf(objArr[0].toString()));
			timeTableDto.setDayId((String) objArr[1]);
			timeTableDto.setDayName((String) objArr[2]);
			timeTableDto.setSubjectId((String) objArr[3]);
			timeTableDto.setSubjectName((String) objArr[4]);
			timeTableDto.setClassId((String) objArr[5]);
			timeTableDto.setSectionId((String) objArr[6]);
			timeTableDto.setClassName((String) objArr[7]);
			timeTableDto.setSectionName((String) objArr[8]);
			timeTableDto.setStartDate((Date) objArr[9]);
			timeTableDto.setEndDate((Date) objArr[10]);
			timeTableDto.setWorkId((String) objArr[11]);
			timeTableDto.setEmpId((String) objArr[12]);			
			timeTableDto.setFirstName((String) objArr[13]);
			timeTableDto.setAcademicYear(Integer.parseInt(objArr[14].toString()));
			timeTableDtoList.add(timeTableDto);
		}
		timeTableDetailsDto.setTimeTableList(timeTableDtoList);
		timeTableDetailsDto.setCount(timeTableDtoList.size());
		timeTableDetailsDto.setMessage("TimeTable Data");
		return timeTableDetailsDto;	
	}

	
	public TimeTableEditParamaters getTimeTableElementById(String classId, String sectionId, String workId, String subjId, String empId) {
		String strQuery="SELECT class_id,section_id,tt.work_id,subj_id"
				+ " ,tt.emp_id,wh.day, wh.hour_no, wh.duration"
				+ " ,wh.start_time,wh.end_time,tt.academic_year FROM lutbl_timetable tt, lutbl_work_hours wh"
				+ " where tt.work_id = wh.work_id and tt.class_id = '"+classId+"' and tt.section_id='"+sectionId+"'"
				+ " and tt.subj_id='"+subjId+"' and tt.work_id='"+workId+"'"
				+ " and tt.emp_id='"+empId+"'";
		System.out.println("strQuery="+strQuery);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery);
		
		//(TimeTableEditParamaters)query.uniqueResult(); 
		Object[] dt = (Object[]) query.uniqueResult();
		TimeTableEditParamaters timeTable = new TimeTableEditParamaters();
		timeTable.setClassId(dt[0].toString());
		timeTable.setSectionId(dt[1].toString());
		timeTable.setWorkId(dt[2].toString());
		timeTable.setSubjectId(dt[3].toString());
		timeTable.setEmpId(dt[4].toString());
		timeTable.setDay(dt[5].toString());
		timeTable.setHrNo(dt[6].toString());
		timeTable.setDuration((float)dt[7]);
		timeTable.setStartTime(dt[8].toString());
		timeTable.setEndTime(dt[9].toString());
		System.out.println("dt[10].toString()==="+dt[10].toString());
		timeTable.setAcademicYear(dt[10].toString());
//	
//		System.out.println("query: "+query);
//		List<Object[]> listOfTextBooks = query.list();
//		
//		List<TimeTableEditParamaters> bookList= new ArrayList<>();
//		TimeTableEditParamaters timeTable=null;
//		
//
//		Object[] objArr = listOfTextBooks;
//			
//			timeTable = new TimeTableEditParamaters();
//			timeTable.setClassId((String)objArr[0]);
//			timeTable.setSectionId((String)objArr[1]);
//			timeTable.setWorkId((String)objArr[2]);
//			timeTable.setSubjectId((String)objArr[3]);
//			timeTable.setEmpId((String)objArr[4]);			
//			timeTable.setDay((String)objArr[5]);			
//			timeTable.setHrNo((String)objArr[6]);
//			
//			timeTable.setDuration((float)objArr[7]);
//			timeTable.setStartTime((String)objArr[8]);
//			timeTable.setEndTime((String)objArr[9]);			
//			bookList.add(timeTable);
//		
		 return timeTable;		
	}
	
	@Override
	public void updateWorkHours(WorkHours workHours) {
		sessionFactory.getCurrentSession().update(workHours);
	}
	@Override
	public TimeTable deleteNotification(TimeTableId timeTableId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTable.class);
		criteria.add(Restrictions.eq("timeTableId",timeTableId));
		TimeTable timeTable=(TimeTable) criteria.uniqueResult();
		//System.out.println(""+timeTable.ge);
		timeTable.setIsDeleted(1);
		sessionFactory.getCurrentSession().update(timeTable);
		return timeTable;
	}
}