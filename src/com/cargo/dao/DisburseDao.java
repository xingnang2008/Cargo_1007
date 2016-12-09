package com.cargo.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.cargo.model.Bitch;
import com.cargo.model.Disburse;
import com.cargo.model.dto.OutFee;
@Component
public class DisburseDao extends BaseDao {
	
	private BitchDao bitchDao;
	private IndemnifyDao indemnifyDao;
	private TrackDao trackDao;
	
	public BitchDao getBitchDao() {
		return bitchDao;
	}
	@Resource
	public void setBitchDao(BitchDao bitchDao) {
		this.bitchDao = bitchDao;
	}
	
	
	public IndemnifyDao getIndemnifyDao() {
		return indemnifyDao;
	}
	@Resource
	public void setIndemnifyDao(IndemnifyDao indemnifyDao) {
		this.indemnifyDao = indemnifyDao;
	}
	
	public TrackDao getTrackDao() {
		return trackDao;
	}
	@Resource
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	
	
	public void save(Disburse transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Disburse persistentInstance) {
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
			String hql ="delete from Disburse where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Disburse persistentInstance){
		try {
			getSession().update(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Disburse findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Disburse instance = (Disburse) getSession().get("com.cargo.model.Disburse", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Disburse";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Map find(String lineId,Integer sort,Integer payMethod, Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Disburse.class);
		if(lineId !=null){
			crit.add(Restrictions.like("lineId", "%"+lineId+"%"));
		}
		if(sort !=null){
			crit.add(Restrictions.eq("sortId", sort));
		}
		if(payMethod !=null){
			crit.add(Restrictions.eq("payMethod", payMethod));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Disburse> comps = (List<Disburse>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
		
		return pageMap;
	}
	public Map listOutFeeByLine(String lineId){
		try {
			String hql ="select bitch,sum(outSum) from Waybill w where w.lineId =:lineId group by bitch order by bitch desc";
			List<Object[]> results = getSession().createQuery(hql)
			.setString("lineId",lineId)
			.list();
			Map<String,Object> pageMap = new HashMap<String,Object>();	
			List<OutFee> list = new ArrayList();			
			if(results!=null&&results.size()>0){
				
			    String bch = "";//批次			    
			    Number outsum =0;
			   			    			    
			    for(Object[] obj:results){
			       bch = obj[0]!=null?(String) obj[0]:"";
			       outsum= obj[1]!=null?Double.parseDouble(obj[1].toString()):BigDecimal.ZERO;
			       
			  
			       DecimalFormat dfw = new DecimalFormat("0.0");
			       OutFee of = new OutFee();
			       Double osum = Double.parseDouble(dfw.format(outsum));
			       Double oInd =indemnifyDao.countOutFeeByBitch(bch);
			       Double oDelay =trackDao.countOutDelayIndemByBitch(bch);
			       Double ofee = osum-oInd-oDelay;
			       Double actOf =countDisburseByBitch(bch);
			       of.setBitch(bch);
			       of.setLineId(lineId);
			       of.setOutSum(osum);
			       of.setOutIndem(oInd);
			       of.setOutDelayIndem(oDelay);
			       of.setOutFee(ofee.intValue());
			       of.setActOutFee(actOf.intValue());
			       list.add(of);
			    }
			}
			pageMap.put("rows",list);
			pageMap.put("total",list.size());	
			return pageMap;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Double countDisburseByBitch(String bitch){
		
		try {
			String queryString = "select sum(fee) from Disburse d where d.bitch =:bitch";
			Double sum =  (Double)getSession().createQuery(queryString)
			.setString("bitch",bitch).uniqueResult();
			
			return sum!=null?sum:0.0;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
