package com.set.service;

import com.set.model.AssignmentEvaluation;
import com.set.model.AssignmentEvaluationDetails;
import com.set.model.AssignmentEvaluationId;
import com.set.model.AssignmentPageId;
import com.set.model.AssignmentPages;
import com.set.model.AssignmentPagesDetails;
import com.set.model.ClassSectionMasterId;

public interface AssignmentEvaluationService {
	void save(AssignmentEvaluation assignmentEvaluation);

	AssignmentEvaluationDetails list(int pagenumber, String searchdata);

	AssignmentEvaluation getElementById(AssignmentEvaluation UID);

	AssignmentEvaluation getAssignmentEvaluaitonById(AssignmentEvaluationId assignmentEvaluationId);

	void update(AssignmentEvaluation assignmentEvaluation);

	public void deleteElementById(AssignmentEvaluation id);

	public boolean IsExist(AssignmentEvaluationId assignmentEvaluationId);

	public int gettotalcount();

	AssignmentEvaluationDetails getClassSectionAssignments(int pagenumber, ClassSectionMasterId classSectionMasterId);

	void savePage(AssignmentPages assignmentPages);

	AssignmentPagesDetails getPagesByAssignmentId(int pagenumber, AssignmentPageId assignmentPageId);

	AssignmentEvaluationDetails studentSubmittedAssignments(AssignmentEvaluationId assignmentEvaluationId);
	
	AssignmentPagesDetails getAssignmentPages(AssignmentPageId assignmentPageId);
}
