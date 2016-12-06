package com.cargo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * 赔偿记录 Indemnify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "indemnify")
public class Indemnify {
	private Integer id;
	private String waybill;
	
	private String lineId;  //线路
	private String bitch;
	private String sender;
	private String custId;
	private String custName;	
	private String rater;
	private String procurator; //代理人
	
	
	private String reason;  // 赔偿原由
	private Double indemnity;	// 赔偿金额
	
	private Double indemWeight;    //丢失重量
	private String goods;
	private String mark;
	
	private Double weight; //货物重量

	private Integer worth; //货值
	private Double outWorth; //外货值

	private Double indemWorth; //丢失货值
	private Double price; //做价
	
	private Double returnBill; //返还运费

	
	private String remarks;	
	
	
	private Integer yiWuBao;  //义务保险值
	private Integer status; //赔偿状态  0：未赔 ，1：已赔付。
	private Date indemDate; //赔偿日期
	private Integer payMethod; //赔偿方式
	
	private Double outPrice;
	
	private Double outIndemWeight;    //丢失重量
	private Integer outYiWuBao;  //对外义务保险值
	private Double outIndemWorth; //對外丢失货值
	private Double outReturnBill; //對外返还运费
	private Double outIndemnity;//外赔偿，即：清关公司给的赔偿
	
	private Integer outStatus; //外赔偿状态0：未赔 ，1：已赔付。
	private Date outIndemDate; //赔偿日期
	
	private Integer approval; //审核状态  0：未审核 ，1：已经审核。
	private Date appDate; //审核日期
	
	@Id
	@GeneratedValue
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Double getIndemnity() {
		return indemnity;
	}
	public void setIndemnity(Double indemnity) {
		this.indemnity = indemnity;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
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
	public Integer getOutStatus() {
		return outStatus;
	}
	public void setOutStatus(Integer outStatus) {
		this.outStatus = outStatus;
	}
	public Date getOutIndemDate() {
		return outIndemDate;
	}
	public void setOutIndemDate(Date outIndemDate) {
		this.outIndemDate = outIndemDate;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Integer getYiWuBao() {
		return yiWuBao;
	}
	public void setYiWuBao(Integer yiWuBao) {
		this.yiWuBao = yiWuBao;
	}
	public Integer getOutYiWuBao() {
		return outYiWuBao;
	}
	public void setOutYiWuBao(Integer outYiWuBao) {
		this.outYiWuBao = outYiWuBao;
	}
	public Double getIndemWeight() {
		return indemWeight;
	}
	public void setIndemWeight(Double indemWeight) {
		this.indemWeight = indemWeight;
	}
	public Integer getWorth() {
		return worth;
	}
	public void setWorth(Integer worth) {
		this.worth = worth;
	}
	public Double getOutWorth() {
		return outWorth;
	}
	public void setOutWorth(Double outWorth) {
		this.outWorth = outWorth;
	}
	public Double getIndemWorth() {
		return indemWorth;
	}
	public void setIndemWorth(Double indemWorth) {
		this.indemWorth = indemWorth;
	}
	public Double getReturnBill() {
		return returnBill;
	}
	public void setReturnBill(Double returnBill) {
		this.returnBill = returnBill;
	}
	public String getProcurator() {
		return procurator;
	}
	public void setProcurator(String procurator) {
		this.procurator = procurator;
	}
	public Double getOutIndemWorth() {
		return outIndemWorth;
	}
	public void setOutIndemWorth(Double outIndemWorth) {
		this.outIndemWorth = outIndemWorth;
	}
	public Double getOutReturnBill() {
		return outReturnBill;
	}
	public void setOutReturnBill(Double outReturnBill) {
		this.outReturnBill = outReturnBill;
	}
	public Double getOutIndemWeight() {
		return outIndemWeight;
	}
	public void setOutIndemWeight(Double outIndemWeight) {
		this.outIndemWeight = outIndemWeight;
	}
	
	public Double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}
	

}
