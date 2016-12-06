package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Bitch;
import com.cargo.model.Mark;
@Component
public class MarkAction extends BaseAction<Mark> {
	
	public void save(){
		this.markService.save(model);
	}
	public void update(){
		this.markService.update(model);
	}
	public void delete(){
		this.markService.delete(model);
	}
	public List<Bitch> findAll(){
		return this.markService.findAll();
	}
	public String listAll(){
		jsonList = this.markService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap=this.markService.find(model.getType(), page, rows);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		markService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	

}
