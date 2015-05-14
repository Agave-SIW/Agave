package it.uniroma3.controller;

import java.util.List;
import java.util.Map;

import it.uniroma3.model.Customer;
import it.uniroma3.facade.CustomerFacade;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class CustomerController {

	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String firstName;
	private String password;
	private String email;
	private Customer customer;
	private List<Customer> customers;
	
	private String page;

	@EJB
	private CustomerFacade customerFacade;
	
	private Map<String, Object> currentSessionMap;
	
	
	public CustomerController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		//default value
		this.page="index";
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
		
		this.currentSessionMap.put("customer", customer);		
		return page;
	}

	public String logoutCustomer(){

		this.customer = null;
		
		this.currentSessionMap.put("customer", customer);
		
		System.out.print("\n\nCustomer LOGGED OUT\n\n");
		
		return page;
	}
	
	public Customer getCurrentCustomer(){
		return (Customer) this.currentSessionMap.get("customer");
	}
	
	public Boolean loggedIn() {
		if(this.getCurrentCustomer()==null)
			return false;
		return true;
	}
	
	public Boolean notLoggedIn() {
		return !this.loggedIn();
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
	
	
}


