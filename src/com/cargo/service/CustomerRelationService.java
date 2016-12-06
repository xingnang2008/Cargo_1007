package com.cargo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.CustomerRelationDao;
import com.cargo.model.Customer;
import com.cargo.model.CustomerRelation;
import com.cargo.model.Procurator;
import com.cargo.model.Rater;
@Component
public class CustomerRelationService {
	@Resource
	private CustomerRelationDao customerRelationDao;
	
	public CustomerRelationDao getCompanyDao() {
		return customerRelationDao;
	}

	public void setCompanyDao(CustomerRelationDao customerRelationDao) {
		this.customerRelationDao = customerRelationDao;
	}

	
	public void save(CustomerRelation customerRelation){
		this.customerRelationDao.save(customerRelation);
	}
	
	public void delete(CustomerRelation customerRelation){
		this.customerRelationDao.delete(customerRelation);
	}
	public void update(CustomerRelation customerRelation){
		this.customerRelationDao.update(customerRelation);
	}
	public List<CustomerRelation> findAll(){
		return this.customerRelationDao.findAll();
		
	}
	public void deleteByIds(String ids){
		this.customerRelationDao.deleteByIds(ids);
	}
	public List<Customer> findCustBySenderId(Integer senderId){
		return customerRelationDao.findCustBySenderId(senderId);
	}
	public List<Rater> findRaterBySenderId(Integer senderId){
		return customerRelationDao.findRaterBySenderId(senderId);
	}
	public Map find(Integer custId,Integer senderId,Integer raterId,Integer procuratorId ){
		return this.customerRelationDao.find(custId, senderId, raterId, procuratorId);
	}
	public Long getCountRelationById(Integer senderId){
		return this.customerRelationDao.getCountRelationById( senderId);
	}
	public List findRelationById(Integer senderId,Integer page,Integer rows){
		return this.customerRelationDao.findRelationById(senderId, page, rows);
	}
	public List<Procurator> findProcuratorBySenderId(Integer senderId){
		return customerRelationDao.findProcuratorBySenderId(senderId);
	}
	public Map findRelationByCustId(Integer custId){
		return customerRelationDao.findRelationByCustId(custId);
	}
}
