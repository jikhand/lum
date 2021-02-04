	package com.set.dao;

	import java.util.List;

	import javax.transaction.Transactional;

	import org.hibernate.Criteria;
	import org.hibernate.SessionFactory;
	import org.hibernate.criterion.Restrictions;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;

	import com.set.model.City;
	import com.set.model.ClassSectionMaster;
	import com.set.model.ClassSectionMasterId;
	import com.set.model.Country;
	import com.set.model.NotesMaster;
	import com.set.model.Role;
	import com.set.model.State;
	import com.set.model.StudentEnrollment;
	import com.set.model.StudentEnrollmentId;
	import com.set.model.User;
	import com.set.model.UserRegistration;
	@Transactional
	@Repository
	public class StudentEnrollmentDaoImpl implements StudentEnrollmentDao {
	    @Autowired
	    private SessionFactory sessionFactory;
		@Override
		public void save(StudentEnrollment studentEnrollment) {
			sessionFactory.getCurrentSession().save(studentEnrollment);
		}

		@Override
		public StudentEnrollment getStudentEnrollmentById(StudentEnrollmentId studentEnrollment) {
			Criteria cr= sessionFactory.getCurrentSession()
					.createCriteria(StudentEnrollment.class);
			 cr.add(Restrictions.eq("studentEnrollmentId",studentEnrollment));
			 List results = cr.list();
			 StudentEnrollment newstudentEnrollment = (StudentEnrollment)cr.uniqueResult(); 
			 return newstudentEnrollment;
		}

		@Override
		public void updateStudentEnrollment(StudentEnrollment studentEnrollment) {
			sessionFactory.getCurrentSession().update(studentEnrollment);

		}

		@Override
		public boolean IsEmailExist(String email) {
			Criteria cr= sessionFactory.getCurrentSession()
					.createCriteria(UserRegistration.class);
			 cr.add(Restrictions.eq("emailId",email));
			 int results = cr.list().size();
			if(results>0) {
				return true;
			}else {
				return false;	
			}
		}

	@Override
	public long cityIdByName(String cityId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(City.class);
		 cr.add(Restrictions.eq("cityName",cityId));
		 List results = cr.list();
		 City newCity = (City)cr.uniqueResult();
		 int count = cr.list().size();
		 long countryValue = 0;
		 if(count>0) {
			 return newCity.getCityId();
		 }else {
			 return countryValue;	
		 } 
	}

	@Override
	public long stateIdByName(String stateId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(State.class);
		 cr.add(Restrictions.eq("stateName",stateId));
		 List results = cr.list();
		 State newState = (State)cr.uniqueResult(); 
		 int count = cr.list().size();
		 long countryValue = 0;
		 if(count>0) {
			 return newState.getStateId();
		 }else {
			 return countryValue;	
		 }
	}

	@Override
	public long countryIdByName(String countryId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(Country.class);
		 cr.add(Restrictions.eq("countryName",countryId));
		 List results = cr.list();
		 Country newCountry = (Country)cr.uniqueResult(); 
		 int count = cr.list().size();
		 long countryValue = 0;
		 if(count>0) {
			 return newCountry.getCountryId();
		 }else {
			 return countryValue;	
		 }
	}

	@Override
	public String roleIdByName(String roleId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(Role.class);
		 cr.add(Restrictions.eq("roleDescription",roleId));
		 List results = cr.list();
		 Role newRole = (Role)cr.uniqueResult(); 
		 int count = cr.list().size();
		 String countryValue = "";
		 if(count>0) {
			 return newRole.getRoleId();
		 }else {
			 return countryValue;	
		 }
	}
	
	@Override
	public String userIdByEmail(String email) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(User.class);
		 cr.add(Restrictions.eq("emailId",email));
		 User user = (User)cr.uniqueResult();
		 List results = cr.list(); 
		 int count = cr.list().size();
		 String countryValue = "";
		 if(count>0) {
			 return user.getUserId();
		 }else {
			 return countryValue;	
		 }
	}

	@Override
	public ClassSectionMaster classIdByName(String classId,String sectionId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(ClassSectionMaster.class);
		 cr.add(Restrictions.eq("className",classId));
		 cr.add(Restrictions.eq("sectionName",sectionId));
		 ClassSectionMaster classSectionMaster = (ClassSectionMaster)cr.uniqueResult(); 
		 return classSectionMaster;
		}

	}