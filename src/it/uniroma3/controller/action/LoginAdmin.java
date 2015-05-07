package it.uniroma3.controller.action;

import it.uniroma3.controller.helper.HelperLogin;
import it.uniroma3.model.Admin;
import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginAdmin implements Action {
    
	public String perform(HttpServletRequest request, Facade facade) {
		
		HelperLogin helper = new HelperLogin();
		HttpSession session = request.getSession();
		
		if (helper.isValid(request)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Admin admin = facade.getAdmin(email);
			if(admin==null || !admin.getPassword().equals(password)){
				session.setAttribute("adminLogged", false);
				session.setAttribute("admin", null);
				System.out.print("\n\nWRONG MAIL OR PASSWORD Admin\n\n");
				request.setAttribute("ex", "Invalid credentials");
				return "/error.jsp";
			}
			else{
				session.setAttribute("adminLogged", true);
				session.setAttribute("admin", admin);
				System.out.print("\n\nLogin Admin OK\n\n");
			}
			return "/success.jsp";
		} else
			request.setAttribute("ex", "Invalid credentials"); //TODO mostrare in pagina
			return "/error.jsp";
	}



}
