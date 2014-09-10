package me.lewei.log;
 
import me.lewei.core.RequestContextHolder;
import me.lewei.obj.AppRequestContext;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.spi.LoggingEvent;
 
public class AppSessionIdPatternConverter extends PatternConverter {
 
    @Override
    protected String convert(LoggingEvent evt) {
    	AppRequestContext ctx = RequestContextHolder.getMPARequestContext();    	
        return ctx.getSessionId();
    }
} 