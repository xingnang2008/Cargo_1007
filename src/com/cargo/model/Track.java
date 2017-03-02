package com.cargo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 到貨记录 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "track")
public class Track {
	private Integer id;
	
	private Date arriveDate; // 到货日期
	private String waybill;
	
	private Date sddate;
	private String lineId;
	private String bitch;
	private String sender;
	private String custId;
	private String custName;
	private String rater;
	private String destName;
	
	private Integer days; //运输周期。
	private Integer pics;
	private Integer wstatus;// 货物‘完好’状态 分：0完好， 1有破损或丢失
		
	private Integer model;  // 赔偿计算方式：0 按天计算，1 重新定价
	private Integer calBy;  //晚到计算：0按重量，1按体积
	private Double delayWeight;  //晚到重量
	private Double delayVol;	//晚到体积
	private Integer inDate;		//承诺天数
	
	private Integer delayDate;  //晚到天数
	private Double delayRate;   //晚到赔偿单价
	private Double delayIndemnity;  //晚到赔偿	
	private Integer status; //赔偿状态   0：未 赔付 ，1：已赔付。
	private Date indemDate; //赔偿日期
	private String mothed; //赔偿方式
	
	
	private Integer approval; //审核状态  0：未审核 ，1：已经审核。
	private Date appDate; //审核日期
	/*
	 * _____________________________________
	 * 以下是外配的晚到结算。
	 */
	private Integer outModel;  // 赔偿计算方式：0 按天计算，1 重新定价
	private Integer outCalBy;  //晚到计算：0按重量，1按体积
	private Date outSdDate;  //外配日期
	private Integer outInDate;		//承诺天数
	private Integer outDelayDate;  //晚到天数
	private Double outDelayRate;   //外晚到赔偿单价
	private Double outIndemnity;//外赔偿，即：清关公司给的赔偿
	private String remarks;	

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public Integer getPics() {
		return pics;
	}

	public void setPics(Integer pics) {
		this.pics = pics;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	
	
	public Integer getWstatus() {
		return wstatus;
	}

	public void setWstatus(Integer wstatus) {
		this.wstatus = wstatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Date getSddate() {
		return sddate;
	}

	public void setSddate(Date sddate) {
		this.sddate = sddate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public Integer getCalBy() {
		return calBy;
	}

	public void setCalBy(Integer calBy) {
		this.calBy = calBy;
	}

	public Double getDelayWeight() {
		return delayWeight;
	}

	public void setDelayWeight(Double delayWeight) {
		this.delayWeight = delayWeight;
	}

	

	public Double getDelayVol() {
		return delayVol;
	}

	public void setDelayVol(Double delayVol) {
		this.delayVol = delayVol;
	}

	public Integer getInDate() {
		return inDate;
	}

	public void setInDate(Integer inDate) {
		this.inDate = inDate;
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

	public Integer getApproval() {
		return approval;
	}

	public void setApproval(Integer approval) {
		this.approval = approval;
	}

	public Double getOutIndemnity() {
		return outIndemnity;
	}

	public void setOutIndemnity(Double outndemnity) {
		this.outIndemnity = outndemnity;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date aDate) {
		this.appDate = aDate;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getIndemDate() {
		return indemDate;
	}

	public void setIndemDate(Date indemDate) {
		this.indemDate = indemDate;
	}

	public String getMothed() {
		return mothed;
	}

	public void setMothed(String mothed) {
		this.mothed = mothed;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public Date getOutSdDate() {
		return outSdDate;
	}

	public void setOutSdDate(Date outSdDate) {
		this.outSdDate = outSdDate;
	}

	public Double getOutDelayRate() {
		return outDelayRate;
	}

	public void setOutDelayRate(Double outDelayRate) {
		this.outDelayRate = outDelayRate;
	}

	public Integer getOutInDate() {
		return outInDate;
	}

	public void setOutInDate(Integer outInDate) {
		this.outInDate = outInDate;
	}

	public Integer getOutDelayDate() {
		return outDelayDate;
	}

	public void setOutDelayDate(Integer outDelayDate) {
		this.outDelayDate = outDelayDate;
	}

	public Integer getOutModel() {
		return outModel;
	}

	public void setOutModel(Integer outModel) {
		this.outModel = outModel;
	}

	public Integer getOutCalBy() {
		return outCalBy;
	}

	public void setOutCalBy(Integer outCalBy) {
		this.outCalBy = outCalBy;
	}
	

	

}
