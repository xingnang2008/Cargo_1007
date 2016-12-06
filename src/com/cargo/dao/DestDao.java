package com.cargo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cargo.model.Dest;
@Component
@SuppressWarnings("unchecked")
public class DestDao extends BaseDao {

	
	public void save(Dest transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Dest persistentInstance) {
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
			String hql ="delete from Dest where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByIds(String ids){
		try {
			String hql ="delete from Dest where id in ("+ ids +")";
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Dest dest){
		try {
			getSession().update(dest);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Dest findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Dest instance = (Dest) getSession().get("com.cargo.model.Dest", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Dest";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByDestName(String destName,Integer page,Integer rows){
		String queryString = "from Dest d where d.destName like:destName";
		return getSession().createQuery(queryString)  
		.setString("destName","%"+destName+"%")
		.setFirstResult((page-1)*rows)
		.setMaxResults(rows)
		.list();
	}
	public Long getCountByDest(String destName){
		String queryString = "Select COUNT(id) from Dest d where d.destName like:destName";
		return (Long)getSession().createQuery(queryString)
					.setString("destName","%"+destName+"%")
					.uniqueResult();
		
	}
	
	public Dest findByDestName(String destName) {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Dest d where d.destName =:destName";
			List<Dest> list=getSession().createQuery(queryString)  
			.setString("destName",destName)
			.list();
			if(list.size()>0){
				return list.get(0);
			}else return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
