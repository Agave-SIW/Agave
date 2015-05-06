package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.Date;
import java.util.List;

// TODO
@Stateless(name="cFacade")
public class CustomerFacade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
	public Customer createCustomer(String firstName, String lastName, String email, String phoneNumber, Date dateofBirth, Address address) {
		Customer customer = new Customer(firstName, lastName, email, phoneNumber, dateofBirth, address);
		em.persist(customer);
		return customer;
	}
	
	public Customer getCustomer(String email) {
		Customer customer = em.find(Customer.class, email); // TODO Non funziona perchè l'Id è ID. Serve una query
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