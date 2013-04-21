package com.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * PortalGroup entity. @author MyEclipse Persistence Tools
 */

public class PortalGroup implements Serializable {

	private static final long serialVersionUID = 3396031589269859090L;
	
	// Fields

	private Integer id;
	private String name;
	private String description;
	private Integer hidden;
	private Set<Portal> portals = new HashSet<Portal>(0);
	private Set<DefaultPortlet> defaultPortlets = new HashSet<DefaultPortlet>(0);
	private Set<RolePortalGroup> rolePortalGroups = new HashSet<RolePortalGroup>(0);

	// Constructors

	/** default constructor */
	public PortalGroup() {
	}

	/** minimal constructor */
	public PortalGroup(String name) {
		this.name = name;
	}

	/** full constructor */
	public PortalGroup(String name, String description, Integer hidden,
			Set<Portal> portals, Set<DefaultPortlet> defaultPortlets, Set<RolePortalGroup> rolePortalGroups) {
		this.name = name;
		this.description = description;
		this.hidden = hidden;
		this.portals = portals;
		this.defaultPortlets = defaultPortlets;
		this.rolePortalGroups = rolePortalGroups;
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

	public Integer getHidden() {
		return this.hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
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

	public Set<RolePortalGroup> getRolePortalGroups() {
		return this.rolePortalGroups;
	}

	public void setRolePortalGroups(Set<RolePortalGroup> rolePortalGroups) {
		this.rolePortalGroups = rolePortalGroups;
	}

}