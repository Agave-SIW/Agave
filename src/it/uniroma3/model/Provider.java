package it.uniroma3.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", vatin=" + vatin
				+ ", address=" + address.toString() + "]";
	}

	@Column(nullable = false)
	private String name;
	
	private String phoneNumber;
	
	private String email;
	
	private String vatin;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Address address;
	
	@ManyToMany
	private List<Product> products;
	

	public Provider() {
		//inizializzo la lista vuota per evitare null pointer exception
		this.products = new LinkedList<Product>();
	}

	public Provider(String name, String phoneNumber, String email, String vatin, Address address) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.vatin = vatin;
		this.address = address;
		this.products = new LinkedList<Product>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}

}
