package com.xpts.csg.service;

import javax.ejb.Local;

import com.xpts.csg.model.User;

@Local
public interface UserService {

	void saveUser(String userJson);
	
	void saveUser(User user);
	
	void saveUser(User user, Integer roleId);
	
	User findUser(Integer id);
	
	String getUserJson(Integer id);
	
	String getUserJson(User user);
}
