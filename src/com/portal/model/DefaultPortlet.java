package com.portal.model;

import java.io.Serializable;

/**
 * DefaultPortlet entity. @author MyEclipse Persistence Tools
 */

public class DefaultPortlet implements Serializable {

	private static final long serialVersionUID = 5549447892223708446L;
	// Fields

	private Integer id;
	private PortalLayout portalLayout;
	private PortalGroup portalGroup;
	private AbstractPortletDescription abstractPortletDescription;
	private Integer column;
	private Integer columnIndex;
	private Integer hidden;

	// Constructors

	/** default constructor */
	public DefaultPortlet() {
	}

	/** full constructor */
	public DefaultPortlet(PortalLayout portalLayout, PortalGroup portalGroup,
			AbstractPortletDescription abstractPortletDescription,
			Integer column, Integer columnIndex, Integer hidden) {
		this.portalLayout = portalLayout;
		this.portalGroup = portalGroup;
		this.abstractPortletDescription = abstractPortletDescription;
		this.column = column;
		this.columnIndex = columnIndex;
		this.hidden = hidden;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PortalLayout getPortalLayout() {
		return this.portalLayout;
	}

	public void setPortalLayout(PortalLayout portalLayout) {
		this.portalLayout = portalLayout;
	}

	public PortalGroup getPortalGroup() {
		return this.portalGroup;
	}

	public void setPortalGroup(PortalGroup portalGroup) {
		this.portalGroup = portalGroup;
	}

	public AbstractPortletDescription getAbstractPortletDescription() {
		return this.abstractPortletDescription;
	}

	public void setAbstractPortletDescription(
			AbstractPortletDescription abstractPortletDescription) {
		this.abstractPortletDescription = abstractPortletDescription;
	}

	public Integer getColumn() {
		return this.column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public Integer getColumnIndex() {
		return this.columnIndex;
	}

	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
	}

	public Integer getHidden() {
		return this.hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

}