/**
 * 
 */
package com.aoeng.oa.model;

import java.util.List;

/**
 * Nov 4, 20137:16:34 PM
 * 
 */
public interface Principal
{
	public int getPrincipalId();

	public String getPrincipalType();

	public List<Principal> getParentPrincipal();
}
