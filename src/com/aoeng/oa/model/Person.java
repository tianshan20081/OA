/**
 * 
 */
package com.aoeng.oa.model;

/**
 * Oct 30, 2013 2:25:11 PM
 *
 */
public class Person extends Party{
	/**
	 * 員工編號
	 */
	private String snumber ;
	/**
	 * 員工地址
	 */
	private String address ;
	/**
	 * 員工QQ
	 */
	private String qq ;
	/**
	 * 員工MSN
	 */
	private String msn ;
	/**
	 * 員工职责
	 */
	private String duty ;
	/**
	 * 員工电话号码
	 */
	private String phone ;
	/**
	 * 員工性别
	 */
	private String sex ;
	/**
	 * 員工邮箱
	 */
	private String email ;
	
	
	
	/**
	 * @return the snumber
	 */
	public String getSnumber() {
		return snumber;
	}
	/**
	 * @param snumber the snumber to set
	 */
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * @return the msn
	 */
	public String getMsn() {
		return msn;
	}
	/**
	 * @param msn the msn to set
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}
	/**
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [snumber=" + snumber + ", address=" + address + ", qq=" + qq + ", msn=" + msn + ", duty=" + duty + ", phone=" + phone + ", sex=" + sex + ", email=" + email + "]";
	}
	
}
