/**
 * 
 */
package com.aoeng.oa.web.action;

import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.model.ACL;
import com.aoeng.oa.model.ActionResource;
import com.aoeng.oa.model.Menu;
import com.aoeng.oa.model.Person;
import com.aoeng.oa.model.Role;
import com.aoeng.oa.service.AclService;
import com.aoeng.oa.service.MenuService;
import com.aoeng.oa.service.ResourceService;
import com.aoeng.oa.service.RoleService;
import com.aoeng.oa.service.UserService;
import com.aoeng.oa.utils.JsonUtils;
import com.aoeng.oa.vo.AuthVo;
import com.aoeng.oa.vo.MenuTreeVo;
import com.opensymphony.xwork2.ActionContext;

/**
 * Nov 7, 201311:05:44 AM
 * 
 */
@Controller("aclAction")
@Scope("prototype")
public class AclAction extends BaseAction
{
	@Resource
	private RoleService roleService;
	@Resource
	private MenuService menuService;
	@Resource
	private AclService aclService;
	@Resource
	private ResourceService resourceService;
	@Resource
	private UserService userService;

	private int principalId;
	private String principalType;
	private int topMenuId;
	private List<AuthVo> authVos;
	private String sSearch;

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

	/**
	 * @return the authVos
	 */
	public List<AuthVo> getAuthVos() {
		return authVos;
	}

	/**
	 * @param authVos
	 *            the authVos to set
	 */
	public void setAuthVos(List<AuthVo> authVos) {
		this.authVos = authVos;
	}

	/**
	 * @return the principalType
	 */
	public String getPrincipalType() {
		return principalType;
	}

	/**
	 * @param principalType
	 *            the principalType to set
	 */
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	/**
	 * @return the principalId
	 */
	public int getPrincipalId() {
		return principalId;
	}

	/**
	 * @param principalId
	 *            the principalId to set
	 */
	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
	}

	/**
	 * @return the topMenuId
	 */
	public int getTopMenuId() {
		return topMenuId;
	}

	/**
	 * @param topMenuId
	 *            the topMenuId to set
	 */
	public void setTopMenuId(int topMenuId) {
		this.topMenuId = topMenuId;
	}

	/**
	 * 所有操作资源的主界面
	 * 
	 * @return
	 */
	public String allActionResource() {
		List<ActionResource> ress = resourceService.findAllActionResource();
		ActionContext.getContext().put("ress", ress);
		return "all_action_resource";
	}

	/**
	 * 打开角色授权的主界面
	 * 
	 * @return
	 */
	public String roleAuthIndex() {
		return "role_auth_index";
	}

	/**
	 * 显示角色授权树
	 */
	public void roleAuthIndexTree() {
		// 查找出所有的角色对象
		List<Role> roles = roleService.findAllRoles();
		// 建立角色树 的 vo 对象
		List roleTreeVos = new ArrayList();
		// 每一个角色创建一个 VO 对象
		for (Role r : roles) {
			Map roleTreeVo = new HashMap();
			roleTreeVo.put("data", r.getName());

			Map attr = new HashMap();
			attr.put("id", r.getId());
			attr.put("principalType", "Role");

			roleTreeVo.put("attr", attr);
			roleTreeVos.add(roleTreeVo);
		}

		JsonUtils.toJson(roleTreeVos);
	}

	/**
	 * 打开用户授权的主界面
	 * 
	 * @return
	 */
	public String userAuthIndex() {
		return "user_auth_index";
	}

	/**
	 * 显示用户授权树 只查询 分配了账户的 用户
	 */
	public void userAuthIndexTree() {
		List<Person> persons = userService.findPersonWithUsers(sSearch);
		Map map = new HashMap();
		map.put("aaData", persons);

		JsonUtils.toJson(map);
	}

	/**
	 * 打开部门/岗位授权的主界面
	 * 
	 * @return
	 */
	public String partyAuthIndex() {
		return "party_auth_index";
	}

	/**
	 * 显示部门/岗位授权树
	 */
	public void partyAuthIndexTree() {

	}

	/**
	 * 转到所有菜单授权页面
	 * 
	 * @return
	 */
	public String allMenuResource() {
		// 查询所有顶层菜单的 id
		List<Integer> topMenuIds = menuService.findAllTopMenuIds();
		ActionContext.getContext().put("topMenuIds", topMenuIds);
		return "all_menu_resource";
	}

	/**
	 * 查找所有菜单的树
	 */
	public void allMenuResourceTree() {
		// 根据顶级菜单ID 查询出顶级菜单
		Menu topMenu = menuService.findMenuById(topMenuId);
		MenuTreeVo vo = new MenuTreeVo(topMenu);
		JsonUtils.toJson(vo);
	}

	/**
	 * 给菜单授权
	 */
	public void authMenu() {
		aclService.addOrUpdatePermission(principalType, principalId, "Menu", authVos);
	}

	/**
	 * 给资源授权
	 */
	public void authActionResource() {

		aclService.addOrUpdatePermission(principalType, principalId, "ActionResource", authVos);
	}

	/**
	 * 把所有授权查询出来，并且显示在已有的菜单树当中
	 */
	public void findMenuAcls() {
		List<AuthVo> acls = aclService.findAclList(principalType, principalId, "Menu");
		JsonUtils.toJson(acls);
	}

	/**
	 * 把所有授权查询出来，并且显示在已有的菜单树当中
	 */
	public void findAllActionResourceAcls() {
		List<AuthVo> acls = aclService.findAclList(principalType, principalId, "ActionResource");
		JsonUtils.toJson(acls);
	}

}
