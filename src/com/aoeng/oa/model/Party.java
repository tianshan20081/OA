/**
 * 
 */
package com.aoeng.oa.model;

import java.util.Set;

/**
 * Oct 30, 2013 1:27:32 PM
 *
 */
public class Party {
	private int id;
	private String name;
	private String description;
	private Party parent;
	private Set<Party> children;
	private String sn;
     
	/**
	 * 
	 */
	public Party() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param parent
	 * @param children
	 * @param sn
	 */
	public Party(int id, String name, String description, Party parent, Set<Party> children, String sn) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.parent = parent;
		this.children = children;
		this.sn = sn;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the parent
	 */
	public Party getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Party parent) {
		this.parent = parent;
	}
	/**
	 * @return the children
	 */
	public Set<Party> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<Party> children) {
		this.children = children;
	}
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
     
     
     
}
