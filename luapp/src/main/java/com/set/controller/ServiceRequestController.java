package com.set.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.set.controller.helper.AccountControllerHelper;
import com.set.controller.helper.AssignmentControllerHelper;
import com.set.controller.helper.ServiceRequestHelper;
import com.set.dto.EmployeeMasterDetailsDto;
import com.set.dto.ServiceRequestDto;
import com.set.dto.UserMasterDetailsDto;
import com.set.dto.UserProfileDto;
import com.set.dto.UserSubjectDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetails;
import com.set.model.DrawingDetails;
import com.set.model.EmployeeMaster;
import com.set.model.GetServiceRequestDetails;
import com.set.model.LuMessage;
//import com.set.dto.ServiceRequestDto;
import com.set.model.ServiceRequest;
import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestDetails;
import com.set.model.StudentEnrollment;
import com.set.model.StudentMaster;
import com.set.model.SubjectResource;
import com.set.model.SubjectResourceId;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.model.UserToken;
import com.set.service.ServiceRequestService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;
@CrossOrigin
@RestController
public class ServiceRequestController {
	@Autowired
	private ServiceRequestService serviceRequestService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	ServiceRequestHelper serviceRequestControllerHelper;
	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger("ServiceRequestController.class");
	/*
	 * @description addClassSubject with details
	 * 
	 * @param @token,ClassSubject
	 * 
	 * @return ClassSubject
	 * 
	 * @ Author Parnay
	 */
	@PutMapping(value = "/addServiceRequest/{categoryId}", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addServiceRequest(
			@PathVariable("categoryId") String categoryId,
			@RequestBody ServiceRequest serviceRequest,
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
            String details = serviceRequest.getDetails();
			if(details!= null && !details.isEmpty ()) {
			String base64Image = serviceRequest.getDetailsAttached();
		    String extension = serviceRequest.getExtension();
			String pathFile = "";
			if ( (base64Image != null && !base64Image.isEmpty ())&& extension != null && !extension.isEmpty () ){
			     String fileName1 = "coverPageImage" + System.currentTimeMillis();
			     String fileName = fileName1.replace(" ", "");
			     String pathFile1 = Constant.SERVICE_REQUEST_UPLOAD_DIRECTORY + fileName + "." + extension;
			     pathFile = fileName + "." + extension;
			     ImageUpload.decoder(base64Image, pathFile1);
			}
		    serviceRequest.setDetailsAttached(pathFile);
		    serviceRequest.setExtension(extension);
			String emptstrng = "";
			ServiceRequestCategory servicedata = serviceRequestService.retrieveFromId(categoryId);
			serviceRequest.setAssignedTo(servicedata.getAssignedTo());
			int resolve_days = servicedata.getResolveDays();
			Date today = new Date();
			Date closed_on = new Date(today.getTime() + (5000 * 60 * 60 * 24 * 30 * resolve_days));
			serviceRequest.setRaisedOn(CommonUtils.getCurrentDateTime());
			serviceRequest.setServiceRequestClosedOn(closed_on);
			serviceRequest.setServiceRequestTitle(servicedata.getDescription());
			User userdata = userService.retrieveFromId(claims.getSubject());
			serviceRequest.setRequestorId(userdata.getUserId());
			serviceRequest.setRemarks(emptstrng);
			serviceRequest.setIsSoftDelete(0);
		    serviceRequest.setSubCategoryId(categoryId);
			serviceRequest.setInsertedTime(CommonUtils.getCurrentDateTime());
			serviceRequest.setInsertedBy(userdata.getUserId());
			serviceRequest.setStatus("O");//opened
			serviceRequestService.save(serviceRequest);
			message.setMessage("Insert successfully");
			}else {
				message.setMessage("Please enter Details");				
			}
		}
		return message;
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
//	@PutMapping(value = "/editServiceRequest/{serviceRequestId}", headers = "Accept=application/json")
//	public @ResponseBody LuMessage updateServiceRequest(
//			@PathVariable("serviceRequestId") int serviceRequestId,
//			@RequestBody ServiceRequest serviceRequest, @RequestHeader String token) {
//		LuMessage message = new LuMessage();
//		Claims claims = TokenUtils.verifyToken(token);
//		if (tokenService.tokenValidate(token)) {
//			message.setMessage("Invalid token");
//		} else {
//			User userdata = userService.retrieveFromId(claims.getSubject());
//			serviceRequest.setUpdatedBy(userdata.getUserId());
//			serviceRequest.setServiceRequestId(serviceRequestId);
//			serviceRequest.setStatus("O");//opened
//			serviceRequestService.update(serviceRequest);
//			message.setMessage("update successfully");
//		}
//		return message;
//	}

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
	
	@GetMapping(value = "/getAllServiceRequest/{pagenumber}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestDetails getListServiceRequest(
			@PathVariable("pagenumber") int pagenumber,
			@PathVariable("searchdata") String searchdata,
			@RequestHeader String token) {
		ServiceRequestDetails serviceRequestDetails = new ServiceRequestDetails();
		 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		//Map<String, String> mhm = new HashMap<String, String>();
		 //ServiceRequestDetails serviceRequestDetails=new ServiceRequestDetails();
		if (tokenService.tokenValidate(token)) {
			serviceRequestDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			serviceRequestDetails = serviceRequestService.listServiceRequest(pagenumber,decodedString);
		}
		return serviceRequestDetails;
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "getListServiceRequest/{userFilter}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestDetails getListServiceRequest(
			@PathVariable("userFilter") String userFilter,
			@RequestHeader String token) {
//		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
//		String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		ServiceRequestDetails serviceRequestDetails = new ServiceRequestDetails();
		if (tokenService.tokenValidate(token)) {
			serviceRequestDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			List<Map<String, String>> hm = serviceRequestService.list(userFilter);
			int length = hm.size();
			serviceRequestDetails.setServiceRequest(hm);
			serviceRequestDetails.setCount(length);
			if(length>0) {
			    serviceRequestDetails.setMessage("List Of ServiceRequest");
			}else {
			    serviceRequestDetails.setMessage("Service Request Record not found");				
			}
		}
		return serviceRequestDetails;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "getRequestorListServiceRequest", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestDetails getRequestorListServiceRequest(
			@RequestHeader String token) {
//		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
//		String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		ServiceRequestDetails serviceRequestDetails = new ServiceRequestDetails();
		if (tokenService.tokenValidate(token)) {
			serviceRequestDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			String userFilter = userdata.getUserId();
			List<Map<String, String>> hm = serviceRequestService.list1(userFilter);
			int length = hm.size();
			serviceRequestDetails.setCount(length);
			serviceRequestDetails.setServiceRequest(hm);
			if(length>0) {
			    serviceRequestDetails.setMessage("List Of ServiceRequest");
			}else {
			    serviceRequestDetails.setMessage("Service Request Record not found");				
			}
		}
		return serviceRequestDetails;
	}

	
	@GetMapping(value = "/getAllRequestorServiceRequest/{pagenumber}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestDetails getAllRequestorServiceRequest(
			@PathVariable("pagenumber") int pagenumber,
			@PathVariable("searchdata") String searchdata,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		ServiceRequestDetails serviceRequestDetails = new ServiceRequestDetails();
		 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		//Map<String, String> mhm = new HashMap<String, String>();
		 //ServiceRequestDetails serviceRequestDetails=new ServiceRequestDetails();
		if (tokenService.tokenValidate(token)) {
			serviceRequestDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			String userFilter = userdata.getUserId();
			serviceRequestDetails = serviceRequestService.getAllRequestorServiceRequest(pagenumber,decodedString,userFilter);
		}
		return serviceRequestDetails;
	}

	
	@GetMapping(value = "/getAllAssignerServiceRequest/{pagenumber}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequestDetails getAllAssignerServiceRequest(
			@PathVariable("pagenumber") int pagenumber,
			@PathVariable("searchdata") String searchdata,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		ServiceRequestDetails serviceRequestDetails = new ServiceRequestDetails();
			 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
			 String decodedString = new String(decodedBytes);
			//Map<String, String> mhm = new HashMap<String, String>();
			 //ServiceRequestDetails serviceRequestDetails=new ServiceRequestDetails();
			if (tokenService.tokenValidate(token)) {
				serviceRequestDetails.setMessage("Invalid Token");
				logger.info("Invalid token");
			} else {
				User userdata = userService.retrieveFromId(claims.getSubject());
				String userFilter = userdata.getUserId();
				serviceRequestDetails = serviceRequestService.getAllRequestorServiceRequest(pagenumber,decodedString,userFilter);
			}
			return serviceRequestDetails;
	}
	
	@PutMapping(value = "/updateStatusServiceRequest/{serviceRequestId}/{status}", headers = "Accept=application/json")
	public @ResponseBody LuMessage updateStatusServiceRequest(
			@PathVariable("serviceRequestId") int serviceRequestId,
			@PathVariable("status") String status,
			@RequestBody ServiceRequest serviceRequest, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			ServiceRequest newServiceRequest = getCategoryById(serviceRequestId,token);
			if(status.equalsIgnoreCase("P")) {
				ServiceRequestCategory servicedata = serviceRequestService.retrieveFromId(newServiceRequest.getSubCategoryId());
				int resolve_days = servicedata.getResolveDays();
				Date today = new Date();
				Date closed_on = new Date(today.getTime() + (5000 * 60 * 60 * 24 * 30 * resolve_days));
				String details = serviceRequest.getDetails();
				String base64Image = serviceRequest.getDetailsAttached();
			    String extension = serviceRequest.getExtension();
				String pathFile = "";
				if ( (base64Image != null && !base64Image.isEmpty ())&& extension != null && !extension.isEmpty () ){
				     String fileName1 = "coverPageImage" + System.currentTimeMillis();
				     String fileName = fileName1.replace(" ", "");
				     String pathFile1 = Constant.SERVICE_REQUEST_UPLOAD_DIRECTORY + fileName + "." + extension;
				     pathFile = fileName + "." + extension;
				     ImageUpload.decoder(base64Image, pathFile1);
				}
				newServiceRequest.setDetailsAttached(pathFile);
			    newServiceRequest.setExtension(extension);
				newServiceRequest.setDetails(details);
			    newServiceRequest.setServiceRequestClosedOn(closed_on);
			}else {
				newServiceRequest.setDetailsAttached(newServiceRequest.getDetailsAttached());
			    newServiceRequest.setExtension(newServiceRequest.getExtension());
				newServiceRequest.setDetails(newServiceRequest.getDetails());
				newServiceRequest.setServiceRequestClosedOn(newServiceRequest.getServiceRequestClosedOn());
			}
			newServiceRequest.setUpdatedBy(userdata.getUserId());
			newServiceRequest.setIsSoftDelete(0);
			newServiceRequest.setUpdatedTime(CommonUtils.getCurrentDateTime());
			newServiceRequest.setStatus(status);
			newServiceRequest.setServiceRequestId(serviceRequestId);
			String remark = serviceRequest.getRemarks();
			if(remark!= null && !remark.isEmpty ()) {
			newServiceRequest.setRemarks(remark);
			}else {
				newServiceRequest.setRemarks("");
			}
			serviceRequestService.update(newServiceRequest);
			message.setMessage("Status updated successfully");
		}
		return message;
	}

	@SuppressWarnings("null")
	@GetMapping(value = "/getSRById/{id}", headers = "Accept=application/json")
	public @ResponseBody ServiceRequest getCategoryById(@PathVariable("id") int SRId, @RequestHeader String token) {
		ServiceRequest serviceRequest = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			serviceRequest.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			serviceRequest = serviceRequestService.getElementById(SRId);
		}
		return serviceRequest;
	}
	

	@PutMapping(value = "/saveServiceRequest/{subCategoryId}", headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody ServiceRequest saveServiceRequest(
			@PathVariable("subCategoryId") String subCategoryId,
			@RequestPart(value = "ServiceRequest", required = false) ServiceRequest serviceRequest,
			@RequestPart(value = "ServiceRequestPath", required = false) MultipartFile file,
			@RequestHeader String token) throws IOException {
		String filepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			serviceRequest.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
            String details = serviceRequest.getDetails();
			if(details!= null && !details.isEmpty ()) {
			if (file != null && !file.isEmpty()) {
				long currentTime = System.currentTimeMillis();
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = file.getBytes();
				filepath = Constant.SERVICE_REQUEST_UPLOAD_DIRECTORY + currentTime + file.getOriginalFilename();
				Path path = Paths.get(filepath);
				Files.write(path, bytes);
				sj.add(file.getOriginalFilename());
				serviceRequest.setDetailsAttached(currentTime + file.getOriginalFilename());
			    serviceRequest.setExtension(serviceRequest.getExtension());
			}else {
				serviceRequest.setDetailsAttached("");
				serviceRequest.setExtension("");
			}
			String emptstrng = "";
			ServiceRequestCategory servicedata = serviceRequestService.retrieveFromId(subCategoryId);
			serviceRequest.setAssignedTo(servicedata.getAssignedTo());
			int resolve_days = servicedata.getResolveDays();
			Date today = new Date();
			Date closed_on = new Date(today.getTime() + (5000 * 60 * 60 * 24 * 30 * resolve_days));
			serviceRequest.setRaisedOn(CommonUtils.getCurrentDateTime());
			serviceRequest.setServiceRequestClosedOn(closed_on);
			serviceRequest.setServiceRequestTitle(servicedata.getDescription());
			User userdata = userService.retrieveFromId(claims.getSubject());
			serviceRequest.setRequestorId(userdata.getUserId());
			serviceRequest.setRemarks(emptstrng);
			serviceRequest.setInsertedBy(userdata.getUserId());
			serviceRequest.setIsSoftDelete(0);
		    serviceRequest.setSubCategoryId(subCategoryId);
			serviceRequest.setInsertedTime(CommonUtils.getCurrentDateTime());
			serviceRequest.setStatus("O");//opened
			serviceRequestService.save(serviceRequest);
			serviceRequest.setMessage("Insert successfully");
			}else {
				serviceRequest.setMessage("Please enter Details");				
			}
		}
		return serviceRequest;
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
	
	@GetMapping(value = "/getServiceRequestbyId/{serviceRequestId}", headers = "Accept=application/json")
	public @ResponseBody GetServiceRequestDetails getServiceRequestbyId(
			@PathVariable("serviceRequestId") int serviceRequestId, 
			@RequestHeader String token) {
//		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
//		String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		GetServiceRequestDetails getServiceRequestDetails = new GetServiceRequestDetails();
		if (tokenService.tokenValidate(token)) {
			getServiceRequestDetails.setMessage("Invalid Token");
			logger.info("Invalid token");
		} else {
			Map<String , String> hm = serviceRequestService.getServiceRequestbyId(serviceRequestId);
			getServiceRequestDetails.setServiceRequest(hm);
			if(hm!=null) {
				getServiceRequestDetails.setMessage("Service Request Details");
			}else {
				getServiceRequestDetails.setMessage("Service Request Record not found");				
			}
		}
		return getServiceRequestDetails;
	}
	
	@GetMapping(value = "/deleteServiceRequest/{serviceRequestId}", headers = "Accept=application/json")
	public @ResponseBody LuMessage deleteServiceRequestCategory(
			@PathVariable("serviceRequestId") int serviceRequestId, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
//		 byte[] decodedBytes = Base64.getDecoder().decode(categoryId);
//		 String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			ServiceRequest newServiceRequest = getCategoryById(serviceRequestId,token);
			newServiceRequest.setIsSoftDelete(1);
			newServiceRequest.setServiceRequestId(serviceRequestId);
			newServiceRequest.setUpdatedTime(CommonUtils.getCurrentDateTime());
			User user=userService.retrieveFromId(claims.getSubject());
			newServiceRequest.setUpdatedBy(user.getUserId());
			serviceRequestService.update(newServiceRequest);
			message.setMessage("Deleted successfully");
		}
		return message;
	}
	
}
