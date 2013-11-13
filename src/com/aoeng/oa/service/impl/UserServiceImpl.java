/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.RoleDao;
import com.aoeng.oa.dao.UserDao;
import com.aoeng.oa.model.Role;
import com.aoeng.oa.model.User;
import com.aoeng.oa.model.UserRoles;
import com.aoeng.oa.service.UserService;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 20139:40:56 AM
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#findPersonUsers(java.lang.String)
	 */
	@Override
	public PagerVo findPersonUsers(String personName) {
		// TODO Auto-generated method stub
		return userDao.findPersonUsers(personName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#addUser(com.aoeng.oa.model.User, int)
	 */
	@Override
	public void addUser(User user, int[] roleIds) {
		// TODO Auto-generated method stub
		userDao.save(user);

		if (roleIds != null) {
			// 建立用户和角色之间的关联
			for (int roleId : roleIds) {
				UserRoles ur = new UserRoles();
				ur.setUser(user);
				ur.setRole(roleDao.findById(Role.class, roleId));
				userDao.save(ur);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#addUser(com.aoeng.oa.model.User)
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#delUser(int)
	 */
	@Override
	public void delUser(int id) {
		// TODO Auto-generated method stub
		userDao.del(findUserById(id));
	}

	/**
	 * @param id
	 * @return
	 */
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(User.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#updateUser(com.aoeng.oa.model.User, int)
	 */
	@Override
	public void updateUser(User user, int[] roleIds) {
		// TODO Auto-generated method stub
		userDao.update(user, roleIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#updateUser(com.aoeng.oa.model.User)
	 */
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#findById(int)
	 */
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(User.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.service.UserService#findRoleIdsOfUser(int)
	 */
	@Override
	public List<Integer> findRoleIdsOfUser(int id) {
		// TODO Auto-generated method stub
		return userDao.findRoleIdsOfUser(id);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.UserService#findPersonWithUsers(java.lang.String)
	 */
	@Override
	public List findPersonWithUsers(String sSearch) {
		// TODO Auto-generated method stub
		return userDao.findPersonWithUsers(sSearch);
	}

}
