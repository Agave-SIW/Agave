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
			String password = request.getParameter("password");
			
			Customer customer = customerFacade.getCustomer(email);
			//request.setAttribute("customer", customer);
			if(customer==null || !customer.getPassword().equals(password)){
				session.setAttribute("customerLogged", false);
				session.setAttribute("customer", null);
				System.out.print("\n\nWRONG MAIL OR PASSWORD\n\n");
				request.setAttribute("ex", "Invalid credentials");
				return "/error.jsp";
			}
			else{
				session.setAttribute("customerLogged", true);
				session.setAttribute("customer", customer);
				System.out.print("\n\nLogin OK\n\n");
			}
			return "/success.jsp";
		} else
			request.setAttribute("ex", "Invalid credentials"); //TODO mostrare in pagina
			return "/error.jsp";
	}

	@Override
	public String perform(HttpServletRequest request,
			ProductFacade productFacade) {
		// TODO Auto-generated method stub
		return null;
	}


}
