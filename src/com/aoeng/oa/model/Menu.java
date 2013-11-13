/**
 * 
 */
package com.aoeng.oa.model;

import java.util.List;
import java.util.Set;

/**
 * Nov 4, 20137:18:17 PM
 * 
 */
public class Menu implements SysResource
{
	private int id;
	private String name;
	private String href;
	private int orderNumber;
	private String sn;
	private boolean display;

	private Menu parent;

	private Set<Menu> children;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href
	 *            the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the display
	 */
	public boolean isDisplay() {
		return display;
	}

	/**
	 * @param display
	 *            the display to set
	 */
	public void setDisplay(boolean display) {
		this.display = display;
	}

	/**
	 * @return the parent
	 */
	public Menu getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Menu parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public Set<Menu> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.model.SysResource#getResourceId()
	 */
	@Override
	public int getResourceId() {
		// TODO Auto-generated method stub
		return id;
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.model.SysResource#getOperIndex()
	 */
	@Override
	public int[] getOperIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.model.SysResource#getResourceType()
	 */
	@Override
	public String getResourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.model.SysResource#getChildrenResource()
	 */
	@Override
	public List<SysResource> getChildrenResource() {
		// TODO Auto-generated method stub
		return null;
	}

}
