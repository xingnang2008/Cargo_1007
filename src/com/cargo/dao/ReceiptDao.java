package com.cargo.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

import com.cargo.model.Receipt;
import com.cargo.model.dto.RceiptByCustAndDate;
import com.cargo.model.dto.WaybillGroupSender;
@Component
public class ReceiptDao extends BaseDao {
	public void save(Receipt transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Receipt persistentInstance) {
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
			String hql ="delete from Receipt where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Receipt line){
		try {
			getSession().update(line);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Receipt findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Receipt instance = (Receipt) getSession().get("com.cargo.model.Receipt", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Receipt";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Map findByCustAndMethod(String custName,String sender,String custId,Integer medthod,Date stdate,Date enddate){
		Map<String,Object> pageMap = new HashMap<String,Object>();	
		Criteria crit = getSession().createCriteria(Receipt.class);				
		if(sender!=null){
			crit.add(Restrictions.like("sender", "%"+sender+"%"));
		}		
		if(custId!=null){
			crit.add(Restrictions.like("custId",  "%"+custId+"%"));
		}
		if(custName!=null){
			crit.add(Restrictions.like("custName", "%"+custName+"%"));
		}
		if(medthod!=null){
			crit.add(Restrictions.like("payMethod", "%"+medthod+"%"));
		}
	
		if(stdate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("rdate",stdate));  
		if(enddate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("rdate",enddate));  
		
		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.groupProperty("custId"));
		crit.setProjection(Projections.groupProperty("payMethod"));
		crit.setProjection(Projections.groupProperty("rdate"));
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Receipt> comps = (List<Receipt>)crit.list();
		System.out.println(comps.size());
		
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
				
		return pageMap;		
	}
	
	public Integer countFeeByWaybill(String waybill){
		
		try {
			String queryString = "select sum(fee) from Receipt t where t.waybill =:waybill";
			Long sum =  (Long)getSession().createQuery(queryString)
			.setString("waybill",waybill).uniqueResult();
			
			return sum!=null?sum.intValue():0;
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//按运单号查找 收款记录是否存在
	public Boolean isexsitTrackByWaybill(String waybill){
		boolean isExsit = false;
		List list = null;
			
			try {
				String queryString = "from Receipt t where t.waybill =:waybill";
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
		Criteria crit = getSession().createCriteria(Receipt.class);				
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
			crit.add(Restrictions.ge("rdate",stdate));  
		if(enddate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("rdate",enddate));  
		
		crit.addOrder(Order.desc("id"));
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Receipt> comps = (List<Receipt>)crit.list();
		System.out.println(comps.size());
		
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
				
		return pageMap;		
	}
	//按客户号查询  按日期 汇总收款
	public List<RceiptByCustAndDate> listByCustIdAndDate(String sender){
		
		try {
			String hql ="select custId,sum(fee) ,rdate  from Receipt r where r.sender=:sender group by rdate order by rdate desc";
			List<Object[]> results = getSession().createQuery(hql)
			.setString("sender",sender)
			.list();
			List<RceiptByCustAndDate> list = new ArrayList();			
			if(results!=null&&results.size()>0){
			    String custId = "";//发货人
			    Number fee =0; //金额
			    Object rdate = new Date();
			    for(Object[] obj:results){
			    	custId = obj[0]!=null?(String) obj[0]:"";
			        fee = obj[1] != null?Integer.parseInt(obj[1].toString()):BigDecimal.ZERO;			      
			        rdate = obj[2]!=null?((Date) obj[2]):"-";
			      
			       RceiptByCustAndDate rbcsd = new RceiptByCustAndDate();
			       rbcsd.setSender(sender);
			       rbcsd.setFee(fee.intValue());
			       rbcsd.setRdate((Date)rdate);
			       rbcsd.setCustId(custId);
			       list.add(rbcsd);
			    }
			}
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	//按日期 汇总收款
	public List<RceiptByCustAndDate> listByCustIdAndDate(){
		
		try {
			String hql ="select custId,sum(fee) ,rdate ,sender from Receipt group by rdate  order by rdate desc";
			List<Object[]> results = getSession().createQuery(hql)
			.list();
			List<RceiptByCustAndDate> list = new ArrayList();			
			if(results!=null&&results.size()>0){
			    String custId = ""; //客户号
			    String sender ="";//发货人
			    Number fee =0; //金额
			    Object rdate = new Date();
			    for(Object[] obj:results){
			    	custId = obj[0]!=null?(String) obj[0]:"";
			        fee = obj[1] != null?Integer.parseInt(obj[1].toString()):BigDecimal.ZERO;			      
			        rdate = obj[2]!=null?((Date) obj[2]):"-";
			        sender = obj[3]!=null?(String) obj[3]:"";
			        
			       RceiptByCustAndDate rbcsd = new RceiptByCustAndDate();
			       rbcsd.setSender(sender);
			       rbcsd.setFee(fee.intValue());
			       rbcsd.setRdate((Date)rdate);
			       rbcsd.setCustId(custId);
			       list.add(rbcsd);
			    }
			}
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
}
