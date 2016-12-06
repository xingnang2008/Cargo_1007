package com.cargo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.HuiQuote;

@Component
public class HuiQuoteDao extends BaseDao {
	public void save(HuiQuote transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(HuiQuote persistentInstance) {
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
			String hql ="delete from HuiQuote where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List lsitByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="from HuiQuote where id in ("+ ids +")";
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(HuiQuote category){
		try {
			getSession().update(category);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public HuiQuote findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			HuiQuote instance = (HuiQuote) getSession().get("com.cargo.model.HuiQuote", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from HuiQuote";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public HuiQuote findByBH(String bh){
		try {
			String queryString = "from HuiQuote b where b.bh =:bh";
			List<HuiQuote> list = getSession().createQuery(queryString)
			.setString("bh",bh)
			.list();
			if(list.size()>0){
				return list.get(0);
			}else return null;
			
		} catch (RuntimeException re) {
			throw re;
		}
	}	
	public Map find(String category,String product,String transType){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(HuiQuote.class);
		if(category !=null){
			crit.add(Restrictions.like("category", "%"+category+"%") );
		}
		if(product != null){
			crit.add(Restrictions.like("products", "%"+product+"%") );
		}
		if(transType !=null){
			crit.add(Restrictions.like("transType", "%"+transType+"%") );
		}
		
		List<HuiQuote> comps = (List<HuiQuote>)crit.list();
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		System.out.println("list size:"+comps.size());
		System.out.println("rowCount:"+rowCount);
		
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
	}
	
	
}
