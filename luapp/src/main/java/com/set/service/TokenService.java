package com.set.service;

import java.util.List;

import com.set.model.UserToken;

public interface TokenService {
	public List<UserToken> list();
	public void save(UserToken userToken);
	public void tokenUpdate(long lastInsertId,String userId);
	public void tokenLogout(String userId);
	public boolean tokenValidate(String token);
}
