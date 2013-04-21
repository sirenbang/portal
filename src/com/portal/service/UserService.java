package com.portal.service;

import java.util.List;

import com.portal.model.User;
import com.portal.model.UserRole;

public interface UserService {
	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password);
	
	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	public boolean regist(User user);
	
	/**
	 *  修改密码
	 * 
	 * @param user
	 * @return
	 */
	public User changePassword(User user);
	
	/**
	 * 通过用户名查找出用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User findUserByName(String username);
	
	/**
	 * 查找所有用户信息
	 * 
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 批量删除用户信息
	 * 
	 * @param ids
	 * @return
	 */
	public boolean batchDeleteUsers(String ids);
	
	/**
	 * 通过用户编号查找用户
	 * 
	 * @param id
	 * @return
	 */
	public User findUserById(int id);
	
	/**
	 * 通过用户编号删除用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUserById(int id);
	
	/**
	 * 查找用户信息
	 * 
	 * @param username
	 * @param enabled
	 * @return
	 */
	public List<User> search(String username, Integer enabled);
	
	/**
	 * 通过用户编号查找用户角色
	 * 
	 * @param id
	 * @return
	 */
	public List<UserRole> findUserRoleById(int id);
	
	/**
	 * 通过用户编号查找激活的用户角色
	 * 
	 * @param id
	 * @return
	 */
	public List<UserRole> findInStatusUserRoleById(int id);
	
}
