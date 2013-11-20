/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.LeaveFormDao;
import com.aoeng.oa.dao.UserDao;
import com.aoeng.oa.model.ApproveInfo;
import com.aoeng.oa.model.LeaveForm;
import com.aoeng.oa.model.User;
import com.aoeng.oa.service.LeaveFormService;

/**
 * Nov 19, 20133:41:48 PM
 *
 */
@Service("leaveFormService")
public class LeaveFormServiceImpl implements LeaveFormService
{
	@Resource
	private LeaveFormDao leaveFormDao;
	
	@Resource
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#addLeaveForm(com.aoeng.oa.model.leaveForm)
	 */
	@Override
	public void addLeaveForm(LeaveForm leaveForm) {
		// TODO Auto-generated method stub
		leaveForm.setStatus(LeaveForm.STATUS_NEW);
		leaveForm.setCreateTime(new Date());
		leaveFormDao.save(leaveForm);
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
	public void addApproveInfo(String content, int approveId, int leaveFormId) {
		// TODO Auto-generated method stub
		ApproveInfo ai = new ApproveInfo();
		ai.setApproveTime(new Date());
		ai.setContent(content);
		User approvor = new User();
		approvor.setId(approveId);
		LeaveForm leaveForm = new LeaveForm();
		leaveForm.setId(leaveFormId);
		ai.setApprover(approvor);
		ai.setLeaveForm(leaveForm);
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
		//查找流向单
		LeaveForm leaveForm = leaveFormDao.findById(LeaveForm.class, leaveFormId);
		//
		if (LeaveForm.STATUS_END.equals(leaveForm.getStatus())) {
			throw new RuntimeException("请假单已经结束，无需再提交!!!");
		}
		//判断是不是创建人
		if (LeaveForm.STATUS_NEW.equals(leaveForm.getStatus())) {
			if (leaveForm.getLeaver().getId() != userId) {
				throw new RuntimeException("您不是是请假人，请假单需本人提交!");
			}
			//建立与下一个审核人(张三)之间关联
			User zs = userDao.findUserByUsername("zs");
			leaveForm.setApprover(zs);
			leaveForm.setStatus("张三审批");
			leaveFormDao.update(leaveForm);
		}else {
			//判断审核者是不是当前用户
			if (leaveForm.getApprover().getId() != userId) {
				throw new RuntimeException("您不是当前审核人，无权审核");
			}
			//判断是不是最后一个审核人
			if (leaveForm.getApprover().getUsername().equals("ls")) {
				leaveForm.setApprover(null);
				leaveForm.setStatus(LeaveForm.STATUS_END);
				leaveFormDao.update(leaveForm);
			}else {
				//建立与下一个审核人(张三)之间关联
				User wmz = userDao.findUserByUsername("wmz");
				leaveForm.setApprover(wmz);
				leaveForm.setStatus("王麻子审批");
				leaveFormDao.update(leaveForm);
			}
			
			
		}
		
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.LeaveFormService#findLeaveFormById(int)
	 */
	@Override
	public LeaveForm findLeaveFormById(int leaveFormId) {
		// TODO Auto-generated method stub
		return leaveFormDao.findById(LeaveForm.class, leaveFormId);
	}

}
