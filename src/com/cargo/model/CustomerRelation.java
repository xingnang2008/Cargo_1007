package com.cargo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerrelation")
public class CustomerRelation implements java.io.Serializable {
	
	private Integer id;
	private Customer customer;
	private Sender sender;
	private Rater rater;
	private Procurator procurator;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="custId")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@ManyToOne
	@JoinColumn(name="senderId")
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	@ManyToOne
	@JoinColumn(name="raterId")
	public Rater getRater() {
		return rater;
	}
	public void setRater(Rater rater) {
		this.rater = rater;
	}
	@ManyToOne
	@JoinColumn(name="procuratorId")
	public Procurator getProcurator() {
		return procurator;
	}
	public void setProcurator(Procurator procurator) {
		this.procurator = procurator;
	}
	
	
	
	
	
	
	
	
	
	
}
