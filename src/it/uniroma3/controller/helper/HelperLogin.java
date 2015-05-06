package it.uniroma3.controller.helper;

import javax.servlet.http.HttpServletRequest;

public class HelperLogin {

	public boolean isValid(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean errors = false;

		if ( email == null || email.equals("")) {
			request.setAttribute("nameErr","Email is mandatory");
			errors = true;
		}

		if ( password == null || password.equals("")) {
			request.setAttribute("codeErr","Password is mandatory");
			errors = true;
		}
		return !errors;
	}
}
