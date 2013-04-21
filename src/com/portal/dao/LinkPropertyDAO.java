package com.portal.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.LinkProperty;
import com.portal.util.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * LinkProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.portal.model.LinkProperty
 * @author MyEclipse Persistence Tools
 */

public class LinkPropertyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LinkPropertyDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String DESCRIPTION = "description";
	public static final String ICON = "icon";

	protected void initDao() {
		// do nothing
	}

	public void save(LinkProperty transientInstance) {
		log.debug("saving LinkProperty instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LinkProperty persistentInstance) {
		log.debug("deleting LinkProperty instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LinkProperty findById(java.lang.Integer id) {
		log.debug("getting LinkProperty instance with id: " + id);
		try {
			LinkProperty instance = (LinkProperty) getHibernateTemplate().get(
					"com.portal.model.LinkProperty", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * 	根据模块编号查找模块属性列表
	 * 
	 * @param linkId
	 * @return
	 */
	public List<LinkProperty> findLinkPropertiesByPortletId(int linkId) {
		log.debug("---> finding LinkProperties with portletId:" + linkId);
		try {
			String queryStr = "from LinkProperty lp where lp.linkPortletDescription.id = " + linkId;
			
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryStr);
			List<LinkProperty> lps = query.list();
			HibernateSessionFactory.closeSession();
			
			log.debug("---> finding LinkProperties success with portletId:" + linkId);
			return lps;
		} catch (RuntimeException re) {
			log.debug("---> finding LinkProperties failed with portletId:" + linkId);
			throw re;
		}
	}

	public List findByExample(LinkProperty instance) {
		log.debug("finding LinkProperty instance by example");
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
		log.debug("finding LinkProperty instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LinkProperty as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByIcon(Object icon) {
		return findByProperty(ICON, icon);
	}

	public List findAll() {
		log.debug("finding all LinkProperty instances");
		try {
			String queryString = "from LinkProperty";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LinkProperty merge(LinkProperty detachedInstance) {
		log.debug("merging LinkProperty instance");
		try {
			LinkProperty result = (LinkProperty) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LinkProperty instance) {
		log.debug("attaching dirty LinkProperty instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LinkProperty instance) {
		log.debug("attaching clean LinkProperty instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LinkPropertyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LinkPropertyDAO) ctx.getBean("LinkPropertyDAO");
	}
}