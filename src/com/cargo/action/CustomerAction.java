package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Customer;
import com.cargo.model.Dest;
@Component
public class CustomerAction extends BaseAction<Customer> {
	
	
	public void save(){
		this.customerService.save(model);
	}
	public void update(){
		this.customerService.update(model);
	}
	
	public String deleteByIds(){
		this.customerService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public String listAll(){
		jsonList = this.customerService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap = this.customerService.find(model.getCustId(),model.getName(), model.getTelphone());
			
		return "jsonMap"; 
	}
}
