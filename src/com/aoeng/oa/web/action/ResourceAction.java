/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.annotations.Oper;
import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.model.ActionMethodOper;
import com.aoeng.oa.model.ActionResource;
import com.aoeng.oa.service.ResourceService;
import com.aoeng.oa.utils.JsonUtils;
import com.aoeng.oa.vo.ActionResourceTreeVo;
import com.aoeng.oa.vo.PagerVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Nov 6, 20135:20:59 PM
 * 
 */
@Controller("resourceAction")
@Scope("prototype")
@Res(name = "资源操作", sn = "resource", orderNumber = 11, parentSn = "security")
public class ResourceAction implements ModelDriven {
	@Resource
	private ResourceService resourceService;
	private ActionResource model;

	private String operSn;
	private int operIndex;
	private String methodName;
	private String operName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (null == model) {
			model = new ActionResource();
		}
		return model;
	}

	/**
	 * 打开资源设置主界面
	 * 
	 * @return
	 */
	@Oper
	public String execute() {

		return "index";
	}

	/**
	 * 生成 资源 主页面上的 资源树
	 */
	public void tree() {
		List<ActionResource> resources = resourceService.findAllTopActionResources();
		List<ActionResourceTreeVo> vos = new ArrayList<ActionResourceTreeVo>();
		for (ActionResource r : resources) {
			vos.add(new ActionResourceTreeVo(r));
		}
		JsonUtils.toJson(vos);
	}

	@Oper
	public String addInput() {
		// 添加的是 子资源，因此需要判断起父节点是否存在
		int parentId = model.getParent().getId();
		if (parentId == 0) {
			throw new RuntimeException("未知父节点，无法创建子节点");
		}
		return "add_input";
	}

	@Oper
	public String add() {
		resourceService.addActionResource(model);
		return "add_success";
	}

	@Oper
	public String updateInput() {
		model = resourceService.findActionResourceById(model.getId());
		return "update_input";
	}

	@Oper
	public String update() {
		resourceService.updateActionResource(model);
		return "update_success";
	}

	@Oper
	public String del() {
		resourceService.delActionResource(model.getId());
		return "del_success";
	}
	
	public String operInput(){
		return "oper_input";
	}
	public String addOper(){
		ActionMethodOper oper = new ActionMethodOper();
		oper.setMethodName(methodName);
		oper.setOperIndex(operIndex);
		oper.setOperName(operName);
		oper.setOperSn(operSn);
		resourceService.addActionResourceOper(model.getId(),oper);
		return "add_success";
	}
	
	public void delOper(){
		resourceService.delActionResourceOper(model.getId(),operSn);
	}

	/**
	 * @return the operSn
	 */
	public String getOperSn() {
		return operSn;
	}

	/**
	 * @param operSn
	 *            the operSn to set
	 */
	public void setOperSn(String operSn) {
		this.operSn = operSn;
	}

	/**
	 * @return the operIndex
	 */
	public int getOperIndex() {
		return operIndex;
	}

	/**
	 * @param operIndex
	 *            the operIndex to set
	 */
	public void setOperIndex(int operIndex) {
		this.operIndex = operIndex;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the operName
	 */
	public String getOperName() {
		return operName;
	}

	/**
	 * @param operName
	 *            the operName to set
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}
}
