package com.cargo.service;

import java.util.List;
import java.util.Map;

import com.cargo.model.Role;

public interface RoleService {
	
	
	public void save(Role role);
	public void update(Role role);
	public void delete(int  id);
	public List<Role> findAll();
	public Role findById(int  id);
	public Map find();
	public void deleteByIds(String ids);	

}
