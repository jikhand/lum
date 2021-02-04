package com.set.service;

import java.util.List;

import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterDetils;

public interface SubjectMasterService {
      void save(SubjectMaster subjectMaster);
	  SubjectMasterDetils list(int pagenumber,String searchdata);
	  SubjectMaster getSubjectMasterById(SubjectMaster UID);
	  void updateSubjectMaster(SubjectMaster subjectMaster);
	  public void deleteSubjectMaster(SubjectMaster SubjectMaster);
	  public boolean IsExist(String SubjectName);
	  public int totalSubjectMaster();
	  public List<SubjectMaster> getAllSubjectMasterSelect();
}
