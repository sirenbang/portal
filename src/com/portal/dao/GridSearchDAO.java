package com.portal.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.GridSearch;

/**
 * A data access object (DAO) providing persistence and search support for
 * GridSearch entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.portal.model.GridSearch
 * @author MyEclipse Persistence Tools
 */

public class GridSearchDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(GridSearchDAO.class);
	// property constants
	public static final String SEARCH_NAME = "searchName";
	public static final String SEARCH_TYPE = "searchType";
	public static final String SEARCH_DESC = "searchDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(GridSearch transientInstance) {
		log.debug("saving GridSearch instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GridSearch persistentInstance) {
		log.debug("deleting GridSearch instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GridSearch findById(java.lang.Integer id) {
		log.debug("getting GridSearch instance with id: " + id);
		try {
			GridSearch instance = (GridSearch) getHibernateTemplate().get(
					"com.portal.model.GridSearch", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(GridSearch instance) {
		log.debug("finding GridSearch instance by example");
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
		log.debug("finding GridSearch instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from GridSearch as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySearchName(Object searchName) {
		return findByProperty(SEARCH_NAME, searchName);
	}

	public List findBySearchType(Object searchType) {
		return findByProperty(SEARCH_TYPE, searchType);
	}

	public List findBySearchDesc(Object searchDesc) {
		return findByProperty(SEARCH_DESC, searchDesc);
	}

	public List findAll() {
		log.debug("finding all GridSearch instances");
		try {
			String queryString = "from GridSearch";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GridSearch merge(GridSearch detachedInstance) {
		log.debug("merging GridSearch instance");
		try {
			GridSearch result = (GridSearch) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GridSearch instance) {
		log.debug("attaching dirty GridSearch instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GridSearch instance) {
		log.debug("attaching clean GridSearch instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GridSearchDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GridSearchDAO) ctx.getBean("GridSearchDAO");
	}
}