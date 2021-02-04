package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.SportsActivityDao;
import com.set.dto.NotificationListDto;
import com.set.dto.SportsActivityListDto;
import com.set.dto.SportsActivityMessageDto;
import com.set.dto.SportsActivityTeacherListDto;
import com.set.dto.StudentActivityDto;
import com.set.dto.StudentActivityDtoDetails;
import com.set.dto.StudentActivityListDto;
import com.set.model.Attendance;
import com.set.model.SportsActivity;
import com.set.model.SportsActivityDetails;
import com.set.model.SportsAnnouncement;
import com.set.model.SportsAnnouncementDetails;
import com.set.model.StudentActivity;
import com.set.model.StudentMaster;
import com.set.model.StudentsActivityDetails;
import com.set.model.TimeTableDetails;

@Service
public class SportsActivityServiceImpl implements SportsActivityService {

	@Autowired
	public SportsActivityDao sportsActivityDao;

	@Override
	public void save(SportsActivity sportsActivity) {
		sportsActivityDao.save(sportsActivity);
	}

	@Override
	public List<SportsActivity> getAllSportsActivity() {
		return sportsActivityDao.getAllSportsActivity();
	}

	@Override
	public void editSportsActivity(SportsActivity sportsActivity) {
		sportsActivityDao.editSportsActivity(sportsActivity);

	}

	@Override
	public void deleteSportsActivity(SportsActivity sportsActivity) {
		sportsActivityDao.deleteSportsActivity(sportsActivity);
	}
	
	@Override
	public List<SportsActivity> getSportsActivityByActivityCode(int activityCode){
		return sportsActivityDao.getSportsActivityByActivityCode(activityCode);
	}
	
	@Override
	public List<SportsActivity> getSportsActivityByTeacherOrCoach(String teacherCoach){
		return sportsActivityDao.getSportsActivityByTeacherOrCoach(teacherCoach);
	}
	
	@Override
	public SportsActivityListDto getSportsActivityByStudentId(String studentId){
		return sportsActivityDao.getSportsActivityByStudentId(studentId);
	}
	
	@Override
	public SportsActivityDetails listSportsActivity() {
		// TODO Auto-generated method stub
		return sportsActivityDao.listSportsActivity();
	}
	
	@Override
	public SportsActivityDetails getSportsActivityByClassIDSectionID(String classId, String sectionId) {
		// TODO Auto-generated method stub
		return sportsActivityDao.getSportsActivityByClassIDSectionID(classId, sectionId);
	}
	
	@Override
	public SportsActivityDetails listSportsActivitySearch(int pageNumber,String searchData) {
		return sportsActivityDao.listSportsActivitySearch(pageNumber, searchData);
	}
	
	@Override
	public SportsActivityTeacherListDto getSportsActivityByUserId(String userId){
		return sportsActivityDao.getSportsActivityByUserId(userId);
	}
	
	//Student Details Data*******************************	
	@Override
	public void saveStudentActivity(List <StudentActivity> studentActivity) {
		sportsActivityDao.saveStudentActivity(studentActivity);
	}
	
	@Override
	public List<StudentActivity> getAllStudentActivity() {
		return sportsActivityDao.getAllStudentActivity();
	}
	
	@Override
	public void updateStudentActivity(List <StudentActivity> studentActivity){
		sportsActivityDao.updateStudentActivity(studentActivity);
	}
	
	@Override
	public void deleteStudentActivity(StudentActivity studentActivity) {
		sportsActivityDao.deleteStudentActivity(studentActivity);
	}
	
	@Override
	public Map<String , String> getClassListSelect() {
		System.out.println("getClassListSelect*******************************");
		return sportsActivityDao.getClassListSelect();
	}
	
	
	@Override
	public Map<String , String> getSectionListSelect(String classId) {
		System.out.println("getClassListSelect*******************************");
		return sportsActivityDao.getSectionListSelect(classId);
	}
	
	@Override
	public StudentActivityDtoDetails getStudentsByClassSectionId(String classId, String sectionId) {
		return sportsActivityDao.getStudentsByClassSectionId(classId, sectionId);
	}
	
	@Override
	public StudentActivityListDto listStudentsByClassSecActivity(String activityCode, String classId, String sectionId) {
		return sportsActivityDao.listStudentsByClassSecActivity(activityCode, classId, sectionId);
	}
	
	@Override
	public void saveSportsAnnouncement(SportsAnnouncement sportsAnnouncement) {
		sportsActivityDao.saveSportsAnnouncement(sportsAnnouncement);
	}
	
	@Override
	public void editSportsAnnouncement(SportsAnnouncement sportsAnnouncement) {
		sportsActivityDao.editSportsAnnouncement(sportsAnnouncement);

	}
	
	@Override
	public void deleteSportsAnnouncement(SportsAnnouncement sportsAnnouncement) {
		sportsActivityDao.deleteSportsAnnouncement(sportsAnnouncement);
	}
	
	@Override
	public SportsAnnouncementDetails listSportsAnnouncement() {
		// TODO Auto-generated method stub
		return sportsActivityDao.listSportsAnnouncement();
	}
	@Override
	public SportsAnnouncementDetails getLatestSportsAnnouncement(String classId, String sectionId) {
		// TODO Auto-generated method stub
		return sportsActivityDao.getLatestSportsAnnouncement(classId, sectionId);
	}

}