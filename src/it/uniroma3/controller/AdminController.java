package it.uniroma3.controller;

import java.util.Map;

import it.uniroma3.model.Admin;
import it.uniroma3.facade.AdminFacade;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AdminController {

	private Long id;
	private String firstName;
	private String password;
	private String email;
	private Admin admin;
	
	private String page;
	
	private Map<String, Object> currentSessionMap;
	
	@EJB
	private AdminFacade adminFacade;

	
	public AdminController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public String loginAdmin(){
		this.admin = adminFacade.getAdmin(email);

		if(admin==null || !admin.getPassword().equals(password)){ //TODO MD5
			this.admin = null;
			System.out.print("\n\nWRONG MAIL OR PASSWORD\n\n");
			FacesContext.getCurrentInstance().addMessage("adminLogin:loginButton", new FacesMessage("Invalid Email or Password"));
		}
		else{
			System.out.print("\n\nLogin OK\n\n");
		}
		
		//workaround, SessionScoped not writing session automatically. Still requires javax.enterprise.context.SessionScoped;
		this.currentSessionMap.put("admin", admin);
		
		return "admin";
	}

	public String logoutAdmin(){
		this.admin = null;
		//workaround, SessionScoped not writing session automatically. Still requires javax.enterprise.context.SessionScoped;
		this.currentSessionMap.put("admin", null);
		System.out.print("\n\nAdmin LOGGED OUT\n\n");
		return "admin";
	}
	
	public Admin getCurrentAdmin(){
		return (Admin) this.currentSessionMap.get("admin");
	}
	
	public Boolean loggedIn() {
		Admin a = (Admin) this.currentSessionMap.get("admin");
		return !(a == null);
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
}


