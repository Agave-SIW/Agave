package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductFacade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
    
	public ProductFacade() {
	}
    
	public Product createProduct(String name, String code, Float price, String description, String picturePath, Integer quantity) {
		Product product = new Product(name, price, description, code, picturePath, quantity);
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
	
	public List<Product> getLastProducts() {
		List<Product> products = new ArrayList<Product>();
		try { 
			products = em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class).getResultList();
		}
		catch(Exception e){
			products = null;
		}
		return products;
	}
	
	public List<Product> getLastProducts(Integer n) {
		List<Product> products = new ArrayList<Product>();
		try { 
			products = em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class).setMaxResults(n).getResultList();
		}
		catch(Exception e){
			products = null;
		}
		return products;
	}
	
	public void addReviewToProduct(Review review, Product product){
		product.addReview(review);
		em.merge(product);
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

	

	
}