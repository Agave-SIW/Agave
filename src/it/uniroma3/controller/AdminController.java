package it.uniroma3.controller;

import java.util.Map;

import it.uniroma3.model.Admin;
import it.uniroma3.facade.AdminFacade;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AdminController {

	@ManagedProperty(value="#{param.id}")
	private Long id;
	private String firstName;
	private String password;
	private String email;
	private Admin admin;
	
	private Map<String, Object> currentSessionMap;
	
	@EJB
	private AdminFacade adminFacade;

	
	
	public AdminController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public String loginAdmin(){
		this.admin = adminFacade.getAdmin(email);

		if(admin==null || !admin.getPassword().equals(password)){
			this.admin = null;
			System.out.print("\n\nWRONG MAIL OR PASSWORD\n\n");
			FacesContext.getCurrentInstance().addMessage("adminLogin:loginButton", new FacesMessage("Invalid Email or Password"));
			return "admin";
		}
		else{
			System.out.print("\n\nLogin OK\n\n");
		}
		
		this.currentSessionMap.put("admin", admin);
		
		return "newProduct?faces-redirect=true";
	}

	public String logoutAdmin(){

		this.admin = null;
		
		this.currentSessionMap.put("admin", admin);
		
		System.out.print("\n\nAdmin LOGGED OUT\n\n");
		
		return "newProduct?faces-redirect=true";
	}
	
	public Admin getCurrentAdmin(){
		return (Admin) this.currentSessionMap.get("admin");
	}
	
	public Boolean loggedIn() {
		if(this.getCurrentAdmin()==null)
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
	
	
}


