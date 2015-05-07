package it.uniroma3.controller;

import it.uniroma3.controller.action.Action;
import it.uniroma3.facade.*;

import java.io.IOException;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shop/*") 
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@EJB(beanName="Facade")
	private Facade facade;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		genericAction(request, response); 
		return;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		genericAction(request, response); 
		return;
	}
	
	private void genericAction(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String nextPage = null;
		String actionName = path2action(request);
		Action action;
		try {
			action = (Action)Class.forName(actionName).newInstance();
			nextPage = action.perform(request, facade);
		}
		catch (InstantiationException e) {
		 	request.setAttribute("ex", e);
		 	nextPage = "/error.jsp";			
		} catch (IllegalAccessException e) { 
			request.setAttribute("ex", e);
			nextPage = "/error.jsp";
		} catch (ClassNotFoundException e) {
			request.setAttribute("ex", e);
			nextPage = "/error.jsp";
		} 
		nextPage = response.encodeURL(nextPage);
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return; 
	}

	@SuppressWarnings("resource")
	private String path2action(HttpServletRequest request) {

		String path = request.getPathInfo(); 

		String modelCommand = path.substring(1);
		Scanner scanner = new Scanner(modelCommand).useDelimiter("\\.");
		String model = scanner.next();
		model = model.substring(0,1).toUpperCase() + model.substring(1);

		String action = scanner.next();
		action = action.substring(0,1).toUpperCase() + action.substring(1);

		String actionName = "it.uniroma3.controller.action." + action + model;
		return actionName;
	}
}