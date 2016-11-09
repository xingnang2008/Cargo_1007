package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cargo.model.Role;
import com.cargo.model.User;
@Component
public class UserAction extends BaseAction<User> {
	private String passwd;
	private Integer[] rids;
	private Integer rid;
	public void save(){
		Role role = null;
		Set<Role> roles = new HashSet<Role>();
		for (int i = 0; i < rids.length; i++) {
			Integer rid =rids[i];
			System.out.println(rid);
			role = roleService.findById(rid);
			roles.add(role);
		}
		model.setRoles(roles);
		
		this.userService.save(model);
	}
	public void update(){
		this.userService.update(model);
	}
	public String updateRole(){
		
		String[] item = ids.split(","); 
			 
		for(int i=0;i<item.length;i++){
			User user = userService.findById(Integer.parseInt(item[i]));
			
			Set<Role> roles = new HashSet<Role>();
			Role role = roleService.findById(rid);
			roles.add(role);
			
			user.setRoles(roles);
			this.userService.update(user);
		}
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
				
	}
	public String refreshUser(){
		String[] item = ids.split(","); 
		
		for(int i=0;i<item.length;i++){
			 System.out.println("ids: "+item[i]);
			User user = userService.findById(Integer.parseInt(item[i]));
			this.userService.refreshUser(user,passwd);			
		}
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
		
	}
	public void delete(){
		this.userService.delete(model);
	}
	public List<User> findAll(){
		return this.userService.findAll();
	}
	public String listAll(){
		jsonList = this.userService.findAll();
		return "jsonList";
	}
	public String find(){
		pageMap = userService.find();
		return "jsonMap";
	}

	public String deleteByIds(){
		userService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}

	
	
	
	
	
	
	
	
	

	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Integer[] getRids() {
		return rids;
	}
	public void setRids(Integer[] rids) {
		this.rids = rids;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	
	

}
