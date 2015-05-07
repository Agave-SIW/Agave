package it.uniroma3.controller.action;

import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAdmin implements Action {

	public String perform(HttpServletRequest request, AdminFacade adminFacade) {

		HttpSession session = request.getSession();

		session.setAttribute("adminLogged", false);
		session.setAttribute("admin", null);

		return "/success.jsp";

	}

	@Override
	public String perform(HttpServletRequest request,
			ProductFacade productFacade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String perform(HttpServletRequest request, CustomerFacade customerFacade) {
		// TODO Auto-generated method stub
		return null;
	}


}
