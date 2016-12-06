package com.cargo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cargo.model.Company;

@Component
public class CompanyDao extends BaseDao {
	
	
	public void save(Company transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Company persistentInstance) {
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
			String hql ="delete from Company where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Company company){
		try {
			getSession().update(company);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Company findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Company instance = (Company) getSession().get("com.cargo.model.Company", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Company";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List isCompanyExist(String companyName){
		
		try {
			String queryString = "from Company c where c.company =:companyName";
			return getSession().createQuery(queryString)  
			.setString("companyName",companyName)		
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public Company findByName(String companyName){
		try {
			String queryString = "from Company c where c.company =:companyName";
			return (Company)getSession().createQuery(queryString)  
			.setString("companyName",companyName)		
			.list().get(0);
			
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
