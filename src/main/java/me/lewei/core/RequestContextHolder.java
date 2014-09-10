package me.lewei.core;

import me.lewei.obj.AppRequestContext;

public class RequestContextHolder {

	private static ThreadLocal<AppRequestContext> requestContextTL = new ThreadLocal<AppRequestContext>();

	public static AppRequestContext getMPARequestContext() {
		AppRequestContext context = (AppRequestContext) requestContextTL.get();
		if (context == null) {
			context = new AppRequestContext();
			requestContextTL.set(context);
		}
		return context;
	}

	/**
	 * set CafContext object
	 * 
	 * @param context
	 */
	public static void setMPARequestContext(AppRequestContext context) {
		requestContextTL.set(context);
	}

	/**
	 * initial CafContext object
	 */
	public static AppRequestContext initContext() {
		AppRequestContext context = new AppRequestContext();
		requestContextTL.set(context);
		return context;
	}

}
