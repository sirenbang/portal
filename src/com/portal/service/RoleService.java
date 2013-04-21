package com.portal.service;

import java.util.List;

import com.portal.model.Role;
import com.portal.model.RolePortalGroup;

public interface RoleService {

	/**
	 * 查询角色信息
	 * 
	 * @param roleName
	 * @param enabled
	 * @return
	 */
	public List<Role> search(String roleName, Integer enabled);
	
	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	public boolean add(Role role);
	
	/**
	 * 通过角色名查找用户
	 * 
	 * @param roleName
	 * @return
	 */
	public Role findRoleByName(String roleName);
	
	/**
	 * 通过角色编号查找角色
	 * 
	 * @param id
	 * @return
	 */
	public Role findRoleById(Integer id);
	
	/**
	 * 更新角色，并返回更新后的角色
	 * 
	 * @param role
	 * @return
	 */
	public Role update(Role role);
	
	/**
	 * 通过角色编号删除角色
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteRoleById(Integer id);
	
	/**
	 * 通过角色编号查找角色门户组编号
	 * 
	 * @param id
	 */
	public List<RolePortalGroup> findRolePortalGroupById(int id);
}
