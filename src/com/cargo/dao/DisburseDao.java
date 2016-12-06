package com.cargo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.Disburse;
@Component
public class DisburseDao extends BaseDao {
	
	public void save(Disburse transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Disburse persistentInstance) {
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
			String hql ="delete from Disburse where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Disburse persistentInstance){
		try {
			getSession().update(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Disburse findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Disburse instance = (Disburse) getSession().get("com.cargo.model.Disburse", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Disburse";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Map find(String lineId,Integer sort,Integer payMethod, Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Disburse.class);
		if(lineId !=null){
			crit.add(Restrictions.like("lineId", "%"+lineId+"%"));
		}
		if(sort !=null){
			crit.add(Restrictions.eq("sortId", sort));
		}
		if(payMethod !=null){
			crit.add(Restrictions.eq("payMethod", payMethod));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Disburse> comps = (List<Disburse>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
		
		return pageMap;
	}
}
