package com.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Portal entity. @author MyEclipse Persistence Tools
 */

public class Portal implements Serializable {

	private static final long serialVersionUID = -8076495853769460041L;
	
	// Fields

	private Integer id;
	private PortalGroup portalGroup;
	private PortalLayout portalLayout;
	private User user;
	private Set<Portlet> portlets = new HashSet<Portlet>(0);

	// Constructors

	/** default constructor */
	public Portal() {
	}

	/** minimal constructor */
	public Portal(PortalGroup portalGroup, PortalLayout portalLayout, User user) {
		this.portalGroup = portalGroup;
		this.portalLayout = portalLayout;
		this.user = user;
	}

	/** full constructor */
	public Portal(PortalGroup portalGroup, PortalLayout portalLayout,
			User user, Set<Portlet> portlets) {
		this.portalGroup = portalGroup;
		this.portalLayout = portalLayout;
		this.user = user;
		this.portlets = portlets;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PortalGroup getPortalGroup() {
		return this.portalGroup;
	}

	public void setPortalGroup(PortalGroup portalGroup) {
		this.portalGroup = portalGroup;
	}

	public PortalLayout getPortalLayout() {
		return this.portalLayout;
	}

	public void setPortalLayout(PortalLayout portalLayout) {
		this.portalLayout = portalLayout;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Portlet> getPortlets() {
		return this.portlets;
	}

	public void setPortlets(Set<Portlet> portlets) {
		this.portlets = portlets;
	}

}