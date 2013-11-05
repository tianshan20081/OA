/**
 * 
 */
package com.aoeng.oa.model;

import java.util.Map;

/**
 * Nov 4, 20137:26:40 PM
 * 
 */
public class ActionResource implements SysResource {
	private int id;
	/**
	 * 资源组织机构名称
	 */
	private String name;
	/**
	 * 资源对应的 action 类名，可以有多个(partyAction ,CompanyAction,用竖线分割)
	 */
	private String className;
	/**
	 * 资源的唯一标识（company,department,）
	 */
	private String sn;
	/**
	 * 资源的排序号
	 */
	private int orderNumber;
	
	private Map<String, ActionMethodOper> opers ;
	
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
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

}
