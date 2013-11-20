/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.model.LeaveForm;
import com.aoeng.oa.service.LeaveFormService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Nov 20, 201310:23:38 AM
 * 
 */
@Controller("leaveFormAction")
@Scope("prototype")
public class LeaveFormAction extends BaseAction implements ModelDriven
{
	@Resource
	private LeaveFormService leaveFormService;
	private LeaveForm leaveForm;
	/**
	 * 审批意见
	 */
	private String info;
	

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (null == leaveForm) {
			leaveForm = new LeaveForm();
		}
		return leaveForm;
	}

	/**
	 * 打开请假页面
	 * 
	 * @return
	 */
	public String addInput() {
		return "add_input";
	}

	/**
	 * 添加
	 */
	public String add() {
		leaveFormService.addLeaveForm(leaveForm);
		return "add_success";
	}

	/**
	 * 查詢我的所有請假條
	 */
	public String uleaves() {
		List<LeaveForm> leaveForms = leaveFormService.findLeavesByUserId(getCurrentUser().getId());
		ActionContext.getContext().put("leaveForms", leaveForms);
		return "user_leaveforms";
	}

	/**
	 * 提交请假单
	 */
	public String submit() {
		leaveFormService.submitLeaveForm(getCurrentUser().getId(), leaveForm.getId());
		return "submit_success";
	}

	/**
	 * 查询所有带审核的请假单
	 */
	public String leaving() {
		List<LeaveForm> leavingFroms = leaveFormService.findLeavingByApproverId(getCurrentUser().getId());
		ActionContext.getContext().put("leaveForms", leavingFroms);
		System.out.println(leavingFroms.size());
		return "leaving";
	}

	/**
	 * 打开审批界面，(詳情)
	 */
	public String approveInput() {
		return "approve_input";
	}

	/**
	 * 進行審批
	 */
	public String approve() {
		System.out.println(info);
		leaveFormService.addApproveInfo(info, getCurrentUser().getId(), leaveForm.getId());
		return "approve_success";
	}

	/**
	 * 查询已经审批的请假单
	 */
	public String leaved() {
		return "leaved";
	}

}
