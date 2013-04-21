package com.portal.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.Portal;

/**
 * A data access object (DAO) providing persistence and search support for
 * Portal entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.portal.model.Portal
 * @author MyEclipse Persistence Tools
 */

public class PortalDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(PortalDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Portal transientInstance) {
		log.debug("saving Portal instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Portal persistentInstance) {
		log.debug("deleting Portal instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Portal findById(java.lang.Integer id) {
		log.debug("getting Portal instance with id: " + id);
		try {
			Portal instance = (Portal) getHibernateTemplate().get(
					"com.portal.model.Portal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Portal instance) {
		log.debug("finding Portal instance by example");
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
		log.debug("finding Portal instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Portal as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Portal instances");
		try {
			String queryString = "from Portal";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Portal merge(Portal detachedInstance) {
		log.debug("merging Portal instance");
		try {
			Portal result = (Portal) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Portal instance) {
		log.debug("attaching dirty Portal instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Portal instance) {
		log.debug("attaching clean Portal instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PortalDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PortalDAO) ctx.getBean("PortalDAO");
	}
}