package com.cargo.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cargo.dao.RoleDao;
import com.cargo.model.Role;
@Component("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao<Role>{
	
	public Map find(){		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		List<Role> list = findAll();
		
		pageMap.put("rows",list);
		pageMap.put("total",list.size());	

		return pageMap;
	}
	public Role findByRoleName(String Rolename) {
		try {
			String queryString = "from Role where name=:RoleName";
			
			return (Role) getSession().createQuery(queryString)  
			.setString("RoleName",Rolename)			
			.list().get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	


	

}
