package com.set.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.StringJoiner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.opencsv.CSVReader;
import com.set.controller.helper.StudentControllerHelper;
import com.set.dto.StudentMasterListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.EmployeeMaster;
import com.set.model.LuMessage;
import com.set.model.Role;
import com.set.model.StudentDetails;
import com.set.model.StudentEnrollment;
import com.set.model.StudentEnrollmentId;
import com.set.model.StudentMaster;
import com.set.model.StudentMasterDetils;
import com.set.model.User;
import com.set.model.UserDetails;
import com.set.model.UserRegistration;
import com.set.service.ClassSectionMasterService;
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
public class StudentController {
	@Autowired
	private UserService userService;
	@Autowired
	private StudentMasterService studentMasterService; 	
	@Autowired
	private UserRegistrationService userRegistrationService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ServletContext context;
	@Autowired
	StudentControllerHelper studentControllerHelper;
	@Autowired
	private StudentEnrollmentService studentEnrollmentService;
	@Autowired
	ClassSectionMasterService classSectionMasterService;

	private Logger logger = Logger.getLogger("StudentController.class");

/*
	 * @description Save user details
	 * @param @profile data, @user data, @address data
	 * @return Tblprofile data
	 *
	 */
    @RequestMapping ( value = "/saveStudentDetails", method = RequestMethod.POST, headers = {"content-type=multipart/mixed","content-type=multipart/form-data"} )
    public @ResponseBody User saveStudentDetails( 
    		@RequestPart ( value = "PROFILEDATA", required = false ) UserRegistration userRegistration,
    		@RequestPart ( value = "PROFILEDATA", required = false ) User user
    		,@RequestPart ( value = "selectFile", required = false ) MultipartFile file,
    		@RequestPart ( value = "PROFILEDATA", required = false ) StudentEnrollmentId studentEnrollmentId,
    		@RequestPart ( value = "PROFILEDATA", required = false ) StudentEnrollment studentEnrollment
    		,@RequestPart ( value = "PROFILEDATA", required = false ) StudentMaster studentMaster
    		,@RequestHeader String token)throws IOException{   
    	String filepath="";     
    	Claims claims = TokenUtils.verifyToken(token);
    	System.out.println("user.getEmailId()="+user.getEmailId());
    	if (!tokenService.tokenValidate(token)) {
		String [] myclsdata = studentMaster.getClassId().split(",");
    		studentMaster.setClassId(myclsdata[0]);
    		studentMaster.setSectionId(myclsdata[1]);
    	if ( file != null && !file.isEmpty () ){
		    	 StringJoiner sj = new StringJoiner(" , ");
		    	 byte[] bytes = file.getBytes();
		    	 filepath=Constant.UPLOADED_FOLDER+System.currentTimeMillis()+file.getOriginalFilename();
		    	 Path path = Paths.get(filepath);
		         Files.write(path, bytes);
		         sj.add(file.getOriginalFilename());
		         studentMaster.setProfilePicture(filepath);
		     	}
			 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			 Calendar calendar = Calendar.getInstance();
				// get a java date (java.util.Date) from the Calendar instance.
				// this java date will represent the current date, or "now".
				java.util.Date currentDate = calendar.getTime();
				// now, create a java.sql.Date from the java.util.Date
				java.sql.Date date = new java.sql.Date(currentDate.getTime());
				studentEnrollment.setInsertedTime(date);
				//String studentId=CommonUtils.generatePrimaryKeyId("lutbl_stdnt_master");
				String customuserId=CommonUtils.generatePrimaryKeyId("lutbl_user_reg");
				StudentEnrollmentId newstudentEnrollmentId=new StudentEnrollmentId(customuserId,myclsdata[0],myclsdata[1]);
				studentMaster.setInsertedTime(date);
				studentEnrollment.setStudentEnrollmentId(newstudentEnrollmentId);
				userRegistration.setUserId(customuserId);
				user.setUserId(customuserId);
				user.setCurrentPassword(bCryptPasswordEncoder.encode(user.getCurrentPassword()));
				Role newrole=new Role();
				newrole.setRoleId(userRegistration.getRoleId());
				studentMaster.setUser(user);
				studentMaster.setRole(newrole);
				studentMaster.setStudentId(customuserId);
				User newuser=userService.retrieveFromId(claims.getSubject());
				studentMaster.setInsertedBy(newuser.getUserId());
				userRegistrationService.save(userRegistration);
				userService.save(user);
				studentMasterService.save(studentMaster);
				studentEnrollmentService.save(studentEnrollment);
    	}else {
    		user.setMessage("Invalide Token");
    	}
    	return user;
    }


    @PostMapping(value = "/importStudent", headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public @ResponseBody LuMessage importStudent(
			@RequestPart ( value = "subjectCoverpage", required = false ) MultipartFile file,
		     @RequestHeader String token) throws IOException, ParseException {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.info("Invalid token");
		} else {
			String filepath="";  
			if ( file != null && !file.isEmpty () ){
		    	 StringJoiner sj = new StringJoiner(" , ");
		    	 byte[] bytes = file.getBytes();
		    	 filepath=Constant.BOOK_UPLOAD_DIRECTORY+file.getOriginalFilename();
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
	                    for(int i=0;i<nextLine.length;i++){	    
	                    	String[] arrOfStr =nextLine[i].split(",");
	                    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
               			    Calendar calendar = Calendar.getInstance();
               			    StudentMaster studentMaster = new StudentMaster();
               			    StudentEnrollment studentEnrollment = new StudentEnrollment();
	                		UserRegistration userRegistration = new UserRegistration();
	                		User user = new User();
               				java.util.Date currentDate = calendar.getTime();
               				java.sql.Date date = new java.sql.Date(currentDate.getTime());
               				studentEnrollment.setInsertedTime(date);
               				String customuserId=CommonUtils.generatePrimaryKeyId("lutbl_user_reg");
               			    ClassSectionMaster classSectionMasterId = studentEnrollmentService.classIdByName(arrOfStr[17],arrOfStr[18]);
               				StudentEnrollmentId newstudentEnrollmentId=new StudentEnrollmentId(customuserId,classSectionMasterId.getClassSectionMasterId().getClassId(),classSectionMasterId.getClassSectionMasterId().getSectionId());
               				studentMaster.setInsertedTime(date);
               				studentEnrollment.setStudentEnrollmentId(newstudentEnrollmentId);
               				userRegistration.setUserId(customuserId);
               				user.setUserId(customuserId);
	                		user.setUserOrganisationId(arrOfStr[0]);
	                		user.setEmailId(arrOfStr[1]);
//	                		user.setPasswordLastChangeDate(passwordLastChangeDate);
	                		user.setCurrentPassword(bCryptPasswordEncoder.encode(arrOfStr[2]));
	                		Role newrole=new Role();
							newrole.setRoleId(studentEnrollmentService.roleIdByName(arrOfStr[3]));
	                		studentMaster.setUser(user);
               				studentMaster.setRole(newrole);
               				studentMaster.setStudentId(customuserId);
	                		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               			    studentMaster.setAdmissionDate(df.parse(arrOfStr[16]));
               			    studentMaster.setAlterPhone(arrOfStr[9]);
               			    studentMaster.setClassId(classSectionMasterId.getClassSectionMasterId().getClassId());
               				studentMaster.setCorrespondenceAddress1(arrOfStr[10]);
               				studentMaster.setCorrespondenceAddress2(arrOfStr[11]);
               				studentMaster.setCorrespondenceAddress3(arrOfStr[12]);
               				studentMaster.setCorrespondenceCity(Long.toString(studentEnrollmentService.cityIdByName(arrOfStr[13])));
               				studentMaster.setCorrespondenceState(Long.toString(studentEnrollmentService.stateIdByName(arrOfStr[14])));
               			    studentMaster.setCorrespondenceZip(Integer.parseInt(arrOfStr[15]));
               			    studentMaster.setDateOfBirth(df.parse(arrOfStr[7]));
               				studentMaster.setEmailId(arrOfStr[1]);
               			    studentMaster.setFatherContact(arrOfStr[22]);
               				studentMaster.setFatherFirstName(arrOfStr[19]);
               				studentMaster.setFatherLastName(arrOfStr[21]);
               				studentMaster.setFatherMiddleName(arrOfStr[20]);
               				studentMaster.setFirstName(arrOfStr[4]);
               				studentMaster.setGender(arrOfStr[27]);
               			    studentMaster.setGuardianContact(arrOfStr[31]);
               				studentMaster.setGuardianFirstName(arrOfStr[28]);
               				studentMaster.setGuardianLastName(arrOfStr[30]);
               				studentMaster.setGuardianMiddleName(arrOfStr[29]);
               				studentMaster.setIs_soft_delete(0);
               				studentMaster.setLastName(arrOfStr[6]);
               				studentMaster.setMiddleName(arrOfStr[5]);
               			    studentMaster.setMobilePhone(arrOfStr[8]);
               			    studentMaster.setMotherContact(arrOfStr[26]);
               				studentMaster.setMotherFirstName(arrOfStr[23]);
               				studentMaster.setMotherLastName(arrOfStr[25]);
               				studentMaster.setMotherMiddleName(arrOfStr[24]);
               				studentMaster.setPermanentAddressLine1(arrOfStr[32]);
               				studentMaster.setPermanentAddressLine2(arrOfStr[33]);
               				studentMaster.setPermanentAddressLine3(arrOfStr[34]);
               				studentMaster.setPermanentCity(Long.toString(studentEnrollmentService.cityIdByName(arrOfStr[35])));
               				studentMaster.setPermanentState(Long.toString(studentEnrollmentService.stateIdByName(arrOfStr[36])));
               			    studentMaster.setPermanentZip(Integer.parseInt(arrOfStr[37]));
               				studentMaster.setPreviousSchool(arrOfStr[38]);
               				studentMaster.setSectionId(classSectionMasterId.getClassSectionMasterId().getSectionId());
               				studentEnrollment.setAcademicReview(arrOfStr[39]);
               			    studentEnrollment.setAcademicYear(Integer.parseInt(arrOfStr[40]));
            				studentEnrollment.setClassTeacher(studentEnrollmentService.userIdByEmail(arrOfStr[41]));
               				studentEnrollment.setGradeObtained(arrOfStr[42]);
               				boolean promoted=false;
               				if(arrOfStr[43]=="1") {
               					studentEnrollment.setPromoted(true);
               				}else {
               					studentEnrollment.setPromoted(false);
               				}
               			    studentEnrollment.setIsSoftDelete(0);
               				studentEnrollment.setStudentEnrollmentId(newstudentEnrollmentId);
	                		userRegistration.setAlterPhone(arrOfStr[9]);
	                		userRegistration.setCorrespondenceAddress1(arrOfStr[10]);
	                		userRegistration.setCorrespondenceAddress2(arrOfStr[11]);
	                		userRegistration.setCorrespondenceAddress3(arrOfStr[12]);
	                		userRegistration.setCorrespondenceCity(Long.toString(studentEnrollmentService.cityIdByName(arrOfStr[13])));
	                		userRegistration.setCorrespondenceState(Long.toString(studentEnrollmentService.stateIdByName(arrOfStr[14])));
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
	                		User loginuser=userService.retrieveFromId(claims.getSubject());
               				studentMaster.setInsertedBy(loginuser.getUserId());
               				studentMaster.setInsertedTime(date);
               				studentEnrollment.setInsertedBy(loginuser.getUserId());
               				studentEnrollment.setInsertedTime(date);
               				boolean isExistSubjectUnit = studentEnrollmentService.IsEmailExist(arrOfStr[1]);
            				if (isExistSubjectUnit == true) {
								message.setMessage("Email is already Exist");
            				} else {            				
            					userRegistrationService.save(userRegistration);
            					userService.save(user);
            					studentMasterService.save(studentMaster);
            					studentEnrollmentService.save(studentEnrollment);
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
    
	@RequestMapping(value = "/getAllStudents/{id}/{searchdata}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody StudentMasterDetils getAllUserSelect(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) throws IOException {
		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		String decodedString = new String(decodedBytes);
		 System.out.println("decodedString="+decodedString);
		StudentMasterDetils studentMasterDetils = new StudentMasterDetils();
		if (!tokenService.tokenValidate(token)) {
			studentMasterDetils = studentMasterService.list(id, decodedString);
		} else {
			studentMasterDetils.setMessage("Invalid token");
		}
		return studentMasterDetils;
	}

	@PostMapping(value = "/ListStudents", headers = "Accept=application/json")
	public @ResponseBody StudentMasterListDto getClassStudents(@RequestHeader String token,
			@RequestBody ClassSectionMasterId classSectionMasterId) throws IOException {
		Claims claims = TokenUtils.verifyToken(token);
		StudentMasterDetils studentMasterDetils = new StudentMasterDetils();
		StudentMasterListDto studentMasterListDto = new StudentMasterListDto();
		int pageNo = 1;
		if (!tokenService.tokenValidate(token)) {
			studentMasterDetils = studentMasterService.getClassStudents(pageNo, classSectionMasterId.getClassId(),
					classSectionMasterId.getSectionId());
			studentMasterListDto = studentControllerHelper.setStudentMasterListDto(studentMasterDetils,
					classSectionMasterService);
		} else {
			studentMasterListDto.setMessage("Invalid token");
			logger.error("Invalid token");
		}
		return studentMasterListDto;
	}
	@RequestMapping(value = "/getAllStudents", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody StudentMasterDetils getAllUserSelect(@RequestHeader String token) throws IOException {
		Claims claims = TokenUtils.verifyToken(token);
		StudentMasterDetils studentMasterDetils = new StudentMasterDetils();
		int pageNo = 1;
		String searchdata = "";
		if (tokenService.tokenValidate(token)) {
			studentMasterDetils = studentMasterService.list(pageNo, searchdata);
		} else {
			studentMasterDetils.setMessage("Invalid token");
		}
		return studentMasterDetils;
	}
	
//	@RequestMapping ( value = "/saveStudentDetails", method = RequestMethod.POST, headers = {"content-type=multipart/mixed","content-type=multipart/form-data"} )
//    public @ResponseBody User saveStudentDetails( 
//    		@RequestPart ( value = "PROFILEDATA", required = false ) UserRegistration userRegistration,
//    		@RequestPart ( value = "PROFILEDATA", required = false ) User user
//    		,@RequestPart ( value = "selectFile", required = false ) MultipartFile file,@RequestPart ( value = "PROFILEDATA", required = false ) StudentEnrollmentId studentEnrollmentId,
//    		@RequestPart ( value = "PROFILEDATA", required = false ) StudentEnrollment studentEnrollment
//    		,@RequestPart ( value = "PROFILEDATA", required = false ) StudentMaster studentMaster
//    		,@RequestHeader String token)throws IOException{   
//    	String filepath="";     
//    	//getClassId();getTeacherId().split(",");
//    	Claims claims = TokenUtils.verifyToken(token);
//    	System.out.println("user.getEmailId()="+user.getEmailId());
//    	if (!tokenService.tokenValidate(token)) {
////    		String [] myclsdata = studentMaster.getClassId().split(",");
////    		myclsdata[0];
//    		String [] myclsdata = studentMaster.getClassId().split(",");
//    		studentMaster.setClassId(myclsdata[0]);
//    		studentMaster.setSectionId(myclsdata[1]);
//    	if ( file != null && !file.isEmpty () ){
//		    	 StringJoiner sj = new StringJoiner(" , ");
//		    	 byte[] bytes = file.getBytes();
//		    	 filepath=Constant.UPLOADED_FOLDER+System.currentTimeMillis()+file.getOriginalFilename();
//		    	 Path path = Paths.get(filepath);
//		         Files.write(path, bytes);
//		         sj.add(file.getOriginalFilename());
//		         studentMaster.setProfilePicture(filepath);
//		     	}
//			 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			 Calendar calendar = Calendar.getInstance();
//				// get a java date (java.util.Date) from the Calendar instance.
//				// this java date will represent the current date, or "now".
//				java.util.Date currentDate = calendar.getTime();
//				// now, create a java.sql.Date from the java.util.Date
//				java.sql.Date date = new java.sql.Date(currentDate.getTime());
//				studentEnrollment.setInsertedTime(date);
//				String studentId=CommonUtils.generatePrimaryKeyId("lutbl_stdnt_master");
//				StudentEnrollmentId newstudentEnrollmentId=new StudentEnrollmentId(studentId,studentEnrollmentId.getClassId(),studentEnrollmentId.getSectionId());
//				studentMaster.setInsertedTime(date);
//				studentEnrollment.setStudentEnrollmentId(newstudentEnrollmentId);
//				String customuserId=CommonUtils.generatePrimaryKeyId("lutbl_user_reg");
//				userRegistration.setUserId(customuserId);
//				user.setUserId(customuserId);
//				user.setCurrentPassword(bCryptPasswordEncoder.encode(user.getCurrentPassword()));
//				Role newrole=new Role();
//				newrole.setRoleId(userRegistration.getRoleId());
//				studentMaster.setUser(user);
//				studentMaster.setRole(newrole);
//				studentMaster.setStudentId(studentId);
//				User newuser=userService.retrieveFromId(claims.getSubject());
//				studentMaster.setInsertedBy(newuser.getUserId());
//				userRegistrationService.save(userRegistration);
//				userService.save(user);
//				studentMasterService.save(studentMaster);
//				studentEnrollmentService.save(studentEnrollment);
//    	}else {
//    		user.setMessage("Invalide Token");
//    	}
//    	return user;
//    		}

}