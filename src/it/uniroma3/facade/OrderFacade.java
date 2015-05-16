package it.uniroma3.facade;

import it.uniroma3.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.Date;
import java.util.List;

@Stateless
public class OrderFacade {
	
    @PersistenceContext(unitName = "agave")
    private EntityManager em;
    
	public OrderFacade() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public void updateCartFromCopy(Orders cart) {
		Orders c = getOrder(cart.getId());
		c.setOrderLines(cart.getOrderLines());
		updateOrder(c);
	}
	
    private void deleteOrder(Orders order) {
        em.remove(order);
    }

	public void deleteOrder(Long id) {
		Orders order = em.find(Orders.class, id);
        deleteOrder(order);
	}
	
	public void addProductToCart(Orders cart, Long idProduct){
		ProductFacade pf = new ProductFacade();
		Product p = pf.getProduct(idProduct);
		addProductToCart(cart, p);
	}
	
	public void addProductToCart(Orders cart, Product product){
		System.out.println("making order line from product");
		OrderLine ol = makeOrderLineFromProduct(product);
		System.out.println("made order line from product");
		System.out.println("adding order line to cart");
		System.out.println(cart.toString());
		cart.addOrderLine(ol);
		System.out.println("added order line to cart, updating order");
		updateCartFromCopy(cart);
		System.out.println("Cart Updated");
	}
	
	public OrderLine makeOrderLineFromProduct(Product product){
		OrderLine ol = new OrderLine(1, product);
		return ol;
	}

}