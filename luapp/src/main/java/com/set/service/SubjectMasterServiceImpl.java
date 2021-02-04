package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.SubjectMasterDao;
import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterDetils;
@Service
public class SubjectMasterServiceImpl implements SubjectMasterService {
    @Autowired
    private SubjectMasterDao subjectMasterDao;
	
	@Override
	public void save(SubjectMaster subjectMaster) {
		subjectMasterDao.save(subjectMaster);
	}

	@Override
	public SubjectMasterDetils list(int pagenumber, String searchdata) {
		// TODO Auto-generated method stub
		return subjectMasterDao.list(pagenumber, searchdata);
	}

	@Override
	public SubjectMaster getSubjectMasterById(SubjectMaster UID) {
		return subjectMasterDao.getSubjectMasterById(UID);
	}

	@Override
	public void updateSubjectMaster(SubjectMaster subjectMaster) {
		subjectMasterDao.updateSubjectMaster(subjectMaster);	
	}

	@Override
	public void deleteSubjectMaster(SubjectMaster SubjectMasterId) {
		subjectMasterDao.deleteSubjectMaster(SubjectMasterId);

	}

	@Override
	public boolean IsExist(String SubjectName) {
		// TODO Auto-generated method stub
		return subjectMasterDao.IsExist(SubjectName);
	}

	@Override
	public int totalSubjectMaster() {
		// TODO Auto-generated method stub
		return subjectMasterDao.totalSubjectMaster();
	}

	@Override
	public List<SubjectMaster> getAllSubjectMasterSelect() {
		// TODO Auto-generated method stub
		return subjectMasterDao.getAllSubjectMasterSelect();
	}

}
