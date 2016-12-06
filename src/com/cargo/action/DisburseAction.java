package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Disburse;
@Component
public class DisburseAction extends BaseAction<Disburse> {
	
	public void save(){
		this.disburseService.save(model);
	}
	public void update(){
		this.disburseService.update(model);
	}
	public void delete(){
		this.disburseService.delete(model);
	}
	public List<Disburse> findAll(){
		return this.disburseService.findAll();
	}
	public String listAll(){
		jsonList = this.disburseService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap=this.disburseService.find(model.getLineId(),model.getSortId(),model.getPayMethod(), stdate, enddate);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		disburseService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	

}
