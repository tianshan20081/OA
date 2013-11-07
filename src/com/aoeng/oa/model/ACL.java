/**
 * 
 */
package com.aoeng.oa.model;

/**
 * Nov 4, 20137:19:43 PM
 * 
 */
public class ACL {

	private int id;
	private String principalType;
	private int principalId;
	private String resourceType;
	private int resourceId;
	private int aclState;
	private int aclTriState;

	public void setPermission(int index, boolean isPermit, boolean isExtend) {
		if (index < 0 || index > 31) {
			throw new RuntimeException("操作索引值有误,必须在0-31 之间的值！");
		}
		// 第 index 位如果是 继承 设置为 1 ，否则设置成 0
		aclTriState = setBit(aclTriState, index, isExtend);
		aclState = setBit(aclState, index, isPermit);
	}
	public boolean isExtend(int index){
		return getBit(aclTriState, index);
	}
	public boolean isPermit(int index){
		return getBit(aclState,index);
	}

	/**
	 * @param aclState2
	 * @param index
	 * @return
	 */
	private boolean getBit(int i, int index) {
		// TODO Auto-generated method stub
		int temp = 1 ;
		temp = temp << index ;
		temp = i & temp ;
		if (temp == 0) {
			return false ;
		}
		return true;
	}
	/**
	 * 000000000000000000...0000000000000000
	 * 
	 * @param isExtend
	 * @param index
	 * @param aclTriState
	 * @return
	 */
	private int setBit(int i, int index, boolean value) {
		// TODO Auto-generated method stub
		int temp = 1;
		temp = temp << index;
		if (value) {
			i = i | temp;
		} else {
			temp = ~temp;// 按位取反
			i = i & temp;
		}
		return i;
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
	 * @return the principalType
	 */
	public String getPrincipalType() {
		return principalType;
	}

	/**
	 * @param principalType
	 *            the principalType to set
	 */
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	/**
	 * @return the principalId
	 */
	public int getPrincipalId() {
		return principalId;
	}

	/**
	 * @param principalId
	 *            the principalId to set
	 */
	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
	}

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
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType
	 *            the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the aclState
	 */
	public int getAclState() {
		return aclState;
	}

	/**
	 * @param aclState
	 *            the aclState to set
	 */
	public void setAclState(int aclState) {
		this.aclState = aclState;
	}

	/**
	 * @return the aclTriState
	 */
	public int getAclTriState() {
		return aclTriState;
	}

	/**
	 * @param aclTriState
	 *            the aclTriState to set
	 */
	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}

}
