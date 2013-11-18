/**
 * 
 */
package com.aoeng.oa.web;

import org.apache.struts2.ServletActionContext;

import com.aoeng.oa.vo.LoginInfoVo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Nov 18, 20137:55:29 PM
 * 
 */
public class LoginInterceptor extends AbstractInterceptor
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub

		LoginInfoVo loginInfoVo = (LoginInfoVo) ServletActionContext.getRequest().getSession().getAttribute("login");
		if (null == loginInfoVo) {
			return "login";
		}
		
		// 转向下一个 拦截器
		return invocation.invoke();
	}

}
