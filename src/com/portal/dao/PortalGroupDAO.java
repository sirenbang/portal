package com.portal.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.PortalGroup;
import com.portal.util.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * PortalGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.portal.model.PortalGroup
 * @author MyEclipse Persistence Tools
 */

public class PortalGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PortalGroupDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String HIDDEN = "hidden";

	protected void initDao() {
		// do nothing
	}

	/**
	 * 保存门户组
	 * 
	 * @param transientInstance
	 */
	public void save(PortalGroup transientInstance) {
		log.debug("saving PortalGroup instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			session.save(transientInstance);
			tx.commit();
			HibernateSessionFactory.closeSession();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * 删除门户组
	 * 
	 * @param persistentInstance
	 */
	public void delete(PortalGroup persistentInstance) {
		log.debug("deleting PortalGroup instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			session.delete(persistentInstance);
			tx.commit();
			HibernateSessionFactory.closeSession();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * 通过门户组编号查找门户组
	 * 
	 * @param id
	 * @return
	 */
	public PortalGroup findById(java.lang.Integer id) {
		log.debug("getting PortalGroup instance with id: " + id);
		try {
			Session session = HibernateSessionFactory.getSession();
			PortalGroup instance = (PortalGroup) session.load("com.portal.model.PortalGroup", id);
			HibernateSessionFactory.closeSession();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PortalGroup> findByProperty(String propertyName, Object value) {
		log.debug("finding PortalGroup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from PortalGroup as model where model."
					+ propertyName + "= ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, value);
			List<PortalGroup> portalGroups = query.list();
			HibernateSessionFactory.closeSession();
			return portalGroups;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<PortalGroup> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<PortalGroup> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<PortalGroup> findByHidden(Object hidden) {
		return findByProperty(HIDDEN, hidden);
	}

	/**
	 * 查找所有的门户组
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PortalGroup> findAll() {
		log.debug("finding all PortalGroup instances");
		try {
			String queryString = "from PortalGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据门户组名搜索门户列表
	 * 
	 * @param portalGroupName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PortalGroup> search(String portalGroupName) {
		log.debug("---> searching PortalGroup instances");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("from PortalGroup pg where 0 = 0");
			if (portalGroupName != null && !"".equals(portalGroupName)) {
				sb.append(" and pg.name like '%"+ portalGroupName + "%'");
			}
			
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(sb.toString());
			List<PortalGroup> portalGroups = query.list();
			HibernateSessionFactory.closeSession();
			return portalGroups;
		} catch (RuntimeException re) {
			log.error("---> searching PortalGroup instances failed", re);
			throw re;
		}
	}

	/**
	 * 合并门户组
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public PortalGroup merge(PortalGroup detachedInstance) {
		log.debug("merging PortalGroup instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			PortalGroup result = (PortalGroup) session.merge(
					detachedInstance);
			tx.commit();
			HibernateSessionFactory.closeSession();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PortalGroup instance) {
		log.debug("attaching dirty PortalGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PortalGroup instance) {
		log.debug("attaching clean PortalGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PortalGroupDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PortalGroupDAO) ctx.getBean("PortalGroupDAO");
	}
}