package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.set.dao.SubjectResourceDao;
import com.set.model.StudentNotes;
import com.set.model.StudentNotesDetails;
import com.set.model.SubjectResource;
import com.set.model.SubjectResourceDetails;
import com.set.model.SubjectResourceId;
@Service
public class SubjectResourceServiceImpl implements SubjectResourceService {
	
	@Autowired
	public SubjectResourceDao subjectResourceDao;

	@Override
	public void save(SubjectResource subjectResource) {
		subjectResourceDao.save(subjectResource);
	}

	@Override
	public SubjectResourceDetails getAllSubjectResource(int pagenumber,String searchdata) {
		return subjectResourceDao.getAllSubjectResource(pagenumber, searchdata);
	}

	@Override
	public List<SubjectResource> getAllSubjectResourceSelect() {
		return subjectResourceDao.getAllSubjectResourceSelect();
	}

	@Override
	public void updateSubjectResource(SubjectResource subjectResource) {
		subjectResourceDao.updateSubjectResource(subjectResource);
	}

	@Override
	public void deleteSubjectResource(SubjectResource subjectResource) {
		subjectResourceDao.deleteSubjectResource(subjectResource);
	}

	@Override
	public boolean IsExist(String notes,String resourceId) {
		// TODO Auto-generated method stub
		return subjectResourceDao.IsExist(notes,resourceId);
	}

	@Override
	public int totalSubjectResource() {
		return subjectResourceDao.totalSubjectResource();
	}

	@Override
	public SubjectResource getSubjectResourceById(SubjectResourceId subjectResourceId) {
		return subjectResourceDao.getSubjectResourceById(subjectResourceId);
	}

	@Override
	public List<Object[]> getAllResourceBankSubjectSelect(String classId) {
		return subjectResourceDao.getAllResourceBankSubjectSelect(classId);
	}

	@Override
	public List<Object[]> getAllResourceBankUnitSelect(String subjectId) {
		return subjectResourceDao.getAllResourceBankUnitSelect(subjectId);
	}

	@Override
	public List<Object[]> getAllResourceBankSelect(String unitId, String SubjectId) {
		return subjectResourceDao.getAllResourceBankSelect(unitId,SubjectId);
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankClassSelect(String teacherId) {
		return subjectResourceDao.getAllTeacherResourceBankClassSelect(teacherId);
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankSubjectSelect(String teacherId, String classId) {
		return subjectResourceDao.getAllTeacherResourceBankSubjectSelect(teacherId, classId);
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankUnitSelect(String subjectId) {
		return subjectResourceDao.getAllTeacherResourceBankUnitSelect(subjectId);
	}

	@Override
	public List<Object[]> getAllTeacherResourceBankSelect(String unitId, String subjectId) {
		return subjectResourceDao.getAllTeacherResourceBankSelect(unitId,subjectId);
	}



}
