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

import com.cargo.model.AdvanceRecorders;
import com.cargo.model.Waybill;
@Component
public class AdvanceRecordersDao extends BaseDao {
	
	public void save(AdvanceRecorders transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void save(Waybill Instance,Date date) {
		//log.debug("saving Rebate instance");
		try {
			AdvanceRecorders ar = new AdvanceRecorders();
			ar.setWaybill(Instance.getWaybill());
			ar.setSender(Instance.getSender());
			ar.setCustId(Instance.getCustId());
			ar.setSddate(Instance.getSddate());
			ar.setRdate(date);
			ar.setPics(Instance.getPics());
			ar.setFee(Instance.getAdvancedZ()+Instance.getNoaccAdvance().doubleValue());
			
			getSession().save(ar);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(AdvanceRecorders persistentInstance) {
		//log.debug("deleting Rebate instance");
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void deleteByWaybill(String waybill) {
		//log.debug("deleting Rebate instance");
		try {
			String queryString = "from AdvanceRecorders a where a.waybill=:waybill";
			Query queryObject = getSession().createQuery(queryString)
				.setString("waybill", waybill);
				
			
			List<AdvanceRecorders> list = queryObject.list();
			for(AdvanceRecorders a : list){
				getSession().delete(a);
			}
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void deleteByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="delete from AdvanceRecorders where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(AdvanceRecorders line){
		try {
			getSession().update(line);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public AdvanceRecorders findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			AdvanceRecorders instance = (AdvanceRecorders) getSession().get("com.cargo.model.AdvanceRecorders", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from AdvanceRecorders";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public Map find(String sender,String custId,Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(AdvanceRecorders.class);
		if(sender!=null) 
		crit.add(Restrictions.like("sender", "%"+sender+"%") );
		if(custId!=null) 
		crit.add(Restrictions.like("custId", "%"+custId+"%") );
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("rdate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("rdate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		List<AdvanceRecorders> comps = (List<AdvanceRecorders>)crit.list();
		
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
	}
}
