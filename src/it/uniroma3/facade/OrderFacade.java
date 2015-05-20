package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class OrderFacade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
	public OrderFacade() {
	}
    
	public Orders createOrder(Customer customer, List<OrderLine> orderlines) {
		Orders order = new Orders();
		order.setCustomer(customer);
		order.setOrderLines(orderlines);
		
		Date creationTime = new Date();
		order.setCreationTime(creationTime);
		
		em.persist(order);
		return order;
	}
	
	public Orders createCart(Customer customer, List<OrderLine> orderlines) {
		Orders cart = new Orders();
		cart.setCustomer(customer);
		cart.setOrderLines(orderlines);
		
		return cart;
	}
	
	public Orders getOrder(Long id) {
	    Orders order = em.find(Orders.class, id);
		return order;
	}
	
	public List<Orders> getAllOrders() {
        CriteriaQuery<Orders> cq = em.getCriteriaBuilder().createQuery(Orders.class);
        cq.select(cq.from(Orders.class));
        List<Orders> ordersList = em.createQuery(cq).getResultList();
		return ordersList;
	}

	public void updateOrder(Orders order) {
        em.merge(order);
	}
	
	
	
    private void deleteOrder(Orders order) {
        em.remove(order);
    }

	public void deleteOrder(Long id) {
		Orders order = em.find(Orders.class, id);
        deleteOrder(order);
	}
	
	/* OLD METHOD TODO delete? */
	public void addProductToCart(Orders cart, Long idProduct){
		ProductFacade pf = new ProductFacade();
		Product p = pf.getProduct(idProduct);
		addProductToCart(cart, p);
	}
	
	/* OLD METHOD TODO delete? */
	public void addProductToCart(Orders cart, Product product){
		OrderLine ol = makeOrderLineFromProduct(product);
		Orders c = getOrder(cart.getId());
		c.addOrderLine(ol);
		updateOrder(c);
		
		System.out.println("Cart Updated");
	}
	
	/* TODO delete?
	 * Metodo Inutilizzato
	 * 
	public void updateCartFromCopy(Orders cart) {
		Orders c = getOrder(cart.getId());
		c.emptyOrderLines();
		updateOrder(c);
		c.setOrderLines(cart.getOrderLines());
		System.out.println("Print Order from DB + new prod");
		System.out.println(c.toString());
		updateOrder(c);
	}
	*/
	
	public void addProductToCart(Orders cart, Product product, Integer quantity){		
		OrderLine ol = makeOrderLineFromProduct(product, quantity);
		Orders c = getOrder(cart.getId());
		c.addOrderLine(ol);
		updateOrder(c);
		
		System.out.println("Cart Updated");
	}
	
	public OrderLine makeOrderLineFromProduct(Product product){
		OrderLine ol = new OrderLine(1, product);
		return ol;
	}
	
	public OrderLine makeOrderLineFromProduct(Product product, int quantity){
		OrderLine ol = new OrderLine(quantity, product);
		return ol;
	}
	
	/**
	 * Return the closed but not evaded order
	 * @return  the closed but not evaded order
	 */
	public List<Orders> getClosedOrders() {

		return 	em.createQuery("SELECT o "
			        	     + "FROM Orders o  "
				             + "WHERE o.closingDate <> NULL "
				             + "and o.evasionDate = NULL", Orders.class)
				             .getResultList();
	}

	/**
	 * Return the list of order lines in base of the
	 * corrispondence by the id of the product
	 * @param id
	 * @return
	 */
	public List<OrderLine> getOrderLines(Long id) {
		return 	em.createQuery("SELECT ol "
						+ "		FROM OrderLine "
						+ "		WHERE ol.id_order = :id"
						, OrderLine.class).setParameter("id", id).getResultList();
	}

	/**
	 * Return the last numOrders orders
	 * @param numOrders
	 * @return
	 */
	public List<Orders> getLastOrders(int numOrders) {

		List<Orders> orders = new ArrayList<Orders>();
		try { 
			orders = em.createQuery("SELECT o "
								  + "FROM Orders o "
								  + "ORDER BY o.id DESC"
								  , Orders.class).setMaxResults(numOrders).getResultList();
		}
		catch(Exception e){
			orders = null;
		}
		return orders;
	}
	
	/**
	 * Return all orders of a selected customer in base the corrispondence of 
	 * the customer's id
	 * @param idCustomer
	 * @return
	 */
	public List<Orders> getAllOrdersFromCustomer(Long idCustomer){
		return em.createQuery("SELECT o "
				            + "FROM Orders o "
							+ "WHERE o.customer.id = :idCustomer", Orders.class)
							.setParameter("idCustomer", idCustomer)
							.getResultList();

	}
	
}