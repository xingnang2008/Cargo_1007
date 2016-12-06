package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sender entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sender")
public class Sender implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sdName;
	private String phone;
	private String email;
	private String qq;
	private String remarks;

	// Constructors

	/** default constructor */
	public Sender() {
	}

	/** full constructor */
	public Sender(String sdName, String phone, String email, String qq,
			String remarks) {
		this.sdName = sdName;
		this.phone = phone;
		this.email = email;
		this.qq = qq;
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

	@Column(name = "sdName", length = 30)
	public String getSdName() {
		return this.sdName;
	}

	public void setSdName(String sdName) {
		this.sdName = sdName;
	}

	@Column(name = "phone", length = 40)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "qq", length = 30)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}