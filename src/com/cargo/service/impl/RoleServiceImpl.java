package com.cargo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cargo.dao.IBaseDao;
import com.cargo.dao.RoleDao;
import com.cargo.model.Role;
import com.cargo.service.BaseService;
import com.cargo.service.RoleService;



@SuppressWarnings({"restriction" })
@Service("roleService")
public class RoleServiceImpl extends BaseService implements RoleService{
	
		

	public Map find(){
		return roleDao.find();
	}

	public void delete(int id) {
		roleDao.delete(id);
		
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public Role findById(int id) {
		return (Role) roleDao.findById(id);
	}

	public void save(Role role) {
		roleDao.save(role);
	}

	public void update(Role role) {
		roleDao.update(role);
		
	}

	public void deleteByIds(String ids) {
		roleDao.deleteByIds(ids);
		
	}
	
}
