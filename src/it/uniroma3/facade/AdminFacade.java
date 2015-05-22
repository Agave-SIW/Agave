package it.uniroma3.facade;

import it.uniroma3.helper.PasswordHelper;
import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import java.util.Date;
import java.util.List;

@Stateless
public class AdminFacade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
    private PasswordHelper md;
    
	public AdminFacade() {
		this.md = new PasswordHelper();
	}
    
	public Admin createAdmin(String firstName, String lastName, String email, String password, String phoneNumber, Date dateofBirth) {
		//making MD5 password to store
		String securePassword = md.securePassword(password);
		
		Admin admin = new Admin(firstName, lastName, email, securePassword, phoneNumber, dateofBirth);
		em.persist(admin);
		return admin;
	}
	
	public Admin getAdmin(String email) {
		Admin admin = new Admin();
		try { 
			TypedQuery<Admin> adminQuery = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class).setParameter("email", email);
			admin = adminQuery.getSingleResult();
			//System.out.print(admin.toString());
		}
		catch(Exception e){
			return null;
		}
		return admin;
	}
	
	public List<Admin> getAllAdmins() {
        CriteriaQuery<Admin> cq = em.getCriteriaBuilder().createQuery(Admin.class);
        cq.select(cq.from(Admin.class));
        List<Admin> admins = em.createQuery(cq).getResultList();
		return admins;
	}
	
	public Boolean checkPassword(Admin admin, String password){
		return admin.getPassword().equals(md.securePassword(password));
	}

	public void updateAdmin(Admin admin) {
        em.merge(admin);
	}
	
    private void deleteAdmin(Admin admin) {
        em.remove(admin);
    }

	public void deleteAdmin(Long id) {
		Admin admin = em.find(Admin.class, id);
        deleteAdmin(admin);
	}

	public PasswordHelper getMd() {
		return md;
	}

	public void setMd(PasswordHelper md) {
		this.md = md;
	}
	
}