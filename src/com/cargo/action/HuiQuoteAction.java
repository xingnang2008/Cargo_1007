package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.cargo.model.HuiQuote;

@Component
public class HuiQuoteAction extends BaseAction<HuiQuote> {
	private InputStream downReport;
	
	public void save(){
		this.huiQuoteService.save(model);
	}
	public void update(){
		this.huiQuoteService.update(model);
	}
	public void delete(){
		this.huiQuoteService.delete(model);
	}
	public List<HuiQuote> findAll(){
		return this.huiQuoteService.findAll();
	}
	public String listAll(){
		jsonList = this.huiQuoteService.findAll();
		return "jsonList";
	}

	public String deleteByIds(){
		huiQuoteService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	public String find(){
		pageMap=this.huiQuoteService.find(model.getCategory(),model.getProducts(),model.getTransType());
		return "jsonMap";
	}
	
	//更新报价表
	public String updateByExcel(){
		try {
			String returnMarks= this.huiQuoteService.checkExcel(excelFile, excelFileFileName);
			if(returnMarks == ""){
				returnMarks  =this.huiQuoteService.updateInputExcel(excelFile, excelFileFileName);
				if(returnMarks==""){
					return SUCCESS;
				}else {
					addActionError("数据更新时时有问题出现：\n\r");
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
	
	
	
	
	
	
	
	
	//导出报价表 的action
	public String downloadReport(){
		return "downreport";
	}
	
	
	public InputStream getDownReport() {
		downReport= huiQuoteService.getDownReport();
		return downReport;
	}
	public void setDownReport(InputStream downReport) {
		this.downReport = downReport;
	}
	
	
	
	
	
	
}
