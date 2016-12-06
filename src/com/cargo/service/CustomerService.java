package com.cargo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cargo.dao.CustomerDao;
import com.cargo.model.Customer;

@Component("customerService")
public class CustomerService {
	@Resource
	private CustomerDao customerDao;
	
	public void save(Customer customer){
		this.customerDao.save(customer);
	}
	public void delete(Customer customer){
		this.customerDao.delete(customer);
	}
	public void update(Customer customer){
		this.customerDao.update(customer);
	}
	public List findAll(){
		return this.customerDao.findAll();
	}
	public Customer findById(java.lang.Integer id) {
		return this.customerDao.findById(id);
	}
	public void deleteByIds(String ids){
		this.customerDao.deleteByIds(ids);
	}
	public List isCustomerExist(String name){
		return this.customerDao.isCustomerExist(name);
	}
	public Customer findByName(String name) {
		return this.customerDao.findByName(name);
	}
	public Customer findByCustId(String custId){
		return this.customerDao.findByCustId(custId);
	}
	public List listByCustId(String custId,Integer page,Integer rows){
		return this.customerDao.listByCustId(custId, page, rows);
	}
	public Long getCountByCustId(String custId){
		return this.customerDao.getCountByCustId(custId);
		
	}
	public Map find(String custId,String name,String telphone){
		return this.customerDao.find(custId, name, telphone);
	}
}
