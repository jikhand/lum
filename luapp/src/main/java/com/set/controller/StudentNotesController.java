package com.set.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.dto.NotesCategoryDto;
import com.set.dto.NotesDetailsDto;
import com.set.dto.NotesSubjectDetailsDto;
import com.set.dto.NotesSubjectDto;
import com.set.dto.NotesUnitDto;
import com.set.dto.StudentNotesDetailsDto;
import com.set.dto.StudentNotesDto;
import com.set.dto.StudentNotesSubjectDto;
import com.set.dto.StudentNotesUnitTopicDto;
import com.set.model.ClassSection;
import com.set.model.LuMessage;
import com.set.model.NotesMaster;
import com.set.model.NotesMasterDetails;
import com.set.model.NotesStudentId;
import com.set.model.Page;
import com.set.model.Student;
//import com.set.model.NotesPagesId;
import com.set.model.StudentNotes;
import com.set.model.StudentNotesDetails;
import com.set.model.StudentNotesId;
import com.set.model.SubjectUnit;
import com.set.service.StudentNotesService;
import com.set.service.TokenService;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class StudentNotesController {
	@Autowired
	private StudentNotesService studentNotesService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("StudentNotesController.class");

	@PostMapping(value = "/InsertNotes", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addStudentNotes(@RequestBody StudentNotes studentNotes,
			@RequestHeader String token) {

		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		StudentNotes tempStudentNotes = new StudentNotes();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			message.setCode("401");
			logger.info("Invalid token");
		} else {
			Page e[] = studentNotes.getPage();
			for (int i = 0; i <= e.length - 1; i++) {
				String notesId = studentNotes.getNotesId();
				int pageNo = e[i].getPageNo();
				String base64Image = e[i].getPagePath();
				String studentId = studentNotes.getStudentId();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String fileName = "notesImage" + System.currentTimeMillis();
				String extension = "png";
				String pathFile = Constant.NOTES_UPLOAD_DIRECTORY + fileName + "." + extension;
				ImageUpload.decoder(base64Image, pathFile);
				tempStudentNotes.setPagePath(fileName.replace(" ", "") + "." + extension);
				//tempStudentNotes.setNotesTitle(studentNotes.getNotesTitle());
				//tempStudentNotes.setNotesDescription(studentNotes.getNotesDescription());
				tempStudentNotes.setPageCreateDate(timestamp);
				StudentNotesId newStudentNotesId = new StudentNotesId(notesId, pageNo, studentId);
				tempStudentNotes.setInsertedTime(timestamp);
				tempStudentNotes.setUpdatedTime(timestamp);
				tempStudentNotes.setStatus('1');
				studentNotesService.deleteStudentNotes(newStudentNotesId);
				tempStudentNotes.setStudentNotesId(newStudentNotesId);
				studentNotesService.save(tempStudentNotes);
			}
			message.setMessage("Inserted successfully");
			message.setCode("200");
		}
		return message;
	}

	@SuppressWarnings("null")
	@GetMapping(value = "/getStudentNotesById/{id}/{pno}/{sid}", headers = "Accept=application/json")
	public @ResponseBody StudentNotes getStudentNotesById(@PathVariable("id") String Id, @PathVariable("pno") int PNo,
			@PathVariable("sid") String SId, @RequestHeader String token) {
		StudentNotes studentNotes = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			studentNotes.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			StudentNotesId studentNotesId = new StudentNotesId(Id, PNo, SId);
			studentNotes = studentNotesService.getStudentNotesById(studentNotesId);
		}
		return studentNotes;
	}

	@GetMapping(value = "/getTotalStudentNotes", headers = "Accept=application/json")
	public @ResponseBody int getTotalStudentNotes(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		int i = 0;
		
		if (tokenService.tokenValidate(token)) {			
			logger.info("Invalid token");
		} else {
			logger.info("list of StudentNotes");
			i = studentNotesService.totalStudentNotes();
		}
		return i;
	}

	@GetMapping(value = "/getAllStudentNotes/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody StudentNotesDetails getAllNotesMaster(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		StudentNotesDetails studentNotesDetails = new StudentNotesDetails();
		
		if (tokenService.tokenValidate(token)) {
			studentNotesDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			studentNotesDetails = studentNotesService.getAllStudentNotes(id, searchdata);
		}
		return studentNotesDetails;
	}

	@PostMapping(value = "/GetAllNotes", headers = "Accept=application/json")
	public @ResponseBody StudentNotesDetailsDto getAllNotesDetails(@RequestBody Student student,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> studentNotesSubjectList = null;
		List<Object[]> studentNotesUnitTopicList = null;
		List<Object[]> studentNotesList = null;
		StudentNotesDetailsDto studentNotesDetailsDto = new StudentNotesDetailsDto();
		List<StudentNotesSubjectDto> studentNotesSubjectDtoList = new ArrayList<>();
		StudentNotesDto studentNotesDto;
		StudentNotesUnitTopicDto studentNotesUnitTopicDto;
		StudentNotesSubjectDto studentNotesSubjectDto;
		String studentId = student.getStudentId();
		
		if (tokenService.tokenValidate(token)) {
			studentNotesDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			studentNotesSubjectList = studentNotesService.getAllStudentNotesSubjectSelect(studentId);
			for (Object[] eachStudentNotesSubject : studentNotesSubjectList) {
				List<StudentNotesUnitTopicDto> studentNotesUnitTopicDtoList = new ArrayList<>();
				studentNotesSubjectDto = new StudentNotesSubjectDto();
				String subjectId = eachStudentNotesSubject[0].toString();
				studentNotesSubjectDto.setSubjectId(subjectId);
				studentNotesSubjectDto.setSubjectName(eachStudentNotesSubject[1].toString());
				studentNotesUnitTopicList = studentNotesService.getAllStudentNotesUnitTopicSelect(subjectId);
				for (Object[] eachStudentNotesTopicUnit : studentNotesUnitTopicList) {
					List<StudentNotesDto> studentNotesDtoList = new ArrayList<>();
					studentNotesUnitTopicDto = new StudentNotesUnitTopicDto();
					String notesId = eachStudentNotesTopicUnit[0].toString();
					studentNotesUnitTopicDto.setCategoryThumbinal(eachStudentNotesTopicUnit[1].toString());
					studentNotesUnitTopicDto.setPageTypeName(eachStudentNotesTopicUnit[2].toString());
					studentNotesUnitTopicDto.setSubjectNotesCategoryId(notesId);
					studentNotesUnitTopicDto.setTeacherId(eachStudentNotesTopicUnit[3].toString());
					studentNotesUnitTopicDto.setTopicName(eachStudentNotesTopicUnit[4].toString());
					studentNotesUnitTopicDto.setUnitName(eachStudentNotesTopicUnit[5].toString());
					studentNotesList = studentNotesService.getAllStudentNotesSelect(notesId, studentId);
					for (Object[] eachStudentNotes : studentNotesList) {
						studentNotesDto = new StudentNotesDto();
						studentNotesDto.setNotesImageUrl(eachStudentNotes[0].toString());
						studentNotesDto.setPageNumber(eachStudentNotes[1].toString());
						studentNotesDtoList.add(studentNotesDto);
					}
					studentNotesUnitTopicDto.setStudentNotesList(studentNotesDtoList);
					studentNotesUnitTopicDtoList.add(studentNotesUnitTopicDto);
				}
				studentNotesSubjectDto.setStudentNotesUnitTopicList(studentNotesUnitTopicDtoList);
				studentNotesSubjectDtoList.add(studentNotesSubjectDto);
			}
			studentNotesDetailsDto.setStudentNotesSubjectList(studentNotesSubjectDtoList);
			studentNotesDetailsDto.setCount(studentNotesSubjectDtoList.size());
			studentNotesDetailsDto.setMessage("List of Notes");
		}
		return studentNotesDetailsDto;
	}
	@PostMapping(value = "/GetAllStudentNotes", headers = "Accept=application/json")
	public @ResponseBody NotesDetailsDto GetAllStudentNotes(
			@RequestBody NotesStudentId notesStudentId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> notesList = null;
		NotesDetailsDto notesDetailsDto = new NotesDetailsDto();
		List<StudentNotesDto> notesDtoList = new ArrayList<>();
		StudentNotesDto notesDto;
		String studentId = notesStudentId.getStudentId();
		String notesId = notesStudentId.getNotesId();
		
		if (tokenService.tokenValidate(token)) {
			notesDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			notesList = studentNotesService.getAllStudentNotesSelect(notesId, studentId);
			for (Object[] eachNotes : notesList) {
				notesDto = new StudentNotesDto();
				notesDto.setNotesImageUrl(eachNotes[0].toString());
				notesDto.setPageNumber(eachNotes[1].toString());
				notesDtoList.add(notesDto);
			}
			notesDetailsDto.setStudentNotesDtoList(notesDtoList);
			notesDetailsDto.setCount(notesDtoList.size());
			notesDetailsDto.setMessage("List of Student notess");

		}
		return notesDetailsDto;
	}


}
