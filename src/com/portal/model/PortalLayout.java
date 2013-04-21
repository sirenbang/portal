package com.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * PortalLayout entity. @author MyEclipse Persistence Tools
 */

public class PortalLayout implements Serializable {

	private static final long serialVersionUID = 3436878342548774660L;
	
	// Fields

	private Integer id;
	private String name;
	private String description;
	private Set<Portal> portals = new HashSet<Portal>(0);
	private Set<DefaultPortlet> defaultPortlets = new HashSet<DefaultPortlet>(0);

	// Constructors

	/** default constructor */
	public PortalLayout() {
	}

	/** full constructor */
	public PortalLayout(String name, String description, Set<Portal> portals,
			Set<DefaultPortlet> defaultPortlets) {
		this.name = name;
		this.description = description;
		this.portals = portals;
		this.defaultPortlets = defaultPortlets;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Portal> getPortals() {
		return this.portals;
	}

	public void setPortals(Set<Portal> portals) {
		this.portals = portals;
	}

	public Set<DefaultPortlet> getDefaultPortlets() {
		return this.defaultPortlets;
	}

	public void setDefaultPortlets(Set<DefaultPortlet> defaultPortlets) {
		this.defaultPortlets = defaultPortlets;
	}

}