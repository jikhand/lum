package com.set.controller;

import java.io.File;
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

import javax.annotation.Resource;
import javax.naming.directory.SearchControls;
import javax.servlet.ServletContext;

import org.hibernate.service.spi.ServiceException;
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

import com.set.dto.ResourceBankDetailsDto;
import com.set.dto.ResourceBankDto;
import com.set.dto.ResourceBankSubjectDto;
import com.set.dto.ResourceBankUnitDto;
import com.set.dto.TeacherResourceBankClassDto;
import com.set.dto.TeacherResourceBankDetailsDto;
import com.set.dto.TeacherResourceBankDto;
import com.set.dto.TeacherResourceBankSubjectDto;
import com.set.dto.TeacherResourceBankUnitDto;
import com.set.model.ClassSection;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.LuMessage;

import com.set.model.SubjectMasterDetils;
import com.set.model.SubjectResource;
import com.set.model.SubjectResourceDetails;
import com.set.model.SubjectResourceId;
import com.set.model.TblLogs;
import com.set.model.Teacher;
import com.set.model.User;
import com.set.service.SubjectResourceService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;
import com.set.utils.checkNullException;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class SubjectResourceController {
	@Resource
	private ServletContext context;
	// @Autowired
	// ServletContext context;
	@Autowired
	private SubjectResourceService subjectResourceService;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("SubjectResourceController.class");

	@PostMapping(value = "/InsertResource", headers = "Accept=Application/json")
	public @ResponseBody LuMessage addSubjectResource(@RequestBody SubjectResource subjectResource,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		SubjectResource tempSubjectResource = new SubjectResource();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String subjectId = subjectResource.getSubjectResourceId().getSubjectId();
			String unitId = subjectResource.getSubjectResourceId().getUnitId();
			String resourcesId = subjectResource.getSubjectResourceId().getResourceId();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String resourceType = subjectResource.getResourceType();
			String val = "";
			// if (resourceType.equals("V")) {
			if (!subjectResource.getResourceLink().isEmpty()) {
				System.out.println("in if");
				tempSubjectResource.setResourcePath(val);
				tempSubjectResource.setResourceExtension(val);
				tempSubjectResource.setResourceLink(subjectResource.getResourceLink());
			} else {
				System.out.println("in else");
				String base64Image = subjectResource.getResourcePath();
				String fileName = "resourceDocument" + System.currentTimeMillis();
				String extension = subjectResource.getResourceExtension();
				String pathFile = Constant.RESOURCE_UPLOAD_DIRECTORY + fileName + "." + extension;
				System.out.println("fileName1=" + fileName + "." + extension);
				ImageUpload.decoder(base64Image, pathFile);
				tempSubjectResource.setResourcePath(fileName + "." + extension);
				tempSubjectResource.setResourceExtension(extension);
				tempSubjectResource.setResourceLink(val);
			}
			String thumbnailImage = subjectResource.getThumbnailImage();
			String fileName1 = "resourceThumbnail" + System.currentTimeMillis();
			String extension1 = "png";
			// String pathFile1 = context.getRealPath("") + File.separator +
			// Constant.RESOURCE_REL_DIRECTORY + fileName1 + "." + extension1;
			String pathFile1 = Constant.RESOURCE_UPLOAD_DIRECTORY + fileName1 + "." + extension1;
			ImageUpload.decoder(thumbnailImage, pathFile1);
			System.out.println("fileName1=" + fileName1 + "." + extension1);
			tempSubjectResource.setThumbnailImage(fileName1 + "." + extension1);
			tempSubjectResource.setResourceTitle(subjectResource.getResourceTitle());
			tempSubjectResource.setResourceType(resourceType);
			tempSubjectResource.setIs_soft_delete(0);
			User userdata = userService.retrieveFromId(claims.getSubject());
			if ((resourcesId.isEmpty()) || (resourcesId.equalsIgnoreCase("null"))) {
				String resourceId = CommonUtils.generatePrimaryKeyId("lutbl_resource");
				SubjectResourceId newSubjectResourceId = new SubjectResourceId(subjectId, unitId, resourceId);
				tempSubjectResource.setInsertedTime(timestamp);
				if (subjectResourceService.IsExist(tempSubjectResource.getResourceTitle(),"")) {
					message.setMessage("Resource title already exist");
					message.setCode("200");
				} else {
					tempSubjectResource.setSubjectResourceId(newSubjectResourceId);
					tempSubjectResource.setInsertedBy(userdata.getUserId());
					subjectResourceService.save(tempSubjectResource);
					message.setMessage("Inserted successfully");
					message.setCode("200");
				}

			} else {
				SubjectResourceId newSubjectResourceId = new SubjectResourceId(subjectId, unitId, resourcesId);
				tempSubjectResource.setUpdatedTime(timestamp);
				tempSubjectResource.setSubjectResourceId(newSubjectResourceId);
				tempSubjectResource.setUpdatedBy(userdata.getUserId());
				subjectResourceService.updateSubjectResource(tempSubjectResource);
				message.setCode("200");
				message.setMessage("Updated successfully");
			}
		}
		return message;
	}

	@PostMapping(value = "/addResource", headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody SubjectResource addResource(
			@RequestPart(value = "resourceMaster", required = false) SubjectResource subjectResource,
			@RequestPart(value = "resourcePath", required = false) MultipartFile file,
			@RequestPart(value = "resourceThumbNail", required = false) MultipartFile thumbnail,
			@RequestHeader String token) throws IOException {
		String filepath = "";
		String thumbnailfilepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		SubjectResource tempSubjectResource = new SubjectResource();
		if (tokenService.tokenValidate(token)) {
			tempSubjectResource.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String subjectId = subjectResource.getSubjectResourceId().getSubjectId();
			String unitId = subjectResource.getSubjectResourceId().getUnitId();
			String resourcesId = subjectResource.getSubjectResourceId().getResourceId();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String resourceType = subjectResource.getResourceType();
			String val = "";
			// if (resourceType.equals("V")) {
			
//			try
//			{
//				if (!subjectResource.getResourceLink().isEmpty()) {
//					tempSubjectResource.setResourcePath(val);
//					tempSubjectResource.setResourceLink(subjectResource.getResourceLink());
//				}
//			}
//			catch (java.lang.NullPointerException exception)
//			{
//			System.out.println(exception.toString());
//			}
			if (file != null && !file.isEmpty()) {
				long currentTime = System.currentTimeMillis();
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = file.getBytes();
				filepath = Constant.RESOURCE_UPLOAD_DIRECTORY + currentTime + file.getOriginalFilename();
				Path path = Paths.get(filepath);
				Files.write(path, bytes);
				sj.add(file.getOriginalFilename());
				tempSubjectResource.setResourcePath(currentTime + file.getOriginalFilename());// setResourcePath(file);
			}else {
				tempSubjectResource.setResourceLink(subjectResource.getResourceLink());	
			}
			if (thumbnail != null && !thumbnail.isEmpty()) {
				long currentTime = System.currentTimeMillis();
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = thumbnail.getBytes();
				thumbnailfilepath = Constant.RESOURCE_UPLOAD_DIRECTORY + currentTime + thumbnail.getOriginalFilename();
				Path path = Paths.get(thumbnailfilepath);
				Files.write(path, bytes);
				sj.add(thumbnail.getOriginalFilename());
				tempSubjectResource.setThumbnailImage(currentTime + thumbnail.getOriginalFilename());// setResourcePath(file);
			}
			tempSubjectResource.setResourceTitle(subjectResource.getResourceTitle());
			tempSubjectResource.setResourceType(resourceType);
			tempSubjectResource.setIs_soft_delete(0);
			tempSubjectResource.setResourceExtension(subjectResource.getResourceExtension());
			User userdata = userService.retrieveFromId(claims.getSubject());
			String resourceId = CommonUtils.generatePrimaryKeyId("lutbl_resource");
			SubjectResourceId newSubjectResourceId = new SubjectResourceId(subjectId, unitId, resourceId);
			tempSubjectResource.setInsertedTime(timestamp);
			tempSubjectResource.setSubjectResourceId(newSubjectResourceId);
			tempSubjectResource.setInsertedBy(userdata.getUserId());
			subjectResourceService.save(tempSubjectResource);
			tempSubjectResource.setMessage("Inserted successfully");
		}
		return tempSubjectResource;
	}

	@PutMapping(value = "/addResource", headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody SubjectResource editResource(
			@RequestPart(value = "resourceMaster", required = false) SubjectResource subjectResource,
			@RequestPart(value = "resourcePath", required = false) MultipartFile file,
			@RequestPart(value = "resourceThumbNail", required = false) MultipartFile thumbnail,
			@RequestHeader String token) throws ServiceException, IllegalStateException, IOException {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String subjectId = subjectResource.getSubjectResourceId().getSubjectId();
		String unitId = subjectResource.getSubjectResourceId().getUnitId();
		String resourcesId = subjectResource.getSubjectResourceId().getResourceId();
		String filepath = "";
		String thumbnailfilepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		SubjectResource tempSubjectResource = new SubjectResource();
		if (tokenService.tokenValidate(token)) {
			tempSubjectResource.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String resourceType = subjectResource.getResourceType();
			String val = "";
			tempSubjectResource = subjectResourceService.getSubjectResourceById(subjectResource.getSubjectResourceId());
				if (file != null && !file.isEmpty()) {
					long currentTime = System.currentTimeMillis();
					StringJoiner sj = new StringJoiner(" , ");
					byte[] bytes = file.getBytes();
					filepath = Constant.RESOURCE_UPLOAD_DIRECTORY + currentTime + file.getOriginalFilename();
					Path path = Paths.get(filepath);
					Files.write(path, bytes);
					sj.add(file.getOriginalFilename());
					tempSubjectResource.setResourceLink("");
					tempSubjectResource.setResourcePath(currentTime + file.getOriginalFilename());// setResourcePath(file);
				}else {
					if(null!=subjectResource.getResourceLink()) {
						tempSubjectResource.setResourcePath("");
						tempSubjectResource.setResourceLink(subjectResource.getResourceLink());
					}	
				}
			if (thumbnail != null && !thumbnail.isEmpty()) {
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = thumbnail.getBytes();
				long currentTime = System.currentTimeMillis();
				thumbnailfilepath = Constant.RESOURCE_UPLOAD_DIRECTORY + currentTime + thumbnail.getOriginalFilename();
				Path path = Paths.get(thumbnailfilepath);
				Files.write(path, bytes);
				sj.add(thumbnail.getOriginalFilename());
				tempSubjectResource.setThumbnailImage(currentTime + thumbnail.getOriginalFilename());// setResourcePath(file);
			}
			tempSubjectResource.setResourceTitle(subjectResource.getResourceTitle());
			tempSubjectResource.setResourceType(resourceType);
			tempSubjectResource.setIs_soft_delete(0);
			tempSubjectResource.setResourceExtension(subjectResource.getResourceExtension());
			User userdata = userService.retrieveFromId(claims.getSubject());
			SubjectResourceId newSubjectResourceId = new SubjectResourceId(subjectId, unitId, resourcesId);
			tempSubjectResource.setSubjectResourceId(newSubjectResourceId);
			tempSubjectResource.setUpdatedBy(userdata.getUserId());
			subjectResourceService.updateSubjectResource(tempSubjectResource);
			tempSubjectResource.setUpdatedTime(timestamp);
			tempSubjectResource.setMessage("Updated successfully");
		}
		return tempSubjectResource;
	}

	@PostMapping(value = "/GetAllResourceBank", headers = "Accept=application/json")
	public @ResponseBody ResourceBankDetailsDto getAllResourceBankDetails(@RequestBody ClassSection classsec,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> resourceBankSubjectList = null;
		List<Object[]> resourceBankUnitList = null;
		List<Object[]> resourceBankList = null;
		ResourceBankDetailsDto resourceBankDetailsDto = new ResourceBankDetailsDto();
		List<ResourceBankSubjectDto> resourceBankSubjectDtoList = new ArrayList<>();
		ResourceBankDto resourceBankDto;
		ResourceBankUnitDto resourceBankUnitDto;
		ResourceBankSubjectDto resourceBankSubjectDto;
		String classId = classsec.getClassId();

		if (tokenService.tokenValidate(token)) {
			resourceBankDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			resourceBankSubjectList = subjectResourceService.getAllResourceBankSubjectSelect(classId);
			for (Object[] eachResourceBankSubject : resourceBankSubjectList) {
				List<ResourceBankUnitDto> resourceBankUnitDtoList = new ArrayList<>();
				resourceBankSubjectDto = new ResourceBankSubjectDto();
				String subjectId = eachResourceBankSubject[0].toString();
				resourceBankSubjectDto.setSubjectId(subjectId);
				logger.info("subjectId" + subjectId);
				resourceBankSubjectDto.setSubjectName(eachResourceBankSubject[1].toString());
				resourceBankUnitList = subjectResourceService.getAllResourceBankUnitSelect(subjectId);
				for (Object[] eachResourceBankUnit : resourceBankUnitList) {
					List<ResourceBankDto> resourceBankDtoList = new ArrayList<>();
					resourceBankUnitDto = new ResourceBankUnitDto();
					String unitId = eachResourceBankUnit[0].toString();
					resourceBankUnitDto.setUnitSubjectId(unitId);
					resourceBankUnitDto.setUnitName(eachResourceBankUnit[1].toString());
					resourceBankList = subjectResourceService.getAllResourceBankSelect(unitId, subjectId);
					for (Object[] eachResourceBank : resourceBankList) {
						resourceBankDto = new ResourceBankDto();
						resourceBankDto.setResourceId(checkNullException.firstNonNull(eachResourceBank[0]).toString());
						resourceBankDto.setResourceExtension(checkNullException.firstNonNull(eachResourceBank[1]).toString());
						resourceBankDto.setResourceLink(checkNullException.firstNonNull(eachResourceBank[2]).toString());
						resourceBankDto.setResourcePath(checkNullException.firstNonNull(eachResourceBank[3]).toString());
						resourceBankDto.setResourceTitle(checkNullException.firstNonNull(eachResourceBank[4]).toString());
						resourceBankDto.setResourceType(checkNullException.firstNonNull(eachResourceBank[5]).toString());
						resourceBankDto.setThumbnailImage(checkNullException.firstNonNull(eachResourceBank[6]).toString());
						resourceBankDto.setSubjectId(checkNullException.firstNonNull(eachResourceBank[7]).toString());
						resourceBankDto.setUnitId(checkNullException.firstNonNull(eachResourceBank[8]).toString());
						resourceBankDtoList.add(resourceBankDto);
					}
					resourceBankUnitDto.setResourceBankList(resourceBankDtoList);
					resourceBankUnitDtoList.add(resourceBankUnitDto);
				}
				resourceBankSubjectDto.setResourceBankUnitList(resourceBankUnitDtoList);
				resourceBankSubjectDtoList.add(resourceBankSubjectDto);
			}
			resourceBankDetailsDto.setResourceBankSubjectList(resourceBankSubjectDtoList);
			resourceBankDetailsDto.setCount(resourceBankSubjectDtoList.size());
			resourceBankDetailsDto.setMessage("List of Resource Bank");
		}
		return resourceBankDetailsDto;
	}

	@SuppressWarnings("null")
	@PostMapping(value = "/TeacherAllResourceBank", headers = "Accept=application/json")
	public @ResponseBody TeacherResourceBankDetailsDto getAllTeacherResourceBankDetails(@RequestBody Teacher teacher,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> teacherResourceBankClassList = null;
		List<Object[]> teacherResourceBankSubjectList = null;
		List<Object[]> teacherResourceBankUnitList = null;
		List<Object[]> teacherResourceBankList = null;
		TeacherResourceBankDetailsDto teacherResourceBankDetailsDto = new TeacherResourceBankDetailsDto();
		List<TeacherResourceBankClassDto> teacherResourceBankClassDtoList = new ArrayList<>();
		TeacherResourceBankDto teacherResourceBankDto;
		TeacherResourceBankUnitDto teacherResourceBankUnitDto;
		TeacherResourceBankSubjectDto teacherResourceBankSubjectDto;
		TeacherResourceBankClassDto teacherResourceBankClassDto;
		String teacherId = teacher.getTeacherId();

		if (tokenService.tokenValidate(token)) {
			teacherResourceBankDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			teacherResourceBankClassList = subjectResourceService.getAllTeacherResourceBankClassSelect(teacherId);
			for (Object[] eachTeacherResourceBankClass : teacherResourceBankClassList) {
				List<TeacherResourceBankSubjectDto> teacherResourceBankSubjectDtoList = new ArrayList<>();
				teacherResourceBankClassDto = new TeacherResourceBankClassDto();
				String classId = eachTeacherResourceBankClass[0].toString();
				teacherResourceBankClassDto.setClassId(classId);
				teacherResourceBankClassDto.setClassName(eachTeacherResourceBankClass[1].toString());
				teacherResourceBankSubjectList = subjectResourceService
						.getAllTeacherResourceBankSubjectSelect(teacherId, classId);
				for (Object[] eachTeacherResourceBankSubject : teacherResourceBankSubjectList) {
					List<TeacherResourceBankUnitDto> teacherResourceBankUnitDtoList = new ArrayList<>();
					teacherResourceBankSubjectDto = new TeacherResourceBankSubjectDto();
					String subjectId = eachTeacherResourceBankSubject[0].toString();
					teacherResourceBankSubjectDto.setSubjectId(subjectId);
					teacherResourceBankSubjectDto.setSubjectName(eachTeacherResourceBankSubject[1].toString());
					teacherResourceBankUnitList = subjectResourceService.getAllTeacherResourceBankUnitSelect(subjectId);
					for (Object[] eachTeacherResourceBankUnit : teacherResourceBankUnitList) {
						List<TeacherResourceBankDto> teacherResourceBankDtoList = new ArrayList<>();
						teacherResourceBankUnitDto = new TeacherResourceBankUnitDto();
						String unitId = eachTeacherResourceBankUnit[0].toString();
						teacherResourceBankUnitDto.setUnitSubjectId(unitId);
						teacherResourceBankUnitDto.setUnitName(eachTeacherResourceBankUnit[1].toString());
						teacherResourceBankList = subjectResourceService.getAllTeacherResourceBankSelect(unitId,
								subjectId);
						for (Object[] eachTeacherResourceBank : teacherResourceBankList) {
							teacherResourceBankDto = new TeacherResourceBankDto();
							
							String resourceId = (String)eachTeacherResourceBank[0];
							resourceId = (resourceId == null ? "" : resourceId);
							System.out.println("str: "+resourceId);							
							teacherResourceBankDto.setResourceId(resourceId);
					
							String resourcExtn = (String)eachTeacherResourceBank[1];
							resourcExtn = (resourcExtn == null ? "" : resourcExtn);
							System.out.println("str: "+resourcExtn);						
							teacherResourceBankDto.setResourceExtension(resourcExtn);
							
							String resourcLink = (String)eachTeacherResourceBank[2];
							resourcLink = (resourcLink == null ? "" : resourcLink);
							System.out.println("str: "+resourcLink);							
							teacherResourceBankDto.setResourceLink(resourcLink);
							
							String resourcePath = (String)eachTeacherResourceBank[3];
							resourcePath = (resourcePath == null ? "" : resourcePath);
							System.out.println("str: "+resourcePath);							
							teacherResourceBankDto.setResourcePath(resourcePath);
							
							String resourceTitle = (String)eachTeacherResourceBank[4];
							resourceTitle = (resourceTitle == null ? "" : resourceTitle);
							System.out.println("str: "+resourceTitle);							
							teacherResourceBankDto.setResourceTitle(resourceTitle);
							
							String resourceType = (String)eachTeacherResourceBank[5];
							resourceType = (resourceType == null ? "" : resourceType);
							System.out.println("str: "+resourceType);							
							teacherResourceBankDto.setResourceType(resourceType);
							
							String thumbnailImage = (String)eachTeacherResourceBank[6];
							thumbnailImage = (thumbnailImage == null ? "" : thumbnailImage);
							System.out.println("str: "+thumbnailImage);							
							teacherResourceBankDto.setThumbnailImage(thumbnailImage);

							String subjectid = (String)eachTeacherResourceBank[7];
							subjectid = (subjectid == null ? "" : subjectid);
							System.out.println("str: "+subjectid);							
							teacherResourceBankDto.setSubjectId(subjectid);

							String unitid = (String)eachTeacherResourceBank[8];
							unitid = (unitid == null ? "" : unitid);
							System.out.println("str: "+unitid);							
							teacherResourceBankDto.setUnitId(unitid);
							
							
							teacherResourceBankDtoList.add(teacherResourceBankDto);
						}
						teacherResourceBankUnitDto.setTeacherResourceBankList(teacherResourceBankDtoList);
						teacherResourceBankUnitDtoList.add(teacherResourceBankUnitDto);
					}
					teacherResourceBankSubjectDto.setTeacherResourceBankUnitList(teacherResourceBankUnitDtoList);
					teacherResourceBankSubjectDtoList.add(teacherResourceBankSubjectDto);
				}
				teacherResourceBankClassDto.setTeacherResourceBankSubjectList(teacherResourceBankSubjectDtoList);
				teacherResourceBankClassDtoList.add(teacherResourceBankClassDto);
			}
			teacherResourceBankDetailsDto.setTeacherResourceBankClassList(teacherResourceBankClassDtoList);
			teacherResourceBankDetailsDto.setCount(teacherResourceBankClassDtoList.size());
			teacherResourceBankDetailsDto.setMessage("List of Teacher Resource Bank Details");
		}
		return teacherResourceBankDetailsDto;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/listOfAllResourceBank/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody SubjectResourceDetails getListSubjectMaster(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		SubjectResourceDetails subjectResourceDetails = new SubjectResourceDetails();
		if (tokenService.tokenValidate(token)) {
			subjectResourceDetails.setMessage("Invalid Token");
		} else {
			subjectResourceDetails = subjectResourceService.getAllSubjectResource(id,decodedString);
			subjectResourceDetails.setMessage("List Of Resource Bank");
		}
		return subjectResourceDetails;
	}

	// "SubjectId":ResourceBankinfo.SubjectId,"UnitId":ResourceBankinfo.UnitId,"ResourceId":ResourceBankinfo.ResourceId
	@GetMapping(value = "/getSubjectResourceById/{resourceId}/{subjectId}/{unitId}", headers = "Accept=application/json")
	public @ResponseBody SubjectResource getSubjectResourceById(@PathVariable("resourceId") String resourceId,
			@PathVariable("subjectId") String subjectId, @PathVariable("unitId") String unitId,
			@RequestHeader String token) {
		SubjectResource subjectResource = new SubjectResource();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			System.out.println("invalide token");
			subjectResource.setMessage("Invalide token");
		} else {
			SubjectResourceId subjectResourceById = new SubjectResourceId(subjectId, unitId, resourceId);
			subjectResource.setSubjectResourceId(subjectResourceById);
			subjectResource = subjectResourceService.getSubjectResourceById(subjectResource.getSubjectResourceId());
			subjectResource.setMessage("subject Resource details");
		}
		return subjectResource;
	}

	@RequestMapping(value = "/deleteResourceBank/{resourceId}/{subjectId}/{unitId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody boolean DeleteResourceBank(@PathVariable("resourceId") String resourceId,
			@PathVariable("subjectId") String subjectId, @PathVariable("unitId") String unitId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		SubjectResource resourcebank = new SubjectResource();
		if (tokenService.tokenValidate(token)) {
			resourcebank.setMessage("Invalid token");
		} else {
			SubjectResourceId myId = new SubjectResourceId(subjectId, unitId, resourceId);
			resourcebank.setSubjectResourceId(myId);
			resourcebank.setIs_soft_delete(1);
			subjectResourceService.deleteSubjectResource(resourcebank);
		}
		return true;
	}

	@RequestMapping(value = "/resourceExist", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody boolean resourceExist(@RequestBody LuMessage luMessage,
			@RequestHeader String token) {		
		System.out.println("StringresourceTitle"+luMessage.getMessage());
		System.out.println("decodedString"+luMessage.getCode());
		if (tokenService.tokenValidate(token)) {
			return false;
		} else {
			System.out.println("in query");
			if (subjectResourceService.IsExist(luMessage.getMessage(),luMessage.getCode())) {
				System.out.println("Result query");
				return true;
			} else {
				return false;
			}
		}
	}

}