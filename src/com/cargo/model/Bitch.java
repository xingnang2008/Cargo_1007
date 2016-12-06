package com.cargo.model;

import java.util.Date;
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
 * Bitch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bitch")
public class Bitch implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bitch;
	private String info;
	private Date sdDate;
	private Date arrDate;
	private Double fee;
	private String remarks;
	private String lineId;
	private Integer ordered;

	private Integer pics;
	private Double weight;
	private Double volumu;

	private Integer advancedZ;
	private Integer advancedU;
	private Integer packfeeU;
	private Integer packfeeZ;
	private Integer noaccPackfee; // 不累计包费;
	private Integer noaccAdvance; // 不加垫付款

	private Integer worth; // 货值
	private Integer insurance; // 保险费
	private Double sumbill; // 运费
	private Integer total; // 应收
	private Integer actualSum; // 实收款

	private Double cod; // 代收款
	private Double discount; // 代理费

	private Double outSum; // 结算金额
	private Double outWorth;// 外货值
	private Double outInsurance;// 外保费

	private Double indemnity;// 索赔
    private Double changeRate;  //汇率
	private Double profit;  //毛利
	// Constructors

	/** default constructor */
	public Bitch() {
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

	@Column(name = "bitch", length = 30)
	public String getBitch() {
		return this.bitch;
	}

	public void setBitch(String bitch) {
		this.bitch = bitch;
	}

	@Column(name = "info", length = 100)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sdDate", length = 10)
	public Date getSdDate() {
		return this.sdDate;
	}

	public void setSdDate(Date sdDate) {
		this.sdDate = sdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "arrDate", length = 10)
	public Date getArrDate() {
		return this.arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	@Column(name = "fee", precision = 11)
	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	@Column(name = "remarks", length = 250)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	@Column(name = "ordered", precision = 11)
	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
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

	public Integer getAdvancedZ() {
		return advancedZ;
	}

	public void setAdvancedZ(Integer advancedZ) {
		this.advancedZ = advancedZ;
	}

	public Integer getAdvancedU() {
		return advancedU;
	}

	public void setAdvancedU(Integer advancedU) {
		this.advancedU = advancedU;
	}

	public Integer getPackfeeU() {
		return packfeeU;
	}

	public void setPackfeeU(Integer packfeeU) {
		this.packfeeU = packfeeU;
	}

	public Integer getPackfeeZ() {
		return packfeeZ;
	}

	public void setPackfeeZ(Integer packfeeZ) {
		this.packfeeZ = packfeeZ;
	}

	public Integer getNoaccPackfee() {
		return noaccPackfee;
	}

	public void setNoaccPackfee(Integer noaccPackfee) {
		this.noaccPackfee = noaccPackfee;
	}

	public Integer getNoaccAdvance() {
		return noaccAdvance;
	}

	public void setNoaccAdvance(Integer noaccAdvance) {
		this.noaccAdvance = noaccAdvance;
	}

	public Integer getWorth() {
		return worth;
	}

	public void setWorth(Integer worth) {
		this.worth = worth;
	}

	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	public Double getSumbill() {
		return sumbill;
	}

	public void setSumbill(Double sumbill) {
		this.sumbill = sumbill;
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

	public Double getCod() {
		return cod;
	}

	public void setCod(Double cod) {
		this.cod = cod;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getOutSum() {
		return outSum;
	}

	public void setOutSum(Double outSum) {
		this.outSum = outSum;
	}

	public Double getOutWorth() {
		return outWorth;
	}

	public void setOutWorth(Double outWorth) {
		this.outWorth = outWorth;
	}

	public Double getOutInsurance() {
		return outInsurance;
	}

	public void setOutInsurance(Double outInsurance) {
		this.outInsurance = outInsurance;
	}

	public Double getIndemnity() {
		return indemnity;
	}

	public void setIndemnity(Double indemnity) {
		this.indemnity = indemnity;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getChangeRate() {
		return changeRate;
	}

	public void setChangeRate(Double changeRate) {
		this.changeRate = changeRate;
	}

}