package me.lewei.obj;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WriteContext {

	private String targetPath;
	private List<String> nameList;
	private String orderParam;
	private List<Map<String, File>> fileMaps = new ArrayList<Map<String, File>>();

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public String getOrderParam() {
		return orderParam;
	}

	public void setOrderParam(String orderParam) {
		this.orderParam = orderParam;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public List<Map<String, File>> getFileMaps() {
		return fileMaps;
	}

	public void setFileMaps(List<Map<String, File>> fileMaps) {
		this.fileMaps = fileMaps;
	}

}
