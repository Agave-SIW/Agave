package it.uniroma3.controller;

import java.util.List;
import java.util.Map;

import it.uniroma3.facade.OrderFacade;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Orders;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * Class that manage the details of a closed Order
 * 
 * @author Andrea
 *
 */

@ManagedBean
public class OrderController {
	
	@EJB
	private OrderFacade orderFacade;
	
	private Map<String, Object> currentSessionMap;
	
	@ManagedProperty(value="#{param.id}")
	private Long id;
	
	private Orders order;

	
	public OrderController(){
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	public List<Orders> getOrders(){
		CustomerController customerController = new CustomerController();
		
		if(customerController.isLogged()){
			Long fetchedId = customerController.getCurrentCustomer().getId();
			if(fetchedId != -1)
				return orderFacade.getAllOrdersFromCustomer(fetchedId);
		}
		System.out.println("you do not have any orders yet!");
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
	

	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}

	public Map<String, Object> getCurrentSessionMap() {
		return currentSessionMap;
	}

	public void setCurrentSessionMap(Map<String, Object> currentSessionMap) {
		this.currentSessionMap = currentSessionMap;
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

}
