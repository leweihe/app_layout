package me.lewei.logic;

import me.lewei.obj.ReadContext;


public interface ReadService {

	public void readFileNameList(ReadContext rc) throws Exception;

	/**
	 * @return void
	 * @param rc
	 */
	public void pasteNameListToExcel(ReadContext rc) throws Exception;

}
