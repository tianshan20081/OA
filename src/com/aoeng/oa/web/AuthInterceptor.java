/**
 * 
 */
package com.aoeng.oa.web;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.aoeng.oa.model.ActionResource;
import com.aoeng.oa.service.AclService;
import com.aoeng.oa.service.ResourceService;
import com.aoeng.oa.vo.LoginInfoVo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Nov 19, 201311:19:00 AM
 * 
 */
public class AuthInterceptor extends AbstractInterceptor
{
	
	@Resource
	private ResourceService resourceService;
	@Resource
	private AclService aclService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// 取得要調用的類名

		String className = invocation.getProxy().getAction().getClass().getName();
		
		//根据类名 查找 Action Resource 对象
		ActionResource ar =resourceService.findActionResourceByClassName(className); 
		//如果这个 Action 不是一种资源，则标识塔无需权限控制，直接向下流转
		if (ar == null) {
			return invocation.invoke();
		}
		//如果没有流转，找到资源，得到操作方法
		String methodName = invocation.getProxy().getMethod();
		//根据方法名可以得到操作的唯一标识
		String operSn = ar.getOperSnByMethodName(methodName);
		//没找到操作标识，说明方法没有定义操作，无需受到权限控制
		if (operSn == null) {
			return invocation.invoke();
		}
		//查找主体
		int userId = ((LoginInfoVo)ServletActionContext.getRequest().getSession().getAttribute("login")).getId();
		//判断是否允许当前登录用户执行本资源操作
		boolean permit = aclService.hasPermission(userId, ar.getSn(), operSn);
		//判断如果 允许，则继续向下
		if (permit) {
			return invocation.invoke();
		}
		throw new RuntimeException("您无权执行本操作【resourceSn = "+ar.getSn()+",operSn = "+operSn +"】请联系系统管理员!!!");
	}

}
