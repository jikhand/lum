package com.set.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.set.controller.helper.NotificationControllerHelper;
import com.set.dto.NotificationDto;
import com.set.dto.NotificationInputDto;
import com.set.dto.NotificationListDto;
import com.set.dto.NotificationListTeacherDto;
import com.set.dto.NotificationTeacherDto;
import com.set.dto.NotifyReadDto;
import com.set.dto.TeacherResourceBankSubjectDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.LibraryBooks;
import com.set.model.LuMessage;
import com.set.model.Notification;
import com.set.model.NotificationDetails;
import com.set.model.NotificationId;
import com.set.model.NotificationListDetails;
import com.set.model.NotifyRead;
import com.set.model.NotifyReadId;
import com.set.model.StudentMaster;
import com.set.model.SubjectResource;
import com.set.model.SubjectResourceId;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.service.ClassSectionMasterService;
import com.set.service.LogsService;
import com.set.service.NotificationService;
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
public class NotificationController {
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private LogsService logsService;
	@Autowired
	private ClassSectionMasterService classSectionMasterService;

	@Autowired
	private NotificationControllerHelper notificationControllerHelper;
	@Autowired
	private StudentMasterService studentMasterService;
	@Autowired
	private UserRegistrationService userRegistrationService;
	private Logger logger = Logger.getLogger("NotificationController.class");

	/*
	 * @description List of Notification with page number and search data
	 * 
	 * @param @Id, @token, @search data
	 * 
	 * @return NotificationDetails
	 *
	 */

	@GetMapping(value = "/ListNotification/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody NotificationListDetails getNotification(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		NotificationDetails notificationDetails = new NotificationDetails();
		NotificationListDetails notificationListDetails = new NotificationListDetails();
		Claims claims = TokenUtils.verifyToken(token);
		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			notificationListDetails.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			UserRegistration userRegistration = userRegistrationService.Login(userdata.getEmailId());
			notificationListDetails = notificationService.list(id, decodedString);
			// notificationListDto =
			// notificationControllerHelper.setNotificationListDto(notificationDetails,
			// classSectionMasterService, notificationService, userRegistration, userdata);
		}
		return notificationListDetails;
	}
	
	@GetMapping(value = "/ListClassNotification/{id}/{searchdata}", headers = "Accept=application/json")
	public @ResponseBody NotificationListDetails listClassNotification(@PathVariable("id") int id,
			@PathVariable("searchdata") String searchdata, @RequestHeader String token) {
		NotificationDetails notificationDetails = new NotificationDetails();
		NotificationListDetails notificationListDetails = new NotificationListDetails();
		Claims claims = TokenUtils.verifyToken(token);
		byte[] decodedBytes = Base64.getDecoder().decode(searchdata);
		String decodedString = new String(decodedBytes);
		if (tokenService.tokenValidate(token)) {
			notificationListDetails.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			List<String> classId=notificationService.getClassIdByTeacherId(userdata.getUserId());
			UserRegistration userRegistration = userRegistrationService.Login(userdata.getEmailId());
			notificationListDetails = notificationService.listClassNotification(id, decodedString,classId);
			// notificationListDto =
			// notificationControllerHelper.setNotificationListDto(notificationDetails,
			// classSectionMasterService, notificationService, userRegistration, userdata);
		}
		return notificationListDetails;
	}

	@SuppressWarnings("unlikely-arg-type")
	@GetMapping(value = "/ListAllNotifications", headers = "Accept=application/json")
	public @ResponseBody NotificationListDto getNotificationAll(@RequestHeader String token) {
		NotificationDetails notificationDetails = new NotificationDetails();
		NotificationListDto notificationListDto = new NotificationListDto();
		List<NotificationDto> notificationDtoList = new ArrayList<>();
		NotificationDto notificationDto = null;
		NotifyRead notifyRead = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			notificationListDto.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			StudentMaster studentMaster = studentMasterService.getStudentMasterById(userdata);
			notificationDetails = notificationService.listAllNotification(studentMaster.getClassId());
			for (Notification eachNotification : notificationDetails.getNotifications()) {
				notificationDto = new NotificationDto();
				if (eachNotification.getClassId().equalsIgnoreCase(studentMaster.getClassId())
						&& eachNotification.getNotifyCategory().equalsIgnoreCase("C")) {
					System.out.println("in Class");
					notificationDto.setId(eachNotification.getNotificationId());
					notificationDto.setClassId(eachNotification.getClassId());
					notificationDto.setNotificationDescription(eachNotification.getDescription());
					notificationDto.setCreatedByUserId(userdata.getUserId());
					notificationDto.setCreatedDate(eachNotification.getInsertedTime());
					notificationDto.setNotificationTitle(eachNotification.getTitle());
					notificationDto.setNotificationType(eachNotification.getNotifyCategory());
				} else if (eachNotification.getNotifyCategory().equalsIgnoreCase("S")
						&& eachNotification.getClassId().isEmpty()) {
					System.out.println("in school");
					notificationDto.setId(eachNotification.getNotificationId());
					notificationDto.setClassId(eachNotification.getClassId());
					notificationDto.setNotificationDescription(eachNotification.getDescription());
					notificationDto.setCreatedByUserId(userdata.getUserId());
					notificationDto.setCreatedDate(eachNotification.getInsertedTime());
					notificationDto.setNotificationTitle(eachNotification.getTitle());
					notificationDto.setNotificationType(eachNotification.getNotifyCategory());
				} else {
					continue;
				}
				// notificationDto.setNotificationType(eachNotification.getNotifyCategory());
				System.out.println("eachNotification.getNotifyCategory=" + eachNotification.getNotifyCategory());
				notificationDto.setStatus(String.valueOf(eachNotification.getIsActive()));
				System.out.println("user id: " + userdata.getUserId());
				System.out.println("noti id: " + eachNotification.getNotificationId());
				notifyRead = notificationService.getNotifyReadById(
						new NotifyReadId(userdata.getUserId(), eachNotification.getNotificationId()));
				// System.out.println("notifyRead: "+notifyRead.getIsRead());
				System.out.println("user: " + userdata.getUserId() + "**" + eachNotification.getNotificationId());

				if (null != notifyRead) {
					System.out.println("notifyRead " + notifyRead);
					if (notifyRead.getIsRead() == 1) {
						System.out.println("read status: " + notifyRead.getIsRead());
						notificationDto.setIsRead(Constant.NOTIFY_READ);
					} else {
						notificationDto.setIsRead(Constant.NOTIFY_UNREAD);
					}
				} else {
					System.out.println("*****");
					notificationDto.setIsRead(Constant.NOTIFY_UNREAD);
				}
				notificationDtoList.add(notificationDto);
			}
			notificationListDto.setMessage("List Of Notifications");
			notificationListDto.setCode("200");
			notificationListDto.setNotifications(notificationDtoList);
			notificationListDto.setCount(notificationDtoList.size());
		}
		if (notificationListDto.getCount() == 0) {
			notificationListDto.setMessage("No Rows Found");
			notificationListDto.setCode("200");
		}

		return notificationListDto;
	}

	@SuppressWarnings("unlikely-arg-type")
	@GetMapping(value = "/ListAllSchoolNotifications", headers = "Accept=application/json")
	public @ResponseBody NotificationListDto getAllSchoolNotifications(@RequestHeader String token) {
		NotificationDetails notificationDetails = new NotificationDetails();
		NotificationListDto notificationListDto = new NotificationListDto();
		List<NotificationDto> notificationDtoList = new ArrayList<>();
		NotificationDto notificationDto = null;
		NotifyRead notifyRead = null;
		Claims claims = TokenUtils.verifyToken(token);
		if (tokenService.tokenValidate(token)) {
			notificationListDto.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			User userdata = userService.retrieveFromId(claims.getSubject());
			StudentMaster studentMaster = studentMasterService.getStudentMasterById(userdata);
			notificationDetails = notificationService.listAllNotificationSchool();

			for (Notification eachNotification : notificationDetails.getNotifications()) {
				notificationDto = new NotificationDto();
				notificationDto.setNotificationType(eachNotification.getNotifyCategory());
				if (eachNotification.getNotifyCategory().equalsIgnoreCase("S")) {
					notificationDto.setId(eachNotification.getNotificationId());
					notificationDto.setClassId(eachNotification.getClassId());
					notificationDto.setNotificationDescription(eachNotification.getDescription());
					notificationDto.setCreatedByUserId(userdata.getUserId());
					notificationDto.setCreatedDate(eachNotification.getInsertedTime());
					notificationDto.setNotificationTitle(eachNotification.getTitle());

					// notificationDto.setNotificationType(eachNotification.getNotifyCategory());
					System.out.println("eachNotification.getNotifyCategory=" + eachNotification.getNotifyCategory());
					notificationDto.setStatus(String.valueOf(eachNotification.getIsActive()));
					System.out.println("user id: " + userdata.getUserId());
					System.out.println("noti id: " + eachNotification.getNotificationId());
					notifyRead = notificationService.getNotifyReadById(
							new NotifyReadId(userdata.getUserId(), eachNotification.getNotificationId()));
					// System.out.println("notifyRead: "+notifyRead.getIsRead());
					System.out.println("user: " + userdata.getUserId() + "**" + eachNotification.getNotificationId());

					if (null != notifyRead) {
						System.out.println("notifyRead " + notifyRead);
						if (notifyRead.getIsRead() == 1) {
							System.out.println("read status: " + notifyRead.getIsRead());
							notificationDto.setIsRead(Constant.NOTIFY_READ);
						} else {
							notificationDto.setIsRead(Constant.NOTIFY_UNREAD);
						}
					} else {
						System.out.println("*****");
						notificationDto.setIsRead(Constant.NOTIFY_UNREAD);
					}
					notificationDtoList.add(notificationDto);
				} else {
					continue;
				}

			}
			notificationListDto.setMessage("List Of Notifications");
			notificationListDto.setCode("200");
			notificationListDto.setNotifications(notificationDtoList);
			notificationListDto.setCount(notificationDtoList.size());

			if (notificationListDto.getCount() == 0) {
				notificationListDto.setMessage("No Rows Found");
				notificationListDto.setCode("200");
			}

		}
		return notificationListDto;
	}

	/*
	 * @description Add Notification
	 * 
	 * @param @token
	 * 
	 * @return Notification
	 *
	 */

	@RequestMapping(value = "/InsertNotification", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody LuMessage addNotification(@RequestBody NotificationInputDto notificationInputDto,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		System.out.println("Date***********" + notificationInputDto.getNotifyDate());
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			Notification notification = new Notification();
			User userdata = userService.retrieveFromId(claims.getSubject());
			notification.setNotificationId(CommonUtils.generatePrimaryKeyId("lutbl_notification"));
			notification.setDescription(notificationInputDto.getNotificationDescription());
			notification.setTitle(notificationInputDto.getNotificationTitle());
			notification.setNotifyCategory(notificationInputDto.getNotificationType());
			notification.setClassId(notificationInputDto.getClassId());
			notification.setUserId(userdata.getUserId());
			notification.setIsActive(1);
			notification.setInsertedBy(userdata.getUserId());
			notification.setExpairyDate(notificationInputDto.getExpiredAt());
			notification.setNotifyDate(notificationInputDto.getNotifyDate());
			notification.setIsDeleted("0");
			notification.setInsertedTime(CommonUtils.getCurrentDateTime());
			notification.setToken(token);

			notificationService.save(notification);

			message.setMessage("Notification added successfully");
		}
		return message;
	}

	/*
	 * @description Add Notification
	 * 
	 * @param @token
	 * 
	 * @return Notification
	 *
	 */

	@PutMapping(value = "/InsertNotification", headers = "Accept=application/json")
	public @ResponseBody LuMessage InsertNotification(@RequestBody NotificationInputDto notificationInputDto,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		System.out.println("Date***********" + notificationInputDto.getNotifyDate());
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			Notification notification = new Notification();
			User userdata = userService.retrieveFromId(claims.getSubject());
			// notification.setNotificationId(CommonUtils.generatePrimaryKeyId("lutbl_notification"));
			notification.setDescription(notificationInputDto.getNotificationDescription());
			notification.setTitle(notificationInputDto.getNotificationTitle());
			notification.setNotifyCategory(notificationInputDto.getNotificationType());
			System.out.println("notificationInputDto=" + notificationInputDto.getNotificationId());
			notification.setNotificationId(notificationInputDto.getNotificationId());
			notification.setClassId(notificationInputDto.getClassId());
			notification.setUserId(userdata.getUserId());
			notification.setIsActive(1);
			notification.setIsDeleted("0");
			notification.setInsertedBy(userdata.getUserId());
			notification.setExpairyDate(notificationInputDto.getExpiredAt());
			notification.setNotifyDate(notificationInputDto.getNotifyDate());

			notification.setInsertedTime(CommonUtils.getCurrentDateTime());
			notification.setToken(token);

			notificationService.update(notification);

			message.setMessage("Notification Updated successfully");
		}
		return message;
	}

	@PostMapping(value = "/AddNotificationRead", headers = "Accept=application/json")
	public @ResponseBody LuMessage addNotificationRead(@RequestBody NotifyReadDto notifyReadDto,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			System.out.println("notifyReadDto: " + notifyReadDto.getNotificationId());
			NotifyRead notifyRead = new NotifyRead();
			notifyRead.setIsRead(1);
			notifyRead.setReadOn(CommonUtils.getCurrentDateTime());
			if (notificationService.isNotificationReadAlreadyInsert(
					new NotifyReadId(notifyReadDto.getUserId(), notifyReadDto.getNotificationId()))) {
				notifyRead.setNotifyReadId(
						new NotifyReadId(notifyReadDto.getUserId(), notifyReadDto.getNotificationId()));
				notificationService.saveNotifyRead(notifyRead);
				message.setMessage("NotificationRead Status added successfully");
			} else {
				message.setMessage("Nothing to add successfully");
			}
		}
		return message;
	}

	@PostMapping(value = "/GetNotifyReadById", headers = "Accept=application/json")
	public @ResponseBody LuMessage addNotificationRead(@RequestBody NotifyReadId notifyReadId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			message.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			NotifyRead notifyRead = notificationService.getNotifyReadById(notifyReadId);
			message.setMessage("NotificationRead Status added successfully");
		}
		return message;
	}

	@GetMapping(value = "/ListAllNotificationRead", headers = "Accept=application/json")
	public @ResponseBody NotificationDetails getNotificationAllRead(@RequestHeader String token) {
		NotificationDetails notificationDetails = new NotificationDetails();

		Claims claims = TokenUtils.verifyToken(token);

		if (tokenService.tokenValidate(token)) {
			notificationDetails.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			notificationDetails = notificationService.listAllNotificationRead();
		}
		return notificationDetails;
	}

	@GetMapping(value = "/ListAllNotificationsTeacher", headers = "Accept=application/json")
	public @ResponseBody NotificationListTeacherDto getNotificationAllTeacher(@RequestHeader String token) {
		NotificationDetails notificationDetails = new NotificationDetails();
		List<Object[]> teacherNotificationDetailsList = null;
		NotificationListTeacherDto notificationListDto = new NotificationListTeacherDto();
		List<NotificationTeacherDto> notificationDtoList = new ArrayList<>();
		NotificationTeacherDto notificationDto = null;
		NotifyRead notifyRead = null;
		Claims claims = TokenUtils.verifyToken(token);
		User userdata = userService.retrieveFromId(claims.getSubject());

		if (tokenService.tokenValidate(token)) {
			notificationListDto.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {

			teacherNotificationDetailsList = notificationService.listAllNotificationTeacher(userdata.getUserId());
			// notificationDetails = notificationService.listAllNotificationTeacher();
			System.out.println("teacherNotificationDetailsList" + teacherNotificationDetailsList);
			for (Object[] eachNotification : teacherNotificationDetailsList) {
				// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				notificationDto = new NotificationTeacherDto();
				notificationDto.setId(eachNotification[0].toString());
				notificationDto.setClassId(eachNotification[1].toString());
				notificationDto.setNotificationDescription(eachNotification[2].toString());
				notificationDto.setCreatedByUserId(userdata.getUserId());
				notificationDto.setCreatedDate((Date) eachNotification[5]);
				notificationDto.setNotificationTitle(eachNotification[9].toString());
				notificationDto.setNotificationType(eachNotification[7].toString());
				notificationDto.setStatus(eachNotification[6].toString());
				notificationDto.setExpiredAt((Date) eachNotification[3]);
				notifyRead = notificationService
						.getNotifyReadById(new NotifyReadId(userdata.getUserId(), eachNotification[0].toString()));

				if (null != notifyRead) {
					System.out.println("notifyRead ****: " + notifyRead.getIsRead());
					if (notifyRead.getIsRead() == 1) {
						notificationDto.setIsRead(Constant.NOTIFY_READ);
					} else {
						System.out.println("notifyRead @@@@@: " + notifyRead.getIsRead());
						notificationDto.setIsRead(Constant.NOTIFY_UNREAD);
					}
				} else {
					// System.out.println("notifyRead $$$$$$: "+notifyRead.getIsRead());
					notificationDto.setIsRead(Constant.NOTIFY_UNREAD);
				}
				notificationDtoList.add(notificationDto);
			}
			notificationListDto.setMessage("List Of Notifications");
			notificationListDto.setCode("200");
			notificationListDto.setNotifications(notificationDtoList);
			notificationListDto.setCount(notificationDtoList.size());
		}
		if (notificationListDto.getCount() == 0) {
			notificationListDto.setMessage("No Rows Found");
			notificationListDto.setCode("200");
		}
		return notificationListDto;
	}

	@PutMapping(value = "/updateNotificationIsActive", headers = "Accept=application/json")
	public @ResponseBody Notification updateNotificationIsActive(@RequestBody Notification notification,
			@RequestHeader String token) {
		if (tokenService.tokenValidate(token)) {
			notification.setMessage("Invalid token");
			return notification;
		} else {
			notificationService.updateNotificationIsActive();
			notification.setMessage("Is Active Updated Successfully");
			return notification;
		}
	}

	// getNotificationById
	@GetMapping(value = "/getNotificationById/{notificationId}", headers = "Accept=application/json")
	public @ResponseBody Notification getNotificationById(@PathVariable("notificationId") String notificationId,
			@RequestHeader String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(notificationId);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);
		Claims claims = TokenUtils.verifyToken(token);
		Notification notification = new Notification();
		LuMessage message = new LuMessage();
		if (tokenService.tokenValidate(token)) {
			notification.setMessage("Invalid token");
			logger.error("Invalid token");
		} else {
			notification = notificationService.getNotificationById(decodedString);
			notification.setMessage("Notification List");
		}
		return notification;
	}
	@RequestMapping(value = "/deleteNotification/{notificationId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody boolean DeleteResourceBank(@PathVariable("notificationId") String notificationId,
			@RequestHeader String token) {
		Claims claims = TokenUtils.verifyToken(token);
		Notification notification = new Notification();
		if (tokenService.tokenValidate(token)) {
			notification.setMessage("Invalid token");
		} else {
			notificationService.deleteNotification(notificationId);//deleteSubjectResource(resourcebank);
		}
		return true;
	}

}
