package com.portal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractPortletDescription entity. @author MyEclipse Persistence Tools
 */

public class AbstractPortletDescription implements Serializable {

	private static final long serialVersionUID = 5724462963911234395L;
	
	// Fields

	private Integer id;
	private String name;
	private Integer width;
	private Integer height;
	private String description;
	private Date createTime;
	private String image;
	private String imagePath;
	private Integer draggable;
	private Integer type;
	private Set<IframePortletDescription> iframePortletDescriptions = new HashSet<IframePortletDescription>(0);
	private Set<LinkPortletDescription> linkPortletDescriptions = new HashSet<LinkPortletDescription>(0);
	private Set<GridPortletDescription> gridPortletDescriptions = new HashSet<GridPortletDescription>(0);
	private Set<Portlet> portlets = new HashSet<Portlet>(0);
	private Set<DefaultPortlet> defaultPortlets = new HashSet<DefaultPortlet>(0);

	// Constructors

	/** default constructor */
	public AbstractPortletDescription() {
	}

	/** minimal constructor */
	public AbstractPortletDescription(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractPortletDescription(String name, Integer width,
			Integer height, String description, Date createTime, String image,
			String imagePath, Integer draggable, Set<IframePortletDescription> iframePortletDescriptions,
			Set<LinkPortletDescription> linkPortletDescriptions, Set<GridPortletDescription> gridPortletDescriptions,
			Set<Portlet> portlets, Set<DefaultPortlet> defaultPortlets, Integer type) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.description = description;
		this.createTime = createTime;
		this.image = image;
		this.imagePath = imagePath;
		this.draggable = draggable;
		this.iframePortletDescriptions = iframePortletDescriptions;
		this.linkPortletDescriptions = linkPortletDescriptions;
		this.gridPortletDescriptions = gridPortletDescriptions;
		this.portlets = portlets;
		this.defaultPortlets = defaultPortlets;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getDraggable() {
		return this.draggable;
	}

	public void setDraggable(Integer draggable) {
		this.draggable = draggable;
	}

	public Set<IframePortletDescription> getIframePortletDescriptions() {
		return this.iframePortletDescriptions;
	}

	public void setIframePortletDescriptions(Set<IframePortletDescription> iframePortletDescriptions) {
		this.iframePortletDescriptions = iframePortletDescriptions;
	}

	public Set<LinkPortletDescription> getLinkPortletDescriptions() {
		return this.linkPortletDescriptions;
	}

	public void setLinkPortletDescriptions(Set<LinkPortletDescription> linkPortletDescriptions) {
		this.linkPortletDescriptions = linkPortletDescriptions;
	}

	public Set<GridPortletDescription> getGridPortletDescriptions() {
		return this.gridPortletDescriptions;
	}

	public void setGridPortletDescriptions(Set<GridPortletDescription> gridPortletDescriptions) {
		this.gridPortletDescriptions = gridPortletDescriptions;
	}

	public Set<Portlet> getPortlets() {
		return this.portlets;
	}

	public void setPortlets(Set<Portlet> portlets) {
		this.portlets = portlets;
	}

	public Set<DefaultPortlet> getDefaultPortlets() {
		return this.defaultPortlets;
	}

	public void setDefaultPortlets(Set<DefaultPortlet> defaultPortlets) {
		this.defaultPortlets = defaultPortlets;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}