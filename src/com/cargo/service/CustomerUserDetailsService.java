package com.cargo.service;

import javax.annotation.Resource;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cargo.dao.impl.RoleDaoImpl;
import com.cargo.dao.impl.UserDaoImpl;
import com.cargo.model.User;
import com.cargo.security.UserDetailsAdapter;

@SuppressWarnings("restriction")
public class CustomerUserDetailsService implements UserDetailsService {

	private UserDaoImpl userDao;
	private RoleDaoImpl roleDao;

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public RoleDaoImpl getRoleDao() {
		return roleDao;
	}

	@Resource
	public void setRoleDao(RoleDaoImpl roleDao) {
		this.roleDao = roleDao;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = (User)userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No such user: " + username);
		} else if (user.getRoles().isEmpty()) {
			throw new UsernameNotFoundException("User " + username
					+ " has no authorities");
		}
		UserDetailsAdapter userAdp = new UserDetailsAdapter(user);
		return userAdp;
	}
	 
}
