package com.cargo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.RebatecustomerDao;
import com.cargo.model.Customer;
import com.cargo.model.Rebatecustomer;

@Component
public class RebatecustomerService {
	@Resource
	private RebatecustomerDao rebatecustomerDao;
	
	
	public RebatecustomerDao getRebatecustomerDao() {
		return rebatecustomerDao;
	}

	public void setRebatecustomerDao(RebatecustomerDao rebatecustomerDao) {
		this.rebatecustomerDao = rebatecustomerDao;
	}

	public void save(Rebatecustomer rebatecustomer){
		this.rebatecustomerDao.save(rebatecustomer);
	}
	
	public void delete(Rebatecustomer rebatecustomer){
		this.rebatecustomerDao.delete(rebatecustomer);
	}
	public void update(Rebatecustomer rebatecustomer){
		this.rebatecustomerDao.update(rebatecustomer);
	}
	public List<Rebatecustomer> findAll(){
		return this.rebatecustomerDao.findAll();
		
	}
	
	public Rebatecustomer findById(java.lang.Integer id) {
		return this.rebatecustomerDao.findById(id);
	}
	public void deleteByIds(String ids){
		this.rebatecustomerDao.deleteByIds(ids);
	}
	
	public List listByCustId(String custId,Integer page,Integer rows){
		return this.rebatecustomerDao.listCustId(custId, page, rows);
	}
	public Long getCountByCustId(String custId){
		return this.rebatecustomerDao.getCount(custId);
		
	}
}
