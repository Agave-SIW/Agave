package it.uniroma3.controller.action;

import it.uniroma3.controller.helper.HelperProductId;
import it.uniroma3.model.Product;
import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;

public class GetProduct implements Action {
    
	public String perform(HttpServletRequest request, Facade facade) {
		
		HelperProductId helper = new HelperProductId();
		
		if (helper.isValid(request)) {
			Long id = Long.parseLong(request.getParameter("id"));
			
			Product product = facade.getProduct(id);
			request.setAttribute("product", product);
			
			return "/product.jsp";
		} else
			return "/invalidProduct.jsp";
	}

}
