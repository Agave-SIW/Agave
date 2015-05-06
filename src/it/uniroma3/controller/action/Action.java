package it.uniroma3.controller.action;

import it.uniroma3.model.ProductFacade;
import javax.servlet.http.HttpServletRequest;

public interface Action {

	public String perform(HttpServletRequest request, ProductFacade facade);

}
