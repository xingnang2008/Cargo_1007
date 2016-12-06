package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import com.cargo.model.Bitch;
import com.cargo.model.Rater;

public class RaterAction extends BaseAction<Rater> {
	public void save(){
		this.raterService.save(model);
	}
	public void update(){
		this.raterService.update(model);
	}
	public void delete(){
		this.raterService.delete(model);
	}
	public List<Rater> findAll(){
		return this.raterService.findAll();
	}
	public String listAll(){
		jsonList = this.raterService.findAll();
		return "jsonList";
	}
	public String deleteByIds(){
		this.raterService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());	
		return "stream";
	}
	public String listByRaterName(){
		System.out.println(model.getRaterName());
		pageMap = this.raterService.find(model.getRaterName(), page, rows);
		return "jsonMap"; 
	}
	
	
	
}
