package com.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements Serializable {

	private static final long serialVersionUID = -6253276927654613845L;
	
	// Fields

	private Integer id;
	private String username;
	private String password;
	private Integer enabled = 0;
	private String description;
	private Integer type = 0;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<Portal> portals = new HashSet<Portal>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, Integer enabled, Integer type) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.type = type;
	}

	/** full constructor */
	public User(String username, String password, Integer enabled,
			String description, Integer type, Set<UserRole> userRoles, Set<Portal> portals) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.description = description;
		this.type = type;
		this.userRoles = userRoles;
		this.portals = portals;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<Portal> getPortals() {
		return this.portals;
	}

	public void setPortals(Set<Portal> portals) {
		this.portals = portals;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}