/**
 * 
 */
package com.aoeng.oa.dao;

import java.util.List;

import com.aoeng.oa.model.User;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 20139:46:20 AM
 * 
 */
public interface UserDao extends BaseDao
{

	/**
	 * 查找所有用户
	 * 
	 * @param personName
	 * @return
	 */
	PagerVo findPersonUsers(String personName);

	/**
	 * @param user
	 * @param roleIds
	 */
	void update(User user, int[] roleIds);

	/**
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdsOfUser(int userId);

	/**
	 * @param sSearch
	 */
	List findPersonWithUsers(String personName);

	/**
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);

}
