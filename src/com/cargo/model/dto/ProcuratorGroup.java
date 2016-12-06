package com.cargo.model.dto;

public class ProcuratorGroup {
	private String Procurator;
	private Double discount;
	private Double cod;
	private Double indemn;
	private Double total;
	
	public String getProcurator() {
		return Procurator;
	}
	public void setProcurator(String procurator) {
		Procurator = procurator;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getCod() {
		return cod;
	}
	public void setCod(Double cod) {
		this.cod = cod;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getIndemn() {
		return indemn;
	}
	public void setIndemn(Double indemn) {
		this.indemn = indemn;
	}
	

}
