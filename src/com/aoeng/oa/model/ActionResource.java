/**
 * 
 */
package com.aoeng.oa.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

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
	/**
	 * 资源所包含的操作 key 就是 ActionMethodOper对象的operSn 该操作的唯一标识，eg:ADD,UPDATE,DELETE,READ
	 */
	private Map<String, ActionMethodOper> opers;

	public void addActionMethodOper(String methodName, String operName, String operSn, int operIndex) {
		if (opers == null) {
			opers = new HashMap<String, ActionMethodOper>();
		}
		ActionMethodOper amo = opers.get(operSn);
		if (amo != null) {
			amo.addMethodName(methodName);
		} else {
			// 判断 operIndex 是否已经存在，如果已经存在则抛出异常，操作索引不允许存在
			for (ActionMethodOper o : opers.values()) {
				if (o.getOperIndex() == operIndex) {
					throw new RuntimeException("针对资源【" + name + "】的操作【" + o.getOperName() + "】已经和索引【" + o.getOperIndex() + "】绑定了，无法把操作【" + operName + "】再次绑定到相同的索引");
				}
				amo = new ActionMethodOper();
				amo.setMethodName(methodName);
				amo.setOperIndex(operIndex);
				amo.setOperName(operName);
				amo.setOperSn(operSn);
				opers.put(operSn, amo);
			}
		}
	}

	public void addClassName(String className) {
		if (StringUtils.isEmpty(className)) {
			this.className = className;
		} else {
			String[] classNames = this.className.split("\\|");
			if (!ArrayUtils.contains(classNames, className)) {
				this.className += "|" + className;
			}
		}
	}

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
