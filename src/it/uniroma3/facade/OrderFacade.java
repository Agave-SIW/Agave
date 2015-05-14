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
    
	public Orders createClosedOrder(Customer customer, List<OrderLine> orderlines) {
		ClosedOrder order = new ClosedOrder();
		order.setCustomer(customer);
		order.setOrderLines(orderlines);
		
		Date creationTime = new Date();
		order.setCreationTime(creationTime);
		
		em.persist(order);
		return order;
	}
	
	public ClosedOrder getClosedOrder(Long id) {
		ClosedOrder order = em.find(ClosedOrder.class, id);
		return order;
	}
	
	public List<ClosedOrder> getAllClosedOrders() {
        CriteriaQuery<ClosedOrder> cq = em.getCriteriaBuilder().createQuery(ClosedOrder.class);
        cq.select(cq.from(ClosedOrder.class));
        List<ClosedOrder> ordersList = em.createQuery(cq).getResultList();
		return ordersList;
	}

	public void updateClosedOrder(ClosedOrder order) {
        em.merge(order);
	}
	
    private void deleteClosedOrder(ClosedOrder order) {
        em.remove(order);
    }

	public void deleteOrder(Long id) {
		ClosedOrder order = em.find(ClosedOrder.class, id);
        deleteClosedOrder(order);
	}

}