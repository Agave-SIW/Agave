package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class OrderLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantity;
	
	@ManyToOne
	private Product product;
	
	
	public OrderLine() {
	}
	
	public OrderLine(Integer quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", quantity=" + quantity + ", product="
				+ product + "]";
	}
	
	
	

}
