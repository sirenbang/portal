package com.portal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.portal.model.Dict;
import com.portal.util.HibernateSessionFactory;

public class DictDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(DictDAO.class);

	@SuppressWarnings("unchecked")
	public List<Dict> findAll() {
		log.debug("--- find all dict instances");
		List<Dict> dicts = new ArrayList<Dict>();
		String querySql = "from Dict";
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(querySql);
			dicts = query.list();
			log.debug("--- find all dict instance success");
			return dicts;
		} catch (RuntimeException re) {
			log.error("--- find all dict instance failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Dict> findByCategory(String category) {
		log.debug("--- find dict instances by category: " + category);
		List<Dict> dicts = new ArrayList<Dict>();
		String querySql = "from Dict d where d.category = ?";
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(querySql);
			query.setParameter(0, category);
			dicts = query.list();
			log.debug("--- find dict instances by category: " + category + "success");
			return dicts;
		} catch (RuntimeException re) {
			log.error("--- find dict instances by category: " + category + "fail", re);
			throw re;
		}
	}
	
	public List<Dict> findAllSearchType() {
		return findByCategory("searchType");
	}
	
	public List<Dict> findAllColumnType() {
		return findByCategory("columnType");
	}
	
	public List<Dict> findAllPortletType() {
		return findByCategory("portletType");
	}
}
