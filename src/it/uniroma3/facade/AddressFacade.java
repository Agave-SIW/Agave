package it.uniroma3.facade;

import it.uniroma3.model.*;

public class AddressFacade {
	
	
    public AddressFacade() {}

	public Address createAddress(String street, String city, String state,
			String zipcode, String country){
    	Address address = new Address(street, city, state, zipcode, country);
    	// persistence handled by cascade type on customer
    	return address;
    }
    
}
