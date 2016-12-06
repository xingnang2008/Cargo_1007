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

import com.cargo.model.Indemnify;
import com.cargo.model.Waybill;

@Component
public class IndemnifyDao extends BaseDao {
	public void save(Indemnify transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Indemnify persistentInstance) {
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
			String hql ="delete from Indemnify where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Indemnify sender){
		try {
			getSession().update(sender);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Indemnify findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Indemnify instance = (Indemnify) getSession().get("com.cargo.model.Indemnify", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Indemnify";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的ids,将赔偿状态调整为是否审核
	public void approvalIndemnify(String ids,Integer status){
			try {
				String hql ="update Indemnify w set w.approval=:approval  where id in ("+ ids +")";			
				getSession().createQuery(hql)
				.setInteger("approval",status)
				.executeUpdate();
			} catch (RuntimeException re) {
				throw re;
			}
		
	}
	public Map find(String custId,String bitch,String waybill,String sender,String rater,String lineId,Date stdate,Date enddate){
		Map<String,Object> pageMap = new HashMap<String,Object>();	
		Criteria crit = getSession().createCriteria(Indemnify.class);
				
		if(bitch!=null){
			crit.add(Restrictions.like("bitch", "%"+bitch+"%"));
		}		
		if(custId!=null){
			crit.add(Restrictions.like("custId",  "%"+custId+"%"));
		}
		if(waybill!=null){
			crit.add(Restrictions.like("waybill", "%"+waybill+"%"));
		}
		if(sender!=null){
			crit.add(Restrictions.like("sender", "%"+sender+"%"));
		}
		if(rater!=null){
			crit.add(
					Restrictions.like("rater", "%"+rater+"%"));
		}
		if(lineId!=null){
			crit.add(
					Restrictions.like("lineId", "%"+lineId+"%"));
		}
		if(stdate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("indate",stdate));  
		if(enddate!=null)                          //le查询指定时间之前的记录  
			crit.add(Restrictions.le("indate",enddate));  
		
		crit.addOrder(Order.desc("custId"));
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Indemnify> comps = (List<Indemnify>)crit.list();
		System.out.println(comps.size());
		
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
				
		return pageMap;
		
	}
	//用于累加赔偿金额。
	public Double countFeeByWaybill(String waybill){
		
		try {
								
			String queryString = "select sum(indemnity) from Indemnify i where i.waybill =:waybill";
			Double sum = (Double)getSession().createQuery(queryString)
			.setString("waybill",waybill).uniqueResult();
			
			return sum!=null?sum:0.0;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Indemnify findByWaybill(String waybill){
		
		try {
			String queryString = "from Indemnify i where i.waybill =:waybill";
			return (Indemnify)getSession().createQuery(queryString)  
			.setString("waybill",waybill)		
			.list().get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//用于代理人 取得有丢失的票号。
	public List listWaybillByProcurator(String procurator){
		
		try {
			String queryString = "select waybill from Indemnify i where i.procurator =:procurator";
			
			Query queryObject = getSession().createQuery(queryString)			
			.setString("procurator",procurator);
			return queryObject.list();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//用于取得丢失重量。
	public Double getWeightByWaybill(String waybill){
		try {
			String queryString = "select indemWeight from Indemnify i where i.waybill =:waybill";
			return (Double)getSession().createQuery(queryString)
			.setString("waybill",waybill)
			.uniqueResult();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public Boolean isExsit(String waybill){
		boolean isExsit = false;
		List list = null;
			
			try {
				String queryString = "from Indemnify i where i.waybill =:waybill";
				list = getSession().createQuery(queryString)  
				.setString("waybill",waybill)
				.list();
				
				if(list.size()>0){
					return true;
				}
			} catch (RuntimeException re) {
				throw re;
			}
			
		
		
		return isExsit;
		
		
	}
}
