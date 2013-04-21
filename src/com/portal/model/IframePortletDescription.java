package com.portal.model;

import java.io.Serializable;

/**
 * IframePortletDescription entity. @author MyEclipse Persistence Tools
 */

public class IframePortletDescription implements Serializable {

	private static final long serialVersionUID = 2649440525214160422L;

	// Fields

	private Integer id;
	private AbstractPortletDescription abstractPortletDescription;
	private String url;

	// Constructors

	/** default constructor */
	public IframePortletDescription() {
	}

	/** full constructor */
	public IframePortletDescription(
			AbstractPortletDescription abstractPortletDescription, String url) {
		this.abstractPortletDescription = abstractPortletDescription;
		this.url = url;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}