package com.set.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.AttendenceDao;
import com.set.dto.AttendenceListDto;
import com.set.model.Attendance;
@Service
public class AttendenceServiceImpl implements AttendenceService {
	
	@Autowired
	public AttendenceDao attendenceDao;

	@Override
	public void save(Attendance attendence) throws Exception{
		attendenceDao.save(attendence);
	}

	@Override
	public List<Attendance> getAllAttendenceByClassId(String classId){
		return attendenceDao.getAllAttendenceByClassId(classId);
	}

	@Override
	public List<Attendance> getAllAttendenceByAttendenceDate(Date attendenceDate) {
		return attendenceDao.getAllAttendenceByAttendenceDate(attendenceDate) ;
	}

	@Override
	public AttendenceListDto getAttendenceByClass(String classId, String sectionId, Date attendenceDate) {
		return attendenceDao.getAttendenceByClass(classId, sectionId, attendenceDate);
	}

	
}
