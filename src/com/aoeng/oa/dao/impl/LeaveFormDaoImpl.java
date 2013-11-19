/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aoeng.oa.dao.LeaveFormDao;

/**
 * Nov 19, 20133:06:42 PM
 * 
 */
@Repository("leaveFormDao")
public class LeaveFormDaoImpl extends BaseDaoImpl implements LeaveFormDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.LeaveFormDao#findLeavesByUserId(int)
	 */
	@Override
	public List findLeaveFormsByUserId(int userId) {
		// TODO Auto-generated method stub
		String hql = "select lf from LeaveFrom where lf.id = ?";
		return getSession().createQuery(hql).setParameter(0, userId).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.LeaveFormDao#findLeavingByUserId(int)
	 */
	@Override
	public List findLeavingFormsByApproverId(int approverId) {
		// TODO Auto-generated method stub
		String hql = "select lf from LeaveForm lf where lf.approver.id = ?";
		return getSession().createQuery(hql).setParameter(0, approverId).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.LeaveFormDao#findLeavedByUserId(int)
	 */
	@Override
	public List findLeavedFormsByApproverId(int approverId) {
		// TODO Auto-generated method stub
		String hql = "select lf from LeaveForm lf join ApproveInfo ai join ai.approver ar where  ar.id = ?";

		return getSession().createQuery(hql).setParameter(0, approverId).list();
	}

}
