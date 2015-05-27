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
	
	/**
	 * Creates an Admin and returns it, admin is persisted
	 * 
	 * @param firstName, lastName, email, password,  phoneNumber, dateofBirth
	 * @return the created admin
	 */
	public Admin createAdmin(String firstName, String lastName, String email, String password, String phoneNumber, Date dateofBirth) {
		//making SHA password to store
		try { 
			String securePassword = md.securePassword(password);

			Admin admin = new Admin(firstName, lastName, email, securePassword, phoneNumber, dateofBirth);
			em.persist(admin);
			return admin;
		}
		catch(Exception e){
			return null;
		}
	}

	/**
	 * Gets the admin from the email address
	 * 
	 * @param email
	 * @return the admin
	 */
	public Admin getAdmin(String email) {
		try { 
			Admin admin = new Admin();
			TypedQuery<Admin> adminQuery = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class).setParameter("email", email);
			admin = adminQuery.getSingleResult();
			//System.out.print(admin.toString());
			return admin;
		}
		catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Gets all the admins in the database
	 * 
	 * @param 
	 * @return admin list
	 */
	public List<Admin> getAllAdmins() {
		CriteriaQuery<Admin> cq = em.getCriteriaBuilder().createQuery(Admin.class);
		cq.select(cq.from(Admin.class));
		List<Admin> admins = em.createQuery(cq).getResultList();
		return admins;
	}
	
	/**
	 * Checks if the password of the given admin is the same as the given password, applying encryption
	 * 
	 * @param admin, password
	 * @return true/false
	 */
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