package it.uniroma3.controller;

import java.util.List;
import java.util.Map;

import it.uniroma3.model.Cart;
import it.uniroma3.model.CartLine;
import it.uniroma3.model.Product;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class CartController {

	private Cart cart;

	private Map<String, Object> currentSessionMap;

	public CartController() {
		this.currentSessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public String createCart() {
		// security check
		CustomerController cc = new CustomerController();

		if(cc.loggedIn()){
			this.cart = new Cart();
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
	
	public List<CartLine> getCartLines(){
		return this.cart.getCartLines();
	}
	
	public Boolean cartIsEmpty() {
		if(this.getCurrentCart()==null)
			return true;
		return false;
	}
	
	public boolean addProductToCart(Product product, int quantity){
		CartLine cartline = new CartLine(product.getPrice(), quantity, product);
		
		System.out.print("\n\nCartLine Created\n\n");
		return this.cart.addCartLine(cartline);
	}
	
	public String removeProductFromCart(Product product){
		int index = this.cart.getIndex(product);
		this.cart.removeCartLine(index);
		
		return "cart";
	}



}


