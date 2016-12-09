package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import com.cargo.model.Bitch;
import com.cargo.model.Dest;

public class DestAction extends BaseAction<Dest> {

	public void save(){
		this.destService.save(model);
	}
	public void update(){
		this.destService.update(model);
	}
	public void delete(){
		this.destService.delete(model);
	}
	public List<Dest> findAll(){
		return this.destService.findAll();
	}
	public String listAll(){
		jsonList = this.destService.findAll();
		return "jsonList";
	}
	public String deleteByIds(){
		this.destService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
			
		return "stream";
	}
	public String listByDestName(){
		
		pageMap = new HashMap<String,Object>();	
		List<Dest> list =this.destService.listByDestName(model.getDestName(),page,rows);							
		pageMap.put("total",destService.getCountByDest(model.getDestName()));	 //			
		pageMap.put("rows",list);		
		return "jsonMap"; 
	}
}
