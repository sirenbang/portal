package com.portal.model;

import java.io.Serializable;

/**
 * GridSearch entity. @author MyEclipse Persistence Tools
 */

public class GridSearch implements Serializable {

	private static final long serialVersionUID = 7788416147802659283L;
	
	// Fields

	private Integer id;
	private GridPortletDescription gridPortletDescription;
	private String searchName;
	private String searchType;
	private String searchDesc;

	// Constructors

	/** default constructor */
	public GridSearch() {
	}

	/** minimal constructor */
	public GridSearch(GridPortletDescription gridPortletDescription,
			String searchName, String searchType) {
		this.gridPortletDescription = gridPortletDescription;
		this.searchName = searchName;
		this.searchType = searchType;
	}

	/** full constructor */
	public GridSearch(GridPortletDescription gridPortletDescription,
			String searchName, String searchType, String searchDesc) {
		this.gridPortletDescription = gridPortletDescription;
		this.searchName = searchName;
		this.searchType = searchType;
		this.searchDesc = searchDesc;
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

	public String getSearchName() {
		return this.searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchType() {
		return this.searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchDesc() {
		return this.searchDesc;
	}

	public void setSearchDesc(String searchDesc) {
		this.searchDesc = searchDesc;
	}

}