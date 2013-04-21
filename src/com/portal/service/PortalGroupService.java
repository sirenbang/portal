package com.portal.service;

import java.util.List;

import com.portal.model.PortalGroup;

public interface PortalGroupService {
	
	/**
	 * 根据门户组名搜索门户信息
	 * 
	 * @param portalGroupName
	 * @return
	 */
	public List<PortalGroup> search(String portalGroupName);
	
	/**
	 * 添加门户组
	 * 
	 * @param portalGroup
	 * @return
	 */
	public boolean add(PortalGroup portalGroup);
	
	/**
	 * 通过门户组名查找对应的门户组
	 * 
	 * @param portalGroupName
	 * @return
	 */
	public PortalGroup findPortalGroupByName(String portalGroupName);
	
	/**
	 * 通过门户组编号查找对应的门户组
	 * 
	 * @param id
	 * @return
	 */
	public PortalGroup findPortalGroupById(int id);
	
	/**
	 * 更新门户组信息
	 * 
	 * @param portalGroup
	 * @return
	 */
	public PortalGroup update(PortalGroup portalGroup);
	
	/**
	 * 根据门户组编号删除对应的门户组
	 * 
	 * @param id
	 * @return
	 */
	public boolean deletePortalGroupById(int id);
}
