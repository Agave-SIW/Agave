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
	
	public Customer createCustomer(String firstName, String lastName, String email, String password, String phoneNumber, Date dateofBirth, Address address) {
		Date currentDate = new Date();
		Orders cart = new Orders();
		
		//making MD5 password to store
		String securePassword = md.securePassword(password);
		Customer customer = new Customer(firstName, lastName, email, securePassword,  phoneNumber, dateofBirth, currentDate, address, cart);
		em.persist(customer);
		return customer;
	}
	
	public Customer getCustomer(String email) {
		Customer customer = new Customer();
		//customer = em.find(Customer.class, email); // Non funziona perche' l'Id e' id, non email. Serve una query per cercare su email
		try { 
			TypedQuery<Customer> customerQuery = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class).setParameter("email", email);
			customer = customerQuery.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		return customer;
	}
	
	public Customer getCustomer(Long id) {
		Customer customer = new Customer();
		customer = em.find(Customer.class, id); 
		return customer;
	}
	
	
	
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
	
	public List<Customer> getAllCustomers() {
        CriteriaQuery<Customer> cq = em.getCriteriaBuilder().createQuery(Customer.class);
        cq.select(cq.from(Customer.class));
        List<Customer> customers = em.createQuery(cq).getResultList();
		return customers;
	}
	
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

	public Customer getCustomerByOrderId(Long id) {
		return em.createQuery("SELECT c "
							+ "FROM Customer c, Orders o "
							+ "WHERE o.customer.id = c.id", Customer.class).getResultList().get(0);
	}

	
}
