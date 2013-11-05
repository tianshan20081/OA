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
