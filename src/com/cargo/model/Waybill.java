package com.cargo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Waybill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "waybill")
public class Waybill implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bitch; // 批次 对应批次号
	private String joinBill;// 合包号
	private String orderNo; //改为货源地
	private String waybill;
	// 运输类型
	private String lineId;
	private String transType;
	

	// 客户对应客户号
	private String custId;
	private String custName;
	private String custTel;
	private String custAdd;// 客户地址
	private String custEmail; // 客户邮箱
	// 目的市场
	private String destName;
	private String destTel;
	// 货物信息
	private Integer pics;
	private Double weight;
	private Double volumu;
	private String goods;
	private Integer quantity;
	private Integer advancedZ;
	private Integer advancedU;
	private Integer packfeeU;
	private Integer packfeeZ;
	private Integer noaccPackfee;  //不累计包费;
	private Integer noaccAdvance;  //不累计垫付
	private Double exchangeRate;
	private Date sddate;
	// 核销记录号
	private Integer isRebate; // 是否核销
	private Integer rebateId;
	
	// 价格信息
	private Double price;
	private Integer worth;
	private Integer insurance;  //保险费
	private Double worthRate;
	private Double sumbill; // 运费
	private Integer total; // 应收
	private Integer actualSum; // 实收款
	// 制价人，经办，代理
	private String raterName;
	private Double cod; // 代收款
	private Double raterRate;
	private Double discount;
	private String payMethod;
	// 发货人
	private Integer sdId;
	private String sender;
	private String sdTel;
	private String procurator;  //代理人
	// 外配信息，结算信息
	private Double outPrice;
	private Double outWorth;
	private Double outWorthrate;
	private Double outInsurance;
	private Double outSum;
	
	private String remarks; // 分货备注
	private String descrip;  //操作说明
	
	private Integer statusId;// 状态信息
	private String mark;  //货物品类
	private String outMark;  //对外品类
	
		
	private String cangId;  //装机仓单号
	private Integer editable;  //锁定运单编辑
    
	private Double indemnity; //赔偿	
	private Integer raterStauts; //代理费状态 0为没有代理费，1为有代理费，2为已经导出代理费。
	private Integer advanceStauts; //垫付运费状态 0为没有，1为有费，2为已经导出。
	
	// Constructors

	

	/** default constructor */
	public Waybill() {
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

	
	public String getBitch() {
		return bitch;
	}

	public void setBitch(String bitch) {
		this.bitch = bitch;
	}

	@Column(name = "waybill", length = 50)
	public String getWaybill() {
		return this.waybill;
	}

	public void setWaybill(String waybill) {
		this.waybill = waybill;
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

	@Column(name = "pics")
	public Integer getPics() {
		return this.pics;
	}

	public void setPics(Integer pics) {
		this.pics = pics;
	}

	@Column(name = "weight", precision = 8)
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "volumu", precision = 6)
	public Double getVolumu() {
		return this.volumu;
	}

	public void setVolumu(Double volumu) {
		this.volumu = volumu;
	}

	@Column(name = "goods", length = 60)
	public String getGoods() {
		return this.goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "advanced_z")
	public Integer getAdvancedZ() {
		return this.advancedZ;
	}

	public void setAdvancedZ(Integer advancedZ) {
		this.advancedZ = advancedZ;
	}

	@Column(name = "advanced_u")
	public Integer getAdvancedU() {
		return this.advancedU;
	}

	public void setAdvancedU(Integer advancedU) {
		this.advancedU = advancedU;
	}

	@Column(name = "packfee_u")
	public Integer getPackfeeU() {
		return this.packfeeU;
	}

	public void setPackfeeU(Integer packfeeU) {
		this.packfeeU = packfeeU;
	}

	@Column(name = "packfee_z")
	public Integer getPackfeeZ() {
		return this.packfeeZ;
	}

	public void setPackfeeZ(Integer packfeeZ) {
		this.packfeeZ = packfeeZ;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sddate", length = 10)
	public Date getSddate() {
		return this.sddate;
	}

	public void setSddate(Date sddate) {
		this.sddate = sddate;
	}

	@Column(name = "price", precision = 10)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "worth")
	public Integer getWorth() {
		return this.worth;
	}

	public void setWorth(Integer worth) {
		this.worth = worth;
	}

	@Column(name = "insurance")
	public Integer getInsurance() {
		return this.insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	@Column(name = "worthRate", precision = 5)
	public Double getWorthRate() {
		return this.worthRate;
	}

	public void setWorthRate(Double worthRate) {
		this.worthRate = worthRate;
	}

	@Column(name = "cod", precision = 10)
	public Double getCod() {
		return this.cod;
	}

	public void setCod(Double cod) {
		this.cod = cod;
	}

	@Column(name = "raterRate", precision = 5)
	public Double getRaterRate() {
		return this.raterRate;
	}

	public void setRaterRate(Double raterRate) {
		this.raterRate = raterRate;
	}

	@Column(name = "discount", precision = 11)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "payMethod", length = 10)
	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	@Column(name = "outPrice", precision = 5)
	public Double getOutPrice() {
		return this.outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	@Column(name = "outWorth", precision = 11)
	public Double getOutWorth() {
		return this.outWorth;
	}

	public void setOutWorth(Double outWorth) {
		this.outWorth = outWorth;
	}

	@Column(name = "outWorthrate", precision = 5)
	public Double getOutWorthrate() {
		return this.outWorthrate;
	}

	public void setOutWorthrate(Double outWorthrate) {
		this.outWorthrate = outWorthrate;
	}

	@Column(name = "outInsurance", precision = 10)
	public Double getOutInsurance() {
		return this.outInsurance;
	}

	public void setOutInsurance(Double outInsurance) {
		this.outInsurance = outInsurance;
	}

	@Column(name = "outSum", precision = 11)
	public Double getOutSum() {
		return this.outSum;
	}

	public void setOutSum(Double outSum) {
		this.outSum = outSum;
	}

	@Column(name = "remarks", length = 250)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String line) {
		this.lineId = line;
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

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getDestTel() {
		return destTel;
	}

	public void setDestTel(String destTel) {
		this.destTel = destTel;
	}

	public Integer getRebateId() {
		return rebateId;
	}

	public void setRebateId(Integer rebateId) {
		this.rebateId = rebateId;
	}


	public String getRaterName() {
		return raterName;
	}

	public void setRaterName(String raterName) {
		this.raterName = raterName;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getJoinBill() {
		return joinBill;
	}

	public void setJoinBill(String joinBill) {
		this.joinBill = joinBill;
	}

	public String getCustAdd() {
		return custAdd;
	}

	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}

	

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public Integer getIsRebate() {
		return isRebate;
	}

	public void setIsRebate(Integer isRebate) {
		this.isRebate = isRebate;
	}

	public Integer getActualSum() {
		return actualSum;
	}

	public void setActualSum(Integer actualSum) {
		this.actualSum = actualSum;
	}

	public String getSdTel() {
		return sdTel;
	}

	public void setSdTel(String sdTel) {
		this.sdTel = sdTel;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

		

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}
	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getProcurator() {
		return procurator;
	}

	public void setProcurator(String procurator) {
		this.procurator = procurator;
	}
	@Column(name = "outmark", length = 20)
	public String getOutMark() {
		return outMark;
	}

	public void setOutMark(String outMark) {
		this.outMark = outMark;
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

	public String getCangId() {
		return cangId;
	}

	public void setCangId(String cangId) {
		this.cangId = cangId;
	}

	public Integer getEditable() {
		return editable;
	}

	public void setEditable(Integer editable) {
		this.editable = editable;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Double getIndemnity() {
		return indemnity;
	}

	public void setIndemnity(Double indemnity) {
		this.indemnity = indemnity;
	}

	public Integer getRaterStauts() {
		return raterStauts;
	}

	public void setRaterStauts(Integer raterStauts) {
		this.raterStauts = raterStauts;
	}

	public Integer getAdvanceStauts() {
		return advanceStauts;
	}

	public void setAdvanceStauts(Integer advanceStauts) {
		this.advanceStauts = advanceStauts;
	}
	


}