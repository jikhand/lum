package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ClassSectionMasterDao;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterDetails;
import com.set.model.SchoolClass;
@Service
public class ClassSectionMasterServiceImpl implements ClassSectionMasterService{
    @Autowired
    private ClassSectionMasterDao classSectionMasterDao;
	@Override
	public void save(ClassSectionMaster classSectionMaster) {
		classSectionMasterDao.save(classSectionMaster);
		
	}

	@Override
	public ClassSectionMasterDetails list(int pagenumber, String searchdata) {
		return classSectionMasterDao.list(pagenumber, searchdata);
	}

	@Override
	public ClassSectionMaster getElementById(ClassSectionMaster UID) {
		return classSectionMasterDao.getElementById(UID);
	}

	@Override
	public void update(ClassSectionMaster classSectionMaster) {
		classSectionMasterDao.update(classSectionMaster);
		
	}

	@Override
	public void deleteElementById(ClassSectionMaster classSectionMaster) {
		classSectionMasterDao.deleteElementById(classSectionMaster);
	}

	@Override
	public boolean IsExist(String name) {
		return classSectionMasterDao.IsExist(name);
	}

	@Override
	public int gettotalcount() {
		return classSectionMasterDao.gettotalcount();
	}

	@Override
	public List<ClassSectionMaster> getAllClassSectionMasterSelect() {
		return classSectionMasterDao.getAllClassSectionMasterSelect();
	}
	
	@Override
	public List<SchoolClass> getSchoolClass() {
		return classSectionMasterDao.getSchoolClass();
	}

}
