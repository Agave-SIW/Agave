package it.uniroma3.model;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date creationTime;
	
	@Temporal(TemporalType.DATE)
	private Date evasionTime;
	
	@ManyToOne
	private Customer customer;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="orders_id")
	private List<OrderLine> orderLines;

	
	public Orders() {
		this.orderLines = new LinkedList<OrderLine>();
	}
	
	public void addOrderLine(OrderLine orderline){
		this.orderLines.add(orderline);
	}
	
	public void emptyOrderLines(){
		this.orderLines = new LinkedList<OrderLine>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	

	@Override
	public String toString() {
		return "Orders [id=" + id + ", creationTime=" + creationTime
				+ ", evasionTime=" + evasionTime + ", customer=" + customer
				+ ", orderLines=" + orderLines + "]";
	}

	public Date getEvasionTime() {
		return evasionTime;
	}

	public void setEvasionTime(Date evasionTime) {
		this.evasionTime = evasionTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((evasionTime == null) ? 0 : evasionTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((orderLines == null) ? 0 : orderLines.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (evasionTime == null) {
			if (other.evasionTime != null)
				return false;
		} else if (!evasionTime.equals(other.evasionTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderLines == null) {
			if (other.orderLines != null)
				return false;
		} else if (!orderLines.equals(other.orderLines))
			return false;
		return true;
	}
	
	public boolean containsProduct(Product product){
		Iterator<OrderLine> olIterator = this.orderLines.iterator();
		
		while(olIterator.hasNext()){
			if(olIterator.next().getProduct().equals(product))
				return true;
		}
		return false;
	}
	
	public boolean updateOrderLine(Product product, Integer quantity){
		Iterator<OrderLine> olIterator = this.orderLines.iterator();
		
		while(olIterator.hasNext()){
			OrderLine ol = olIterator.next();
			if(ol.getProduct().equals(product) && product.getStorageQuantity() > (ol.getQuantity() + quantity)){
				ol.setQuantity(ol.getQuantity() + quantity);
				return true;
			}
		}
		return false;
	}


}
