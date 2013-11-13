/**
 * 
 */
package com.aoeng.oa.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Nov 4, 20137:16:59 PM
 * 
 */
public class User implements Principal
{
	private int id;
	private String username;
	private String password;
	private Person person;
	private Set<UserRoles> userRoles = new HashSet<UserRoles>();

	/**
	 * @return the userRoles
	 */
	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles
	 *            the userRoles to set
	 */
	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

}
