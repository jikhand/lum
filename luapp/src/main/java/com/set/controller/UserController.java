package com.set.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.StringJoiner;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opencsv.CSVReader;
import com.set.model.EmployeeMaster;
import com.set.model.LuMessage;
import com.set.model.Role;
import com.set.model.User;
import com.set.model.UserDetails;
import com.set.model.UserRegistration;
import com.set.service.EmployeeMasterService;
import com.set.service.LogsService;
import com.set.service.StudentEnrollmentService;
import com.set.service.StudentMasterService;
import com.set.service.TokenService;
import com.set.service.UserRegistrationService;
import com.set.service.UserService;
import com.set.utils.CommonUtils;
import com.set.utils.Constant;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmployeeMasterService employeeMasterService;
	@Autowired
	private StudentEnrollmentService studentEnrollmentService;
	@Autowired
	private UserRegistrationService userRegistrationService;
	@Autowired
	private TokenService tokenService;

	@Autowired
	ServletContext context;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Logger logger = Logger.getLogger("UserController.class");
	private static String UPLOADED_FOLDER = Constant.UPLOADED_FOLDER;

	// @RequestMapping ( value = "/saveUserDetails", method = RequestMethod.POST,
	// headers = "Accept=application/json")
	// public @ResponseBody User saveUserDetails (
	// @RequestBody User us,@RequestHeader String token)throws IOException{
	// String filepath="";
	// //logger.info("userRegistration="+userRegistration.toString());
	//// if ( file != null && !file.isEmpty () ){
	//// StringJoiner sj = new StringJoiner(" , ");
	//// byte[] bytes = file.getBytes();
	//// filepath=UPLOADED_FOLDER+file.getOriginalFilename();
	//// Path path = Paths.get(filepath);
	//// Files.write(path, bytes);
	//// sj.add(file.getOriginalFilename());
	//// employeeMaster.setProfilePic(filepath);
	//// }
	// // System.out.println("getFirstName="+userRegistration.getFirstName());
	// Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	// Calendar calendar = Calendar.getInstance();
	// java.util.Date currentDate = calendar.getTime();
	// Date date = new Date(currentDate.getTime());
	//// employeeMaster.setInsertedTime(date);
	//// userRegistration.setUserId("55");
	////// User newUser=new User();
	//// user.setUserId("55");
	//// //EmployeeMaster newemployeeMaster=new EmployeeMaster();
	//// employeeMaster.setEmployeeId("E005");
	//// employeeMaster.setUser(user);
	//// employeeMaster.setRole(role);
	// //userRegistrationService.save(userRegistration);
	// //User user=new User();
	// System.out.println("userInput.toString==="+userInput.getUserdata().getEmailId());
	// userInput.getUserdata().setUserId(CommonUtils.generatePrimaryKeyId("lutbl_passwd"));
	// userService.save(userInput.getUserdata());
	// //employeeMasterService.save(employeeMaster);
	// return userInput.getUserdata();
	// }
	/*
	 * @description Save user details
	 * 
	 * @param @profile data, @user data, @address data
	 * 
	 * @return Tblprofile data
	 *
	 */
	@RequestMapping(value = "/saveUserDetails", method = RequestMethod.POST, headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody User saveUserDetails(@RequestPart(value = "PROFILEDATA", required = false) User user,
			@RequestPart(value = "PROFILEDATA", required = false) UserRegistration userRegistration,
			@RequestPart(value = "selectFile", required = false) MultipartFile file,
			@RequestPart(value = "PROFILEDATA", required = false) EmployeeMaster employeeMaster,
			HttpServletRequest request, @RequestHeader String token) throws IOException {
		// String baseurl=this.getAppUrl(request);
		// baseurl=baseurl+"/assets/upload";
		String filepath = "";
		Claims claims = TokenUtils.verifyToken(token);
		if (!tokenService.tokenValidate(token)) {
//			if (file != null && !file.isEmpty()) {
//				StringJoiner sj = new StringJoiner(" , ");
//				byte[] bytes = file.getBytes();
//				filepath = UPLOADED_FOLDER + System.currentTimeMillis() + file.getOriginalFilename();
//				Path path = Paths.get(filepath);
//				Files.write(path, bytes);
//				sj.add(file.getOriginalFilename());
//				employeeMaster.setProfilePic(filepath);
//			}
			

				if (file != null && !file.isEmpty()) {
					long currentTime=System.currentTimeMillis();
					StringJoiner sj = new StringJoiner(" , ");
					byte[] bytes = file.getBytes();
					filepath = Constant.UPLOADED_FOLDER + currentTime+file.getOriginalFilename();
					Path path = Paths.get(filepath);
					Files.write(path, bytes);
					sj.add(file.getOriginalFilename());
					employeeMaster.setProfilePic(currentTime+file.getOriginalFilename());// setResourcePath(file);
				}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Calendar calendar = Calendar.getInstance();
			// get a java date (java.util.Date) from the Calendar instance.
			// this java date will represent the current date, or "now".
			java.util.Date currentDate = calendar.getTime();
			// now, create a java.sql.Date from the java.util.Date
			java.sql.Date date = new java.sql.Date(currentDate.getTime());
			employeeMaster.setInsertedTime(date);
			String customuserId = CommonUtils.generatePrimaryKeyId("lutbl_user_reg");
			userRegistration.setUserId(customuserId);
			user.setUserId(customuserId);
			user.setCurrentPassword(bCryptPasswordEncoder.encode(user.getCurrentPassword()));
			Role newrole = new Role();
			newrole.setRoleId(userRegistration.getRoleId());
			employeeMaster.setUser(user);
			employeeMaster.setRole(newrole);
			employeeMaster.setInsertedTime(date);
			employeeMaster.setEmployeeId(CommonUtils.generatePrimaryKeyId("lutbl_emp_master"));
			User newuser = userService.retrieveFromId(claims.getSubject());
			employeeMaster.setInsertedBy(newuser.getUserId());
			userRegistrationService.save(userRegistration);
			userService.save(user);
			employeeMasterService.save(employeeMaster);
		} else {
			user.setMessage("Invalide Token");
		}
		return user;
	}

	@PostMapping(value = "/importUser", headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public @ResponseBody LuMessage importUser(
			@RequestPart(value = "subjectCoverpage", required = false) MultipartFile file,
			@RequestHeader String token)
			throws IOException, ParseException {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (claims == null) {
			message.setMessage("Invalid token");
		} else {
			String filepath = "";
			if (file != null && !file.isEmpty()) {
				StringJoiner sj = new StringJoiner(" , ");
				byte[] bytes = file.getBytes();
				filepath = Constant.BOOK_UPLOAD_DIRECTORY + file.getOriginalFilename();
				Path path = Paths.get(filepath);
				Files.write(path, bytes);
				sj.add(file.getOriginalFilename());
			}
			File serverFile = new File(filepath);
			String[] nextLine;
			try {
				try (FileReader fileReader = new FileReader(serverFile);
						CSVReader reader = new CSVReader(fileReader, ';', '\'', 1);) {
					while ((nextLine = reader.readNext()) != null) {
						System.out.println("content : ");
						for (int i = 0; i < nextLine.length; i++) {
							String[] arrOfStr = nextLine[i].split(",");
							System.out.println("arrOfStr"+arrOfStr.toString());
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							Calendar calendar = Calendar.getInstance();
							EmployeeMaster employeeMaster = new EmployeeMaster();
							UserRegistration userRegistration = new UserRegistration();
							User user = new User();
							java.util.Date currentDate = calendar.getTime();
							java.sql.Date date = new java.sql.Date(currentDate.getTime());
							String customuserId = CommonUtils.generatePrimaryKeyId("lutbl_user_reg");
							userRegistration.setUserId(customuserId);
							user.setUserId(customuserId);
							user.setUserOrganisationId(arrOfStr[0]);
							user.setEmailId(arrOfStr[1]);
							// user.setPasswordLastChangeDate(passwordLastChangeDate);
							user.setCurrentPassword(bCryptPasswordEncoder.encode(arrOfStr[2]));
							Role newrole = new Role();
							newrole.setRoleId(studentEnrollmentService.roleIdByName(arrOfStr[3]));
							employeeMaster.setUser(user);
							employeeMaster.setRole(newrole);
							employeeMaster.setAddressLine1(arrOfStr[16]);
							employeeMaster.setAddressLine2(arrOfStr[17]);
							employeeMaster.setAddressLine3(arrOfStr[18]);
							employeeMaster.setAlternatePhone(arrOfStr[9]);
							employeeMaster
									.setCountry(Long.toString(studentEnrollmentService.countryIdByName(arrOfStr[22])));
							employeeMaster.setCrspAdd2(arrOfStr[11]);
							employeeMaster.setCrspAdd3(arrOfStr[12]);
							employeeMaster
									.setCrspCity(Long.toString(studentEnrollmentService.cityIdByName(arrOfStr[13])));
							employeeMaster
									.setCrspState(Long.toString(studentEnrollmentService.stateIdByName(arrOfStr[14])));
							employeeMaster.setCrspZip(arrOfStr[15]);
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							employeeMaster.setDateOfBirth(df.parse(arrOfStr[7]));
							employeeMaster.setDateOfJoining(df.parse(arrOfStr[23]));
//							employeeMaster.setDateOfLeaving(df.parse(arrOfStr[24]));
							employeeMaster.setDeg_pass_year(Integer.parseInt(arrOfStr[29]));
							employeeMaster.setDegreeOfSpecilization(arrOfStr[27]);
							employeeMaster.setFatherContact(arrOfStr[36]);
							employeeMaster.setFatherFirstName(arrOfStr[30]);
							employeeMaster.setFatherLastName(arrOfStr[32]);
							employeeMaster.setFatherMiddleName(arrOfStr[31]);
							employeeMaster.setFirstName(arrOfStr[4]);
							employeeMaster.setGender(arrOfStr[25]);
							employeeMaster.setMiddleName(arrOfStr[5]);
							employeeMaster.setMobilePhone(arrOfStr[8]);
							employeeMaster.setMotheFirstName(arrOfStr[33]);
							employeeMaster.setMotherContact(arrOfStr[37]);
							employeeMaster.setMotherLastname(arrOfStr[35]);
							employeeMaster.setMotherMiddleName(arrOfStr[34]);
							employeeMaster.setLastName(arrOfStr[6]);							
							employeeMaster
									.setPerCity(Long.toString(studentEnrollmentService.cityIdByName(arrOfStr[19])));
							employeeMaster
									.setPerState(Long.toString(studentEnrollmentService.stateIdByName(arrOfStr[20])));
							employeeMaster.setPerZip(arrOfStr[21]);
							// employeeMaster.setProfilePic(profilePic);
							employeeMaster.setQulaificationDegree(arrOfStr[26]);
							employeeMaster.setUnivFrom(arrOfStr[28]);
							employeeMaster.setInsertedTime(date);
							employeeMaster.setEmployeeId(CommonUtils.generatePrimaryKeyId("lutbl_emp_master"));
							userRegistration.setAlterPhone(arrOfStr[9]);
							userRegistration.setCorrespondenceAddress1(arrOfStr[10]);
							userRegistration.setCorrespondenceAddress2(arrOfStr[11]);
							userRegistration.setCorrespondenceAddress3(arrOfStr[12]);
							userRegistration.setCorrespondenceCity(
									Long.toString(studentEnrollmentService.cityIdByName(arrOfStr[13])));
							userRegistration.setCorrespondenceState(
									Long.toString(studentEnrollmentService.stateIdByName(arrOfStr[14])));
							userRegistration.setCorrespondenceZip(arrOfStr[15]);
							userRegistration.setDateOfBirth(df.parse(arrOfStr[7]));
							userRegistration.setEmailId(arrOfStr[1]);
							userRegistration.setFirstName(arrOfStr[4]);
							userRegistration.setLastName(arrOfStr[6]);
							userRegistration.setMiddleName(arrOfStr[5]);
							userRegistration.setMobilePhone(arrOfStr[8]);
							userRegistration.setPermanentZip(arrOfStr[15]);
							userRegistration.setRoleId(studentEnrollmentService.roleIdByName(arrOfStr[3]));
							userRegistration.setUserId(customuserId);
							User newuser = userService.retrieveFromId(claims.getSubject());
							employeeMaster.setInsertedBy(newuser.getUserId());
							boolean isExistSubjectUnit = studentEnrollmentService.IsEmailExist(arrOfStr[1]);
							if (isExistSubjectUnit == true) {
								message.setMessage("Email is already Exist");
							} else {
								userRegistrationService.save(userRegistration);
								userService.save(user);
								employeeMasterService.save(employeeMaster);
								message.setMessage("Inserted Successfully");
							}
						}
					}
				}
			} catch (IOException e) {
				message.setMessage("error while reading csv and put to db : " + e.getMessage());
			}
		}
		return message;
	}
	@RequestMapping(value = "/getAllUserSelect", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<UserRegistration> getAllUserSelect(@RequestHeader String token) throws IOException {
		Claims claims = TokenUtils.verifyToken(token);
		List<UserRegistration> userRegistration = new ArrayList();
		if (!tokenService.tokenValidate(token)) {
			userRegistration = userRegistrationService.getAllUserSelect();
		}
		return userRegistration;
	}
	@RequestMapping(value = "/getAllAdminSelect", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<UserRegistration> getAllAdminSelect(@RequestHeader String token) throws IOException {
		Claims claims = TokenUtils.verifyToken(token);
		List<UserRegistration> userRegistration = new ArrayList();
		if (!tokenService.tokenValidate(token)) {
			userRegistration = userRegistrationService.getAllAdminSelect();
		}
		return userRegistration;
	}

	/*
	 * @description List of Users
	 * 
	 * @param @token
	 * 
	 * @return List of User
	 *
	 */
	@GetMapping(value = "/listOfAllUser/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody UserDetails getListUser(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		UserDetails userDetails = new UserDetails();
		// byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		// String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		if (!tokenService.tokenValidate(token)) {
			logger.info("valide token");
			userDetails = userService.list(id, "");// decodedString
		} else {
			logger.info("invalide token");
			userDetails.setMessage("Invalide Token");
		}
		return userDetails;
	}

	/*
	 * @description User Email Exist
	 * 
	 * @param Email
	 * 
	 * @return Upload status
	 *
	 */
	@GetMapping(value = "/userEmailExist/{emailId}", headers = "Accept=application/json")
	public @ResponseBody boolean userEmailExist(@PathVariable("emailId") String emailId, @RequestHeader String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(emailId);
		String decodedString = new String(decodedBytes);
		// int isexist = userService.retrieveFromEmailId("rakesh@gmail.com");
		int isexist = userService.retrieveFromEmailId(decodedString);
		if (isexist > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * @description User Email Exist
	 * 
	 * @param Email
	 * 
	 * @return Upload status
	 *
	 */
	@GetMapping(value = "/getAllRoll", headers = "Accept=application/json")
	public @ResponseBody List<Role> GetAllRoll(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		List<Role> listRoll;
		if (!tokenService.tokenValidate(token)) {
			listRoll = userService.GetAllRoll();// decodedString
		} else {
			listRoll = null;
		}
		return listRoll;
	}

	@RequestMapping(value = "/getImage/{imagetype}/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imagetype, @PathVariable String imageId, HttpServletRequest request)
			throws IOException {
		try {
		String UPLOADED_FOLDER = Constant.ASSIGNMENT_UPLOAD_DIRECTORY + imageId + "." + imagetype;
		Path path = Paths.get(UPLOADED_FOLDER);
		byte[] data = Files.readAllBytes(path);
		return data;
		}catch (Exception e) {
			String UPLOADED_FOLDER = Constant.ASSIGNMENT_UPLOAD_DIRECTORY+"download.jpeg";
			Path path = Paths.get(UPLOADED_FOLDER);
			byte[] data = Files.readAllBytes(path);
			return data;
		}
	}
	
	@RequestMapping(value = "/getWebImage/{imagetype}/{imageId}")
	@ResponseBody
	public byte[] getWebImage(@PathVariable String imagetype, @PathVariable String imageId, HttpServletRequest request)
			throws IOException {
		String UPLOADED_FOLDER = Constant.ASSIGNMENT_UPLOAD_DIRECTORY + imageId + "." + imagetype;
		Path path = Paths.get(UPLOADED_FOLDER);
		byte[] data = Files.readAllBytes(path);
		return data;
	}

	@RequestMapping(value = "/tgetImage/{imagetype}/{imageId}")
    @ResponseBody
    public byte[] tgetImage(@PathVariable String imagetype,@PathVariable String imageId, HttpServletRequest request) throws IOException  {
    String UPLOADED_FOLDER = Constant.ASSIGNMENT_UPLOAD_DIRECTORY+imageId+"."+imagetype;  
    byte[] data;
    try {
    	Path path = Paths.get(UPLOADED_FOLDER);
        data = Files.readAllBytes(path);
        return data;
	}

	catch (IOException e) {
		e.printStackTrace();
	}
    String name="mytest data";
    data=name.getBytes();
    return data;
    }

	@GetMapping("/getFiles/{doctype}/{filename}")
	public void downloadFile3(@PathVariable String doctype, @PathVariable String filename, HttpServletResponse resonse)
			throws IOException {
		// File file = new
		// File(Constant.ASSIGNMENT_UPLOAD_DIRECTORY+"resourceDocument2018-08-0610:25:40.8.pdf");
		File file = new File(Constant.ASSIGNMENT_UPLOAD_DIRECTORY + filename + "." + doctype);
		resonse.setContentType("application/" + doctype);
		resonse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		System.out.println("ile.getName(): "+file.getName());
		BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = inStrem.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		outStream.flush();
		inStrem.close();
	}

}