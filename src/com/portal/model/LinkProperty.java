package com.portal.model;

import java.io.Serializable;

/**
 * LinkProperty entity. @author MyEclipse Persistence Tools
 */

public class LinkProperty implements Serializable {

	private static final long serialVersionUID = -7549717240528506560L;
	
	// Fields

	private Integer id;
	private LinkPortletDescription linkPortletDescription;
	private String url;
	private String description;
	private String icon;

	// Constructors

	/** default constructor */
	public LinkProperty() {
	}

	/** minimal constructor */
	public LinkProperty(LinkPortletDescription linkPortletDescription,
			String url) {
		this.linkPortletDescription = linkPortletDescription;
		this.url = url;
	}

	/** full constructor */
	public LinkProperty(LinkPortletDescription linkPortletDescription,
			String url, String description, String icon) {
		this.linkPortletDescription = linkPortletDescription;
		this.url = url;
		this.description = description;
		this.icon = icon;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LinkPortletDescription getLinkPortletDescription() {
		return this.linkPortletDescription;
	}

	public void setLinkPortletDescription(
			LinkPortletDescription linkPortletDescription) {
		this.linkPortletDescription = linkPortletDescription;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}