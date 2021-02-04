package com.set.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.controller.helper.AccountControllerHelper;
import com.set.dto.EmployeeMasterDetailsDto;
import com.set.dto.StudentProfileDto;
import com.set.dto.TeacherProfileDto;
import com.set.dto.StudentMasterDetailsDto;
import com.set.dto.UserProfileDto;
import com.set.dto.UserSubjectDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectDetails;
import com.set.model.EmployeeMaster;
import com.set.model.Mail;
import com.set.model.StudentEnrollment;
import com.set.model.StudentMaster;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.model.UserToken;
import com.set.service.ClassSectionMasterService;
import com.set.service.ClassSubjectService;
import com.set.service.EmployeeMasterService;
import com.set.service.LogsService;
import com.set.service.MailService;
import com.set.service.PasswordTokenService;
import com.set.service.StudentMasterService;
import com.set.service.TokenService;
import com.set.service.UserRegistrationService;
import com.set.service.UserService;
import com.set.utils.Constant;
import com.set.utils.TokenUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
public class AccountController {
	@Autowired
	private UserService userService;
	@Autowired
	private EmployeeMasterService employeeMasterService;
	@Autowired
	private LogsService logsService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private PasswordTokenService passwordTokenService;

	@Autowired
	MailService mailService;

	@Autowired
	private StudentMasterService studentMasterService;
	@Autowired
	private UserRegistrationService userRegistrationService;

	@Autowired
	private ClassSectionMasterService classSectionMasterService;

	@Autowired
	private ClassSubjectService classSubjectService;
	private Logger logger = Logger.getLogger("AccountController.class");

	@PostMapping(value = "/Login", headers = "Accept=application/json")
	public @ResponseBody UserProfileDto Login(@RequestBody User user) {
		UserProfileDto userProfileDto = new UserProfileDto();
		AccountControllerHelper accountControllerHelper = new AccountControllerHelper();
		StudentMasterDetailsDto studentMasterDetailsDto = new StudentMasterDetailsDto();
		EmployeeMaster employeeMaster = new EmployeeMaster();
		StudentMaster studentMaster = new StudentMaster();
		EmployeeMasterDetailsDto employeeMasterDetailsDto = new EmployeeMasterDetailsDto();
		ClassSubjectDetails classSubjectDetails = new ClassSubjectDetails();
		UserSubjectDto userSubjectDto = new UserSubjectDto();
		List<UserSubjectDto> userSubjectDtoList = new ArrayList<>();

		logger.info("Validating User registration");
		UserRegistration userRegistration = userRegistrationService.Login(user.getEmailId());
		if (userRegistration == null) {
			userProfileDto.setMessage("Not a registered user");
			logger.error("User registration has no record");
			userProfileDto = new UserProfileDto();
			return userProfileDto;
		}
		if (userService.UserLogin(user.getEmailId(), user.getCurrentPassword())) {
			User userdata = userService.retrieveFromId(user.getEmailId());
			employeeMaster = employeeMasterService.getEmployeeMasterById(userdata);
			studentMaster = studentMasterService.getStudentMasterById(userdata);
			UserToken userToken = accountControllerHelper.getUserToken(userdata);
			String userFname = "";
			String userMname = "";
			String userLname = "";
			userToken.setUserid(userdata.getUserId());
			if (employeeMaster != null) {
				List<ClassSubject> teacherSubjects = classSubjectService.getTeacherSubjects(userdata.getUserId());
				employeeMasterDetailsDto = accountControllerHelper.setEmployeeMasterDto(employeeMaster, tokenService,
						employeeMasterService, userRegistration, userdata, teacherSubjects, classSectionMasterService);
				userFname = employeeMaster.getFirstName();
				userMname = employeeMaster.getMiddleName();
				userLname = employeeMaster.getLastName();
				userProfileDto.setMessage("Log in successful");
				userProfileDto = new UserProfileDto();
				userProfileDto.setEmployeeMasterDetailsDto(employeeMasterDetailsDto);
				return userProfileDto;
			} else if (studentMaster != null) {
				StudentEnrollment studentEnrollment = new StudentEnrollment();
				ClassSectionMaster classSectionMaster = new ClassSectionMaster();
				ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(studentMaster.getClassId(),
						studentMaster.getSectionId());
				classSectionMaster.setClassSectionMasterId(classSectionMasterId);
				classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
				studentMasterDetailsDto = accountControllerHelper.setStudentMasterDto(tokenService,
						studentMasterService, studentEnrollment, userRegistration, userdata,
						studentMaster, classSectionMaster);
				userFname = studentMaster.getFirstName();
				userMname = studentMaster.getMiddleName();
				userLname = studentMaster.getLastName();
				try {
					classSubjectDetails = classSubjectService.getUserSubjects(userdata.getUserId());
					if (classSubjectDetails.getClassSubject().size() == 0) {
						userSubjectDto = new UserSubjectDto(null, null, null, null, null, null, null);
						userSubjectDtoList.add(userSubjectDto);
					} else {
						for (ClassSubject classSubject : classSubjectDetails.getClassSubject()) {
							userSubjectDto = new UserSubjectDto();
							classSubject = classSubjectService.getElementById(classSubject);
							userSubjectDto.setSubjectCode(classSubject.getClassSubjectId().getSubjectId());
							userSubjectDto.setSubjectName(classSubject.getSubjectName());
							userSubjectDto.setTeacherFirstName(userFname);
							userSubjectDto.setTeacherMiddleName(userMname);
							userSubjectDto.setTeacherLastName(userLname);
							userSubjectDtoList.add(userSubjectDto);
						}
					}
					studentMasterDetailsDto.setSubjectData(userSubjectDtoList);
					userProfileDto = new UserProfileDto();
					userProfileDto.setUserMasterDetailsDto(studentMasterDetailsDto);
					return userProfileDto;
				} catch (Exception csEx) {
					userProfileDto = new UserProfileDto();
					userProfileDto.setUserMasterDetailsDto(studentMasterDetailsDto);
					return userProfileDto;
				}
			}

		} else {
			userProfileDto = new UserProfileDto();
//			userProfileDto.setUserMasterDetailsDto(studentMasterDetailsDto);
			userProfileDto.setMessage("Invalid credential!");
			return userProfileDto;
		}
		return userProfileDto;
	}

	/*
	 * @description Login based on user id and password
	 * 
	 * @param @user
	 * 
	 * @return User
	 *
	 */
	@PostMapping(value = "/StudentLogin", headers = "Accept=application/json")
	public @ResponseBody StudentProfileDto StudentLogin(@RequestBody User user) {
		StudentProfileDto studentProfileDto = new StudentProfileDto();
		AccountControllerHelper accountControllerHelper = new AccountControllerHelper();
		StudentMasterDetailsDto item = new StudentMasterDetailsDto();
		StudentMaster studentMaster = new StudentMaster();
		ClassSubjectDetails classSubjectDetails = new ClassSubjectDetails();
		UserSubjectDto userSubjectDto = new UserSubjectDto();
		List<UserSubjectDto> userSubjectDtoList = new ArrayList<>();

		logger.info("Validating User registration");
		UserRegistration userRegistration = userRegistrationService.StudentLogin(user.getEmailId());
		if (userRegistration == null) {
			studentProfileDto.setCode(Constant.UN_AUTHORISED_ACCESS_CODE);
			studentProfileDto.setMessage("Not a registered user");
			logger.error("User registration has no record");
//			studentProfileDto.setItem(item);
			return studentProfileDto;
		}
		if (userService.UserLogin(user.getEmailId(), user.getCurrentPassword())) {
			studentProfileDto.setCode(Constant.SUCCESS_CODE);
			studentProfileDto.setMessage("Login Successfull");
			logger.info("Login Successfull");
			User userdata = userService.retrieveFromId(user.getEmailId());
			studentMaster = studentMasterService.getStudentMasterById(userdata);
			UserToken userToken = accountControllerHelper.getUserToken(user);
			userToken.setUserid(userdata.getUserId());
			if (studentMaster != null) {

				StudentEnrollment studentEnrollment = new StudentEnrollment();
				ClassSectionMaster classSectionMaster = new ClassSectionMaster();
				ClassSectionMasterId classSectionMasterId = new ClassSectionMasterId(studentMaster.getClassId(),
						studentMaster.getSectionId());
				classSectionMaster.setClassSectionMasterId(classSectionMasterId);
				classSectionMaster = classSectionMasterService.getElementById(classSectionMaster);
				item = accountControllerHelper.setStudentMasterDto(tokenService, studentMasterService,
						studentEnrollment, userRegistration, userdata, studentMaster,
						classSectionMaster);
				try {
					classSubjectDetails = classSubjectService.getUserSubjects(userdata.getUserId());
					if (classSubjectDetails.getClassSubject().size() == 0) {
						userSubjectDto = new UserSubjectDto(null, null, null, null, null, null, null);
						userSubjectDtoList.add(userSubjectDto);
					} else {
						for (ClassSubject eachClassSubject : classSubjectDetails.getClassSubject()) {
							userSubjectDto = new UserSubjectDto();
							userSubjectDto.setSubjectCode(eachClassSubject.getClassSubjectId().getSubjectId());
							userSubjectDto.setSubjectName(eachClassSubject.getSubjectName());
							userSubjectDto.setTeacherFirstName(eachClassSubject.getTeacherName());
							userSubjectDtoList.add(userSubjectDto);
						}
					}
					item.setSubjectData(userSubjectDtoList);
					studentProfileDto.setItem(item);
					return studentProfileDto;
				} catch (Exception csEx) {
					// TODO Error Code need to be set
					studentProfileDto.setMessage(csEx.getLocalizedMessage());
					return studentProfileDto;
				}
			}
		} else {
			studentProfileDto.setCode(Constant.UN_AUTHORISED_ACCESS_CODE);
			studentProfileDto.setMessage("Invalid credential!");
			logger.error("Invalid credential!");
			logger.info("Invalid credential!");
			return studentProfileDto;
		}
		return studentProfileDto;
	}

	@PostMapping(value = "/TeacherLogin", headers = "Accept=application/json")
	public @ResponseBody TeacherProfileDto TeacherLogin(@RequestBody User user) {
		TeacherProfileDto teacherProfileDto = new TeacherProfileDto();
		AccountControllerHelper accountControllerHelper = new AccountControllerHelper();
		EmployeeMaster employeeMaster = new EmployeeMaster();
		EmployeeMasterDetailsDto employeeMasterDetailsDto = new EmployeeMasterDetailsDto();

		logger.info("Validating User registration");
		UserRegistration userRegistration = userRegistrationService.TeacherLogin(user.getEmailId());
		if (userRegistration == null) {
			teacherProfileDto.setCode(Constant.UN_AUTHORISED_ACCESS_CODE);
			teacherProfileDto.setMessage("Not a registered user");
			logger.error("User registration has no record");
			return teacherProfileDto;
		}
		if (userService.UserLogin(user.getEmailId(), user.getCurrentPassword())) {
			
			User userdata = userService.retrieveFromId(user.getEmailId());
			List<ClassSubject> teacherSubjects = classSubjectService.getTeacherSubjects(userdata.getUserId());
			employeeMaster = employeeMasterService.getEmployeeMasterById(userdata);
			UserToken userToken = accountControllerHelper.getUserToken(user);
			userToken.setUserid(userdata.getUserId());
			if (employeeMaster != null) {
				employeeMasterDetailsDto = accountControllerHelper.setEmployeeMasterDto(employeeMaster, tokenService,
						employeeMasterService, userRegistration, userdata, teacherSubjects, classSectionMasterService);
				teacherProfileDto.setCode(Constant.SUCCESS_CODE);
				teacherProfileDto.setMessage("Log in successful");
				teacherProfileDto.setItem(employeeMasterDetailsDto);
				return teacherProfileDto;
			}
		} else {
			teacherProfileDto.setCode(Constant.UN_AUTHORISED_ACCESS_CODE);
//			teacherProfileDto.setItem(employeeMasterDetailsDto);
			teacherProfileDto.setMessage("Invalid credential!");
			return teacherProfileDto;
		}
		return teacherProfileDto;
	}
	/*
	 * @description User form
	 * 
	 * @param N/A
	 * 
	 * @return userform
	 *
	 */

	@GetMapping("/")
	public String userForm() {
		logger.info("userForm =====================================");
		return "<img src='/assets/upload/profile.png' />";
	}

	/*
	 * @description Get all Logs Data based on Page number and search data
	 * 
	 * @param @Id, @search data, @token
	 * 
	 * @return Map
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/logs/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody List<Map<String, String>> getListLogs(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		String decodedString = new String(decodedBytes);
		Claims claims = TokenUtils.verifyToken(token);
		Map<String, String> mhm = new HashMap<String, String>();
		if (tokenService.tokenValidate(token)) {
			logger.info("logs invalid token");
			mhm.put("Message", "Invalid Token");
			List<Map<String, String>> list= new ArrayList<>() ;
			list.add(mhm);
			return  list;
		} else {
			List<Map<String, String>> hm = logsService.list(id, decodedString);
			return hm;
		}
	}

	/*
	 * @description Logout
	 * 
	 * @param @token
	 * 
	 * @return Map
	 *
	 */
	@GetMapping(value = "/Logout", headers = "Accept=application/json")
	public @ResponseBody Map<String, String> Logout(@RequestHeader String token) {
		Map<String, String> hm = new HashMap<String, String>();
		if (tokenService.tokenValidate(token)) {
			hm.put("Message", "Invalid token");
			logger.info("logs invalid token");
		} else {
			Claims claims = TokenUtils.verifyToken(token);
			User userdata = userService.retrieveFromId(claims.getSubject());
			tokenService.tokenLogout(userdata.getUserId());
			hm.put("Message", "Logout  successfully");
		}
		return hm;
	}

	/*
	 * @description Get total logs
	 * 
	 * @param @token
	 * 
	 * @return integer
	 *
	 */
	@GetMapping(value = "/getTotalLogs", headers = "Accept=application/json")
	public @ResponseBody int getTotalLogs(@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		int i = 0;
		if (tokenService.tokenValidate(token)) {
			logger.info("list of logs");
			i = logsService.totalLogs();
		}
		return i;
	}

	/*
	 * @description Reset Password
	 * 
	 * @param @user
	 * 
	 * @return boolean
	 *
	 */

	@PostMapping(value = "/resetPassword", headers = "Accept=application/json")
	public @ResponseBody boolean UserExist(@RequestBody User user) {
		int isexist = userService.retrieveFromEmailId(user.getEmailId());
		logger.info("user.getEmail ===" + user.getEmailId());
		User userId = userService.retrieveFromId(user.getEmailId());
		if (isexist > 0) {
			Mail mail = new Mail();
			String url = "http://localhost:4200/change-password";// getAppUrl(request);
			String token = UUID.randomUUID().toString();
			// String ForgetPasswordLink="<a href="+url+"/"+userId.getId()+"/"+token+"
			// >Reset Password</a>";
			String ForgetPasswordLink = "<a href=" + url + "/" + userId.getUserId() + "/" + token
					+ " >Reset Password</a>";
			String str = "<table style=\"width:100%;padding:20px;\"><tbody><tr><td></td>\n"
					+ "<td style=\"border:1px solid #f0f0f0;\" bgcolor=\"#FFFFFF\">\n"
					+ "<div style=\"margin:0 auto;\">\n" + "<table>\n" + "<tbody>\n"
					+ "<tr><td><hr><h2 style=\"font-size:28px;\">Reset Password</h2><p style=\"margin-bottom:10px;font-weight:normal;font-size:14px;\">Hi,  </p><p style=\"margin-bottom:10px;font-weight:normal;font-size:14px;\">This email was sent automatically by  in response to your request to reset your password.This is done for your protection; only you, the recipient of this email can take the next step in the password recovery process.</p><p style=\"margin-bottom:10px;font-weight:normal;font-size:14px;\"> To reset your password and access your account, click on the link (expires in 30 minutes).</p><table><tbody><tr><td class=\"padding\"></td></tr></tbody></table></div></td><td></td>\n"
					+ "                            </tr></tbody>\n" + "                            </table><p>"
					+ ForgetPasswordLink + "</p><p>Thank You Learning Umbrella Team</p>";
			mail.setMailContent(str);
			mail.setMailFrom("Lusupport@setinfotech.com");
			mail.setMailSubject("Forget Password");
			mail.setMailTo(user.getEmailId());
			passwordTokenService.createPasswordResetTokenForUser(userId, token);
			mailService.sendEmail(mail);
			logger.info("new send mail");
			return true;
		} else {
			return false;
		}
	}

	/*
	 * @description change password
	 * 
	 * @param @Id, @token
	 * 
	 * @return boolean
	 *
	 */

	@RequestMapping(value = "/changePassword/{id}/{token}", method = RequestMethod.GET, headers = "Accept=application/json")
	public boolean showChangePasswordPage(@PathVariable("id") Long id, @PathVariable("token") String token) {
		// boolean result = passwordTokenService.validatePasswordResetToken(id, token);
		boolean result = true;
		return result;
	}

	/*
	 * @description construct reset token email link
	 * 
	 * @param @contextpath, @locale, @token, @user
	 * 
	 * @return string
	 *
	 */

	@SuppressWarnings("unused")
	private String constructResetTokenEmail(final String contextPath, final Locale locale, final String token,
			final User user) {
		final String url = contextPath + "/changePassword/" + user.getUserId() + "/" + token;
		return null;
	}

	/*
	 * @description Get application base Url
	 * 
	 * @param @request
	 * 
	 * @return string
	 *
	 */

	@SuppressWarnings("unused")
	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	/*
	 * @description update password
	 * 
	 * @param @user data
	 * 
	 * @return boolean
	 *
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT, headers = "Accept=application/json")
	public boolean changeUserPassword(@RequestBody User user) {
		try {
			User myuser = userService.getUserById(user.getUserId());
			myuser.setUserId(user.getUserId());
			myuser.setCurrentPassword(user.getCurrentPassword());
			userService.updateUser(myuser);
			passwordTokenService.delete(user.getUserId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping("/user")
	public String user(Principal principal) {
		// Get authenticated user name from Principal
		logger.info(principal.getName());
		return "user";
	}

	@GetMapping("/admin")
	public String admin() {
		// Get authenticated user name from SecurityContext
		SecurityContext context = SecurityContextHolder.getContext();
		logger.info(context.getAuthentication().getName());
		return "admin";
	}
}
