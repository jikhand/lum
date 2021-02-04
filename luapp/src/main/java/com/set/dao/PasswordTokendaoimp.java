package com.set.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.PasswordResetToken;
import com.set.model.User;
@Repository
public class PasswordTokendaoimp implements PasswordTokendao {

	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("PasswordTokendaoimp.class"); 
	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken();
		myToken.setUser(user);
		myToken.setToken(token);
		logger.info("token ==="+token);
		//get current Time
		long currentTime = System.currentTimeMillis();
		//now add half an hour, 1 800 000 miliseconds = 30 minutes
		long halfAnHourLater = currentTime + 1800000;
		Timestamp timestamp = new Timestamp(halfAnHourLater);
		myToken.setExpiryDate(timestamp);
		logger.info("token is created");
		sessionFactory.getCurrentSession().save(myToken);
	}
	@Override
	public boolean validatePasswordResetToken(String id, String token) {
		User u=new User();
		u.setUserId(id);
		Criteria cr= sessionFactory.getCurrentSession().createCriteria(PasswordResetToken.class);
			 cr.add(Restrictions.eq("user",u)).
			 add(Restrictions.eq("token",token));
			 PasswordResetToken prt=(PasswordResetToken) cr.uniqueResult();
			 List results = cr.list();
			if(results.size()>0) {
				Timestamp PreviousTime=prt.getExpiryDate();
				 Timestamp CurrentTime= new Timestamp(System.currentTimeMillis());
				Long lPreviousTime=PreviousTime.getTime();
				 Long lCurrentTime=CurrentTime.getTime();
				 if(lPreviousTime>=lCurrentTime) {
					 return true;
				 }else {
					 return false;
				 }
			}else {
				return false;
			}
	}
	@Override
	public void delete(String id) {
		User user=new User();
		user.setUserId(id);
		PasswordResetToken passwordResetToken = (PasswordResetToken ) sessionFactory.getCurrentSession().createCriteria(PasswordResetToken.class)
                  .add(Restrictions.eq("user", user)).uniqueResult();
		sessionFactory.getCurrentSession().delete(passwordResetToken);
	}

}
