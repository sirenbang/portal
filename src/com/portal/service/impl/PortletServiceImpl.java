package com.portal.service.impl;

import java.util.List;

import com.portal.dao.AbstractPortletDescriptionDAO;
import com.portal.dao.GridPortletDescriptionDAO;
import com.portal.dao.GridPropertyDAO;
import com.portal.dao.GridSearchDAO;
import com.portal.dao.IframePortletDescriptionDAO;
import com.portal.dao.LinkPortletDescriptionDAO;
import com.portal.dao.LinkPropertyDAO;
import com.portal.model.AbstractPortletDescription;
import com.portal.model.GridPortletDescription;
import com.portal.model.GridProperty;
import com.portal.model.GridSearch;
import com.portal.model.IframePortletDescription;
import com.portal.model.LinkPortletDescription;
import com.portal.model.LinkProperty;
import com.portal.service.PortletService;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class PortletServiceImpl implements PortletService {
	
	private AbstractPortletDescriptionDAO abstractPortletDescriptionDAO;
	private IframePortletDescriptionDAO iframePortletDescriptionDAO;
	private LinkPortletDescriptionDAO linkPortletDescriptionDAO;
	private LinkPropertyDAO linkPropertyDAO;
	private GridPortletDescriptionDAO gridPortletDescriptionDAO;
	private GridPropertyDAO gridPropertyDAO;
	private GridSearchDAO gridSearchDAO;
	
	public void setAbstractPortletDescriptionDAO(
			AbstractPortletDescriptionDAO abstractPortletDescriptionDAO) {
		this.abstractPortletDescriptionDAO = abstractPortletDescriptionDAO;
	}
	
	public void setIframePortletDescriptionDAO(
			IframePortletDescriptionDAO iframePortletDescriptionDAO) {
		this.iframePortletDescriptionDAO = iframePortletDescriptionDAO;
	}

	public void setLinkPortletDescriptionDAO(
			LinkPortletDescriptionDAO linkPortletDescriptionDAO) {
		this.linkPortletDescriptionDAO = linkPortletDescriptionDAO;
	}

	public void setLinkPropertyDAO(LinkPropertyDAO linkPropertyDAO) {
		this.linkPropertyDAO = linkPropertyDAO;
	}

	public void setGridPortletDescriptionDAO(
			GridPortletDescriptionDAO gridPortletDescriptionDAO) {
		this.gridPortletDescriptionDAO = gridPortletDescriptionDAO;
	}

	public void setGridPropertyDAO(GridPropertyDAO gridPropertyDAO) {
		this.gridPropertyDAO = gridPropertyDAO;
	}

	public void setGridSearchDAO(GridSearchDAO gridSearchDAO) {
		this.gridSearchDAO = gridSearchDAO;
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#search(java.lang.String, int)
	 */
	public List<AbstractPortletDescription> search(String name, int type) {
		try {
			return abstractPortletDescriptionDAO.search(name, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#findPortletDescriptionById(int)
	 */
	public AbstractPortletDescription findPortletDescriptionById(int id) {
		try {
			return abstractPortletDescriptionDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#findIframeByAbstractId(int)
	 */
	public IframePortletDescription findIframeByAbstractId(int id) {
		try {
			return iframePortletDescriptionDAO.findIframeByAbstractId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#findLinkByAbstractId(int)
	 */
	public LinkPortletDescription findLinkByAbstractId(int id) {
		try {
			return linkPortletDescriptionDAO.findLinkByAbstractId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#findGridByAbstractId(int)
	 */
	public GridPortletDescription findGridByAbstractId(int id) {
		try {
			return gridPortletDescriptionDAO.findGridByAbstractId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#findLinkPropertiesByPortletId(int)
	 */
	public List<LinkProperty> findLinkPropertiesByPortletId(int linkId) {
		try {
			return linkPropertyDAO.findLinkPropertiesByPortletId(linkId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#findGridPropertiesByPortletId(int)
	 */
	public List<GridProperty> findGridPropertiesByPortletId(int gridId) {
		try {
			return gridPropertyDAO.findGridPropertiesByPortletId(gridId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.portal.service.PortletService#findGridSearchesByPortletId(int)
	 */
	public List<GridSearch> findGridSearchesByPortletId(int gridId) {
		try {
			return gridSearchDAO.findGridSearchesByPortletId(gridId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
