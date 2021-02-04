package com.set.controller;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.dto.LibraryBooksListDto;
import com.set.dto.LibraryDto;
import com.set.dto.LibraryRequestBooksListDto;
import com.set.dto.LibraryRequestDto;
import com.set.model.LibraryBooks;
import com.set.model.LibraryDetails;
import com.set.model.LibraryRequest;
import com.set.model.LibraryRequestDetails;
import com.set.model.LibraryRequestId;
import com.set.model.LuMessage;
import com.set.model.User;
import com.set.service.LibraryService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class LibraryController {
	@Autowired
	private LibraryController libraryController;
	private Logger logger = Logger.getLogger("LibraryController.class");
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private LibraryService libraryService;
	
	//Books adding in library
	@RequestMapping(value = "/addLibBooks", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LuMessage addLibBooks(@RequestBody LibraryDto libraryDto, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else { 
			LibraryBooks libraryBooks = new LibraryBooks();
			User user=userService.retrieveFromId(claims.getSubject());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			libraryBooks.setBookId(CommonUtils.generatePrimaryKeyId("lutbl_libbooks"));
			libraryBooks.setBookIsbn(libraryDto.getBookIsbn());
			libraryBooks.setAuthor(libraryDto.getAuthor());
			libraryBooks.setBookPublisher(libraryDto.getBookPublisher());
			libraryBooks.setBookPublishYear(libraryDto.getBookPublishYear());
			libraryBooks.setCopiesAvailable(libraryDto.getCopiesAvailable());
			libraryBooks.setCopyType(libraryDto.getCopyType());
			libraryBooks.setEcopyPath(libraryDto.getEcopyPath());
			libraryBooks.setInsertedBy(user.getUserId());
			libraryBooks.setInsertedTime(timestamp);
			libraryBooks.setNoOfCopies(libraryDto.getNoOfCopies());
			libraryBooks.setPublisher(libraryDto.getPublisher());
			libraryBooks.setSubCategoryId(libraryDto.getSubCategoryId());
			libraryBooks.setTitle(libraryDto.getTitle());		
			libraryBooks.setSubCategoryId(libraryDto.getSubCategoryId());
			
			libraryBooks.setToken(token);
			libraryService.save(libraryBooks);

			message.setMessage("Books Added In Library Successfully");
		}
		return message;
	}
	
	//Update the LibBooks by bookId
	@PutMapping(value = "/editLibBooks", headers = "Accept=application/json")
	public @ResponseBody LuMessage editLibBooks(@RequestBody LibraryDto libraryDto,
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		Claims claims = TokenUtils.verifyToken(token);
		LibraryBooks libraryBooks = new LibraryBooks();
		//String bookId = libraryDto.getBookId();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
			return message;
		} else {			
			User user=userService.retrieveFromId(claims.getSubject());
			
			libraryService.editLibBooks(libraryDto,user.getUserId());
			message.setMessage("Books Updated In Library Successfully");
			return message;
		}
	}
	
	//Delete the LibBooks by bookId	
	@RequestMapping(value = "/deleteLibBooks/{bookId}", method = RequestMethod.PUT)
	public @ResponseBody LuMessage deleteLibBooks(@PathVariable("bookId") String bookId, 
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		LibraryBooks libraryBooks = new LibraryBooks();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			libraryBooks.setBookId(bookId);			
			libraryService.deleteLibBooks(libraryBooks);
			message.setMessage("Books Deleted From Library successfully");
		}
		return message;
	}
	
	//Search a book in library
	@GetMapping(value = "/listLibBooks/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody LibraryDetails listLibBooks(@PathVariable("id") int id,@PathVariable("searchdata") String searchData,
			@RequestHeader String token) {
		LibraryDetails libraryDetails =new LibraryDetails();
		LibraryBooksListDto libraryBooksListDto =new LibraryBooksListDto();
		
		byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
		String encodeString = new String(encodeBytes);
		System.out.println("encodeString: "+encodeString);
		
		byte[] decodedBytes = Base64.getDecoder().decode(searchData);
		System.out.println("decodedBytes:"+decodedBytes);
		String decodedString = new String(decodedBytes);
		System.out.println("decodedString: "+decodedString);
		Claims claims = TokenUtils.verifyToken(token);
		
		//if (tokenService.tokenValidate(token)) {
			libraryDetails.setMessage("Invalid token");
			logger.error("Invalid token");
		//} else {			
			libraryDetails=libraryService.listLibBooks(id,decodedString);	
			System.out.println("title +title");
			System.out.println("author +author");
			System.out.println("bookId +bookId");
		
		return libraryDetails;
	}
	//	LibraryDetails libraryDetails =new LibraryDetails();
	//	LibraryBooksListDto libraryBooksListDto =new LibraryBooksListDto();
		
	//	byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
	//	String encodeString = new String(encodeBytes);
	//	System.out.println("encodeString: "+encodeString);
		
	//	byte[] decodedBytes = Base64.getDecoder().decode(searchData);
	//	System.out.println("decodedBytes:"+decodedBytes);
	//	String decodedString = new String(decodedBytes);
	//	System.out.println("decodedString: "+decodedString);
	//	Claims claims = TokenUtils.verifyToken(token);
	//	
	//	if (tokenService.tokenValidate(token)) {
	//		libraryDetails.setMessage("Invalid token");
	//		logger.error("Invalid token");
	//	} else {			
	//		libraryDetails=libraryService.listLibBooks(id,decodedString);			
	//	}
	//	return libraryDetails;
	// }
	
	//view all books
	@GetMapping(value = "/listLibBooks", headers = "Accept=application/json")
	public @ResponseBody LibraryDetails listLibBooks(@RequestHeader String token) {
		LibraryDetails libraryDetails =new LibraryDetails();
		LibraryBooksListDto libraryBooksListDto =new LibraryBooksListDto();
		
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			libraryDetails.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {		
			libraryDetails=libraryService.listLibBooks();		
		}
		return libraryDetails;
	}
	
//*********Lib Request starting**************************************************************************	
	//Books Request adding in library
	@RequestMapping(value = "/addLibRequest", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LuMessage addLibraryRequest(@RequestBody LibraryRequestDto libraryRequestDto, @RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else { 
			LibraryRequest libraryRequest = new LibraryRequest();
			User user=userService.retrieveFromId(claims.getSubject());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String bookId = libraryRequestDto.getBookId();
			String studentId = libraryRequestDto.getStudentId();
			
			LibraryRequestId libraryRequestId = new LibraryRequestId(bookId, studentId);
			libraryRequest.setLibraryRequestId(libraryRequestId);
			
			libraryRequest.setBookIsbn(libraryRequestDto.getBookIsbn());
			libraryRequest.setDuesCharged(libraryRequestDto.getDuesCharged());
			libraryRequest.setDuesPaid(libraryRequestDto.getDuesPaid());
			libraryRequest.setExpectedReturn(libraryRequestDto.getExpectedReturn());
			libraryRequest.setInsertedBy(user.getUserId());
			libraryRequest.setInsertedTime(timestamp);
			libraryRequest.setIssuedOn(libraryRequestDto.getIssuedOn());
			libraryRequest.setNoOfRenewals(libraryRequestDto.getNoOfRenewals());
			libraryRequest.setRemarks(libraryRequestDto.getRemarks());
			libraryRequest.setRenewedOn(libraryRequestDto.getRenewedOn());
			libraryRequest.setRequestOn(libraryRequestDto.getRequestOn());
			libraryRequest.setReturnOn(libraryRequestDto.getReturnOn());
			libraryRequest.setTitle(libraryRequestDto.getTitle());
										
			libraryService.saveLibraryRequest(libraryRequest);

			message.setMessage("Books Request Added Successfully");
		}
		return message;
	}

	//Update the LibBooks Request by bookId and studentId
	@PutMapping(value = "/editLibRequest", headers = "Accept=application/json")
	public @ResponseBody LuMessage editLibRequest(@RequestBody LibraryRequestDto libraryRequestDto,
			@RequestHeader String token) {
		LuMessage message = new LuMessage();
		
		Claims claims = TokenUtils.verifyToken(token);
		LibraryRequest libraryRequest = new LibraryRequest();
			
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
			return message;
		} else {			
			User user=userService.retrieveFromId(claims.getSubject());		
			libraryService.editLibRequest(libraryRequestDto,user.getUserId());
			message.setMessage("Book Request Updated Successfully");
			return message;
		}
	}
	
	//Delete the LibBooks Request by bookId	
	@RequestMapping(value = "/deleteLibRequest/{bookId}/{studentId}", method = RequestMethod.PUT)
	public @ResponseBody LuMessage deleteLibRequest(@PathVariable("bookId") String bookId,@PathVariable("studentId") String studentId, 
			@RequestHeader String token) {
		LibraryRequest libraryRequest = new LibraryRequest();
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			//libraryBooks.setBookId(bookId);			
			libraryService.deleteLibRequest(bookId,studentId);
			message.setMessage("Books Request Deleted Successfully");
		}
		return message;
	}
	
	//Search based on student id in request details table
	@GetMapping(value = "/listLibBooksRequest/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody LibraryRequestDetails listLibBooksRequest(@PathVariable("id") int id,@PathVariable("searchdata") String searchData,
			@RequestHeader String token) {
		LibraryRequestDetails libraryRequestDetails =new LibraryRequestDetails();
		LibraryRequestBooksListDto libraryRequestBooksListDto =new LibraryRequestBooksListDto();
		
		byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
		String encodeString = new String(encodeBytes);
		System.out.println("encodeString: "+encodeString);
		
		byte[] decodedBytes = Base64.getDecoder().decode(searchData);
		System.out.println("decodedBytes:"+decodedBytes);
		String decodedString = new String(decodedBytes);
		System.out.println("decodedString: "+decodedString);
		Claims claims = TokenUtils.verifyToken(token);
		
		if (tokenService.tokenValidate(token)) {
			libraryRequestDetails.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {
			System.out.println("list of notification*******************************");
			User userdata = userService.retrieveFromId(claims.getSubject());
			libraryRequestDetails=libraryService.listLibBooksRequest(id,decodedString);			
		}
		return libraryRequestDetails;
	}
	
	@GetMapping(value = "/ViewRecentLibBooks/{id}", headers = "Accept=application/json")
	public @ResponseBody LibraryRequestBooksListDto recentLibBooks(@PathVariable("id") String id,
			@RequestHeader String token) {
		LibraryRequestBooksListDto libraryDetails =new LibraryRequestBooksListDto();
		LibraryBooksListDto libraryBooksListDto =new LibraryBooksListDto();
		
//		byte[] encodeBytes = Base64.getEncoder().encode(searchData.getBytes());
//		String encodeString = new String(encodeBytes);
//		System.out.println("encodeString: "+encodeString);
//		
//		byte[] decodedBytes = Base64.getDecoder().decode(searchData);
//		System.out.println("decodedBytes:"+decodedBytes);
//		String decodedString = new String(decodedBytes);
//		System.out.println("decodedString: "+decodedString);
		if (tokenService.tokenValidate(token)) {
			libraryDetails.setMessage("Invalid Token");
			logger.error("Invalid token");
		} else {					
			libraryDetails=libraryService.recentLibBooks(id);			
		}
		return libraryDetails;
	}
	
}