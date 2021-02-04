package com.set.controller;

import java.sql.Timestamp;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.set.model.Assignment;
import com.set.model.AssignmentDetails;
import com.set.model.TblLogs;
import com.set.model.User;
import com.set.service.AssignmentService;
import com.set.service.LogsService;
import com.set.service.UserService;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class AssignmentController {
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private LogsService logsService;
	@Autowired
	private UserService userservice;
	
	@PostMapping(value="/addAssignment",headers="Accept=Application/json")
	public @ResponseBody Assignment addAssignment(@RequestBody Assignment assignment,@RequestHeader String token) {
		
		Claims claims=TokenUtils.verifyToken(token);
		if(claims==null) {
			assignment.setMessage("Invalide token");
		}else {
			User user=new User();
			User newuser=userservice.retrieveFromId(claims.getSubject());
			user.setUserId(newuser.getUserId());
			assignment.setUser(user);
			assignment.setIsDeleted(false);			
			Timestamp timestamp =new Timestamp(System.currentTimeMillis());
			TblLogs tblLogs=new TblLogs();
			assignment.setUser(user);
			assignment.setCreatedAt(timestamp);
			assignmentService.save(assignment);
			tblLogs.setCreatedAt(timestamp);
			tblLogs.setActionElement("Assignment");
			tblLogs.setActionType("insert");
			tblLogs.setActionPerformedById(newuser.getUserId());
			logsService.save(tblLogs);
			assignment.setMessage("Insert successfully");
		}
		return assignment;
	}
	@PutMapping(value="/addAssignment/{id}",headers="Accept=Application/json")
	public @ResponseBody Assignment updateAssignment(@PathVariable("id") String id,@RequestBody Assignment assignment,@RequestHeader String token) {
		Claims claims=TokenUtils.verifyToken(token);
		if(claims==null) {
			assignment.setMessage("Invalide Token");
			return assignment;
		}else {
			System.out.println("in true condition");
			Assignment newassignment = assignmentService.getAssignmentById(id);
			newassignment.setAssignmentId(id);
			User user=new User();
			User newuser=userservice.retrieveFromId(claims.getSubject());
			user.setUserId(newuser.getUserId());
			newassignment.setUser(user);
			newassignment.setAssignmentNumber(assignment.getAssignmentNumber());
			newassignment.setAssignmentDueDate(assignment.getAssignmentDueDate());
			newassignment.setAssignmentType(assignment.getAssignmentType());
			newassignment.setAssignmentIssuedDate(assignment.getAssignmentIssuedDate());
			newassignment.setAssignmentSubject(assignment.getAssignmentSubject());
			newassignment.setReviewComments(assignment.getReviewComments());
			newassignment.setAssignmentMarks(assignment.getAssignmentMarks());
			newassignment.setAssignmentAttachment(assignment.getAssignmentAttachment());
            newassignment.setUpdatedAt(assignment.getUpdatedAt());
			assignmentService.save(newassignment);
			TblLogs tblLogs=new TblLogs();
			tblLogs.setActionElement("Assignment");
			tblLogs.setActionType("Update");
			tblLogs.setActionPerformedById(newuser.getUserId());
			logsService.save(tblLogs);
			assignment.setMessage("Updated Successfully");
			return newassignment;
		}	
	}
@RequestMapping(value = "/getAllAssignment/{id}/{searchdata}",headers="Accept=Application/json")
public @ResponseBody AssignmentDetails getAllAssignment(@PathVariable("id") int id,@PathVariable("searchdata") String searchdata,@RequestHeader String token){
	AssignmentDetails assignmentDetails =new AssignmentDetails();
	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
	String decodedString = new String(decodedBytes);
	System.out.println("AssignmentType +AssignmentType");
	System.out.println("AssignmentNumber +AssignmentNumber");
	Claims claims = TokenUtils.verifyToken(token);
	if (claims != null) {
		assignmentDetails= assignmentService.getAllAssignment(id,decodedString);
	}else {
		assignmentDetails.setMessage("Invalide Token");
	}
	return assignmentDetails;
}

@RequestMapping(value = "/getAssignmentById/{id}",headers="Accept=Application/json")
public @ResponseBody Assignment getAssignmentById(@PathVariable("id") String id,@RequestHeader String token ){
	System.out.println("test controller");
	Claims claims=TokenUtils.verifyToken(token);
	Assignment assignment=new Assignment();
	if(claims==null) {
		assignment.setMessage("Invalide token");
	}else {
		assignment= assignmentService.getAssignmentById(id);	
	}
	return assignment;
}
@RequestMapping(value = "/deleteAssignment/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
public @ResponseBody boolean DeleteAssignment(@PathVariable("id") String id,@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	Assignment assignment=new Assignment();
	if (claims == null) {
		assignment.setMessage("Invalid token");
	}else {
		assignment.setAssignmentId(id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		assignmentService.deleteAssignment(assignment.getAssignmentId());;
		User userdata = userservice.retrieveFromId(claims.getSubject());
		TblLogs tblLogs=new TblLogs();
		tblLogs.setElementId(String.valueOf(assignment.getAssignmentId()));
		tblLogs.setActionElement("");
		tblLogs.setActionType("Delete");
		tblLogs.setActionPerformedById(userdata.getUserId());
		tblLogs.setCreatedAt(timestamp);
		logsService.save(tblLogs);
		assignment.setMessage("User Updated Successfully");
	}
	return true;
}
@RequestMapping(value = "/assignmentIsExist/{assignmentName}/{assignmentId}", method = RequestMethod.GET, headers = "Accept=application/json")
public @ResponseBody boolean getNotificationById(@PathVariable("assignmentName") String assignmentName,@PathVariable("assignmentId") long assignmentId,@RequestHeader String token) {
	return assignmentService.IsExist(assignmentName,assignmentId);
}


}