package me.lewei.core;
public interface ProcerConstants {

    public static final String COMMAND = "comand";
    public static final String PATH = "path";
    public static final String CONFIG = "config";
    
    public static final String WORKING_FILE_NAME = "RENAME_WORKING_GROUND.xls";
    
    public static interface P_PARAM{
    	
    	public static final String P_HELP = "-h";
    	public static final String P_READ = "-r";
    	public static final String P_WRITE = "-w";
    	
    }

    public static interface SYMBOL{
    	public static final char HYPHEN = '-';
    	public static final char DOT = '.';
    }
    public static interface WORKER{
    	public static final String READ = "Read";
    	public static final String WRITE = "Write";
    }
}
