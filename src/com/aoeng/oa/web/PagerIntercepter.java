/**
 * 
 */
package com.aoeng.oa.web;

import org.apache.struts2.ServletActionContext;

import com.aoeng.oa.SystemContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Nov 1, 2013 11:37:46 AM
 *
 */
public class PagerIntercepter extends AbstractInterceptor {

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		//设置分页参数
		SystemContext.setOffSet(getOffSet());
		SystemContext.setPageSize(getPageSize());
		
		try {
			return invocation.invoke();
		} finally{
			SystemContext.removeOffSet();
			SystemContext.removePageSize();
		}
	}
	/**
	 * @return
	 */
	private int getPageSize() {
		// TODO Auto-generated method stub
		int pageSize = 10 ;
		try {
			pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("iDisplayLength"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			throw new RuntimeException(e.getMessage());
		}
		return pageSize;
	}
	private int getOffSet(){
		int offSet = 0;
		try {
			offSet = Integer.parseInt(ServletActionContext.getRequest().getParameter("iDisplayStart"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			throw new RuntimeException(e.getMessage());
		}
		return offSet ;
	}

}
