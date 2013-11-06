/**
 * 
 */
package com.aoeng.oa.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.annotations.Oper;
import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.model.Company;
import com.aoeng.oa.model.Party;
import com.aoeng.oa.service.PartyService;
import com.aoeng.oa.utils.JsonUtils;
import com.aoeng.oa.vo.PartyTreeVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Oct 31, 2013 2:44:15 PM
 *
 */
@Controller("partyAction")
@Scope("prototype")
@Res(name="组织机构操作",sn="party")
public class PartyAction implements ModelDriven {
	
	protected Party model;
	@Resource
	protected PartyService partyService ;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (null == model) {
			model = new Party();
		}
		return model;
	}
	/**
	 * 打开部门、岗位设置得主界面
	 * @return
	 */
	@Oper
	public String execute(){
		
		return "index";
	}
	//生成主界面上的 属性结构
	public void tree(){
		//查找顶层的公司
		Company c = partyService.findCurrentCompany();
		PartyTreeVo partyTreeVo = new PartyTreeVo(c);
		JsonUtils.toJson(partyTreeVo);
	}
	@Oper
	public String updateInput(){
		model = partyService.findPartyById(model.getId());
		System.out.println(model.toString());
		return "update_input";
	}
	@Oper
	public String update(){
		System.out.println(model.getId());
		partyService.updateParty(model);
		return "update_success";
	}
	@Oper
	public String del(){
		partyService.delParty(model.getId());
		return "del_success";
	}
	@Oper
	public String addInput(){
		//添加的 一定是 子 party,页面传过来的有 parentId
		int parentId = model.getParent().getId();
		if (parentId == 0 ) {
			throw new RuntimeException("未知父节点,无法创建自节点");
		}
		return "add_input";
	}
	@Oper
	public String add(){
		partyService.addParty(model);
		return "add_success";
	}

}
