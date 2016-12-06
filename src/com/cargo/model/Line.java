package com.cargo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Line entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "line")
public class Line implements java.io.Serializable {

	// Fields

	private Integer id;
	private String transType;
	private String lineId;
	private String info;
	private String remarks;

	// Constructors

	/** default constructor */
	public Line() {
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

	@Column(name = "lineId", length = 40)
	public String getLineId() {
		return this.lineId;
	}

	public void setLineId(String line) {
		this.lineId = line;
	}

	@Column(name = "info", length = 100)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "remarks", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "transType", length = 20)
	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}