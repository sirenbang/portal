package com.portal.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.PortalLayout;

/**
 * A data access object (DAO) providing persistence and search support for
 * PortalLayout entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.portal.model.PortalLayout
 * @author MyEclipse Persistence Tools
 */

public class PortalLayoutDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PortalLayoutDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(PortalLayout transientInstance) {
		log.debug("saving PortalLayout instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PortalLayout persistentInstance) {
		log.debug("deleting PortalLayout instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PortalLayout findById(java.lang.Integer id) {
		log.debug("getting PortalLayout instance with id: " + id);
		try {
			PortalLayout instance = (PortalLayout) getHibernateTemplate().get(
					"com.portal.model.PortalLayout", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PortalLayout instance) {
		log.debug("finding PortalLayout instance by example");
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
		log.debug("finding PortalLayout instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PortalLayout as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all PortalLayout instances");
		try {
			String queryString = "from PortalLayout";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PortalLayout merge(PortalLayout detachedInstance) {
		log.debug("merging PortalLayout instance");
		try {
			PortalLayout result = (PortalLayout) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PortalLayout instance) {
		log.debug("attaching dirty PortalLayout instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PortalLayout instance) {
		log.debug("attaching clean PortalLayout instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PortalLayoutDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PortalLayoutDAO) ctx.getBean("PortalLayoutDAO");
	}
}