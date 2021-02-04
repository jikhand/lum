package com.set.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.EmployeeMaster;
import com.set.model.User;

@Transactional
@Repository
public class EmployeeMasterDaoImp implements EmployeeMasterDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void save(EmployeeMaster employeeMaster) {
		sessionFactory.getCurrentSession().save(employeeMaster);
	}

	@Override
	public EmployeeMaster list(int pagenumber, String searchdata) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeMaster getEmployeeMasterById(User user) {
		 Criteria cr= sessionFactory.getCurrentSession()
					.createCriteria(EmployeeMaster.class);
		 EmployeeMaster employeeMaster =(EmployeeMaster)cr.
				 add(Restrictions.eq("user",user)).uniqueResult();
			 return employeeMaster;
	}

	@Override
	public void updateUser(EmployeeMaster employeeMaster) {
		sessionFactory.getCurrentSession().update(employeeMaster);
		
	}

	@Override
	public void deleteUser(String UserId) {
		EmployeeMaster employeeMaster = sessionFactory.getCurrentSession().get(EmployeeMaster.class, UserId);
		sessionFactory.getCurrentSession().delete(employeeMaster);
		
	}

	@Override
	public EmployeeMaster findUserById(String UserId) {
		return sessionFactory.getCurrentSession().get(EmployeeMaster.class, UserId);
	}

}
