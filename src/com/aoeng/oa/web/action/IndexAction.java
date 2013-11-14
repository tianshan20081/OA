/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.model.Menu;
import com.aoeng.oa.service.AclService;
import com.aoeng.oa.vo.LoginInfoVo;

/**
 * Nov 14, 20134:42:39 PM
 * 
 */
@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseAction
{
	@Resource
	private AclService aclService;

	public void menu() {
		// 验证是否已经登录

		LoginInfoVo userInfo = getCurrentUser();
		//取得用户的所有授权
		List<Menu> authMenus = aclService.findAllPermitMenusById(userInfo.getId());
	}

}
