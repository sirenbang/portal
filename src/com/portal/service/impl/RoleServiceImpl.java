package com.portal.service.impl;

import java.util.List;

import com.portal.dao.PortalGroupDAO;
import com.portal.dao.RoleDAO;
import com.portal.dao.RolePortalGroupDAO;
import com.portal.dao.UserDAO;
import com.portal.dao.UserRoleDAO;
import com.portal.model.PortalGroup;
import com.portal.model.Role;
import com.portal.model.RolePortalGroup;
import com.portal.model.User;
import com.portal.model.UserRole;
import com.portal.service.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;
 	private UserDAO userDAO;
	private UserRoleDAO userRoleDAO;
	private PortalGroupDAO portalGroupDAO;
	private RolePortalGroupDAO rolePortalGroupDAO;
	
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	
	public void setPortalGroupDAO(PortalGroupDAO portalGroupDAO) {
		this.portalGroupDAO = portalGroupDAO;
	}

	public void setRolePortalGroupDAO(RolePortalGroupDAO rolePortalGroupDAO) {
		this.rolePortalGroupDAO = rolePortalGroupDAO;
	}

	/* (non-Javadoc)
	 * @see com.portal.service.RoleService#search(java.lang.String, java.lang.Integer)
	 */
	public List<Role> search(String roleName, Integer enabled) {
		try {
			return roleDAO.search(roleName, enabled);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.RoleService#add(com.portal.model.Role)
	 */
	public boolean add(Role role) {
		try {
			roleDAO.save(role);	// 保存角色信息
			
			/*=======保存用户角色信息=========*/
			List<User> users = userDAO.findAll();
			for (User user : users) {
				UserRole userRole = new UserRole();
				userRole.setRole(role);
				userRole.setUser(user);
				userRole.setStatus(0);
				userRoleDAO.save(userRole);
			}
			
			/*=========保存用户资源信息==========*/
			List<PortalGroup> portalGroups = portalGroupDAO.findAll();
			for (PortalGroup portalGroup : portalGroups) {
				RolePortalGroup rpg = new RolePortalGroup();
				rpg.setPortalGroup(portalGroup);
				rpg.setRole(role);
				rpg.setPermission(0);
				rolePortalGroupDAO.save(rpg);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.RoleService#findRoleByName(java.lang.String)
	 */
	public Role findRoleByName(String roleName) {
		try {
			List<Role> roles = roleDAO.findByName(roleName);
			if (roles == null || roles.size() == 0) {
				return null;
			} else {
				return roles.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.RoleService#findRoleById(java.lang.Integer)
	 */
	public Role findRoleById(Integer id) {
		try {
			return roleDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.RoleService#update(com.portal.model.Role)
	 */
	public Role update(Role role) {
		try {
			return roleDAO.merge(role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.RoleService#deleteRoleById(java.lang.Integer)
	 */
	public boolean deleteRoleById(Integer id) {
		try {
			roleDAO.delete(roleDAO.findById(id));
			
			List<UserRole> userRoles = userRoleDAO.findByRoleId(id);
			for (UserRole userRole : userRoles) {
				userRoleDAO.delete(userRole);
			}
			
			List<RolePortalGroup> rolePortalGroups = rolePortalGroupDAO.findByRoleId(id);
			for (RolePortalGroup rolePortalGroup : rolePortalGroups) {
				rolePortalGroupDAO.delete(rolePortalGroup);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.RoleService#findRolePortalGroupById(int)
	 */
	public List<RolePortalGroup> findRolePortalGroupById(int id) {
		try {
			return rolePortalGroupDAO.findByRoleId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
