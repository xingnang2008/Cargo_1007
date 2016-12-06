package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.AdvanceRecorders;
@Component
public class AdvanceRecordersAction extends BaseAction<AdvanceRecorders> {
	
	public void save(){
		this.advanceRecordersService.save(model);
	}
	public void update(){
		this.advanceRecordersService.update(model);
	}
	public void delete(){
		this.advanceRecordersService.delete(model);
	}
	public List<AdvanceRecorders> findAll(){
		return this.advanceRecordersService.findAll();
	}
	public String listAll(){
		jsonList = this.advanceRecordersService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap=this.advanceRecordersService.find(model.getSender(),model.getCustId(), stdate, enddate);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		advanceRecordersService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	

}
