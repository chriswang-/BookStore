/**
 * Chris Book Store.
 */


package com.chris.bookstore.users;

/**
 * @author chris
 *
 */
public class User {
    private Long userId;
    private String userName;   
    private String password;   
    private String email;    
	private int enabled;
	
	public User(User user) {
		userId = user.getUserId();
		userName = user.getUserName();
		password = user.getPassword();
	}
	public User(Long id, String name, String pwd) {
		userId = id;
		userName = name;
		password = pwd;
	}
	
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
}
