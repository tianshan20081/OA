/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.model.Person;
import com.aoeng.oa.utils.JsonUtils;
import com.aoeng.oa.vo.PagerVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Oct 31, 2013 7:30:38 PM
 *
 */
@Controller("personAction")
@Scope("prototype")
@Res(name="员工操作",sn="person",orderNumber=5,parentSn="party")
public class PersonAction extends PartyAction implements ModelDriven {
	private String sSearch ;
	

	/**
	 * @return the sSearch
	 */
	public String getSSearch() {
		return sSearch;
	}
	/**
	 * @param sSearch the sSearch to set
	 */
	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (null == model) {
			model = new Person();
		}
		return model;
	}
	/* (non-Javadoc)
	 * @see com.aoeng.oa.web.action.PartyAction#execute()
	 */
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		
		return "person_list";
	}
	/*
	 * 分页查询人员
	 * 根据ID来查询人员，可能是  公司人员，部门人员，岗位人员
	 */
	public void list(){
		int parentId = model.getId();
		PagerVo pagerVo = partyService.findPersons(parentId,sSearch);
		Map map = new HashMap();
		map.put("aaData", pagerVo.getDatas());
		map.put("iTotalRecords", pagerVo.getDatas().size());
		map.put("iTotalDisplayRecords", pagerVo.getTotal());
		
		JsonUtils.toJson(map);
	}

}
