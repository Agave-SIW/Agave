package it.uniroma3.controller;

import java.util.List;
import java.util.Map;

import it.uniroma3.facade.OrderFacade;
import it.uniroma3.model.Cart;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class CartController {

	private Cart cart;
	private OrderFacade of;

	private Map<String, Object> currentSessionMap;

	public CartController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public String createCart() {
		// security check
		CustomerController cc = new CustomerController();

		if(cc.loggedIn()){
			this.cart = (Cart) of.createOrder(null, null);
			this.currentSessionMap.put("cart", cart);

			System.out.print("\n\nCart Created\n\n");

			return "index?faces-redirect=true";
		}
		else{
			System.out.print("\n\nNo Customer is Logged in\n\n");
			return "index?faces-redirect=true";
		}

	}
	
	public String showCart(){
		//TODO
		
		return "cart?faces-redirect=true";
	}

	public String emptyCart(){
		this.cart = null;

		this.currentSessionMap.put("cart", cart);

		System.out.print("\n\nCart is Empty\n\n");

		return "index?faces-redirect=true";

	}
	
	public Cart getCurrentCart(){
		return (Cart) this.currentSessionMap.get("cart");
	}
	
	public List<OrderLine> getOrderLines(){
		return this.cart.getOrderLines();
	}
	
	public Boolean cartIsEmpty() {
		if(this.getCurrentCart()==null)
			return true;
		return false;
	}
	
	public String addProductToCart(Product product, int quantity){
		OrderLine orderLine = new OrderLine(product.getPrice(), quantity, product);
		
		System.out.print("\n\nCartLine Created\n\n");
		this.cart.addOrderLine(orderLine);
		
		return "cart";
	}
	
	public String removeProductFromCart(Integer index){
		this.cart.removeOrderLine(index);
		
		return "cart";
	}



}


