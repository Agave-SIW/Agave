package it.uniroma3.facade;

import it.uniroma3.model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless(name="pFacade")
public class ProductFacade {
	
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
/*
	
		
	    private EntityManager entityManager;
	    private EntityManagerFactory emf;

		public ProductFacade()  {
			emf = Persistence.createEntityManagerFactory("product-unit");
			entityManager = emf.createEntityManager();
		}

		public Product createProduct(String name, String code, Float price, String description) {
			Product product = new Product(name, price, description, code);
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(product);
			tx.commit();
			entityManager.close();
			emf.close();
			return product;
		}
		
		public Product getProduct(Long id) {
		    Product product = entityManager.find(Product.class, id);
			entityManager.close();
			emf.close();
			return product;
		}
		
		public List<Product> getAllProducts() {
	        CriteriaQuery<Product> cq = entityManager.getCriteriaBuilder().createQuery(Product.class);
	        cq.select(cq.from(Product.class));
	        List<Product> products = entityManager.createQuery(cq).getResultList();
			entityManager.close();
			emf.close();
			return products;
		}

		public void updateProduct(Product product) {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
	        entityManager.merge(product);
			tx.commit();
			entityManager.close();
			emf.close();	}
		
	    private void deleteProduct(Product product) {
	        entityManager.remove(product);
	    }

		public void deleteProduct(Long id) {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
	        Product product = entityManager.find(Product.class, id);
	        deleteProduct(product);
			tx.commit();
			entityManager.close();
			emf.close();	
		}
*/
}