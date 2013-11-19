/**
 * 
 */
package com.aoeng.oa.service;

import java.security.acl.Acl;
import java.util.List;

import com.aoeng.oa.model.ACL;
import com.aoeng.oa.model.Menu;
import com.aoeng.oa.vo.AuthVo;
import com.aoeng.oa.vo.LoginInfoVo;

/**
 * Nov 7, 20133:34:38 PM
 * 
 */
public interface AclService
{

	/**
	 * @param principalType
	 * @param principalId
	 * @param string
	 * @param authVos
	 */
	void addOrUpdatePermission(String principalType, int principalId, String string, List<AuthVo> authVos);

	/**
	 * @param principalType
	 * @param principalId
	 * @param string
	 * @return
	 */
	List<AuthVo> findAclList(String principalType, int principalId, String string);

	/**
	 * @param id
	 * @return
	 */
	List<Menu> findAllPermitMenusById(int userId);

	/**
	 * @param userId
	 * @param resourceSn
	 * @param operSn
	 * @return
	 */
	boolean hasPermission(int userId, String resourceSn, String operSn);

}
