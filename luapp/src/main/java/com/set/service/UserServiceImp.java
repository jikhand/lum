package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.set.dao.UserDao;
import com.set.model.Role;
import com.set.model.User;
import com.set.model.UserDetails;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */	
	@Override
	public void save(User user) {
		userDao.save(user);
	}
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */
	@Override
	public UserDetails list(int pagenumber,String searchdata) {
		return userDao.list(pagenumber,searchdata);
	}
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */
	@Override
	public User getUserById(String UID) {
		User obj = userDao.getUserById(UID);
		return obj;
	}
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */
	@Override
	public void deleteUser(String UserId) {
		userDao.deleteUser(UserId);
	}
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */
	@Override
	public User findUserById(String UserId) {
		return userDao.findUserById(UserId);
	}
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */
	@Override
	public boolean UserLogin(String UserId,String Password) {
		return userDao.UserLogin(UserId,Password);
	}
	/**  
	 * (Method description)
	 * @param
	 * @return
	 * etc
	 */
	@Override
	public User retrieveFromId(String email) {
		return userDao.retrieveFromId(email);
	}
	/**  
	 * @description: retrieve From Email Id
	 * @param @Email
	 * @return int
	 * etc
	 */
	@Override
	public int retrieveFromEmailId(String email) {
		return userDao.retrieveFromEmailId(email);
	}
	@Override
	public List<Role> GetAllRoll() {
		// TODO Auto-generated method stub
		return userDao.GetAllRoll();
	}

}
