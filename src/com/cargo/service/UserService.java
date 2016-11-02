package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.UserDao;
import com.cargo.model.User;
@Component
public class UserService {
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save(User user){
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
	public List<User> findAll(){
		return this.userDao.findAll();
		
	}
}
