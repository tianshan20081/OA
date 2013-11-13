/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.AclDao;
import com.aoeng.oa.model.ACL;
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
		// TODO Auto-generated method stub
		// return aclDao.findAclList(principalType, principalId, resourceType);
		List<AuthVo> vos = new ArrayList<AuthVo>();

		// 查询出指定类型的所有资源
		List<SysResource> resources = aclDao.findAllSysResources(resourceType);
		// 针对每个资源取出操作索引
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
		// TODO Auto-generated method stub
		//首先查询主体是否有这个资源对象
		ACL acl = aclDao.findACL(principalType, principalId, resourceType, resourceId);
		AuthVo vo = null ;
		if (null != acl && acl.isExtend(operIndex)) {
			vo = new AuthVo();
			vo.setResourceId(acl.getPrincipalId());
			vo.setPermit(acl.isPermit(operIndex));
			vo.setOperIndex(operIndex);
			vo.setPermit(acl.isExtend(operIndex));
			return vo ;
		}
		//如果沒有找到授權，則判断父主体上是否存在授权
		Principal principal = aclDao.findPrincipalById(principalType,principalId);
		List<Principal> parents = principal.getParentPrincipal();
		if (null != parents) {
			for (Principal p : parents) {
				//获得父主体 的 authVo 
				AuthVo pvo = searchAcl(p.getPrincipalType(), p.getPrincipalId(), resourceId, resourceType, operIndex);
				if (null != pvo) {
					vo = new AuthVo();
					vo.setPermit(pvo.isPermit());
					vo.setExtend(true);
					vo.setResourceId(resourceId);
					vo.setOperIndex(operIndex);
					
				}
			}
		}
		return vo;
	}

}
