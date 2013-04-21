package com.portal.model;

import java.io.Serializable;

/**
 * GridProperty entity. @author MyEclipse Persistence Tools
 */

public class GridProperty implements Serializable {

	private static final long serialVersionUID = 6741297954883878723L;
	
	// Fields

	private Integer id;
	private GridPortletDescription gridPortletDescription;
	private String name;
	private String desc;
	private String type;
	private Integer orderBy;
	private Integer index;

	// Constructors

	/** default constructor */
	public GridProperty() {
	}

	/** minimal constructor */
	public GridProperty(GridPortletDescription gridPortletDescription,
			String name, String type, Integer index) {
		this.gridPortletDescription = gridPortletDescription;
		this.name = name;
		this.type = type;
		this.index = index;
	}

	/** full constructor */
	public GridProperty(GridPortletDescription gridPortletDescription,
			String name, String desc, String type, Integer orderBy,
			Integer index) {
		this.gridPortletDescription = gridPortletDescription;
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.orderBy = orderBy;
		this.index = index;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GridPortletDescription getGridPortletDescription() {
		return this.gridPortletDescription;
	}

	public void setGridPortletDescription(
			GridPortletDescription gridPortletDescription) {
		this.gridPortletDescription = gridPortletDescription;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getIndex() {
		return this.index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}