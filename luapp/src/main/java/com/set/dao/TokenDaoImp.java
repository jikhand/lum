package com.set.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.set.utils.TokenUtils;
import com.set.model.UserToken;
import com.set.utils.TokenUtils;
import io.jsonwebtoken.Claims;
@Repository
public class TokenDaoImp implements TokenDao {
@Autowired SessionFactory sessionFactory;
	@Override
	public List<UserToken> list() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save(UserToken userToken) {
		sessionFactory.getCurrentSession().save(userToken);
	}
	@Override
	public void tokenUpdate(long lastInsertId, String userId) {
		String hql = "UPDATE UserToken SET isdeleted =1 WHERE id!="+lastInsertId+" and userid='"+userId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	@Override
	public void tokenLogout(String userId) {
		String hql = "UPDATE UserToken SET isdeleted =1 WHERE userid='"+userId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	@Override
	public boolean tokenValidate(String token) {
		Claims claims=TokenUtils.verifyToken(token);
		if(claims == null) {
			return true;	
		}else {
			String hql = "select id from tbl_token where isdeleted !=1 and token='"+token+"'";
			System.out.println("hql="+hql);
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
			List<Object[]> rows = query.list();
			if(rows.size()>0) {
				return false;
			}else {
				return true;
			}
		}
	}
	
}