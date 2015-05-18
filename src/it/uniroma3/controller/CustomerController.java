package it.uniroma3.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import it.uniroma3.model.Address;
import it.uniroma3.model.Customer;
import it.uniroma3.facade.AddressFacade;
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
	private String repeatPassword;
	private String email;
	private String phoneNumber;
	private Date dateofBirth;

	//address data fetch from form
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;

	private Customer customer;
	private List<Customer> customers;

	private String page;
	private String param;

	@EJB
	private CustomerFacade customerFacade;
	//not ejb
	private AddressFacade addressFacade;

	private Map<String, Object> currentSessionMap;


	public CustomerController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		this.addressFacade = new AddressFacade();
	}

	public String signIn() {
		if(this.repeatPassword.equals(this.password)){
			if(!customerFacade.existsCustomer(this.email)){
				Address address = addressFacade.createAddress(street, city, state, zipcode, country);
				System.out.println("\nAddress created\n");
				this.customer = customerFacade.createCustomer(firstName, lastName, email, password, phoneNumber, dateofBirth, address);
				System.out.println("\nCustomer Created\n");

				return "successSignin";
			}
			else {
				FacesContext.getCurrentInstance().addMessage("signIn:email", new FacesMessage("There is already a customer with this email address"));
				return "signin";
			}
		}
		else {
			FacesContext.getCurrentInstance().addMessage("signIn:repeatPassword", new FacesMessage("Repeated password must be the same as password"));
			return "signin";
		}
	}

	public String loginCustomer(){
		this.customer = customerFacade.getCustomer(email);
		
		if(customer==null || !customerFacade.checkPassword(customer, password)){ 
			this.customer = null;
			System.out.print("\n\nWRONG MAIL OR PASSWORD\n\n");
			//FacesContext.getCurrentInstance().addMessage("customerLogin:loginButton", new FacesMessage("Invalid Email or Password"));
			return "errorLogin";
		}
		else{
			System.out.print("\n\nLogin OK\n\n");
		}
		//workaround, SessionScoped not writing session automatically. Still requires javax.enterprise.context.SessionScoped;
		this.currentSessionMap.put("customer", customer);	
		
		if(param!=null) return page + "?id="+param+"&faces-redirect=true&includeViewParams=true";
		return page;
	}

	public String logoutCustomer(){

		this.customer = null;

		//workaround, SessionScoped not writing session automatically. Still requires javax.enterprise.context.SessionScoped;
		this.currentSessionMap.put("customer", customer);

		System.out.print("\n\nCustomer LOGGED OUT\n\n");

		if(param!=null) return page + "?id="+param+"&faces-redirect=true&includeViewParams=true";
		return page;
	}

	public Customer getCurrentCustomer(){
		return (Customer) this.currentSessionMap.get("customer");
	}

	public Boolean isLogged() {
		Customer c = (Customer) this.currentSessionMap.get("customer");
		return !(c == null);
	}

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

	public AddressFacade getAddressFacade() {
		return addressFacade;
	}

	public void setAddressFacade(AddressFacade addressFacade) {
		this.addressFacade = addressFacade;
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}


}


