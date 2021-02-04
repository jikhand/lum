package com.set.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.TokenDao;
import com.set.model.UserToken;
@Transactional
@Service
public class TokenServiceImp implements TokenService {
	
	@Autowired
	private TokenDao tokenDao;
	/**  
	 * description Get List of token
	 * @param N/A
	 * @return List<UserToken>
	 * etc
	 */
	@Override
	public List<UserToken> list() {
		// TODO Auto-generated method stub
		return tokenDao.list();
	}
	/**  
	 * @description: save user Token
	 * @param @userToken
	 * @return void
	 * etc
	 */
	@Override
	public void save(UserToken userToken) {
		tokenDao.save(userToken);
	}
	@Override
	public void tokenUpdate(long lastInsertId, String userId) {
		tokenDao.tokenUpdate(lastInsertId,userId);
	}
	@Override
	public void tokenLogout(String userId) {
		tokenDao.tokenLogout(userId);
		
	}
	@Override
	public boolean tokenValidate(String token) {
		return tokenDao.tokenValidate(token);
	}

}