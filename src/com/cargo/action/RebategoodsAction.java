package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Rebategoods;
@Component
public class RebategoodsAction extends BaseAction<Rebategoods> {
	
	public void save(){
		System.out.println("model:" +model.getRebateId());
		this.rebategoodsService.save(model);
	}
	public String saveInput(){
				
		return SUCCESS;
	}
	public void update(){
		this.rebategoodsService.update(model);
	}
	public String updateInput(){
		
		return SUCCESS;
	}
	public String deleteByIds(){
		this.rebategoodsService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}

	public String listByRebateId(){
		System.out.println("rebateId :"+model.getRebateId());
		pageMap = new HashMap<String,Object>();	
		List<Rebategoods> rgList=this.rebategoodsService.listByRebateId(model.getRebateId(), page, rows);
		pageMap.put("total", this.rebategoodsService.getCountByRebateId(model.getRebateId()));
		pageMap.put("rows", rgList);
		return "jsonMap";
	}
	
	
}
