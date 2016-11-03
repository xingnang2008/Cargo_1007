package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.RoleDao;
import com.cargo.model.Role;
@Component
public class RoleService {
	private RoleDao roleDao;
	public RoleDao getRoleDao() {
		return roleDao;
	}
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void save(Role role){
		this.roleDao.save(role);
	}
	public void deleteByIds(String ids) {
		roleDao.deleteByIds(ids);
	}
	public void delete(Role role){
		this.roleDao.delete(role);
	}
	public void update(Role role){
		this.roleDao.update(role);
	}
	public List<Role> findAll(){
		return this.roleDao.findAll();
		
	}
}
