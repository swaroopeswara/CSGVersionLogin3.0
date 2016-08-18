package com.xpts.csg.service.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.xpts.csg.common.JsonSerializer;
import com.xpts.csg.model.User;
import com.xpts.csg.model.UserProfile;
import com.xpts.csg.service.UserService;

@Stateless
public class UserServiceBean implements UserService {

	@PersistenceContext(unitName="csg_pu")
	private EntityManager em;
	
	@Override
	public void saveUser(String userJson){
		User user = JsonSerializer.create(User.class, false).deserialize(userJson);	
		saveUser(user);
	}

	@Override
	public void saveUser(User user){
		user = em.merge(user);
		System.out.println("user persisted to db: "+user.getUserid());
	}
		
	@Override
	public void saveUser(User user, Integer roleId){
		user = em.merge(user);
		System.out.println("user persisted to db: "+user.getUserid());
		saveUserProfile(user.getUserid(), roleId);
	}
	
	private void saveUserProfile(Integer userId, Integer roleId){
		UserProfile userProfile = new UserProfile();
		userProfile.setUserid(userId);
		userProfile.setRoleid(roleId);
		userProfile = em.merge(userProfile);
		System.out.println("userProfile persisted to db: "+userProfile.getUserprofileid());
	}
	
	@Override
	public User findUser(Integer id){
		return em.find(User.class, id);
	}
	
	@Override
	public String getUserJson(Integer id){
	    return getUserJson(findUser(id));
	}
		
	@Override
	public String getUserJson(User user){
	    return JsonSerializer.create(User.class, false).serialize(user);
	}
	
}
