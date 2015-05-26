package it.uniroma3.controller;

import java.util.GregorianCalendar;
import java.util.List;

import it.uniroma3.facade.CustomerFacade;
import it.uniroma3.facade.OrderFacade;
import it.uniroma3.facade.ProductFacade;
import it.uniroma3.helper.ContextHelper;
import it.uniroma3.model.Customer;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Orders;
import it.uniroma3.model.Product;

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

	@EJB
	private OrderFacade orderFacade;
	@EJB
	private CustomerFacade customerFacade;
	@EJB
	private ProductFacade productFacade;

	private ContextHelper ch;

	@ManagedProperty(value="#{param.id}")
	private Long id;
	
	private Orders order;
	private Customer orderCustomer;

	public OrderController(){
		this.ch = new ContextHelper();
	}

	public void findOrderAndCustomer(Long idOrder){
		this.order = orderFacade.getOrder(idOrder);
		this.orderCustomer = customerFacade.getCustomerByOrderId(idOrder);
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

	public boolean canEvade(Long idOrder){

		if(!new AdminController().isLogged())
			return false;

		Orders myOrder = orderFacade.getOrder(idOrder);

		if(myOrder != null)
			return myOrder.getEvasionTime() == null;

		System.out.println("Order is null");

		return  false;
	}


	public List<Orders> getLastOrders(int numOrders){
		return orderFacade.getLastOrders(numOrders);
	}

	public String findOrder(Long orderId){
		return "order?id=" + orderId + "&faces-redirect=true&includeViewParams=true";
	}

	public String findOrder(){
		return findOrder(this.id);
	}

	public double getOrderTotal(Long orderId){
		double sum = 0;
		for(OrderLine ol : orderFacade.getOrderLines(orderId))
			sum += ol.getQuantity()*ol.getProduct().getPrice();
		return sum;
	}

	public String tryEvadeOrder(Long idOrder){
		System.out.println(idOrder);
		try{
			Orders closedOrder = orderFacade.getOrder(idOrder);

			if(closedOrder.canEvadeAllLines()){
				evadeOrder(closedOrder);
				closedOrder.setEvasionTime(new GregorianCalendar().getTime());
				this.orderFacade.updateOrder(closedOrder);
				ch.addToSession("evadedResult", new Boolean(true));
				return "evadedResult.xhtml?faces-redirect=true&includeViewParams=true";
			}
			else{
				ch.addToSession("evadeResult", new Boolean(false));
				return "evadedResult.xhtml?faces-redirect=true&includeViewParams=true";
			}
		}catch(Exception e){
			ch.addToSession("evadedResult", new Boolean(false));
			return "evadedResult.xhtml?faces-redirect=true&includeViewParams=true";
		}
		
	}
	
	public void evadeOrder(Orders order) {
		for(OrderLine ol : order.getOrderLines()){
			Product product = ol.getProduct();
			product.decreaseStorageQuantity(ol.getQuantity());
			productFacade.updateProduct(product);
		}
	}

	/** GETTER AND SETTERS **/

	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
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

	public Customer getOrderCustomer() {
		return orderCustomer;
	}

	public void setOrderCustomer(Customer customer) {
		this.orderCustomer = customer;
	}
	
	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public ProductFacade getProductFacade() {
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
