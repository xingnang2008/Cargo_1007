package com.cargo.service;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.cargo.dao.RoleDao;
import com.cargo.dao.UserDao;



public abstract class BaseService {
	protected Md5PasswordEncoder md5 = new Md5PasswordEncoder();
	
	@Resource
	protected RoleDao roleDao;
	@Resource
	protected UserDao userDao;
	
	
	
	
}
