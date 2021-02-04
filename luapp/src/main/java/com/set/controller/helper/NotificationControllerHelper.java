/**
 * 
 */
package com.set.controller.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.set.dto.NotificationDto;
import com.set.dto.NotificationListDto;
import com.set.model.Notification;
import com.set.model.NotificationDetails;
import com.set.model.User;
import com.set.model.UserRegistration;
import com.set.model.UserToken;
import com.set.service.ClassSectionMasterService;
import com.set.service.NotificationService;
import com.set.utils.TokenUtils;

/**
 * @author Administrator
 *
 */
@Component
public class NotificationControllerHelper {
	private Logger logger = Logger.getLogger("NotificationControllerHelper.class");
	Date today = new Date();
	Date tomorrow = new Date(today.getTime() + (5000 * 60 * 60 * 24 * 30));

	/**
	 * 
	 */
	public NotificationControllerHelper() {
	}

	/**
	 * Helper method to generate token for the user logged in user
	 * 
	 * @param user
	 * @return String
	 */
	public String genarateUserToken(User user) {
		String jwtToken = TokenUtils.generateToken(user.getEmailId(), tomorrow);
		return jwtToken;
	}

	/**
	 * Helper method to get UserToken object fo the loged in user
	 * 
	 * @param user
	 * @return UserToken
	 */
	public UserToken getUserToken(User user) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		UserToken userToken = new UserToken();
		userToken.setIsdeleted(false);
		userToken.setToken(genarateUserToken(user));
		userToken.setExpireat(tomorrow);
		userToken.setCreatedat(timestamp);
		return userToken;
	}

	// to get and display the searched data.
	public NotificationListDto setNotificationListDto(NotificationDetails notificationDetail,
			ClassSectionMasterService classSectionMasterService, NotificationService notificationService,
			UserRegistration userRegistration, User user) {
		NotificationListDto notificationListDto = new NotificationListDto();
		List<NotificationDto> notificationDtoList = new ArrayList<>();
		NotificationDto notificationDetailsDto = null;

		for (Notification notification : notificationDetail.getNotifications()) {

			notificationDetailsDto = new NotificationDto();
			notificationDetailsDto.setClassId(notification.getClassId());

			notificationDetailsDto.setCreatedByUserId(user.getUserId());

			String userName = userRegistration.getFirstName() + userRegistration.getMiddleName()
					+ userRegistration.getLastName();
			notificationDetailsDto.setStatus((notification.getIsActive() != 1) ? "In Active" : "Active");
			notificationDetailsDto.setNotificationDescription(notification.getDescription());
			notificationDetailsDto.setId(notification.getNotificationId());
			notificationDetailsDto.setNotificationTitle(notification.getTitle());
			notificationDetailsDto.setNotificationType(notification.getNotifyCategory());

			notificationDtoList.add(notificationDetailsDto);
		}
		notificationListDto.setCount(notificationDtoList.size());
		notificationListDto.setNotifications(notificationDtoList);
		notificationListDto.setMessage("Notification List");
		return notificationListDto;

	}

}
