package com.cargo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 晚到赔偿记录 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "delayrecord")
public class DelayRecord {
	
	private Integer id;
	private String waybill;
	
	private String lineId;
	private String bitch;
	private String sender;
	private String custId;
	private String custName;
	private String rater;
	private Date sddate;
	
	private Integer model;  // 赔偿计算方式：0 按天计算，1 重新定价
	private Double delayWeight;
	private Integer arrPics;
	
	private Date arrdate;
	private Integer inDate;		//承诺天数
	private Integer transDays; //运期天数
	private Integer delayDate;  //晚到天数
	private Double delayRate;   //晚到赔偿单价
	private Double delayIndemnity;  //晚到赔偿
	private Integer approval; //审核状态  0：未审核 ，1：已经审核。
	private String remarks;	
	

	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getWaybill() {
		return waybill;
	}
	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
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
	public Date getSddate() {
		return sddate;
	}
	public void setSddate(Date sddate) {
		this.sddate = sddate;
	}
	public Integer getModel() {
		return model;
	}
	public void setModel(Integer model) {
		this.model = model;
	}
	public Date getArrdate() {
		return arrdate;
	}
	public void setArrdate(Date arrdate) {
		this.arrdate = arrdate;
	}
	public Integer getApproval() {
		return approval;
	}
	public void setApproval(Integer approval) {
		this.approval = approval;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getDelayDate() {
		return delayDate;
	}
	public void setDelayDate(Integer delayDate) {
		this.delayDate = delayDate;
	}
	public Double getDelayRate() {
		return delayRate;
	}
	public void setDelayRate(Double delayRate) {
		this.delayRate = delayRate;
	}
	public Double getDelayIndemnity() {
		return delayIndemnity;
	}
	public void setDelayIndemnity(Double delayIndemnity) {
		this.delayIndemnity = delayIndemnity;
	}
	public Double getDelayWeight() {
		return delayWeight;
	}
	public void setDelayWeight(Double delayWeight) {
		this.delayWeight = delayWeight;
	}
	public Integer getArrPics() {
		return arrPics;
	}
	public void setArrPics(Integer arrPics) {
		this.arrPics = arrPics;
	}
	public Integer getInDate() {
		return inDate;
	}
	public void setInDate(Integer inDate) {
		this.inDate = inDate;
	}
	public Integer getTransDays() {
		return transDays;
	}
	public void setTransDays(Integer transDays) {
		this.transDays = transDays;
	}
	
	
	
}
