package com.set.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.controller.helper.ClassSectionControllerHelper;
import com.set.dto.ClassSectionMasterDetailsDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterDetails;
import com.set.model.ClassSectionMasterId;
import com.set.model.SchoolClass;
import com.set.service.ClassSectionMasterService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class ClassSectionMasterController {
	@Autowired
	private ClassSectionMasterService classSectionMasterService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClassSectionControllerHelper classSectionControllerHelper;
	private Logger logger = Logger.getLogger("ClassSectionMasterController.class");

	/*
	 * @description addClassSubject with details
	 * 
	 * @param @token,ClassSubject
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 */
	@PostMapping(value = "/addClassSectionMaster", headers = "Accept=Application/json")
	public @ResponseBody ClassSectionMaster addClassSectionMaster(@RequestBody ClassSectionMaster classSectionMaster,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			classSectionMaster.setMessage("Invalid token");
		} else {
			String[] arrOfStr = classSectionMaster.getClassName().split(",");
			String classId = arrOfStr[0];
			String className = arrOfStr[1];
			classSectionMaster.setClassName(className);
			ClassSectionMasterId SectionMaster = new ClassSectionMasterId(classId,
					CommonUtils.generatePrimaryKeyId("lutbl_sec_master"));
			classSectionMaster.setClassSectionMasterId(SectionMaster);
			classSectionMasterService.save(classSectionMaster);
			classSectionMaster.setMessage("Insert successfully");
		}
		return classSectionMaster;
	}

	@PutMapping(value = "/addClassSectionMaster/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody ClassSectionMaster addClassSectionMaster(@PathVariable("classId") String classId,
			@PathVariable("sectionId") String sectionId, @RequestBody ClassSectionMaster classSectionMaster,
			@RequestHeader String token) {
		if (tokenService.tokenValidate(token)) {
			System.out.println("in Invalide");
			classSectionMaster.setMessage("Invalid token");
			return classSectionMaster;
		} else {
//			String[] arrOfStr = classSectionMaster.getClassName().split(",");
//			String className = arrOfStr[1];
//			classSectionMaster.setClassName(className);
			ClassSectionMaster newclassSectionMaster=new ClassSectionMaster();
			newclassSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
			classSectionMaster.setClassName(newclassSectionMaster.getClassName());
			ClassSectionMasterId SectionMaster = new ClassSectionMasterId(classId, sectionId);
			classSectionMaster.setClassSectionMasterId(SectionMaster);
			classSectionMasterService.update(classSectionMaster);
			classSectionMaster.setMessage("update successfully");
			return classSectionMaster;
		}
	}

	/*
	 * 
	 * @description Update ClassSubject by id
	 * 
	 * @param @Id, @token,classSubject object
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 *
	 */
	@PutMapping(value = "/updateClassSectionMaster/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody ClassSectionMaster updateClassSectionMaster(@PathVariable("classId") String classId,
			@PathVariable("sectionId") String sectionId, @RequestBody ClassSectionMaster classSectionMaster,
			@RequestHeader String token) {
		if (tokenService.tokenValidate(token)) {
			classSectionMaster.setMessage("Invalid token");
			return classSectionMaster;
		} else {
			ClassSectionMasterId SectionMaster = new ClassSectionMasterId(classId, sectionId);
			classSectionMaster.setClassSectionMasterId(SectionMaster);
			classSectionMasterService.update(classSectionMaster);
			classSectionMaster.setMessage("update successfully");
			return classSectionMaster;
		}
	}

	/*
	 * @description ClassSubjectby details based on id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubjectbyId
	 * 
	 * @ Author Parnay
	 *
	 */
	@GetMapping(value = "/getClassSectionMasterbyId/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody ClassSectionMaster getClassSectionMaster(@PathVariable("classId") String classId,
			@PathVariable("sectionId") String sectionId, @RequestHeader String token) {
		// byte[] decodedBytes = Base64.getDecoder().decode(id);
		// String decodedString = new String(decodedBytes);
		ClassSectionMaster classSectionMaster = new ClassSectionMaster();
		if (tokenService.tokenValidate(token)) {
			classSectionMaster.setMessage("Invalid token");
		} else {
			ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(classId, sectionId);
			classSectionMaster.setClassSectionMasterId(classSectionMasterId);
			classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
			classSectionMaster.setMessage("class Section Master details");
		}
		return classSectionMaster;
	}

	/*
	 * @description List of ClassSubject
	 * 
	 * @param @token
	 * 
	 * @return List of ClassSubject
	 * 
	 * @ Author Parnay
	 *
	 */
	@GetMapping(value = "/listOfAllClassSectionMaster/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody ClassSectionMasterDetailsDto getClassSectionMasterList(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
	ClassSectionMasterDetailsDto classSectionMasterDetailsDto = new ClassSectionMasterDetailsDto();
	 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
	 String decodedString = new String(decodedBytes);
	ClassSectionMasterDetails classSectionMasterDetails = new ClassSectionMasterDetails();
	if (tokenService.tokenValidate(token)) {
	classSectionMasterDetailsDto.setMessage("Invalid Token");
	} else {
	classSectionMasterDetails = classSectionMasterService.list(id,decodedString);
	classSectionMasterDetailsDto = classSectionControllerHelper
	.setClassSectionDetailsDto(classSectionMasterDetails);
	}
	return classSectionMasterDetailsDto;
	}

	@GetMapping(value = "/GetlistOfAllClassSectionMaster", headers = "Accept=application/json")
	public @ResponseBody ClassSectionMasterDetails getListClassSectionMaster(@RequestHeader String token) {
		// ClassSubjectDetails classSubjectDetails = new ClassSubjectDetails();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		ClassSectionMasterDetails classSectionMasterDetails = new ClassSectionMasterDetails();
		if (tokenService.tokenValidate(token)) {
			classSectionMasterDetails.setMessage("Invalid Token");
		} else {
			classSectionMasterDetails = classSectionMasterService.list(1, "6");
		}
		return classSectionMasterDetails;
	}

	/*
	 * @description Delete by Id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubject
	 *
	 */
	@RequestMapping(value = "/deleteClassSectionMaster/{classId}/{sectionId}", method = RequestMethod.PUT)
	public @ResponseBody ClassSectionMaster deleteClassSectionMasterById(@PathVariable("classId") String classId,
			@PathVariable("sectionId") String sectionId, @RequestHeader String token) {
		ClassSectionMaster classSectionMaster = new ClassSectionMaster();
		if (tokenService.tokenValidate(token)) {
			classSectionMaster.setMessage("Invalid Token");
		} else {
			ClassSectionMasterId newClassSectionMasterId = new ClassSectionMasterId(classId, sectionId);
			classSectionMaster.setClassSectionMasterId(newClassSectionMasterId);
			classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
			// classSectionMaster.set//IsSoftDelete(true);
			classSectionMasterService.update(classSectionMaster);
			classSectionMaster.setMessage("Deleted successfully");
		}
		return classSectionMaster;
	}

	@RequestMapping(value = "/getAllClassSectionMasterSelect", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<ClassSectionMaster> getAllClassSectionMasterSelect(@RequestHeader String token)
			throws IOException {
		Claims claims = TokenUtils.verifyToken(token);
		List<ClassSectionMaster> classSectionMaster = new ArrayList<>();
		if (!tokenService.tokenValidate(token)) {
			classSectionMaster = classSectionMasterService.getAllClassSectionMasterSelect();
		} else {
			ClassSectionMaster csm = new ClassSectionMaster();
			csm.setMessage("Invalid token");
			classSectionMaster.add(csm);
			logger.info("Invalid token");
		}
		return classSectionMaster;
	}

	@RequestMapping(value = "/getSchoolClassSelect", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<SchoolClass> getSchoolClass(@RequestHeader String token) throws IOException {
		Claims claims = TokenUtils.verifyToken(token);
		List<SchoolClass> nSchoolClass = new ArrayList<>();
		if (!tokenService.tokenValidate(token)) {
			nSchoolClass = classSectionMasterService.getSchoolClass();
		} else {
			SchoolClass csm = new SchoolClass();
			nSchoolClass.add(csm);
			logger.info("Invalid token");
		}
		return nSchoolClass;
	}

}
