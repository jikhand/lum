package com.set.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.set.dao.AssignmentDao;
import com.set.model.Assignment;
import com.set.model.AssignmentDetails;

@Service
public class AssignmentServiceImp implements AssignmentService {
	@Autowired
	private AssignmentDao assignmentDao;

	@Override
	public AssignmentDetails getAllAssignment(int pagenumber, String searchdata) {
		// TODO Auto-generated method stub
		return assignmentDao.getAllAssignment(pagenumber, searchdata);
	}

	@Override
	public Assignment getAssignmentById(String AssignmentId) {
		// TODO Auto-generated method stub
		return assignmentDao.getAssignmentById(AssignmentId);
	}

	@Override
	public void save(Assignment assignment) {
		// TODO Auto-generated method stub
		assignmentDao.save(assignment);
		
	}

	@Override
	public void deleteAssignment(String id) {
		// TODO Auto-generated method stub
		assignmentDao.deleteAssignment(id);
	}

	@Override
	public boolean IsExist(String searchcontent, long id) {
		// TODO Auto-generated method stub
		return assignmentDao.IsExist(searchcontent, id);
	}

	

}