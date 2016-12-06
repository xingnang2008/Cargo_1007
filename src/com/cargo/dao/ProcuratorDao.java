package com.cargo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.cargo.model.Procurator;
@Component
public class ProcuratorDao extends BaseDao {

	public void save(Procurator transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Procurator persistentInstance) {
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
			String hql ="delete from Procurator where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Procurator procurator){
		try {
			getSession().update(procurator);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Procurator findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Procurator instance = (Procurator) getSession().get("com.cargo.model.Procurator", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Procurator";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByProcurator(String procurator,Integer page,Integer rows){
		String queryString = "from Procurator s where s.name like:name";
		return getSession().createQuery(queryString)  
		.setString("name","%"+procurator+"%")
		.setFirstResult((page-1)*rows)
		.setMaxResults(rows)
		.list();
	}
	public Long getCountByProcurator(String name){
		String queryString = "Select COUNT(id) from Procurator s where s.name like:name";
		return (Long)getSession().createQuery(queryString)
					.setString("name","%"+name+"%")
					.uniqueResult();
		
	}
	public Procurator findByProcurator(String procurator) {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Procurator s where s.name =:name";
			
			List<Procurator> list =getSession().createQuery(queryString)  
			.setString("name",procurator)
			.list();
			if(list.size()>0){
			return list.get(0);
			}else return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
