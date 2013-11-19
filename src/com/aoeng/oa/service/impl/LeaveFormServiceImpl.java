/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.LeaveFormDao;
import com.aoeng.oa.dao.UserDao;
import com.aoeng.oa.model.ApproveInfo;
import com.aoeng.oa.model.LeaveFrom;
import com.aoeng.oa.model.User;
import com.aoeng.oa.service.LeaveFormService;

/**
 * Nov 19, 20133:41:48 PM
 *
 */
@Service("leaveFormService")
@Scope("prototype")
public class LeaveFormServiceImpl implements LeaveFormService
{
	@Resource
	private LeaveFormDao leaveFormDao;
	
	@Resource
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#addLeaveForm(com.aoeng.oa.model.LeaveFrom)
	 */
	@Override
	public void addLeaveForm(LeaveFrom leaveFrom) {
		// TODO Auto-generated method stub
		leaveFrom.setStatus(LeaveFrom.STATUS_NEW);
		leaveFrom.setCreateTime(new Date());
		leaveFormDao.save(leaveFrom);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#findLeavesByUserId(int)
	 */
	@Override
	public List findLeavesByUserId(int userId) {
		// TODO Auto-generated method stub
		return leaveFormDao.findLeaveFormsByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#addApproveInfo(java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public void addApproveInfo(String content, Date approveTime, int approveId, int leaveFormId) {
		// TODO Auto-generated method stub
		ApproveInfo ai = new ApproveInfo();
		ai.setApproveTime(new Date());
		ai.setContent(content);
		User approvor = new User();
		approvor.setId(approveId);
		LeaveFrom leaveFrom = new LeaveFrom();
		leaveFrom.setId(leaveFormId);
		ai.setApprover(approvor);
		ai.setLeaveFrom(leaveFrom);
		leaveFormDao.save(ai);
		
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#findLeavingByApproverId(int)
	 */
	@Override
	public List findLeavingByApproverId(int approverId) {
		// TODO Auto-generated method stub
		return leaveFormDao.findLeavingFormsByApproverId(approverId);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#findLeavedByApproverId(int)
	 */
	@Override
	public List findLeavedByApproverId(int approverId) {
		// TODO Auto-generated method stub
		return leaveFormDao.findLeavedFormsByApproverId(approverId);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#submitLeaveForm(int, int)
	 */
	@Override
	public void submitLeaveForm(int userId, int leaveFormId) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#findLeaveFormById(int)
	 */
	@Override
	public LeaveFrom findLeaveFormById(int leaveFormId) {
		// TODO Auto-generated method stub
		return leaveFormDao.findById(LeaveFrom.class, leaveFormId);
	}

}
