package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dest")
public class Dest implements java.io.Serializable {

	// Fields

	private Integer id;	
	private String destName;
	private String destOperator;
	private String phone;
	private String remarks;

	// Constructors

	/** default constructor */
	public Dest() {
	}

	/** full constructor */
	

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

	

	@Column(name = "destName", length = 30)
	public String getDestName() {
		return this.destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	@Column(name = "destOperator", length = 30)
	public String getDestOperator() {
		return this.destOperator;
	}

	public void setDestOperator(String destOperator) {
		this.destOperator = destOperator;
	}

	@Column(name = "phone", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "remarks", length = 250)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}