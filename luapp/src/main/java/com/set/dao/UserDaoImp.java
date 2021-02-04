package com.set.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.set.model.EmployeeMaster;
import com.set.model.Role;
import com.set.model.User;
import com.set.model.UserDetails;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
//	@Autowired
//	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	@SuppressWarnings("unchecked")
	@Override
	//@Scheduled(cron = "0 0 12 * * ?")
	public UserDetails list(int pagenumber,String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
//		return (List<User>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EmployeeMaster.class,"user");
		//criteria.add(Restrictions.eq("IsDelete",false));
//		if (!searchdata.equalsIgnoreCase("null")) {
//			
//			criteria.createAlias("user.address","address");
//			criteria.createAlias("user.tblProfile","tblProfile");
////			criteria.createAlias("address.city","city");
////			criteria.createAlias("city.stateMaster","state");
////			criteria.createAlias("state.country","country");
//			Disjunction or = Restrictions.disjunction();
////			or.add(Restrictions.like("city.cityName", searchdata, MatchMode.ANYWHERE))
////			  .add(Restrictions.like("state.stateName", searchdata, MatchMode.ANYWHERE)).
////			   add(Restrictions.like("country.countryName", searchdata, MatchMode.ANYWHERE)).
//			or.add(Restrictions.like("user.FirstName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.MiddleName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.LastName", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.Email", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("user.Mobile", searchdata, MatchMode.ANYWHERE)).
//			   add(Restrictions.like("tblProfile.ProfileType", searchdata, MatchMode.ANYWHERE));
//			   criteria.add(or);
//		}
		UserDetails userDetails = new UserDetails();
		System.out.println("total number of record="+criteria.list().size());
		userDetails.setCount(criteria.list().size());
		userDetails.setUser((List<EmployeeMaster>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return userDetails;
	}

	@Override
	public User getUserById(String UID) {
		return sessionFactory.getCurrentSession().get(User.class, UID);
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void deleteUser(String UserId) {
		User user = sessionFactory.getCurrentSession().get(User.class, UserId);
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public User findUserById(String UserId) {
		return sessionFactory.getCurrentSession().get(User.class, UserId);
	}

	@Override
	public boolean UserLogin(String UserId,String Password) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(User.class);
		 cr.add(Restrictions.eq("emailId",UserId)).uniqueResult();
		 List results = cr.list();
		 if(results.isEmpty()) {
			 return false;
		 }else {
			 User user=(User) results.get(0);
			 if(bCryptPasswordEncoder.matches(Password,user.getCurrentPassword()) & results.size()>0) {
				 return true;
			 }else {
				 return false;
			 } 
		 }
	}
	public  User retrieveFromId(String email) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(User.class);
		 cr.add(Restrictions.eq("emailId",email));
		  User user = (User)cr.uniqueResult();
		  return user;
		 }
	
	public  int retrieveFromEmailId(String email) {
		 Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(User.class);
		 cr.add(Restrictions.eq("emailId",email));
		 List results = cr.list();
		  return results.size();
		 }
	@Override
	public List<Role> GetAllRoll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
		//userDetails.setUser((List<EmployeeMaster>) criteria.list());
		return criteria.list();
	}

//	@Override
//	public boolean UserLogin(String UserId, String Password) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
