package com.set.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

import javax.servlet.annotation.MultipartConfig;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.set.controller.helper.SubjectMasterControllerHelper;
import com.set.dto.SubjectMasterDetailsDto;
import com.set.model.SubjectMaster;
import com.set.model.SubjectMasterDetils;
import com.set.model.SubjectMasterId;
import com.set.model.User;
import com.set.service.SubjectMasterService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;
@CrossOrigin
@RestController
public class SubjectMasterController {
	@Autowired
	private SubjectMasterService subjectMasterService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private SubjectMasterControllerHelper subjectMasterControllerHelper;
	private Logger logger = Logger.getLogger("SubjectMasterController.class");

	/*
	 * @description addClassSubject with details
	 * 
	 * @param @token,ClassSubject
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 */
	
	@PostMapping(value = "/AddSubjectMaster", headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public @ResponseBody SubjectMaster addSubjectMaster(@RequestBody SubjectMaster subjectMaster,
			@RequestHeader String token) {
		if (tokenService.tokenValidate(token)) {
			subjectMaster.setMessage("Invalide token");
			logger.info("Invalid token");
		} else {
			Claims claims=TokenUtils.verifyToken(token);
			User user=userService.retrieveFromId(claims.getSubject());
			subjectMaster.setInsertedBy(user.getUserId());
			subjectMaster.setIs_soft_delete(0);
			subjectMaster.setInsertedTime(CommonUtils.getCurrentDateTime());
			SubjectMasterId SubjectMasterId = new SubjectMasterId(CommonUtils.generatePrimaryKeyId("lutbl_subject_master"),CommonUtils.generatePrimaryKeyId("lutbl_subject_master"));
			subjectMaster.setSubjectMasterId(SubjectMasterId);
			subjectMasterService.save(subjectMaster);
			subjectMaster.setMessage("Insert successfully");
		}
		return subjectMaster;
	}
	@PostMapping(value = "/addSubjectMaster", headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public @ResponseBody SubjectMaster addSubjectMaster(
			@RequestPart( value = "subjectMaster", required = false ) SubjectMaster subjectMaster
			,@RequestPart ( value = "subjectCoverpage", required = false ) MultipartFile file
			,@RequestHeader String token)throws IOException {
			String filepath="";
		if (tokenService.tokenValidate(token)) {
			subjectMaster.setMessage("Invalide token");
			logger.info("Invalid token");
		} else {
			if ( file != null && !file.isEmpty () ){
		    	 StringJoiner sj = new StringJoiner(" , ");
		    	 byte[] bytes = file.getBytes();
		    	 filepath=Constant.BOOK_UPLOAD_DIRECTORY+file.getOriginalFilename();
		    	 Path path = Paths.get(file.getOriginalFilename());
		         Files.write(path, bytes);
		         sj.add(file.getOriginalFilename());
		         subjectMaster.setSubjectCoverpage(filepath);//setSubjectCoverpage(file);
		     	}
			Claims claims=TokenUtils.verifyToken(token);
			User user=userService.retrieveFromId(claims.getSubject());
			subjectMaster.setInsertedBy(user.getUserId());
			subjectMaster.setIs_soft_delete(0);
			subjectMaster.setInsertedTime(CommonUtils.getCurrentDateTime());
			SubjectMasterId SubjectMasterId = new SubjectMasterId(CommonUtils.generatePrimaryKeyId("lutbl_subject_master"),CommonUtils.generatePrimaryKeyId("lutbl_subject_master"));
			subjectMaster.setSubjectMasterId(SubjectMasterId);
			subjectMasterService.save(subjectMaster);
			subjectMaster.setMessage("Insert successfully");
		}
		return subjectMaster;
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
//	@PutMapping(value = "/addSubjectMaster/{SubjectId}/{textBookISBN}", headers = "Accept=application/json")
//	public @ResponseBody SubjectMaster updateClassSubject(
//			@PathVariable("SubjectId") String SubjectId,
//			@PathVariable("textBookISBN") String textBookISBN,
//			@RequestBody SubjectMaster subjectMaster, @RequestHeader String token) {
//		if (tokenService.tokenValidate(token)) {
//			subjectMaster.setMessage("Invalide token");
//			return subjectMaster;
//		} else {
//			Claims claims=TokenUtils.verifyToken(token);
//			User user=userService.retrieveFromId(claims.getSubject());
//			SubjectMasterId newsubjectMasterid = new SubjectMasterId(SubjectId,textBookISBN);
//			subjectMaster.setSubjectMasterId(newsubjectMasterid);
//			subjectMaster=subjectMasterService.getSubjectMasterById(subjectMaster);
//			subjectMaster.setUpdatedBy(user.getUserId());
//			subjectMaster.setUpdatedTime(CommonUtils.getCurrentDateTime());
//			subjectMasterService.updateSubjectMaster(subjectMaster);//update(subjectMaster);
//			subjectMaster.setMessage("update successfully");
//			return subjectMaster;
//		}
//	}
	@PutMapping(value = "/addSubjectMaster/{SubjectId}/{textBookISBN}", headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public @ResponseBody SubjectMaster updateSubjectMaster(
			@PathVariable("SubjectId") String SubjectId,
 			@PathVariable("textBookISBN") String textBookISBN,
			@RequestPart( value = "subjectMaster", required = false ) SubjectMaster subjectMaster
			,@RequestPart ( value = "subjectCoverpage", required = false ) MultipartFile file
			,@RequestHeader String token)throws IOException {
			String filepath="";   
			System.out.println("subjectMaster.getSubjectName="+subjectMaster.getSubjectName());
		if (tokenService.tokenValidate(token)) {
			subjectMaster.setMessage("Invalide token");
			logger.info("Invalid token");
		} else {
			Claims claims=TokenUtils.verifyToken(token);
			User user=userService.retrieveFromId(claims.getSubject());
			System.out.println("decodedStringSubjectId="+SubjectId);
			System.out.println("decodedStringTextBookISBN="+textBookISBN);
			SubjectMasterId newsubjectMasterid = new SubjectMasterId(SubjectId,textBookISBN);
			if ( file != null && !file.isEmpty () ){
		    	 StringJoiner sj = new StringJoiner(" , ");
		    	 byte[] bytes = file.getBytes();
		    	 filepath=Constant.UPLOADED_FOLDER+file.getOriginalFilename();
		    	 System.out.println("filepath="+filepath);
		    	 Path path = Paths.get(filepath);
		         Files.write(path, bytes);
		         sj.add(file.getOriginalFilename());
		         subjectMaster.setSubjectCoverpage(file.getOriginalFilename());//setSubjectCoverpage(file);
		     	}
			subjectMaster.setSubjectMasterId(newsubjectMasterid);
			SubjectMaster newsubjectMaster=subjectMasterService.getSubjectMasterById(subjectMaster);
			if ( filepath != null && !filepath.isEmpty () ) {
				subjectMaster.setSubjectCoverpage(filepath);	
			}
			subjectMaster.setInsertedBy(newsubjectMaster.getInsertedBy());
			subjectMaster.setInsertedTime(newsubjectMaster.getInsertedTime());
			subjectMaster.setUpdatedBy(user.getUserId());
			subjectMaster.setUpdatedTime(CommonUtils.getCurrentDateTime());
			subjectMasterService.updateSubjectMaster(subjectMaster);//update(subjectMaster);
			subjectMaster.setMessage("update successfully");
			return subjectMaster;
		}
		return subjectMaster;
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
	@GetMapping(value = "/getSubjectMasterbyId/{SubjectId}/{textBookISBN}", headers = "Accept=application/json")
	public @ResponseBody SubjectMaster getSubjectMaster(
			@PathVariable("SubjectId") String SubjectId,
			@PathVariable("textBookISBN") String textBookISBN,
			@RequestHeader String token) {
//		byte[] decodedBytes = Base64.getDecoder().decode(id);
//		String decodedString = new String(decodedBytes);
		SubjectMaster subjectMaster = new SubjectMaster();
		if (tokenService.tokenValidate(token)) {
			subjectMaster.setMessage("Invalide token");
			logger.info("Invalid token");
		} else {
			SubjectMasterId SubjectMasterId = new SubjectMasterId(SubjectId,textBookISBN);
			subjectMaster.setSubjectMasterId(SubjectMasterId);
			subjectMaster = subjectMasterService.getSubjectMasterById(subjectMaster);//getElementById(subjectMaster);
			subjectMaster.setMessage("Subject Master Details");
		}
		return subjectMaster;
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
	@GetMapping(value = "/ListOfAllSubjectMaster", headers = "Accept=application/json")
	public @ResponseBody SubjectMasterDetailsDto getListSubjectMaster(@RequestHeader String token) {
		SubjectMasterDetils subjectMasterDetils = new SubjectMasterDetils();
		SubjectMasterDetailsDto subjectMasterDetailsDto = new SubjectMasterDetailsDto();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			subjectMasterDetils.setMessage("Invalide Token");
			logger.info("Invalid token");
		} else {
			subjectMasterDetils = subjectMasterService.list(1,"");
			subjectMasterDetailsDto = subjectMasterControllerHelper.setMasterSubjectsDetailDto(subjectMasterDetils);
		}
		return subjectMasterDetailsDto;
	}
	@GetMapping(value = "/listOfAllSubjectMaster/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody SubjectMasterDetils getListSubjectMaster(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata,
			@RequestHeader String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		 System.out.println("subjectName +subjectName");
		System.out.println("textBookName +textBookName");
		 SubjectMasterDetils subjectMasterDetils = new SubjectMasterDetils();
		 subjectMasterDetils=subjectMasterService.list(id, decodedString);
		 return subjectMasterDetils;
	}
	//	SubjectMasterDetils subjectMasterDetils = new SubjectMasterDetils();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
	//	Claims claims = TokenUtils.verifyToken(token);
	//	System.out.println("token=="+token);
	//	if (tokenService.tokenValidate(token)) {
	//		subjectMasterDetils.setMessage("Invalide Token");
	//		logger.info("Invalid token");
	//	} else {
		//	subjectMasterDetils = subjectMasterService.list(1,"");
		//}
	//	return subjectMasterDetils;
//	}


	/*
	 * @description Delete by Id
	 * 
	 * @param @Id, @token
	 * 
	 * @return ClassSubject
	 *
	 */
	@RequestMapping(value = "/deleteSubjectMaster/{SubjectId}/{textBookISBN}", method = RequestMethod.PUT)
	public @ResponseBody SubjectMaster deleteClassSubjecttById(
			@PathVariable("SubjectId") String SubjectId,
			@PathVariable("textBookISBN") String textBookISBN,
			@RequestHeader String token) {
		SubjectMaster subjectMaster = new SubjectMaster();
		if (tokenService.tokenValidate(token)) {
			subjectMaster.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			SubjectMasterId newSubjectMasterId = new SubjectMasterId(SubjectId, textBookISBN);
			subjectMaster.setSubjectMasterId(newSubjectMasterId);
			subjectMaster = subjectMasterService.getSubjectMasterById(subjectMaster);
			//subjectMaster.setIsSoftDelete(true);
			subjectMasterService.deleteSubjectMaster(subjectMaster);//update(classSubject);
			subjectMaster.setMessage("Deleted successfully");
		}
		return subjectMaster;
	}
@RequestMapping(value = "/getAllSubjectMasterSelect", method = RequestMethod.GET, headers = "Accept=application/json")
	  public @ResponseBody List<SubjectMaster> getAllSubjectMasterSelect(@RequestHeader String token)throws IOException {
	  	Claims claims = TokenUtils.verifyToken(token);
	  	List<SubjectMaster> subjectMaster=new ArrayList();
	  	if (!tokenService.tokenValidate(token)) {
	  		subjectMaster=subjectMasterService.getAllSubjectMasterSelect();
	  	}else {
	  		SubjectMaster csm = new SubjectMaster();
			csm.setMessage("Invalid token");
			subjectMaster.add(csm);
			logger.info("Invalid token");
	  	}
	  	return subjectMaster;
	  }
}
