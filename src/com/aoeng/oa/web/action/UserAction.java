/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.model.Role;
import com.aoeng.oa.model.User;
import com.aoeng.oa.service.RoleService;
import com.aoeng.oa.service.UserService;
import com.aoeng.oa.utils.JsonUtils;
import com.aoeng.oa.vo.PagerVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Nov 5, 20138:53:47 AM
 * 
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction implements ModelDriven {
	private User model;
	private String sSearch;
	private int[] roleIds;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;


	/**
	 * @return the roleIds
	 */
	public int[] getRoleIds() {
		return roleIds;
	}

	/**
	 * @param roleIds the roleIds to set
	 */
	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return the sSearch
	 */
	public String getSSearch() {
		return sSearch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (null == model) {
			model = new User();
		}
		return model;
	}

	public String execute() {
		return "index";
	}

	public void list() {
		PagerVo vo = userService.findPersonUsers(sSearch);
		Map map = new HashMap();
		map.put("aaData", vo.getDatas());
		map.put("iTotalRecords", vo.getDatas().size());
		map.put("iTotalDisplayRecords", vo.getTotal());

		JsonUtils.toJson(map);
	}

	public String addInput() {
		List<Role> roles = roleService.findAllRoles();
		ActionContext.getContext().put("roles", roles);
		return "add_input";
	}

	public String add() {
		userService.addUser(model, roleIds);
		return "add_success";
	}

	/**
	 * 删除人员之后还在本页面，故不许要返回
	 */
	public void del() {
		userService.delUser(model.getId());
		// return "del_success";
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 */
	public String updateInput() {
		// 查询所用的角色
		List<Role> roles = roleService.findAllRoles();
		// 查询用户已经被赋予的角色 Id
		List<Integer> selRolses = userService.findRoleIdsOfUser(model.getId());
		ActionContext.getContext().put("roles", roles);
		ActionContext.getContext().put("selRoles", selRolses);
		return "update_input";
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 */
	public String update() {
		userService.updateUser(model, roleIds);
		return "update_success";
	}

	/**
	 * 判断 role 是否已经是用户的角色，如果是 返回 “selected” 字符串
	 * 
	 * @param roleId
	 * @param selRoles
	 * @return
	 */
	public String hasSelected(int roleId, List<Integer> selRoles) {
		if (selRoles == null || selRoles.size() == 0) {
			return "";
		}
		for (Integer i : selRoles) {
			if (i == roleId) {
				return "selected";
			}
		}
		return "";
	}
}
