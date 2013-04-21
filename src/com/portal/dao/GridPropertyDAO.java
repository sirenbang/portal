package com.portal.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.GridProperty;
import com.portal.model.LinkProperty;
import com.portal.util.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * GridProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.portal.model.GridProperty
 * @author MyEclipse Persistence Tools
 */

public class GridPropertyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(GridPropertyDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESC = "desc";
	public static final String TYPE = "type";
	public static final String ORDER_BY = "orderBy";
	public static final String INDEX = "index";

	protected void initDao() {
		// do nothing
	}

	public void save(GridProperty transientInstance) {
		log.debug("saving GridProperty instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GridProperty persistentInstance) {
		log.debug("deleting GridProperty instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GridProperty findById(java.lang.Integer id) {
		log.debug("getting GridProperty instance with id: " + id);
		try {
			GridProperty instance = (GridProperty) getHibernateTemplate().get(
					"com.portal.model.GridProperty", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * 	根据模块编号查找模块属性列表
	 * 
	 * @param gridId
	 * @return
	 */
	public List<GridProperty> findGridPropertiesByPortletId(int gridId) {
		log.debug("---> finding GridProperties with portletId:" + gridId);
		try {
			String queryStr = "from GridProperty gp where gp.gridPortletDescription.id = " + gridId;
			
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryStr);
			List<GridProperty> gps = query.list();
			HibernateSessionFactory.closeSession();
			
			log.debug("---> finding GridProperties success with portletId:" + gridId);
			return gps;
		} catch (RuntimeException re) {
			log.debug("---> finding GridProperties failed with portletId:" + gridId);
			throw re;
		}
	}

	public List findByExample(GridProperty instance) {
		log.debug("finding GridProperty instance by example");
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
		log.debug("finding GridProperty instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from GridProperty as model where model."
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

	public List findByDesc(Object desc) {
		return findByProperty(DESC, desc);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByOrderBy(Object orderBy) {
		return findByProperty(ORDER_BY, orderBy);
	}

	public List findByIndex(Object index) {
		return findByProperty(INDEX, index);
	}

	public List findAll() {
		log.debug("finding all GridProperty instances");
		try {
			String queryString = "from GridProperty";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GridProperty merge(GridProperty detachedInstance) {
		log.debug("merging GridProperty instance");
		try {
			GridProperty result = (GridProperty) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GridProperty instance) {
		log.debug("attaching dirty GridProperty instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GridProperty instance) {
		log.debug("attaching clean GridProperty instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GridPropertyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (GridPropertyDAO) ctx.getBean("GridPropertyDAO");
	}
}