package it.uniroma3.controller.action;

import it.uniroma3.facade.*;

import javax.servlet.http.HttpServletRequest;

public interface Action {

	public String perform(HttpServletRequest request, ProductFacade productFacade);
	public String perform(HttpServletRequest request, CustomerFacade customerFacade);
	public String perform(HttpServletRequest request, AdminFacade adminFacade);

}
