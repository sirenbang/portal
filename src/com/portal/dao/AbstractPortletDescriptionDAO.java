package com.portal.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.AbstractPortletDescription;

public class AbstractPortletDescriptionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(AbstractPortletDescriptionDAO.class);
	
	// property constants
	public static final String NAME = "name";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String DESCRIPTION = "description";
	public static final String IMAGE = "image";
	public static final String IMAGE_PATH = "imagePath";
	public static final String DRAGGABLE = "draggable";

	protected void initDao() {
		// do nothing
	}

	public void save(AbstractPortletDescription transientInstance) {
		log.debug("saving AbstractPortletDescription instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * 删锟斤拷锟斤拷锟侥ｏ拷锟斤拷锟斤拷锟�	 * 
	 * @param persistentInstance
	 */
	public void delete(AbstractPortletDescription persistentInstance) {
		log.debug("deleting AbstractPortletDescription instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * 	通锟斤拷锟脚诧拷锟揭筹拷锟斤拷模锟斤拷锟斤拷锟斤拷
	 * 
	 * @param id
	 * @return
	 */
	public AbstractPortletDescription findById(Integer id) {
		log.debug("getting AbstractPortletDescription instance with id: " + id);
		try {
			AbstractPortletDescription instance = (AbstractPortletDescription) getHibernateTemplate()
					.get("com.portal.model.AbstractPortletDescription", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 通锟斤拷实锟斤拷锟揭碉拷锟斤拷应锟侥筹拷锟斤拷模锟斤拷锟斤拷锟斤拷锟斤拷锟叫憋拷
	 * 
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AbstractPortletDescription> findByExample(AbstractPortletDescription instance) {
		log.debug("finding AbstractPortletDescription instance by example");
		try {
			List<AbstractPortletDescription> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * 通锟斤拷锟斤拷锟斤拷锟斤拷投锟接︼拷锟斤拷锟斤拷锟街碉拷锟斤拷页锟斤拷锟侥ｏ拷锟斤拷锟斤拷锟斤拷斜锟�	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AbstractPortletDescription> findByProperty(String propertyName, Object value) {
		log.debug("finding AbstractPortletDescription instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from AbstractPortletDescription as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 	通锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟狡诧拷锟揭讹拷应锟侥筹拷锟斤拷模锟斤拷锟斤拷锟斤拷锟叫憋拷
	 * 
	 * @param name
	 * @return
	 */
	public List<AbstractPortletDescription> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * 	通锟斤拷模锟斤拷锟角凤拷锟斤拷隙锟斤拷锟斤拷叶锟接︼拷某锟斤拷锟侥ｏ拷锟斤拷锟斤拷锟斤拷斜锟�	 * 
	 * @param draggable
	 * @return
	 */
	public List<AbstractPortletDescription> findByDraggable(Object draggable) {
		return findByProperty(DRAGGABLE, draggable);
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟叫的筹拷锟斤拷模锟斤拷锟斤拷锟斤拷锟叫憋拷
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AbstractPortletDescription> findAll() {
		log.debug("finding all AbstractPortletDescription instances");
		try {
			String queryString = "from AbstractPortletDescription";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 锟较诧拷锟斤拷锟斤拷锟斤拷锟斤拷模锟斤拷锟斤拷锟斤拷
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public AbstractPortletDescription merge(AbstractPortletDescription detachedInstance) {
		log.debug("merging AbstractPortletDescription instance");
		try {
			AbstractPortletDescription result = getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟铰筹拷锟斤拷模锟斤拷锟斤拷锟斤拷
	 * 
	 * @param instance
	 */
	public void attachDirty(AbstractPortletDescription instance) {
		log.debug("attaching dirty AbstractPortletDescription instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟叫伙拷贸锟斤拷锟侥ｏ拷锟斤拷锟斤拷锟�DAO
	 * 
	 * @param ctx
	 * @return
	 */
	public static AbstractPortletDescriptionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AbstractPortletDescriptionDAO) ctx.getBean("AbstractPortletDescriptionDAO");
	}
}