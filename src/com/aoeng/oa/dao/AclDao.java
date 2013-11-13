/**
 * 
 */
package com.aoeng.oa.dao;

import java.security.acl.Acl;
import java.util.List;

import com.aoeng.oa.model.ACL;
import com.aoeng.oa.model.Principal;
import com.aoeng.oa.model.SysResource;
import com.aoeng.oa.vo.AuthVo;

/**
 * Nov 7, 20133:38:29 PM
 * 
 */
public interface AclDao extends BaseDao
{

	/**
	 * @param principalType
	 * @param principalId
	 * @param resourceType
	 */
	void delAcl(String principalType, int principalId, String resourceType);

	/**
	 * @param principalType
	 * @param principalId
	 * @param resourceType
	 * @param resourceId
	 * @return
	 */
	ACL findACL(String principalType, int principalId, String resourceType, int resourceId);

	/**
	 * @param principalType
	 * @param principalId
	 * @param resourceType
	 * @return
	 */
	List<ACL> findAclList(String principalType, int principalId, String resourceType);

	/**
	 * @param resourceType
	 * @return
	 */
	List<SysResource> findAllSysResources(String resourceType);

	/**
	 * @param principalType
	 * @param principalId
	 * @return
	 */
	Principal findPrincipalById(String principalType, int principalId);


}
