package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mark entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mark")
public class Mark implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String remarks;

	// Constructors

	/** default constructor */
	public Mark() {
	}

	/** full constructor */
	public Mark(String info, String remarks) {
		this.type = info;
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

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String info) {
		this.type = info;
	}

	@Column(name = "remarks", length = 160)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}