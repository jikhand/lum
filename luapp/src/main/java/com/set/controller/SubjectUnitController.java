package com.set.controller;

import java.util.ArrayList;
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

import com.set.dto.TopicDto;
import com.set.dto.UnitDto;
import com.set.dto.UnitSubjectClassDto;
import com.set.dto.UnitSubjectClassTopicDto;
import com.set.dto.UnitSubjectDetailsDto;
import com.set.dto.UnitSubjectDto;
import com.set.dto.UnitSubjectTopicDetailsDto;
import com.set.dto.UnitSubjectTopicDto;
import com.set.dto.UnitTopicDto;
import com.set.model.LuMessage;
import com.set.model.SubjectUnit;
import com.set.model.SubjectUnitDetails;
import com.set.model.Teacher;
import com.set.service.SubjectUnitService;
import com.set.service.TokenService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class SubjectUnitController {
	@Autowired
	private SubjectUnitService subjectUnitService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("SubjectUnitController.class");

	@PostMapping(value = "/InsertUnit", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addSubjectUnit(@RequestBody SubjectUnit subjectUnit, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		SubjectUnit tempSubjectUnit = new SubjectUnit();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String unitsId = subjectUnit.getUnitId();
			String unitsName = subjectUnit.getUnitName();
			if ((unitsId.isEmpty()) || (unitsId.equalsIgnoreCase("null"))) {
				tempSubjectUnit.setUnitId(CommonUtils.generatePrimaryKeyId("lutbl_units"));
				tempSubjectUnit.setUnitName(unitsName);
				boolean isExistSubjectUnit = subjectUnitService.IsExist(unitsName);
				if (isExistSubjectUnit == true) {
					message.setMessage("UnitName already Exist");
				} else {
					subjectUnitService.save(tempSubjectUnit);
					message.setMessage("Inserted successfully");
				}
			} else {
				tempSubjectUnit.setUnitId(unitsId);
				tempSubjectUnit.setUnitName(subjectUnit.getUnitName());
				subjectUnitService.updateSubjectUnit(subjectUnit);
				message.setMessage("Updated successfully");
			}
		}
		return message;
	}

	@PutMapping(value = "/editSubjectUnit/{id}", headers = "Accept=Application/json")
	public @ResponseBody SubjectUnit editSubjectUnit(@PathVariable("id") String Id,
			@RequestBody SubjectUnit subjectUnit, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			subjectUnit.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			subjectUnit.setUnitId(Id);
			subjectUnitService.updateSubjectUnit(subjectUnit);
			subjectUnit.setMessage("Updated successfully");
		}
		return subjectUnit;
	}

	// @PutMapping(value="/deleteSubjectUnit/{id}",headers="Accept=Application/json")
	// public @ResponseBody SubjectUnit deleteSubjectUnit(@PathVariable("id") String
	// Id, @RequestBody SubjectUnit subjectUnit,@RequestHeader String token) {
	// Claims claims=TokenUtils.verifyToken(token);
	// if(claims==null) {
	// subjectUnit.setMessage("Invalid token");
	// }else {
	//// SubjectUnitId newSubjectUnit=new SubjectUnitId("1");
	//// subjectUnit.setSubjectUnitId(newSubjectUnit);
	// subjectUnitService.deleteSubjectUnit(subjectUnit);
	// subjectUnit.setMessage("Deleted successfully");
	// }
	// return subjectUnit;
	// }

	@SuppressWarnings("null")
	@GetMapping(value = "/getSubjectUnitById/{id}", headers = "Accept=application/json")
	public @ResponseBody SubjectUnit getSubjectUnitById(@PathVariable("id") String Id, @RequestHeader String token) {
		SubjectUnit subjectUnit = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (claims == null) {
			subjectUnit.setMessage("Invalid token");
		} else {
			subjectUnit = subjectUnitService.getSubjectUnitById(Id);
		}
		return subjectUnit;
	}

	@GetMapping(value = "/getTotalSubjectUnit", headers = "Accept=application/json")
	public @ResponseBody int getTotalSubjectUnit(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		int i = 0;
		
		if (tokenService.tokenValidate(token)) {			
			logger.info("Invalid token");
		} else {
			logger.info("list of SubjectUnit");
			i = subjectUnitService.totalSubjectUnit();
		}
		return i;
	}
	@GetMapping(value = "/getAllSubjectUnit", headers = "Accept=application/json")
	public @ResponseBody List<SubjectUnit> getAllSubjectUnitSelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		SubjectUnitDetails subjectUnitDetails = new SubjectUnitDetails();
		List<SubjectUnit> list=new ArrayList<SubjectUnit>();
		if (tokenService.tokenValidate(token)) {
			subjectUnitDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			list = subjectUnitService.getAllSubjectUnit();
		}
		return list;
	}
	@GetMapping(value = "/getAllSubjectUnit/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody SubjectUnitDetails getAllSubjectUnit(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		SubjectUnitDetails subjectUnitDetails = new SubjectUnitDetails();
		
		if (tokenService.tokenValidate(token)) {
			subjectUnitDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			subjectUnitDetails = subjectUnitService.getAllSubjectUnit(id, searchdata);
		}
		return subjectUnitDetails;
	}

	@PostMapping(value = "/SubjectUnitDetailsTeacher", headers = "Accept=application/json")
	public @ResponseBody UnitSubjectDetailsDto getAllTeacherUnitSubjectDetails(@RequestBody Teacher teacher,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> unitSubjectClassList = null;
		List<Object[]> unitSubjectList = null;
		List<Object[]> unitList = null;
		UnitSubjectDetailsDto unitSubjectDetailsDto = new UnitSubjectDetailsDto();
		List<UnitSubjectClassDto> unitSubjectClassDtoList = new ArrayList<>();
		UnitDto unitDto;
		UnitSubjectDto unitSubjectDto;
		UnitSubjectClassDto unitSubjectClassDto;
		String teacherId = teacher.getTeacherId();
		
		if (tokenService.tokenValidate(token)) {
			unitSubjectDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			unitSubjectClassList = subjectUnitService.getAllUnitSubjectClassSelect(teacherId);
			for (Object[] eachUnitSubjectClass : unitSubjectClassList) {
				List<UnitSubjectDto> unitSubjectDtoList = new ArrayList<>();
				unitSubjectClassDto = new UnitSubjectClassDto();
				String classId = eachUnitSubjectClass[0].toString();
				String sectionId = eachUnitSubjectClass[1].toString();
				unitSubjectClassDto.setClassId(classId);
				unitSubjectClassDto.setSectionId(sectionId);
				unitSubjectClassDto.setClassName(eachUnitSubjectClass[2].toString());
				unitSubjectClassDto.setSectionName(eachUnitSubjectClass[3].toString());
				unitSubjectList = subjectUnitService.getAllUnitSubjectSelect(classId, sectionId, teacherId);
				for (Object[] eachUnitSubject : unitSubjectList) {
					List<UnitDto> unitDtoList = new ArrayList<>();
					unitSubjectDto = new UnitSubjectDto();
					String subjectId = eachUnitSubject[0].toString();
					unitSubjectDto.setSubjectId(subjectId);
					unitSubjectDto.setSubjectName(eachUnitSubject[1].toString());
					unitList = subjectUnitService.getAllUnitSelect(subjectId);
					for (Object[] eachUnit : unitList) {
						unitDto = new UnitDto();
						unitDto.setSubjectUnitId(eachUnit[0].toString());
						unitDto.setUnitName(eachUnit[1].toString());
						unitDtoList.add(unitDto);
					}
					unitSubjectDto.setUnitList(unitDtoList);
					unitSubjectDtoList.add(unitSubjectDto);
				}
				unitSubjectClassDto.setUnitSubjectList(unitSubjectDtoList);
				unitSubjectClassDtoList.add(unitSubjectClassDto);
			}
			unitSubjectDetailsDto.setUnitSubjectClassDtoList(unitSubjectClassDtoList);
			unitSubjectDetailsDto.setCount(unitSubjectClassDtoList.size());
			unitSubjectDetailsDto.setMessage("List of Subject Unit Details");
		}
		return unitSubjectDetailsDto;
	}

	@PostMapping(value = "/NotesCategoryInsertDetails", headers = "Accept=application/json")
	public @ResponseBody UnitSubjectTopicDetailsDto getAllTeacherUnitTopicSubjectDetails(@RequestBody Teacher teacher,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> unitSubjectClassList = null;
		List<Object[]> unitSubjectList = null;
		List<Object[]> unitList = null;
		List<Object[]> topicList = null;
		UnitSubjectTopicDetailsDto unitSubjectDetailsDto = new UnitSubjectTopicDetailsDto();
		List<UnitSubjectClassTopicDto> unitSubjectClassDtoList = new ArrayList<>();
		TopicDto topicDto;
		UnitTopicDto unitTopicDto;
		UnitSubjectTopicDto unitSubjectTopicDto;
		UnitSubjectClassTopicDto unitSubjectClassDto;
		String teacherId = teacher.getTeacherId();
		
		if (tokenService.tokenValidate(token)) {
			unitSubjectDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			unitSubjectClassList = subjectUnitService.getAllUnitSubjectClassSelect(teacherId);
			for (Object[] eachUnitSubjectClass : unitSubjectClassList) {
				List<UnitSubjectTopicDto> unitSubjectDtoList = new ArrayList<>();
				unitSubjectClassDto = new UnitSubjectClassTopicDto();
				String classId = eachUnitSubjectClass[1].toString();
				String sectionId = eachUnitSubjectClass[0].toString();
				unitSubjectClassDto.setClassId(classId);
				unitSubjectClassDto.setSectionId(sectionId);
				unitSubjectClassDto.setClassName(eachUnitSubjectClass[2].toString());
				unitSubjectClassDto.setSectionName(eachUnitSubjectClass[3].toString());
				unitSubjectList = subjectUnitService.getAllUnitSubjectSelect(classId, sectionId, teacherId);
				for (Object[] eachUnitSubject : unitSubjectList) {
					List<UnitTopicDto> unitDtoList = new ArrayList<>();
					unitSubjectTopicDto = new UnitSubjectTopicDto();
					String subjectId = eachUnitSubject[0].toString();
					unitSubjectTopicDto.setSubjectId(subjectId);
					unitSubjectTopicDto.setSubjectName(eachUnitSubject[1].toString());
					unitList = subjectUnitService.getAllUnitSelect(subjectId);
					for (Object[] eachUnit : unitList) {
						List<TopicDto> topicDtoList = new ArrayList<>();
						unitTopicDto = new UnitTopicDto();
						String unitId = eachUnit[0].toString();
						unitTopicDto.setUnitId(eachUnit[0].toString());
						unitTopicDto.setUnitName(eachUnit[1].toString());
						topicList = subjectUnitService.getAllTopicSelect(subjectId, unitId);
						for (Object[] eachTopic : topicList) {
							topicDto = new TopicDto();
							topicDto.setTopicId(eachTopic[0].toString());
							topicDto.setTopicName(eachTopic[1].toString());
							topicDtoList.add(topicDto);
						}
						unitTopicDto.setTopicList(topicDtoList);
						unitDtoList.add(unitTopicDto);
					}
					unitSubjectTopicDto.setUnitTopicList(unitDtoList);
					unitSubjectDtoList.add(unitSubjectTopicDto);
				}
				unitSubjectClassDto.setUnitSubjectTopicList(unitSubjectDtoList);
				unitSubjectClassDtoList.add(unitSubjectClassDto);
			}
			unitSubjectDetailsDto.setUnitSubjectClassTopicDtoList(unitSubjectClassDtoList);
			unitSubjectDetailsDto.setCount(unitSubjectClassDtoList.size());
			unitSubjectDetailsDto.setMessage("List of Subject Unit Topic Details");
		}
		return unitSubjectDetailsDto;
	}
}
