package com.cargo.dao;

import java.util.List;
import java.util.Map;

import com.cargo.model.User;

public interface UserDao<User> extends IBaseDao<User> {

	public void save(User user);
	public void update(User user);
	public void delete(int id);
	public List<User> findAll();
	public User findById(int id);
	public Map find();
	public User findByUserName(String username) ;
	
}
