package com.cargo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.AdvanceRecordersDao;
import com.cargo.model.AdvanceRecorders;
import com.cargo.model.Waybill;

@Component("advanceRecorderService")
public class AdvanceRecordersService {
	private AdvanceRecordersDao advanceRecordersDao;

	public AdvanceRecordersDao getDao() {
		return advanceRecordersDao;
	}
	@Resource
	public void setDao(AdvanceRecordersDao dao) {
		this.advanceRecordersDao = dao;
	}
	
	public void save(AdvanceRecorders instance){
		this.advanceRecordersDao.save(instance);
	}
	public void save(Waybill Instance,Date date) {
		this.advanceRecordersDao.save(Instance,date);
	}
	public void delete(AdvanceRecorders instance){
		this.advanceRecordersDao.delete(instance);
	}
	public void deleteByWaybill(String waybill) {
		this.advanceRecordersDao.deleteByWaybill(waybill);
	}
	public void update(AdvanceRecorders instance){
		this.advanceRecordersDao.update(instance);
	}
	public List findAll(){
		return this.advanceRecordersDao.findAll();
	}

	public void deleteByIds(String ids){
		this.advanceRecordersDao.deleteByIds(ids);
	}
	public Map find(String sender,String custId,Date stDate,Date edDate){
		return this.advanceRecordersDao.find(sender, custId, stDate, edDate);
	}

	public AdvanceRecorders findById(Integer id){
		return this.advanceRecordersDao.findById(id);
	}
	
}
