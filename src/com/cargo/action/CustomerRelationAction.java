package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cargo.model.Customer;
import com.cargo.model.CustomerRelation;
import com.cargo.model.Procurator;
import com.cargo.model.Rater;
@Component
public class CustomerRelationAction extends BaseAction<CustomerRelation> {
	private List<Customer> custList;
	private List<Rater> raterList;
	private List<Procurator> procuratorList;
	private Integer custId;
	private Integer senderId;
	private Integer raterId;
	private Integer procuratorId;
					
	
	public void save(){
		model.setCustomer(customerService.findById(custId));
		model.setSender(senderService.findById(senderId));
		model.setRater(raterService.findById(raterId));
		model.setProcurator(procuratorService.findById(procuratorId));
		
		this.customerRelationService.save(model);
	}
	public void update(){
		this.customerRelationService.update(model);
	}
	public void delete(){
		this.customerRelationService.delete(model);
	}
	public List<CustomerRelation> findAll(){
		return this.customerRelationService.findAll();
	}
	public String listAll(){
		jsonList = this.customerRelationService.findAll();
		return "jsonList";
	}
	
	public String deleteByIds(){
		this.customerRelationService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public String listCustBySenderId(){
		System.out.println("SenderAction id "+senderId);
		custList = customerRelationService.findCustBySenderId(senderId);
		return "custList";
	}
	public String listRaterBySenderId(){
		System.out.println("SenderAction id "+senderId);
		raterList = customerRelationService.findRaterBySenderId(senderId);
		return "raterList";
	}
	public String listProcuratorBySenderId(){
		procuratorList = customerRelationService.findProcuratorBySenderId(senderId);
		return "procuratorList";
	}
	
	public String find(){
	
		/*
		pageMap = new HashMap<String,Object>();	
		List<Dest> list =this.customerRelationService.findRelationById(senderId, page, rows);						
		pageMap.put("total",customerRelationService.getCountRelationById(senderId));	 //			
		pageMap.put("rows",list);
		*/
		pageMap =customerRelationService.find(custId, senderId, raterId,procuratorId);
		return "jsonMap"; 
	}
	public String findByCustId(){
		pageMap =customerRelationService.findRelationByCustId(custId);
		return "jsonMap"; 
	}
	
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public List<Customer> getCustList() {
		return custList;
	}
	public void setCustList(List<Customer> custList) {
		this.custList = custList;
	}
	public List<Rater> getRaterList() {
		return raterList;
	}
	public void setRaterList(List<Rater> raterList) {
		this.raterList = raterList;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getRaterId() {
		return raterId;
	}
	public void setRaterId(Integer raterId) {
		this.raterId = raterId;
	}
	public Integer getProcuratorId() {
		return procuratorId;
	}
	public void setProcuratorId(Integer procuratorId) {
		this.procuratorId = procuratorId;
	}
	public List<Procurator> getProcuratorList() {
		return procuratorList;
	}
	public void setProcuratorList(List<Procurator> procuratorList) {
		this.procuratorList = procuratorList;
	}

}
