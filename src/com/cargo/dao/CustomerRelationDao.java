package com.cargo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.Customer;
import com.cargo.model.CustomerRelation;
import com.cargo.model.Procurator;
import com.cargo.model.Rater;

@Component
public class CustomerRelationDao extends BaseDao {
	public void save(CustomerRelation transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(CustomerRelation persistentInstance) {
		//log.debug("deleting Rebate instance");
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void deleteByIds(String ids) {
		//log.debug("deleting Rebate instance");
		System.out.print(ids);
		try {
			String hql ="delete from CustomerRelation where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void update(CustomerRelation customer){
		try {
			getSession().update(customer);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public CustomerRelation findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			CustomerRelation instance = (CustomerRelation) getSession().get("com.cargo.model.CustomerRelation", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from CustomerRelation";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List<Customer> findCustBySenderId(Integer senderId){
		try {
			String queryString = "from CustomerRelation c left join fetch c.customer where c.sender.id =:senderId";
			List<CustomerRelation> list= getSession().createQuery(queryString)
					.setInteger("senderId", senderId)
					.list();
			List<Customer> clist = new ArrayList();
			if(list !=null){
				for(CustomerRelation cr : list){
					clist.add(cr.getCustomer());
				}
			}
			return clist;
			
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List<Rater> findRaterBySenderId(Integer senderId){
		try {
			String queryString = "from CustomerRelation c left join fetch c.rater where c.sender.id =:senderId";
			List<CustomerRelation> list= getSession().createQuery(queryString)
					.setInteger("senderId", senderId)
					.list();
			List<Rater> clist = new ArrayList();
			if(list !=null){
				for(CustomerRelation cr : list){
					clist.add(cr.getRater());
				}
			}
			return clist;
			
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List<Procurator> findProcuratorBySenderId(Integer senderId){
		try {
			String queryString = "from CustomerRelation c left join fetch c.procurator where c.sender.id =:senderId";
			List<CustomerRelation> list= getSession().createQuery(queryString)
					.setInteger("senderId", senderId)
					.list();
			
			List<Procurator> clist = new ArrayList();
			if(list !=null){
				for(CustomerRelation cr : list){
					clist.add(cr.getProcurator());
				}
			}
			
			return clist;
			
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List findRelationById(Integer senderId,Integer page,Integer rows){
		try {
			String queryString = "from CustomerRelation c  where c.sender.id =:senderId";
			List<CustomerRelation> list= getSession().createQuery(queryString)
					.setInteger("senderId", senderId)
					.setFirstResult((page-1)*rows)
					.setMaxResults(rows)
					.list();
			
			return list;
			
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public Map findRelationByCustId(Integer custId){
		try {
			String queryString = "from CustomerRelation c  where c.customer.id =:custId";
			List<CustomerRelation> list= getSession().createQuery(queryString)
					.setInteger("custId", custId)					
					.list();
			Map<String,Object> pageMap = new HashMap<String,Object>();	
			pageMap.put("rows",list);
			pageMap.put("total",list.size());	
			return pageMap;
			
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public Long getCountRelationById(Integer senderId){
		String queryString = "Select COUNT(id) from CustomerRelation c where c.sender.id =:senderId";
		return (Long)getSession().createQuery(queryString)
					.setInteger("senderId", senderId)
					.uniqueResult();
		
	}
	public Map find(Integer custId,Integer senderId,Integer raterId,Integer procuratorId ){
		Map<String,Object> pageMap = new HashMap<String,Object>();	
		Criteria crit = getSession().createCriteria(CustomerRelation.class);
		crit.createAlias("customer", "cust")
		.createAlias("sender","sd")
		.createAlias("procurator","pr")
		.createAlias("rater", "rt");
		crit.addOrder(Order.desc("id"));
		/*
		.add(
				Restrictions.or( 
				Restrictions.eq("cust.id", custId),
				Restrictions.or( 
				Restrictions.eq("sd.id", senderId),
				Restrictions.or( 
				Restrictions.eq("rt.id", raterId)))));
		*/
		if(senderId!=null){
			crit.add(Restrictions.or( 
					Restrictions.eq("sd.id", senderId)));
		}
		if(custId!=null){
			crit.add(Restrictions.or( 
				Restrictions.eq("cust.id", custId)));
		}
		if(raterId!=null){
			crit.add(Restrictions.or( 
					Restrictions.eq("rt.id", raterId)));
		}
		if(procuratorId !=null){
			crit.add(Restrictions.or( 
					Restrictions.eq("pr.id", procuratorId)));
		}
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<CustomerRelation> comps = (List<CustomerRelation>)crit.list();
		System.out.println(comps.size());
		
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
				
		return pageMap;
		
	}
	
}
