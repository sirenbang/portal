package com.portal.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.LinkPortletDescription;

/**
 * A data access object (DAO) providing persistence and search support for
 * LinkPortletDescription entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.portal.model.LinkPortletDescription
 * @author MyEclipse Persistence Tools
 */

public class LinkPortletDescriptionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LinkPortletDescriptionDAO.class);
	// property constants
	public static final String LINE_CLASS = "lineClass";

	protected void initDao() {
		// do nothing
	}

	public void save(LinkPortletDescription transientInstance) {
		log.debug("saving LinkPortletDescription instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LinkPortletDescription persistentInstance) {
		log.debug("deleting LinkPortletDescription instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LinkPortletDescription findById(java.lang.Integer id) {
		log.debug("getting LinkPortletDescription instance with id: " + id);
		try {
			LinkPortletDescription instance = (LinkPortletDescription) getHibernateTemplate()
					.get("com.portal.model.LinkPortletDescription", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LinkPortletDescription instance) {
		log.debug("finding LinkPortletDescription instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding LinkPortletDescription instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LinkPortletDescription as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLineClass(Object lineClass) {
		return findByProperty(LINE_CLASS, lineClass);
	}

	public List findAll() {
		log.debug("finding all LinkPortletDescription instances");
		try {
			String queryString = "from LinkPortletDescription";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LinkPortletDescription merge(LinkPortletDescription detachedInstance) {
		log.debug("merging LinkPortletDescription instance");
		try {
			LinkPortletDescription result = (LinkPortletDescription) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LinkPortletDescription instance) {
		log.debug("attaching dirty LinkPortletDescription instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LinkPortletDescription instance) {
		log.debug("attaching clean LinkPortletDescription instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LinkPortletDescriptionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LinkPortletDescriptionDAO) ctx
				.getBean("LinkPortletDescriptionDAO");
	}
}