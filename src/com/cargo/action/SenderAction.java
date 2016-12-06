package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Sender;
import com.cargo.model.Waybill;
@Component
public class SenderAction extends BaseAction<Sender> {
	public void save(){
		this.senderService.save(model);
	}
	public void update(){
		this.senderService.update(model);
	}
	public void delete(){
		this.senderService.delete(model);
	}
	public List<Sender> findAll(){
		return this.senderService.findAll();
	}
	public String listAll(){
		jsonList = this.senderService.findAll();
		return "jsonList";
	}
	public List<Waybill> queryWaybillBySender(){		
		return this.waybillService.queryBySender(model.getSdName());
	}
	public String listBySender(){
		pageMap = new HashMap<String,Object>();	
		List<Sender> list =this.senderService.listBySender(model.getSdName(),page,rows);							
		pageMap.put("total",senderService.getCountBySender(model.getSdName()));	 //			
		pageMap.put("rows",list);		
		return "jsonMap"; 
	}
	public String deleteByIds(){
		this.senderService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());	
		return "stream";
	}

}
