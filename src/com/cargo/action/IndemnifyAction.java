package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cargo.model.Indemnify;
import com.cargo.model.Receipt;
import com.cargo.model.Track;
import com.cargo.model.Waybill;
@Component
public class IndemnifyAction extends BaseAction<Indemnify> {
	private String waybills;
	private Integer approvalId;
	private InputStream downStream;
	
	
	public void save(){
		this.indemnifyService.save(model);
	}
	public void update(){
		this.indemnifyService.update(model);
	}
	
	public void delete(){
		this.indemnifyService.delete(model);
	}
	public String createIndemnify(){
		this.indemnifyService.createIndemnify(waybills);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public List<Indemnify> findAll(){
		return this.indemnifyService.findAll();
	}
	public String find(){
		pageMap = this.indemnifyService.find(model.getCustId(), model.getBitch(), model.getWaybill(), model.getSender(), model.getRater(), model.getLineId(), stdate, enddate);
		return "jsonMap";
	}
	public String deleteByIds(){
		this.indemnifyService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	
	//审核赔偿记录
	public String approvalIndemnify(){
		this.indemnifyService.updateApprovalIndemnify(ids, approvalId);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	public String inputFromExcel(){
		try {
			String returnMarks= this.indemnifyService.checkExcel(excelFile, excelFileFileName);
			if(returnMarks == ""){
				returnMarks  =this.indemnifyService.saveInputExcel(excelFile, excelFileFileName);
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
		downStream = indemnifyService.getInputStream(waybills);
		return "downStream";
	}
	public String getWaybills() {
		return waybills;
	}
	public void setWaybills(String waybills) {
		this.waybills = waybills;
	}

	public InputStream getDownStream() {
		return downStream;
	}
	public void setDownStream(InputStream downStream) {
		this.downStream = downStream;
	}
	public Integer getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Integer approvalId) {
		this.approvalId = approvalId;
	}
}
