package me.lewei.util;

import me.lewei.core.ApplicationContextHolder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.setAppContext(applicationContext);
	}

}
