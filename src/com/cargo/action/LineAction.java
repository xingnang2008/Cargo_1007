package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Bitch;
import com.cargo.model.Line;
import com.cargo.model.Rebate;
@Component
public class LineAction extends BaseAction<Line> {
	
	public void save(){
		this.lineService.save(model);
	}
	public void update(){
		this.lineService.update(model);
	}
	public void delete(){
		this.lineService.delete(model);
	}
	public List<Bitch> findAll(){
		return this.lineService.findAll();
	}
	public String listAll(){
		jsonList = this.lineService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap=this.lineService.find(model.getLineId(), page, rows);
		return "jsonMap";
	}
	public String listByLineId(){
		pageMap = new HashMap<String,Object>();			
		List rebateList = lineService.listBylineId(model.getLineId(),page,rows);			
		pageMap.put("total",lineService.getCountByLineId(model.getLineId()));	 //			
		pageMap.put("rows",rebateList);
			System.out.println("pageMap: "+pageMap.toString());		 
		return "jsonMap";
	}
	public String deleteByIds(){
		lineService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	

}
