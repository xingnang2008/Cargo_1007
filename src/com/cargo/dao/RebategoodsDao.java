package com.cargo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cargo.model.Rebategoods;
@Component
public class RebategoodsDao extends BaseDao {
	
	public void save(Rebategoods transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Rebategoods persistentInstance) {
		//log.debug("deleting Rebate instance");
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void deleteByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="delete from Rebategoods where id in ("+ ids +")";
			System.out.print(ids);
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="from Rebategoods where rebateId in ("+ ids +")";
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Rebategoods rebatecustomer){
		try {
			getSession().update(rebatecustomer);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Rebategoods findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Rebategoods instance = (Rebategoods) getSession().get("com.cargo.model.Rebategoods", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Rebategoods";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//用于分页显示的查询
	public List listByRebateId(Integer rebateId,Integer page,Integer rows){
		String queryString = "from Rebategoods  where rebateId =:rebateId";
		return getSession().createQuery(queryString)  
		.setInteger("rebateId",rebateId)
		.setFirstResult((page-1)*rows)
		.setMaxResults(rows)
		.list();
	}
	public List listByRebateId(Integer Id){
		String queryString = "from Rebategoods  where rebateId =:rebateId";
		return getSession().createQuery(queryString) 
		.setInteger("rebateId", Id) 
		.list();
		
	}
	public Long getCountByRebateId(Integer rebateId){
		String queryString = "Select COUNT(id) from Rebategoods  where rebateId =:rebateId";
		return (Long)getSession().createQuery(queryString)
					.setInteger("rebateId",rebateId)
					.uniqueResult();
	}
	
}
