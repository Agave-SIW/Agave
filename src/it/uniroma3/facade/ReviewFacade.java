package it.uniroma3.facade;

import java.util.Date;

import it.uniroma3.model.*;

public class ReviewFacade {
	
	
    public ReviewFacade() {}

	public Review createReview(Integer stars, String comment, Customer customer){
		System.out.println("Making Review...");
		Date date = new Date();
		Review review = new Review(stars, comment, date, customer);
    	// persistence handled by cascade type on product
		System.out.println("Review made.");
		System.out.println(review.toString());
    	return review;
    }
    
}
