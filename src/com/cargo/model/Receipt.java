package com.cargo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 收款记录 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "receipt")
public class Receipt {

	private Integer id;
	private Date rdate; // 收款日期
	private String waybill;
	private Integer fee;  //金额
	private String remarks;
	
	private String lineId;  //线路
	private String bitch;
	private String sender;
	private String custId;
	private String custName;	
	private String rater;
	private String procurator; //代理人
	
	private Integer payMethod;  //0： 到付 ，1：正付 ，
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getWaybill() {
		return waybill;
	}
	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getBitch() {
		return bitch;
	}
	public void setBitch(String bitch) {
		this.bitch = bitch;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
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
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
	}
	public String getProcurator() {
		return procurator;
	}
	public void setProcurator(String procurator) {
		this.procurator = procurator;
	}
	
	
	
}
