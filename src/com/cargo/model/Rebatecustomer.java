package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Rebatecustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rebatecustomer")
public class Rebatecustomer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String custId;
	private String telphone;
	private String email;
	private String remarks;

	// Constructors

	/** default constructor */
	public Rebatecustomer() {
	}

	/** full constructor */
	public Rebatecustomer(String name, String custId, String telphone,
			String email, String remarks) {
		this.name = name;
		this.custId = custId;
		this.telphone = telphone;
		this.email = email;
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

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "custId", length = 20)
	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "remarks", length = 250)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}