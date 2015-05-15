package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AddressFacade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
    public Address createAddress(String street, String city, String state,
			String zipcode, String country){
    	Address address = new Address(street, city, state, zipcode, country);
    	//persistance handled by cascade type on customer
    	return address;
    }
    
}
