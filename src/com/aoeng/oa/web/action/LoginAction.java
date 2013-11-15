/**
 * 
 */
package com.aoeng.oa.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aoeng.oa.model.User;
import com.aoeng.oa.service.UserService;
import com.aoeng.oa.vo.LoginInfoVo;

/**
 * Nov 14, 201311:57:46 AM
 * 
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction
{
	@Resource
	private UserService userService;
	private String username;
	private String password;

	public String execute() {
		User user = userService.login(username, password);
		LoginInfoVo loginInfoVo = new LoginInfoVo();
		loginInfoVo.setId(user.getId());
		loginInfoVo.setUsername(user.getUsername());
		loginInfoVo.setName(user.getPerson().getName());
		loginInfoVo.setIp(ServletActionContext.getRequest().getRemoteHost());
		loginInfoVo.setLoginTime(new Date());

		ServletActionContext.getRequest().getSession().setAttribute("login", loginInfoVo);
		System.out.println(loginInfoVo.toString());
		return "back_index";
	}

	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
