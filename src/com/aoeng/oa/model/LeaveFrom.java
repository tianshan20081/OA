/**
 * 
 */
package com.aoeng.oa.model;

import java.util.Date;

/**
 * Nov 19, 20131:56:24 PM
 * 
 */
public class LeaveFrom
{
	public static final String STATUS_NEW = "新建";
	public static final String STATUS_END = "完成";
	/**
	 * 表单id
	 */
	private int id;
	/**
	 * 表单内容
	 */
	private String content;
	/**
	 * 表单状态
	 */
	private String status ;
	/**
	 * 表单单据有效时间
	 */
	private int days;
	/**
	 * 表单创建时间
	 */
	private Date createTime;
	/**
	 * 表单开始时间
	 */
	private Date beginTime;
	/**
	 * 表单结束时间
	 */
	private Date endTime;
	/**
	 * 表单发起人
	 */
	private User leaver;
	/**
	 * 表单审批人
	 */
	private User approver;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the leaver
	 */
	public User getLeaver() {
		return leaver;
	}
	/**
	 * @param leaver the leaver to set
	 */
	public void setLeaver(User leaver) {
		this.leaver = leaver;
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
}
