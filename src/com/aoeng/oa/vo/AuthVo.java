/**
 * 
 */
package com.aoeng.oa.vo;

/**
 * Nov 7, 20133:28:39 PM
 * 
 */
public class AuthVo
{
	private int resourceId;
	private int operIndex;
	private boolean permit;// 允许 true ,拒绝 false
	private boolean extend; // 继承 true，不继承 false

	/**
	 * @return the resourceId
	 */
	public int getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId
	 *            the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
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
	 * @return the permit
	 */
	public boolean isPermit() {
		return permit;
	}

	/**
	 * @param permit
	 *            the permit to set
	 */
	public void setPermit(boolean permit) {
		this.permit = permit;
	}

	/**
	 * @return the extend
	 */
	public boolean isExtend() {
		return extend;
	}

	/**
	 * @param extend
	 *            the extend to set
	 */
	public void setExtend(boolean extend) {
		this.extend = extend;
	}

}
