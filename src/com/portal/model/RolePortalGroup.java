package com.portal.model;

import java.io.Serializable;

/**
 * RolePortalGroup entity. @author MyEclipse Persistence Tools
 */

public class RolePortalGroup implements Serializable {

	private static final long serialVersionUID = 8799215948479556713L;
	
	// Fields

	private Integer id;
	private PortalGroup portalGroup;
	private Role role;
	private Integer permission = 0;

	// Constructors

	/** default constructor */
	public RolePortalGroup() {
	}

	/** full constructor */
	public RolePortalGroup(PortalGroup portalGroup, Role role,
			Integer permission) {
		this.portalGroup = portalGroup;
		this.role = role;
		this.permission = permission;
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

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getPermission() {
		return this.permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

}