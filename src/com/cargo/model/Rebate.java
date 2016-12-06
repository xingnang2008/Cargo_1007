package com.cargo.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Rebate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rebate")
public class Rebate implements java.io.Serializable {

	// Fields
	private Integer id;
	private Date sddate;
	private int companyId;
	private String company;
	private String companyCode;	
	private String contract;
	private String tradeType;
	private String source;
	private String remarks;
	private Integer cashStatus;
	private Integer status;
	private Date bcdate;
	private Date yldate;
	private String yuludan;
	private Integer bitchId;
	private String bitch;
	
	private Double grossWeight;
	private Integer packages;
	private String telphone;
	private String custId;
	private Integer inFee;
	private Date infeeDate;
	private Integer outFee;
	private Date outfeeDate;
	private Integer payMethod;
	
	// Constructors

	/** default constructor */
	public Rebate() {
		
	}

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

	@Temporal(TemporalType.DATE)
	@Column(name = "sddate", length = 10)
	public Date getSddate() {
		return this.sddate;
	}

	public void setSddate(Date sddate) {
		this.sddate = sddate;
	}

	@Column(name = "contract", length = 20)
	public String getContract() {
		return this.contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	@Column(name = "tradeType", length = 20)
	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	@Column(name = "source", length = 20)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "remarks", length = 250)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "cashStatus")
	public Integer getCashStatus() {
		return this.cashStatus;
	}

	public void setCashStatus(Integer cashStatus) {
		this.cashStatus = cashStatus;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bcdate", length = 10)
	public Date getBcdate() {
		return this.bcdate;
	}

	public void setBcdate(Date bcdate) {
		this.bcdate = bcdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "yldate", length = 10)
	public Date getYldate() {
		return this.yldate;
	}

	public void setYldate(Date yldate) {
		this.yldate = yldate;
	}

	@Column(name = "yuludan", length = 30)
	public String getYuludan() {
		return this.yuludan;
	}

	public void setYuludan(String yuludan) {
		this.yuludan = yuludan;
	}

	public Integer getBitchId() {
		return bitchId;
	}

	public void setBitchId(Integer bitchId) {
		this.bitchId = bitchId;
	}

	public String getBitch() {
		return bitch;
	}

	public void setBitch(String bitch) {
		this.bitch = bitch;
	}


	@Column(name = "grossWeight", precision = 22, scale = 0)
	public Double getGrossWeight() {
		return this.grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	@Column(name = "packages")
	public Integer getPackages() {
		return this.packages;
	}

	public void setPackages(Integer packages) {
		this.packages = packages;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}


	@Column(name = "custId", length = 20)
	public String getCustId() {
		return this.custId;
	}
	@Column(name = "payMethod", length = 2)
	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name = "inFee")
	public Integer getInFee() {
		return this.inFee;
	}

	public void setInFee(Integer inFee) {
		this.inFee = inFee;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "infeeDate", length = 10)
	public Date getInfeeDate() {
		return this.infeeDate;
	}

	public void setInfeeDate(Date infeeDate) {
		this.infeeDate = infeeDate;
	}

	@Column(name = "outFee")
	public Integer getOutFee() {
		return this.outFee;
	}

	public void setOutFee(Integer outFee) {
		this.outFee = outFee;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "outfeeDate", length = 10)
	public Date getOutfeeDate() {
		return this.outfeeDate;
	}

	public void setOutfeeDate(Date outfeeDate) {
		this.outfeeDate = outfeeDate;
	}


	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	
}