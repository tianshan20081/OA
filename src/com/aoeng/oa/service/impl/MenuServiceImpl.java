/**
 * 
 */
package com.aoeng.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoeng.oa.dao.MenuDao;
import com.aoeng.oa.model.Menu;
import com.aoeng.oa.service.MenuService;
import com.aoeng.oa.vo.PagerVo;

/**
 * Nov 5, 20135:20:37 PM
 *
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuDao menuDao ;


	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.MenuService#save(com.aoeng.oa.model.Menu)
	 */
	@Override
	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		menuDao.save(menu);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.MenuService#findMenuById(int)
	 */
	@Override
	public Menu findMenuById(int menuId) {
		// TODO Auto-generated method stub
		return menuDao.findById(Menu.class, menuId);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.MenuService#update(com.aoeng.oa.model.Menu)
	 */
	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menuDao.update(menu);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.MenuService#delMenuById(int)
	 */
	@Override
	public void delMenuById(int menuId) {
		// TODO Auto-generated method stub
		menuDao.del(findMenuById(menuId));
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.MenuService#findAllTopMenus()
	 */
	@Override
	public List<Menu> findAllTopMenus() {
		// TODO Auto-generated method stub
		return menuDao.findAllTopMenus();
	}

	/* (non-Javadoc)
	 * @see com.aoeng.oa.service.MenuService#findAllTopMenuIds()
	 */
	@Override
	public List<Integer> findAllTopMenuIds() {
		// TODO Auto-generated method stub
		return menuDao.findAllTopMenuIds();
	}


}
