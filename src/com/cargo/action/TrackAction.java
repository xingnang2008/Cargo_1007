package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.cargo.model.Track;
@Component
public class TrackAction extends BaseAction<Track> {
	
	private String waybills;
	private Date tdate;
	private Integer editId;
	private Integer md;
	private Double drate;
	private Date outsddate;
	private Integer outindate;
	private Double outdelayrate;
	private Integer indate;
	private InputStream downStream;
	
	public void save(){
		this.trackService.save(model);
	}
	public void update(){
		
		this.trackService.update(model);
	
	}
	public String deleteByIds(){
		this.trackService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	//查询到货记录
	public String find(){	
		pageMap=this.trackService.find(model.getCustId(), model.getBitch(),model.getWaybill(), model.getSender(),model.getRater() ,model.getLineId(), stdate, enddate);
		return "jsonMap";
	}
	public String updateWaybillsArr(){
		
		this.trackService.updateWaybillsArr(waybills,tdate);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public String updateAppOn(){
		trackService.updateAppOn(ids,editId);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	public String createTrRecord(){
		trackService.updateCreatRecord(ids,md,drate,indate,outsddate,outindate,outdelayrate);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}

	public String inputFromExcel(){
		try {
			String returnMarks= this.trackService.checkExcel(excelFile, excelFileFileName);
			if(returnMarks == ""){
				returnMarks  =this.trackService.saveInputExcel(excelFile, excelFileFileName);
				if(returnMarks==""){
					return SUCCESS;
				}else {
					addActionError("数据导入时有问题出现：\n\r");
					addActionError(returnMarks);
					
				}
			}else {
				addActionError("数据未通过导入前的检查：\n\r");
				addActionError(returnMarks);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return "ERROR";
	}
	public String download(){
		downStream = trackService.getInputStream(ids);
		return "downStream";
	}
	
	
	public InputStream getDownStream() {
		return downStream;
	}
	public void setDownStream(InputStream downStream) {
		this.downStream = downStream;
	}
	public String getWaybills() {
		return waybills;
	}
	public void setWaybills(String waybills) {
		this.waybills = waybills;
	}
	public Date getTdate() {
		return tdate;
	}
	public void setTdate(Date tdate) {
		this.tdate = tdate;
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
	public Integer getEditId() {
		return editId;
	}
	public void setEditId(Integer editId) {
		this.editId = editId;
	}
	public Date getOutsddate() {
		return outsddate;
	}
	public void setOutsddate(Date outsddate) {
		this.outsddate = outsddate;
	}
	public Integer getOutindate() {
		return outindate;
	}
	public void setOutindate(Integer outindate) {
		this.outindate = outindate;
	}
	public Double getOutdelayrate() {
		return outdelayrate;
	}
	public void setOutdelayrate(Double outdelayrate) {
		this.outdelayrate = outdelayrate;
	}
	
}
