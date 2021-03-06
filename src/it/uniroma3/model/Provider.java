package it.uniroma3.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NamedQuery(name = "findAllProviders", query = "SELECT pr FROM Provider pr")
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	private String phoneNumber;
	
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@Pattern(regexp = "^[0-9]{11}$", message = "Please insert a valid vatin (11 numbers)")
	private String vatin;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Address address;
	
	@ManyToMany(mappedBy="providers")
	private List<Product> products;
	

	public Provider() {
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
	
	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", vatin=" + vatin
				+ ", address=" + address.toString() + "]";
	}

}
