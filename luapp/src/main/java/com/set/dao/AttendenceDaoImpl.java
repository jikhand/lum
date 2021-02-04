package com.set.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.dto.AttendenceDto;
import com.set.dto.AttendenceListDto;
import com.set.model.Attendance;

@Transactional
@Repository
public class AttendenceDaoImpl implements AttendenceDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("AttendanceImp.class");

	@Override
	public void save(Attendance attendence) throws Exception{
		sessionFactory.getCurrentSession().saveOrUpdate(attendence);
	}

	@Override
	public List<Attendance> getAllAttendenceByClassId(String classId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Attendance.class,"att")
				.add(Restrictions.eq("att.classId", classId));
		return (List<Attendance>) criteria.list();
	}

	@Override
	public List<Attendance> getAllAttendenceByAttendenceDate(Date attendenceDate) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Attendance.class)
				.add(Restrictions.eq("attendanceId.attendanceDate", attendenceDate));
		return (List<Attendance>) criteria.list();
	}
	
	@Override
	public AttendenceListDto getAttendenceByClass(String classId, String sectionId, Date attendenceDate) {

		Query query = sessionFactory.getCurrentSession().createQuery("Select "
				+ "a.attendanceId,"
				+ "csm.className,sm.permanentAddressLine1,"
				+ "sm.emailId,sm.firstName,sm.middleName,sm.lastName,sm.mobilePhone,sm.profilePicture"
				+ " from Attendance a, StudentMaster sm, ClassSectionMaster csm"
				+ " where a.studentId=sm.studentId and a.classId=csm.classSectionMasterId.classId and a.classId = '"+classId+"'"
				+ " and a.sectionId = '"+sectionId+"' and a.attendanceDate = '"+attendenceDate+"'") ;
		
		List<Object[]> listOfAttendence = query.list();
		AttendenceListDto attendenceListDto = new AttendenceListDto();
		List<AttendenceDto> attendenceDtoList= new ArrayList<>();
		AttendenceDto attendenceDetailsDto=null;
		for (Object[] objArr : listOfAttendence) {
			
			attendenceDetailsDto = new AttendenceDto();
			attendenceDetailsDto.setId((String)objArr[0]);
			attendenceDetailsDto.setClassName((String)objArr[1]);			
			attendenceDetailsDto.setStudentAddress((String)objArr[2]);
			attendenceDetailsDto.setStudentEmail((String)objArr[3]);
			attendenceDetailsDto.setStudentFirstName((String)objArr[4]);			
			attendenceDetailsDto.setStudentMiddleName((String)objArr[5]);
			attendenceDetailsDto.setStudentLastName((String)objArr[6]);
			attendenceDetailsDto.setStudentMobileNumber(String.valueOf((Integer)objArr[7]));
			attendenceDetailsDto.setStudentProfileImage((String)objArr[8]);
//			attendenceDetailsDto.setStudentRollNumber((String)objArr[9]);			
			attendenceDtoList.add(attendenceDetailsDto);
		}
		attendenceListDto.setCount(attendenceDtoList.size());
		attendenceListDto.setAttendence(attendenceDtoList);
		attendenceListDto.setMessage("Attendence List");
		return attendenceListDto;	
	}

}
