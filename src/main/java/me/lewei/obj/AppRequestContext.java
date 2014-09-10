package me.lewei.obj;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class AppRequestContext implements Serializable {

	public static final String CURRENT_REQUEST_URI = "CURRENT_REQUEST_URI";
	public static final String CURRENT_REQUEST_IP = "CURRENT_REQUEST_IP";	
	public static final String CURRENT_CONTEXT_PATH = "CURRENT_CONTEXT_PATH";
	public static final String LOGIN_USER_ID = "LOGIN_USER_ID";
	public static final String LOGIN_USER_NAME = "LOGIN_USER_NAME";
	public static final String LAST_LOGIN_DATE = "LAST_LOGIN_DATE";
	public static final String SORT_BY = "pagination_sort_by";
	public static final String SORT_TYPE = "pagination_sort_type";
	
	public static final String TRANSACTION_ID = "TRANSACTION_ID";
	public static final String SESSION_ID = "SESSION_ID";
	
	public static final String PU_PROFILE = "PU_PROFILE";
	
	public static final String LOGIN_USER_DEPARTMENT = "LOGIN_USER_DEPARTMENT";
	
	public static final String REQUEST_MODULE="REQUEST_MODULE";

	private HashMap<String, Object> map = null;
	
	public static String ELEARNING_HTTPPATH ="elearning_httpPath";
	

	public AppRequestContext() {
		map = new HashMap<String, Object>();
	}

	/**
	 * Stores an attribute in this CafContext. null values are ignored.
	 * 
	 * @param key
	 *            the attribute key
	 * @param value
	 *            the Object to be stored
	 */
	public void setAttribute(String key, Object value) {
		map.put(key, value);
	}
	public void removeAttribute(String key) {
		map.remove(key);
	}

	public Object getAttribute(String key) {
		return map.get(key);
	}

	public String getLoginUserId() {
		return (String) getAttribute(LOGIN_USER_ID);
	}

	public String getLoginUserName() {
		return (String) getAttribute(LOGIN_USER_NAME);
	}

	public String getRequestURI() {
		return (String) getAttribute(CURRENT_REQUEST_URI);
	}

	/**
	 * Get the current request IP stored in this container.
	 * 
	 * @return the current request IP.
	 */

	public String getRequestIP() {
		return (String) getAttribute(CURRENT_REQUEST_IP);
	}

	/**
	 * Get the current context path stored in this container.
	 * 
	 * @return the current context path.
	 */

	public String getContextPath() {
		return (String) getAttribute(CURRENT_CONTEXT_PATH);
	}

	public static String getLastLoginDate() {
		return LAST_LOGIN_DATE;
	}

	public String getSortBy(){
		return (String) getAttribute(SORT_BY);
	}

	public String getSortType(){
		return (String) getAttribute(SORT_TYPE);
	}
	public String getTransId(){
		return (String) getAttribute(TRANSACTION_ID);
	}
	public String getSessionId(){
		return (String) getAttribute(SESSION_ID);
	}
	
	public Object getPuProfile(){
		return  getAttribute(PU_PROFILE);
		
	}
	public String getLoginUserDepartment(){
		return  (String)getAttribute(LOGIN_USER_DEPARTMENT);
		
	}
	public String getRequestModule(){
		return  (String)getAttribute(REQUEST_MODULE);
		
	}
}
