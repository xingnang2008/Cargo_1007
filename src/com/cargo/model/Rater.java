package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Rater entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rater")
public class Rater implements java.io.Serializable {

	// Fields

	private Integer id;
	private String raterName;
	private String phone;
	private String cardName;
	private String card;
	private String bank;
	private String remarks;

	// Constructors

	/** default constructor */
	public Rater() {
	}

	/** full constructor */
	public Rater(String raterName, String phone, String cardName, String card,
			String bank, String remarks) {
		this.raterName = raterName;
		this.phone = phone;
		this.cardName = cardName;
		this.card = card;
		this.bank = bank;
		this.remarks = remarks;
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

	@Column(name = "raterName", length = 30)
	public String getRaterName() {
		return this.raterName;
	}

	public void setRaterName(String raterName) {
		this.raterName = raterName;
	}

	@Column(name = "phone", length = 60)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "cardName", length = 20)
	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@Column(name = "card", length = 30)
	public String getCard() {
		return this.card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	@Column(name = "bank", length = 30)
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "remarks", length = 250)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}