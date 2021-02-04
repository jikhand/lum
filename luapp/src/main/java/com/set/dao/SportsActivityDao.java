package com.set.dao;

import java.util.List;
import java.util.Map;

import com.set.dto.SportsActivityDto;
import com.set.dto.SportsActivityListDto;
import com.set.dto.SportsActivityMessageDto;
import com.set.dto.SportsActivityTeacherListDto;
import com.set.dto.StudentActivityDto;
import com.set.dto.StudentActivityDtoDetails;
import com.set.dto.StudentActivityListDto;
import com.set.model.SportsActivity;
import com.set.model.SportsActivityDetails;
import com.set.model.SportsAnnouncement;
import com.set.model.SportsAnnouncementDetails;
import com.set.model.StudentActivity;
import com.set.model.StudentsActivityDetails;

public interface SportsActivityDao {
	//Sports Activity Data
	public void save(SportsActivity sportsActivity);
    public List<SportsActivity> getAllSportsActivity();
    public void editSportsActivity(SportsActivity sportsActivity); 
    public void deleteSportsActivity(SportsActivity sportsActivity); 
    public List<SportsActivity> getSportsActivityByActivityCode(int activityCode);
    public List<SportsActivity> getSportsActivityByTeacherOrCoach(String teacherCoach);
    public SportsActivityListDto getSportsActivityByStudentId(String studentId);
    public SportsActivityTeacherListDto getSportsActivityByUserId(String userId);
    SportsActivityDetails listSportsActivity();
    SportsActivityDetails getSportsActivityByClassIDSectionID(String classId, String sectionId);
    public SportsActivityDetails listSportsActivitySearch(int id, String searchData);
     
  //Student Activity Data*******************************
    public void saveStudentActivity(List <StudentActivity> studentActivity);
    public List<StudentActivity> getAllStudentActivity();    
    public void updateStudentActivity(List <StudentActivity> studentActivity);    
    public void deleteStudentActivity(StudentActivity studentActivity); 
    public Map<String , String> getClassListSelect();
    public Map<String , String> getSectionListSelect(String classId);
    public StudentActivityDtoDetails getStudentsByClassSectionId(String classId, String sectionId);
    public StudentActivityListDto listStudentsByClassSecActivity(String activityCode, String classId, String sectionId);
  //Sports Announcement Details *************************
    public void saveSportsAnnouncement(SportsAnnouncement sportsAnnouncement);
    public void editSportsAnnouncement(SportsAnnouncement sportsAnnouncement); 
    public void deleteSportsAnnouncement(SportsAnnouncement sportsAnnouncement);
    SportsAnnouncementDetails listSportsAnnouncement();
    SportsAnnouncementDetails getLatestSportsAnnouncement(String classId, String sectionId);
}