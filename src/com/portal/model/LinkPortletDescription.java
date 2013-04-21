package com.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * LinkPortletDescription entity. @author MyEclipse Persistence Tools
 */

public class LinkPortletDescription implements Serializable {

	private static final long serialVersionUID = 3303032058341773219L;

	// Fields

	private Integer id;
	private AbstractPortletDescription abstractPortletDescription;
	private String lineClass;
	private Set<LinkProperty> linkProperties = new HashSet<LinkProperty>(0);

	// Constructors

	/** default constructor */
	public LinkPortletDescription() {
	}

	/** minimal constructor */
	public LinkPortletDescription(
			AbstractPortletDescription abstractPortletDescription,
			String lineClass) {
		this.abstractPortletDescription = abstractPortletDescription;
		this.lineClass = lineClass;
	}

	/** full constructor */
	public LinkPortletDescription(
			AbstractPortletDescription abstractPortletDescription,
			String lineClass, Set<LinkProperty> linkProperties) {
		this.abstractPortletDescription = abstractPortletDescription;
		this.lineClass = lineClass;
		this.linkProperties = linkProperties;
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

	public String getLineClass() {
		return this.lineClass;
	}

	public void setLineClass(String lineClass) {
		this.lineClass = lineClass;
	}

	public Set<LinkProperty> getLinkProperties() {
		return this.linkProperties;
	}

	public void setLinkProperties(Set<LinkProperty> linkProperties) {
		this.linkProperties = linkProperties;
	}

}