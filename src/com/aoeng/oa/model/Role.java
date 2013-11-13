/**
 * 
 */
package com.aoeng.oa.model;

import java.util.List;

/**
 * Nov 4, 20137:23:02 PM
 * 
 */
public class Role implements Principal
{
	private int id;
	private String name;

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

	/* (non-Javadoc)
	 * @see com.aoeng.oa.model.Principal#getPrincipalId()
	 */
	@Override
	public int getPrincipalId() {
		// TODO Auto-generated method stub
		return id;
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.model.Principal#getPrincipalType()
	 */
	@Override
	public String getPrincipalType() {
		// TODO Auto-generated method stub
		return "Role";
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.model.Principal#getParentPrincipal()
	 */
	@Override
	public List<Principal> getParentPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

}
