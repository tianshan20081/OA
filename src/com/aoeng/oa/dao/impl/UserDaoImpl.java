/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aoeng.oa.dao.UserDao;
import com.aoeng.oa.model.Role;
import com.aoeng.oa.model.User;
import com.aoeng.oa.model.UserRoles;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 201310:33:26 AM
 * 
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.UserDao#findPersonUsers(java.lang.String)
	 */
	@Override
	public PagerVo findPersonUsers(String personName) {
		// TODO Auto-generated method stub
		String hql = "select p.id,p.name,pt.name,u.username from Person p left join p.parent pt left join p.user u where p.name like ?";
		return findPaging(hql, "%" + personName + "%");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.UserDao#update(com.aoeng.oa.model.User, int[])
	 */
	@Override
	public void update(User user, int[] roleIds) {
		// TODO Auto-generated method stub
		getSession().update(user);
		String hql = "select ur from UserRoles ur left join ur.user u where u.id = ?";
		List<UserRoles> roles = getSession().createQuery(hql).setParameter(0, user.getId()).list();
		// 删除旧的关联
		for (UserRoles ur : roles) {
			getSession().delete(ur);
		}
		// 建立新的关联
		if (roleIds != null) {
			for (int roleId : roleIds) {
				UserRoles ur = new UserRoles();
				ur.setUser(user);
				ur.setRole(findById(Role.class, roleId));
				getSession().save(ur);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.UserDao#findRoleIdsOfUser(int)
	 */
	@Override
	public List<Integer> findRoleIdsOfUser(int userId) {
		// TODO Auto-generated method stub
		String hql = "select r.id from UserRoles ur join ur.role r join ur.user u where u.id = ?";
		return getSession().createQuery(hql).setParameter(0, userId).list();
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.UserDao#findPersonWithUsers(java.lang.String)
	 */
	@Override
	public List findPersonWithUsers(String personName) {
		// TODO Auto-generated method stub
		String hql = "select p.id,p.name from Person p join p.user u where p.name like ?";
		return getSession().createQuery(hql).setParameter(0, "%"+personName+"%").list();
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.dao.UserDao#findUserByUsername(java.lang.String)
	 */
	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		String hql = "select u from User u where u.username = ? ";
		return (User) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
	}

}
