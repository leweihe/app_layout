package me.lewei.logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.lewei.logic.ReadService;
import me.lewei.obj.ReadContext;
import me.lewei.util.CommonExcelUtil;
import me.lewei.util.FileUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReadServiceImpl implements ReadService {

	private static final Log log = LogFactory.getLog(ReadServiceImpl.class);

	CommonExcelUtil ceu = new CommonExcelUtil();
	
	@Override
	public void readFileNameList(ReadContext rc) throws Exception {
		
		String path = rc.getInputPath();
		if(StringUtils.isEmpty(path)){
			throw new Exception("[ readFileNameList ] The input path is empty.");
		}
		List<File> fileList = new ArrayList<File>();
		fileList = FileUtil.getAllFiles(path, true);
		
		log.info("[ readFileNameList ] Totally: " + fileList.size());
		
		if(fileList != null && fileList.size() > 0) {
			rc.setFiles(fileList);
		}

		ceu.creatExcel4ReadedFile(rc);
	}

	@Override
	public void pasteNameListToExcel(ReadContext rc) throws Exception {
		

	}

}
