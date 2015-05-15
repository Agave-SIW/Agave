package it.uniroma3.controller;

import java.util.List;

import it.uniroma3.facade.CustomerFacade;
import it.uniroma3.facade.OrderFacade;
import it.uniroma3.facade.ProductFacade;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Orders;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class CartController {
	
	private Orders cart;
	
	@EJB
	private OrderFacade orderFacade;
	@EJB
	private CustomerFacade customerFacade;
	
	public void setCartFromId(Long idCustomer) {
		try{
			this.cart = customerFacade.getCart(idCustomer);
		}
		catch(Exception e){
			//TODO
		}
	}
	
	
	/*SETTER and GETTER*/

	public Orders getCart() {
		return cart;
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
	
	

}
