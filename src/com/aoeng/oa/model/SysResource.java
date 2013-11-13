/**
 * 
 */
package com.aoeng.oa.model;

import java.util.List;

/**
 * Nov 4, 20137:18:06 PM
 * 
 */
public interface SysResource
{
	public int getResourceId();

	public int[] getOperIndex();

	public String getResourceType();

	public List<SysResource> getChildrenResource();

	public String getSn();

}
