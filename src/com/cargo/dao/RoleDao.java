package com.cargo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface RoleDao<Role> extends IBaseDao<Role> {
	
	public void save(Role role);
	public void update(Role role);
	public void delete(int  id);
	public List<Role> findAll();
	public Role findById(int  id);
	public Map find();


}
