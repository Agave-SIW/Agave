package it.uniroma3.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="closedorder")
public class ClosedOrder extends Orders {
	
	@Temporal(TemporalType.DATE)
	private Date creationTime;

	
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Orders [creationTime=" + creationTime + "]";
	}

}
