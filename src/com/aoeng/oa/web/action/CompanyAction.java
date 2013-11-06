/**
 * 
 */
package com.aoeng.oa.web.action;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.aoeng.oa.annotations.Oper;
import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.model.Company;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Oct 30, 2013 6:49:11 PM
 * 
 */
@Controller("companyAction")
@Scope("prototype")
@Res(name="公司操作",sn="company",orderNumber=2,parentSn="party" )
public class CompanyAction extends PartyAction implements ModelDriven {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (null == model) {
			model = new Company();
		}
		return model;
	}

	/**
	 * 返回设置公司基本信息的页面
	 * 
	 * @return
	 */
	@Oper(name="公司信息维护",index=4,sn="saveCompany")
	public String saveInput() {
		model = partyService.findCurrentCompany();
		return "company_input";
	}
	@Oper(name="公司信息维护",index=4,sn="saveCompany")
	public String save() {
		partyService.saveOrUpdateCompany((Company) model);
		return "add_success";
	}
}
