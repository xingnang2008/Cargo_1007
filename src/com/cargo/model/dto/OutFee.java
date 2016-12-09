package com.cargo.model.dto;

public class OutFee {
	private String bitch;
	private String lineId;
	private Double outSum;   //应付清关费
	private Double outIndem;  //丢失赔偿
	private Double outDelayIndem; //晚到赔偿
	private Integer outFee; //应付款
	private Integer actOutFee; //实付款
	
	
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
	public Double getOutSum() {
		return outSum;
	}
	public void setOutSum(Double outSum) {
		this.outSum = outSum;
	}
	public Double getOutIndem() {
		return outIndem;
	}
	public void setOutIndem(Double outIndem) {
		this.outIndem = outIndem;
	}
	public Double getOutDelayIndem() {
		return outDelayIndem;
	}
	public void setOutDelayIndem(Double outDelayIndem) {
		this.outDelayIndem = outDelayIndem;
	}
	public Integer getOutFee() {
		return outFee;
	}
	public void setOutFee(Integer outFee) {
		this.outFee = outFee;
	}
	public Integer getActOutFee() {
		return actOutFee;
	}
	public void setActOutFee(Integer actOutFee) {
		this.actOutFee = actOutFee;
	}
	
	
	

}
