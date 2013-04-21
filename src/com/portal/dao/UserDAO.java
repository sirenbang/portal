package com.portal.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.User;
import com.portal.util.HibernateSessionFactory;

public class UserDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String ENABLED = "enabled";
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}


	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			HibernateSessionFactory.closeSession();
			// getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/**
	 * 更新用户信息
	 * 
	 * @param instance
	 */
	public void update(User instance) {
		log.debug("updating User instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			session.update(instance);
			tx.begin();
			tx.commit();
			HibernateSessionFactory.closeSession();
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> search(String username, Integer enabled) {
		log.debug("---> searching User info");
		try {
			StringBuffer querySB = new StringBuffer();
			querySB.append("from User u where 0 = 0");
			
			if (username != null && !"".equals(username)) {
				querySB.append(" and u.username like '%" + username + "%'");
			}

			if (enabled != null && enabled != -1) {
				querySB.append(" and u.enabled = " + enabled);
			}
			
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(querySB.toString());
			List<User> users = query.list();
			HibernateSessionFactory.closeSession();
			return users;
		} catch (RuntimeException re) {
			log.debug("---> searching User info failed");
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.portal.model.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, value);
			List<User> users = query.list();// getHibernateTemplate().find(queryString, value);
			HibernateSessionFactory.closeSession();
			return users;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<User> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<User> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<User> findByEnabled(Object enabled) {
		return findByProperty(ENABLED, enabled);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		log.debug("finding all User instances");
		try {
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from User";
			Query query = session.createQuery(queryString);
			return query.list();//getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			User result = (User) session.merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
}