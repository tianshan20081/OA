/**
 * 
 */
package com.aoeng.oa.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aoeng.oa.model.Menu;
import com.aoeng.oa.model.Party;

/**
 * Oct 31, 2013 2:50:11 PM
 * 
 */
public class AuthMenuTreeVo
{
	private Map data = new HashMap();
	private Map attr = new HashMap();
	private List<AuthMenuTreeVo> children = new ArrayList<AuthMenuTreeVo>();

	public AuthMenuTreeVo(Menu menu) {
		//菜单项名称
		this.data.put("title", menu.getName());
		//菜单项链接
		Map linkAtrr = new HashMap();
		linkAtrr.put("href", menu.getHref());
		this.data.put("attr", linkAtrr);
		
		Set subMenus = menu.getChildren();
		for (Iterator<Menu> iter=subMenus.iterator();iter.hasNext();) {
			Menu subMenu = iter.next();
			children.add(new AuthMenuTreeVo(subMenu));
		}
	}


	/**
	 * @return the data
	 */
	public Map getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(Map data) {
		this.data = data;
	}


	/**
	 * @return the attr
	 */
	public Map getAttr() {
		return attr;
	}

	/**
	 * @param attr
	 *            the attr to set
	 */
	public void setAttr(Map attr) {
		this.attr = attr;
	}

	/**
	 * @return the children
	 */
	public List<AuthMenuTreeVo> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<AuthMenuTreeVo> children) {
		this.children = children;
	}
}
