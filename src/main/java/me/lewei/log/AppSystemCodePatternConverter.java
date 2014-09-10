package me.lewei.log;
 
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
 
public class AppSystemCodePatternConverter extends PatternConverter {
 
    @Override
    protected String convert(LoggingEvent evt) {
    	LocationInfo info = evt.getLocationInformation();
    	String className = info.getClassName();;
    	String[] _temp = className.split("\\.");
    	if(_temp==null||_temp.length<=4){
    		return className;
    	}
		
		String temp = _temp[4];
		if((temp.equals("intranet")||temp.equals("internet"))){
			temp = _temp[5];
		}
		
		if(temp.equals("archival")||temp.equals("base")||temp.equals("ejb")||temp.equals("common")
				){
			return "CM";
		}else if(temp.equals("dg")||temp.equals("edi")
				){
			return "DG";
		}else if(temp.equals("cv")||temp.equals("ie")||temp.equals("pc")||temp.equals("pu")||temp.equals("lc")
				){
			return temp.toUpperCase();
		}else  
			return className;
    }
} 