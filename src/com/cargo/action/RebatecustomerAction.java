package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Dest;
import com.cargo.model.Rebatecustomer;
@Component
public class RebatecustomerAction extends BaseAction<Rebatecustomer> {
	
	
	public void save(){
		this.rebatecustomerService.save(model);
	}
	public void update(){
		this.rebatecustomerService.update(model);
	}
	
	public String deleteByIds(){
		this.rebatecustomerService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public String listAll(){
		jsonList = this.rebatecustomerService.findAll();
		return "jsonList";
	}
	public String listByCustId(){
		
		pageMap = new HashMap<String,Object>();	
		List<Dest> list =this.rebatecustomerService.listByCustId(model.getCustId(),page,rows);							
		pageMap.put("total",rebatecustomerService.getCountByCustId(model.getCustId()));	 //			
		pageMap.put("rows",list);		
		return "jsonMap"; 
	}
}
