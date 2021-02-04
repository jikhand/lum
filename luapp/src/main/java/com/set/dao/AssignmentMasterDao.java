package com.set.dao;

import java.util.List;


import com.set.model.AssignmentMaster;
import com.set.model.AssignmentMasterDetails;
import com.set.model.AssignmentMasterId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.ClassSectionMasterId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AssignmentMasterDao {
	void save(AssignmentMaster assignmentMaster);

	AssignmentMasterDetails list(int pagenumber, String searchdata);

	AssignmentMasterDetails getUserAssignments(int pagenumber, String userId);

	AssignmentMaster getElementById(AssignmentMaster UID);
	
	List<Map<String , String>> getStudentView(AssignmentMasterId UID);

	void update(AssignmentMaster assignmentMaster);

	public void deleteElementById(AssignmentMaster id);

	public void AssignmentActive(AssignmentMaster id);

	public void AssignmentInactive(AssignmentMaster id);

	public boolean IsExist(String name);

	public int gettotalcount();

	void deleteAssignment(AssignmentMaster assignmentMaster);

	AssignmentMasterDetails getClassSectionAssignments(int pagenumber, ClassSectionMasterId classSectionMasterId);

	AssignmentMaster getAssignmentId(AssignmentMasterId assignmentMasterId);

	int deleteAssignmentMaster(String assignmentId);
}
