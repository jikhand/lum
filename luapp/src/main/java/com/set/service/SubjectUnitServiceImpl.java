package com.set.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.SubjectUnitDao;
import com.set.model.SubjectUnit;
import com.set.model.SubjectUnitDetails;
import com.set.model.SubjectUnitMaster;
import com.set.model.SubjectUnitMasterDetails;
@Service
public class SubjectUnitServiceImpl implements SubjectUnitService {
	
	@Autowired
	public SubjectUnitDao subjectUnitDao;
	private Logger logger = Logger.getLogger("SubjectUnitController.class");

	@Override
	public void save(SubjectUnit subjectUnit) {
		subjectUnitDao.save(subjectUnit);
	}

	@Override
	public SubjectUnitDetails getAllSubjectUnit(int pagenumber,String searchdata) {
		return subjectUnitDao.getAllSubjectUnit(pagenumber, searchdata);
	}

	@Override
	public void updateSubjectUnit(SubjectUnit subjectUnit) {
		subjectUnitDao.updateSubjectUnit(subjectUnit);
	}

	@Override
	public void deleteSubjectUnit(SubjectUnit subjectUnit) {
		subjectUnitDao.deleteSubjectUnit(subjectUnit);
	}

	@Override
	public boolean IsExist(String notes) {
		// TODO Auto-generated method stub
		return subjectUnitDao.IsExist(notes);
	}

	@Override
	public int totalSubjectUnit() {
		return subjectUnitDao.totalSubjectUnit();
	}

	@Override
	public SubjectUnit getSubjectUnitById(String notesId) {
		return subjectUnitDao.getSubjectUnitById(notesId);
	}

	@Override
	public List<Object[]> getAllUnitSubjectClassSelect(String teacherId) {
		return subjectUnitDao.getAllUnitSubjectClassSelect(teacherId);
	}

	@Override
	public List<Object[]> getAllUnitSubjectSelect(String classId, String sectionId, String teacherId) {
		return subjectUnitDao.getAllUnitSubjectSelect(classId, sectionId, teacherId);
	}

	@Override
	public List<Object[]> getAllUnitSelect(String subjectId) {
		return subjectUnitDao.getAllUnitSelect(subjectId);
	}

	@Override
	public List<Object[]> getAllTopicSelect(String subjectId, String unitId) {
		return subjectUnitDao.getAllTopicSelect(subjectId, unitId);
	}

	@Override
	public List<SubjectUnit> getAllSubjectUnit() {
		// TODO Auto-generated method stub
		return subjectUnitDao.getAllSubjectUnit();
	}


}
