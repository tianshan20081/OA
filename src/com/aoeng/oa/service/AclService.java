/**
 * 
 */
package com.aoeng.oa.service;

import java.util.List;

import com.aoeng.oa.vo.AuthVo;

/**
 * Nov 7, 20133:34:38 PM
 *
 */
public interface AclService {

	/**
	 * @param principalType
	 * @param principalId
	 * @param string
	 * @param authVos
	 */
	void addOrUpdatePermission(String principalType, int principalId, String string, List<AuthVo> authVos);

}
