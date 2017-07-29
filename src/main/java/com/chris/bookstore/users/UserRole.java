/**
 * Chris Book Store.
 */


package com.chris.bookstore.users;

/**
 * @author chris
 *
 */
public class UserRole {
	private Long userroleid;	
	private Long userid;	
	private String role;
	/**
	 * @return the userroleid
	 */
	public Long getUserroleid() {
		return userroleid;
	}
	/**
	 * @param userroleid the userroleid to set
	 */
	public void setUserroleid(Long userroleid) {
		this.userroleid = userroleid;
	}
	/**
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}	
	
}
