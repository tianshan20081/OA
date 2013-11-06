/**
 * 
 */
package com.aoeng.oa.dao;

import com.aoeng.oa.model.ActionResource;

/**
 * Nov 6, 20132:03:32 PM
 *
 */
public interface ResourceDao extends BaseDao{

	/**
	 * 根据 ActionResource 的唯一标识 sn 查找 ActionResource
	 * @param sn
	 * @return
	 */
	ActionResource findActionResourceBySn(String sn);

}
