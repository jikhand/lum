package com.set.service;

import com.set.model.User;

public interface PasswordTokenService {
	public void createPasswordResetTokenForUser(User user, String token);
	   public boolean validatePasswordResetToken(String id, String token);
	   public void delete(String id);
}
