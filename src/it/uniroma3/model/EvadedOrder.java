package it.uniroma3.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="evadedorder")
public class EvadedOrder extends Orders {
	@Temporal(TemporalType.DATE)
	private Date creationTime;
	
	@Temporal(TemporalType.DATE)
	private Date evasionTime;

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

	public Date getEvasionTime() {
		return evasionTime;
	}

	public void setEvasionTime(Date evasionTime) {
		this.evasionTime = evasionTime;
	}

}
