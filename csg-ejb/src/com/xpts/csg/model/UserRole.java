package com.xpts.csg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * The persistent class for the USER_ROLES database table.
 * 
 */
@Entity
@Table(name="USER_ROLES")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal audittrailid;

	private String roledescr;

	@Id
	private Integer roleid;

	public UserRole() {
	}

	public BigDecimal getAudittrailid() {
		return this.audittrailid;
	}

	public void setAudittrailid(BigDecimal audittrailid) {
		this.audittrailid = audittrailid;
	}

	public String getRoledescr() {
		return this.roledescr;
	}

	public void setRoledescr(String roledescr) {
		this.roledescr = roledescr;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}