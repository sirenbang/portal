package com.portal.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.Portlet;

/**
 * A data access object (DAO) providing persistence and search support for
 * Portlet entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.portal.model.Portlet
 * @author MyEclipse Persistence Tools
 */

public class PortletDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(PortletDAO.class);
	// property constants
	public static final String COLUMN = "column";
	public static final String COLUMN_INDEX = "columnIndex";
	public static final String HIDDEN = "hidden";

	protected void initDao() {
		// do nothing
	}

	public void save(Portlet transientInstance) {
		log.debug("saving Portlet instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Portlet persistentInstance) {
		log.debug("deleting Portlet instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Portlet findById(java.lang.Integer id) {
		log.debug("getting Portlet instance with id: " + id);
		try {
			Portlet instance = (Portlet) getHibernateTemplate().get(
					"com.portal.model.Portlet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Portlet instance) {
		log.debug("finding Portlet instance by example");
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
		log.debug("finding Portlet instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Portlet as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByColumn(Object column) {
		return findByProperty(COLUMN, column);
	}

	public List findByColumnIndex(Object columnIndex) {
		return findByProperty(COLUMN_INDEX, columnIndex);
	}

	public List findByHidden(Object hidden) {
		return findByProperty(HIDDEN, hidden);
	}

	public List findAll() {
		log.debug("finding all Portlet instances");
		try {
			String queryString = "from Portlet";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Portlet merge(Portlet detachedInstance) {
		log.debug("merging Portlet instance");
		try {
			Portlet result = (Portlet) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Portlet instance) {
		log.debug("attaching dirty Portlet instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Portlet instance) {
		log.debug("attaching clean Portlet instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PortletDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PortletDAO) ctx.getBean("PortletDAO");
	}
}