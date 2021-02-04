/**
 * 
 */
package com.set.dao;
import java.util.List;

import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterDetils;
import com.set.model.UserRegistration;

/**
 * @author set0091
 *
 */
public interface SubjectMasterDao {
      void save(SubjectMaster subjectMaster);
	  SubjectMasterDetils list(int pagenumber,String searchdata);
	  SubjectMaster getSubjectMasterById(SubjectMaster UID);
	  void updateSubjectMaster(SubjectMaster subjectMaster);
	  public void deleteSubjectMaster(SubjectMaster SubjectMasterId);
	  public boolean IsExist(String SubjectName);
	  public int totalSubjectMaster();
	  public List<SubjectMaster> getAllSubjectMasterSelect();
}
