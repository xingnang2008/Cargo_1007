package com.cargo.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cargo.model.Bitch;
import com.cargo.model.Sender;
import com.cargo.model.Waybill;
import com.cargo.model.dto.WaybillGroupSender;
@Component
public class WaybillAction extends BaseAction<Waybill> {
	private Integer sid;  //用于配仓状态的，statusId号
	private Integer editId;
	private String cangid;
	private Date advanceDate; //结算垫付款日期
	private Date procuratorDate; //结算代理费日期
	private Integer raterStatusId;//用于结算代理费状态的Id号
	private Integer advanceStatusId;//用于结算垫付款状态的Id号
	private InputStream downReport;
	private InputStream downMakePrice; //生成做价表
	private InputStream downRaterFee; //生成按ids查询的代理费表
	private InputStream downAdvance; //生成按ids查询的垫付款
	private InputStream downCaiReport;
	private InputStream advanceReport;
	private InputStream downRaterReport;
	private InputStream allBitchReport;
	private InputStream downTrackReport;
	private InputStream downArrearagesReport;  //欠款
	private Integer reportType;
	private String bitches; //多个批次组成的字符串
	private String waybills;
	
	public void save(){
		Sender sender =senderService.findById(model.getSdId());
		model.setSdTel(sender.getPhone());
		model.setDestTel( destService.findByDestName(model.getDestName()).getPhone());
		this.waybillService.save(model);	
		
	}
	public void update(){
		Sender sender =senderService.findById(model.getSdId());
		model.setSdTel(sender.getPhone());
		model.setDestTel( destService.findByDestName(model.getDestName()).getPhone());
		this.waybillService.update(model);
	}
	//删除运单
	public String deleteByIds(){
		this.waybillService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	public String updateTrackOnByIds(){
		waybillService.updateTrackOnStauts(ids,sid);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	//锁定运单编辑
	public String updateLockOnWaybills(){
		waybillService.updateLockOnWaybills(ids,editId);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	public String listByBitchGroup(){
		pageMap = new HashMap<String,Object>();
		List<Bitch> bList = this.waybillService.listByBitchGroup(stdate, enddate);
		pageMap.put("total", bList.size());
		pageMap.put("rows",bList);
		return "jsonMap";
	}
	public String listByBitch(){
		jsonList =this.waybillService.listByBitch(model.getBitch());
		return "jsonList"; 
		
	}
	//配仓时按客户统计
	public String listByCustGroup(){
		pageMap = new HashMap<String,Object>();	
		List<WaybillGroupSender> list =this.waybillService.listByCustGroup(model.getBitch(),model.getStatusId());	
		pageMap.put("total",waybillService.getCountByCustGroup(model.getBitch()));	 //			
		pageMap.put("rows",list);		
		return "jsonMap"; 
	}
	//查看垫付款
	public String listAdvance(){
		pageMap =this.waybillService.listAdvanceWaybill(model.getSender(),model.getCustId(), model.getAdvanceStauts(), stdate, enddate);	
		return "jsonMap"; 
	}
	//查看代理费
	public String listRaterFee(){
		pageMap =this.waybillService.listRaterWaybill(model.getProcurator(), model.getRaterStauts(), stdate, enddate);	
		return "jsonMap"; 
	}
	//配仓更新状态
	public String updateTrackOnSendByIds(){
		waybillService.updateTrackOnSend(ids,sid,cangid);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	//更新运输状态
	public String updateWaybillStatus(){
		waybillService.updateWaybillStatusByWaybills(ids,sid);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	//更新运输状态 By Waybills 
	public String updateWaybillStatusByWaybills(){
		waybillService.updateWaybillStatusByWaybills(waybills,sid);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	//绿色通道更新批次
	public String updateBitch(){
		waybillService.updateBitch(ids, model.getBitch(),model.getLineId());
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	//更新代理费状态
	public String updateRaterStatus(){
		waybillService.updateRaterStatus(ids,raterStatusId,procuratorDate);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	//更新垫付款状态
	public String updateAdvanceStatus(){
		waybillService.updateAdvanceStatus(ids,advanceStatusId,advanceDate);
		inputStream = new ByteArrayInputStream("true".getBytes());		
		return "stream";
	}
	//查看欠款
	public String findArrearages(){
		pageMap=this.waybillService.listWaybillArrearages( model.getSender(),model.getCustName());
		return "jsonMap";
	}
	public String find(){
		pageMap=this.waybillService.find(model.getLineId(),model.getCustId(), model.getWaybill(), model.getRaterName(), model.getSender(), model.getBitch(),model.getProcurator(),model.getMark(), stdate, enddate);
		return "jsonMap";
	}
	public String findUnArrivalTrack(){
		pageMap=this.waybillService.listUnArrivalTrack(model.getLineId(),model.getCustId(), model.getWaybill(), model.getRaterName(), model.getSender(), model.getBitch(),model.getProcurator(),model.getStatusId(), stdate, enddate);
		return "jsonMap";
	}
	//用于查看运单 的财务数据
	public String findWaybillFee(){
		pageMap=this.waybillService.listWaybillFee(model.getLineId(),model.getCustId(), model.getWaybill(), model.getRaterName(), model.getSender(), model.getBitch(),model.getProcurator(),model.getStatusId(), stdate, enddate);
		return "jsonMap";
	}
	
	public String findTrack(){
		pageMap=this.waybillService.findTrack(model.getLineId(),model.getCustId(), model.getWaybill(), model.getRaterName(), model.getSender(), model.getBitch(),model.getProcurator(),model.getStatusId(), stdate, enddate);
		return "jsonMap";
	}
	public String findByStatusId(){
		pageMap=this.waybillService.findByStatusId( model.getWaybill(),model.getSender(), model.getBitch(),model.getStatusId());
		return "jsonMap";
	}
	public String findBySenderAndSddate(){
		pageMap=this.waybillService.findBySenderAndSddate(model.getBitch(), model.getSender(), model.getSddate(),model.getStatusId());
		return "jsonMap";
	}
	public String findByRebateId(){
		pageMap=this.waybillService.findByRebateId(model.getRebateId(), page, rows);
		return "jsonMap";
	}
	public String findExist(){
		inputStream =new ByteArrayInputStream("false".getBytes());	
		if(this.waybillService.isBillExsit(model.getWaybill())!=true){
			inputStream = new ByteArrayInputStream("true".getBytes());
		}
		return "stream";
	}
	public String inputFromExcel(){
		try {
			String returnMarks= this.waybillService.checkExcel(excelFile, excelFileFileName);
			if(returnMarks == ""){
				returnMarks  =this.waybillService.saveInputExcel(excelFile, excelFileFileName);
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
	public String inputFromMakePriceExcel(){
		try {
			String returnMarks= this.waybillService.checkMakePriceExcel(excelFile, excelFileFileName);
			if(returnMarks == ""){
				returnMarks  =this.waybillService.saveInputMakePriceExcel(excelFile, excelFileFileName);
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
	public String inputFromOutchargeExcel(){
		
		try {
			String returnMarks= this.waybillService.checkOutchargeExcel(excelFile, excelFileFileName);
			if(returnMarks == ""){
				returnMarks  =this.waybillService.saveInputOutchargeExcel(excelFile, excelFileFileName);
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
	@Override
	public InputStream getDownloadFile() {	
		downloadFile = waybillService.getInputStream(model.getBitch(),model.getStatusId());
		return downloadFile;
	}
	
	public String downloadExcel(){
		return "download";
	}
	//导出做价表
	public String downloadReport(){
		return "downreport";
	}
	
	public void setDownReport(InputStream downReport) {
		this.downReport = downReport;
	}
	//生成批量运单
	public InputStream getDownReport() {
		downReport=waybillService.getReportStream(ids);
		return downReport;
	}
	//导出做价预览表
	public String downloadMakePrice(){
		downMakePrice= waybillService.getMakePriceStream(ids);
		return "downMakePrice";
	}
	//代理费按id生成报表
	public String downloadRaterFee(){
		downRaterFee = waybillService.getRaterStream(ids);
		return "downRaterFee";
	}
	//垫付款按id生成报表
	public String downloadAdvance(){
		downAdvance = waybillService.getAdvanceStream(ids);
		return "downAdvance";
	}
	
	//财务报表
	public String downloadCaiReport(){
		System.out.println(model.getBitch()+" type:"+reportType);		
		downCaiReport= waybillService.getMocReportStream(model.getBitch(),reportType);
		System.out.println(downCaiReport== null);
		return "downloadCaiReport";
	}
	//垫付款报表
	public String advancedReport(){
		System.out.println("Advanced Report :"+model.getBitch());		
		advanceReport= waybillService.getAdvanceReportStream(model.getBitch());
		System.out.println(advanceReport== null);
		return "advanceReport";
	}
	//代理费报表
	public String downloadRaterReport(){
		System.out.println("Action sdDate:"+ stdate+" ;EndDate:"+enddate);
		downRaterReport= waybillService.getRaterReportStream(stdate, enddate);
		System.out.println(downRaterReport== null);
		return "downloadRaterReport";
	}
	//欠款总表
	public String downloadArrearagesReport(){
		downArrearagesReport= waybillService.getArrearagesStream(model.getSender(),model.getCustName());
		return "downloadArrearagesReport";
	}
	//到货报表
	public String downloadTrackReport(){
		
		downTrackReport= waybillService.getTrackReportSream(waybills);
		System.out.println(downTrackReport== null);
		return "downloadTrackReport";
	}
	//时间段内的批次总报表
	public String downloadAllBitchReport(){
		System.out.println("<All bitch Report> sdDate:"+ stdate+" ;EndDate:"+enddate);
		allBitchReport= waybillService.getAllBitchReportStream(stdate, enddate);
		System.out.println(allBitchReport== null);
		return "downloadAllBitchReport";
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public InputStream getDownMakePrice() {
		
		return downMakePrice;
	}
	public void setDownMakePrice(InputStream downMakePrice) {
		this.downMakePrice = downMakePrice;
	}
	
	public Integer getReportType() {
		return reportType;
	}
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	public InputStream getDownCaiReport() {
		return downCaiReport;
	}
	public void setDownCaiReport(InputStream downCaiReport) {
		this.downCaiReport = downCaiReport;
	}
	
	public InputStream getDownTrackReport() {
		return downTrackReport;
	}
	public void setDownTrackReport(InputStream downTrackReport) {
		this.downTrackReport = downTrackReport;
	}
	public String getCangid() {
		return cangid;
	}
	public void setCangid(String cangid) {
		this.cangid = cangid;
	}
	public InputStream getDownRaterReport() {
		return downRaterReport;
	}
	public void setDownRaterReport(InputStream downRaterReport) {
		this.downRaterReport = downRaterReport;
	}
		
	public String getBitches() {
		return bitches;
	}
	public void setBitches(String bitches) {
		this.bitches = bitches;
	}
	public Integer getEditId() {
		return editId;
	}
	public void setEditId(Integer editId) {
		this.editId = editId;
	}
	public InputStream getAdvanceReport() {
		return advanceReport;
	}
	public void setAdvanceReport(InputStream advanceReport) {
		this.advanceReport = advanceReport;
	}
	public InputStream getAllBitchReport() {
		return allBitchReport;
	}
	public void setAllBitchReport(InputStream allBitchReport) {
		this.allBitchReport = allBitchReport;
	}
	public String getWaybills() {
		return waybills;
	}
	public void setWaybills(String waybills) {
		this.waybills = waybills;
	}


	public InputStream getDownArrearagesReport() {
		return downArrearagesReport;
	}
	public void setDownArrearagesReport(InputStream downArrearagesReport) {
		this.downArrearagesReport = downArrearagesReport;
	}
	public InputStream getDownRaterFee() {
		return downRaterFee;
	}
	public void setDownRaterFee(InputStream downRaterFee) {
		this.downRaterFee = downRaterFee;
	}
	public Integer getRaterStatusId() {
		return raterStatusId;
	}
	public void setRaterStatusId(Integer raterStatusId) {
		this.raterStatusId = raterStatusId;
	}
	public Integer getAdvanceStatusId() {
		return advanceStatusId;
	}
	public void setAdvanceStatusId(Integer advanceStatusId) {
		this.advanceStatusId = advanceStatusId;
	}
	public InputStream getDownAdvance() {
		return downAdvance;
	}
	public void setDownAdvance(InputStream downAdvance) {
		this.downAdvance = downAdvance;
	}
	public Date getAdvanceDate() {
		return advanceDate;
	}
	public void setAdvanceDate(Date advanceDate) {
		this.advanceDate = advanceDate;
	}
	public Date getProcuratorDate() {
		return procuratorDate;
	}
	public void setProcuratorDate(Date procuratorDate) {
		this.procuratorDate = procuratorDate;
	}
	
	
	
}
