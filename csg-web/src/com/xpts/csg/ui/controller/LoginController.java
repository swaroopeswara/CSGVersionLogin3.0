package com.xpts.csg.ui.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@RequestScoped
@Named
public class LoginController implements Serializable {

	private static final long serialVersionUID = 5534145551385779620L;

	private String username;
	private String password;

	@PersistenceContext(unitName="csg_pu")
	private EntityManager em;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
		try {
			if (Faces.getRemoteUser() == null) {
				Faces.getRequest().login(username, password);		
				Faces.getRequest().getSession(true).setAttribute("username", username);
			}
			Faces.redirect("Secure/Inbox.jsf");
		} catch (ServletException e) {
			Messages.addError(null, "login.failed", e.getMessage());
		} catch (IOException e) {}	
	}

	public void resetPassword(){
		//TODO
	}
	
	public void logout() {
		Faces.invalidateSession();
		try {
			Faces.redirect("login.jsf");
		} catch (IOException e) {}
	}
}
