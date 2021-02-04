package com.set.dao;

import com.set.model.Assignment;
import com.set.model.AssignmentDetails;

public interface AssignmentDao {
	public AssignmentDetails getAllAssignment(int pagenumber,String searchdata);
	public Assignment getAssignmentById(String AssignmentId);
	public void save(Assignment assignment);
	public void deleteAssignment(String id);
	public boolean IsExist(String searchcontent, long id);


}