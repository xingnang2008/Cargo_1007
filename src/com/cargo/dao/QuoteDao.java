package com.cargo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.Quote;

@Component
public class QuoteDao extends BaseDao {
	public void save(Quote transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Quote persistentInstance) {
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
			String hql ="delete from Quote where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List lsitByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="from Quote where id in ("+ ids +")";
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Quote quote){
		try {
			getSession().update(quote);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Quote findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Quote instance = (Quote) getSession().get("com.cargo.model.Quote", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Quote order by id desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Quote findByBh(String bh){
		try {
			String queryString = "from Quote b where b.bh =:bh";
			List<Quote> list = getSession().createQuery(queryString)
			.setString("bh",bh)
			.list();
			if(list.size()>0){
				return list.get(0);
			}else return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Quote findByBH(String bh){
		try {
			String queryString = "from Quote b where b.bh =:bh";
			List<Quote> list = getSession().createQuery(queryString)
			.setString("bh",bh)
			.list();
			if(list.size()>0){
				return list.get(0);
			}else return null;
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
			
	
	
	public Map find(String product,String hsCodeRu){
		
			
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Quote.class);
		
		if(product !=null){
		crit.add(Restrictions.like("product", "%"+product+"%") );
		}
		if(hsCodeRu !=null){
			crit.add(Restrictions.like("hsCodeRu", "%"+hsCodeRu+"%") );
		}
		/*
		 * if(hsCodeCh !=null){
			crit.add(Restrictions.like("hsCodeCh", "%"+hsCodeCh+"%") );
		}
		*/
		
		List<Quote> comps = (List<Quote>)crit.list();
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		System.out.println("list size:"+comps.size());
		System.out.println("rowCount:"+rowCount);
		
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
	}
	
	
}
