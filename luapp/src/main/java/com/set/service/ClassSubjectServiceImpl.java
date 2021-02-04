package com.set.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.set.dao.ClassSubjectDao;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetail;
import com.set.model.ClassSubjectDetails;
import com.set.model.User;

@Service
public class ClassSubjectServiceImpl implements ClassSubjectService {

	private Logger logger = Logger.getLogger("ClassSubjectServiceImpl.class");

	@Autowired
	private ClassSubjectDao classSubjectDao;

	@Override
	public void save(ClassSubject ClassSubject) {
		// TODO Auto-generated method stub
		classSubjectDao.save(ClassSubject);
	}

	@Override
	public ClassSubjectDetails list(int pagenumber, String searchdata) {
		// TODO Auto-generated method stub
		return classSubjectDao.list(pagenumber, searchdata);
	}

	@Override
	public ClassSubject getElementById(ClassSubject UID) {
		// TODO Auto-generated method stub
		return classSubjectDao.getElementById(UID);
	}

	@Override
	public void update(ClassSubject classSubject) {
		// TODO Auto-generated method stub
		classSubjectDao.update(classSubject);
	}

	@Override
	public void deleteElementById(ClassSubject ClassSubject) {
		classSubjectDao.deleteElementById(ClassSubject);// TODO Auto-generated method stub

	}

	@Override
	public boolean IsExist(String name) {
		// TODO Auto-generated method stub
		return classSubjectDao.IsExist(name);
	}

	@Override
	public int getTotalcount() {
		// TODO Auto-generated method stub
		return classSubjectDao.getTotalcount();
	}

	@Override
	public List<ClassSubject> getAllClassSubjectSelect() {
		return classSubjectDao.getAllClassSubjectSelect();
	}

	@Override
	public ClassSubjectDetails getUserSubjects(String userId) {
		return classSubjectDao.getUserSubjects(userId);
	}

	@Override
	public List<ClassSubject> getTeacherSubjects(String teacherId) {
		return classSubjectDao.getTeacherSubjects(teacherId);
	}

	@Override
	public ClassSubjectDetail getClassSubjectbyTeacherId(String teacherId) {
		return classSubjectDao.getClassSubjectbyTeacherId(teacherId);
	}

}
