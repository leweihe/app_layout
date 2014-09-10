package me.lewei.logic.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import me.lewei.logic.WriteService;
import me.lewei.obj.WriteContext;
import me.lewei.util.CommonExcelUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WriteServiceImpl implements WriteService {

	private static final Log log = LogFactory.getLog(WriteServiceImpl.class);

	@Override
	public void readNameListFromExcel(WriteContext wc) {

		String path = wc.getTargetPath();
		String order = wc.getOrderParam();

		try {
			CommonExcelUtil excelReader = new CommonExcelUtil();
			InputStream is = new FileInputStream(path);
			
			List<Map<String, File>> fileMaps = excelReader.readExcelFileMaps(is, path);
			log.info("[ readNameListFromExcel ] Totally " + fileMaps.size());
			
			wc.setFileMaps(fileMaps);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void processRenameFiles(WriteContext wc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportResultToExcel(WriteContext wc) {
		// TODO Auto-generated method stub

	}

}
