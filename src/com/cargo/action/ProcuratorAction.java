package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Procurator;
import com.cargo.model.Waybill;
@Component
public class ProcuratorAction extends BaseAction<Procurator> {
	public void save(){
		this.procuratorService.save(model);
	}
	public void update(){
		this.procuratorService.update(model);
	}
	public void delete(){
		this.procuratorService.delete(model);
	}
	public List<Procurator> findAll(){
		return this.procuratorService.findAll();
	}
	public String listAll(){
		jsonList = this.procuratorService.findAll();
		return "jsonList";
	}
	
	public String listByProcurator(){
		pageMap = new HashMap<String,Object>();	
		List<Procurator> list =this.procuratorService.listByProcurator(model.getName(),page,rows);							
		pageMap.put("total",procuratorService.getCountByProcurator(model.getName()));	 //			
		pageMap.put("rows",list);		
		return "jsonMap"; 
	}
	public String deleteByIds(){
		this.procuratorService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());	
		return "stream";
	}

}
