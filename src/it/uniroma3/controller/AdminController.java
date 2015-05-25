package it.uniroma3.controller;

import it.uniroma3.model.Admin;
import it.uniroma3.facade.AdminFacade;
import it.uniroma3.helper.ContextHelper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;

/**
 * System operations for Admin management
 * 
 * @author Gaetano
 *
 */

@ManagedBean
@SessionScoped
public class AdminController {

	private Long id;
	private String firstName;
	private String password;
	private String email;
	
	private Admin admin;
	
	private String page;
	
	@EJB
	private AdminFacade adminFacade;
	
	private ContextHelper ch;

	
	public AdminController() {
		this.ch = new ContextHelper();
	}

	public String loginAdmin(){
		this.admin = adminFacade.getAdmin(email);

		if(admin==null || !adminFacade.checkPassword(admin, password)){ 
			this.admin = null;
			System.out.print("\n\nWRONG MAIL OR PASSWORD\n\n");
			this.ch.addMessage("adminLogin", "loginButton", "Invalid Email or Password");
		}
		else{
			System.out.print("\n\nLogin Admin OK\n\n");
		}
		
		this.ch.addToSession("admin", admin);
		
		return "admin";
	}
	
	
	public String logoutAdmin(){
		this.admin = null;
		
		this.ch.addToSession("admin", null);
		System.out.print("\n\nAdmin LOGGED OUT\n\n");
		return "admin";
	}
	
	public Admin getCurrentAdmin(){
		return (Admin) this.ch.getFromSession("admin");
	}
	
	public Boolean isLogged() {
		Admin a = (Admin) this.ch.getFromSession("admin");
		return !(a == null);
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public AdminFacade getAdminFacade() {
		return adminFacade;
	}

	public void setAdminFacade(AdminFacade adminFacade) {
		this.adminFacade = adminFacade;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public ContextHelper getCh() {
		return ch;
	}

	public void setCh(ContextHelper ch) {
		this.ch = ch;
	}
	
	
}


