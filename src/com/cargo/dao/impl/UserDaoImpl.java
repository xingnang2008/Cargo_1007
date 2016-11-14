package com.cargo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Component;

import com.cargo.dao.UserDao;
import com.cargo.model.User;
@Component("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{
	

	public User findByUserName(String username) {
		try {
			String queryString = "from User where userName=:userName";
			
			return (User) getSession().createQuery(queryString)  
			.setString("userName",username)			
			.list().get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Map find(){		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		List<User> list = findAll();
		
		pageMap.put("rows",list);
		pageMap.put("total",list.size());	

		return pageMap;
	}

	
}
