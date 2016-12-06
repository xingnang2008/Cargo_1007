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

import com.cargo.model.Bitch;
import com.cargo.model.Waybill;
@Component
public class BitchDao extends BaseDao {
	public void save(Bitch transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Bitch persistentInstance) {
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
			String hql ="delete from Bitch where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List lsitByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="from Bitch where id in ("+ ids +")";
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Bitch bitch){
		try {
			getSession().update(bitch);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void updateBitch(Integer id,String bitch,String info,Double fee,Date sdDate,Date arrDate,String lineId,String remarks,Double changeRate){
		try {
			Bitch bc = this.findById(id);
			bc.setBitch(bitch);
			bc.setInfo(info);
			bc.setSdDate(sdDate);
			bc.setArrDate(arrDate);
			bc.setLineId(lineId);
			bc.setRemarks(remarks);
			bc.setFee(fee);
			bc.setChangeRate(changeRate);
			update(bc);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Bitch findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Bitch instance = (Bitch) getSession().get("com.cargo.model.Bitch", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Bitch order by id desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Bitch findByBitch(String bitch){
		try {
			String queryString = "from Bitch b where b.bitch =:bitch";
			Query query = getSession().createQuery(queryString)
			.setString("bitch",bitch);
			List list= query.list();
			if(list.size()>0){
				return (Bitch) list.get(0);
			}else return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public String findLineIdByBitch(String bitch){
		try {
			String queryString = "from Bitch b where b.bitch =:bitch";
			List<Bitch> list = getSession().createQuery(queryString)
			.setString("bitch",bitch)
			.list();
			if(list.size()>0){
				return list.get(0).getLineId();
			}else return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<Bitch> queryBitchByLine(String lineId){
		try {
			String queryString = "from Bitch b where b.lineId =:lineId order by b.bitch desc";
			List<Bitch> list = getSession().createQuery(queryString)
			.setString("lineId",lineId)
			.list();
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Long getCount(String line){
		
		String queryString = "Select COUNT(id) from Bitch b where b.lineId like:lineId";
		return (Long)getSession().createQuery(queryString)
					.setString("lineId","%"+line+"%")
					.uniqueResult();
		
	}
	public Map find(String lineId,Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Bitch.class);			
		crit.add(Restrictions.like("lineId", "%"+lineId+"%") );
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sdDate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sdDate",edDate));  
		
		
		List<Bitch> comps = (List<Bitch>)crit.list();
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		System.out.println("list size:"+comps.size());
		System.out.println("rowCount:"+rowCount);
		
		pageMap.put("total",rowCount);	
		pageMap.put("rows",comps);
		
		return pageMap;
	}
	public List<Bitch> findBySdDate(Date stDate,Date edDate){
		Criteria crit = getSession().createCriteria(Bitch.class);			
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sdDate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sdDate",edDate));  
					
		 List<Bitch> bList =(List<Bitch>)crit.list();
		 return bList;
	}
	
}
