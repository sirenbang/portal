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

import com.portal.model.RolePortalGroup;
import com.portal.util.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * RolePortalGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.portal.model.RolePortalGroup
 * @author MyEclipse Persistence Tools
 */

public class RolePortalGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RolePortalGroupDAO.class);
	// property constants
	public static final String PERMISSION = "permission";

	protected void initDao() {
		// do nothing
	}

	/**
	 * 保存角色门户组
	 * 
	 * @param transientInstance
	 */
	public void save(RolePortalGroup transientInstance) {
		log.debug("saving RolePortalGroup instance");
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
	 * 删除角色门户组
	 * 
	 * @param persistentInstance
	 */
	public void delete(RolePortalGroup persistentInstance) {
		log.debug("deleting RolePortalGroup instance");
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
	 * 通过角色门户组编号查找角色门户组
	 * 
	 * @param id
	 * @return
	 */
	public RolePortalGroup findById(java.lang.Integer id) {
		log.debug("getting RolePortalGroup instance with id: " + id);
		try {
			Session session = HibernateSessionFactory.getSession();
			RolePortalGroup instance = (RolePortalGroup) session.get("com.portal.model.RolePortalGroup", id);
			HibernateSessionFactory.closeSession();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RolePortalGroup instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RolePortalGroup as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPermission(Object permission) {
		return findByProperty(PERMISSION, permission);
	}

	/**
	 * 查找所有角色门户组
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolePortalGroup> findAll() {
		log.debug("finding all RolePortalGroup instances");
		try {
			String queryString = "from RolePortalGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 通过角色编号查找对应的角色门户组
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolePortalGroup> findByRoleId(int id) {
		log.debug("---> finding all RolePortalGroup instances with id:" + id);
		try {
			String queryString = "from RolePortalGroup rpg where rpg.role.id = ?";
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, id);
			List<RolePortalGroup> rpgs = query.list();
			HibernateSessionFactory.closeSession();
			return rpgs;
		} catch (RuntimeException re) {
			log.error("find all RolePortalGroup instances  failed with id:" + id, re);
			throw re;
		}
	}
	/**
	 * 通过门户组编号查找对应的角色门户组
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolePortalGroup> findByPortalGroupId(int id) {
		log.debug("---> finding all RolePortalGroup instances with id:" + id);
		try {
			String queryString = "from RolePortalGroup rpg where rpg.portalGroup.id = ?";
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, id);
			List<RolePortalGroup> rpgs = query.list();
			HibernateSessionFactory.closeSession();
			return rpgs;
		} catch (RuntimeException re) {
			log.error("find all RolePortalGroup instances  failed with id:" + id, re);
			throw re;
		}
	}

	/**
	 * 合并角色门户组
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public RolePortalGroup merge(RolePortalGroup detachedInstance) {
		log.debug("merging RolePortalGroup instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			RolePortalGroup result = (RolePortalGroup) session.merge(detachedInstance);
			tx.commit();
			HibernateSessionFactory.closeSession();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RolePortalGroup instance) {
		log.debug("attaching dirty RolePortalGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RolePortalGroup instance) {
		log.debug("attaching clean RolePortalGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RolePortalGroupDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RolePortalGroupDAO) ctx.getBean("RolePortalGroupDAO");
	}
}