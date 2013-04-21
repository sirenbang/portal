package com.portal.model;

import java.io.Serializable;

/**
 * Portlet entity. @author MyEclipse Persistence Tools
 */

public class Portlet implements Serializable {

	private static final long serialVersionUID = -998721766736477162L;
	
	// Fields

	private Integer id;
	private Portal portal;
	private AbstractPortletDescription abstractPortletDescription;
	private Integer column;
	private Integer columnIndex;
	private Integer hidden;

	// Constructors

	/** default constructor */
	public Portlet() {
	}

	/** full constructor */
	public Portlet(Portal portal,
			AbstractPortletDescription abstractPortletDescription,
			Integer column, Integer columnIndex, Integer hidden) {
		this.portal = portal;
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

	public Portal getPortal() {
		return this.portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
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