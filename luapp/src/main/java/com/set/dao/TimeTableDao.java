package com.set.dao;

import java.util.List;
import java.util.Map;

import com.set.dto.TimeTableDayDto;
import com.set.dto.TimeTableDaysDto;
import com.set.dto.TimeTableDetailsDto;
import com.set.dto.TimeTableDetailsDtoSearch;
import com.set.dto.TimeTableEditParamaters;
import com.set.dto.TimeTableListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSubject;
import com.set.model.Notification;
import com.set.model.SubjectMaster;
import com.set.model.TimeTable;
import com.set.model.TimeTableDetails;
import com.set.model.TimeTableId;
import com.set.model.UserRegistration;
import com.set.model.WeekName;
import com.set.model.WorkHours;

public interface TimeTableDao {
	  public void save(WorkHours workHours);
	  public List<Map<String , String>> getAllClassSectionSelect(String teacherId,String subjectId);
	  public List<WeekName> getAllDaysOfWeekSelect();
	  public List<Map<String , String>> getAllClassSubjectSelect(String teacherId);
	  public List<UserRegistration> getAllUserRegSelect();
	  public List<WorkHours> getAllWorkHoursSelect();
	  public void saveTimeTable(TimeTable timeTable);
	  public int update(TimeTable timeTable);
	  List<TimeTableDayDto> listTimeTableByClassId(String classId, String sectionId); 
	  List<TimeTableDaysDto> listTodayTimeTableByClassId(String day, String classId, String sectionId); 	  
	  List<TimeTableDayDto> listTimeTableByTeacher(String empId);
	  List<TimeTableDaysDto> listTodayTimeTableByTeacher(String day, String empId);
	  
	  public TimeTableDetails getAllTimeTable(int pageNumber,String searchData);
	  TimeTableDetailsDtoSearch listAllTimeTable(int pageNumber, String searchData);
	  TimeTableDetailsDtoSearch listAllTeacherTimeTable(int pageNumber, String searchData);
	  TimeTableDetailsDtoSearch listAllStudentTimeTable(int pageNumber, String searchData,String ClassId,String SectionId);
	  
	  public TimeTableEditParamaters getTimeTableElementById(String classId, String sectionId, String workId, String subjId, String empId);
	  public void updateWorkHours(WorkHours workHours);
	  public TimeTable deleteNotification(TimeTableId timeTableId); 
	  
}