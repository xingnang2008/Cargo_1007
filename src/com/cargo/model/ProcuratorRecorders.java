package com.cargo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * 用来记录已经结账的代理费信息。
 */
@Entity
@Table(name = "procuratorRecorders")
public class ProcuratorRecorders {
	private Integer id;
	
	private String waybill;  //运单号
	private Integer pics;  //包数
	
	private String procurator; //代理人	
	private String sender;  // 发货人
	
	private Date sddate;  //发货日期
	private Double fee;  //结算金额
	private Date rdate;  //结算日期
	
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
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
	public Integer getPics() {
		return pics;
	}
	public void setPics(Integer pics) {
		this.pics = pics;
	}
	public String getProcurator() {
		return procurator;
	}
	public void setProcurator(String procurator) {
		this.procurator = procurator;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Date getSddate() {
		return sddate;
	}
	public void setSddate(Date sddate) {
		this.sddate = sddate;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	

}
