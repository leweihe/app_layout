package me.lewei.core;

import org.springframework.context.ApplicationContext;

public class ApplicationContextHolder {

	private static ApplicationContext appContext;

	public static ApplicationContext getAppContext() {
		return appContext;
	}

	public static void setAppContext(ApplicationContext appContext) {
		ApplicationContextHolder.appContext = appContext;
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return (T) appContext.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType) {
		return (T) appContext.getBean(requiredType.getName(), requiredType);
	}

}
