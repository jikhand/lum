package com.set.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetail;
import com.set.model.ClassSubjectDetails;

@Service
public interface ClassSubjectService {
	void save(ClassSubject ClassSubject);

	ClassSubjectDetails list(int pagenumber, String searchdata);

	ClassSubject getElementById(ClassSubject UID);

	void update(ClassSubject classSubject);

	public void deleteElementById(ClassSubject ClassSubject);

	public boolean IsExist(String name);

	public int getTotalcount();

	public List<ClassSubject> getAllClassSubjectSelect();

	ClassSubjectDetails getUserSubjects(String userId);
	
	public List<ClassSubject> getTeacherSubjects(String teacherId);

	ClassSubjectDetail getClassSubjectbyTeacherId(String teacherId);
}
