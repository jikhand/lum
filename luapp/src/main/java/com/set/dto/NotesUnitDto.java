package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class NotesUnitDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -190962577743000551L;
	private String UnitId;
	private String UnitName;
	private List<NotesCategoryDto> NotesCategoryList;
	
	public String getUnitId() {
		return UnitId;
	}

	@JsonProperty("UnitId")
	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public String getUnitName() {
		return UnitName;
	}

	@JsonProperty("UnitName")
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	public List<NotesCategoryDto> getNotesCategoryList() {
		return NotesCategoryList;
	}

	@JsonProperty("notesdata")
	public void setNotesCategoryList(List<NotesCategoryDto> notesCategoryList) {
		NotesCategoryList = notesCategoryList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
