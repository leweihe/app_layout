package me.lewei.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleBatch extends BaseJobProcess {

	private static final Log log = LogFactory.getLog(SimpleBatch.class);

	public BaseWorker worker;

	@Override
	public Log getLogger() {
		return log;
	}

	public void setWorker(BaseWorker worker) {
		this.worker = worker;
	}

	@Override
	public void run() throws Exception {
		log.info("=============run>worker: " + worker.getBeanName());
		runWorker(worker);
	}

}
