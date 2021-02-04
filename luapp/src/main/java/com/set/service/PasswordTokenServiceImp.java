package com.set.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.set.dao.PasswordTokendao;
import com.set.model.User;

@Service
@Transactional
public class PasswordTokenServiceImp implements PasswordTokenService{

	@Autowired
	private PasswordTokendao passwordTokendao;
	
	/**  
	 * description: create password reset token
	 * @param: @User,@Token
	 * @return: Void
	 * etc
	 */
	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		passwordTokendao.createPasswordResetTokenForUser(user, token);
	}
	/**  
	 * @description validate whether token is expired 
	 * @param @id,@token 
	 * @return boolean
	 * etc
	 */
	@Override
	public boolean validatePasswordResetToken(String id, String token) {
		return passwordTokendao.validatePasswordResetToken(id, token);
	}
	
	/**  
	 * @description delete based on reset password token id
	 * @param  @Id
	 * @return @void
	 * etc
	 */
	@Override
	public void delete(String id) {
		passwordTokendao.delete(id);
	}

}
