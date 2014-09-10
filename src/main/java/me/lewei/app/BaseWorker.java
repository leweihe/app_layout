package me.lewei.app;

import me.lewei.core.IExceptionHandler;
import me.lewei.obj.JobContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;


public abstract class BaseWorker implements InitializingBean, BeanNameAware,
		Runnable {
	
	private static final Log log = LogFactory.getLog(BaseWorker.class);
	
	private String beanName;
	private JobContext jobContext;
	private IExceptionHandler exceptionHandler;

	public void run() {
		try {
			if (berforeRun()) {
				workerRun();
			}
		} catch (Throwable th) {
			log.error(th);
		} finally {
			try {
				afterRun();
			} catch (Throwable th) {
				log.error(th);
			}
		}
	}
	
	public boolean berforeRun() throws Throwable {
		try {

		} catch (Throwable th) {
			log.error(th);
		}
		return true;
	}

	public void afterRun() throws Throwable {
		try {

		} catch (Throwable th) {
			log.error(th);
		}
	}

	public abstract void workerRun() throws Exception;

	public void afterPropertiesSet() throws Exception {

	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}

	public JobContext getJobContext() {
		return jobContext;
	}

	public void setJobContext(JobContext jobContext) {
		if(jobContext == null)
			jobContext = new JobContext();
		this.jobContext = jobContext;
	}

	public IExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(IExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public void dispose() {
		this.exceptionHandler = null;
	}

}
