package com.portal.base;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.portal.dao.DictDAO;
import com.portal.model.Dict;

public class InitServlet  extends HttpServlet  {

	private static final long serialVersionUID = 7032134122716067696L;
	private static final Logger log = LoggerFactory.getLogger(InitServlet.class);
	
	public void init() throws ServletException {
		log.debug("----init dict messages...");
		ServletContext application = this.getServletContext();
		DictDAO dictDAO = new DictDAO();
		
		List<Dict> allDicts = dictDAO.findAll();
		application.setAttribute("allDicts", allDicts);
		
		List<Dict> allSearchTypeDicts = dictDAO.findAllSearchType();
		application.setAttribute("allSearchType", allSearchTypeDicts);
		
		List<Dict> allColumnTypeDicts = dictDAO.findAllColumnType();
		application.setAttribute("allColumnType", allColumnTypeDicts);
		
		List<Dict> allPortletTypeDicts = dictDAO.findAllPortletType();
		application.setAttribute("allPortletType", allPortletTypeDicts);	
	}
}
