package it.uniroma3.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import it.uniroma3.model.Customer;
import it.uniroma3.facade.CustomerFacade;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class CustomerController {

	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String passwordRepeated;
	private String email;
	private Date dateOfBirth;
	
	//address data
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	private Customer customer;
	private List<Customer> customers;
	
	private String page;

	@EJB
	private CustomerFacade customerFacade;
	
	private Map<String, Object> currentSessionMap;
	
	
	public CustomerController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public String loginCustomer(){
		this.customer = customerFacade.getCustomer(email);

		if(customer==null || !customer.getPassword().equals(password)){
			this.customer = null;
			System.out.print("\n\nWRONG MAIL OR PASSWORD\n\n");
			FacesContext.getCurrentInstance().addMessage("customerLogin:loginButton", new FacesMessage("Invalid Email or Password"));
		}
		else{
			System.out.print("\n\nLogin OK\n\n");
		}
		
		//workaround, SessionScoped not writing session automatically. Still requires javax.enterprise.context.SessionScoped;
		this.currentSessionMap.put("customer", customer);		
		return page;
	}

	public String logoutCustomer(){

		this.customer = null;
		
		//workaround, SessionScoped not writing session automatically. Still requires javax.enterprise.context.SessionScoped;
		this.currentSessionMap.put("customer", customer);
		
		System.out.print("\n\nCustomer LOGGED OUT\n\n");
		
		return page;
	}
	
	public Customer getCurrentCustomer(){
		return (Customer) this.currentSessionMap.get("customer");
	}
	
	// currently not used in view, as #{SessionScope} works
	public Boolean isLogged() {
		Customer c = (Customer) this.currentSessionMap.get("customer");
		return !(c == null);
	}
	
	// currently not used in view, as #{SessionScope} works
	public Boolean isNotLogged() {
		return !this.isLogged();
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

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Map<String, Object> getCurrentSessionMap() {
		return currentSessionMap;
	}

	public void setCurrentSessionMap(Map<String, Object> currentSessionMap) {
		this.currentSessionMap = currentSessionMap;
	}

	public String getPasswordRepeated() {
		return passwordRepeated;
	}

	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}
	
	
}


