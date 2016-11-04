package com.cargo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import com.cargo.model.User;
@Component
public class UserDao extends BaseDao {
	
	public void save(User transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void deleteByIds(String ids) {
		try {
			String hql ="delete from User where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(User transType){
		try {
			getSession().update(transType);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		try {
			User instance = (User) getSession().get("com.cargo.model.User", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		try {
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public User findByUserName(String username) {
		try {
			String queryString = "from User where userName=:userName";
			
			return (User) getSession().createQuery(queryString)  
			.setString("userName",username)			
			.list().get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Map find(String name,String username){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(User.class);
		if(name!=null)  {
		crit.add(Restrictions.like("name", "%"+name+"%"));
		}
		if(username!=null)  {
			crit.add(Restrictions.eq("username", username));
		}		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		List<User> comps = (List<User>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	

		return pageMap;
	}
}
