package com.cargo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.Line;
@Component
public class LineDao extends BaseDao {
	
	public void save(Line transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Line persistentInstance) {
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
			String hql ="delete from Line where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Line line){
		try {
			getSession().update(line);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Line findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Line instance = (Line) getSession().get("com.cargo.model.Line", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Line findByLineId(String lineId){
		String queryString = "from Line l where l.lineId =:lineId order by l.lineId";
		return (Line)getSession().createQuery(queryString)  
		.setString("lineId",lineId)
		.list().get(0);
		
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Line";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByline(String lineId,Integer page,Integer rows){
		String queryString = "from Line l where l.lineId like:lineId";
		return getSession().createQuery(queryString)  
		.setString("lineId","%"+lineId+"%")
		.setFirstResult((page-1)*rows)
		.setMaxResults(rows)
		.list();
	}
	public Long getCountByLine(String lineId){
		String queryString = "Select COUNT(id) from Line l where l.lineId like:lineId";
		return (Long)getSession().createQuery(queryString)
					.setString("lineId","%"+lineId+"%")
					.uniqueResult();
		
	}
	public Map find(String lineId,Integer page,Integer rows){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Line.class);			
		crit.add(Restrictions.like("lineId", "%"+lineId+"%") );
		crit.setFirstResult((page-1)*rows);
		crit.setMaxResults(rows);
		
		List<Line> comps = (List<Line>)crit.list();
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		System.out.println("list size:"+comps.size());
		System.out.println("rowCount:"+rowCount);
		
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
	}
}
