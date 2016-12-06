package com.cargo.model.dto;

import java.util.Date;

public class WaybillGroupSender {
	private String sender;
	private Integer pics;
	private Double weight;
	private Double volumu;
	private Double density;  //密度
	private Date sddate;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
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
	public Double getVolumu() {
		return volumu;
	}
	public void setVolumu(Double volumu) {
		this.volumu = volumu;
	}
	public Date getSddate() {
		return sddate;
	}
	public void setSddate(Date sddate) {
		this.sddate = sddate;
	}
	public Double getDensity() {
		return density;
	}
	public void setDensity(Double density) {
		this.density = density;
	}
	
	
	

}
