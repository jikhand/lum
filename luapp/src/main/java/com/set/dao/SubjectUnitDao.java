package com.set.dao;

import java.util.List;

import com.set.model.SubjectUnit;
import com.set.model.SubjectUnitDetails;

public interface SubjectUnitDao {
	  public void save(SubjectUnit subjectUnit);
	  public SubjectUnitDetails getAllSubjectUnit(int pagenumber,String searchdata);
	  public List<Object[]> getAllUnitSubjectClassSelect(String teacherId);
	  public List<Object[]> getAllUnitSubjectSelect(String classId, String sectionId, String teacherId);
	  public List<Object[]> getAllUnitSelect(String subjectId);
	  public List<Object[]> getAllTopicSelect(String subjectId, String unitId);
	  public SubjectUnit getSubjectUnitById(String notesId);
	  public void updateSubjectUnit(SubjectUnit subjectUnit);
	  public void deleteSubjectUnit(SubjectUnit subjectUnit);
	  public boolean IsExist(String notes);
	  public int totalSubjectUnit();
	  public List<SubjectUnit> getAllSubjectUnit();

}
