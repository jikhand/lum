package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.TimeTableDao;
import com.set.dto.TimeTableDayDto;
import com.set.dto.TimeTableDaysDto;
import com.set.dto.TimeTableDetailsDto;
import com.set.dto.TimeTableDetailsDtoSearch;
import com.set.dto.TimeTableEditParamaters;
import com.set.dto.TimeTableListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSubject;
import com.set.model.SubjectMaster;
import com.set.model.TimeTable;
import com.set.model.TimeTableDetails;
import com.set.model.TimeTableId;
import com.set.model.UserRegistration;
import com.set.model.WeekName;
import com.set.model.WorkHours;
@Service
public class TimeTableServiceImpl implements TimeTableService {
	
	@Autowired
	public TimeTableDao timeTableDao;

	@Override
	public void save(WorkHours workHours) {
		timeTableDao.save(workHours);
	}
	
	@Override
	public List<Map<String , String>> getAllClassSectionSelect(String teacherId,String subjectId) {
		return timeTableDao.getAllClassSectionSelect(teacherId,subjectId);
	}
	
	@Override
	public List<WeekName> getAllDaysOfWeekSelect() {
		return timeTableDao.getAllDaysOfWeekSelect();
	}
	
	@Override
	public List<Map<String , String>> getAllClassSubjectSelect(String teacherId) {		
		return timeTableDao.getAllClassSubjectSelect(teacherId);
	}
	
	@Override
	public List<UserRegistration> getAllUserRegSelect() {
		return timeTableDao.getAllUserRegSelect();
	}
	
	@Override
	public List<WorkHours> getAllWorkHoursSelect() {
		return timeTableDao.getAllWorkHoursSelect();
	}
	
	
	@Override
	public void saveTimeTable(TimeTable timeTable) {
		timeTableDao.saveTimeTable(timeTable);
	}
	
	@Override
	public int update(TimeTable timeTable) {
		return timeTableDao.update(timeTable);
	}
	
	@Override
	public List<TimeTableDayDto> listTimeTableByClassId(String classId,String sectionId) {
		// TODO Auto-generated method stub
		return timeTableDao.listTimeTableByClassId(classId,sectionId);
	}
	@Override
	public List<TimeTableDaysDto> listTodayTimeTableByClassId(String day, String classId, String sectionId) {
		// TODO Auto-generated method stub
		return timeTableDao.listTodayTimeTableByClassId(day, classId, sectionId);
	}
	@Override
	public List<TimeTableDayDto> listTimeTableByTeacher(String empId) {
		// TODO Auto-generated method stub
		return timeTableDao.listTimeTableByTeacher(empId);
	}
	@Override
	public List<TimeTableDaysDto> listTodayTimeTableByTeacher(String day, String empId) {
		// TODO Auto-generated method stub
		return timeTableDao.listTodayTimeTableByTeacher(day, empId);
	}
	@Override
	public TimeTableDetails getAllTimeTable(int pageNumber,String searchData) {
		return timeTableDao.getAllTimeTable(pageNumber, searchData);
	}
	@Override
	public TimeTableDetailsDtoSearch listAllTimeTable(int pageNumber, String searchData) {
		// TODO Auto-generated method stub
		return timeTableDao.listAllTimeTable(pageNumber, searchData);
	}
	
	@Override
	public TimeTableDetailsDtoSearch listAllTeacherTimeTable(int pageNumber, String searchData) {
		// TODO Auto-generated method stub
		return timeTableDao.listAllTeacherTimeTable(pageNumber, searchData);
	}
	
	@Override
	public TimeTableDetailsDtoSearch listAllStudentTimeTable(int pageNumber, String searchData,String ClassId,String SectionId) {
		// TODO Auto-generated method stub
		return timeTableDao.listAllStudentTimeTable(pageNumber, searchData,ClassId,SectionId);
	}
	
	@Override
	public TimeTableEditParamaters getTimeTableElementById(String classId,String sectionId,String workId,String subjId,String empId) {
		return timeTableDao.getTimeTableElementById(classId,sectionId,workId,subjId,empId);
	}
	
	@Override
	public void updateWorkHours(WorkHours workHours) {
		timeTableDao.updateWorkHours(workHours);
		
	}

	@Override
	public TimeTable deleteNotification(TimeTableId timeTableId) {
		// TODO Auto-generated method stub
		return timeTableDao.deleteNotification(timeTableId);
	}
}