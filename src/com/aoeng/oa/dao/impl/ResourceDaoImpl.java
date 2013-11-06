/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import sun.rmi.server.UnicastRef;

import com.aoeng.oa.dao.ResourceDao;
import com.aoeng.oa.model.ActionResource;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 6, 20132:03:50 PM
 *
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl implements ResourceDao {

	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.ResourceDao#findActionResourceBySn(java.lang.String)
	 */
	@Override
	public ActionResource findActionResourceBySn(String sn) {
		// TODO Auto-generated method stub
		String hql = "select ar from ActionResource ar where ar.sn = ? ";
		return (ActionResource) getSession().createQuery(hql).setParameter(0, sn).uniqueResult();
	}


}
