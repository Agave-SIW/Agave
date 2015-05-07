package it.uniroma3.controller.action;

import java.util.List;

import it.uniroma3.model.Product;
import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;

public class ListProduct implements Action {
    
	public String perform(HttpServletRequest request, ProductFacade productFacade) {	
		List<Product> products = productFacade.getAllProducts();
		request.setAttribute("products", products);	//TODO   invertire lista prodotti che si passa alla view
		return "/products.jsp";
	}

	@Override
	public String perform(HttpServletRequest request,
			CustomerFacade customerFacade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String perform(HttpServletRequest request, AdminFacade adminFacade) {
		// TODO Auto-generated method stub
		return null;
	}
}
