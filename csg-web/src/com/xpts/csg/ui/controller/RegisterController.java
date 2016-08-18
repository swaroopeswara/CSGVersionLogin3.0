package com.xpts.csg.ui.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.mindrot.jbcrypt.BCrypt;

import com.xpts.csg.model.User;
import com.xpts.csg.model.UserRole;
import com.xpts.csg.service.UserService;
 
@ManagedBean(name = "registerController", eager = true)
@RequestScoped
public class RegisterController implements Serializable {
 
	private static final long serialVersionUID = 8205522068005094784L;

	@PersistenceContext(unitName="csg_pu")
	private EntityManager em;
	
	@EJB
	private UserService service;
	
	private User user;
	
	private int roleid;    
	private List<UserRole> roles;
    private Map<String,Object> rolesData;

    @PostConstruct
	 public void init(){
    	user = new User();
    	roles = getAllRoles();
    	rolesData = new LinkedHashMap<String,Object>();
		 for(UserRole role : getAllRoles()){
			 rolesData.put(role.getRoledescr(), role.getRoleid());
		 }
	 }
    
    private List<UserRole> getAllRoles(){
    	return em.createNamedQuery("UserRole.findAll").getResultList();
    }
    
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
    
    public Map<String,Object> getRolesData() {
		return rolesData;
	}
    
    public RegisterController() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        setRoleid(1);
    }
 
    public String RegisterUser() {    	
    	user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())); 
    	user.setUserind((getRoleid() == 1) ? "E" : "I");
    	service.saveUser(user, getRoleid());
    	return "Home.xhtml"; 
    }

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	
//	private UserRole getRole(Integer id){
//		for(UserRole role : roles){
//			if(role.getRoleid() == id)
//		}
//	}
}

