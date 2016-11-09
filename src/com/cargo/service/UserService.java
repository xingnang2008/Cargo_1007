package com.cargo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cargo.dao.UserDao;
import com.cargo.model.User;
@Component
public class UserService {
	private UserDao userDao;
	Md5PasswordEncoder md5 = new Md5PasswordEncoder();
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void save(User user){
		String password =md5.encodePassword(user.getPassword(), user.getName());
		user.setPassword(password);
		
		this.userDao.save(user);
	}
	public void deleteByIds(String ids) {
		this.userDao.deleteByIds(ids);
	}
	public void delete(User user){
		this.userDao.delete(user);
	}
	public void update(User user){
		this.userDao.update(user);
	}
	public User findById(java.lang.Integer id) {
		return userDao.findById(id);
	}
	public boolean checkPassword(Integer id,String oldPassword){
			Boolean isRight = false;
		 	User user =findById(id);
	        String result = md5.encodePassword(user.getUsername(), oldPassword);
	        if(user.getPassword().equals(result)){
	        	return isRight =true;
	        }
	        return isRight;
	}
	public void refreshUser(User user,String pswd){
		String password =md5.encodePassword(pswd,user.getUsername());
		user.setPassword(password);
		this.userDao.update(user);
	}
	public List<User> findAll(){
		return this.userDao.findAll();
	}
	public Map find(){
		return userDao.find();
		
	}
}
