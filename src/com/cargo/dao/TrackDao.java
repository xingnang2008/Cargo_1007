package com.cargo.dao;

import java.math.BigInteger;
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

import com.cargo.model.Track;
import com.cargo.model.Waybill;
@Component
public class TrackDao extends BaseDao {
	public void save(Track transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Track persistentInstance) {
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
			String hql ="delete from Track where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Track track){
		try {
			getSession().update(track);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Track findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Track instance = (Track) getSession().get("com.cargo.model.Track", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Track";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Integer countPicsByWaybill(String waybill){
		try {
			String queryString = "select sum(pics) from Track t where t.waybill =:waybill";
			Long sum =  (Long)getSession().createQuery(queryString)
			.setString("waybill",waybill).uniqueResult();
			
			return sum!=null?sum.intValue():0;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Integer countDelayIndemByWaybill(String waybill){
		try {
			String queryString = "select sum(delayIndemnity) from Track t where t.waybill =:waybill";
			Double sum =  (Double)getSession().createQuery(queryString)
			.setString("waybill",waybill).uniqueResult();
			
			return sum!=null?sum.intValue():0;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Double countOutDelayIndemByBitch(String bitch){
		
		try {
			String queryString = "select sum(outIndemnity) from Track t where t.bitch =:bitch";
			Double sum =  (Double)getSession().createQuery(queryString)
			.setString("bitch",bitch).uniqueResult();
			
			return sum!=null?sum:0.0;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Boolean CheckPics(String waybill,Integer pics){
		Boolean isRight = false;
		try {
			String queryString = "select sum(pics) from Track t where t.waybill =:waybill";
			Long apics =(Long)getSession().createQuery(queryString)
			.setString("waybill",waybill).uniqueResult();
			String queryString2 = "select pics from Waybill w where w.waybill =:waybill";
			Long allpics = (Long) getSession().createQuery(queryString)  
			.setString("waybill",waybill)		
			.list().get(0);
			
			if(apics+pics <= allpics){
				isRight=true;
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return isRight;
	}
	public void updateAppOn(String ids,Integer editable){
		try {
			String hql ="update Track t set t.approval=:approval  where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setInteger("approval",editable)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List<Track> findByWaybill(String waybill){
		try {
			String queryString = "from Track t where t.waybill =:waybill";
			return getSession().createQuery(queryString)
			.setString("waybill",waybill).list();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//按运单号查找 到货记录是否存在
	public Boolean isexsitTrackByWaybill(String waybill){
		boolean isExsit = false;
		List list = null;
			
			try {
				String queryString = "from Track t where t.waybill =:waybill";
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
		
	
	public Map find(String custId,String bitch,String waybill,String sender,String rater,String lineId,Date stdate,Date enddate){
		Map<String,Object> pageMap = new HashMap<String,Object>();	
		Criteria crit = getSession().createCriteria(Track.class);
				
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
			crit.add(Restrictions.ge("arriveDate",stdate));  
		if(enddate!=null)                          //le查询指定时间之前的记录  
			crit.add(Restrictions.le("arriveDate",enddate));  
		
		crit.addOrder(Order.desc("arriveDate"));
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Track> comps = (List<Track>)crit.list();
		System.out.println(comps.size());
		
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
				
		return pageMap;
		
	}
}
