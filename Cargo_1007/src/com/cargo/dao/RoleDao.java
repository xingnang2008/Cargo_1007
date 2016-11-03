package com.cargo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cargo.model.Role;
import com.cargo.model.User;
@Component
public class RoleDao extends BaseDao {
	
	public void save(Role transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Role persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void deleteByIds(String ids) {
		try {
			String hql ="delete from Role where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Role transType){
		try {
			getSession().update(transType);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Role findById(java.lang.Integer id) {
		try {
			Role instance = (Role) getSession().get("com.cargo.model.Role", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		try {
			String queryString = "from Role";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
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
