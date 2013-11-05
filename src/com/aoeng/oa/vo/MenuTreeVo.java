/**
 * 
 */
package com.aoeng.oa.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aoeng.oa.model.Menu;
import com.aoeng.oa.model.Party;

/**
 * Oct 31, 2013 2:50:11 PM
 *
 */
public class MenuTreeVo {
	private String data ;
	private Map attr = new HashMap();
	private List<MenuTreeVo> children = null;
	
	public MenuTreeVo(Menu menu){
		this.data = menu.getName();
		this.attr.put("id", menu.getId());
		
		Set<Menu> ms = menu.getChildren();
		if (ms != null && ms.size() > 0) {
			this.children = new ArrayList<MenuTreeVo>();
			for (Menu m : ms) {
				this.children.add(new MenuTreeVo(m));
			}
		}
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the attr
	 */
	public Map getAttr() {
		return attr;
	}
	/**
	 * @param attr the attr to set
	 */
	public void setAttr(Map attr) {
		this.attr = attr;
	}
	/**
	 * @return the children
	 */
	public List<MenuTreeVo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<MenuTreeVo> children) {
		this.children = children;
	}
}
