package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.set.model.AssignmentPages;

public class SubmittedAssignmentDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3216582300211201785L;

	private SubmittedAssignmentEvaluationDto AssignmentDetail;
	private List<SubmittedAssignmentPageDto> Pages;

	public SubmittedAssignmentEvaluationDto getAssignmentDetail() {
		return AssignmentDetail;
	}
	@JsonProperty("AssignmentDetail")
	public void setAssignmentDetail(SubmittedAssignmentEvaluationDto assignmentDetail) {
		AssignmentDetail = assignmentDetail;
	}

	public List<SubmittedAssignmentPageDto> getPages() {
		return Pages;
	}
	@JsonProperty("Pages")
	public void setPages(List<SubmittedAssignmentPageDto> pages) {
		Pages = pages;
	}

}
