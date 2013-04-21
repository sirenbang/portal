package com.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements Serializable {

	private static final long serialVersionUID = -8314004514791596925L;
	
	// Fields

	private Integer id;
	private String name;
	private String description;
	private Integer enable;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<RolePortalGroup> rolePortalGroups = new HashSet<RolePortalGroup>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String name, Integer enable) {
		this.name = name;
		this.enable = enable;
	}

	/** full constructor */
	public Role(String name, String description, Integer enable, Set<UserRole> userRoles,
			Set<RolePortalGroup> rolePortalGroups) {
		this.name = name;
		this.description = description;
		this.enable = enable;
		this.userRoles = userRoles;
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

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<RolePortalGroup> getRolePortalGroups() {
		return this.rolePortalGroups;
	}

	public void setRolePortalGroups(Set<RolePortalGroup> rolePortalGroups) {
		this.rolePortalGroups = rolePortalGroups;
	}

}