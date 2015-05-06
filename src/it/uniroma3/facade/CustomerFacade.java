package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;

import java.util.Date;
import java.util.List;

// TODO
@Stateless(name="cFacade")
public class CustomerFacade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
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
}