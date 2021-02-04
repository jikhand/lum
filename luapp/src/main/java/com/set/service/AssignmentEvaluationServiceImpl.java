package com.set.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.AssignmentEvaluationDao;
import com.set.model.AssignmentEvaluation;
import com.set.model.AssignmentEvaluationDetails;
import com.set.model.AssignmentEvaluationId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.ClassSectionMasterId;

@Service
public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {
	@Autowired
	private AssignmentEvaluationDao assignmentEvaluationDao;

	@Override
	public void save(AssignmentEvaluation assignmentEvaluation) {
		assignmentEvaluationDao.save(assignmentEvaluation);
	}

	@Override
	public AssignmentEvaluationDetails list(int pagenumber, String searchdata) {
		return assignmentEvaluationDao.list(pagenumber, searchdata);
	}

	@Override
	public AssignmentEvaluation getElementById(AssignmentEvaluation UID) {
		return assignmentEvaluationDao.getElementById(UID);
	}

	@Override
	public void update(AssignmentEvaluation assignmentEvaluation) {
		assignmentEvaluationDao.update(assignmentEvaluation);
	}

	@Override
	public void deleteElementById(AssignmentEvaluation id) {
		assignmentEvaluationDao.deleteElementById(id);
	}

	@Override
	public boolean IsExist(AssignmentEvaluationId assignmentEvaluationId) {
		return assignmentEvaluationDao.IsExist(assignmentEvaluationId);
	}

	@Override
	public int gettotalcount() {
		return assignmentEvaluationDao.gettotalcount();
	}

	@Override
	public AssignmentEvaluationDetails getClassSectionAssignments(int pagenumber,
			ClassSectionMasterId classSectionMasterId) {
		return assignmentEvaluationDao.getClassSectionAssignments(pagenumber, classSectionMasterId);
	}
	
	@Override
	public void savePage(AssignmentPages assignmentPages) {
		assignmentEvaluationDao.savePage(assignmentPages);
	}

	@Override
	public AssignmentPagesDetails getPagesByAssignmentId(int pagenumber, AssignmentPageId assignmentPageId) {
		return assignmentEvaluationDao.getPagesByAssignmentId(pagenumber, assignmentPageId);
	}

	@Override
	public AssignmentEvaluation getAssignmentEvaluaitonById(AssignmentEvaluationId assignmentEvaluationId) {
		return assignmentEvaluationDao.getAssignmentEvaluaitonById(assignmentEvaluationId);
	}

	@Override
	public AssignmentEvaluationDetails studentSubmittedAssignments(AssignmentEvaluationId assignmentEvaluationId) {
		return assignmentEvaluationDao.studentSubmittedAssignments(assignmentEvaluationId);
	}

	@Override
	public AssignmentPagesDetails getAssignmentPages(AssignmentPageId assignmentPageId) {
		return assignmentEvaluationDao.getAssignmentPages(assignmentPageId);
	}
}
