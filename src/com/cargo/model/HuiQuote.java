package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * 用于灰关报价的存储
 */
@Entity
@Table(name = "huiquote")
public class HuiQuote {

	private Integer id;
	private String bh;
	private String category;
	private String products;
	private Double price;
	private String worthRate;
	private Integer yiBao;
	private Integer days; //承诺运期晚到天数
	private Integer outDays; //承诺灭失天数
	private String transType;
	private Integer isUse; //是否收货，0 不收，1 收货，2待定；
	private String remarks;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getWorthRate() {
		return worthRate;
	}
	public void setWorthRate(String worthRate) {
		this.worthRate = worthRate;
	}
	public Integer getYiBao() {
		return yiBao;
	}
	public void setYiBao(Integer yiBao) {
		this.yiBao = yiBao;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getOutDays() {
		return outDays;
	}
	public void setOutDays(Integer outDays) {
		this.outDays = outDays;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	
	
	
}
