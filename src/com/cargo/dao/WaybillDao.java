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
import com.cargo.model.Indemnify;
import com.cargo.model.Waybill;
import com.cargo.model.dto.Arrearages;
import com.cargo.model.dto.BillStatus;
import com.cargo.model.dto.BillStatusCopy;
import com.cargo.model.dto.ProcuratorGroup;
import com.cargo.model.dto.UnarrivalTrack;
import com.cargo.model.dto.WaybillGroupSender;
@Component
public class WaybillDao extends BaseDao {
	private BitchDao bitchDao;
	public BitchDao getBitchDao() {
		return bitchDao;
	}
	@Resource
	public void setBitchDao(BitchDao bitchDao) {
		this.bitchDao = bitchDao;
	}
	
	public void save(Waybill transientInstance) {
		//log.debug("saving Rebate instance");
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void delete(Waybill persistentInstance) {
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
			String hql ="delete from Waybill where id in ("+ ids +")";
			getSession().createQuery(hql).executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//选中的记录集
	public List listByIds(String ids) {
		//log.debug("deleting Rebate instance");
		try {
			String hql ="from Waybill where id in ("+ ids +")";
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List<Bitch> listByBitchGroup(String bitches){
		try {
			String hql ="select bitch,sum(pics),sum(weight),sum(volumu),sum(advancedZ),sum(advancedU),"+
			"sum(packfeeU),sum(packfeeZ),sum(noaccPackfee),sum(noaccAdvance),sum(worth),sum(insurance),"+
			"sum(sumbill),sum(total),sum(actualSum),sum(cod),sum(discount),sum(outSum),sum(outWorth),sum(outInsurance),"+
			"sum(indemnity)"+
			"  from Waybill w where w.bitch in ("+bitches+") group by bitch";
			List<Object[]> results = getSession().createQuery(hql).list();
			
			List<Bitch> bitchList = new ArrayList();			
			if(results!=null&&results.size()>0){
				
			    String bch = "";//批次
			    Number pic =0; //件数
			    Number weight = 0.0;//重量			   
			    Number volumu = 0.00; //体积
			    Number advz =0;
			    Number advu =0;
			    Number packu =0;
			    Number packz =0;
			    Number nopack =0;
			    Number noadv =0;
			    Number worth =0;
			    Number insurance =0;
			    Number sumb =0;
			    Number total =0;
			    Number acsum =0;
			    Number cod =0;
			    Number disct =0;
			    Number outsum =0;
			    Number outworth =0;
			    Number outinsu =0;
			    Number indem =0;
			    			    
			    for(Object[] obj:results){
			       bch = obj[0]!=null?(String) obj[0]:"";
			       pic = obj[1] != null?Integer.parseInt(obj[1].toString()):BigDecimal.ZERO;
			       weight = obj[2]!=null?Double.parseDouble(obj[2].toString()):BigDecimal.ZERO;
			       volumu = obj[3]!=null?Double.parseDouble(obj[3].toString()):BigDecimal.ZERO;
			       advz = obj[4]!=null?Integer.parseInt(obj[4].toString()):BigDecimal.ZERO;
			       advu = obj[5]!=null?Integer.parseInt(obj[5].toString()):BigDecimal.ZERO;
			       packu = obj[6]!=null?Integer.parseInt(obj[6].toString()):BigDecimal.ZERO;
			       packz = obj[7]!=null?Integer.parseInt(obj[7].toString()):BigDecimal.ZERO;
			       nopack =obj[8]!=null?Integer.parseInt(obj[8].toString()):BigDecimal.ZERO;
			       noadv = obj[9]!=null?Integer.parseInt(obj[9].toString()):BigDecimal.ZERO;
			       worth= obj[10]!=null?Integer.parseInt(obj[10].toString()):BigDecimal.ZERO;
			       insurance= obj[11]!=null?Integer.parseInt(obj[11].toString()):BigDecimal.ZERO;
			       sumb  = obj[12]!=null?Double.parseDouble(obj[12].toString()):BigDecimal.ZERO;
			       total= obj[13]!=null?Integer.parseInt(obj[13].toString()):BigDecimal.ZERO;
			       acsum= obj[14]!=null?Integer.parseInt(obj[14].toString()):BigDecimal.ZERO;
			       cod  = obj[15]!=null?Double.parseDouble(obj[15].toString()):BigDecimal.ZERO;
			       disct= obj[16]!=null?Double.parseDouble(obj[16].toString()):BigDecimal.ZERO;
			       outsum= obj[17]!=null?Double.parseDouble(obj[17].toString()):BigDecimal.ZERO;
			       outworth= obj[18]!=null?Double.parseDouble(obj[18].toString()):BigDecimal.ZERO;
			       outinsu = obj[19]!=null?Double.parseDouble(obj[19].toString()):BigDecimal.ZERO;
			       indem = obj[20]!=null?Double.parseDouble(obj[20].toString()):BigDecimal.ZERO;
			       
			       
			       DecimalFormat dfv = new DecimalFormat("0.00");
			       
			       DecimalFormat dfw = new DecimalFormat("0.0");
			       
			      
			       String vol = dfv.format(volumu);
			       String wt = dfw.format(weight);
			       
			       Bitch bb = bitchDao.findByBitch(bch);
			       Bitch bitchModel = new Bitch();
			       bitchModel.setChangeRate(bb.getChangeRate());
			       bitchModel.setSdDate(bb.getSdDate());
			       
			       bitchModel.setArrDate(bb.getArrDate()==null?null:bb.getArrDate());
			       
			       bitchModel.setBitch(bch);
			       bitchModel.setPics((Integer) pic);
			       bitchModel.setWeight(Double.parseDouble(wt));
			       bitchModel.setVolumu(Double.parseDouble(vol));
			       bitchModel.setAdvancedU((Integer)advu);
			       bitchModel.setAdvancedZ((Integer)advz);
			       bitchModel.setPackfeeU((Integer)packu);
			       bitchModel.setPackfeeZ((Integer)packz);
			       bitchModel.setWorth((Integer)worth);
			       bitchModel.setNoaccAdvance((Integer)noadv);
			       bitchModel.setNoaccPackfee((Integer)nopack);
			       bitchModel.setInsurance((Integer)insurance);
			       bitchModel.setSumbill(Double.parseDouble(dfv.format(sumb)));
			       bitchModel.setTotal((Integer)total);
			       bitchModel.setActualSum((Integer)acsum);
			       bitchModel.setCod(Double.parseDouble(dfv.format(cod)));
			       bitchModel.setDiscount(Double.parseDouble(dfv.format(disct)));
			       bitchModel.setOutSum(Double.parseDouble(dfw.format(outsum)));
			       bitchModel.setOutWorth(Double.parseDouble(dfw.format(outworth)));
			       bitchModel.setOutInsurance(Double.parseDouble(dfw.format(outinsu)));
			       bitchModel.setIndemnity(Double.parseDouble(dfw.format(indem)));
			       
			       Integer zz = (Integer)noadv+(Integer)nopack;
			       Double zzd = zz/bitchModel.getChangeRate();
			       String sZzd = dfw.format(zzd);
			       Double profit = (Integer)insurance+Double.parseDouble(dfv.format(sumb))-Double.parseDouble(dfw.format(outsum))-Double.parseDouble(dfw.format(indem))-Double.parseDouble(sZzd);
			       bitchModel.setProfit(profit);
			       bitchList.add(bitchModel);
			    }
			}
			return bitchList;
		} catch (RuntimeException re) {
			throw re;
		}
		
		
	}
	public Long countFeeByWaybill(String waybill){
		
		try {
			String queryString = "select sum(fee) from Receipt t where t.waybill =:waybill";
			return (Long)getSession().createQuery(queryString)
			.setString("waybill",waybill)
			.uniqueResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//用于生成每票货的已到，未到，统计，到货物跟踪上。
	public Map listUnArrivalTrack(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,Integer status,Date stDate,Date edDate){
			
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);	
		if(custId !=null){
			crit.add(Restrictions.like("custId", "%"+custId+"%") );
		}
		if(waybill !=null){
			crit.add(Restrictions.like("waybill", "%"+waybill+"%"));
		}
		if(raterName!=null){
			crit.add(Restrictions.like("raterName", "%"+raterName+"%"));
		}
		if(sdName!=null){
			crit.add(Restrictions.like("sender", "%"+sdName+"%"));
		}
		if(bitch !=null){
			crit.add(Restrictions.like("bitch", "%"+bitch+"%"));
		}
		if(lineId !=null){
			crit.add(Restrictions.like("lineId", "%"+lineId+"%"));
		}
		if(procurator!=null){
			crit.add(Restrictions.like("procurator", "%"+procurator+"%"));
		}
		if(status!=null){
			crit.add(Restrictions.eq("statusId", +status));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("sddate"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> waybills = (List<Waybill>)crit.list();
		List<UnarrivalTrack> wgsList = new ArrayList();	
			
		for(int i=0;i<waybills.size();i++){
			Waybill wb = waybills.get(i);
			String wbill = wb.getWaybill();
			UnarrivalTrack uat = new UnarrivalTrack();
			uat.setWaybill(wbill);
	    	uat.setBitch(wb.getBitch());
	    	uat.setCustId(wb.getCustId());
	    	uat.setCustName(wb.getCustName());
	    	uat.setDestName(wb.getDestName());
	    	uat.setGoods(wb.getGoods());
	    	uat.setLineId(wb.getLineId());
	    	uat.setMark(wb.getMark());
	    	uat.setOrderNo(wb.getOrderNo());
	    	uat.setPics(wb.getPics());
	    	uat.setProcurator(wb.getProcurator());
	    	uat.setQuantity(wb.getQuantity());
	    	uat.setRaterName(wb.getRaterName());
	    	uat.setSddate(wb.getSddate());
	    	uat.setSender(wb.getSender());
	    	uat.setTransType(wb.getTransType());
	    	uat.setVolumu(wb.getVolumu());
	    	uat.setWeight(wb.getWeight());
	    	
	    	uat.setStatusId(wb.getStatusId());
	    	
	    	uat.setTotal(wb.getTotal());
	    	Integer actual =countFeeByWaybill(wbill)!=null?countFeeByWaybill(wbill).intValue():0;
	    	uat.setActualSum(actual);
			try {
				String hql ="select waybill, sum(pics) as ps from Track t where t.waybill=:waybill";
				List<Object[]> results = getSession().createQuery(hql)
				.setString("waybill",wbill)
				.list();
								
				
				    for(Object[] obj:results){
				    	
				    	Integer pis = obj[1] != null?Integer.parseInt(obj[1].toString()):0;			     				       
				    	uat.setArrPics(pis);
				    	uat.setUnArrPics(wb.getPics()-pis);
				    	
				    	
				    }
				
					
			} catch (RuntimeException re) {
				throw re;
			}
			wgsList.add(uat);
		}
		pageMap.put("rows",wgsList);
		pageMap.put("total",rowCount);
		return pageMap;
		
	}
	// 用于查看客户的欠款
	public Map listWaybillArrearages(String sender, String custName) {

		Map<String, Object> pageMap = new HashMap<String, Object>();
		Criteria crit = getSession().createCriteria(Waybill.class);
		if (sender != null) {
			crit.add(Restrictions.like("sender", "%" + sender + "%"));
		}
		if (custName != null) {
			crit.add(Restrictions.like("custName", "%" + custName + "%"));
		}
		crit.add(Restrictions.eq("statusId", 4));

		crit.setProjection(null);

		List<Waybill> waybills = (List<Waybill>) crit.list();
		List<BillStatusCopy> wgsList = new ArrayList<BillStatusCopy>();
		List<BillStatusCopy> list1 = new ArrayList<BillStatusCopy>();
		List<Arrearages> list2 = new ArrayList<Arrearages>();

		for (int i = 0; i < waybills.size(); i++) {
			Waybill wb = waybills.get(i);
			String wbill = wb.getWaybill();
			BillStatusCopy bs = new BillStatusCopy();
			bs.setWaybill(wbill);			
			bs.setCustId(wb.getCustId());
			bs.setCustName(wb.getCustName());
			
			bs.setSender(wb.getSender());		

			bs.setTotal(wb.getTotal());
			Integer actual = countFeeByWaybill(wbill) != null ? countFeeByWaybill(
					wbill).intValue()
					: 0;
			bs.setActualSum(actual);

			Double delayIndem = 0.0;
			// 取得已经到货的包数，并计算出未到货的包数。

			try {
				String hql = "select waybill, sum(pics),sum(delayIndemnity),sum(outIndemnity) from Track t where t.waybill=:waybill";
				List<Object[]> results = getSession().createQuery(hql)
						.setString("waybill", wbill).list();

				for (Object[] obj : results) {
					
					delayIndem = obj[2] != null ? Double.parseDouble(obj[2]
							.toString()) : 0.0;
					
					
					bs.setDelayIndem(delayIndem);
					
					System.out.println("晚到：" + delayIndem); // ---------------
				}

			} catch (RuntimeException re) {
				throw re;
			}
			// 取得丢失赔偿的金额。Indemnity
			try {
				String hql = "from Indemnify i where i.waybill=:waybill";
				List<Indemnify> results = getSession().createQuery(hql)
						.setString("waybill", wbill).list();

				Double indem = results.size() != 0 ? ((Indemnify) results
						.get(0)).getIndemnity() : 0.0;
				
				bs.setIndemnify(indem);
				

				Double arr = wb.getTotal() - delayIndem - indem - actual;
				BigDecimal b = new BigDecimal(arr);
				double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP)
						.doubleValue();

				System.out.println("丢失：" + indem); // ---------------
				bs.setArrear(f1);

			} catch (RuntimeException re) {
				throw re;
			}
			wgsList.add(bs);
			// 取得丢失赔偿的金额。delayIndemnity
			

			
		}
		for(int i=0;i<wgsList.size();i++){
			BillStatusCopy bss  = wgsList.get(i);
			if(list1.indexOf(bss) == -1){
				list1.add(bss);
			}else{
				
				Double oArr = bss.getArrear();
				BillStatusCopy b1 = list1.get(list1.indexOf(bss));
				b1.setArrear(b1.getArrear()+oArr);
								
			}
			
		}
		
		for(int i=0;i<list1.size();i++){
			Arrearages arg = new Arrearages();
			BillStatusCopy bss =list1.get(i);
			arg.setCustId(bss.getCustId());
			arg.setCustName(bss.getCustName());
			arg.setSender(bss.getSender());
			
			arg.setFee(bss.getArrear().intValue());
			System.out.println("欠款：" + bss.getArrear().intValue()); // ---------------
			list2.add(arg);
		}
		
		pageMap.put("rows", list2);
		pageMap.put("total",list2.size());
		return pageMap;

	}
	//用于查看垫付款
	public Map listAdvanceWaybill(String sender,String custId,Integer advanceStatus,Date stDate,Date edDate){
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);	
		crit.add(Restrictions.eq("advanceStauts", advanceStatus));
		if(sender!=null){
			crit.add(Restrictions.like("sender", "%"+sender+"%"));
		}
		if(custId!=null){
			crit.add(Restrictions.like("custId", "%"+custId+"%"));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
		
		return pageMap;
	}
	
	//用于查看代理费
	public Map listRaterWaybill(String procurator,Integer raterStauts,Date stDate,Date edDate){
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);	
		crit.add(Restrictions.eq("raterStauts", raterStauts));
		if(procurator!=null){
			crit.add(Restrictions.like("procurator", "%"+procurator+"%"));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
		
		return pageMap;
	}
		
	
	//用于查看货物的收款，赔偿，实收情况
	public Map listWaybillFee(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,Integer status,Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);	
		if(custId !=null){
			crit.add(Restrictions.like("custId", "%"+custId+"%") );
		}
		if(waybill !=null){
			crit.add(Restrictions.like("waybill", "%"+waybill+"%"));
		}
		if(raterName!=null){
			crit.add(Restrictions.like("raterName", "%"+raterName+"%"));
		}
		if(sdName!=null){
			crit.add(Restrictions.like("sender", "%"+sdName+"%"));
		}
		if(bitch !=null){
			crit.add(Restrictions.like("bitch", "%"+bitch+"%"));
		}
		if(lineId !=null){
			crit.add(Restrictions.like("lineId", "%"+lineId+"%"));
		}
		if(procurator!=null){
			crit.add(Restrictions.like("procurator", "%"+procurator+"%"));
		}
		if(status!=null){
			crit.add(Restrictions.eq("statusId", +status));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("sddate"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> waybills = (List<Waybill>)crit.list();
		List<BillStatus> wgsList = new ArrayList();	
			
		for(int i=0;i<waybills.size();i++){
			Waybill wb = waybills.get(i);
			String wbill = wb.getWaybill();
			BillStatus bs = new BillStatus();
			bs.setWaybill(wbill);  
			bs.setOrderNo(wb.getOrderNo());
			bs.setSddate(wb.getSddate());
			bs.setLineId(wb.getLineId());
	    	bs.setTransType(wb.getTransType());
	    	bs.setBitch(wb.getBitch());
	    	bs.setCustId(wb.getCustId());
	    	bs.setCustName(wb.getCustName());
	    	
	    	bs.setRaterName(wb.getRaterName());
	    	bs.setSender(wb.getSender());
	    	bs.setProcurator(wb.getProcurator());
	    	bs.setDestName(wb.getDestName());	    	
	    	
	    	bs.setPics(wb.getPics());	    		    	
	    	bs.setWeight(wb.getWeight());
	    	bs.setStatusId(wb.getStatusId());
	    	
	    	
	    	bs.setTotal(wb.getTotal());
	    	Integer actual =countFeeByWaybill(wbill)!=null?countFeeByWaybill(wbill).intValue():0;
	    	bs.setActualSum(actual);
	    	
	    	Double delayIndem=0.0;
	    	//取得已经到货的包数，并计算出未到货的包数。
	    	
			try {
				String hql ="select waybill, sum(pics),sum(delayIndemnity),sum(outIndemnity) from Track t where t.waybill=:waybill";
				List<Object[]> results = getSession().createQuery(hql)
				.setString("waybill",wbill)
				.list();
				   
				for(Object[] obj:results){
				    	
				    	Integer pis = obj[1] != null?Integer.parseInt(obj[1].toString()):0;	
				    	delayIndem = obj[2] != null?Double.parseDouble(obj[2].toString()):0.0;
				    	Double outdelayIn =obj[3] != null?Double.parseDouble(obj[3].toString()):0.0;
				    	bs.setArrPics(pis);
				    	bs.setUnArrPics(wb.getPics()-pis);
				    	bs.setDelayIndem(delayIndem);
				    	bs.setOutDelayIndemnity(outdelayIn);
				    	
				    }
				
					
			} catch (RuntimeException re) {
				throw re;
			}
			//取得丢失赔偿的金额。Indemnity
			try {
				String hql ="from Indemnify i where i.waybill=:waybill";
				List<Indemnify> results = getSession().createQuery(hql)
				.setString("waybill",wbill)
				.list();
				
				Double indem = results.size() !=0?((Indemnify)results.get(0)).getIndemnity():0.0;
				Double outIndem =results.size() !=0?((Indemnify)results.get(0)).getOutIndemnity():0.0;
				bs.setIndemnify(indem);
				bs.setOutIndemnify(outIndem);
				
				Double arr = wb.getTotal()-delayIndem-indem-actual;				
				BigDecimal   b   =   new   BigDecimal(arr);  
				double   f1   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				bs.setArrear(f1);
				   
					
			} catch (RuntimeException re) {
				throw re;
			}
			//取得丢失赔偿的金额。delayIndemnity
			wgsList.add(bs);
		}
		pageMap.put("rows",wgsList);
		pageMap.put("total",rowCount);
		return pageMap;
		
	}
	
	//配仓时按发货人和发货日期，汇总件数，重量，体积
	public List<WaybillGroupSender> listByCustGroup(String bitch,Integer statusId){
		
		try {
			String hql ="select sender,sum(pics) ,sum(weight),sum(volumu),sddate  from Waybill w where w.bitch=:bitch and statusId=:statusId group by sddate,sender";
			List<Object[]> results = getSession().createQuery(hql)
			.setString("bitch",bitch)
			.setInteger("statusId", statusId)
			.list();
			List<WaybillGroupSender> wgsList = new ArrayList();			
			if(results!=null&&results.size()>0){
			    String sender = "";//发货人
			    Number pic =0; //件数
			    Number weight = 0.0;//重量			   
			    Number volumu = 0.00; //体积
			    Object sddate = new Date();
			    for(Object[] obj:results){
			       sender = obj[0]!=null?(String) obj[0]:"";
			       pic = obj[1] != null?Integer.parseInt(obj[1].toString()):BigDecimal.ZERO;
			       weight = obj[2]!=null?Double.parseDouble(obj[2].toString()):BigDecimal.ZERO;
			       volumu = obj[3]!=null?Double.parseDouble(obj[3].toString()):BigDecimal.ZERO;
			       sddate = obj[4]!=null?((Date) obj[4]):"-";
			       
			       DecimalFormat dfv = new DecimalFormat("0.00");
			       String vol = dfv.format(volumu);
			       DecimalFormat dfw = new DecimalFormat("0.0");
			       String wt = dfw.format(weight);
			       DecimalFormat dfd = new DecimalFormat("0");

			       Double dsy = Double.parseDouble(wt)/Double.parseDouble(vol);
				   Double density = Double.parseDouble(dfd.format(dsy));
			      
			       WaybillGroupSender wgs = new WaybillGroupSender();
			       wgs.setSender(sender);
			       wgs.setPics((Integer) pic);
			       wgs.setWeight(Double.parseDouble(wt));
			       wgs.setVolumu(Double.parseDouble(vol));
			       wgs.setDensity(density);
			       wgs.setSddate((Date)sddate);
			       wgsList.add(wgs);
			    }
			}
			return wgsList;
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public Integer getCountByCustGroup(String bitch){
		String queryString = "Select COUNT(id) from Waybill w where w.bitch=:bitch  group by custId ";
		List list =getSession().createQuery(queryString)
					.setString("bitch",bitch).list();
		return list.size();			
	}
	//根据选中的ids,将运单状态调整为是否锁定
	public void updateLockOnWaybills(String ids,Integer editable){
		try {
			String hql ="update Waybill w set w.editable=:editable  where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setInteger("editable",editable)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的ids,将运输状态调整
	public void updateWaybillStatus(String ids,Integer status){
		try {
			String hql ="update Waybill w set w.statusId=:statusId  where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setInteger("statusId",status)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的Waybills,将运输状态调整
	public void updateWaybillStatusByWaybills(String waybills,Integer status){
		try {
			String[] waybs =waybills.split(",");
			for(int i=0;i<waybs.length;i++){
				
				String hql ="update Waybill w set w.statusId=:statusId  where waybill =:waybill";			
				getSession().createQuery(hql)
				.setString("waybill",waybs[i])
				.setInteger("statusId",status)
				.executeUpdate();
			}
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的ids,将运单状态调整，用作配仓操作
	public void updateTrackOnStauts(String ids,Integer statusId){
		try {
			String hql ="update Waybill w set w.statusId=:statusId  where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setInteger("statusId",statusId)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的ids,将运单状态调整，用作装机操作
	public void updateTrackOnSend(String ids,Integer statusId,String cangId){
		
		try {
			String hql ="update Waybill w set w.statusId=:statusId ,w.cangId =:cangId where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setInteger("statusId",statusId)
			.setString("cangId",cangId)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的ids,将运单批次调整 用于绿色通道
	public void updateBitch(String ids,String bitch,String lineId){
		try {
			String hql ="update Waybill w set w.bitch=:bitch,w.lineId=:lineId  where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setString("bitch",bitch)
			.setString("lineId",lineId)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的ids,将运单代理费状态更新无已经结
	public void updateRaterStatus(String ids ,Integer status){
		try {
			String hql ="update Waybill w set w.raterStauts=:raterStauts where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setInteger("raterStauts",status)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//根据选中的ids,将运单垫付款状态更新
	public void updateAdvanceStatus(String ids ,Integer status){
		try {
			String hql ="update Waybill w set w.advanceStauts=:advanceStauts where id in ("+ ids +")";			
			getSession().createQuery(hql)
			.setInteger("advanceStauts",status)
			.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List<Waybill> listByBitch(String bitch){
	
		try {
			String queryString = "from Waybill w where w.bitch =:bitch  order by w.sender asc";
			return getSession().createQuery(queryString)  
			.setString("bitch",bitch)		
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List<Waybill> listAdvancedByBitch(String bitch){
		
		try {
			String queryString = "from Waybill w where w.bitch =:bitch  and (w.advancedU <>0 or w.advancedZ <>0 or w.noaccAdvance <>0 or noaccPackfee <>0)";
			return getSession().createQuery(queryString)  
			.setString("bitch",bitch)		
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//获得 多个批次的 代理人为“无”的数据集合
	public List listByBitches(String bitches){
		
		
		System.out.println(bitches);  //----------------
		try {
			String queryString = "from Waybill w where w.bitch in ("+bitches+") and w.procurator <>'无' order by w.procurator asc" ;
			return getSession().createQuery(queryString)					
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//获得 多个批次的 代理人为“无”的  汇总集合
	public List listGroupByBitches(String bitches){
		/*
		String[] ss = bitches.split(",");
		String bes="";
		for(int i=0; i<ss.length;i++){
			bes+="'"+ss[i].trim()+"',";
			
		}
		System.out.println(bes);  //----------------
		bes = bes.substring(0, bes.lastIndexOf(","));
		*/
		
		System.out.println(bitches);  //----------------
		try {
			String queryString = "select procurator,sum(discount),sum(cod) from Waybill w where w.bitch in ("+bitches+") and procurator <>'无' group by procurator";
			List<Object[]> results =getSession().createQuery(queryString).list();
			
			System.out.println(results.size());  //----------------
			
			List<ProcuratorGroup> wgsList = new ArrayList();			
			if(results!=null&&results.size()>0){
			    String procurator = "";//代理人
			    Number discount =0.0; //返点
			    Number cod = 0.0;//代收款			   
			   
			    for(Object[] obj: results){
			    	procurator = obj[0]!=null?(String) obj[0]:"";
			       discount = obj[1] != null?Double.parseDouble(obj[1].toString()):BigDecimal.ZERO;
			       cod = obj[2]!=null?Double.parseDouble(obj[2].toString()):BigDecimal.ZERO;
			      
			       DecimalFormat dfw = new DecimalFormat("0.0");
			       String cd = dfw.format(cod);
			       String disc = dfw.format(discount);
			       
			       ProcuratorGroup wgs = new ProcuratorGroup();
			       wgs.setProcurator(procurator);
			       wgs.setDiscount(Double.parseDouble(disc));
			       wgs.setCod(Double.parseDouble(cd));
			       wgsList.add(wgs);
			       System.out.println(wgsList.size());  //----------------
			    }
			}
			return wgsList;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//获得 多个批次的运单明细数据 集合
	public List listAllByBitches(String bitches){
		
		
		System.out.println(bitches);  //----------------
		try {
			String queryString = "from Waybill w where w.bitch in ("+bitches+") order by w.bitch asc" ;
			return getSession().createQuery(queryString)					
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//获得 多个批次的经办人的  汇总集合
	public List listAllGroupByBitches(String bitches){
		/*
		String[] ss = bitches.split(",");
		String bes="";
		for(int i=0; i<ss.length;i++){
			bes+="'"+ss[i].trim()+"',";
			
		}
		System.out.println(bes);  //----------------
		bes = bes.substring(0, bes.lastIndexOf(","));
		*/
		
		System.out.println(bitches);  //----------------
		try {
			String queryString = "select procurator,sum(discount),sum(cod) from Waybill w where w.bitch in ("+bitches+") and procurator <>'无' group by procurator";
			List<Object[]> results =getSession().createQuery(queryString).list();
			
			System.out.println(results.size());  //----------------
			
			List<ProcuratorGroup> wgsList = new ArrayList();			
			if(results!=null&&results.size()>0){
			    String procurator = "";//代理人
			    Number discount =0.0; //返点
			    Number cod = 0.0;//代收款			   
			   
			    for(Object[] obj: results){
			    	procurator = obj[0]!=null?(String) obj[0]:"";
			       discount = obj[1] != null?Double.parseDouble(obj[1].toString()):BigDecimal.ZERO;
			       cod = obj[2]!=null?Double.parseDouble(obj[2].toString()):BigDecimal.ZERO;
			      
			       DecimalFormat dfw = new DecimalFormat("0.0");
			       String cd = dfw.format(cod);
			       String disc = dfw.format(discount);
			       
			       ProcuratorGroup wgs = new ProcuratorGroup();
			       wgs.setProcurator(procurator);
			       wgs.setDiscount(Double.parseDouble(disc));
			       wgs.setCod(Double.parseDouble(cd));
			       wgsList.add(wgs);
			       System.out.println(wgsList.size());  //----------------
			    }
			}
			return wgsList;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List listByDestIds(String ids){
		try {
			String hql ="from Waybill where dest.id in ("+ ids +")";
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void update(Waybill waybill){
		try {
			getSession().update(waybill);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Waybill findById(java.lang.Integer id) {
		//log.debug("getting Rebate instance with id: " + id);
		try {
			Waybill instance = (Waybill) getSession().get("com.cargo.model.Waybill", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List findAll() {
		//log.debug("finding all Rebate instances");
		try {
			String queryString = "from Waybill";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Waybill findByWaybill(String waybill){
		try {
			String queryString = "from Waybill w where w.waybill =:waybill";
			return (Waybill)getSession().createQuery(queryString)  
			.setString("waybill",waybill)		
			.list().get(0);
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List<Waybill> queryByBitchId(Integer id){
		
		try {
			String queryString = "from Bitch b where b.id=:id";
			Bitch bitch =(Bitch) getSession().createQuery(queryString)  
			.setInteger("id",id)		
			.list().get(0);
			String queryString2 = "from Waybill w where w.bitch=:bitch";
			return getSession().createQuery(queryString2)  
			.setString("bitch",bitch.getBitch())		
			.list();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List<Waybill> queryByBitch(String bitch){
		
		try {
			String queryString = "from Waybill w where w.bitch.bitch like:bitch";
			return getSession().createQuery(queryString)  
			.setString("bitch","%"+bitch+"%")		
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List queryBySender(String sdName){
		
		try {
			String queryString = "from Waybill w where w.sender.sdName like:sdName";
			return getSession().createQuery(queryString)  
			.setString("sdName","%"+sdName+"%")		
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List queryByStatus(Integer status){
		try {
			String queryString = "from Waybill w where w.status.id =:sdId";
			return getSession().createQuery(queryString)  
			.setInteger("sdId",status)		
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public List queryByDest(Integer destId){
		try {
			String queryString = "from Waybill w where w.dest.id =:Id";
			return getSession().createQuery(queryString)  
			.setInteger("Id",destId)		
			.list();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	//所有运单查询，用在运单浏览Waybill_list.jsp
	public Map find(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,String mark ,Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);	
		if(custId !=null){
			crit.add(Restrictions.like("custId", "%"+custId+"%") );
		}
		if(waybill !=null){
			crit.add(Restrictions.like("waybill", "%"+waybill+"%"));
		}
		if(raterName!=null){
			crit.add(Restrictions.like("raterName", "%"+raterName+"%"));
		}
		if(sdName!=null){
			crit.add(Restrictions.like("sender", "%"+sdName+"%"));
		}
		if(bitch !=null){
			crit.add(Restrictions.like("bitch", "%"+bitch+"%"));
		}
		if(lineId !=null){
			crit.add(Restrictions.like("lineId", "%"+lineId+"%"));
		}
		if(mark !=null){
			crit.add(Restrictions.like("mark", "%"+mark+"%"));
		}
		if(procurator!=null){
			crit.add(Restrictions.like("procurator", "%"+procurator+"%"));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
		
		return pageMap;
	}
	//所有运单到货查询，用在货物跟踪Waybill_track.jsp
	public Map findTrack(String lineId,String custId,String waybill,String raterName,String sdName,String bitch,String procurator,Integer status,Date stDate,Date edDate){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);	
		if(custId !=null){
			crit.add(Restrictions.like("custId", "%"+custId+"%") );
		}
		if(waybill !=null){
			crit.add(Restrictions.like("waybill", "%"+waybill+"%"));
		}
		if(raterName!=null){
			crit.add(Restrictions.like("raterName", "%"+raterName+"%"));
		}
		if(sdName!=null){
			crit.add(Restrictions.like("sender", "%"+sdName+"%"));
		}
		if(bitch !=null){
			crit.add(Restrictions.like("bitch", "%"+bitch+"%"));
		}
		if(lineId !=null){
			crit.add(Restrictions.like("lineId", "%"+lineId+"%"));
		}
		if(procurator!=null){
			crit.add(Restrictions.like("procurator", "%"+procurator+"%"));
		}
		if(status!=null){
			crit.add(Restrictions.eq("statusId", +status));
		}
		if(stDate!=null)                        //ge查询制定时间之后的记录  
			crit.add(Restrictions.ge("sddate",stDate));  
		if(edDate!=null)                          //le查询指定时间之前的记录  
			  crit.add(Restrictions.le("sddate",edDate));  
		crit.addOrder(Order.desc("id"));
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	
		
		return pageMap;
	}
	//根据批次的运单状态查询，用在配仓管理Waybill_marshall.jsp
	public Map findByStatusId(String waybill,String sdName,String bitch,Integer statusId){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);			
		crit.add(Restrictions.like("waybill", "%"+waybill+"%"))
			.add(Restrictions.eq("bitch", bitch))
			
			.add(Restrictions.eq("statusId", statusId))
			.add(Restrictions.like("sender", "%"+sdName+"%"));
		
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	

		return pageMap;
	}
	//根据核销编号查询，用在核销运单管理
	public Map findByRebateId(Integer rebateId,Integer page,Integer rows){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);			
		crit.add(Restrictions.eq("rebateId", rebateId));
		
		
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		crit.setFirstResult((page-1)*rows);
		crit.setMaxResults(rows);
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	

		return pageMap;
	}
	//根据批次、发货人、发货日期查询，用在配仓管理
	public Map findBySenderAndSddate(String bitch,String sender,Date sddate,Integer statusId){
		
		Map<String,Object> pageMap = new HashMap<String,Object>();		
		Criteria crit = getSession().createCriteria(Waybill.class);
		if(sender!=null)  {
		crit.add(Restrictions.like("sender", "%"+sender+"%"));
		}
		if(sddate!=null)  {
			crit.add(Restrictions.eq("sddate", sddate));
		}
		crit.add(Restrictions.eq("bitch", bitch));
		crit.add(Restrictions.eq("statusId", statusId));
		Long rowCount = (Long) crit.setProjection(Projections.rowCount()).uniqueResult();  //执行查询记录行数
		crit.setProjection(null);
		
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		pageMap.put("rows",comps);
		pageMap.put("total",rowCount);	

		return pageMap;
	}
	public List findByRebateId(Integer rebateId){		
		Criteria crit = getSession().createCriteria(Waybill.class);			
		crit.add(Restrictions.eq("rebateId", rebateId));
		
		List<Waybill> comps = (List<Waybill>)crit.list();
		return comps;
	}
	public List findByBitchAndStatusId(String bitch,Integer statusId){
		Criteria crit = getSession().createCriteria(Waybill.class);			
		crit.add(Restrictions.eq("bitch", bitch))
			.add(Restrictions.eq("statusId", statusId));
		List<Waybill> comps = (List<Waybill>)crit.list();
		return comps;
	}
	public Boolean isBillExsit(String waybill){
		boolean isExsit = false;
		List list = null;
			
			try {
				String queryString = "from Waybill w where w.waybill =:waybill";
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
