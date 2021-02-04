package com.set.dao;

import java.util.List;

import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetail;
import com.set.model.ClassSubjectDetails;
import com.set.model.ClassSubjectId;

public interface ClassSubjectDao {
	void save(ClassSubject ClassSubject);

	ClassSubjectDetails list(int pagenumber, String searchdata);

	ClassSubject getElementById(ClassSubject UID);

	void update(ClassSubject classSubject);

	public void deleteElementById(ClassSubject classSubject);

	public boolean IsExist(String name);

	public int getTotalcount();

	public List<ClassSubject> getAllClassSubjectSelect();

	ClassSubjectDetails getUserSubjects(String userId);

	List<ClassSubject> getTeacherSubjects(String teacherId);

	ClassSubjectDetail getClassSubjectbyTeacherId(String teacherId);
}
