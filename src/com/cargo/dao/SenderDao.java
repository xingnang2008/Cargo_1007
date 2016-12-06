package com.cargo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cargo.model.Sender;
@Component
public class SenderDao extends BaseDao {

	public void save(Sender transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Sender persistentInstance) {
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
			String hql ="delete from Sender where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Sender sender){
		try {
			getSession().update(sender);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Sender findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Sender instance = (Sender) getSession().get("com.cargo.model.Sender", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Sender";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listBySender(String sender,Integer page,Integer rows){
		String queryString = "from Sender s where s.sdName like:sdName";
		return getSession().createQuery(queryString)  
		.setString("sdName","%"+sender+"%")
		.setFirstResult((page-1)*rows)
		.setMaxResults(rows)
		.list();
	}
	public Long getCountBySender(String sdName){
		String queryString = "Select COUNT(id) from Sender s where s.sdName like:sdName";
		return (Long)getSession().createQuery(queryString)
					.setString("sdName","%"+sdName+"%")
					.uniqueResult();
		
	}
	public Sender findBySender(String sender) {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Sender s where s.sdName =:sdName";
			
			List<Sender> list =getSession().createQuery(queryString)  
			.setString("sdName",sender)
			.list();
			if(list.size()>0){
			return list.get(0);
			}else return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
