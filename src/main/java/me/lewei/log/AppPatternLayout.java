package me.lewei.log;
 
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
 
public class AppPatternLayout extends PatternLayout {
 
    @Override
    protected PatternParser createPatternParser(String pattern) {
        return new AppPatternParser(pattern);
    }
} 