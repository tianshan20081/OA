/**
 * 
 */
package com.aoeng.oa.model;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Nov 5, 20137:51:43 PM
 * 
 */
public class ActionMethodOper
{
	/**
	 * 操作的唯一标识 eg:ADD，UPDATE，
	 */
	private String operSn;
	/**
	 * 操作存储 aclstate在 ACL 当中的 索引位置，索引必须大于0，并且 小于等于 31 同一个资源当中索引值都是唯一的，不能重复
	 */
	private int operIndex;
	/**
	 * 对应的方法名字，同一个操作可能对应多个方法 eg:addInput|add,apdateInput|update,del
	 */
	private String methodName;
	/**
	 * 操作的名称
	 */
	private String operName;

	public void addMethodName(String methodName) {
		if (StringUtils.isEmpty(this.methodName)) {
			this.methodName = methodName;
		} else {
			String[] methodNames = this.methodName.split("\\|");
			if (!ArrayUtils.contains(methodNames, methodName)) {
				this.methodName += "|" + methodName;
			}
		}
	}

	/**
	 * @return the operSn
	 */
	public String getOperSn() {
		return operSn;
	}

	/**
	 * @param operSn
	 *            the operSn to set
	 */
	public void setOperSn(String operSn) {
		this.operSn = operSn;
	}

	/**
	 * @return the operIndex
	 */
	public int getOperIndex() {
		return operIndex;
	}

	/**
	 * @param operIndex
	 *            the operIndex to set
	 */
	public void setOperIndex(int operIndex) {
		this.operIndex = operIndex;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the operName
	 */
	public String getOperName() {
		return operName;
	}

	/**
	 * @param operName
	 *            the operName to set
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}

}
