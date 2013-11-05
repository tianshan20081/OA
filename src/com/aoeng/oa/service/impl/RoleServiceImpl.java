/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.RoleDao;
import com.aoeng.oa.dao.impl.BaseDaoImpl;
import com.aoeng.oa.model.Role;
import com.aoeng.oa.service.RoleService;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 201311:10:15 AM
 * 
 */
@Service("roleService")
public class RoleServiceImpl extends BaseDaoImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.RoleService#findAllRoles()
	 */
	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		return roleDao.findAll(Role.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.RoleService#findAllRoles(java.lang.String)
	 */
	@Override
	public PagerVo findAllRoles(String query) {
		// TODO Auto-generated method stub
		if (null == query || "".equals(query)) {
			return roleDao.findPaging("select r.id,r.name from Role r ");
		} else {
			return roleDao.findPaging("select r.id,r.name from Role r where r.name like ?" + "'%" + query + "%'");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.RoleService#addRole(com.aoeng.oa.model.Role)
	 */
	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.RoleService#delRoleById(int)
	 */
	@Override
	public void delRoleById(int roleId) {
		// TODO Auto-generated method stub
		roleDao.del(findById(Role.class, roleId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.RoleService#findRoleById(int)
	 */
	@Override
	public Role findRoleById(int roleId) {
		// TODO Auto-generated method stub
		return roleDao.findById(Role.class, roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.RoleService#updateRole(com.aoeng.oa.model.Role)
	 */
	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

}
