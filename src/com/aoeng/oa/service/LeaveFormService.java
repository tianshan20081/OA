/**
 * 
 */
package com.aoeng.oa.service;

import java.util.Date;
import java.util.List;

import com.aoeng.oa.model.LeaveForm;

/**
 * Nov 19, 20133:26:30 PM
 * 
 */
public interface LeaveFormService
{
	/**
	 * 添加流向单
	 * 
	 * @param leaveForm
	 */
	public void addLeaveForm(LeaveForm leaveForm);

	/**
	 * 通过 userId 查找用户的所有 LeaveForms
	 * 
	 * @param userId
	 * @return
	 */
	public List findLeavesByUserId(int userId);

	/**
	 * 添加审核信息
	 * 
	 * @param content
	 *            审核意见
	 * @param approveId
	 *            审核人Id
	 * @param leaveFormId
	 *            审核单据 ID
	 */
	public void addApproveInfo(String content, int approveId, int leaveFormId);

	/**
	 * 查找审批中的流向单
	 * 
	 * @param approverId
	 *            依据审核人 ID 来查找该审核人应该处理的单据
	 * @return
	 */
	public List findLeavingByApproverId(int approverId);

	/**
	 * 查找审批完成的流向单
	 * 
	 * @param approverId
	 * @return
	 */
	public List findLeavedByApproverId(int approverId);

	/**
	 * 提交流向单
	 * 
	 * @param userId
	 * @param leaveFormId
	 */
	public void submitLeaveForm(int userId, int leaveFormId);

	/**
	 * 根據流向單的id 查找流向单
	 * 
	 * @return
	 */
	public LeaveForm findLeaveFormById(int leaveFormId);
}
