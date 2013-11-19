/**
 * 
 */
package com.aoeng.oa.service;

import java.util.Date;
import java.util.List;

import com.aoeng.oa.model.ApproveInfo;
import com.aoeng.oa.model.LeaveFrom;
import com.sdicons.json.validator.impl.predicates.Int;

/**
 * Nov 19, 20133:26:30 PM
 * 
 */
public interface LeaveFormService
{
	/**
	 * 添加流向单
	 * 
	 * @param leaveFrom
	 */
	public void addLeaveForm(LeaveFrom leaveFrom);

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
	 * @param approveTime
	 * @param approveId
	 * @param leaveFormId
	 */
	public void addApproveInfo(String content, Date approveTime, int approveId, int leaveFormId);

	/**
	 * 查找审批中的流向单
	 * 
	 * @param approverId
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
	public LeaveFrom findLeaveFormById(int leaveFormId);
}
