/**
 * 
 */
package com.aoeng.oa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Nov 4, 20137:26:40 PM
 * 
 */
public class ActionResource implements SysResource
{
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
	 * 父资源的唯一标识
	 */
	private String parentSn;
	/**
	 * 资源的排序号
	 */
	private int orderNumber;
	/**
	 * 资源所包含的操作 key 就是 ActionMethodOper对象的operSn 该操作的唯一标识，eg:ADD,UPDATE,DELETE,READ
	 */
	private Map<String, ActionMethodOper> opers;
	/**
	 * 父资源
	 */
	private ActionResource parent;
	/**
	 * 子资源
	 */
	private Set<ActionResource> children = new HashSet<ActionResource>();

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
					throw new RuntimeException("针对资源【" + name + "】的操作【" + o.getOperName() + "】已经和索引【"
							+ o.getOperIndex() + "】绑定了，无法把操作【" + operName + "】再次绑定到相同的索引");
				}
			}
			amo = new ActionMethodOper();
			amo.setMethodName(methodName);
			amo.setOperIndex(operIndex);
			amo.setOperName(operName);
			amo.setOperSn(operSn);
			opers.put(operSn, amo);

		}
	}

	public void addClassName(String className) {
		if (StringUtils.isEmpty(this.className)) {
			this.className = className;
		} else {
			String[] classNames = this.className.split("\\|");
			if (!ArrayUtils.contains(classNames, className)) {
				this.className += "|" + className;
			}
		}
	}

	/**
	 * @return the parentSn
	 */
	public String getParentSn() {
		return parentSn;
	}

	/**
	 * @param parentSn
	 *            the parentSn to set
	 */
	public void setParentSn(String parentSn) {
		this.parentSn = parentSn;
	}

	/**
	 * @return the children
	 */
	public Set<ActionResource> getChildren() {
		return children;
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
	 * @return the opers
	 */
	public Map<String, ActionMethodOper> getOpers() {
		return opers;
	}

	/**
	 * @param opers
	 *            the opers to set
	 */
	public void setOpers(Map<String, ActionMethodOper> opers) {
		this.opers = opers;
	}

	/**
	 * @return the parent
	 */
	public ActionResource getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(ActionResource parent) {
		this.parent = parent;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(Set<ActionResource> children) {
		this.children = children;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.model.SysResource#getResourceId()
	 */
	@Override
	public int getResourceId() {
		// TODO Auto-generated method stub
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.model.SysResource#getOperIndex()
	 */
	@Override
	public int[] getOperIndex() {
		// TODO Auto-generated method stub
		if (opers != null) {
			Collection<ActionMethodOper> ams = opers.values();
			int[] opersIndex = new int[ams.size()];
			int i = 0;
			for (ActionMethodOper oper : ams) {
				opersIndex[i++] = oper.getOperIndex();
			}
			return opersIndex;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.model.SysResource#getResourceType()
	 */
	@Override
	public String getResourceType() {
		// TODO Auto-generated method stub
		return "ActionResource";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.model.SysResource#getChildrenResource()
	 */
	@Override
	public List<SysResource> getChildrenResource() {
		if (children != null) {
			List<SysResource> res = new ArrayList<SysResource>();
			res.addAll(children);
			return res;
		}
		return null;

	}

}
