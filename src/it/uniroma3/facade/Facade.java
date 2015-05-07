package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import java.util.Date;
import java.util.List;

@Stateless(name="Facade")
public class Facade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
	public Product createProduct(String name, String code, Float price, String description) {
		Product product = new Product(name, price, description, code);
		em.persist(product);
		return product;
	}
	
	public Product getProduct(Long id) {
	    Product product = em.find(Product.class, id);
		return product;
	}
	
	public List<Product> getAllProducts() {
        CriteriaQuery<Product> cq = em.getCriteriaBuilder().createQuery(Product.class);
        cq.select(cq.from(Product.class));
        List<Product> products = em.createQuery(cq).getResultList();
		return products;
	}

	public void updateProduct(Product product) {
        em.merge(product);
	}
	
    private void deleteProduct(Product product) {
        em.remove(product);
    }

	public void deleteProduct(Long id) {
        Product product = em.find(Product.class, id);
        deleteProduct(product);
	}
	public Customer createCustomer(String firstName, String lastName, String email, String password, String phoneNumber, Date dateofBirth, Address address) {
		Customer customer = new Customer(firstName, lastName, email, password,  phoneNumber, dateofBirth, address);
		em.persist(customer);
		return customer;
	}
	
	public Customer getCustomer(String email) {
		Customer customer = new Customer();
		//customer = em.find(Customer.class, email); // Non funziona perchè l'Id è id, non email. Serve una query per cercare su email
		try { 
			TypedQuery<Customer> customerQuery = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class).setParameter("email", email);
			customer = customerQuery.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		return customer;
	}
	
	public List<Customer> getAllCustomers() {
        CriteriaQuery<Customer> cq = em.getCriteriaBuilder().createQuery(Customer.class);
        cq.select(cq.from(Customer.class));
        List<Customer> customers = em.createQuery(cq).getResultList();
		return customers;
	}

	public void updateCustomer(Customer customer) {
        em.merge(customer);
	}
	
    private void deleteCustomer(Customer customer) {
        em.remove(customer);
    }

	public void deleteCustomer(Long id) {
		Customer customer = em.find(Customer.class, id);
        deleteCustomer(customer);
	}
	public Admin createAdmin(String firstName, String lastName, String email, String password, String phoneNumber, Date dateofBirth) {
		Admin admin = new Admin(firstName, lastName, email, password, phoneNumber, dateofBirth);
		em.persist(admin);
		return admin;
	}
	
	public Admin getAdmin(String email) {
		Admin admin = new Admin();
		try { 
			TypedQuery<Admin> adminQuery = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class).setParameter("email", email);
			admin = adminQuery.getSingleResult();
			System.out.print(admin.toString());
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
}