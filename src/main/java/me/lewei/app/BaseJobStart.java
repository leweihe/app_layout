package me.lewei.app;

import java.util.HashMap;

import me.lewei.core.ApplicationContextHolder;
import me.lewei.core.ProcerConstants;
import me.lewei.obj.JobContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseJobStart {

	private static final String APPLN_CONEXT_SPRING_NAME = "processer.xml";
	
	private static final Log log = LogFactory.getLog(BaseJobStart.class);
	
	public static void test(String[] args) {
		main(args);
	}

	public static void main(String[] args) {
		long startMillis = System.currentTimeMillis();
		
		new ClassPathXmlApplicationContext(APPLN_CONEXT_SPRING_NAME);
		
		if (args.length == 0) {
			System.exit(1);
		}
		JobContext jobContext = copyAllInputParameter(args);

		String command = jobContext.getCommand();
		if (command == null || "".equals(command)) {
			System.out.println("Error: Unknown command.");
		}

		try {
			BaseJobProcess job = null;
			job = getProcessor(command, job);
			if (job == null) {
				System.exit(4);
			}
			job.execute(jobContext);
		} catch (Exception e) {
			log.error("[BaseJobStart] Cannot init services", e);
			System.exit(-1);
		}
		long endMillis = System.currentTimeMillis();
		
		log.info("Spent totally " + (endMillis - startMillis) + " million seconds.");
		System.exit(0);
	}


	private static BaseJobProcess getProcessor(String command, BaseJobProcess job) throws Exception {
		String jobId = "";

		if (ProcerConstants.P_PARAM.P_READ.equals(command))
			jobId = ProcerConstants.WORKER.READ;
		else if (ProcerConstants.P_PARAM.P_WRITE.equals(command))
			jobId = ProcerConstants.WORKER.WRITE;
		
		if (command != null && !"".equals(command)) {
			job = ApplicationContextHolder.getBean(jobId, BaseJobProcess.class);
		} else {
			job = ApplicationContextHolder.getBean(jobId, BaseJobProcess.class);
		}
		return job;
	}

	private static JobContext copyAllInputParameter(String[] args) {
		JobContext jobContext = new JobContext();
		HashMap<String, String> exContext = new HashMap<String, String>();
		if (args != null && args.length > 0) {
			for (String param : args) {
				if (param.indexOf(ProcerConstants.SYMBOL.HYPHEN) == 0) {
					exContext.put(ProcerConstants.COMMAND, param);
				} else {
					exContext.put(ProcerConstants.PATH, param);
				}
			}
		}
		jobContext.setExContext(exContext);
		return jobContext;
	}
}
