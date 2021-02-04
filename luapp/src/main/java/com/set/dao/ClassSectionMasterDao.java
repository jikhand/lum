package com.set.dao;

import java.util.List;

import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterDetails;
import com.set.model.SchoolClass;

public interface ClassSectionMasterDao {
	void save(ClassSectionMaster classSectionMaster);

	ClassSectionMasterDetails list(int pagenumber, String searchdata);

	ClassSectionMaster getElementById(ClassSectionMaster UID);

	void update(ClassSectionMaster classSectionMaster);

	public void deleteElementById(ClassSectionMaster classSectionMaster);

	public boolean IsExist(String name);

	public int gettotalcount();

	public List<ClassSectionMaster> getAllClassSectionMasterSelect();
	
	public List<SchoolClass> getSchoolClass();
}
