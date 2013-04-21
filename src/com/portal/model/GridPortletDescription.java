package com.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * GridPortletDescription entity. @author MyEclipse Persistence Tools
 */

public class GridPortletDescription implements Serializable {

	private static final long serialVersionUID = 2247615487593494L;
	
	// Fields

	private Integer id;
	private AbstractPortletDescription abstractPortletDescription;
	private String dataRepository;
	private Integer pageIs;
	private Integer pageSize;
	private Set<GridProperty> gridProperties = new HashSet<GridProperty>(0);
	private Set<GridSearch> gridSearchs = new HashSet<GridSearch>(0);

	// Constructors

	/** default constructor */
	public GridPortletDescription() {
	}

	/** minimal constructor */
	public GridPortletDescription(
			AbstractPortletDescription abstractPortletDescription) {
		this.abstractPortletDescription = abstractPortletDescription;
	}

	/** full constructor */
	public GridPortletDescription(
			AbstractPortletDescription abstractPortletDescription,
			String dataRepository, Integer pageIs, Integer pageSize,
			Set<GridProperty> gridProperties, Set<GridSearch> gridSearchs) {
		this.abstractPortletDescription = abstractPortletDescription;
		this.dataRepository = dataRepository;
		this.pageIs = pageIs;
		this.pageSize = pageSize;
		this.gridProperties = gridProperties;
		this.gridSearchs = gridSearchs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AbstractPortletDescription getAbstractPortletDescription() {
		return this.abstractPortletDescription;
	}

	public void setAbstractPortletDescription(
			AbstractPortletDescription abstractPortletDescription) {
		this.abstractPortletDescription = abstractPortletDescription;
	}

	public String getDataRepository() {
		return this.dataRepository;
	}

	public void setDataRepository(String dataRepository) {
		this.dataRepository = dataRepository;
	}

	public Integer getPageIs() {
		return this.pageIs;
	}

	public void setPageIs(Integer pageIs) {
		this.pageIs = pageIs;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Set<GridProperty> getGridProperties() {
		return this.gridProperties;
	}

	public void setGridProperties(Set<GridProperty> gridProperties) {
		this.gridProperties = gridProperties;
	}

	public Set<GridSearch> getGridSearchs() {
		return this.gridSearchs;
	}

	public void setGridSearchs(Set<GridSearch> gridSearchs) {
		this.gridSearchs = gridSearchs;
	}

}