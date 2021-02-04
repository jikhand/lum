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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.controller.helper.DrawingControllerHelper;
import com.set.dto.DrawingCategoryDetailsDto;
import com.set.dto.DrawingCategoryDto;
import com.set.dto.DrawingCategoryNameDto;
import com.set.dto.DrawingDetailsDto;
import com.set.dto.DrawingDto;
import com.set.dto.InsertDrawingDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.Drawing;
import com.set.model.DrawingCategory;
import com.set.model.DrawingDetails;
import com.set.model.DrawingListDetails;
import com.set.model.LuMessage;
import com.set.model.NotesMaster;
import com.set.model.Student;
import com.set.model.StudentMaster;
import com.set.model.User;
import com.set.service.ClassSectionMasterService;
import com.set.service.DrawingCategoryService;
import com.set.service.DrawingService;
import com.set.service.StudentMasterService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.ImageUpload;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class DrawingController {
	@Autowired
	private DrawingService drawingService;
	@Autowired
	private DrawingCategoryService drawingCategoryService;
	@Autowired
	private DrawingCategoryController drawingCategoryController;
	@Autowired
	private StudentMasterService studentMasterServuce;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("DrawingController.class");

	@PostMapping(value = "/InsertDrawing", headers = "Accept=Application/json")
	public @ResponseBody InsertDrawingDto addDrawing(@RequestBody Drawing drawing, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		Drawing tempDrawing = new Drawing();
		InsertDrawingDto insertDrawingDto = new InsertDrawingDto();
		DrawingControllerHelper drawingControllerHelper = new DrawingControllerHelper();
		if (tokenService.tokenValidate(token)) {
			insertDrawingDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String base64Image = drawing.getImageUrl();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String fileName = "coverPageImage" + System.currentTimeMillis();
			String extension = "png";
			StudentMaster studentMaster = studentMasterServuce.getStudentMasterByStudentId(drawing.getStudentId());
			if(null!=studentMaster) {
			String pathFile = Constant.DRAWING_UPLOAD_DIRECTORY + fileName + "." + extension;
			ImageUpload.decoder(base64Image, pathFile);
			tempDrawing.setImageUrl(fileName.replace(" ", "") + "." + extension);
			tempDrawing.setDrawingName(drawing.getDrawingName());
			tempDrawing.setClassId(studentMaster.getClassId());
			tempDrawing.setSectionId(studentMaster.getSectionId());
			DrawingCategory drawingCategory = drawingCategoryService
					.getDrawingCategoryByName(drawing.getDrawingCategory());
			String categoryId = "";
			String categoryName = "";
			if (null == drawingCategory) {
				categoryId = drawingCategoryController.addDrawingCategory(drawing.getDrawingCategory());
				categoryName = drawing.getDrawingCategory();
			} else {
				categoryId = drawingCategory.getDrawingCategoryId();
				categoryName = drawingCategory.getDrawingCategoryName();
			}
			tempDrawing.setDrawingCategory(categoryId);
			tempDrawing.setDrawingCode(drawing.getDrawingCode());
			tempDrawing.setDrawingType(drawing.getDrawingType());
			tempDrawing.setStudentId(drawing.getStudentId());
			tempDrawing.setCreatedAt(timestamp);
			String drawingsId = CommonUtils.checkNull(drawing.getDrawingId());
			logger.info("drawingsId" + drawingsId);
			logger.info("pathFile" + pathFile);
			if ((drawingsId.isEmpty()) || (null == drawingsId)) {
				String drawingId = CommonUtils.generatePrimaryKeyId("lutbl_drawing");
				tempDrawing.setDrawingId(drawingId);
				tempDrawing.setIsDeleted(false);
				drawingService.save(tempDrawing);
				//tempDrawing.setMessage("Drawing Inserted successfully");
				insertDrawingDto.setMessage("Drawing Inserted successfully");
			} else {
				tempDrawing.setDrawingId(drawingsId);
				tempDrawing.setUpdatedAt(timestamp);
				drawingService.updateDrawing(tempDrawing);
				//tempDrawing.setMessage("Updated successfully");
				insertDrawingDto.setMessage("Updated successfully");
			}
			insertDrawingDto = drawingControllerHelper.setDrawingDto(insertDrawingDto, tempDrawing, categoryName);
			}
			else {
				insertDrawingDto.setMessage("Invalid Student Id!");
			}
		}
		return insertDrawingDto;
	}

//	@PutMapping(value = "/editDrawing/{id}", headers = "Accept=Application/json")
//	public @ResponseBody Drawing editDrawing(@PathVariable("id") String Id, @RequestBody Drawing drawing,
//			@RequestHeader String token) {
//		Claims claims = TokenUtils.verifyToken(token);
//		if (tokenService.tokenValidate(token)) {
//			drawing.setMessage("Invalid token");
//			logger.info("Invalid token");
//		} else {
//			String base64Image = drawing.getImageUrl();
//			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			String fileName = "coverPageImage" + System.currentTimeMillis();
//			String extension = "png";
//			String pathFile = Constant.DRAWING_UPLOAD_DIRECTORY + fileName + "." + extension;
//			ImageUpload.decoder(base64Image, pathFile);
//			drawing.setImageUrl(pathFile);
//			drawing.setDrawingId(Id);
//			drawing.setUpdatedAt(timestamp);
//			drawing.setIsDeleted(false);
//			drawingService.updateDrawing(drawing);
//			drawing.setMessage("Updated successfully");
//		}
//		return drawing;
//	}

	@PutMapping(value = "/deleteDrawing/{id}", headers = "Accept=Application/json")
	public @ResponseBody Drawing deleteDrawing(@PathVariable("id") String Id, @RequestBody Drawing drawing,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			drawing.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			drawing.setDrawingId(Id);
			drawing.setUpdatedAt(timestamp);
			drawing.setIsDeleted(true);
			drawingService.deleteDrawing(drawing);
			drawing.setMessage("Deleted successfully");
		}
		return drawing;
	}

	@GetMapping(value = "/getDrawingById/{id}", headers = "Accept=application/json")
	public @ResponseBody Drawing getStudentNotesById(@PathVariable("id") String Id, @RequestHeader String token) {
		Drawing drawing = new Drawing();
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			drawing.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			drawing = drawingService.getDrawingById(Id);
		}
		return drawing;
	}

	@GetMapping(value = "/getTotalDrawing", headers = "Accept=application/json")
	public @ResponseBody int getTotalDrawing(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		int i = 0;
		
		if (tokenService.tokenValidate(token)) {
			logger.info("Invalid token");
		} else {
			logger.info("list of Drawing");
			i = drawingService.totalDrawing();
		}
		return i;
	}

	@GetMapping(value = "/getAllDrawing/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody DrawingDetails getAllDrawing(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		 byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		 System.out.println("decodedString="+decodedString);
		Claims claims = TokenUtils.verifyToken(token);
		DrawingDetails drawingDetails = new DrawingDetails();
		
		if (tokenService.tokenValidate(token)) {
			drawingDetails.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			drawingDetails.setMessage("get All Drawing");
			drawingDetails = drawingService.getAllDrawing(id,decodedString);
		} 
		return drawingDetails;
	}

	@GetMapping(value = "/getAllStudentDrawing/{id}/{Rtype}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody DrawingListDetails getAllStudentDrawing(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata,
			@PathVariable("Rtype") String Rtype,
			@RequestHeader String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		 String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		User user = userService.retrieveFromId(claims.getSubject());
		String userId = user.getUserId();
		String TeacherId="";
		String studentid="";
		DrawingListDetails drawingListDetails=new DrawingListDetails();
		if (claims != null) {
			if(Rtype.equalsIgnoreCase("Student")) {
				studentid=userId;
			}else if(Rtype.equalsIgnoreCase("Teacher")){
				TeacherId=userId;//drawingService.getClassSubject(userId); currently not in used
				}
			drawingListDetails = drawingService.getAllStudentDrawingList(id, decodedString, studentid,TeacherId);
			drawingListDetails.setMessage("drawing details");
		} else {
			drawingListDetails.setMessage("Invalid token");
		}
		return drawingListDetails;
	}

	@GetMapping(value = "/getAllDrawingSelect", headers = "Accept=application/json")
	public @ResponseBody List<Drawing> getAllDrawingSelect(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Drawing> drawing = new ArrayList<>();		

		if (tokenService.tokenValidate(token)) {
			Drawing csm = new Drawing();
			csm.setMessage("Invalid token");
			drawing.add(csm);
			logger.info("Invalid token");
		} else {
			drawing = drawingService.getAllDrawingSelect();
		}
		return drawing;
	}

	@PostMapping(value = "/GetAllStudentDrawings", headers = "Accept=application/json")
	public @ResponseBody DrawingDetailsDto getAllStudentDrawings(@RequestBody Student student,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> drawingList = null;
		DrawingDetailsDto drawingDetailsDto = new DrawingDetailsDto();
		List<DrawingDto> drawingDtoList = new ArrayList<>();
		DrawingDto drawingDto;
		
		if (tokenService.tokenValidate(token)) {
			drawingDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			drawingList = drawingService.getAllStudentDrawings(student.getStudentId());
			for (Object[] eachDrawing : drawingList) {
				drawingDto = new DrawingDto();
				drawingDto.setDrawingId(eachDrawing[0].toString());
				drawingDto.setDrawingName(eachDrawing[3].toString());
				drawingDto.setDrawing(eachDrawing[4].toString());
				drawingDto.setDrawingCategoryId(eachDrawing[2].toString());
				drawingDto.setDrawingCategoryName(eachDrawing[7].toString());
				drawingDtoList.add(drawingDto);
			}
			drawingDetailsDto.setDrawingDtoList(drawingDtoList);
			drawingDetailsDto.setCount(drawingDtoList.size());
			drawingDetailsDto.setMessage("List of Student drawings");

		}
		return drawingDetailsDto;
	}

	@PostMapping(value = "/DrawingCategoryDetails", headers = "Accept=application/json")
	public @ResponseBody DrawingCategoryDetailsDto getAllStudentDrawingCategoryDetails(@RequestBody Student student,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Object[]> drawingCategoryList = null;
		List<Object[]> drawingCategoryNameList = null;
		DrawingCategoryDetailsDto drawingCategoryDetailsDto = new DrawingCategoryDetailsDto();
		List<DrawingCategoryNameDto> drawingCategoryNameDtoList = new ArrayList<>();
		DrawingCategoryDto drawingCategoryDto;
		DrawingCategoryNameDto drawingCategoryNameDto;
		
		if (tokenService.tokenValidate(token)) {
			drawingCategoryDetailsDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			User user = userService.retrieveFromId(claims.getSubject());
			String userId = user.getUserId();
			drawingCategoryNameList = drawingService.getAllStudentDrawingCategoryName(student.getStudentId(), userId);
			for (Object[] eachDrawingCategoryName : drawingCategoryNameList) {
				List<DrawingCategoryDto> drawingCategoryDtoList = new ArrayList<>();
				drawingCategoryNameDto = new DrawingCategoryNameDto();
				String drawingCategoryId = eachDrawingCategoryName[0].toString();
				drawingCategoryNameDto.setDrawingCategoryId(drawingCategoryId);
				drawingCategoryNameDto.setDrawingCategoryName(eachDrawingCategoryName[1].toString());
				drawingCategoryNameDto.setDrawingCategoryList(drawingCategoryDtoList);
				drawingCategoryList = drawingService.getAllStudentDrawingCategory(student.getStudentId(), userId,
						drawingCategoryId);
				for (Object[] eachDrawingCategory : drawingCategoryList) {
					drawingCategoryDto = new DrawingCategoryDto();
					drawingCategoryDto.setDrawingId(eachDrawingCategory[0].toString());
					drawingCategoryDto.setDrawingName(eachDrawingCategory[1].toString());
					drawingCategoryDto.setImageUrl(eachDrawingCategory[2].toString());
					drawingCategoryDto.setDrawingCategoryName(eachDrawingCategory[3].toString());
					drawingCategoryDto.setClassId(eachDrawingCategory[5].toString());
					drawingCategoryDto.setSectionId(eachDrawingCategory[6].toString());
					drawingCategoryDto.setStudentId(eachDrawingCategory[4].toString());
					drawingCategoryDtoList.add(drawingCategoryDto);
				}
				drawingCategoryNameDtoList.add(drawingCategoryNameDto);
			}
			drawingCategoryDetailsDto.setDrawingCategoryNameDtoList(drawingCategoryNameDtoList);
			drawingCategoryDetailsDto.setCount(drawingCategoryNameDtoList.size());
			drawingCategoryDetailsDto.setMessage("List of Drawings for teacher");
		}
		return drawingCategoryDetailsDto;
	}
}
