/**
 * 
 */
package com.aoeng.oa.web.action;

import org.apache.struts2.ServletActionContext;

import com.aoeng.oa.vo.LoginInfoVo;

/**
 * Nov 14, 20134:45:49 PM
 * 
 */
public class BaseAction
{
	/**
	 * 获得用户的登录信息
	 * 
	 * @return
	 */
	protected LoginInfoVo getCurrentUser() {
		System.out.println(ServletActionContext.getRequest().getQueryString());;
		return (LoginInfoVo) ServletActionContext.getRequest().getSession().getAttribute("login");
	}

}
