package com.portal.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.GridPortletDescription;

public class GridPortletDescriptionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(GridPortletDescriptionDAO.class);
	
	// property constants
	public static final String DATA_REPOSITORY = "dataRepository";
	public static final String PAGE_IS = "pageIs";
	public static final String PAGE_SIZE = "pageSize";

	protected void initDao() {
		// do nothing
	}

	public void save(GridPortletDescription transientInstance) {
		log.debug("saving GridPortletDescription instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GridPortletDescription persistentInstance) {
		log.debug("deleting GridPortletDescription instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GridPortletDescription findById(java.lang.Integer id) {
		log.debug("getting GridPortletDescription instance with id: " + id);
		try {
			GridPortletDescription instance = (GridPortletDescription) getHibernateTemplate()
					.get("com.portal.model.GridPortletDescription", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(GridPortletDescription instance) {
		log.debug("finding GridPortletDescription instance by example");
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
		log.debug("finding GridPortletDescription instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from GridPortletDescription as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDataRepository(Object dataRepository) {
		return findByProperty(DATA_REPOSITORY, dataRepository);
	}

	public List findByPageIs(Object pageIs) {
		return findByProperty(PAGE_IS, pageIs);
	}

	public List findByPageSize(Object pageSize) {
		return findByProperty(PAGE_SIZE, pageSize);
	}

	public List findAll() {
		log.debug("finding all GridPortletDescription instances");
		try {
			String queryString = "from GridPortletDescription";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GridPortletDescription merge(GridPortletDescription detachedInstance) {
		log.debug("merging GridPortletDescription instance");
		try {
			GridPortletDescription result = (GridPortletDescription) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GridPortletDescription instance) {
		log.debug("attaching dirty GridPortletDescription instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GridPortletDescription instance) {
		log.debug("attaching clean GridPortletDescription instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GridPortletDescriptionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (GridPortletDescriptionDAO) ctx
				.getBean("GridPortletDescriptionDAO");
	}
}