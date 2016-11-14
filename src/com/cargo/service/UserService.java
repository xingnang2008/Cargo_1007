package com.cargo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cargo.model.Role;
import com.cargo.model.User;

public interface UserService {
	
	public void save(User user);
	public void update(User user);
	public void delete(int id);
	public List<User> findAll();
	public User findById(int id);
	public Map find();
	public User findByUserName(String username) ;
	public void save(User user,int id);
	public void refreshUser(User user,String pswd);
	public void deleteByIds(String ids);
}
