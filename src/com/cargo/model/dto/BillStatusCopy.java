package com.cargo.model.dto;

import java.util.Date;

public class BillStatusCopy {

	private Integer id;
	private String waybill;	


	// 客户对应客户号
	private String custId;
	private String custName;
	
	
	private String sender;		
	
	
	private Integer total; // 运费
	private Integer actualSum; // 实收款
	private Double indemnify; //丢赔
	
	private Double delayIndem; //晚到赔偿
	private Double arrear;
	
	
	
	 @Override
	    public boolean equals(Object object) {
		 BillStatusCopy info=(BillStatusCopy) object;
	        return custId.equals(info.custId)&& sender.equals(info.sender);
	    }
	
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

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
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

	public Double getArrear() {
		return arrear;
	}
	public void setArrear(Double arrear) {
		this.arrear = arrear;
	}
	
	
	
}
