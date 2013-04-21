package com.portal.service;

import com.portal.model.RolePortalGroup;

public interface RolePortalGroupService {

	/**
	 * 通过角色门户组编号查找角色门户组
	 * 
	 * @param id
	 * @return
	 */
	public RolePortalGroup findRolePortalGroupById(int id);
	
	/**
	 * 更新角色门户组
	 * 
	 * @param userRole
	 * @return
	 */
	public boolean update(RolePortalGroup rolePortalGroup);
	
}
