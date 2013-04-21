package com.portal.service;

import java.util.List;

import com.portal.model.AbstractPortletDescription;
import com.portal.model.GridPortletDescription;
import com.portal.model.GridProperty;
import com.portal.model.GridSearch;
import com.portal.model.IframePortletDescription;
import com.portal.model.LinkPortletDescription;
import com.portal.model.LinkProperty;

public interface PortletService {

	/**
	 * 根据模块名称和模块类型搜索模块
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<AbstractPortletDescription> search(String name, int type);
	
	/**
	 *  根据抽象模块编号查找抽象模块
	 * @param id
	 * @return
	 */
	public AbstractPortletDescription findPortletDescriptionById(int id);
	
	/**
	 * 	根据抽象模块编号查找 Iframe 类型模块
	 * 
	 * @param id
	 * @return
	 */
	public IframePortletDescription findIframeByAbstractId(int id);
	
	/**
	 * 	根据抽象模块编号查找 Link 类型模块
	 * 
	 * @param id
	 * @return
	 */
	public LinkPortletDescription findLinkByAbstractId(int id);
	
	/**
	 *  根据抽象模块编号查找 Grid 类型模块
	 * 
	 * @param id
	 * @return
	 */
	public GridPortletDescription findGridByAbstractId(int id);
	
	/**
	 * 	根据 Link 模块编号查找 Link 属性列表
	 * 
	 * @param linkId
	 * @return
	 */
	public List<LinkProperty> findLinkPropertiesByPortletId(int linkId);
	
	/**
	 * 	根据 Grid 模块编号查找 Grid 属性列表
	 * 
	 * @param gridId
	 * @return
	 */
	public List<GridProperty> findGridPropertiesByPortletId(int gridId);
	
	/**
	 * 	根据 Grid 模块编号查找 Grid 搜索属性列表
	 * 	
	 * @param gridId
	 * @return
	 */
	public List<GridSearch> findGridSearchesByPortletId(int gridId);
}
