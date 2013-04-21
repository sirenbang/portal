package com.portal.service.impl;

import com.portal.dao.RolePortalGroupDAO;
import com.portal.model.RolePortalGroup;
import com.portal.service.RolePortalGroupService;

public class RolePortalGroupServiceImpl implements RolePortalGroupService {

	private RolePortalGroupDAO rolePortalGroupDAO;
	
	public RolePortalGroupDAO getRolePortalGroupDAO() {
		return rolePortalGroupDAO;
	}

	public void setRolePortalGroupDAO(RolePortalGroupDAO rolePortalGroupDAO) {
		this.rolePortalGroupDAO = rolePortalGroupDAO;
	}

	public RolePortalGroup findRolePortalGroupById(int id) {
		try {
			return rolePortalGroupDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(RolePortalGroup rolePortalGroup) {
		try {
			rolePortalGroupDAO.merge(rolePortalGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
