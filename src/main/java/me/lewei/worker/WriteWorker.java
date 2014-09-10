package me.lewei.worker;

import me.lewei.app.BaseWorker;
import me.lewei.logic.WriteService;
import me.lewei.obj.JobContext;
import me.lewei.obj.WriteContext;

public class WriteWorker extends BaseWorker {

	JobContext jobContext;

	private WriteService writeService;

	@Override
	public void workerRun() throws Exception {
		
		this.jobContext = super.getJobContext(); 
		WriteContext wc = new WriteContext();
		
		wc.setTargetPath(jobContext.getPath());
		
		writeService.readNameListFromExcel(wc);
		
		writeService.processRenameFiles(wc);
		
		writeService.exportResultToExcel(wc);
	}

	public WriteService getWriteService() {
		return writeService;
	}

	public void setWriteService(WriteService writeService) {
		this.writeService = writeService;
	}

}
