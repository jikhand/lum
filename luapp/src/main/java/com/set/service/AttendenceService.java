package com.set.service;

import java.util.Date;
import java.util.List;

import com.set.dto.AttendenceListDto;
import com.set.model.Attendance;

public interface AttendenceService {
	public void save(Attendance attendence) throws Exception;

	public List<Attendance> getAllAttendenceByClassId(String classId);

	public List<Attendance> getAllAttendenceByAttendenceDate(Date attendenceDate);
	
	public AttendenceListDto getAttendenceByClass(String classId, String sectionId, Date attendanceDate);
	
}
