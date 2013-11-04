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

import com.aoeng.oa.model.Party;

/**
 * Oct 31, 2013 2:50:11 PM
 *
 */
public class PartyTreeVo {
	private String data ;
	private Map attr = new HashMap();
	private List<PartyTreeVo> children = null;
	
	public PartyTreeVo(Party party){
		this.data = party.getName();
		this.attr.put("id", party.getId());
		
		//用类名作为 类型  
		/**
		 * 类型 可以是
		 * company
		 * department
		 * position
		 * person
		 */
		this.attr.put("partyType", party.getClass().getSimpleName().toLowerCase());
		Set<Party> cs = party.getChildren();
		if (cs != null && cs.size() > 0) {
			this.children = new ArrayList<PartyTreeVo>();
			for (Party p : cs) {
				this.children.add(new PartyTreeVo(p));
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
	public List<PartyTreeVo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<PartyTreeVo> children) {
		this.children = children;
	}
}
