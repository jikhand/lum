package com.set.service;

import com.set.model.AssignmentMaster;
import com.set.model.AssignmentMasterDetails;
import com.set.model.AssignmentMasterId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.ClassSectionMasterId;

import java.util.List;
import java.util.Map;
public interface AssignmentMasterService {
	void save(AssignmentMaster assignmentMaster);

	AssignmentMasterDetails list(int pagenumber, String searchdata);

	AssignmentMasterDetails getUserAssignments(int pagenumber, String userId);

	AssignmentMaster getElementById(AssignmentMaster UID);
	
	List<Map<String , String>> getStudentView(AssignmentMasterId UID);

	void update(AssignmentMaster assignmentMaster);

	public void deleteElementById(AssignmentMaster id);

	public boolean IsExist(String name);

	public int gettotalcount();

	public void AssignmentActive(AssignmentMaster assignmentMaster);

	public void AssignmentInactive(AssignmentMaster assignmentMaster);

	public void deleteAssignement(AssignmentMaster assignmentMaster);

	AssignmentMasterDetails getClassSectionAssignments(int pagenumber, ClassSectionMasterId classSectionMasterId);

	AssignmentMaster getAssignmentById(AssignmentMasterId assignmentMasterId);
	
	int deleteAssignmentMaster(String assignmentId);

}
