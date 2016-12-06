package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Rebate;
import com.cargo.model.Rebategoods;
@Component
public class RebateAction extends BaseAction<Rebate> {
	private List<Rebategoods> goodsList;
	private List<Rebate> rebateList;
	private InputStream downRebateReport;
	
	public void save(){
		this.rebateService.save(model);
	}
	public void update(){
		this.rebateService.update(model);
	}
	
	public String deleteByIds(){
		List list = this.rebategoodsService.listByIds(ids);
		if(list.size()==0){
		this.rebateService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		
		}else{
			inputStream = new ByteArrayInputStream("false".getBytes());;
		}
		return "stream";
	}
	public String listCustId(){
	
		pageMap = new HashMap<String,Object>();			
		List<Rebate> rebateList = rebateService.listByCustId(model.getCustId(),page,rows);			
		pageMap.put("total",rebateService.getCountByCustId(model.getCustId()));	 //			
		pageMap.put("rows",rebateList);
			System.out.println("pageMap: "+pageMap.toString());		 
		return "jsonMap";
	}
	public String listInput(){
		
		return "input";
	}
	
	public String queryGoodsByRebateId(){
		pageMap = new HashMap<String,Object>();		
		List<Rebategoods> rebateList =this.rebategoodsService.listByRebateId(model.getId(),page,rows);			
		pageMap.put("total",rebategoodsService.getCountByRebateId(model.getId()));	 //			
		pageMap.put("rows",rebateList);
			System.out.println("pageMap: "+pageMap.toString());		 
		return "jsonMap";
		
	}
	public String inputFromExcel(){
			System.out.println("File is null? "+ (excelFile == null));
			System.out.println("FileName is "+ excelFileFileName );
			try {
				String returnMarks= this.rebateService.checkExcel(excelFile, excelFileFileName);
				if(returnMarks == ""){
					returnMarks  =this.rebateService.saveInputExcel(excelFile, excelFileFileName);
					if(returnMarks==""){
						return SUCCESS;
					}
				}else {
					addActionError(returnMarks);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
			return "ERROR";
		}
	@Override
	public InputStream getDownloadFile() {		
		return this.rebateService.getInputStream(rebateService.listByIds(ids));
	}
	
	public String downloadReport(){
		downRebateReport=this.rebateService.getInputStream(rebateService.listByIds(ids));
		return "download";
	}
	public String getGoods(){
		model = this.rebateService.findById(model.getId());
		goodsList = this.rebategoodsService.listByRebateId(model.getId());
		
		return SUCCESS;
	}
	public String getGoodsInput(){
		rebateList = this.rebateService.findAll();
				
		return "input";
	}	
	
	public List<Rebategoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Rebategoods> goodsList) {
		this.goodsList = goodsList;
	}
	public List<Rebate> getRebateList() {
		return rebateList;
	}
	public void setRebateList(List<Rebate> rebateList) {
		this.rebateList = rebateList;
	}
	public InputStream getDownRebateReport() {
		return downRebateReport;
	}
	public void setDownRebateReport(InputStream downRebateReport) {
		this.downRebateReport = downRebateReport;
	}
	
	

	
	
	
	
}
