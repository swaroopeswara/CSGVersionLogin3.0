package com.xpts.csg.ui.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.xpts.csg.model.User;
import com.xpts.csg.service.UserService;

@Named
@ViewScoped
public class UserMaintenanceController {

	private User user;

	@EJB
	private UserService service;

	@PostConstruct
	public void init(){
		user = service.findUser(10);
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void saveUser() {	
		System.out.println("User object: "+user.toString());		
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())); 
		String json = service.getUserJson(user);
		System.out.println("User json object: "+json);
		service.saveUser(json);
	}

}

