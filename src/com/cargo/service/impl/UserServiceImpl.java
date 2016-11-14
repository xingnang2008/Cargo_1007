package com.cargo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cargo.model.Role;
import com.cargo.model.User;
import com.cargo.service.BaseService;
import com.cargo.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService{
	
	public void save(User user) {
		userDao.save(user);
	}
	public void update(User user) {
		userDao.update(user);
	}
	public Map find(){
		return userDao.find();
	}
	public void refreshUser(User user,String pswd){
		String password =md5.encodePassword(pswd,user.getUsername());
		user.setPassword(password);
		userDao.update(user);
	}
	public void save(User user,int id){
		String password =md5.encodePassword(user.getName(), user.getName());
		Role r = (Role) roleDao.findById(id);
		Set<Role> ss = new HashSet();
		ss.add(r);
		
		user.setRoles(ss);
		user.setPassword(password);
		userDao.save(user);
	}
	public void deleteByIds(String ids){
		userDao.deleteByIds(ids);
	}
	
	public void delete(int id) {
		userDao.delete(id);
		
	}
	public List<User> findAll() {
		return userDao.findAll();
	}
	public User findById(int id) {
		return (User) userDao.findById(id);
	}
	public User findByUserName(String username) {
		return (User) userDao.findByUserName(username);
	}
	
}
