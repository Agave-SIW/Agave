package it.uniroma3.controller.action;

import it.uniroma3.controller.helper.HelperLogin;
import it.uniroma3.model.Customer;
import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCustomer implements Action {
    
	public String perform(HttpServletRequest request, CustomerFacade customerFacade) {
		
		HelperLogin helper = new HelperLogin();
		HttpSession session = request.getSession();
		
		if (helper.isValid(request)) {
			String email = request.getParameter("email");
			
			Customer customer = customerFacade.getCustomer(email);
			//request.setAttribute("customer", customer);
			if(customer!=null){
				session.setAttribute("customerLogged", true);
				session.setAttribute("customer", customer);
			}
			else{
				session.setAttribute("customerLogged", false);
				session.setAttribute("customer", null);
			}
			
			return "/index.jsp";
		} else
			return "/invalid.jsp";
	}

	@Override
	public String perform(HttpServletRequest request,
			ProductFacade productFacade) {
		// TODO Auto-generated method stub
		return null;
	}


}
