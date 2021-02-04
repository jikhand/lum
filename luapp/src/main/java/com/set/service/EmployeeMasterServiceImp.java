package com.set.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.set.dao.EmployeeMasterDao;
import com.set.model.EmployeeMaster;
import com.set.model.User;
@Service
public class EmployeeMasterServiceImp implements EmployeeMasterService {
    @Autowired
    EmployeeMasterDao employeeMasterDao;
	@Override
	public void save(EmployeeMaster employeeMaster) {
		employeeMasterDao.save(employeeMaster);

	}

	@Override
	public EmployeeMaster list(int pagenumber, String searchdata) {
		return employeeMasterDao.list(pagenumber, searchdata);
	}

	@Override
	public EmployeeMaster getEmployeeMasterById(User user) {
		return employeeMasterDao.getEmployeeMasterById(user);
	}

	@Override
	public void updateUser(EmployeeMaster employeeMaster) {
		employeeMasterDao.updateUser(employeeMaster);
	}
	@Override
	public void deleteUser(String UserId) {
		employeeMasterDao.deleteUser(UserId);// TODO Auto-generated method stub
	}
	@Override
	public EmployeeMaster findUserById(String UserId) {
		return employeeMasterDao.findUserById(UserId);
	}
}
