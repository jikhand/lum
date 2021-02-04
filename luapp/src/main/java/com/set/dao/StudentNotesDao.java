package com.set.dao;

import java.util.List;

import com.set.model.StudentNotes;
import com.set.model.StudentNotesDetails;
import com.set.model.StudentNotesId;

public interface StudentNotesDao {
	  public void save(StudentNotes studentNotes);
	  public StudentNotesDetails getAllStudentNotes(int pagenumber,String searchdata);
	  public List<StudentNotes> getAllStudentNotesSelect();
	  public List<Object[]> getAllStudentNotesSubjectSelect(String studentId);
	  public List<Object[]> getAllStudentNotesUnitTopicSelect(String subjectId);
	  public List<Object[]> getAllStudentNotesSelect(String notesId, String studentId);
	  public StudentNotes getStudentNotesById(StudentNotesId studentNotesId);
	  public void updateStudentNotes(StudentNotes studentNotes);
	  public void deleteStudentNotes(StudentNotesId studentNotesId);
	  public boolean IsExist(String notes);
	  public int totalStudentNotes();

}
