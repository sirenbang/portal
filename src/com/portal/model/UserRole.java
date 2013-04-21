package com.portal.model;

import java.io.Serializable;

/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */

public class UserRole implements Serializable {

	private static final long serialVersionUID = 793212049729919812L;
	
	// Fields

	private Integer id;
	private Role role;
	private User user;
	private int status = 0;

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** full constructor */
	public UserRole(Role role, User user, int status) {
		this.role = role;
		this.user = user;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}