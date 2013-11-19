/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import sun.rmi.server.UnicastRef;

import com.aoeng.oa.dao.ResourceDao;
import com.aoeng.oa.model.ActionMethodOper;
import com.aoeng.oa.model.ActionResource;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 6, 20132:03:50 PM
 * 
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl implements ResourceDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.ResourceDao#findActionResourceBySn(java.lang.String)
	 */
	@Override
	public ActionResource findActionResourceBySn(String sn) {
		// TODO Auto-generated method stub
		String hql = "select ar from ActionResource ar where ar.sn = ? ";
		return (ActionResource) getSession().createQuery(hql).setParameter(0, sn).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.ResourceDao#findAllTopActionResources()
	 */
	@Override
	public List<ActionResource> findAllTopActionResources() {
		// TODO Auto-generated method stub
		String hql = "select r from ActionResource r where r.parent is null  order by r.orderNumber ";
		return getSession().createQuery(hql).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.ResourceDao#update(com.aoeng.oa.model.ActionResource)
	 */
	@Override
	public void update(ActionResource ar) {
		// TODO Auto-generated method stub
		ActionResource old = (ActionResource) getSession().load(ActionResource.class, ar.getId());
		ar.setOpers(old.getOpers());
		// 注意此处不能用 update
		getSession().merge(ar);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.ResourceDao#findAllActionResource()
	 */
	@Override
	public List<ActionResource> findAllActionResource() {
		// TODO Auto-generated method stub
		String hrl = "select ar from ActionResource ar order by orderNumber ";
		return getSession().createQuery(hrl).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.ResourceDao#findActionResourceByClassName(java.lang.String)
	 */
	@Override
	public ActionResource findActionResourceByClassName(String className) {
		// TODO Auto-generated method stub
		String hql = "select ar from ActionResource ar where ar.className like ?";
		return (ActionResource) getSession().createQuery(hql).setParameter(0, "%" + className + "%").uniqueResult();
	}

}
