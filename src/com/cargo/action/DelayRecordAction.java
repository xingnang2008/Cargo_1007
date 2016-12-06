package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import com.cargo.model.Bitch;
import com.cargo.model.DelayRecord;

public class DelayRecordAction extends BaseAction<DelayRecord> {
	private String waybills;
	private Integer md;
	private Double drate;
	private Integer indate;

	public void save(){
		this.delayRecordService.save(model);
	}
	public void update(){
		this.delayRecordService.update(model);
	}
	public void delete(){
		this.delayRecordService.delete(model);
	}
	public List<DelayRecord> findAll(){
		return this.delayRecordService.findAll();
	}
	public String listAll(){
		jsonList = this.delayRecordService.findAll();
		return "jsonList";
	}
	public String deleteByIds(){
		
		this.delayRecordService.deleteByIds(ids,waybills);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public String createFromForm(){
		System.out.println("DelayRecordAction--ids:"+ids);
		System.out.println("DelayRecordAction--md:"+md);
		System.out.println("DelayRecordAction--drate:"+drate);
		System.out.println("DelayRecordAction--inDate:"+indate);
		delayRecordService.updateCreatRecord(ids,md,drate,indate);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	
	public String getWaybills() {
		return waybills;
	}
	public void setWaybills(String waybills) {
		this.waybills = waybills;
	}
	public Integer getMd() {
		return md;
	}
	public void setMd(Integer md) {
		this.md = md;
	}
	public Double getDrate() {
		return drate;
	}
	public void setDrate(Double drate) {
		this.drate = drate;
	}
	public Integer getIndate() {
		return indate;
	}
	public void setIndate(Integer indate) {
		this.indate = indate;
	}
	
}
