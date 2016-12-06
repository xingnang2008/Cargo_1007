package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Bitch;
import com.cargo.model.Waybill;

@Component
public class BitchAction extends BaseAction<Bitch> {

	public void save(){
		this.bitchService.save(model);
	}
	public void update(){
		this.bitchService.update(model);
	}
	public void delete(){
		this.bitchService.delete(model);
	}
	public List<Bitch> findAll(){
		return this.bitchService.findAll();
	}
	public String listAll(){
		jsonList = this.bitchService.findAll();
		return "jsonList";
	}
	public List<Waybill> queryWaybillByBitch(){	
		System.out.println(model.getBitch());
		return this.bitchService.queryWaybillByBitch(model.getBitch());
	}
	public String listByLine(){
		jsonList =this.bitchService.queryBitchByLine(model.getLineId());
		return "jsonList"; 
	}
	public String deleteByIds(){
		List<Bitch> bitchList = this.bitchService.listByIds(ids);
		List list =new ArrayList();
		for(Bitch b : bitchList){
			if(this.waybillService.listByBitch(b.getBitch()).size()!=0){
				list.add(this.waybillService.listByBitch(b.getBitch()));
			}
			
		}
		
		if(list.size()==0){
		this.bitchService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		
		}else{
			inputStream = new ByteArrayInputStream("false".getBytes());;
		}
		return "stream";
	}
	public String listByLineId(){
		pageMap= this.bitchService.find(model.getLineId(), stdate, enddate);
		return "jsonMap";
	}
	public String listByDate(){
		jsonList = bitchService.findBySdDate(stdate, enddate);
		return "jsonList";
	}
}
