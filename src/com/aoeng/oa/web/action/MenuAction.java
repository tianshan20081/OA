/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.model.Menu;
import com.aoeng.oa.service.MenuService;
import com.aoeng.oa.utils.JsonUtils;
import com.aoeng.oa.vo.MenuTreeVo;
import com.aoeng.oa.vo.PagerVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Nov 5, 20135:16:07 PM
 * 
 */
@Controller("menuAction")
@Scope("prototype")
public class MenuAction implements ModelDriven {
	private Menu model;
	private String sSearch;
	@Resource
	private MenuService menuService;

	/**
	 * @return the sSearch
	 */
	public String getSSearch() {
		return sSearch;
	}

	/**
	 * @param sSearch
	 *            the sSearch to set
	 */
	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (null == model) {
			model = new Menu();
		}
		return model;
	}

	/**
	 * 生成菜單設置主界面
	 */
	public void tree() {
		List<Menu> menus = menuService.findAllTopMenus();
		List<MenuTreeVo> vos = new ArrayList<MenuTreeVo>();
		for (Menu menu : menus) {
			vos.add(new MenuTreeVo(menu));
		}
		JsonUtils.toJson(vos);
	}

	public String execute() {
		return "index";
	}

	public String addInput() {
		int parentId = model.getParent().getId();
		if (parentId == 0) {
			throw new RuntimeException("未知父节点，无法创建子节点");
		}
		return "add_input";
	}

	public String add() {
		menuService.addMenu(model);
		return "add_success";
	}

	public String updateInput() {
		model = menuService.findMenuById(model.getId());
		return "update_input";
	}

	public String update() {
		menuService.updateMenu(model);
		return "update_success";
	}

	public String del() {
		menuService.delMenuById(model.getId());
		return "del_success";
	}

}
