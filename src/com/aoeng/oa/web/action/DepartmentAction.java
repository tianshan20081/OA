/**
 * 
 */
package com.aoeng.oa.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.model.Department;
import com.opensymphony.xwork2.ModelDriven;


/**
 * Nov 1, 2013 5:44:12 PM
 *
 */
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction  extends PartyAction implements ModelDriven{
	

	/* (non-Javadoc)
	 * @see com.aoeng.oa.web.action.PartyAction#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (model == null) {
			model = new Department();
		}
		return model;
	}
}
