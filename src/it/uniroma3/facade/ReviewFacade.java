package it.uniroma3.facade;

import java.util.Date;

import it.uniroma3.model.*;

public class ReviewFacade {
	
	
    public ReviewFacade() {}

	public Review createReview(Integer stars, String comment, Customer customer){
		Date date = new Date();
		Review review = new Review(stars, comment, date, customer);
    	// persistence handled by cascade type on product
    	return review;
    }
    
}
