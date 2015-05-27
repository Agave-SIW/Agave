package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ProductFacade {

	@PersistenceContext(unitName = "agave")
	private EntityManager em;


	public ProductFacade() {}

	/**
	 * Creates a product returns it, product is persisted
	 * 
	 * @param name, price, description, code, picturePath, quantity
	 * @return the created product
	 */
	public Product createProduct(String name, String code, Float price, String description, String picturePath, Integer quantity) {
		Product product = new Product(name, price, description, code, picturePath, quantity);
		em.persist(product);
		return product;
	}

	/**
	 * Same as above, but with a list of providers
	 * 
	 * @param name, price, description, code, picturePath, quantity, providers
	 * @return the created product
	 */
	public Product createProduct(String name, String code, Float price, String description, String picturePath, Integer quantity, List<Provider> providers) {
		Product product = new Product(name, price, description, code, picturePath, quantity);
		product.addProviders(providers);
		em.persist(product);
		return product;
	}

	/**
	 * Gets a product from its id
	 * 
	 * @param the product id
	 * @return the product
	 */
	public Product getProduct(Long id) {
		Product product = em.find(Product.class, id);
		return product;
	}

	/**
	 * Gets a provider from its id
	 * 
	 * @param the provider id
	 * @return the provider
	 */
	public Provider getProvider(Long id) {
		Provider provider = em.find(Provider.class, id);
		return provider;
	}

	/**
	 * Gets all the products in the database
	 * 
	 * @param 
	 * @return product list
	 */
	public List<Product> getAllProducts() {
		CriteriaQuery<Product> cq = em.getCriteriaBuilder().createQuery(Product.class);
		cq.select(cq.from(Product.class));
		List<Product> products = em.createQuery(cq).getResultList();
		return products;
	}

	/**
	 * Gets all the providers in the database
	 * 
	 * @param 
	 * @return provider list
	 */	
	public List<Provider> getAllProviders() {
		CriteriaQuery<Provider> cq = em.getCriteriaBuilder().createQuery(Provider.class);
		cq.select(cq.from(Provider.class));
		List<Provider> providers = em.createQuery(cq).getResultList();
		return providers;
	}


	/**
	 * Gets all the products of the product with the given id
	 * 
	 * @param product id
	 * @return provider list
	 */
	public List<Provider> findProviders(Long productId) {
		Product p = em.find(Product.class, productId);
		if(p==null) return new LinkedList<Provider>();
		return p.getProviders();
	}

	/**
	 * Adds a provider list to a product, updating the join table
	 * 
	 * @param product, provider list
	 * @return 
	 */
	public void addProvidersToProduct(Product product, List<Provider> providers){
		try { 
			product.addProviders(providers);
			em.merge(product);
		}
		catch(Exception e){
			System.out.println("Exception: addProvidersToProduct(...)");
		}
	}

	/**
	 * Gets all the products in the database but with DESC order
	 * 
	 * @param 
	 * @return product list
	 */
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

	/**
	 * Gets the last n products
	 * 
	 * @param the number n of wanted products
	 * @return product list
	 */
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

	/**
	 * Adds a review to a product, updating it
	 * 
	 * @param review, product
	 * @return 
	 */
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