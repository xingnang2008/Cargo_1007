package com.cargo.model.dto;

import java.util.Date;

public class BillStatus {

	private Integer id;
	private String waybill;	
	private String orderNo; //改为货源地
	private Date sddate;
	// 运输类型
	private String lineId;
	private String transType;
	private String bitch; // 批次 对应批次号

	// 客户对应客户号
	private String custId;
	private String custName;
	
	private String raterName;	
	private String sender;	
	private String procurator;  //代理人
	// 目的市场
	private String destName;
	
	// 货物信息
	private Integer pics;
	private Double weight;
		
	private Integer arrPics; //到货包数	
	private Integer unArrPics; //未到货包数
	
	
	private Integer total; // 运费
	private Integer actualSum; // 实收款
	private Double indemnify; //丢赔
	private Double outIndemnify;
	private Double delayIndem; //晚到赔偿
	private Double outDelayIndemnity;
	private Double arrear;
	private Integer statusId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWaybill() {
		return waybill;
	}
	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getSddate() {
		return sddate;
	}
	public void setSddate(Date sddate) {
		this.sddate = sddate;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getBitch() {
		return bitch;
	}
	public void setBitch(String bitch) {
		this.bitch = bitch;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getRaterName() {
		return raterName;
	}
	public void setRaterName(String raterName) {
		this.raterName = raterName;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getProcurator() {
		return procurator;
	}
	public void setProcurator(String procurator) {
		this.procurator = procurator;
	}
	public String getDestName() {
		return destName;
	}
	public void setDestName(String destName) {
		this.destName = destName;
	}
	public Integer getPics() {
		return pics;
	}
	public void setPics(Integer pics) {
		this.pics = pics;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getArrPics() {
		return arrPics;
	}
	public void setArrPics(Integer arrPics) {
		this.arrPics = arrPics;
	}
	public Integer getUnArrPics() {
		return unArrPics;
	}
	public void setUnArrPics(Integer unArrPics) {
		this.unArrPics = unArrPics;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getActualSum() {
		return actualSum;
	}
	public void setActualSum(Integer actualSum) {
		this.actualSum = actualSum;
	}
	public Double getIndemnify() {
		return indemnify;
	}
	public void setIndemnify(Double indemnify) {
		this.indemnify = indemnify;
	}
	public Double getDelayIndem() {
		return delayIndem;
	}
	public void setDelayIndem(Double delayIndem) {
		this.delayIndem = delayIndem;
	}
	public Double getOutIndemnify() {
		return outIndemnify;
	}
	public void setOutIndemnify(Double outIndemnify) {
		this.outIndemnify = outIndemnify;
	}
	public Double getOutDelayIndemnity() {
		return outDelayIndemnity;
	}
	public void setOutDelayIndemnity(Double outDelayIndemnity) {
		this.outDelayIndemnity = outDelayIndemnity;
	}
	public Double getArrear() {
		return arrear;
	}
	public void setArrear(Double arrear) {
		this.arrear = arrear;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
}
