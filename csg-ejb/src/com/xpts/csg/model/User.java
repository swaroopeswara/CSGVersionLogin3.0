package com.xpts.csg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -5402959135226322993L;
	@Id	
	@SequenceGenerator(name = "CSG_USER_ID_GEN", sequenceName = "CSG_USER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CSG_USER_ID_GEN")
	private Integer userid;
	
	@Column(name="AUDITTRAIL_ID")
	private Integer audittrailId = 1;

	private String emailaddress;

	private String name;

	private String password;

	private String plsnumber;

	private String userind;

	private String username;

	public User() {
	}

	public Integer getAudittrailId() {
		return this.audittrailId;
	}

	public void setAudittrailId(Integer audittrailId) {
		this.audittrailId = audittrailId;
	}

	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlsnumber() {
		return this.plsnumber;
	}

	public void setPlsnumber(String plsnumber) {
		this.plsnumber = plsnumber;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUserind() {
		return this.userind;
	}

	public void setUserind(String userind) {
		this.userind = userind;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}