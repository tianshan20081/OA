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

import com.aoeng.oa.model.ActionResource;
import com.aoeng.oa.model.Menu;
import com.aoeng.oa.model.Party;

/**
 * Oct 31, 2013 2:50:11 PM
 *
 */
public class ActionResourceTreeVo {
	private String data ;
	private Map attr = new HashMap();
	private List<ActionResourceTreeVo> children = null;
	
	public ActionResourceTreeVo(ActionResource actionResource){
		this.data = actionResource.getName();
		this.attr.put("id", actionResource.getId());
		
		Set<ActionResource> ms = actionResource.getChildren();
		if (ms != null && ms.size() > 0) {
			this.children = new ArrayList<ActionResourceTreeVo>();
			for (ActionResource m : ms) {
				this.children.add(new ActionResourceTreeVo(m));
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
	public List<ActionResourceTreeVo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ActionResourceTreeVo> children) {
		this.children = children;
	}
}
