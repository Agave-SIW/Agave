package it.uniroma3.controller;

import java.util.List;
import java.util.Map;

import it.uniroma3.facade.OrderFacade;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Orders;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Class that manage the details of a closed Order
 * 
 * @author Andrea
 *
 */

@ManagedBean
@SessionScoped
public class OrderController {
	
	@EJB
	private OrderFacade orderFacade;
	
	private Map<String, Object> currentSessionMap;
	
	private Orders order;

	
	public OrderController(){
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	public List<Orders> getOrders(){
		CustomerController customerController = new CustomerController();
		
		if(customerController.isLogged()){
			Long fetchedId = customerController.getCustomer().getId();
			if(fetchedId != -1)
				return orderFacade.getAllOrdersFromCustomer(fetchedId);
		}
		System.out.println("you do not have any orders yet!");
		return null;
	}
	
	public List<OrderLine> getOrderLines(){
		return orderFacade.getOrderLines(order.getId());
	}
	
	public List<Orders> getLastOrders(int numOrders){
		return orderFacade.getLastOrders(numOrders);
	}
	
	public String goToOrderDetails(Long id){
		return "order?id="+id+"&faces-redirect=true&includeViewParams=true";
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

}
