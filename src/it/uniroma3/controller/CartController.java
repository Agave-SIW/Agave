package it.uniroma3.controller;

import java.util.List;

import it.uniroma3.facade.CustomerFacade;
import it.uniroma3.facade.OrderFacade;
import it.uniroma3.helper.ContextHelper;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Orders;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * System operations for Cart management
 * 
 * @author Veronica
 *
 */

@ManagedBean
public class CartController {

	private Orders cart;
	private Integer quantity;


	@EJB
	private OrderFacade orderFacade;
	@EJB
	private CustomerFacade customerFacade;

	private ContextHelper ch;


	public CartController() {
		this.ch = new ContextHelper();
	}

	public Orders setCartFromId(Long idCustomer) {
		try{
			this.cart = customerFacade.getCart(idCustomer);
		}
		catch(Exception e){
			System.out.println("Error while trying to set controller cart from customer id");
		}
		return this.cart;
	}

	public Float getTotal() {
		List<OrderLine> ols = this.cart.getOrderLines();
		Integer size = ols.size();
		Float total = new Float(0);

		for (Integer i = 0; i < size; i++) {
			total += (ols.get(i).getQuantity() * ols.get(i).getProduct().getPrice());
		}

		return total;
	}

	public String addProductToCart(Orders cart, Product product){
		CustomerController cc = new CustomerController();

		if(cc.isNotLogged()) {

			this.ch.addErrorMessage("You must be logged to complete this operation");
			return "WEB-INF/error";
		}

		Integer quantity = 1;
		if(this.quantity != null && this.quantity != 0) quantity = this.quantity;
		System.out.println("trying to add " + quantity +" Product to Cart");

		try{
			orderFacade.addProductToCart(cart, product, quantity);
			System.out.println("Product added to Cart");

			return "cart?faces-redirect=true";
		}
		catch(Exception e){
			System.out.println("Insufficient Storage Quantity");
			
			this.ch.addErrorMessage("Cart already contains the storage quantity");
			return "WEB-INF/error?quantity=-1";
		}

	}

	public String addProductToCartReactive(Orders cart, Product product){
		String output = addProductToCart(cart, product);
		
		if(output.indexOf("quantity") != -1)
			return "Insufficient quantity";

		if(output.indexOf("error") != -1)
			return "Error";

		return "Success";
	}

	public String removeOrderLine(Orders cart, OrderLine orderLine){
		CustomerController cc = new CustomerController();

		if(cc.isNotLogged()) {

			this.ch.addErrorMessage("You must be logged to complete this operation");
			return "WEB-INF/error";
		}

		System.out.println("Trying to Remove OrderLine");
		orderFacade.removeOrderLine(cart, orderLine);

		return "cart?faces-redirect=true";
	}

	public String confirmCart(Orders cart){
		CustomerController cc = new CustomerController();

		if(cc.isNotLogged())  {
			//return "WEB-INF/errorNotLogged";
			this.ch.addErrorMessage("You must be logged to complete this operation");
			return "WEB-INF/error";
		}

		System.out.println("Creating new Order from Cart");

		try{
			orderFacade.createOrderFromCart(cart);
			//return "WEB-INF/successNewOrder";
			this.ch.addSuccessMessage("New Order created successfully");
			return "WEB-INF/success";
		}
		catch(Exception e){
			System.out.println("Cart is Empty!");
			//return "WEB-INF/errorEmptyCart";
			this.ch.addErrorMessage("You can't create an order from an empty cart");
			return "WEB-INF/error";
		}		
	}

	public boolean IsEmpty(Orders cart){
		return cart.isEmpty();
	}

	public boolean NotEmpty(Orders cart){
		return !cart.isEmpty();
	}

	public Orders getCart() {
		return cart;
	}

	public List<OrderLine> getOrderLines(Orders cart) {
		return cart.getOrderLines();
	}

	public void setCart(Orders cart) {
		this.cart = cart;
	}

	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	

}
