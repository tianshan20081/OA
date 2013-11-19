/**
 * 
 */
package com.aoeng.oa.dao;

import java.util.List;

/**
 * Nov 19, 20132:56:25 PM
 *
 */
public interface LeaveFormDao extends BaseDao
{
	/**
	 * 查询某一个用户的所有申请单
	 * @param userId
	 * @return
	 */
	public List findLeaveFormsByUserId(int userId);
	/**
	 * 查询某一个用户的所有审批中的申请单
	 * @param userId
	 * @return
	 */
	public List findLeavingFormsByApproverId(int approverId);
	/**
	 * 查询某一个用户的所有审批完毕的申请单
	 * @param userId
	 * @return
	 */
	public List findLeavedFormsByApproverId(int approverId);
}
