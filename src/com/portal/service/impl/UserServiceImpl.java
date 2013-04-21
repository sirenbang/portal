package com.portal.service.impl;

import java.util.List;

import com.portal.dao.RoleDAO;
import com.portal.dao.UserDAO;
import com.portal.dao.UserRoleDAO;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.model.UserRole;
import com.portal.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;
	private RoleDAO roleDAO;
	private UserRoleDAO userRoleDAO;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}


	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}


	/* (non-Javadoc)
	 * @see com.portal.service.UserService#login(java.lang.String, java.lang.String)
	 */
	public User login(String username, String password) {
		List<User> users = userDao.findByUsername(username);	// 通过用户名查找用户列表
		
		if (users == null || users.size() == 0) {	// 列表为空或者长度为0时，表示不存在该用户
			return null;
		} else {	// 当用户存在时，取出第一个用户，进行密码匹配
			User user = users.get(0);
			if (user.getEnabled() == 0 && password.equals(user.getPassword())) {	// 密码匹配时，返回查找到的用户对象
				return user;
			} else {	// 密码不匹配时，返回空值
				return null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.UserService#regist(com.portal.model.User)
	 */
	public boolean regist(User user) {
		try {
			userDao.save(user);	// 保存用户
			
			/* ==========保存用户角色信息==============*/
			List<Role> roles = roleDAO.findAll();
			for (Role role : roles) {
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRole.setStatus(0);
				userRoleDAO.save(userRole);
			}
			
			return true;	// 保存成功，返回 true
		} catch (Exception e) {
			e.printStackTrace();
			return false;	// 保存失败，返回false
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.UserService#changePassword(com.portal.model.User)
	 */
	public User changePassword(User user) {
		try {
			user = userDao.merge(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.UserService#findUserByName(java.lang.String)
	 */
	public User findUserByName(String username) {
		
		List<User> users = userDao.findByUsername(username);	// 通过用户名查找用户列表
		
		if (users == null || users.size() == 0) {	// 列表为空或者长度为0时，表示不存在该用户
			return null;
		} else {	// 当用户存在时，取出第一个用户，进行密码匹配
			return users.get(0);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.portal.service.UserService#findAll()
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.portal.service.UserService#batchDeleteUsers(java.lang.String)
	 */
	public boolean batchDeleteUsers(String ids) {
		if (ids != null) {
			String[] idArray = ids.split(",");		// 将用户编号组成的列表转化成字符串数组
			for (String idStr : idArray) {			// 遍历数组
				try {
					int id = Integer.parseInt(idStr.trim());	// 将字符串转化成整形，转化之前，先去除空格
					userDao.delete(userDao.findById(id));		// 先通过用户编号找到用户，再删除改用户信息
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public User findUserById(int id) {
		try {
			return userDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteUserById(int id) {
		try {
			User user = userDao.findById(id);
			userDao.delete(user);
			List<UserRole> userRoles = userRoleDAO.findInStatusByUserId(id);
			for (UserRole userRole : userRoles) {
				userRole.setStatus(0);
				userRoleDAO.merge(userRole);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<User> search(String username, Integer enabled) {
		try {
			return userDao.search(username, enabled);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<UserRole> findUserRoleById(int id) {
		try {
			return userRoleDAO.findByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<UserRole> findInStatusUserRoleById(int id) {
		try {
			return userRoleDAO.findInStatusByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
