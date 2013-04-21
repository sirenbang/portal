package com.portal.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.portal.model.PortalGroup;
import com.portal.service.PortalGroupService;

public class PortalGroupAction extends ActionSupport {

	private static final long serialVersionUID = -5227192124737484498L;
	
	private static final Logger log = LoggerFactory.getLogger(PortalGroupAction.class);
	private static final int pageSize = 10;

	// User 服务类
	private PortalGroupService portalGroupService;
	
	private PortalGroup portalGroup;
	private List<PortalGroup> showPortalGroups;
	
	private int hidden;				// 是否可见
	private String description;		// 描述
	private int id;					// 待操作的门户组编号
	private String msg;				// 返回信息
	private int currentPage = 1;	// 当前页码
	private int totalPage = 0;		// 总页数
	
	private String searchPortalGroupName;	// 查找时输入的门户组名
	
	public PortalGroup getPortalGroup() {
		return portalGroup;
	}

	public void setPortalGroup(PortalGroup portalGroup) {
		this.portalGroup = portalGroup;
	}

	public List<PortalGroup> getShowPortalGroups() {
		return showPortalGroups;
	}

	public void setShowPortalGroups(List<PortalGroup> showPortalGroups) {
		this.showPortalGroups = showPortalGroups;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
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

	public String getSearchPortalGroupName() {
		return searchPortalGroupName;
	}

	public void setSearchPortalGroupName(String searchPortalGroupName) {
		this.searchPortalGroupName = searchPortalGroupName;
	}

	public void setPortalGroupService(PortalGroupService portalGroupService) {
		this.portalGroupService = portalGroupService;
	}

	/**
	 * 角色管理Action
	 * 
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	public String allPortalGroup() {
		log.debug("---> Role Action allRole");
		List<PortalGroup> allPortalGroups = portalGroupService.search(this.searchPortalGroupName);
		Map session = ActionContext.getContext().getSession();
		this.showPortalGroups = (List<PortalGroup>) session.get("showPortalGroups");			// 从session中取出当前正在显示的角色信息
		if (showPortalGroups == null) {		// 当显示角色信息列表为空时，新建对象
			showPortalGroups = new ArrayList<PortalGroup>();
		} else {						// 当当前显示角色信息列表不为空时，清空角色信息列表，以便放入待显示角色信息，避免冲突
			showPortalGroups.clear();
		}
		
		if (allPortalGroups == null || allPortalGroups.size() == 0) {			// 当所有角色信息为空时，设置页脚导航处的值
			this.totalPage = 1;
			this.currentPage = 1;
		} else {
			int portalGroupSize = allPortalGroups.size();
			this.totalPage = portalGroupSize % pageSize == 0 ? portalGroupSize / pageSize : portalGroupSize / pageSize + 1;
			int start = (currentPage - 1) * pageSize;
			int end = start + pageSize;
			for (int i = start; i < end; i++) {
				if (i >= portalGroupSize) {
					break;
				} else {
					showPortalGroups.add(allPortalGroups.get(i));
				}
			}
		}
		session.put("showPortalGroups", showPortalGroups);
		this.msg = (String) session.get("msg");
		Integer roleAction = (Integer) session.get("portalGroupAction");
		if (roleAction == null || roleAction == 0) {
			session.put("msg", "");
		} else {
			session.remove("portalGroupAction");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 添加角色Action
	 * 
	 * @return
	 */
	public String add() {
		if (checkPortalGroup()) {
			if (portalGroupService.add(this.portalGroup)) {	// 添加门户组信息
				msg = "添加成功！";
			} else {
				msg = "添加失败！";
			}
		}

		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("portalGroupAction", 1);		// portalGroupAction: 1 表示添加门户组
		return SUCCESS;
	}
	
	public String update() {
		ActionContext.getContext().getSession().remove("updatePortalGroup");
		if (checkPortalGroup()) {
			this.portalGroup = portalGroupService.findPortalGroupByName(portalGroup.getName());		// 通过角色名查找门户组
			if (portalGroup != null) {
				portalGroup.setHidden(this.hidden);				// 设置查找出来门户组是否可见
				portalGroup.setDescription(this.description);	// 设置查找出来门户组的描述信息
				
				portalGroup = portalGroupService.update(portalGroup);	// 修改门户组信息
				if (portalGroup != null) {
					msg = "修改成功！";
				} else {
					msg = "修改失败！";
				}
			}
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("portalGroupAction", 2);		// portalGroupAction：2 表示修改门户组信息
		return SUCCESS;
	}
	
	public String updatePortalGroup() {
		PortalGroup updatePortalGroup  = portalGroupService.findPortalGroupById(this.id);
		
		ActionContext.getContext().getSession().put("updatePortalGroup", updatePortalGroup);
		ActionContext.getContext().getSession().put("portalGroupAction", -1);		// roleAction: -1 表示无效的门户组信息
		return SUCCESS;
	}
	
	public String delete() {
		if (portalGroupService.deletePortalGroupById(this.id)) {		// 当删除门户组成功
			msg = "删除成功！";
		} else {
			msg = "删除失败！";
		}
		
		ActionContext.getContext().getSession().put("msg", msg);
		ActionContext.getContext().getSession().put("portalGroupAction", 3);		// roleAction：3 表示删除门户组信息
		return SUCCESS;
	}
	
	private boolean checkPortalGroup() {
		boolean result = true;
		if (this.portalGroup.getName() == null || "".equals(this.portalGroup.getName())) {
			msg = "门户组名不能为空！";
			return false;
		}
		return result;
	}
}
