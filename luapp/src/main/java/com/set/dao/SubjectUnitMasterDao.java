package com.set.dao;

import java.util.List;
import java.util.Map;

import com.set.model.SubjectUnitMaster;
import com.set.model.SubjectUnitMasterDetails;
import com.set.model.SubjectUnitMasterId;

public interface SubjectUnitMasterDao {
	public void save(SubjectUnitMaster subjectUnitMaster);
	  public SubjectUnitMasterDetails getAllSubjectUnitMaster(int pagenumber,String searchdata);
	  public List<SubjectUnitMaster> getAllSubjectUnitMasterSelect();
	  public SubjectUnitMaster getSubjectUnitMasterById(SubjectUnitMasterId subjectUnitMasterId);
	  public void updateSubjectUnitMaster(SubjectUnitMaster subjectUnitMaster);
	  public void deleteSubjectUnitMaster(SubjectUnitMaster subjectUnitMaster);
	  public boolean IsExist(String notes);
	  public int totalSubjectUnitMaster();
	  public List<Map<String, String>> getUnitBySubjectList(String SubjectId);
	  public List<Map<String, String>> getTopicByUnitList(String SubjectId, String UnitId);
}
