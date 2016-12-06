package com.cargo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.ProcuratorRecordersDao;
import com.cargo.model.ProcuratorRecorders;
import com.cargo.model.Waybill;

@Component("procuratorRecordersService")
public class ProcuratorRecordersService {
	private ProcuratorRecordersDao procuratorRecordersDao;

	public ProcuratorRecordersDao getDao() {
		return procuratorRecordersDao;
	}
	@Resource
	public void setDao(ProcuratorRecordersDao dao) {
		this.procuratorRecordersDao = dao;
	}
	
	public void save(ProcuratorRecorders instance){
		this.procuratorRecordersDao.save(instance);
	}
	public void save(Waybill Instance,Date date) {
		this.procuratorRecordersDao.save(Instance,date);
	}
	public void delete(ProcuratorRecorders instance){
		this.procuratorRecordersDao.delete(instance);
	}
	public void deleteByWaybill(String waybill) {
		this.procuratorRecordersDao.deleteByWaybill(waybill);
	}
	public void update(ProcuratorRecorders instance){
		this.procuratorRecordersDao.update(instance);
	}
	public List findAll(){
		return this.procuratorRecordersDao.findAll();
	}

	public void deleteByIds(String ids){
		this.procuratorRecordersDao.deleteByIds(ids);
	}
	public Map find(String sender,String procurator,Date stDate,Date edDate){
		return this.procuratorRecordersDao.find(sender, procurator, stDate, edDate);
	}

	public ProcuratorRecorders findById(Integer id){
		return this.procuratorRecordersDao.findById(id);
	}
	
}
