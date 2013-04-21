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

import com.portal.model.UserRole;
import com.portal.util.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.portal.model.UserRole
 * @author MyEclipse Persistence Tools
 */

public class UserRoleDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserRoleDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(UserRole transientInstance) {
		log.debug("saving UserRole instance");
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
	 * 删除用户角色
	 * 
	 * @param persistentInstance
	 */
	public void delete(UserRole persistentInstance) {
		log.debug("deleting UserRole instance");
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
	 * 通过用户角色编号查找用户角色
	 * 
	 * @param id
	 * @return
	 */
	public UserRole findById(java.lang.Integer id) {
		log.debug("getting UserRole instance with id: " + id);
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			UserRole instance = (UserRole) session.get(
					"com.portal.model.UserRole", id);
			tx.commit();
			HibernateSessionFactory.closeSession();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserRole instance) {
		log.debug("finding UserRole instance by example");
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
		log.debug("finding UserRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserRole as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserRole instances");
		try {
			String queryString = "from UserRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 通过用户编号查找用户角色列表
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findByUserId(int userId) {
		log.debug("---> find userRole by userId:" + userId);
		try {
			String queryString = "from UserRole ur where ur.user.id = ?";
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, userId);
			List<UserRole> userRoles = query.list();
			HibernateSessionFactory.closeSession();
			return userRoles;
		} catch (RuntimeException re) {
			log.error("---> find userRole failed by userId :" + userId, re);
			throw re;
		}
	}

	/**
	 * 通过用户编号查找该用户授权的用户角色列表
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findInStatusByUserId(int userId) {
		log.debug("---> find userRole by userId:" + userId);
		try {
			String queryString = "from UserRole ur where ur.status = 1 and ur.user.id = ?";
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, userId);
			List<UserRole> userRoles = query.list();
			HibernateSessionFactory.closeSession();
			return userRoles;
		} catch (RuntimeException re) {
			log.error("---> find userRole failed by userId :" + userId, re);
			throw re;
		}
	}
	
	/**
	 * 通过角色编号查找用户角色列表
	 * 
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findByRoleId(int roleId) {
		log.debug("---> find userRole by RoleId:" + roleId);
		try {
			String queryString = "from UserRole ur where ur.role.id = ?";
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, roleId);
			List<UserRole> userRoles = query.list();
			HibernateSessionFactory.closeSession();
			return userRoles;	
		} catch (RuntimeException re) {
			log.error("---> find userRole failed by roleId :" + roleId, re);
			throw re;
		}
	}
	
	/**
	 * 合并用户角色
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public UserRole merge(UserRole detachedInstance) {
		log.debug("merging UserRole instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			UserRole result = (UserRole) session.merge(detachedInstance);
			tx.commit();
			HibernateSessionFactory.closeSession();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserRole instance) {
		log.debug("attaching dirty UserRole instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserRole instance) {
		log.debug("attaching clean UserRole instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserRoleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserRoleDAO) ctx.getBean("UserRoleDAO");
	}
}