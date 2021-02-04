package com.set.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.model.ClassSectionMaster;
import com.set.model.Country;
import com.set.model.CountryDetails;
import com.set.model.StateDetails;
import com.set.model.TblLogs;
import com.set.model.User;
import com.set.service.CountryService;
import com.set.service.LogsService;
import com.set.service.TokenService;
import com.set.service.UserService;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class CountryController {
@Autowired
CountryService countryService;
@Autowired
private UserService userService;
@Autowired
private LogsService logsService;
@Autowired
private TokenService tokenService;
private Logger logger = Logger.getLogger("CountryController.class");

/*
 * @description Add country
 * @param @Country
 * @return Country Data
 *
 */
@RequestMapping(value = "/addcountry", method = RequestMethod.POST, headers = "Accept=application/json")
public @ResponseBody Country AddCountry(@RequestBody Country country, @RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	// if claims == null means token is invalid
	if (tokenService.tokenValidate(token)) {
		//country.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		// valid token
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//country.setCreatedAt(timestamp);
		//country.setIsDeleted(0);
		User userdata = userService.retrieveFromId(claims.getSubject());
//		TblLogs tblLogs=new TblLogs();
//		tblLogs.setElementId(country.getCountryId());
//		tblLogs.setActionElement("Country");
//		tblLogs.setActionType("Insert");
//		tblLogs.setActionPerformedById(userdata.getUserId());
//		tblLogs.setCreatedAt(timestamp);
//		logsService.save(tblLogs);
		countryService.saveCountryData(country);
		//country.setMessage("Country added successfully");
	}
	return country;
}

/*
 * @description Update country by Id
 * @param @Id
 * @return country
 *
 */
@RequestMapping(value = "/UpdateCountry/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
public @ResponseBody Country UpdateCountry(@PathVariable("id") Long id, @RequestBody Country country,@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		//country.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {
		country.setCountryId(id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		countryService.editCountryData(country);
		User userdata = userService.retrieveFromId(claims.getSubject());
//		TblLogs tblLogs=new TblLogs();
//		tblLogs.setElementId(country.getCountryId());
//		tblLogs.setActionElement("Country");
//		tblLogs.setActionType("Update");
//		tblLogs.setActionPerformedById(userdata.getUserId());
//		tblLogs.setCreatedAt(timestamp);
//		logsService.save(tblLogs);
		//country.setMessage("Country Updated Successfully");
	}
	return country;
}

/*
 * @description Get All Country by Id
 * @param @Id
 * @return List of Country
 *
 */
@RequestMapping(value = "/getAllCountry/{id}/{searchdata}", method = RequestMethod.GET, headers = "Accept=application/json")
public @ResponseBody CountryDetails getAllCountry(@PathVariable("id") int id,@PathVariable("searchdata") String searchdata,@RequestHeader String token) {
//	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
//	String decodedString = new String(decodedBytes);
//	Claims claims = TokenUtils.verifyToken(token);
//	List<Country> country=null;
//	if (claims != null) {
//		country=countryService.getAllCountry();
//	}
//	return country;
	byte[] encodeBytes = Base64.getEncoder().encode(searchdata.getBytes());
	String encodeString = new String(encodeBytes);
	System.out.println("encodeString: "+encodeString);
	
	byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
	String decodedString = new String(decodedBytes);
	Claims claims=TokenUtils.verifyToken(token);
	CountryDetails countryDetails=new CountryDetails();
	
	if (tokenService.tokenValidate(token)) {
		countryDetails.setMessage("Invalid token");
		logger.info("Invalid token");
	} else {
		countryDetails=countryService.getAllCountry(id, decodedString);
	}
	return countryDetails;
}

/*
 * @description Get All Country Select
 * @param N/A
 * @return List of Country
 *
 */

@RequestMapping(value = "/getAllCountrySelect", method = RequestMethod.GET, headers = "Accept=application/json")
public @ResponseBody List<Country> getAllCountrySelect() {
//	Claims claims = TokenUtils.verifyToken(token);
	List<Country> country=new ArrayList<>();
	
//	if (tokenService.tokenValidate(token)) {
//		Country csm = new Country();
//		csm.setMessage("Invalid token");
//		country.add(csm);
//		logger.info("Invalid token");
//	} else {
//		country=countryService.getAllCountrySelect();
//	}
	country=countryService.getAllCountrySelect();
	return country;
}

/*
 * @description Get Country by id
 * @param @Id
 * @return Country Data
 *
 */

@RequestMapping(value = "/getCountryById/{id}")
public @ResponseBody Country getCountryById(@PathVariable("id") long id,@RequestHeader String token) {
	Country country=new Country();
	Claims claims=TokenUtils.verifyToken(token);
	if (tokenService.tokenValidate(token)) {
		//country.setMessage("Invalide token");
		logger.info("Invalid token");
	}else {
		country=countryService.getCountryData(id);
		//country.setMessage("Country details");
	}
	return country;
}

/*
 * @description Delete Country
 * @param @Id
 * @return boolean
 *
 */

@RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
public @ResponseBody boolean DeleteCountry(@PathVariable("id") Long id,@RequestHeader String token) {
	Claims claims = TokenUtils.verifyToken(token);
	Country country=new Country();
	if (tokenService.tokenValidate(token)) {
		//country.setMessage("Invalid token");
		logger.info("Invalid token");
	}else {
		country.setCountryId(id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		countryService.deleteCountryData(country.getCountryId());
		User userdata = userService.retrieveFromId(claims.getSubject());
//		TblLogs tblLogs=new TblLogs();
//		tblLogs.setElementId(country.getCountryId());
//		tblLogs.setActionElement("Country");
//		tblLogs.setActionType("Delete");
//		tblLogs.setActionPerformedById(userdata.getUserId());
//		tblLogs.setCreatedAt(timestamp);
//		logsService.save(tblLogs);
		//country.setMessage("User Updated Successfully");
	}
	return true;
}

/*
 * @description whether country already exist
 * @param @country name
 * @return boolean
 *
 */
@RequestMapping(value = "/countryIsExist/{countryName}", method = RequestMethod.GET, headers = "Accept=application/json")
public @ResponseBody boolean getNotificationById(@PathVariable("countryName") String countryName,@RequestHeader String token) {
	return countryService.IsExist(countryName);
}

}