package it.uniroma3.helper;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Helper class for Faces Context management
 *
 * @author Gaetano
 *
 */

public class ContextHelper {

	private FacesContext currentInstance;
	private ExternalContext externalContext;
	private Map<String, Object> sessionMap;

	public ContextHelper() {
		this.currentInstance = FacesContext.getCurrentInstance();
		this.externalContext = currentInstance.getExternalContext();
		this.sessionMap = externalContext.getSessionMap();
	}
	
	//for easier target input
	public void addMessage(String formId, String fieldName, String message){
		this.addMessage(formId+":"+fieldName, message);
	}
	
	//target is like "formId:fieldName"
	public void addMessage(String target, String message){
		this.addMessage(target, new FacesMessage(message));
	}
	
	//target is like "formId:fieldName"
	public void addMessage(String target, FacesMessage message){
		this.currentInstance.addMessage(target, message);
	}

	public void addToSession(String key, Object object){
		this.sessionMap.put(key, object);
	}

	public Object getFromSession(String key){
		return this.sessionMap.get(key);
	}

	public FacesContext getCurrentInstance() {
		return currentInstance;
	}

	public void setCurrentInstance(FacesContext currentInstance) {
		this.currentInstance = currentInstance;
	}

	public ExternalContext getExternalContext() {
		return externalContext;
	}

	public void setExternalContext(ExternalContext externalContext) {
		this.externalContext = externalContext;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}


