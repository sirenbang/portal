package com.portal.service;

import com.portal.model.UserRole;

public interface UserRoleService {

	/**
	 * 通过用户角色编号查找用户角色
	 * 
	 * @param id
	 * @return
	 */
	public UserRole findUserRoleById(int id);
	
	/**
	 * 更新用户角色
	 * 
	 * @param userRole
	 * @return
	 */
	public boolean update(UserRole userRole);
	
}
