package it.uniroma3.controller;

import java.util.List;

import it.uniroma3.model.Customer;
import it.uniroma3.facade.CustomerFacade;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


@ManagedBean
@SessionScoped
public class CustomerController {

	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String firstName;
	private String password;
	private String email;
	private Boolean customerLogged;
	private Customer customer;
	private List<Customer> customers;

	@EJB
	private CustomerFacade customerFacade;

	public String loginCustomer(){
		this.customer = customerFacade.getCustomer(email);

		if(customer==null || !customer.getPassword().equals(password)){
			this.customerLogged = false;
			this.customer = null;
			System.out.print("\n\nWRONG MAIL OR PASSWORD\n\n");
			return "index";
		}
		else{
			this.customerLogged = true;
			System.out.print("\n\nLogin OK\n\n");
			return "index";
		}
	}

	public String logoutCustomer(){

		this.customerLogged = false;
		this.customer = null;
		System.out.print("\n\nCUSTOMER LOGGED OUT\n\n");
		return "index";
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

	public Boolean getCustomerLogged() {
		return customerLogged;
	}

	public void setCustomerLogged(Boolean customerLogged) {
		this.customerLogged = customerLogged;
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
	
	
}


