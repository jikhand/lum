package com.set.dao;

import java.util.List;
import java.util.Map;

import com.set.model.SubjectResource;
import com.set.model.SubjectResourceDetails;
import com.set.model.SubjectResourceId;

public interface SubjectResourceDao {
	public void save(SubjectResource subjectResource);
	  public SubjectResourceDetails getAllSubjectResource(int pagenumber,String searchdata);
	  public List<SubjectResource> getAllSubjectResourceSelect();
	  public List<Object[]> getAllTeacherResourceBankClassSelect(String teacherId);
	  public List<Object[]> getAllTeacherResourceBankSubjectSelect(String teacherId, String classId);
	  public List<Object[]> getAllTeacherResourceBankUnitSelect(String subjectId);
	  public List<Object[]> getAllTeacherResourceBankSelect(String unitId, String subjectId);
	  public List<Object[]> getAllResourceBankSubjectSelect(String classId);
	  public List<Object[]> getAllResourceBankUnitSelect(String subjectId);
	  public List<Object[]> getAllResourceBankSelect(String unitId, String SubjectId);
	  public SubjectResource getSubjectResourceById(SubjectResourceId subjectResourceId);
	  public void updateSubjectResource(SubjectResource subjectResource);
	  public void deleteSubjectResource(SubjectResource subjectResource);
	  public boolean IsExist(String notes,String resourceId);
	  public int totalSubjectResource();
}
