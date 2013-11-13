/**
 * 
 */
package com.aoeng.oa.model;

/**
 * Oct 30, 2013 2:46:37 PM
 * 
 */
public class Department extends Party
{
	private String snumber;// 部门编号
	private String tel;// 部门电话

	/**
	 * @return the snumber
	 */
	public String getSnumber() {
		return snumber;
	}

	/**
	 * @param snumber
	 *            the snumber to set
	 */
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

}
