package com.cargo.dao;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.Rebate;
@SuppressWarnings("unchecked")
@Component
public class RebateDao extends BaseDao {
	
	public void save(Rebate transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Rebate persistentInstance) {
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
			
			String hql ="delete from Rebate where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByIds(String ids){
		try {
			String queryString = "from Rebate r where r.id in("+ids+")";
			Query queryObject = getSession().createQuery(queryString);
			return  queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Rebate rebate){
		try {
			getSession().update(rebate);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Rebate findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Rebate instance = (Rebate) getSession().get("com.cargo.model.Rebate", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Rebate";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	public List<Rebate> listCustId(String cust_id,Integer page,Integer rows){
		String queryString = "from Rebate r where r.custId like:custId order by r.id desc";
		return getSession().createQuery(queryString)  
		.setString("custId","%"+cust_id+"%")
		.setFirstResult((page-1)*rows)
		.setMaxResults(rows)
		.list();
		
	}
	public Long getCountByCustId(String cust_id){
		String queryString = "Select COUNT(id) from Rebate r where r.custId like:custId";
		return (Long)getSession().createQuery(queryString)
					.setString("custId","%"+cust_id+"%")
					.uniqueResult();
		
	}

	
	public Map<String,Object> find(String custName,String company,String bitch,Date beginTime,Date endTime,Integer page,Integer rows){
		Map<String,Object> pageMap = new HashMap<String,Object>();
		
		Criteria crit = getSession().createCriteria(Rebate.class);
		
		
		crit.add(Restrictions.like("custName", "%"+custName+"%") )
			.add(Restrictions.like("company", "%"+company+"%"))
			.add(Restrictions.ge("sddate",beginTime))
			.add(Restrictions.le("sddate",endTime));		
		
		
		
		/*
		crit.createAlias("company", "c");
		crit.createAlias("bitch", "b");
		System.out.println(crit.list().size());
		crit.add(Restrictions.like("b.bitch", "%"+bitch+"%") );
		System.out.println(crit.list().size());
		if(beginTime!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",beginTime));  
		if(endTime!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",endTime));  
		
		crit.setFirstResult((page-1)*rows);
		crit.setMaxResults(rows);
		*/
		List<Rebate> comps = (List<Rebate>)crit.list();
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
				
	}
				
	
}
