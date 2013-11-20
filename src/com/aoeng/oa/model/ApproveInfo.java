/**
 * 
 */
package com.aoeng.oa.model;

import java.util.Date;

/**
 * Nov 19, 20132:21:59 PM
 * 审批信息
 */
public class ApproveInfo
{
	/**
	 * 
	 */
	private int id ;
	/**
	 * 审批意见
	 */
	private String content ;
	/**
	 * 审批时间
	 */
	private Date approveTime ;
	/**
	 * 审批人
	 */
	private User approver ;
	/**
	 * 审批单据
	 */
	private LeaveForm leaveForm ;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the approveTime
	 */
	public Date getApproveTime() {
		return approveTime;
	}
	/**
	 * @param approveTime the approveTime to set
	 */
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	/**
	 * @return the approver
	 */
	public User getApprover() {
		return approver;
	}
	/**
	 * @param approver the approver to set
	 */
	public void setApprover(User approver) {
		this.approver = approver;
	}
	/**
	 * @return the leaveForm
	 */
	public LeaveForm getLeaveForm() {
		return leaveForm;
	}
	/**
	 * @param leaveForm the leaveForm to set
	 */
	public void setLeaveForm(LeaveForm leaveForm) {
		this.leaveForm = leaveForm;
	}
	

}
