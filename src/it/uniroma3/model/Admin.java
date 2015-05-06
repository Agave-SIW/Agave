package it.uniroma3.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findAllAdmins", query = "SELECT a FROM Admin a")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
	
	private String email;

	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	private Date dateofBirth;

	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String firstName, String lastName, String email,
			String phoneNumber, Date dateofBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateofBirth = dateofBirth;
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

	public boolean equals(Admin customer) {

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
				+ "]";
	}


}
