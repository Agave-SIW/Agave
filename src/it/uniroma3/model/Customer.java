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
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	private Date dateofBirth;
	
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Address address;
	
	@OneToMany(mappedBy="customer")
	private List<Orders> orders;
	

	public Customer() {	
		this.orders = new LinkedList<Orders>();
	}

	public Customer(String firstName, String lastName, String email, String password,
			String phoneNumber, Date dateofBirth, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password; //TODO MD5
		this.phoneNumber = phoneNumber;
		this.dateofBirth = dateofBirth;
		this.address = address;
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
				+ ", address=" + address.toString() + "]"; // nb: non aggiungere gli ordini per evitare overflow in ricorsione
	}


}
