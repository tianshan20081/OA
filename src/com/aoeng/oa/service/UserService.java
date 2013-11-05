/**
 * 
 */
package com.aoeng.oa.service;

import java.util.List;

import com.aoeng.oa.model.User;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 20138:58:31 AM
 * 
 */
public interface UserService {

	/**
	 * 查找用戶
	 * 
	 * @param sSearch
	 * @return
	 */
	PagerVo findPersonUsers(String sSearch);

	/**
	 * 添加用戶並分配權限
	 * 
	 * @param model
	 * @param roleIds
	 *            用户ids
	 */
	void addUser(User user, int[] roleIds);

	/**
	 * 添加用戶
	 * 
	 * @param model
	 */
	void addUser(User user);

	/**
	 * 刪除用戶
	 * 
	 * @param id
	 */
	void delUser(int id);

	/**
	 * 更新用户信息
	 * 
	 * @param model
	 *            用户信息
	 * @param roleIds
	 *            用户角色ID
	 */
	void updateUser(User model, int[] roleIds);

	/**
	 * 更新用户信息
	 * 
	 * @param model
	 *            用户信息
	 */
	void updateUser(User model);

	/**
	 * 根据用户 id 查找用户
	 * 
	 * @param id
	 * @return
	 */
	User findById(int id);

	User findUserById(int id);

	/**
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdsOfUser(int userId);

}
