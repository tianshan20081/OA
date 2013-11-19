/**
 * 
 */
package com.aoeng.oa.web.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.aoeng.oa.service.AclService;
import com.aoeng.oa.vo.LoginInfoVo;

/**
 * Nov 14, 20134:45:49 PM
 * 
 */
public class BaseAction
{
	@Resource
	private AclService aclService;
	/**
	 * 获得用户的登录信息
	 * 
	 * @return
	 */
	protected LoginInfoVo getCurrentUser() {
		System.out.println(ServletActionContext.getRequest().getQueryString());;
		return (LoginInfoVo) ServletActionContext.getRequest().getSession().getAttribute("login");
	}
	
	public boolean permit(String resourceSn,String operSn){
		return aclService.hasPermission(getCurrentUser().getId(),resourceSn,operSn);
	}

}
