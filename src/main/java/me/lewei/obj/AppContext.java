package me.lewei.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppContext {

	public static final int SUCCESS = 0;
	public static final int FAILURE = -9;

	public static final String KEY_MAP = "key_map";
	public static final String KEY_LIST = "key_map";

	private long code = 0;
	private String messae = null;
	private Object obj = null;
	private Map<String, Object> contextMap = new HashMap<String, Object>();
	private List<?> contextList = new ArrayList<Object>();

	public AppContext() {
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getMessae() {
		return messae;
	}

	public void setMessae(String messae) {
		this.messae = messae;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<?> getContextList() {
		return contextList;
	}

	public void setContextList(List<?> contextList) {
		this.contextList = contextList;
	}

	public Map<String, Object> getContextMap() {
		return contextMap;
	}

	public void setContextMap(Map<String, Object> contextMap) {
		this.contextMap = contextMap;
	}

	public void putValue(String key, Object value) {
		contextMap.put(key, value);
	}

	public void removeValue(String key) {
		contextMap.remove(key);
	}

	public Object getValue(String key) {
		return contextMap.get(key);
	}

	public String getStringValue(String key) {
		return (String) contextMap.get(key);
	}
}
