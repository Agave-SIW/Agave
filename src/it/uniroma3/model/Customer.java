package it.uniroma3.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	private Date dateofBirth;
	
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Address address;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Orders cart;
	
	@OneToMany(mappedBy="customer")
	private List<Orders> orders;
	

	public Customer() {	
		this.orders = new LinkedList<Orders>();
	}

	public Customer(String firstName, String lastName, String email, String password,
			String phoneNumber, Date dateofBirth, Date registrationDate, Address address, Orders cart) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password; 
		this.phoneNumber = phoneNumber;
		this.dateofBirth = dateofBirth;
		this.address = address;
		this.registrationDate = registrationDate;
		this.cart = cart;
	}
	
	public Customer(Customer c) {
		this.firstName = c.getFirstName();
		this.lastName = c.getLastName();
		this.email = c.getEmail();
		this.password = c.getPassword();
		this.phoneNumber = c.getPhoneNumber();
		this.dateofBirth = c.getDateofBirth();
		this.address = c.getAddress();
		this.registrationDate = c.getRegistrationDate();
		this.cart = c.getCart();
	}
	
	public List<Orders> getOrdersAndCart() {
		List<Orders> allOrdersAndCart = new LinkedList<Orders>();
		allOrdersAndCart.add(this.cart);
		allOrdersAndCart.addAll(this.orders);
		return allOrdersAndCart;
	}

	public Orders getCart() {
		return cart;
	}

	public void setCart(Orders cart) {
		this.cart = cart;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean equals(Customer customer) {
		
		if(this.firstName == customer.getFirstName() && this.lastName == customer.getLastName() &&
				this.dateofBirth == customer.getDateofBirth())
			return true;
		else return false;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", dateofBirth="
				+ dateofBirth + ", registrationDate=" + registrationDate
				+ ", address=" + address.toString() + "]"; 
		// nb: non aggiungere gli ordini per evitare overflow in ricorsione
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	


}
