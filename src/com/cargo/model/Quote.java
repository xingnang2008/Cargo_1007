package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * 用于白关货物运输报价查询
 * 
 * 
 */

@Entity
@Table(name = "quote")
public class Quote {
	
	private Integer id;
	private String bh;   //编号
//	private String category;  //分类
	private String product;  //品名
	private String metarial; //材质
	private Double price;	//报价
	private String transType;  //运输方式
//	private String qingType;	//清关方式
	private Boolean inspection;  //是否商检
	private String hsCodeRu;  //俄方的海关编码
	private Integer isUse;   //是否可用 0：不收货,1：可收货，，2：待定
	private String remarks;  //备注
		
	
	
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
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

	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getMetarial() {
		return metarial;
	}

	public void setMetarial(String metarial) {
		this.metarial = metarial;
	}
	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	public Boolean getInspection() {
		return inspection;
	}

	public void setInspection(Boolean inspection) {
		this.inspection = inspection;
	}

	public String getHsCodeRu() {
		return hsCodeRu;
	}

	public void setHsCodeRu(String hsCodeRu) {
		this.hsCodeRu = hsCodeRu;
	}



	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	
	
}
