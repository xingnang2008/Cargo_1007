package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.sound.sampled.Line;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cargo.model.User;
@Component
public class UserAction extends BaseAction<User> {
	
	public void save(){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String password =md5.encodePassword(model.getPassword(), model.getName());
		model.setPassword(password);
		System.out.println("Service:"+password);	 //--------------------	
		this.userService.save(model);
	}
	public void update(){
		this.userService.update(model);
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
		userService.find(model.getName(), model.getUsername());
		return "jsonMap";
	}

	public String deleteByIds(){
		userService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	

}
