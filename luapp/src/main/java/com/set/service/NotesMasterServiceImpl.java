package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.NotesMasterDao;
import com.set.dto.NotesMasterTopicListDto;
import com.set.model.Drawing;
import com.set.model.DrawingDetails;
import com.set.model.NotesMaster;
import com.set.model.NotesMasterDetails;
import com.set.model.SubjectUnitMaster;
import com.set.model.SubjectUnitMasterDetails;
@Service
public class NotesMasterServiceImpl implements NotesMasterService {
	
	@Autowired
	public NotesMasterDao notesMasterDao;

	@Override
	public void save(NotesMaster notesMaster) {
        notesMasterDao.save(notesMaster);
	}

	@Override
	public List<Map<String , String>> getAllNotesMaster(int pagenumber,String searchdata) {
		return notesMasterDao.getAllNotesMaster(pagenumber, searchdata);
	}

	@Override
	public List<NotesMaster> getAllNotesMasterSelect() {
		return notesMasterDao.getAllNotesMasterSelect();
	}

	@Override
	public void updateNotesMaster(NotesMaster notesMaster) {
        notesMasterDao.updateNotesMaster(notesMaster);
	}

	@Override
	public void deleteNotesMaster(NotesMaster notesMaster) {
		notesMasterDao.deleteNotesMaster(notesMaster);
	}

	@Override
	public boolean IsExist(String SubjectId, String TeacherId, String UnitId, String TopicId) {
		return notesMasterDao.IsExist(SubjectId, TeacherId, UnitId, TopicId);
	}

	@Override
	public int totalNotesMaster() {
		return notesMasterDao.totalNotesMaster();
	}

	@Override
	public NotesMaster getNotesMasterById(String notesId) {
		return notesMasterDao.getNotesMasterById(notesId);
	}

	@Override
	public List<Object[]> getAllNotesSubjectSelect(String classId, String sectionId) {
		return notesMasterDao.getAllNotesSubjectSelect(classId, sectionId);
	}

	@Override
	public List<Object[]> getAllNotesUnitSelect(String subjectId) {
		return notesMasterDao.getAllNotesUnitSelect(subjectId);
	}

	@Override
	public List<Object[]> getAllNotesCategorySelect(String subjectId, String unitId) {
		return notesMasterDao.getAllNotesCategorySelect(subjectId, unitId);
	}
	
//	@Override
	public NotesMasterTopicListDto getAllTopicSelect() {
		return notesMasterDao.getAllTopicSelect();
	}

}
