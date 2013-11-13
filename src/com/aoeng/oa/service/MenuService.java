/**
 * 
 */
package com.aoeng.oa.service;

import java.util.List;

import com.aoeng.oa.model.Menu;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 20135:20:25 PM
 * 
 */
public interface MenuService
{

	/**
	 * 根据id 查找 菜单
	 * 
	 * @param menuId
	 * @return
	 */
	Menu findMenuById(int menuId);

	/**
	 * 根據 id 刪除 菜單
	 * 
	 * @param id
	 */
	void delMenuById(int menuId);

	/**
	 * 查找所有頂級菜單
	 * 
	 * @return
	 */
	List<Menu> findAllTopMenus();

	/**
	 * 更新菜单
	 * 
	 * @param model
	 */
	void updateMenu(Menu menu);

	/**
	 * 添加 菜单
	 * 
	 * @param model
	 */
	void addMenu(Menu menu);

	/**
	 * @return
	 */
	List<Integer> findAllTopMenuIds();

}
