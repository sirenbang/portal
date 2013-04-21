package com.portal.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.portal.model.User;
import com.portal.model.UserRole;
import com.portal.service.UserRoleService;
import com.portal.service.UserService;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -5227192124737484498L;
	
	private static final Logger log = LoggerFactory.getLogger(UserAction.class);
	private static final int pageSize = 10;

	// User 服务类
	private UserService userService;
	private UserRoleService userRoleService;
	
	private User user;
	private List<User> showUsers;
	
	private String repassword;		// 确认密码
	private int enabled;			// 是否激活
	private String description;		// 描述
	private int id;					// 待操作的用户编号
	private String ids;				// 待操作的用户编号
	private String msg;				// 返回信息
	private int currentPage = 1;	// 当前页码
	private int totalPage = 0;		// 总页数
	private String searchUsername;	// 查找时输入的用户名
	private Integer searchEnabled;	// 查找时输入的用户状态信息
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setIds(int id) {
		this.id = id;
	}
	public List<User> getShowUsers() {
		return showUsers;
	}
	public void setShowUsers(List<User> showUsers) {
		this.showUsers = showUsers;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSearchUsername() {
		return searchUsername;
	}
	public void setSearchUsername(String searchUsername) {
		this.searchUsername = searchUsername;
	}
	public Integer getSearchEnabled() {
		return searchEnabled;
	}
	public void setSearchEnabled(Integer searchEnabled) {
		this.searchEnabled = searchEnabled;
	}
	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	/**
	 * 登录Action
	 * 
	 * @return
	 */
	public String login() {
		log.debug("=======> invoke login Action");
		if (this.user.getUsername() == null || "".equals(this.user.getUsername())) {
			msg = "用户名不能为空";
			ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
			return INPUT;
		}
		
		if (this.user.getPassword() == null || "".equals(this.user.getPassword())) {
			msg = "密码不能为空";
			ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
			return INPUT;
		}
		
		this.user = userService.login(this.user.getUsername(), this.user.getPassword());
		
		if (user == null) {
			msg = "用户名与密码不匹配";
			ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
			return INPUT;
		} else {
			ActionContext.getContext().getSession().put("user", user);
			msg = "登录成功";
			ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
			return SUCCESS;
		}
		
	}
	
	/**
	 * 注册Action
	 * 
	 * @return
	 */
	public String regist() {
		log.debug("===> invoke regist Action");
		// 判断用户名是否为空
		if (this.user.getUsername() == null || "".equals(this.user.getUsername())) {
			msg = "用户名不能为空！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		// 判断密码是否为空
		if (this.user.getPassword() == null || "".equals(this.user.getPassword())) {
			msg = "密码不能为空！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		// 判断确认密码是否为空
		if (this.repassword == null || "".equals(this.repassword)) {
			msg = "请确认密码！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		// 判断两次输入密码是否一致
		if (!this.repassword.equals(this.user.getPassword())) {
			msg = "两次输入密码不一致！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		// 保存用户信息
		if (this.userService.regist(user)) {
			msg = "注册成功";
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
			return SUCCESS;
		} else {
			msg = "注册失败";
			ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
			return INPUT;
		}
	}
	
	/**
	 * 退出Action
	 * 
	 * @return
	 */
	public String logout() {
		log.debug("===> invoke logout Action");
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		msg = "成功退出";
		ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
		return INPUT;
	}
	
	/**
	 * 修改密码Action
	 * 
	 * @return
	 */
	public String changePassword() {
		log.debug("===> invoke changePassword Action");
		// 判断用户名是否为空
		if (this.user.getUsername() == null || "".equals(this.user.getUsername())) {
			msg = "用户名不能为空！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		// 判断密码是否为空
		if (this.user.getPassword() == null || "".equals(this.user.getPassword())) {
			msg = "密码不能为空！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		// 判断确认密码是否为空
		if (this.repassword == null || "".equals(this.repassword)) {
			msg = "请确认密码！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		// 判断两次输入密码是否一致
		if (!this.repassword.equals(this.user.getPassword())) {
			msg = "两次输入密码不一致！";
			ActionContext.getContext().getSession().put("msg", msg);
			return INPUT;
		}
		
		this.user = userService.findUserByName(user.getUsername());		// 通过用户名查找到用户信息
		if (user != null) {
			user.setPassword(this.repassword);
			// 修改用户密码
			this.user = this.userService.changePassword(user);
			if (this.user != null) {
				msg = "修改密码成功";
				ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				return SUCCESS;
			} else {
				msg = "修改密码失败";
				ServletActionContext.getRequest().getSession().setAttribute("msg", msg);
				return INPUT;
			}
		} else {
			return INPUT;
		}
		
	}
	
	/**
	 * 用户管理Action
	 * 
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	public String allUser() {
		List<User> allUsers = userService.search(this.searchUsername, this.searchEnabled);
		Map session = ActionContext.getContext().getSession();
		this.showUsers = (List<User>) session.get("showUsers");			// 从session中取出当前正在显示的用户信息
		if (showUsers == null) {		// 当显示用户信息列表为空时，新建对象
			showUsers = new ArrayList<User>();
		} else {						// 当当前显示用户信息列表不为空时，清空用户信息列表，以便放入待显示用户信息，避免冲突
			showUsers.clear();
		}
		
		if (allUsers == null || allUsers.size() == 0) {			// 当所有用户信息为空时，设置页脚导航处的值
			this.totalPage = 1;
			this.currentPage = 1;
		} else {
			int userSize = allUsers.size();
			this.totalPage = userSize % pageSize == 0 ? userSize / pageSize : userSize / pageSize + 1;
			int start = (currentPage - 1) * pageSize;
			int end = start + pageSize;
			for (int i = start; i < end; i++) {
				if (i >= userSize) {
					break;
				} else {
					User user = allUsers.get(i);
					Set<UserRole> userRoles = new HashSet<UserRole>(userService.findInStatusUserRoleById(user.getId()));
					user.setUserRoles(userRoles);
					showUsers.add(user);
				}
			}
		}
		session.put("showUsers", showUsers);
		this.msg = (String) session.get("msg");
		Integer userAction = (Integer) session.get("userAction");
		if (userAction == null || userAction == 0) {
			session.put("msg", "");
		} else {
			session.remove("userAction");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 添加用户Action
	 * 
	 * @return
	 */
	public String add() {
		if (!checkUser()) {
			if (userService.regist(this.user)) {	// 添加用户信息
				msg = "添加成功！";
			} else {
				msg = "添加失败！";
			}
		}

		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("userAction", 1);		// userAction: 1 表示添加用户
		return SUCCESS;
	}
	
	public String update() {
		ActionContext.getContext().getSession().remove("updateUser");
		if (!checkUser()) {
			this.user = userService.findUserByName(user.getUsername());		// 通过用户名查找用户
			if (user != null) {
				user.setEnabled(this.enabled);		// 设置查找出来用户的激活性
				user.setDescription(this.description);	// 设置查找出来的用户的描述信息
				user.setPassword(this.repassword);		// 设置查找出来的用户的密码
				
				user = userService.changePassword(user);	// 修改用户信息
				if (user != null) {
					msg = "修改成功！";
				} else {
					msg = "修改失败！";
				}
			}
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("userAction", 2);		// userAction：2 表示修改用户信息
		return SUCCESS;
	}
	
	public String updateUser() {
		User updateUser = userService.findUserById(this.id);
		
		ActionContext.getContext().getSession().put("updateUser", updateUser);
		ActionContext.getContext().getSession().put("userAction", -1);		// userAction: -1 表示无效的用户信息
		return SUCCESS;
	}
	
	public String delete() {
		if (userService.deleteUserById(this.id)) {		// 当删除用户编号列表不为空，并且批量删除用户信息成功
			msg = "删除成功！";
		} else {
			msg = "删除失败！";
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("userAction", 3);		// userAction：3 表示删除用户信息
		return SUCCESS;
	}
	
	private boolean checkUser() {
		boolean result = true;
		if (user.getUsername() == null || "".equals(user.getUsername().trim())) {
			msg = "用户名不能为空！";
			result = false;
		} else if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
			msg = "密码不能为空！";
			result = false;
		} else if (this.repassword == null || "".equals(this.repassword)) {
			msg = "请确认密码！";
			result = false;
		} else if (this.repassword.trim().equals(user.getPassword().trim())) {
			msg = "两次输入密码不一致";
			result = false;
		} 
		return result;
	}
	
	public String authorizeUser() {
		User authorizeUse = userService.findUserById(id);
		List<UserRole> userRoles = userService.findUserRoleById(id);
		
		ActionContext.getContext().getSession().put("authorizeUser", authorizeUse);
		ActionContext.getContext().getSession().put("userRoles", userRoles);
		return SUCCESS; 	
	}
	
	public String authorizeUserAction() {
		UserRole userRole = userRoleService.findUserRoleById(id);
		if (userRole.getStatus() == 0) {
			userRole.setStatus(1);
		} else {
			userRole.setStatus(0);
		}
		if (userRoleService.update(userRole)) {
			msg = "授权成功！";
		} else {
			msg = "授权失败！";
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		try {
			ServletActionContext.getResponse().sendRedirect("./authorizeUser?id=" + userRole.getUser().getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
