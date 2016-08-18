package com.xpts.csg.ui.login.jass;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.mindrot.jbcrypt.BCrypt;

import com.xpts.csg.common.JndiUtils;
import com.xpts.csg.model.User;

public class UserLoginModule implements LoginModule {

	private static final String INVALID_PASSWORD = "The password specified was invalid";
	private static final String INVALID_USER = "Denied due to an invalid username";
	private static final String PERSISTENCE_UNIT_NAME = "java:global/csg/env/persistence/csg_pu";
	
	private User user;
	private Subject subject;	
	private CallbackHandler callback;
	private EntityManager em;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callback, Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callback = callback;
		this.em = JndiUtils.lookup(PERSISTENCE_UNIT_NAME);
		this.user = this.getUser();
	}
	
	@Override
	public boolean login() throws LoginException {
		try {
			onLogin(user, getPassword());
//			em.merge(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		this.subject.getPrincipals().clear();
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		Principal identity = new DefaultPrinciple(user.getUsername());
		Group roles = new DefaultGroup("Roles");
		subject.getPrincipals().add(identity);
		subject.getPrincipals().add(roles);
		return true;
	}
	
	private void onLogin(User user, String password) throws LoginException {
		if (user == null) {
			throw new LoginException(INVALID_USER);
		} 
		boolean valid = user.getPassword() == null ? false : BCrypt.checkpw(password, user.getPassword());
		if (!valid) {
			throw new LoginException(INVALID_PASSWORD);
		} 
	}
	
	private User getUser() {
		return (User) em.createNamedQuery("User.findByUsername").setParameter(1, getUserName()).getResultList().get(0);		
	}
	
	private String getUserName() {
		NameCallback nc = new NameCallback("_");
		try {
			callback.handle(new Callback[] { nc });
			return nc.getName();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected String getPassword() {
		PasswordCallback pc = new PasswordCallback("_", false);
		try {
			callback.handle(new Callback[] { pc });
			return new String(pc.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	protected HttpSession getSession() {
//		try {
//			return ((HttpServletRequest) PolicyContext
//				.getContext(HttpServletRequest.class.getName()))
//				.getSession();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
}
