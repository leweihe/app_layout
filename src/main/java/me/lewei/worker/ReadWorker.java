package me.lewei.worker;

import java.io.File;
import java.util.List;

import me.lewei.app.BaseWorker;
import me.lewei.logic.ReadService;
import me.lewei.obj.JobContext;
import me.lewei.obj.ReadContext;

public class ReadWorker extends BaseWorker {

	JobContext jobContext;

	private ReadService readService;

	@Override
	public void workerRun() throws Exception {

		this.jobContext = this.getJobContext();
		
		String inputPath = jobContext.getPath();
		inputPath = inputPath.replaceAll("/", "\\");
		if (inputPath.endsWith("\\")) {
			inputPath = inputPath + "\\";
		}

		ReadContext rc = new ReadContext();
		rc.setInputPath(inputPath);

		readService.readFileNameList(rc);

		List<File> files = rc.getFiles();

		if (files == null || files.size() <= 0) {
			throw new Exception("[ReadWorker] Read from folder " + inputPath + " is empty");// TODO
		}

		readService.pasteNameListToExcel(rc);

	}

	public ReadService getReadService() {
		return readService;
	}

	public void setReadService(ReadService readService) {
		this.readService = readService;
	}
}
