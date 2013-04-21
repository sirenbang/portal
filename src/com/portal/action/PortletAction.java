package com.portal.action;

import java.util.HashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.portal.model.AbstractPortletDescription;
import com.portal.model.GridPortletDescription;
import com.portal.model.GridProperty;
import com.portal.model.GridSearch;
import com.portal.model.IframePortletDescription;
import com.portal.model.LinkPortletDescription;
import com.portal.model.LinkProperty;
import com.portal.service.PortletService;

public class PortletAction  extends ActionSupport  {

	private static final long serialVersionUID = 4950142152154053705L;
	
	// 服务类
	private PortletService portletService;
	
	// 前后台数据
	private String msg;											// 返回前台消息
	private Integer actionType;									// 动作类型
	private List<AbstractPortletDescription> allPortlets;		// 所有模块集合
	private String searchPortletName;							// 搜索时的模块名称
	private int searchPortletType;								// 搜索时的模块类型
	private int id;												// 更新或删除的模块编号
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}
	
	public List<AbstractPortletDescription> getAllPortlets() {
		return allPortlets;
	}

	public void setAllPortlets(List<AbstractPortletDescription> allPortlets) {
		this.allPortlets = allPortlets;
	}

	public void setPortletService(PortletService portletService) {
		this.portletService = portletService;
	}

	public String getSearchPortletName() {
		return searchPortletName;
	}

	public void setSearchPortletName(String searchPortletName) {
		this.searchPortletName = searchPortletName;
	}

	public int getSearchPortletType() {
		return searchPortletType;
	}

	public void setSearchPortletType(int searchPortletType) {
		this.searchPortletType = searchPortletType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 显示所有模块
	 * 
	 * @return
	 */
	public String allPortlets() {
		if (actionType == null) {
			ActionContext.getContext().getSession().remove("msg");
		}
		
		this.allPortlets = portletService.search(this.searchPortletName, this.searchPortletType);
		return SUCCESS;
	}
	
	/**
	 * 为修改页面准备数据的Action 
	 * 
	 * @return
	 */
	public String updatePortlet() {
		AbstractPortletDescription apd = portletService.findPortletDescriptionById(this.id); 
		if (apd == null) {
			msg = "无相应模块";
			ActionContext.getContext().getSession().put("msg", msg);
			ServletActionContext.getRequest().setAttribute("actionType", 4);
			return INPUT;
		}
		
		int type = apd.getType();			// 获取模块类型
		
		if (type == 1) {			// 如果是 Iframe 类型的模块
			IframePortletDescription iframePortletDescription = portletService.findIframeByAbstractId(apd.getId());
			if (iframePortletDescription != null) {
				iframePortletDescription.setAbstractPortletDescription(apd);
				ActionContext.getContext().getSession().put("updatePortlet", iframePortletDescription);
			} else {
				msg = "无相应模块";
				ActionContext.getContext().getSession().put("msg", msg);
				ServletActionContext.getRequest().setAttribute("actionType", 4);
				return INPUT;
			}
		} else if (type == 2) {		// 如果是 Link 类型的模块
			LinkPortletDescription linkPortletDescription = portletService.findLinkByAbstractId(apd.getId());
			if (linkPortletDescription != null) {
				linkPortletDescription.setAbstractPortletDescription(apd);
				List<LinkProperty> linkProperties = portletService.findLinkPropertiesByPortletId(linkPortletDescription.getId());
				linkPortletDescription.setLinkProperties(new HashSet<LinkProperty>(linkProperties));
				ActionContext.getContext().getSession().put("updatePortlet", linkPortletDescription);
			} else {
				msg = "无相应模块";
				ActionContext.getContext().getSession().put("msg", msg);
				ServletActionContext.getRequest().setAttribute("actionType", 4);
				return INPUT;
			}
		} else if (type == 3) {		// 如果是 Grid 类型的模块
			GridPortletDescription gridPortletDescription = portletService.findGridByAbstractId(apd.getId());
			if (gridPortletDescription != null) {
				int gridId = gridPortletDescription.getId();
				List<GridProperty> gridProperties = portletService.findGridPropertiesByPortletId(gridId);
				List<GridSearch> gridSearches = portletService.findGridSearchesByPortletId(gridId);
				gridPortletDescription.setAbstractPortletDescription(apd);
				gridPortletDescription.setGridProperties(new HashSet<GridProperty>(gridProperties));
				gridPortletDescription.setGridSearchs(new HashSet<GridSearch>(gridSearches));
				ActionContext.getContext().getSession().put("updatePortlet", gridPortletDescription);
			} else {
				msg = "无相应模块";
				ActionContext.getContext().getSession().put("msg", msg);
				ServletActionContext.getRequest().setAttribute("actionType", 4);
				return INPUT;
			}
		} else {
			msg = "无相应类型模块";
			ActionContext.getContext().getSession().put("msg", msg);
			ServletActionContext.getRequest().setAttribute("actionType", 0);
			return INPUT;
		}
		
		return SUCCESS;
	}
}
