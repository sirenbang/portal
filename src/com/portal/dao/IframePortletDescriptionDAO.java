package com.portal.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.IframePortletDescription;
import com.portal.util.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * IframePortletDescription entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.portal.model.IframePortletDescription
 * @author MyEclipse Persistence Tools
 */

public class IframePortletDescriptionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(IframePortletDescriptionDAO.class);
	// property constants
	public static final String URL = "url";

	protected void initDao() {
		// do nothing
	}

	public void save(IframePortletDescription transientInstance) {
		log.debug("saving IframePortletDescription instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IframePortletDescription persistentInstance) {
		log.debug("deleting IframePortletDescription instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IframePortletDescription findById(java.lang.Integer id) {
		log.debug("getting IframePortletDescription instance with id: " + id);
		try {
			IframePortletDescription instance = (IframePortletDescription) getHibernateTemplate()
					.get("com.portal.model.IframePortletDescription", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * 	通过抽象模块描述编号查找 Iframe 模块描述
	 * 
	 * @param abstractId
	 * @return
	 */
	public IframePortletDescription findIframeByAbstractId(int abstractId) {
		log.debug("getting IframePortletDescription instance with abstractId: " + abstractId);
		try {
			String queryStr = "frome IframePortletDescription ipd where ipd.abstractPortletDescription.id = " + abstractId;
			
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryStr);
			List<IframePortletDescription> ipds = query.list();
			HibernateSessionFactory.closeSession();
			
			if (ipds == null || ipds.size() == 0) {
				log.debug("getting IframePortletDescription instance null with abstractId: " + abstractId);
				return null;
			} else {
				log.debug("getting IframePortletDescription instance success with abstractId: " + abstractId);
				return ipds.get(0);
			}
		} catch (RuntimeException re) {
			log.debug("getting IframePortletDescription instance failed with abstractId: " + abstractId, re);
			throw re;
		}
	}

	public List findByExample(IframePortletDescription instance) {
		log.debug("finding IframePortletDescription instance by example");
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
		log.debug("finding IframePortletDescription instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IframePortletDescription as model where model."
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

	public List findAll() {
		log.debug("finding all IframePortletDescription instances");
		try {
			String queryString = "from IframePortletDescription";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IframePortletDescription merge(
			IframePortletDescription detachedInstance) {
		log.debug("merging IframePortletDescription instance");
		try {
			IframePortletDescription result = (IframePortletDescription) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IframePortletDescription instance) {
		log.debug("attaching dirty IframePortletDescription instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IframePortletDescription instance) {
		log.debug("attaching clean IframePortletDescription instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IframePortletDescriptionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IframePortletDescriptionDAO) ctx
				.getBean("IframePortletDescriptionDAO");
	}
}