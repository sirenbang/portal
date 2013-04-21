package com.portal.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.portal.model.Role;
import com.portal.model.RolePortalGroup;
import com.portal.service.RolePortalGroupService;
import com.portal.service.RoleService;

public class RoleAction extends ActionSupport {

	private static final long serialVersionUID = -5227192124737484498L;
	
	private static final Logger log = LoggerFactory.getLogger(RoleAction.class);
	private static final int pageSize = 10;

	// User 服务类
	private RoleService roleService;
	private RolePortalGroupService rolePortalGroupService;
	
	private Role role;
	private List<Role> showRoles;
	
	private int enabled;			// 是否激活
	private String description;		// 描述
	private int id;					// 待操作的角色编号
	private String ids;				// 待操作的角色编号
	private String msg;				// 返回信息
	private int currentPage = 1;	// 当前页码
	private int totalPage = 0;		// 总页数
	private String searchRolename;	// 查找时输入的用户名
	private Integer searchEnabled;	// 查找时输入的用户状态信息
	
	public Integer getSearchEnabled() {
		return searchEnabled;
	}

	public void setSearchEnabled(Integer searchEnabled) {
		this.searchEnabled = searchEnabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getShowRoles() {
		return showRoles;
	}

	public void setShowRoles(List<Role> showRoles) {
		this.showRoles = showRoles;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public String getSearchRolename() {
		return searchRolename;
	}

	public void setSearchRolename(String searchRolename) {
		this.searchRolename = searchRolename;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	public void setRolePortalGroupService(
			RolePortalGroupService rolePortalGroupService) {
		this.rolePortalGroupService = rolePortalGroupService;
	}

	/**
	 * 角色管理Action
	 * 
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	public String allRole() {
		log.debug("---> Role Action allRole");
		List<Role> allRoles = roleService.search(this.searchRolename, this.searchEnabled);
		Map session = ActionContext.getContext().getSession();
		this.showRoles = (List<Role>) session.get("showRoles");			// 从session中取出当前正在显示的角色信息
		if (showRoles == null) {		// 当显示角色信息列表为空时，新建对象
			showRoles = new ArrayList<Role>();
		} else {						// 当当前显示角色信息列表不为空时，清空角色信息列表，以便放入待显示角色信息，避免冲突
			showRoles.clear();
		}
		
		if (allRoles == null || allRoles.size() == 0) {			// 当所有角色信息为空时，设置页脚导航处的值
			this.totalPage = 1;
			this.currentPage = 1;
		} else {
			int userSize = allRoles.size();
			this.totalPage = userSize % pageSize == 0 ? userSize / pageSize : userSize / pageSize + 1;
			int start = (currentPage - 1) * pageSize;
			int end = start + pageSize;
			for (int i = start; i < end; i++) {
				if (i >= userSize) {
					break;
				} else {
					showRoles.add(allRoles.get(i));
				}
			}
		}
		session.put("showRoles", showRoles);
		this.msg = (String) session.get("msg");
		Integer roleAction = (Integer) session.get("roleAction");
		if (roleAction == null || roleAction == 0) {
			session.put("msg", "");
		} else {
			session.remove("roleAction");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 添加角色Action
	 * 
	 * @return
	 */
	public String add() {
		if (checkRole()) {
			if (roleService.add(this.role)) {	// 添加角色信息
				msg = "添加成功！";
			} else {
				msg = "添加失败！";
			}
		}

		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("roleAction", 1);		// roleAction: 1 表示添加角色
		return SUCCESS;
	}
	
	public String update() {
		ActionContext.getContext().getSession().remove("updateRole");
		if (checkRole()) {
			this.role = roleService.findRoleByName(role.getName());		// 通过角色名查找角色
			if (role != null) {
				role.setEnable(this.enabled);		// 设置查找出来橘色的激活性
				role.setDescription(this.description);	// 设置查找出来的橘色的描述信息
				
				role = roleService.update(role);	// 修改角色信息
				if (role != null) {
					msg = "修改成功！";
				} else {
					msg = "修改失败！";
				}
			}
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("roleAction", 2);		// roleAction：2 表示修改角色信息
		return SUCCESS;
	}
	
	public String updateRole() {
		Role updateRole = roleService.findRoleById(this.id);
		
		ActionContext.getContext().getSession().put("updateRole", updateRole);
		ActionContext.getContext().getSession().put("roleAction", -1);		// roleAction: -1 表示无效的角色信息
		return SUCCESS;
	}
	
	public String delete() {
		if (roleService.deleteRoleById(this.id)) {		// 当删除角色成功
			msg = "删除成功！";
		} else {
			msg = "删除失败！";
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("roleAction", 3);		// roleAction：3 表示删除角色信息
		return SUCCESS;
	}
	
	private boolean checkRole() {
		boolean result = true;
		if (this.role.getName() == null || "".equals(this.role.getName())) {
			msg = "角色名不能为空！";
			return false;
		}
		return result;
	}
	
	public String authorizeRole() {
		Role authorizeRole = roleService.findRoleById(id);
		List<RolePortalGroup> rpgs = roleService.findRolePortalGroupById(id);
		
		ActionContext.getContext().getSession().put("authorizeRole", authorizeRole);
		ActionContext.getContext().getSession().put("rolePortalGroups", rpgs);
		return SUCCESS; 
	}
	
	public String authorizeRoleAction() {
		RolePortalGroup rolePortalGroup = rolePortalGroupService.findRolePortalGroupById(id);
		if (rolePortalGroup.getPermission() == 0) {
			rolePortalGroup.setPermission(1);
		} else {
			rolePortalGroup.setPermission(0);
		}
		if (rolePortalGroupService.update(rolePortalGroup)) {
			msg = "授权成功！";
		} else {
			msg = "授权失败！";
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		try {
			ServletActionContext.getResponse().sendRedirect("./authorizeRole?id=" + rolePortalGroup.getRole().getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
