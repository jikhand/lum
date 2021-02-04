package com.set.dao;

import java.util.Date;
import java.util.List;

import com.set.dto.AttendenceListDto;
import com.set.model.Attendance;

public interface AttendenceDao {
	public void save (Attendance attendence) throws Exception;

	public List<Attendance> getAllAttendenceByClassId(String classId);// new

	public List<Attendance> getAllAttendenceByAttendenceDate(Date attendenceDate);

	public AttendenceListDto getAttendenceByClass(String classId, String sectionId, Date attendenceDate);	
	
}
