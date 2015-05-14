package it.uniroma3.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class EvadedOrder extends Orders {
	
	@Temporal(TemporalType.DATE)
	private Date creationTime;
	
	@Temporal(TemporalType.DATE)
	private Date evasionTime;

	public EvadedOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EvadedOrder(Date creationTime, Date evasionTime) {
		super();
		this.creationTime = creationTime;
		this.evasionTime = evasionTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getEvasionTime() {
		return evasionTime;
	}

	public void setEvasionTime(Date evasionTime) {
		this.evasionTime = evasionTime;
	}
	
}
