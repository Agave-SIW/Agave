package it.uniroma3.controller.action;

import it.uniroma3.controller.helper.HelperProduct;
import it.uniroma3.model.Product;
import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;

public class CreateProduct implements Action {

	public String perform(HttpServletRequest request, ProductFacade productFacade) {
		
		HelperProduct helper = new HelperProduct();
		
		if (helper.isValid(request)) {
			String name = request.getParameter("name");
			String code = request.getParameter("code");
			Float price = Float.parseFloat(request.getParameter("price"));
			String description = request.getParameter("description");
			
			Product product = productFacade.createProduct(name, code, price, description);
			request.setAttribute("product", product);
			
			return "/product.jsp";
		} else
			return "/newProduct.jsp";
	}

	@Override
	public String perform(HttpServletRequest request,
			CustomerFacade customerFacade) {
		// TODO Auto-generated method stub
		return null;
	}

}
