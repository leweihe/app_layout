package me.lewei.obj;

import java.util.HashMap;

import me.lewei.core.ProcerConstants;

public class JobContext {

	public HashMap<String, String> exContext;

	public String getCommand() {
		if (getExContext() != null && getExContext().get(ProcerConstants.COMMAND) != null) {
			return getExContext().get(ProcerConstants.COMMAND);
		}
		return null;
	}

	public String getPath() {
		if (getExContext() != null && getExContext().get(ProcerConstants.PATH) != null) {
			return getExContext().get(ProcerConstants.PATH);
		}
		return null;
	}

	public HashMap<String, String> getExContext() {
		return exContext;
	}

	public void setExContext(HashMap<String, String> exContext) {
		this.exContext = exContext;
	}
	
	

}
