package me.lewei.logic;

import me.lewei.obj.WriteContext;



public interface WriteService {

	public void readNameListFromExcel(WriteContext wc);

	public void processRenameFiles(WriteContext wc);

	public void exportResultToExcel(WriteContext wc);

}
