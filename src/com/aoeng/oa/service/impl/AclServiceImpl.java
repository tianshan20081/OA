/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.security.acl.Acl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.AclDao;
import com.aoeng.oa.model.ACL;
import com.aoeng.oa.service.AclService;
import com.aoeng.oa.vo.AuthVo;

/**
 * Nov 7, 20133:37:31 PM
 * 
 */
@Service("aclService")
public class AclServiceImpl implements AclService {

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
				}else {
					acl.setPermission(operIndex, isPermit, isExtend);
					aclDao.update(acl);
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.AclService#findAclList(java.lang.String, int, java.lang.String)
	 */
	@Override
	public List<ACL> findAclList(String principalType, int principalId, String resourceType) {
		// TODO Auto-generated method stub
		return aclDao.findAclList(principalType,principalId,resourceType);
	}

}
