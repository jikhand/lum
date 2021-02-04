package com.set.service;

import java.util.List;
import java.util.Map;

import com.set.dto.NotesMasterTopicListDto;
import com.set.model.NotesMaster;
import com.set.model.NotesMasterDetails;

public interface NotesMasterService {
	  public void save(NotesMaster notesMaster);
	  public List<Map<String , String>> getAllNotesMaster(int pagenumber,String searchdata);
	  public List<NotesMaster> getAllNotesMasterSelect();
	  public List<Object[]> getAllNotesSubjectSelect(String classId, String sectionId);
	  public List<Object[]> getAllNotesUnitSelect(String subjectId);
	  public List<Object[]> getAllNotesCategorySelect(String subjectId, String unitId);
	  public NotesMaster getNotesMasterById(String notesId);
	  public void updateNotesMaster(NotesMaster notesMaster);
	  public void deleteNotesMaster(NotesMaster notesMaster);
	  public boolean IsExist(String SubjectId, String TeacherId, String UnitId, String TopicId);
	  public int totalNotesMaster();
	  public NotesMasterTopicListDto getAllTopicSelect();
}
