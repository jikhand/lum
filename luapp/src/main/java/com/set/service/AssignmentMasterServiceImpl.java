package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.AssignmentMasterDao;
import com.set.model.AssignmentMaster;
import com.set.model.AssignmentMasterDetails;
import com.set.model.AssignmentMasterId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.ClassSectionMasterId;

@Service
public class AssignmentMasterServiceImpl implements AssignmentMasterService {
	@Autowired
	private AssignmentMasterDao assignmentMasterDao;
	
	@Override
	public void save(AssignmentMaster assignmentMaster) {
		assignmentMasterDao.save(assignmentMaster);
	}

	@Override
	public AssignmentMasterDetails list(int pagenumber, String searchdata) {
		return assignmentMasterDao.list(pagenumber, searchdata);
	}


	@Override
	public AssignmentMaster getElementById(AssignmentMaster UID) {
		return assignmentMasterDao.getElementById(UID);
	}
	
	@Override
	public List<Map<String , String>> getStudentView(AssignmentMasterId UID) {
		return assignmentMasterDao.getStudentView(UID);
	}
	
	@Override
	public AssignmentMaster getAssignmentById(AssignmentMasterId assignmentMasterId) {
		return assignmentMasterDao.getAssignmentId(assignmentMasterId);
	}
	@Override
	public void update(AssignmentMaster assignmentMaster) {
		assignmentMasterDao.update(assignmentMaster);
	}

	@Override
	public void deleteElementById(AssignmentMaster id) {
		assignmentMasterDao.deleteElementById(id);
	}

	@Override
	public boolean IsExist(String name) {
		return assignmentMasterDao.IsExist(name);
	}

	@Override
	public int gettotalcount() {
		return assignmentMasterDao.gettotalcount();
	}

	@Override
	public void AssignmentActive(AssignmentMaster assignmentMaster) {
		assignmentMasterDao.AssignmentActive(assignmentMaster);
	}

	@Override
	public void AssignmentInactive(AssignmentMaster assignmentMaster) {
		assignmentMasterDao.AssignmentInactive(assignmentMaster);
	}

	@Override
	public AssignmentMasterDetails getUserAssignments(int pagenumber, String userId) {
		return assignmentMasterDao.getUserAssignments(pagenumber, userId);
	}

	@Override
	public void deleteAssignement(AssignmentMaster assignmentMaster) {
		assignmentMasterDao.deleteAssignment(assignmentMaster);
	}

	@Override
	public AssignmentMasterDetails getClassSectionAssignments(int pagenumber, ClassSectionMasterId classSectionMasterId) {
		return assignmentMasterDao.getClassSectionAssignments(pagenumber, classSectionMasterId);
	}
	@Override
	public int deleteAssignmentMaster(String assignmentId) {
		return assignmentMasterDao.deleteAssignmentMaster(assignmentId);
	} 
}
