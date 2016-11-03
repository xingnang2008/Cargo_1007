package com.cargo.service;

import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cargo.dao.RoleDao;
import com.cargo.dao.UserDao;
import com.cargo.model.User;
import com.cargo.secrity.UserDetailsAdapter;

@SuppressWarnings("restriction")
public class CustomerUserDetailsService implements UserDetailsService {

	private UserDao userDao;
	private RoleDao roleDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
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
