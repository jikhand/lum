package com.set.controller;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.dto.TextBookDto;
import com.set.dto.TextBookListDto;
import com.set.model.LuMessage;
import com.set.model.SportsActivityDetails;
import com.set.model.TextBookDetails;
import com.set.model.TextBookMaster;
import com.set.model.TextBookMasterId;
import com.set.service.ClassSectionMasterService;
import com.set.service.SportsActivityService;
import com.set.service.TextBookService;
import com.set.service.TokenService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class TextBookController {

	@Autowired
	private TextBookService textBookService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	ServletContext context;
	@Autowired
	private SportsActivityService sportsActivityService;
	@Autowired
	ClassSectionMasterService classSectionMasterService;

	private Logger logger = Logger.getLogger("TextBookMaster.class");

	//Insert into book table
	@RequestMapping(value = "/addTextBook", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LuMessage addTextBook(@RequestBody TextBookDto textBookDto, @RequestHeader String token) {
		
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (claims == null) {
			message.setMessage("Invalid token");
		} else { 
			
			TextBookMaster textBookMaster = new TextBookMaster();
			
			String bookId = CommonUtils.generatePrimaryKeyId("lutbl_book_master");		
			String subjectId = textBookDto.getTemp();	
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			TextBookMasterId textBookMasterId = new TextBookMasterId(bookId,subjectId);	
			
			textBookMaster.setStockBorrowQuantity(textBookDto.getStockBorrowQuantity());
			textBookMaster.setBookName(textBookDto.getBookName());
			textBookMaster.setSubCatogeryName(textBookDto.getSubCatogeryName());
			textBookMaster.setCatogeryName(textBookDto.getCatogeryName());
			textBookMaster.setBookAuthor(textBookDto.getBookAuthor());
			textBookMaster.setBookIsbn(textBookDto.getBookIsbn());
			textBookMaster.setBookPublisher(textBookDto.getBookPublisher());
			textBookMaster.setBookPublishYear(textBookDto.getBookPublishYear());
			textBookMaster.setSubCategoryId(textBookDto.getSubCategoryId());
			textBookMaster.setBookType(textBookDto.getBookType());
			textBookMaster.setBookLength(textBookDto.getBookLength());
			textBookMaster.setBookCoverImage(textBookDto.getBookCoverImage());
			textBookMaster.setBookBarcodeImage(textBookDto.getBookBarcodeImage());
			textBookMaster.setIsDeleted(0);
			textBookMaster.setCreatedAt(timestamp);
			textBookMaster.setCategoryId(textBookDto.getCategoryId());
			textBookMaster.setStatus(textBookDto.getStatus());
			textBookMaster.setPdfUrl(textBookDto.getPdfUrl());
			logger.info("book name: "+textBookMaster.getBookName());
			
			textBookMaster.setTextBookMasterId(textBookMasterId);
			
			textBookService.save(textBookMaster);

			message.setMessage("TextBook added successfully");
		}
		return message;
	}	
	//Get book details based on class
	@GetMapping(value = "/getAllTextBooksByClassId/{classId}", headers = "Accept=application/json")
	public @ResponseBody TextBookListDto getAllTextBooksByClassId(@PathVariable("classId") String classId,
			@RequestHeader String token) {
		TextBookListDto bookListDto =new TextBookListDto();	

		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			bookListDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {		
			bookListDto=textBookService.getAllTextBooksByClassId(classId);			
		}
		if (bookListDto.getCount() == 0) {
			bookListDto.setMessage("No Rows Found");
			bookListDto.setCode("200");
		}
		return bookListDto;
	}
	//Get book details based on class and section
	@GetMapping(value = "/getAllTextBooksByClassIdSectionId/{classId}/{sectionId}", headers = "Accept=application/json")
	public @ResponseBody TextBookListDto getAllTextBooksByClassIdSectionId(@PathVariable("classId") String classId, 
			@PathVariable("sectionId") String sectionId, @RequestHeader String token) {		
		TextBookListDto bookListDto =new TextBookListDto();	

		Claims claims = TokenUtils.verifyToken(token);
		
		if (tokenService.tokenValidate(token)) {
			bookListDto.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {			
			bookListDto=textBookService.getAllTextBooksByClassIdSectionId(classId, sectionId);				
		}
		if (bookListDto.getCount() == 0) {
			bookListDto.setMessage("No Rows Found");
			bookListDto.setCode("200");
		}
		return bookListDto;
	}
	//Class selection - drop down
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getClassListSelectForBook", headers = "Accept=application/json")
	public @ResponseBody Map<String, String> getClassListSelect(@RequestHeader String token) {
		
		Claims claims = TokenUtils.verifyToken(token);
		Map<String, String> classList = new HashMap<String, String>();
		if (claims == null) {
			logger.info("logs invalid token");
			classList.put("Message", "Invalid Token");
			return (Map<String, String>) classList;
		} else {
			Map<String, String> hm = sportsActivityService.getClassListSelect();
			return hm;
		}
	}
	
	//BaSed on the class selection - section drop down 
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getSectionListSelectForBook/{classId}", headers = "Accept=application/json")
	public @ResponseBody Map<String, String> getSectionListSelect(@PathVariable("classId") String classId,
			@RequestHeader String token) {
		
		Claims claims = TokenUtils.verifyToken(token);
		Map<String, String> sectionList = new HashMap<String, String>();
		if (claims == null) {
			logger.info("logs invalid token");
			sectionList.put("Message", "Invalid Token");
			return (Map<String, String>) sectionList;
		} else {
			Map<String, String> hm = sportsActivityService.getSectionListSelect(classId);
			return hm;
		}
	}
	
	// TextBook Search 
	@GetMapping(value = "/TextBookSearch/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody TextBookDetails textBookSearch(@PathVariable("id") int id,@PathVariable("searchdata") String searchData,
			@RequestHeader String token) {		
		TextBookDetails textBookDetails =new TextBookDetails();		
		
//		byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
//		String encodeString = new String(encodeBytes);
//		System.out.println("encodeString: "+encodeString);
		
		byte[] decodedBytes = Base64.getDecoder().decode(searchData);		
		String decodedString = new String(decodedBytes);		
	//	Claims claims = TokenUtils.verifyToken(token);
	//	try {
	//		if (claims != null) {			
				textBookDetails=textBookService.textBookSearch(id,decodedString);			
	//		}else {
	//			textBookDetails.setMessage("Invalid Token");
	//		}
	//	} catch (Exception e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		return textBookDetails;
	}
	
	//Get book details based on teacher id
		@GetMapping(value = "/getAllTextBooksByTeacherId/{teacherId}", headers = "Accept=application/json")
		public @ResponseBody TextBookListDto getAllTextBooksByTeacherId(@PathVariable("teacherId") String teacherId,@RequestHeader String token) {		
			TextBookListDto bookListDto =new TextBookListDto();	

			Claims claims = TokenUtils.verifyToken(token);
			
			if (tokenService.tokenValidate(token)) {
				bookListDto.setMessage("Invalid token");
				logger.info("Invalid token");
			} else {			
				bookListDto=textBookService.getAllTextBooksByTeacherId(teacherId);				
			}
			if (bookListDto.getCount() == 0) {
				bookListDto.setMessage("No Rows Found");
				bookListDto.setCode("200");
			}
			return bookListDto;
		}	
}