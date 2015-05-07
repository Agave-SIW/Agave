package it.uniroma3.controller.action;

import java.util.List;

import it.uniroma3.model.Product;
import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;

public class ListProduct implements Action {
    
	public String perform(HttpServletRequest request, Facade facade) {	
		List<Product> products = facade.getAllProducts();
		request.setAttribute("products", products);	//TODO   invertire lista prodotti che si passa alla view
		return "/products.jsp";
	}

}
