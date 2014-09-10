package me.lewei.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import me.lewei.core.IExceptionHandler;
import me.lewei.obj.JobContext;

import org.apache.commons.logging.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

public abstract class BaseJobProcess implements InitializingBean, BeanNameAware, BeanFactoryAware {

	private static final String DEFAULT_THREADPOOL_KEY = "BaseJobProcess_DEFAULT_ThreadPool";
	
	public static  Map<String, ThreadPoolExecutor> poolCacheMap = new ConcurrentHashMap<String, ThreadPoolExecutor>();

	public static  Map<String, Boolean> jobStatusMap = new ConcurrentHashMap<String, Boolean>();

	/** */
	private String beanName;
	private BeanFactory beanFactory;

	private String poolExecutorKey;
	private String timeUnit = "3";

	private boolean needThreadPool = false;
	private boolean particularThreadPool = false;

	private JobContext jobContext;
	private IExceptionHandler exceptionHandler;

	public void execute(JobContext jobContext) throws Exception {
		try {
			this.jobContext = jobContext;
			if (preExecute())
				run();
			postExecute();
		} catch (Exception ex) {
			getExceptionHandler().handleExcepition(ex);
		}
	}

	public boolean preExecute() throws Exception {
		return true;
	}

	public abstract void run() throws Exception;

	public void runWorker(BaseWorker baseWorker) {
		if (baseWorker == null)
			return;

		baseWorker.setJobContext(jobContext);

		if (isNeedThreadPool()) {
			ThreadPoolExecutor poolExecutor = getThreadPoolExecutor();
			if (poolExecutor == null) {
				baseWorker.run();
			} else {
				poolExecutor.execute(baseWorker);
			}
		} else {
			baseWorker.run();
		}
	}

	public void postExecute() throws Exception {
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		if (!isNeedThreadPool())
			return;

		poolExecutorKey = null;
		if (isParticularThreadPool()) {
			poolExecutorKey = this.getBeanName();
		} else {
			poolExecutorKey = BaseJobProcess.DEFAULT_THREADPOOL_KEY;
		}

		Boolean jobStatus = jobStatusMap.get(poolExecutorKey);
		ThreadPoolExecutor poolExecutor = poolCacheMap.get(poolExecutorKey);

		if (jobStatus == null || !jobStatus || poolExecutor == null) {
			poolExecutor = initThreadPoolExecutor(poolExecutor);
			poolCacheMap.put(poolExecutorKey, poolExecutor);
			jobStatusMap.put(poolExecutorKey, Boolean.TRUE);
		}
	}

	private synchronized ThreadPoolExecutor getThreadPoolExecutor() {
		ThreadPoolExecutor poolExecutor = poolCacheMap.get(poolExecutorKey);
		return poolExecutor;
	}

	private synchronized ThreadPoolExecutor initThreadPoolExecutor(ThreadPoolExecutor poolExecutor) {
		TimeUnit tu = TimeUnit.SECONDS;
		if (poolExecutor == null) {
			poolExecutor = new ThreadPoolExecutor(3, 3, 30 * 60, tu, new LinkedBlockingQueue<Runnable>());
		}
		return poolExecutor;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public String getPoolExecutorKey() {
		return poolExecutorKey;
	}

	public void setPoolExecutorKey(String poolExecutorKey) {
		this.poolExecutorKey = poolExecutorKey;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public boolean isNeedThreadPool() {
		return needThreadPool;
	}

	public boolean isParticularThreadPool() {
		return particularThreadPool;
	}

	public IExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(IExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
	
	public abstract Log getLogger();

}
