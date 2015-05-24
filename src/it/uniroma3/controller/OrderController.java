package it.uniroma3.controller;

import java.util.List;

import it.uniroma3.facade.OrderFacade;
import it.uniroma3.helper.ContextHelper;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Orders;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * System operations for closed/evaded Order management
 * 
 * @author Andrea
 *
 */

@ManagedBean
public class OrderController {

	@ManagedProperty(value="#{param.id}")
	private Long id;

	private Orders order;
	
	@EJB
	private OrderFacade orderFacade;
	
	private ContextHelper ch;


	public OrderController(){
		this.ch = new ContextHelper();
	}

	public List<Orders> getOrders(){
		CustomerController customerController = new CustomerController();

		if(customerController.isLogged()){
			Long fetchedId = customerController.getCurrentCustomer().getId();
			if(fetchedId != -1)
				return orderFacade.getAllOrdersFromCustomer(fetchedId);
		}

		return null;
	}
	
	public List<Orders> getAllClosedOrders(){
		return orderFacade.getAllClosedOrders();
	}

	public List<Orders> getClosedOrders(){
		CustomerController customerController = new CustomerController();

		if(customerController.isLogged()){
			Long fetchedId = customerController.getCurrentCustomer().getId();
			if(fetchedId != -1)
				return orderFacade.getAllClosedOrdersFromCustomer(fetchedId);
		}

		return null;	
	}

	public List<Orders> getEvadedOrders(){
		CustomerController customerController = new CustomerController();

		if(customerController.isLogged()){
			Long fetchedId = customerController.getCurrentCustomer().getId();
			if(fetchedId != -1)
				return orderFacade.getAllEvadedOrdersFromCustomer(fetchedId);
		}

		return null;
	}


	public List<Orders> getLastOrders(int numOrders){
		return orderFacade.getLastOrders(numOrders);
	}

	public String findOrder(Long id){
		this.order = orderFacade.getOrder(id);
		return "order";
	}

	public String findOrder(){
		return findOrder(this.id);
	}
	
	public double getOrderTotal(Long orderId){
		System.out.println(orderId);
		double sum = 0;
		for(OrderLine ol : orderFacade.getOrderLines(orderId))
			sum += ol.getQuantity()*ol.getProduct().getPrice();
		return sum;
	}
	
	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}

	/** GETTER AND SETTERS **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public ContextHelper getCh() {
		return ch;
	}

	public void setCh(ContextHelper ch) {
		this.ch = ch;
	}

}
