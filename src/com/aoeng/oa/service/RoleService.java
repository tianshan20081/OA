/**
 * 
 */
package com.aoeng.oa.service;

import java.util.List;

import com.aoeng.oa.model.Role;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 20139:15:05 AM
 * 
 */
public interface RoleService
{

	/**
	 * 查找所有的角色
	 * 
	 * @return
	 */
	List<Role> findAllRoles();

	/**
	 * 根據條件查詢角色
	 * 
	 * @param query
	 * @return
	 */
	PagerVo findAllRoles(String query);

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	void addRole(Role role);

	/**
	 * 根據id 刪除角色
	 * 
	 * @param roleId
	 */
	void delRoleById(int roleId);

	/**
	 * 根據id 查找 角色
	 * 
	 * @param roleId
	 * @return
	 */
	Role findRoleById(int roleId);

	/**
	 * 跟新角色
	 * 
	 * @param role
	 */
	void updateRole(Role role);
}
