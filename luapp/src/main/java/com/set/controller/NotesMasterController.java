package com.set.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.set.dto.NotesCategoryDto;
import com.set.dto.NotesMasterTopicListDto;
import com.set.dto.NotesSubjectDetailsDto;
import com.set.dto.NotesSubjectDto;
import com.set.dto.NotesUnitDto;
import com.set.model.ClassSection;
import com.set.model.ClassSectionMaster;
import com.set.model.LuMessage;
import com.set.model.NotesMaster;
import com.set.model.NotesMasterDetails;
import com.set.model.NotificationDetails;
import com.set.model.SubjectUnitMaster;
import com.set.model.SubjectUnitMasterDetails;
import com.set.service.NotesMasterService;
import com.set.service.TokenService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class NotesMasterController {
	@Autowired
	private NotesMasterService notesMasterService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("NotesMasterController.class");

	@PostMapping(value = "/InsertNotesCategory", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addNotesMaster(@RequestBody NotesMaster notesMaster, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		NotesMaster tempNotesMaster = new NotesMaster();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String base64Image = notesMaster.getCoverPhoto();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String fileName = "coverPageImage" + System.currentTimeMillis();
			String extension = "png";
			String pathFile = Constant.NOTES_UPLOAD_COVERPAGE_DIRECTORY + fileName + "." + extension;
            ImageUpload.decoder(base64Image, pathFile);
			String noteId = notesMaster.getNotesId();
			tempNotesMaster.setCoverPhoto(fileName + "." + extension);
			String subjectId = notesMaster.getSubjectId();
			String teacherId = notesMaster.getTeacherId();
			String unitId = notesMaster.getUnitId();
			String topicId = notesMaster.getTopicId();
			tempNotesMaster.setSubjectId(subjectId);
			tempNotesMaster.setTeacherId(teacherId);
			tempNotesMaster.setUnitId(unitId);
			tempNotesMaster.setTopicId(topicId);
			tempNotesMaster.setPageType(notesMaster.getPageType());
			if ((noteId.isEmpty()) || (noteId.equalsIgnoreCase("null"))) {
				String notesId = CommonUtils.generatePrimaryKeyId("lutbl_category_notes");
				tempNotesMaster.setNotesId(notesId);
				boolean isExistSubjectUnit = notesMasterService.IsExist(subjectId, teacherId, unitId, topicId);
				if (isExistSubjectUnit == true) {
					message.setMessage("Notes Category is already Exist");
				} else {
					notesMasterService.save(tempNotesMaster);
					message.setMessage("Inserted successfully");
				}
			} else {
				tempNotesMaster.setNotesId(noteId);
				notesMasterService.updateNotesMaster(tempNotesMaster);
				message.setMessage("Updated successfully");
			}
		}
		return message;
	}

	@PostMapping(value = "/addNotesCategory", headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody LuMessage addNotesCategory(
			@RequestPart(value = "NotesMaster", required = false) NotesMaster notesMaster,
			@RequestPart(value = "coverImagee", required = false) MultipartFile file, @RequestHeader String token)
			throws IOException {
		String filepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		NotesMaster tempNotesMaster = new NotesMaster();
		if (claims == null) {
			message.setMessage("Invalid token");
		} else {
//			if (file != null && !file.isEmpty()) {
//				StringJoiner sj = new StringJoiner(" , ");
//				byte[] bytes = file.getBytes();
//				filepath = Constant.NOTES_UPLOAD_DIRECTORY + file.getOriginalFilename();
//				Path path = Paths.get(filepath);
//				Files.write(path, bytes);
//				sj.add(file.getOriginalFilename());
//				tempNotesMaster.setCoverPhoto(filepath);// setNotesCoverpage(file);
//			}
			if (file != null && !file.isEmpty()) {
				long currentTime=System.currentTimeMillis();
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = file.getBytes();
				filepath = Constant.NOTES_UPLOAD_DIRECTORY + currentTime+file.getOriginalFilename();
				Path path = Paths.get(filepath);
				Files.write(path, bytes);
				sj.add(file.getOriginalFilename());
				tempNotesMaster.setCoverPhoto(currentTime+file.getOriginalFilename());// setResourcePath(file);
			}
			
			String subjectId = notesMaster.getSubjectId();
			String teacherId = notesMaster.getTeacherId();
			String unitId = notesMaster.getUnitId();
			String topicId = notesMaster.getTopicId();
			tempNotesMaster.setSubjectId(subjectId);
			tempNotesMaster.setTeacherId(teacherId);
			tempNotesMaster.setUnitId(unitId);
			tempNotesMaster.setTopicId(topicId);
			tempNotesMaster.setPageType(notesMaster.getPageType());
			String notesId = CommonUtils.generatePrimaryKeyId("lutbl_category_notes");
			tempNotesMaster.setNotesId(notesId);
			notesMasterService.save(tempNotesMaster);
			message.setMessage("Inserted successfully");
		}
		return message;
	}
	@PutMapping(value = "/addNotesCategory", headers = { "content-type=multipart/mixed",
	"content-type=multipart/form-data" })
public @ResponseBody NotesMaster updateNotesCategory(
	@RequestPart(value = "NotesMaster", required = false) NotesMaster notesMaster,
	@RequestPart(value = "coverImagee", required = false) MultipartFile file, @RequestHeader String token)
	throws IOException {
		String filepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		NotesMaster tempNotesMaster = new NotesMaster();
		if (claims == null) {
			tempNotesMaster.setMessage("Invalid token");
		} else {
//			if (file != null && !file.isEmpty()) {
//				StringJoiner sj = new StringJoiner(" , ");
//				byte[] bytes = file.getBytes();
//				filepath = Constant.NOTES_UPLOAD_DIRECTORY + file.getOriginalFilename();
//				Path path = Paths.get(filepath);
//				Files.write(path, bytes);
//				sj.add(file.getOriginalFilename());
//				tempNotesMaster.setCoverPhoto(filepath);// setNotesCoverpage(file);
//			}
				if (file != null && !file.isEmpty()) {
					long currentTime=System.currentTimeMillis();
					StringJoiner sj = new StringJoiner(" , ");
					byte[] bytes = file.getBytes();
					filepath = Constant.NOTES_UPLOAD_DIRECTORY + currentTime+file.getOriginalFilename();
					Path path = Paths.get(filepath);
					Files.write(path, bytes);
					sj.add(file.getOriginalFilename());
					tempNotesMaster.setCoverPhoto(currentTime+file.getOriginalFilename());// setResourcePath(file);
				}
			String subjectId = notesMaster.getSubjectId();
			String teacherId = notesMaster.getTeacherId();
			String unitId = notesMaster.getUnitId();
			String topicId = notesMaster.getTopicId();
			tempNotesMaster.setSubjectId(subjectId);
			tempNotesMaster.setTeacherId(teacherId);
			tempNotesMaster.setUnitId(unitId);
			tempNotesMaster.setTopicId(topicId);
			tempNotesMaster.setPageType(notesMaster.getPageType());
			tempNotesMaster.setNotesId(notesMaster.getNotesId());
			notesMasterService.updateNotesMaster(tempNotesMaster);
			tempNotesMaster.setMessage("Updated successfully");
		}
		return tempNotesMaster;
	}
	@GetMapping(value = "/IsExistNotesCategory/{subjectId}/{teacherId}/{unitId}/{topicId}", headers = "Accept=Application/json")
	public @ResponseBody Boolean IsExist(
			@PathVariable("subjectId") String subjectId,
			@PathVariable("teacherId") String teacherId,
			@PathVariable("unitId") String unitId,
			@PathVariable("topicId") String topicId) {
		boolean isExistSubjectUnit = notesMasterService.IsExist(subjectId, teacherId, unitId, topicId);
		return isExistSubjectUnit;
	}
	@PutMapping(value = "/editNotes/{id}", headers = "Accept=Application/json")
	public @ResponseBody NotesMaster editNotesMaster(@PathVariable("id") String Id,
			@RequestBody NotesMaster notesMaster, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			notesMaster.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String base64Image = notesMaster.getCoverPhoto();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String fileName = "coverPageImage" + System.currentTimeMillis();
			String extension = "png";
			String pathFile = Constant.NOTES_UPLOAD_COVERPAGE_DIRECTORY + fileName + "." + extension;
			ImageUpload.decoder(base64Image, pathFile);
			notesMaster.setCoverPhoto(fileName + "." + extension);
			notesMaster.setNotesId(Id);
			notesMasterService.updateNotesMaster(notesMaster);
			notesMaster.setMessage("Updated successfully");
		}
		return notesMaster;
	}

	@SuppressWarnings("null")
	@GetMapping(value = "/getNotesMasterById/{id}", headers = "Accept=application/json")
	public @ResponseBody NotesMaster getNotesMasterById(@PathVariable("id") String Id, @RequestHeader String token) {
		 byte[] decodedBytes = Base64.getDecoder().decode(Id);
		String decodedString = new String(decodedBytes);
		NotesMaster notesMaster = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			notesMaster.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			notesMaster = notesMasterService.getNotesMasterById(decodedString);
		}
		return notesMaster;
	}

	@GetMapping(value = "/getTotalNotesMaster", headers = "Accept=application/json")
	public @ResponseBody int getTotalNotesMaster(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		int i = 0;
		if (tokenService.tokenValidate(token)) {			
			logger.info("Invalid token");
		} else {
			logger.info("list of NotesMaster");
			i = notesMasterService.totalNotesMaster();
		}
		return i;
	}

	@GetMapping(value = "/getAllNotes/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody List<Map<String, String>> getAllNotesMaster(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		 System.out.println("decodedString="+decodedString);
	//	Claims claims = TokenUtils.verifyToken(token);
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		
	//	if (tokenService.tokenValidate(token)) {
			Map<String, String> hm = new HashMap<String, String>();
	//		hm.put("token", "Invalid Token");
			arrayList.add(hm);
	//	} else {
	//		System.out.println("in notes details");
			System.out.println("TeacherId +TeacherId");
			System.out.println("PageType +PageType");
			arrayList = notesMasterService.getAllNotesMaster(id, decodedString);
//		}
		return arrayList;
	}

	@GetMapping(value = "/getAllNotesMasterSelect", headers = "Accept=application/json")
	public @ResponseBody List<NotesMaster> getAllNotesMasterSelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<NotesMaster> notesMaster = new ArrayList<>();
		
		if (tokenService.tokenValidate(token)) {
			NotesMaster csm = new NotesMaster();
			csm.setMessage("Invalid token");
			notesMaster.add(csm);
			logger.info("Invalid token");
		} else {
			notesMaster = notesMasterService.getAllNotesMasterSelect();
		}
		return notesMaster;
	}

	@PostMapping(value = "/NotesSubjectUnitDetails", headers = "Accept=application/json")
	public @ResponseBody NotesSubjectDetailsDto getAllNotesSubjectUnitDetails(@RequestBody ClassSection classSection,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> notesSubjectList = null;
		List<Object[]> notesUnitList = null;
		List<Object[]> notesCategoryList = null;
		NotesSubjectDetailsDto notesSubjectDetailsDto = new NotesSubjectDetailsDto();
		List<NotesSubjectDto> notesSubjectDtoList = new ArrayList<>();
		NotesCategoryDto notesCategoryDto;
		NotesUnitDto notesUnitDto;
		NotesSubjectDto notesSubjectDto;
		String classId = classSection.getClassId();
		String sectionId = classSection.getSectionId();
		
		if (tokenService.tokenValidate(token)) {
			notesSubjectDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			notesSubjectList = notesMasterService.getAllNotesSubjectSelect(classId, sectionId);
			for (Object[] eachNotesSubject : notesSubjectList) {
				List<NotesUnitDto> notesUnitDtoList = new ArrayList<>();
				notesSubjectDto = new NotesSubjectDto();
				String subjectId = eachNotesSubject[0].toString();
				notesSubjectDto.setSubjectId(subjectId);
				notesSubjectDto.setSubjectName(eachNotesSubject[1].toString());
				notesUnitList = notesMasterService.getAllNotesUnitSelect(subjectId);
				for (Object[] eachNotesUnit : notesUnitList) {
					List<NotesCategoryDto> notesCategoryDtoList = new ArrayList<>();
					notesUnitDto = new NotesUnitDto();
					String unitId = eachNotesUnit[0].toString();
					notesUnitDto.setUnitId(unitId);
					notesUnitDto.setUnitName(eachNotesUnit[1].toString());
					notesCategoryList = notesMasterService.getAllNotesCategorySelect(subjectId, unitId);
					for (Object[] eachNotesCategory : notesCategoryList) {
						notesCategoryDto = new NotesCategoryDto();
						notesCategoryDto.setCategoryThumbinal(eachNotesCategory[1].toString());
						notesCategoryDto.setNotesCategoryId(eachNotesCategory[0].toString());
						notesCategoryDto.setPageType(eachNotesCategory[2].toString());
						notesCategoryDto.setTopicName(eachNotesCategory[4].toString());
						notesCategoryDto.setUnitTopicId(eachNotesCategory[3].toString());
						notesCategoryDtoList.add(notesCategoryDto);
					}
					notesUnitDto.setNotesCategoryList(notesCategoryDtoList);
					notesUnitDtoList.add(notesUnitDto);
				}
				notesSubjectDto.setNotesUnitList(notesUnitDtoList);
				notesSubjectDtoList.add(notesSubjectDto);
			}
			notesSubjectDetailsDto.setNotesSubjectClassDtoList(notesSubjectDtoList);
			notesSubjectDetailsDto.setCount(notesSubjectDtoList.size());
			notesSubjectDetailsDto.setMessage("List of Subject Unit Details");
		} 
		return notesSubjectDetailsDto;
	}
	//Topic id and name Drop Down
	@GetMapping(value = "/GetAllTopics", headers = "Accept=application/json")
	public @ResponseBody NotesMasterTopicListDto getAllTopicSelect(@RequestHeader String token) {
		NotesMasterTopicListDto listOfTopic = new NotesMasterTopicListDto();

		Claims claims = TokenUtils.verifyToken(token);
		
		if (tokenService.tokenValidate(token)) {
			listOfTopic.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			listOfTopic = notesMasterService.getAllTopicSelect();
		}
		return listOfTopic;
	}
}
