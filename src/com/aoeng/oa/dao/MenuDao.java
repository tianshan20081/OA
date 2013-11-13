/**
 * 
 */
package com.aoeng.oa.dao;

import java.util.List;

import com.aoeng.oa.model.Menu;

/**
 * Nov 5, 20135:23:16 PM
 * 
 */
public interface MenuDao extends BaseDao
{

	/**
	 * @return
	 */
	List<Menu> findAllTopMenus();

	/**
	 * @return
	 */
	List<Integer> findAllTopMenuIds();

}
