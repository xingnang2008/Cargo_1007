package com.cargo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.Rater;
@Component
public class RaterDao extends BaseDao {
	
	public void save(Rater transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Rater persistentInstance) {
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
			String hql ="delete from Rater where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public void update(Rater rater){
		try {
			getSession().update(rater);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Rater findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Rater instance = (Rater) getSession().get("com.cargo.model.Rater", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Rater";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Rater findByRaterName(String raterName){
		try {
		String queryString = "from Rater r where r.raterName like:raterName";
		List<Rater> list=getSession().createQuery(queryString)  
		.setString("raterName","%"+raterName+"%")		
		.list();
		if(list.size()>0){
			return list.get(0);
		}else return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByRater(String raterName,Integer page,Integer rows){
		try {
			String queryString = "from Rater r where r.raterName like:raterName";
			return getSession().createQuery(queryString)  
			.setString("raterName","%"+raterName+"%")
			.setFirstResult((page-1)*rows)
			.setMaxResults(rows)
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Long getCountByRater(String raterName){
		String queryString = "Select COUNT(id) from Rater r where r.raterName like:raterName";
		return (Long)getSession().createQuery(queryString)
					.setString("raterName","%"+raterName+"%")
					.uniqueResult();
		
	}
	public Map find(String raterName,Integer page,Integer rows){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Rater.class);			
		crit.add(Restrictions.like("raterName", "%"+raterName+"%") );		
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		crit.setFirstResult((page-1)*rows);
		crit.setMaxResults(rows);	
		List<Rater> comps = (List<Rater>)crit.list();
		System.out.println("list size:"+comps.size());
		System.out.println("rowCount:"+rowCount);
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
	}
}
