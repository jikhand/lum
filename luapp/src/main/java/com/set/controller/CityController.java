package com.set.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.set.model.NotificationDetails;
import com.set.model.State;
import com.set.model.TblLogs;
import com.set.model.User;
import com.set.service.CityService;
import com.set.service.LogsService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private LogsService logsService;
	@Autowired
	private UserService userservice;
	@Autowired
	private TokenService tokenService;
	private Logger logger = Logger.getLogger("CityController.class");
	
	@PostMapping(value="/addCity",headers="Accept=Application/json")
	public @ResponseBody City addCity(@RequestBody City city,@RequestHeader String token) {
		//System.out.println("getTempStateIdxx="+city.getTempStateId());
		Claims claims=TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			city.setMessage("Invalid token");
			logger.error("Invalid token");
		}else {
			State state=new State();// for reference variable we have to pass forign key as object then object need to set
			//state.setStateId(city.getTempStateId());
			//city.setStateMaster(state);//setting state object
			City newCity= new City();
			newCity.setStateMaster(state);
			//newCity.setIsDeleted(0);
			newCity.setCityName(city.getCityName());
			System.out.println("City service in");
			Timestamp timestamp =new Timestamp(System.currentTimeMillis());
			TblLogs tblLogs=new TblLogs();
			//newCity.setCreatedAt(timestamp);
			cityService.saveCityMaster(newCity);
			tblLogs.setCreatedAt(timestamp);
			tblLogs.setActionElement("City");
			tblLogs.setActionType("insert");
			User user=userservice.retrieveFromId(claims.getSubject());
			tblLogs.setActionPerformedById(user.getUserId());
			logsService.save(tblLogs);
			city.setMessage("Insert successfully");
		}
		return city;
	}
	@PutMapping(value="/updateCity/{id}",headers="Accept=Application/json")
	public @ResponseBody City updateCity(@PathVariable("id") long id,@RequestBody City city,@RequestHeader String token) {
		Claims claims=TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			city.setMessage("Invalid Token");
			logger.error("Invalid token");
			return city;
		}else {
			//System.out.println("city getTempStateId"+city.getTempStateId());
			State state=new State();// for reference variable we have to pass forign key as object then object need to set
			//state.setStateId(city.getTempStateId());
			city.setCityId(id);
			System.out.println("state: "+state);
			City newcity= cityService.getCityMasterDto(city.getCityId());
			System.out.println("newcity: "+newcity);
			newcity.setStateMaster(state);
			newcity.setCityName(city.getCityName());
			cityService.saveCityMaster(newcity);
			TblLogs tblLogs=new TblLogs();
			tblLogs.setActionElement("City");
			tblLogs.setActionType("Update");
			User user=userservice.retrieveFromId(claims.getSubject());
			tblLogs.setActionPerformedById(user.getUserId());
			logsService.save(tblLogs);
			city.setMessage("Updated Successfully");
			return newcity;
		}	
	}
@RequestMapping(value = "/getAllCity/{id}/{searchdata}",headers="Accept=Application/json")
public @ResponseBody CityDetails getAllCity(@PathVariable("id") int id,@PathVariable("searchdata") String searchdata,@RequestHeader String token){
//byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
//String decodedString = new String(decodedBytes);
//Claims claims=TokenUtils.verifyToken(token);
//List<City> city=null;	
//if(claims!=null) {
//	city=cityService.getAllCityList();
//	}
//  return city;
	
	byte[] encodeBytes = Base64.getEncoder().encode(searchdata.getBytes());
	String encodeString = new String(encodeBytes);
	System.out.println("encodeString: "+encodeString);
	
	CityDetails cityDetails =new CityDetails();
	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
	String decodedString = new String(decodedBytes);
	Claims claims = TokenUtils.verifyToken(token);
	
	if (tokenService.tokenValidate(token)) {
		cityDetails.setMessage("Invalid token");
		logger.error("Invalid token");
	} else {	
		cityDetails=cityService.getAllCityList(id,decodedString);
	}
	return cityDetails;
}

@RequestMapping(value = "/getCityById/{id}",headers="Accept=Application/json")
public @ResponseBody City getCityById(@PathVariable("id") long id,@RequestHeader String token ){
	System.out.println("test controller");
	Claims claims=TokenUtils.verifyToken(token);
	City city=null;
	if (tokenService.tokenValidate(token)) {
		city.setMessage("Invalide token");
		logger.error("Invalid token");
	}else {
	city=cityService.getCityMasterDto(id);	
	}
	return city;
}
@RequestMapping(value = "/deleteCity/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
public @ResponseBody boolean DeleteCity(@PathVariable("id") Long id,@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	City city=new City();
	if (tokenService.tokenValidate(token)) {
		city.setMessage("Invalid token");
		logger.error("Invalid token");
	}else {
		city.setCityId(id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		cityService.deleteCity(city.getCityId());
		User userdata = userservice.retrieveFromId(claims.getSubject());
//		TblLogs tblLogs=new TblLogs();
//		tblLogs.setElementId(city.getCityId());
//		tblLogs.setActionElement("Country");
//		tblLogs.setActionType("Delete");
//		tblLogs.setActionPerformedById(userdata.getUserId());
//		tblLogs.setCreatedAt(timestamp);
//		logsService.save(tblLogs);
		city.setMessage("User Updated Successfully");
	}
	return true;
}
@RequestMapping(value = "/cityIsExist/{cityName}/{cityId}", method = RequestMethod.GET, headers = "Accept=application/json")
public @ResponseBody boolean getNotificationById(@PathVariable("cityName") String cityName,@PathVariable("cityId") long cityId,@RequestHeader String token) {
	return cityService.IsExist(cityName,cityId);
}
/*
 * Get All State Name and ID
 * @param N/A
 * @return list of state
 *
 */
@RequestMapping(value="/getAllCitySelect/{stateid}",headers="Accept=application/json")
public @ResponseBody List<Map<String , String>> getAllCitySelect(@PathVariable("stateid") long stateid,@RequestHeader String token){
	Claims claims=TokenUtils.verifyToken(token);
		Map<String, String> hm = new HashMap<String, String>();
	List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
	if (tokenService.tokenValidate(token)) {
		hm.put("token", "Invalid Token");
		arrayList.add(hm);
	}else {
		System.out.println("out claims");
		arrayList =cityService.getAllCitySelect(stateid);
	}
	return arrayList;
}
}