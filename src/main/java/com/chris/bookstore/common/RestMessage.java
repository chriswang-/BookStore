/**
 * Chris Book Store.
 */


package com.chris.bookstore.common;

/**
 * @author chris
 *
 */
public class RestMessage {
	
	public static final String OK = "OK";
	public static final String ERR = "ERROR";
	public static final String USAGE = "USAGE";

	private String status;
	private String message;
	private Object object;
	
	public RestMessage(String status, String message) {
		this.status = status;
		this.message = message;
	}
	public RestMessage(String status, String message, Object obj) {
		this.status = status;
		this.message = message;
		this.object = obj;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

}
