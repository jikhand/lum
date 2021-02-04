package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.set.dao.StudentNotesDao;
import com.set.model.NotesMaster;
import com.set.model.NotesMasterDetails;
import com.set.model.StudentNotes;
import com.set.model.StudentNotesDetails;
import com.set.model.StudentNotesId;
@Service
public class StudentNotesServiceImpl implements StudentNotesService {
	
	@Autowired
	public StudentNotesDao studentNotesDao;

	@Override
	public void save(StudentNotes studentNotes) {
		studentNotesDao.save(studentNotes);
	}

	@Override
	public StudentNotesDetails getAllStudentNotes(int pagenumber,String searchdata) {
		return studentNotesDao.getAllStudentNotes(pagenumber, searchdata);
	}

	@Override
	public List<StudentNotes> getAllStudentNotesSelect() {
		return studentNotesDao.getAllStudentNotesSelect();
	}

	@Override
	public void updateStudentNotes(StudentNotes studentNotes) {
		studentNotesDao.updateStudentNotes(studentNotes);
	}

	@Override
	public void deleteStudentNotes(StudentNotesId studentNotesId) {
		studentNotesDao.deleteStudentNotes(studentNotesId);
	}

	@Override
	public boolean IsExist(String notes) {
		// TODO Auto-generated method stub
		return studentNotesDao.IsExist(notes);
	}

	@Override
	public int totalStudentNotes() {
		return studentNotesDao.totalStudentNotes();
	}

	@Override
	public StudentNotes getStudentNotesById(StudentNotesId studentNotesId) {
		return studentNotesDao.getStudentNotesById(studentNotesId);
	}

	@Override
	public List<Object[]> getAllStudentNotesSubjectSelect(String studentId) {
		return studentNotesDao.getAllStudentNotesSubjectSelect(studentId);
	}

	@Override
	public List<Object[]> getAllStudentNotesUnitTopicSelect(String subjectId) {
		return studentNotesDao.getAllStudentNotesUnitTopicSelect(subjectId);
	}

	@Override
	public List<Object[]> getAllStudentNotesSelect(String notesId, String studentId) {
		return studentNotesDao.getAllStudentNotesSelect(notesId,studentId);
	}


}
