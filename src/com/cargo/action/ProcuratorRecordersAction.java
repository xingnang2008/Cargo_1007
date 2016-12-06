package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.ProcuratorRecorders;
@Component
public class ProcuratorRecordersAction extends BaseAction<ProcuratorRecorders> {
	
	public void save(){
		this.procuratorRecordersService.save(model);
	}
	public void update(){
		this.procuratorRecordersService.update(model);
	}
	public void delete(){
		this.procuratorRecordersService.delete(model);
	}
	public List<ProcuratorRecorders> findAll(){
		return this.procuratorRecordersService.findAll();
	}
	public String listAll(){
		jsonList = this.procuratorRecordersService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap=this.procuratorRecordersService.find(model.getSender(),model.getProcurator(), stdate, enddate);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		procuratorRecordersService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	

}
