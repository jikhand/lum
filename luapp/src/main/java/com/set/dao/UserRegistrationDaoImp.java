package com.set.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.Country;
import com.set.model.User;
import com.set.model.UserRegistration;
@Transactional
@Repository
public class UserRegistrationDaoImp implements UserRegistrationDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void save(UserRegistration userRegistaion) {
		sessionFactory.getCurrentSession().save(userRegistaion);
	}

	@Override
	public void updateUserRegistaion(UserRegistration userRegistaion) {
		sessionFactory.getCurrentSession().update(userRegistaion);	
	}

	@Override
	public void deleteUser(UserRegistration userRegistaion) {
		sessionFactory.getCurrentSession().update(userRegistaion);
	}

	@Override
	public UserRegistration getUserRegistaionById(String userId) {
 		return sessionFactory.getCurrentSession().get(UserRegistration.class, userId);
	}

	@Override
	public UserRegistration Login(String email) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(UserRegistration.class);
		 cr.add(Restrictions.eq("emailId",email));
			  UserRegistration userRegistration = (UserRegistration)cr.uniqueResult();
			  return userRegistration;
	}
	@Override
	public List<UserRegistration> getAllUserSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("roleId","T"));
		return (List<UserRegistration>) criteria.list();
		
//		
//		Criteria cr = sessionFactory.getCurrentSession().createCriteria(UserRegistration.class)
//			    .setProjection(Projections.projectionList()
//			      .add(Projections.property("userId"), "userId")
//			      .add(Projections.property("emailId"), "emailId"))
//			    .add(Restrictions.eq("roleId","T"));
//		return (List<UserRegistration>) cr.list();
		
	}
	@Override
	public List<UserRegistration> getAllAdminSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("roleId","A"));
		return (List<UserRegistration>) criteria.list();
	}

	@Override
	public UserRegistration StudentLogin(String email) {
		UserRegistration userRegistrationNew= new UserRegistration(); 
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(UserRegistration.class);
		userRegistrationNew.setRoleId("S");
		 cr.add(Restrictions.eq("emailId",email));
		 cr.add(Restrictions.eq("roleId",userRegistrationNew.getRoleId()));
			  UserRegistration userRegistration = (UserRegistration)cr.uniqueResult();
			  return userRegistration;
	}

	@Override
	public UserRegistration TeacherLogin(String email) {
		UserRegistration userRegistrationNew= new UserRegistration();
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(UserRegistration.class);
		userRegistrationNew.setRoleId("S");
		 cr.add(Restrictions.eq("emailId",email));
		 cr.add(Restrictions.not(Restrictions.eq("roleId",userRegistrationNew.getRoleId())));
		 UserRegistration userRegistration = (UserRegistration)cr.uniqueResult();	 
		 return userRegistration;
	}
}
