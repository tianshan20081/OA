/**
 * 
 */
package com.aoeng.oa.web.action;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.aoeng.oa.model.Company;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Oct 30, 2013 6:49:11 PM
 * 
 */
@Controller("companyAction")
@Scope("prototype")
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
	public String saveInput() {
		model = partyService.findCurrentCompany();
		return "company_input";
	}

	public String save() {
		partyService.saveOrUpdateCompany((Company) model);
		return "add_success";
	}

	public void ajax() {
		System.out.println("this is from ajax");
		try {
			String s = "{\"name\":\"张三\",\"age\":\"20\",\"job\":\"It\"}";
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// public void tree(){
	// try {
	// Node node = new Node("首信易支付",1,"www.beijing.com.cn");
	// Node node2 = new Node("财务部",2,"www.payeasenet.cn");
	// node2.addChildNode(new Node("经济财务",4,"www.baidu.com"));
	// node.addChildNode(node2);
	// node.addChildNode(new Node("市场部", 3,"www.beijing1.com.cn"));
	//
	// String jsonTree = JSONMapper.toJSON(node).render(false) ;
	// System.out.println(jsonTree);
	//
	// ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
	// ServletActionContext.getResponse().getWriter().print(jsonTree);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
