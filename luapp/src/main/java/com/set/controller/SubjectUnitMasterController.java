package com.set.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
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

import com.set.model.ClassSectionMaster;
import com.set.model.LuMessage;
import com.set.model.NotesMaster;
import com.set.model.SubjectUnitMaster;
import com.set.model.SubjectUnitMasterDetails;
import com.set.model.SubjectUnitMasterId;
import com.set.model.User;
import com.set.service.SubjectUnitMasterService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class SubjectUnitMasterController {
	@Autowired
	private SubjectUnitMasterService subjectUnitMasterService;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("SubjectUnitMasterController.class");

//	@PostMapping(value = "/InsertUnitTopic", headers = "Accept=Application/json")
//	public @ResponseBody LuMessage addSubjectUnitMaster(@RequestBody SubjectUnitMaster subjectUnitMaster,
//			@RequestHeader String token) {
//		Claims claims = TokenUtils.verifyToken(token);
//		LuMessage message = new LuMessage();
//		SubjectUnitMaster tempSubjectUnitMaster = new SubjectUnitMaster();
//		if (tokenService.tokenValidate(token)) {
//			message.setMessage("Invalid token");
//			logger.info("Invalid token");
//		} else {
//			String subjectId = subjectUnitMaster.getSubjectUnitMasterId().getSubjectId();
//			String unitId = subjectUnitMaster.getSubjectUnitMasterId().getUnitId();
//			String topicsId = subjectUnitMaster.getSubjectUnitMasterId().getTopicId();
//			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			String topicName = subjectUnitMaster.getTopicName();
//			tempSubjectUnitMaster.setTopicBookLink(subjectUnitMaster.getTopicBookLink());
//			tempSubjectUnitMaster.setTopicBookPath(subjectUnitMaster.getTopicBookPath());
//			tempSubjectUnitMaster.setTopicMaterialFiletype(subjectUnitMaster.getTopicMaterialFiletype());
//			tempSubjectUnitMaster.setSubjectUnitKey(subjectUnitMaster.getSubjectUnitKey());
//			tempSubjectUnitMaster.setTypeOfMaterial(subjectUnitMaster.getTypeOfMaterial());
//			tempSubjectUnitMaster.setTopicName(subjectUnitMaster.getTopicName());
//			tempSubjectUnitMaster.setIs_soft_delete(0);
//			User userdata = userService.retrieveFromId(claims.getSubject());
//			if ((topicsId.isEmpty()) || (topicsId.equalsIgnoreCase("null"))) {
//				String topicId = CommonUtils.generatePrimaryKeyId("lutbl_topic");
//				SubjectUnitMasterId newSubjectUnitMasterId = new SubjectUnitMasterId(subjectId, unitId, topicId);
//				tempSubjectUnitMaster.setInsertedTime(timestamp);
//				tempSubjectUnitMaster.setSubjectUnitMasterId(newSubjectUnitMasterId);
//				tempSubjectUnitMaster.setInsertedBy(userdata.getUserId());
//				boolean isExistSubjectUnitMaster = subjectUnitMasterService.IsExist(topicName);
//				if (isExistSubjectUnitMaster == true) {
//					message.setMessage("TopicName already Exist");
//				} else {
//					subjectUnitMasterService.save(tempSubjectUnitMaster);
//					message.setMessage("Inserted successfully");
//				}
//			} else {
//				SubjectUnitMasterId newSubjectUnitMasterId = new SubjectUnitMasterId(subjectId, unitId, topicsId);
//				tempSubjectUnitMaster.setUpdatedTime(timestamp);
//				tempSubjectUnitMaster.setSubjectUnitMasterId(newSubjectUnitMasterId);
//				tempSubjectUnitMaster.setUpdatedBy(userdata.getUserId());
//				subjectUnitMasterService.updateSubjectUnitMaster(tempSubjectUnitMaster);
//				message.setMessage("Updated successfully");
//			}
//		}
//		return message;
//	}

	@PostMapping(value="/InsertUnitTopic", headers="Accept=Application/json")
	public @ResponseBody LuMessage addSubjectUnitMaster(@RequestPart ( value = "PROFILEDATA", required = false ) SubjectUnitMaster subjectUnitMaster,@RequestHeader String token) {
		Claims claims=TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		SubjectUnitMaster tempSubjectUnitMaster = new SubjectUnitMaster();
		if(claims==null) {
			message.setMessage("Invalid token");
		}else {
			String subjectId = subjectUnitMaster.getSubjectUnitMasterId().getSubjectId();
			String unitId = subjectUnitMaster.getSubjectUnitMasterId().getUnitId();
			String topicsId = subjectUnitMaster.getSubjectUnitMasterId().getTopicId();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String topicName = subjectUnitMaster.getTopicName();
			tempSubjectUnitMaster.setTopicBookLink(subjectUnitMaster.getTopicBookLink());
			tempSubjectUnitMaster.setTopicBookPath(subjectUnitMaster.getTopicBookPath());
			tempSubjectUnitMaster.setTopicMaterialFiletype(subjectUnitMaster.getTopicMaterialFiletype());
			tempSubjectUnitMaster.setSubjectUnitKey(subjectUnitMaster.getSubjectUnitKey());
			tempSubjectUnitMaster.setTypeOfMaterial(subjectUnitMaster.getTypeOfMaterial());
			tempSubjectUnitMaster.setTopicName(subjectUnitMaster.getTopicName());
			tempSubjectUnitMaster.setIs_soft_delete(0);
			User userdata = userService.retrieveFromId(claims.getSubject());
			if((topicsId.isEmpty())||(topicsId.equalsIgnoreCase("null"))) {
				String topicId = CommonUtils.generatePrimaryKeyId("lutbl_topic");
				SubjectUnitMasterId newSubjectUnitMasterId = new SubjectUnitMasterId(subjectId,unitId,topicId);
				tempSubjectUnitMaster.setInsertedTime(timestamp);
				tempSubjectUnitMaster.setSubjectUnitMasterId(newSubjectUnitMasterId);
				tempSubjectUnitMaster.setInsertedBy(userdata.getUserId());
				//boolean isExistSubjectUnitMaster = subjectUnitMasterService.IsExist(topicName);
//				if(isExistSubjectUnitMaster==true) {
//					message.setMessage("TopicName already Exist");
//				}
//				else{
//					
//				}
				subjectUnitMasterService.save(tempSubjectUnitMaster);
				message.setMessage("Inserted successfully");
			}else {
				SubjectUnitMasterId newSubjectUnitMasterId = new SubjectUnitMasterId(subjectId,unitId,topicsId);
				tempSubjectUnitMaster.setUpdatedTime(timestamp);
				tempSubjectUnitMaster.setSubjectUnitMasterId(newSubjectUnitMasterId);
				tempSubjectUnitMaster.setUpdatedBy(userdata.getUserId());
				subjectUnitMasterService.updateSubjectUnitMaster(tempSubjectUnitMaster);
				message.setMessage("Updated successfully");			
			}		
		}
		return message;
	}
	@PutMapping(value = "/editSubjectUnitMaster/{id}/{uid}/{tid}", headers = "Accept=Application/json")
	public @ResponseBody SubjectUnitMaster editSubjectUnitMaster(@PathVariable("id") String Id,
			@PathVariable("uid") String uId, @PathVariable("tid") String tId,
			@RequestBody SubjectUnitMaster subjectUnitMaster, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			subjectUnitMaster.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			SubjectUnitMasterId newSubjectUnitMasterId = new SubjectUnitMasterId(Id, uId, tId);
			subjectUnitMaster.setSubjectUnitMasterId(newSubjectUnitMasterId);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			subjectUnitMaster.setUpdatedTime(timestamp);
			subjectUnitMaster.setIs_soft_delete(0);
			subjectUnitMasterService.updateSubjectUnitMaster(subjectUnitMaster);
			subjectUnitMaster.setMessage("Updated successfully");
		}
		return subjectUnitMaster;
	}

	@PutMapping(value = "/deleteSubjectUnitMaster/{id}/{uid}/{tid}", headers = "Accept=Application/json")
	public @ResponseBody SubjectUnitMaster deleteSubjectUnitMaster(@PathVariable("id") String Id,
			@PathVariable("uid") String uId, @PathVariable("tid") String tId,
			@RequestBody SubjectUnitMaster subjectUnitMaster, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			subjectUnitMaster.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			SubjectUnitMasterId newSubjectUnitMasterId = new SubjectUnitMasterId(Id, uId, tId);
			subjectUnitMaster.setSubjectUnitMasterId(newSubjectUnitMasterId);
			subjectUnitMaster.setIs_soft_delete(1);
			subjectUnitMasterService.deleteSubjectUnitMaster(subjectUnitMaster);
			subjectUnitMaster.setMessage("Deleted successfully");
		}
		return subjectUnitMaster;
	}

	@SuppressWarnings("null")
	@GetMapping(value = "/getSubjectUnitMasterById/{id}/{uid}/{tid}", headers = "Accept=application/json")
	public @ResponseBody SubjectUnitMaster getSubjectUnitMasterById(@PathVariable("id") String Id,
			@PathVariable("uid") String uId, @PathVariable("tid") String tId, @RequestHeader String token) {
		SubjectUnitMaster subjectUnitMaster = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			subjectUnitMaster.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			SubjectUnitMasterId newSubjectUnitMasterId = new SubjectUnitMasterId(Id, uId, tId);
			subjectUnitMaster = subjectUnitMasterService.getSubjectUnitMasterById(newSubjectUnitMasterId);
		}
		return subjectUnitMaster;
	}

	@GetMapping(value = "/getTotalSubjectUnitMaster", headers = "Accept=application/json")
	public @ResponseBody int getTotalSubjectUnitMaster(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		int i = 0;
		
		if (tokenService.tokenValidate(token)) {			
			logger.info("Invalid token");
		} else {
			logger.info("list of subjectUnitMaster");
			i = subjectUnitMasterService.totalSubjectUnitMaster();
		}
		return i;
	}

	@GetMapping(value = "/getAllSubjectUnitMaster/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody SubjectUnitMasterDetails getAllSubjectUnitMaster(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		 System.out.println("decodedString="+decodedString);
	//	Claims claims = TokenUtils.verifyToken(token);
		SubjectUnitMasterDetails subjectUnitMasterDetails = new SubjectUnitMasterDetails();
		
		//	if (tokenService.tokenValidate(token)) {
			subjectUnitMasterDetails.setMessage("Invalid token");
		//	logger.info("Invalid token");
		//} else {
		    subjectUnitMasterDetails = subjectUnitMasterService.getAllSubjectUnitMaster(id, decodedString);
		    System.out.println("TopicName +TopicName");
			System.out.println("typeOfMaterial +typeOfMaterial");
	//	}
		return subjectUnitMasterDetails;
	}

	@GetMapping(value = "/getAllSubjectUnitMasterSelect", headers = "Accept=application/json")
	public @ResponseBody List<SubjectUnitMaster> getAllSubjectUnitMasterSelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<SubjectUnitMaster> subjectUnitMaster = new ArrayList<>();
		
		if (tokenService.tokenValidate(token)) {
			SubjectUnitMaster csm = new SubjectUnitMaster();
			csm.setMessage("Invalid token");
			subjectUnitMaster.add(csm);
			logger.info("Invalid token");
		} else {
			subjectUnitMaster = subjectUnitMasterService.getAllSubjectUnitMasterSelect();
		}
		return subjectUnitMaster;
	}

	@GetMapping(value = "/GetUnitBySubjectId/{subjectId}", headers = "Accept=application/json")
	public @ResponseBody List<Map<String, String>> getUnitBySubjectId(@PathVariable("subjectId") String subjectId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		NotesMaster notesMaster = new NotesMaster();
		
		
		
		if (claims != null) {
			arrayList = subjectUnitMasterService.getUnitBySubjectList(subjectId);
		} else {
			notesMaster.setMessage("Invalid token");
		}
		return arrayList;
	}

	@GetMapping(value = "/GetTopicByUnitId/{subjectId}/{unitId}", headers = "Accept=application/json")
	public @ResponseBody List<Map<String, String>> getUnitBySubjectId(@PathVariable("subjectId") String subjectId,
			@PathVariable("unitId") String unitId, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		NotesMaster notesMaster = new NotesMaster();
		if (claims != null) {
			arrayList = subjectUnitMasterService.getTopicByUnitList(subjectId, unitId);
		} else {
			notesMaster.setMessage("Invalid token");
		}
		return arrayList;
	}

}
