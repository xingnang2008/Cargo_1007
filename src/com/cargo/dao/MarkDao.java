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

import com.cargo.model.Line;
import com.cargo.model.Mark;
import com.cargo.model.Rebate;
import com.cargo.model.Waybill;

@Component
public class MarkDao extends BaseDao {
	
	public void save(Mark transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Mark persistentInstance) {
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
			String hql ="delete from Mark where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Mark mark){
		try {
			getSession().update(mark);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Mark findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Mark instance = (Mark) getSession().get("com.cargo.model.Mark", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Mark";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//所有品类查询，用在品类浏览Mark_list.jsp
	public Map find(String type,Integer page,Integer rows){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Mark.class);	
		if(type !=null){
			crit.add(Restrictions.like("type", "%"+type+"%") );
		}		
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		crit.setFirstResult((page-1)*rows);
		crit.setMaxResults(rows);
		
		List<Mark> comps = (List<Mark>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	

		return pageMap;
	}
	public Mark findByType(String type){
		String queryString = "from Mark m where m.type =:type";
		return (Mark)getSession().createQuery(queryString)  
		.setString("type",type)
		.list().get(0);
	}
}
