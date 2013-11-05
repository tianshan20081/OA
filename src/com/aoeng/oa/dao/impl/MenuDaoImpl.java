/**
 * 
 */
package com.aoeng.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aoeng.oa.dao.MenuDao;
import com.aoeng.oa.model.Menu;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 20135:23:45 PM
 * 
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.oa.dao.MenuDao#findAllTopMenus()
	 */
	@Override
	public List<Menu> findAllTopMenus() {
		// TODO Auto-generated method stub
		String hql = "select m from Menu m where m.parent is null ";

		return getSession().createQuery(hql).list();
	}

}
