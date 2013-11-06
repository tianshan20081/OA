/**
 * 
 */
package com.aoeng.oa.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aoeng.oa.annotations.Res;
import com.aoeng.oa.model.Department;
import com.aoeng.oa.model.Position;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Nov 4, 2013 2:23:28 PM
 *
 */
@Service("positionAction")
@Scope("prototype")
@Res(name="岗位操作",sn="position",parentSn="party",orderNumber=4)
public class PositionAction extends PartyAction implements ModelDriven {
	/* (non-Javadoc)
	 * @see com.aoeng.oa.web.action.PartyAction#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		if (model == null) {
			model = new Position();
		}
		return model;
	}
}
