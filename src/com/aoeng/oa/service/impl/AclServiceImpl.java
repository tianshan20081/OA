/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.AclDao;
import com.aoeng.oa.dao.MenuDao;
import com.aoeng.oa.model.ACL;
import com.aoeng.oa.model.Menu;
import com.aoeng.oa.model.Principal;
import com.aoeng.oa.model.SysResource;
import com.aoeng.oa.service.AclService;
import com.aoeng.oa.vo.AuthVo;

/**
 * Nov 7, 20133:37:31 PM
 * 
 */
@Service("aclService")
public class AclServiceImpl implements AclService
{

	@Resource
	private AclDao aclDao;
	@Resource
	private MenuDao menuDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.AclService#addOrUpdatePermission(java.lang.String, java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public void addOrUpdatePermission(String principalType, int principalId, String resourceType, List<AuthVo> acls) {
		// TODO Auto-generated method stub
		// 先删除当前主题及资源类型的所有 acl 记录
		aclDao.delAcl(principalType, principalId, resourceType);
		if (acls != null) {
			for (AuthVo auth : acls) {
				int resourceId = auth.getResourceId();
				int operIndex = auth.getOperIndex();
				boolean isPermit = auth.isPermit();
				boolean isExtend = auth.isExtend();

				ACL acl = aclDao.findACL(principalType, principalId, resourceType, resourceId);
				if (acl == null) {
					acl = new ACL();
					acl.setPrincipalId(principalId);
					acl.setPrincipalType(principalType);
					acl.setResourceId(resourceId);
					acl.setResourceType(resourceType);
					acl.setPermission(operIndex, isPermit, isExtend);
					aclDao.save(acl);
				} else {
					acl.setPermission(operIndex, isPermit, isExtend);
					aclDao.update(acl);
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.AclService#findAclList(java.lang.String, int, java.lang.String)
	 */
	@Override
	public List<AuthVo> findAclList(String principalType, int principalId, String resourceType) {
		List<AuthVo> vos = new ArrayList<AuthVo>();

		List<SysResource> resources = aclDao.findAllSysResources(resourceType);

		for (SysResource r : resources) {
			int[] opers = r.getOperIndex();
			if (opers != null) {
				for (int operIndex : opers) {
					AuthVo vo = searchAcl(principalType, principalId, r.getResourceId(), resourceType, operIndex);
					if (vo != null) {
						vos.add(vo);
					}
				}
			}
		}

		return vos;
	}

	/**
	 * @param principalType
	 * @param principalId
	 * @param resourceId
	 * @param resourceType
	 * @param operIndex
	 * @return
	 */
	private AuthVo searchAcl(String principalType, int principalId, int resourceId, String resourceType, int operIndex) {
		ACL acl = aclDao.findACL(principalType, principalId, resourceType, resourceId);
		AuthVo vo = null;
		if (acl != null && !acl.isExtend(operIndex)) {
			vo = new AuthVo();
			vo.setResourceId(resourceId);
			vo.setPermit(acl.isPermit(operIndex));
			vo.setExtend(acl.isExtend(operIndex));
			vo.setOperIndex(operIndex);
			return vo;
		}
		Principal principal = aclDao.findPrincipalById(principalType, principalId);
		List<Principal> parents = principal.getParentPrincipal();

		if (parents != null) {
			for (Principal p : parents) {
				AuthVo pvo = searchAcl(p.getPrincipalType(), p.getPrincipalId(), resourceId, resourceType, operIndex);
				if (pvo != null) {
					vo = new AuthVo();
					vo.setResourceId(resourceId);
					vo.setOperIndex(operIndex);
					vo.setPermit(pvo.isPermit());
					vo.setExtend(true);
				}
			}

		}
		return vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.AclService#findAllPermitMenusById(int)
	 */
	@Override
	public List<Menu> findAllPermitMenusById(int userId) {
		// TODO Auto-generated method stub
		// 查询出所有的顶级菜单
		List<Menu> topMenus = menuDao.findAllTopMenus();
		removeDenyMenus(topMenus, userId);
		return topMenus;
	}

	/**
	 * 删除用户没有许可的菜单
	 * 
	 * @param topMenus
	 * @param userId
	 */
	private void removeDenyMenus(Collection<Menu> menus, int userId) {
		// TODO Auto-generated method stub
		for (Iterator<Menu> iter = menus.iterator();iter.hasNext();) {
			//查询针对当前用户所拥有的权限
			Menu menu = iter.next();
			AuthVo vo = searchAcl("User", userId, menu.getId(), "Menu", menu.getOperIndex()[0]);
			if (null == vo  || !vo.isPermit()) {
				iter.remove();
			}else {
				//如果当前菜单是许可的，则查询其子菜单是否是许可的
				removeDenyMenus(menu.getChildren(), userId);
			}
		}
	}

}
