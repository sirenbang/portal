package com.portal.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.DefaultPortlet;

/**
 * @author Administrator
 *
 */
public class DefaultPortletDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(DefaultPortletDAO.class);
	
	// property constants
	public static final String COLUMN = "column";
	public static final String COLUMN_INDEX = "columnIndex";
	public static final String HIDDEN = "hidden";

	protected void initDao() {
		// do nothing
	}

	/**
	 * 	����Ĭ��ģ��
	 * 
	 * @param transientInstance
	 */
	public void save(DefaultPortlet transientInstance) {
		log.debug("saving DefaultPortlet instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * ɾ��Ĭ��ģ��
	 * 
	 * @param persistentInstance
	 */
	public void delete(DefaultPortlet persistentInstance) {
		log.debug("deleting DefaultPortlet instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * ͨ���Ų�����Ӧ��Ĭ��ģ��
	 * 
	 * @param id
	 * @return
	 */
	public DefaultPortlet findById(java.lang.Integer id) {
		log.debug("getting DefaultPortlet instance with id: " + id);
		try {
			DefaultPortlet instance = (DefaultPortlet) getHibernateTemplate().get("com.portal.model.DefaultPortlet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * ͨ�������������ֵ�ҵ���Ӧ��Ĭ��ģ���б�
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DefaultPortlet> findByProperty(String propertyName, Object value) {
		log.debug("finding DefaultPortlet instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DefaultPortlet as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 	ͨ���кŲ��Ҹ��е�Ĭ��ģ���б�
	 * 
	 * @param column
	 * @return
	 */
	public List<DefaultPortlet> findByColumn(Object column) {
		return findByProperty(COLUMN, column);
	}

	/**
	 * 	ͨ���кŲ��Ҹ��е�Ĭ��ģ���б�
	 * 
	 * @param columnIndex
	 * @return
	 */
	public List<DefaultPortlet> findByColumnIndex(Object columnIndex) {
		return findByProperty(COLUMN_INDEX, columnIndex);
	}

	/**
	 * 	ͨ���Ƿ�ɼ���Ҷ�Ӧ��Ĭ��ģ���б�
	 * 
	 * @param hidden
	 * @return
	 */
	public List<DefaultPortlet> findByHidden(Object hidden) {
		return findByProperty(HIDDEN, hidden);
	}

	/**
	 * �������е�Ĭ��ģ��
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DefaultPortlet> findAll() {
		log.debug("finding all DefaultPortlet instances");
		try {
			String queryString = "from DefaultPortlet";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * �ϲ������Ĭ��ģ��
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public DefaultPortlet merge(DefaultPortlet detachedInstance) {
		log.debug("merging DefaultPortlet instance");
		try {
			DefaultPortlet result = (DefaultPortlet) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 *  ��������Ĭ��ģ��
	 * 
	 * @param instance
	 */
	public void attachDirty(DefaultPortlet instance) {
		log.debug("attaching dirty DefaultPortlet instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	/**
	 * 	���������еõ�Ĭ��ģ�� DAO
	 * 
	 * @param ctx
	 * @return
	 */
	public static DefaultPortletDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DefaultPortletDAO) ctx.getBean("DefaultPortletDAO");
	}
}