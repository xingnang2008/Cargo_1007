package com.cargo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cargo.model.Rebatecustomer;
@Component
public class RebatecustomerDao extends BaseDao {

	public void save(Rebatecustomer transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Rebatecustomer persistentInstance) {
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
			String hql ="delete from Rebatecustomer where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Rebatecustomer rebatecustomer){
		try {
			getSession().update(rebatecustomer);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Rebatecustomer findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Rebatecustomer instance = (Rebatecustomer) getSession().get("com.cargo.model.Rebatecustomer", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Rebatecustomer";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	public List<Rebatecustomer> listCustId(String cust_id,Integer page,Integer rows){
		String queryString = "from Rebatecustomer r where custId like:custId";
		return getSession().createQuery(queryString)  
		.setString("custId","%"+cust_id+"%")
		.setFirstResult((page-1)*rows)
		.setMaxResults(rows)
		.list();
		
	}
	public Long getCount(String cust_id){
		String queryString = "Select COUNT(id) from Rebatecustomer r where custId like:custId";
		return (Long)getSession().createQuery(queryString)
					.setString("custId","%"+cust_id+"%")
					.uniqueResult();
		
	}

	
	
}
