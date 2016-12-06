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

import com.cargo.model.ProcuratorRecorders;
import com.cargo.model.Waybill;
@Component
public class ProcuratorRecordersDao extends BaseDao {
	
	public void save(ProcuratorRecorders transientInstance) {
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
			ProcuratorRecorders ar = new ProcuratorRecorders();
			ar.setWaybill(Instance.getWaybill());
			ar.setSender(Instance.getSender());
			ar.setProcurator(Instance.getProcurator());
			ar.setSddate(Instance.getSddate());
			ar.setRdate(date);
			ar.setPics(Instance.getPics());
			ar.setFee(Instance.getAdvancedZ()+Instance.getNoaccAdvance().doubleValue());
			
			getSession().save(ar);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(ProcuratorRecorders persistentInstance) {
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
			String queryString = "from ProcuratorRecorders a where a.waybill=:waybill";
			Query queryObject = getSession().createQuery(queryString)
				.setString("waybill", waybill);
			List<ProcuratorRecorders> list = queryObject.list();
			for(ProcuratorRecorders a : list){
				getSession().delete(a);
			}
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void deleteByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="delete from ProcuratorRecorders where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(ProcuratorRecorders line){
		try {
			getSession().update(line);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public ProcuratorRecorders findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			ProcuratorRecorders instance = (ProcuratorRecorders) getSession().get("com.cargo.model.ProcuratorRecorders", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from ProcuratorRecorders";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public Map find(String sender,String procurator,Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(ProcuratorRecorders.class);
		if(sender!=null) 
		crit.add(Restrictions.like("sender", "%"+sender+"%") );
		if(procurator!=null) 
		crit.add(Restrictions.like("procurator", "%"+procurator+"%") );
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("rdate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("rdate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		List<ProcuratorRecorders> comps = (List<ProcuratorRecorders>)crit.list();
		
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
	}
}
