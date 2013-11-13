/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.security.acl.Acl;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aoeng.oa.dao.AclDao;
import com.aoeng.oa.model.ACL;
import com.aoeng.oa.model.Principal;
import com.aoeng.oa.model.SysResource;
import com.aoeng.oa.vo.AuthVo;

/**
 * Nov 7, 20133:41:39 PM
 * 
 */
@Repository("aclDao")
public class AclDaoImpl extends BaseDaoImpl implements AclDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.AclDao#delAcl(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void delAcl(String principalType, int principalId, String resourceType) {
		// TODO Auto-generated method stub
		// 先查找出 所有 acl 记录
		String hql = "select ac from ACL ac where ac.principalType = ? and ac.principalId = ? and ac.resourceType = ? ";
		Iterator<ACL> acls = getSession().createQuery(hql).setParameter(0, principalType).setParameter(1, principalId)
				.setParameter(2, resourceType).iterate();
		// 删除所有的 acl 记录
		while (acls.hasNext()) {
			getSession().delete(acls.next());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.AclDao#findACL(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public ACL findACL(String principalType, int principalId, String resourceType, int resourceId) {
		// TODO Auto-generated method stub
		String hql = "select ac from ACL ac where ac.principalType = ? and ac.principalId = ? and ac.resourceType = ? and ac.resourceId = ?";
		return (ACL) getSession().createQuery(hql).setParameter(0, principalType).setParameter(1, principalId)
				.setParameter(2, resourceType).setParameter(3, resourceId).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.AclDao#findAclList(java.lang.String, int, java.lang.String)
	 */
	@Override
	public List<ACL> findAclList(String principalType, int principalId, String resourceType) {
		String hql = "select ac from ACL ac where ac.principalType = ? and ac.principalId = ? and ac.resourceType = ? ";
		return getSession().createQuery(hql).setParameter(0, principalType).setParameter(1, principalId)
				.setParameter(2, resourceType).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.AclDao#findAllSysResources(java.lang.String)
	 */
	@Override
	public List<SysResource> findAllSysResources(String resourceType) {
		// TODO Auto-generated method stub
		String hql = " from " + resourceType;
		return getSession().createQuery(hql).list();
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.AclDao#findPrincipalById(java.lang.String, int)
	 */
	@Override
	public Principal findPrincipalById(String principalType, int principalId) {
		// TODO Auto-generated method stub
		String hql = "from " + principalType +" t where t.id = ?";
		return (Principal) getSession().createQuery(hql).setParameter(0, principalId).uniqueResult();
	}

}
