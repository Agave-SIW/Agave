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
public class CustomerFacade {

	@PersistenceContext(unitName = "agave")
	private EntityManager em;

	private PasswordHelper md;

	public CustomerFacade() {
		this.md = new PasswordHelper();
	}

	/**
	 * Creates a customer with an empty cart and returns it, customer is persisted
	 * 
	 * @param firstName, lastName, email, password,  phoneNumber, dateofBirth, address
	 * @return the created customer
	 */
	public Customer createCustomer(String firstName, String lastName, String email, String password, String phoneNumber, Date dateofBirth, Address address) {
		try { 
			Date currentDate = new Date();
			Orders cart = new Orders();

			//making SHA password to store
			String securePassword = md.securePassword(password);
			Customer customer = new Customer(firstName, lastName, email, securePassword,  phoneNumber, dateofBirth, currentDate, address, cart);
			em.persist(customer);
			return customer;
		}
		catch(Exception e){
			return null;
		}
	}

	/**
	 * Finds a customer from its email
	 * 
	 * @param email
	 * @return the customer
	 */
	public Customer getCustomer(String email) {
		try { 
			Customer customer = new Customer();
			//customer = em.find(Customer.class, email); // Non funziona perche' l'Id e' id, non email. Serve una query per cercare su email
			TypedQuery<Customer> customerQuery = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class).setParameter("email", email);
			customer = customerQuery.getSingleResult();

			return customer;
		}
		catch(Exception e){
			return null;
		}

	}

	/**
	 * Finds a customer from its id
	 * 
	 * @param id
	 * @return the customer
	 */
	public Customer getCustomer(Long id) {
		try { 
			Customer customer = new Customer();
			customer = em.find(Customer.class, id); 
			return customer;
		}
		catch (Exception e){
			return null;
		}
	}

	/**
	 * Checks if a customer with the given email exists
	 * 
	 * @param email
	 * @return true/false
	 */
	public boolean existsCustomer(String email) {
		try { 
			TypedQuery<Customer> customerQuery = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class).setParameter("email", email);
			customerQuery.getSingleResult();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	/**
	 * Returns the list of all customers in the database
	 * 
	 * @param 
	 * @return customer list
	 */
	public List<Customer> getAllCustomers() {
		CriteriaQuery<Customer> cq = em.getCriteriaBuilder().createQuery(Customer.class);
		cq.select(cq.from(Customer.class));
		List<Customer> customers = em.createQuery(cq).getResultList();
		return customers;
	}

	/**
	 * Checks if the password of the given customer is the same as the given password, applying encryption
	 * 
	 * @param customer, password
	 * @return true/false
	 */
	public Boolean checkPassword(Customer customer, String password){
		return customer.getPassword().equals(md.securePassword(password));
	}

	public void updateCustomer(Customer customer) {
		em.merge(customer);
	}

	public void updateCustomer(Long idCustomer) {
		Customer customer = em.find(Customer.class, idCustomer);
		updateCustomer(customer);
	}

	private void deleteCustomer(Customer customer) {
		em.remove(customer);
	}

	public void deleteCustomer(Long idCustomer) {
		Customer customer = em.find(Customer.class, idCustomer);
		deleteCustomer(customer);
	}

	public Orders getCart(Long idCustomer){
		Customer customer = em.find(Customer.class, idCustomer);
		return getCart(customer);

	}

	public Orders getCart(Customer customer){
		return customer.getCart();
	}

	public void setCart(Long idCustomer, Orders cart){
		Customer customer = em.find(Customer.class, idCustomer);
		setCart(customer, cart);
	}

	public void setCart(Customer customer, Orders cart){

		customer.setCart(cart);
		updateCustomer(customer);

	}

	public PasswordHelper getMd() {
		return md;
	}

	public void setMd(PasswordHelper md) {
		this.md = md;
	}


}
