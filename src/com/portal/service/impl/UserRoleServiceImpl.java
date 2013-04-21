package com.portal.service.impl;

import com.portal.dao.UserRoleDAO;
import com.portal.model.UserRole;
import com.portal.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDAO userRoleDAO;
	
	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public UserRole findUserRoleById(int id) {
		try {
			return userRoleDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(UserRole userRole) {
		try {
			userRoleDAO.merge(userRole);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
