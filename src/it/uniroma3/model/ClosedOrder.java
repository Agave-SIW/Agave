package it.uniroma3.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class ClosedOrder extends Orders {
	
	@Temporal(TemporalType.DATE)
	private Date creationTime;

	public ClosedOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClosedOrder(Date creationTime) {
		super();
		this.creationTime = creationTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
