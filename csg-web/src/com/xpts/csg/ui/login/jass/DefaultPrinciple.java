package com.xpts.csg.ui.login.jass;

import java.io.Serializable;
import java.security.Principal;

public class DefaultPrinciple implements Principal, Serializable {

	private static final long serialVersionUID = 7701951188631723261L;

	private final String name;

	public DefaultPrinciple(String name) {
		this.name = name;
	}

	public DefaultPrinciple(DefaultPrinciple other) {
		this.name = other.name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "DefaultPrinciple [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DefaultPrinciple)) {
			return false;
		}
		DefaultPrinciple other = (DefaultPrinciple) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
