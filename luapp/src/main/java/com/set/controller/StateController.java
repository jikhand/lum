package com.set.controller;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
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

import com.set.model.City;
import com.set.model.CityDetails;
import com.set.model.Country;
import com.set.model.State;
import com.set.model.StateDetails;
import com.set.model.TblLogs;
import com.set.model.User;
import com.set.service.LogsService;
import com.set.service.StateService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class StateController {
@Autowired
private StateService stateService;
@Autowired
private UserService userService;
@Autowired
private LogsService logsService;
@Autowired
private TokenService tokenService;
private Logger logger = Logger.getLogger("StateController.class");
/*
 * @description Add State
 * @param N/A
 * @return State
 *
 */
@PostMapping(value ="/addState",headers="Accept=application/json")
public @ResponseBody State addState(@RequestBody State state, @RequestHeader String token) {
Claims claims=TokenUtils.verifyToken(token);
if (tokenService.tokenValidate(token)) {
	state.setMessage("Invalide token");	
	logger.info("Invalid token");
}else {
	Country country=new Country();
	System.out.println("state get temp country id"+state.getTempCountryId());
	country.setCountryId(state.getTempCountryId());
	State newState=new State();
	newState.setCountry(country);
	newState.setStateName(state.getStateName());
	newState.setIsDeleted(0);
	Timestamp timestamp=new Timestamp(System.currentTimeMillis());
	newState.setCreatedAt(timestamp);
	stateService.save(newState);
	TblLogs tblLogs=new TblLogs();
	User user =userService.retrieveFromId(claims.getSubject());
	tblLogs.setActionPerformedById(user.getUserId());
	tblLogs.setActionElement("State");
	tblLogs.setActionType("Insert");	
	tblLogs.setCreatedAt(timestamp);
	logsService.save(tblLogs);
	state.setMessage("State added succssfully");
}
return state;
}

/*
 * @description Update State based on State ID 
 * @param @Id
 * @return State
 *
 */
@PutMapping(value="/updateState/{id}",headers="Accept=application/json")
public @ResponseBody State updateState(@PathVariable("id") Long id,@RequestBody State state,@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	state.setStateId(id);
	if (tokenService.tokenValidate(token)) {
		state.setMessage("Invalide Token");
		logger.info("Invalid token");
	}else {
		Country country=new Country();
		country.setCountryId(state.getTempCountryId());
		state.setStateId(id);
		State newState=stateService.getStateByStateId(id);
		newState.setCountry(country);
		newState.setStateName(state.getStateName());
		newState.setIsDeleted(0);
		stateService.save(newState);
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		TblLogs tblLogs=new TblLogs();
		User user =userService.retrieveFromId(claims.getSubject());
		tblLogs.setActionPerformedById(user.getUserId());
		tblLogs.setActionElement("State");
		tblLogs.setActionType("Update");	
		tblLogs.setCreatedAt(timestamp);
		logsService.save(tblLogs);
		state.setMessage("State Updated successfully");
	}
	return state;
}

/*
 * Get all State by Page number and Search data
 * @param @ID, @Search data
 * @return State Detail
 *
 */

@RequestMapping(value="/getAllState/{id}/{searchdata}",headers="Accept=application/json")
public @ResponseBody StateDetails getAllState(@PathVariable("id") int id,@PathVariable("searchdata") String searchdata ,@RequestHeader String token){
	
	byte[] encodeBytes = Base64.getEncoder().encode(searchdata.getBytes());
	String encodeString = new String(encodeBytes);
	System.out.println("encodeString: "+encodeString);

	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
	String decodedString = new String(decodedBytes);
	Claims claims=TokenUtils.verifyToken(token);
	StateDetails stateDetails=new StateDetails();
	
	if (tokenService.tokenValidate(token)) {
		stateDetails.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		stateDetails=stateService.getAllState(id, decodedString);
	}
	return stateDetails;
}

/*
 * Get All State Name and ID
 * @param NA
 * @return list of state
 *
 */
@RequestMapping(value="/getAllStateSelect/{countryId}",headers="Accept=application/json")
public @ResponseBody List<State> getAllStateSelect(@PathVariable("countryId") long countryId,@RequestHeader String token){
	Claims claims=TokenUtils.verifyToken(token);
	State ostate=new State();
	if (tokenService.tokenValidate(token)) {
		System.out.println("null claims");
		ostate.setMessage("Invalid Token");
		logger.info("Invalid token");
		return (List<State>) ostate;
	}else {
		System.out.println("out claims");
		List<State> state=stateService.getAllStateSelect(countryId);
		return state;
	}
}

/*
 * Get State by ID
 * @param StateId
 * @return State object
 *
 */

@RequestMapping(value= "/getStateById/{id}",headers="Accept=application/json")
public @ResponseBody State getStateById(@PathVariable("id") long id,@RequestHeader String token) {
	Claims claims=TokenUtils.verifyToken(token);
	State state=new State();
	state.setStateId(id);
	if (tokenService.tokenValidate(token)) {
		state.setMessage("invalide token");
		logger.info("Invalid token");
	}else {
		state=stateService.getStateByStateId(state.getStateId());
		state.setMessage("State details");
	}
	return state;
}

/*
 * (Method description)
 * @param
 * @return
 *
 */
@RequestMapping(value = "/deleteState/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
public @ResponseBody boolean DeleteState(@PathVariable("id") Long id,@RequestHeader String token) {
	System.out.println("DeleteState: ******************************************");
	Claims claims = TokenUtils.verifyToken(token);
	State state=new State();
	if (tokenService.tokenValidate(token)) {
		state.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {
		System.out.println("id: "+id);
		state.setStateId(id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		stateService.deleteState(state.getStateId());
		User userdata = userService.retrieveFromId(claims.getSubject());
//		TblLogs tblLogs=new TblLogs();
//		tblLogs.setElementId(state.getStateId());
//		tblLogs.setActionElement("Country");
//		tblLogs.setActionType("Delete");
//		tblLogs.setActionPerformedById(userdata.getUserId());
//		tblLogs.setCreatedAt(timestamp);
//		logsService.save(tblLogs);
		state.setMessage("State deleted Successfully");
	}
	return true;
}

/*
 * Whether State Name is exist
 * @param StateName,param Country Id
 * @return boolean
 *
 */
@RequestMapping(value = "/stateIsExist/{stateName}/{countryId}", method = RequestMethod.GET, headers = "Accept=application/json")
public @ResponseBody boolean getNotificationById(@PathVariable("stateName") String stateName,@PathVariable("countryId") long countryId,
		@RequestHeader String token) {
	return stateService.IsExist(stateName,countryId);
}
}