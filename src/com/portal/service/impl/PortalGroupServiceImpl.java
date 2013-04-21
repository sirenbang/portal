package com.portal.service.impl;

import java.util.List;

import com.portal.dao.PortalGroupDAO;
import com.portal.dao.RoleDAO;
import com.portal.dao.RolePortalGroupDAO;
import com.portal.model.PortalGroup;
import com.portal.model.Role;
import com.portal.model.RolePortalGroup;
import com.portal.service.PortalGroupService;

public class PortalGroupServiceImpl implements PortalGroupService {
	
	private PortalGroupDAO portalGroupDAO;
	private RolePortalGroupDAO rolePortalGroupDAO;
	private RoleDAO roleDAO;
	
	public PortalGroupDAO getPortalGroupDAO() {
		return portalGroupDAO;
	}

	public void setPortalGroupDAO(PortalGroupDAO portalGroupDAO) {
		this.portalGroupDAO = portalGroupDAO;
	}

	public RolePortalGroupDAO getRolePortalGroupDAO() {
		return rolePortalGroupDAO;
	}

	public void setRolePortalGroupDAO(RolePortalGroupDAO rolePortalGroupDAO) {
		this.rolePortalGroupDAO = rolePortalGroupDAO;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortalGroupService#search(java.lang.String)
	 */
	public List<PortalGroup> search(String portalGroupName) {
		try {
			return portalGroupDAO.search(portalGroupName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortalGroupService#add(com.portal.model.PortalGroup)
	 */
	public boolean add(PortalGroup portalGroup) {
		try {
			portalGroupDAO.save(portalGroup);
			
			List<Role> roles = roleDAO.search(null, null);
			for (Role role : roles) {
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
	 * @see com.portal.service.PortalGroupService#findPortalGroupByName(java.lang.String)
	 */
	public PortalGroup findPortalGroupByName(String portalGroupName) {
		try {
			List<PortalGroup> portalGroups = portalGroupDAO.findByName(portalGroupName);
			
			if (portalGroups == null || portalGroups.size() == 0) {
				return null;
			}
			
			return portalGroups.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortalGroupService#findPortalGroupById(int)
	 */
	public PortalGroup findPortalGroupById(int id) {
		try {
			return portalGroupDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortalGroupService#update(com.portal.model.PortalGroup)
	 */
	public PortalGroup update(PortalGroup portalGroup) {
		try {
			return portalGroupDAO.merge(portalGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortalGroupService#deletePortalGroupById(int)
	 */
	public boolean deletePortalGroupById(int id) {
		try {
			portalGroupDAO.delete(portalGroupDAO.findById(id));
			
			List<RolePortalGroup> rpgs = rolePortalGroupDAO.findByPortalGroupId(id);
			
			for (RolePortalGroup rolePortalGroup : rpgs) {
				rolePortalGroupDAO.delete(rolePortalGroup);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
