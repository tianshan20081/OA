/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.annotations.Oper;
import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.model.Role;
import com.aoeng.oa.service.RoleService;
import com.aoeng.oa.utils.JsonUtils;
import com.aoeng.oa.vo.PagerVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Nov 5, 201311:18:57 AM
 * 
 */
@Controller("roleAction")
@Scope("prototype")
@Res(name = "角色操作", sn = "role", parentSn = "security", orderNumber = 80)
public class RoleAction extends BaseAction implements ModelDriven
{
	private Role model;

	private String sSearch;

	@Resource
	private RoleService roleService;

	/**
	 * @return the sSearch
	 */
	public String getSSearch() {
		return sSearch;
	}

	/**
	 * @param sSearch
	 *            the sSearch to set
	 */
	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
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
			model = new Role();
		}
		return model;
	}

	/**
	 * 分页查询角色的信息
	 * 
	 * @return
	 */
	public void list() {
		PagerVo vo = roleService.findAllRoles(sSearch);
		Map map = new HashMap();
		map.put("aaData", vo.getDatas());
		map.put("iTotalRecords", vo.getDatas().size());
		map.put("iTotalDisplayRecords", vo.getTotal());

		JsonUtils.toJson(map);
	}

	@Oper
	public String execute() {
		return "index";
	}

	@Oper
	public String addInput() {
		return "add_input";
	}

	@Oper
	public void del() {
		roleService.delRoleById(model.getId());
	}

	@Oper
	public String add() {
		roleService.addRole(model);
		return "add_success";
	}

	@Oper
	public String updateInput() {
		model = roleService.findRoleById(model.getId());
		return "update_input";
	}

	@Oper
	public String update() {
		roleService.updateRole(model);
		return "update_success";
	}

}
