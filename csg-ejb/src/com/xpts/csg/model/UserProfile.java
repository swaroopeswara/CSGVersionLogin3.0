package com.xpts.csg.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * The persistent class for the USER_PROFILES database table.
 * 
 */
@Entity
@Table(name="USER_PROFILES")
public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer profileid = 1;

	private Integer roleid;

	private Integer userid;

	@Id
	@SequenceGenerator(name = "USER_PROFILE_ID_GEN", sequenceName = "USER_PROFILE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILE_ID_GEN")
	private Integer userprofileid;

	public UserProfile() {
	}

	public Integer getProfileid() {
		return this.profileid;
	}

	public void setProfileid(Integer profileid) {
		this.profileid = profileid;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getUserprofileid() {
		return this.userprofileid;
	}

	public void setUserprofileid(Integer userprofileid) {
		this.userprofileid = userprofileid;
	}

}