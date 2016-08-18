package com.xpts.csg.ui.login.jass;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class DefaultGroup extends DefaultPrinciple implements Group {

	private static final long serialVersionUID = 6051859639378507247L;

	private Set<Principal> members;

	public DefaultGroup(String name) {
		super(name);
		members = new HashSet<>();
	}

	public DefaultGroup(DefaultGroup other) {
		super(other);
		members = new HashSet<>(other.members);
	}

	@Override
	public boolean addMember(Principal user) {
		return members.add(user);
	}

	@Override
	public boolean removeMember(Principal user) {
		return members.remove(user);
	}

	@Override
	public boolean isMember(Principal member) {
		return members.contains(member);
	}

	@Override
	public Enumeration<Principal> members() {
		return Collections.enumeration(members);
	}

	@Override
	public String toString() {
		return "DefaultGroup [getName()=" + getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof DefaultGroup)) {
			return false;
		}
		DefaultGroup other = (DefaultGroup) obj;
		if (members == null) {
			if (other.members != null) {
				return false;
			}
		} else if (!members.equals(other.members)) {
			return false;
		}
		return true;
	}
}
