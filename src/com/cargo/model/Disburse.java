package com.cargo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 用于支出费用记录
 * 
 */
@Entity
@Table(name = "disburse")
public class Disburse {
	
	private Integer id;
	private String bitch;
	private String lineId;
	private String payFor;  //项目
	private String remarks;
	private Double fee;  //美元
	
	private Date payDate;
	private Integer payMethod; // 付款方式0:莫付，1：国内转账；2：国内现金；3：其他
	private Integer sortId; //支出用途 0：外付清关费；1：
	
	
	
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayFor() {
		return payFor;
	}
	public void setPayFor(String payFor) {
		this.payFor = payFor;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public String getBitch() {
		return bitch;
	}
	public void setBitch(String bitch) {
		this.bitch = bitch;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	
	
	

}
