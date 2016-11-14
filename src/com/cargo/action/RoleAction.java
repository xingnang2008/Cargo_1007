package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cargo.model.Role;
import com.cargo.model.User;
@Component
public class RoleAction extends BaseAction<Role> {
	
	private Integer[] rids;
	public void save(){
		this.roleService.save(model);
	}
	public void update(){
		this.roleService.update(model);
	}
	
	
	public void delete(){
		this.roleService.delete(model.getId());
	}
	public List<Role> findAll(){
		return this.roleService.findAll();
	}
	public String listAll(){
		jsonList = this.roleService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap = roleService.find();
		return "jsonMap";
	}

	public String deleteByIds(){
		roleService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	
	
	
	
	
	
	
	
	
	
	public Integer[] getRids() {
		return rids;
	}
	public void setRids(Integer[] rids) {
		this.rids = rids;
	}
	
	
	

}
