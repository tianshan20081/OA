/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.util.List;

import com.aoeng.oa.dao.InitDao;
import com.aoeng.oa.model.ACL;
import com.aoeng.oa.model.Person;
import com.aoeng.oa.model.Principal;
import com.aoeng.oa.model.Role;
import com.aoeng.oa.model.SysResource;
import com.aoeng.oa.model.User;
import com.aoeng.oa.model.UserRoles;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 14, 20135:07:31 PM
 * 
 */
public class InitDaoImpl extends BaseDaoImpl implements InitDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.InitDao#addInitAdmin()
	 */
	@Override
	public void addInitAdmin() {
		// TODO Auto-generated method stub
		getSession().flush();

		Person admin = new Person();
		admin.setName("超级管理员");
		User adminUser = new User();
		adminUser.setUsername("admin");
		adminUser.setPassword("admin");
		admin.setUser(adminUser);

		getSession().save(admin);
		getSession().save(adminUser);

		// 创建系统管理员角色
		Role adminRole = new Role();
		adminRole.setName("系统管理员");
		getSession().save(adminRole);

		// 创建普通员工
		Role commonRole = new Role();
		commonRole.setName("普通员工");
		getSession().save(commonRole);

		UserRoles userRoles = new UserRoles();
		userRoles.setRole(adminRole);
		userRoles.setUser(adminUser);
		getSession().save(userRoles);

		UserRoles comonRoles = new UserRoles();
		comonRoles.setRole(commonRole);
		comonRoles.setUser(adminUser);
		getSession().save(comonRoles);

		// 查询系统管理的菜单,安全相关的菜单，组织机构
		String hql = " select r from com.aoeng.oa.model.SysResource r where r.sn in ('system','security','party')";
		
		List<SysResource> resources = getSession().createQuery(hql).list();
		for (SysResource sr : resources) {
			saveAllPermitAcl(adminRole,sr);
		}
		
	}

	/**
	 * @param adminRole
	 * @param sr
	 */
	private void saveAllPermitAcl(Principal principal, SysResource r) {
		// TODO Auto-generated method stub
		ACL acl = new ACL();
		acl.setPrincipalId(principal.getPrincipalId());
		acl.setResourceId(r.getResourceId());
		acl.setPrincipalType(r.getResourceType());
		acl.setResourceType(r.getResourceType());
		acl.setAclState(-1);
		acl.setAclTriState(0);//授权不继承
		getSession().save(acl);
		
		List<SysResource> children = r.getChildrenResource();
		if (children != null) {
			for (SysResource s : children) {
				saveAllPermitAcl(principal, s);
			}
		}
		
	}

}
