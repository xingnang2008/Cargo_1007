package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.cargo.model.Quote;

@Component
public class QuoteAction extends BaseAction<Quote> {
	private InputStream downReport;

	public void save(){
		this.quoteService.save(model);
	}
	public void update(){
		this.quoteService.update(model);
	}
	public void delete(){
		this.quoteService.delete(model);
	}
	public List<Quote> findAll(){
		return this.quoteService.findAll();
	}
	public String listAll(){
		jsonList = this.quoteService.findAll();
		return "jsonList";
	}
	
	public String deleteByIds(){		
		this.quoteService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public String find(){
		pageMap=this.quoteService.find(model.getProduct(),model.getHsCodeRu());
		return "jsonMap";
	}
	
	//更新报价表
	public String updateByExcel(){
		try {
			String returnMarks= this.quoteService.checkExcel(excelFile, excelFileFileName);
			if(returnMarks == ""){
				returnMarks  =this.quoteService.updateInputExcel(excelFile, excelFileFileName);
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
		
		downReport = quoteService.getDownReport();
		return downReport;
	}
	public void setDownReport(InputStream downReport) {
		this.downReport = downReport;
	}
	
	
	
}
