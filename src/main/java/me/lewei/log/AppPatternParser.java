package me.lewei.log;
 
import org.apache.log4j.helpers.PatternParser;
 
public class AppPatternParser extends PatternParser {
    private static final char SYSTEM_CODE = 's';
    private static final char TRANSACTION_ID = 'T';
    private static final char SESSION_ID = 'S';
    
     
    public AppPatternParser(String pattern) {
        super(pattern);
    }
     
    @Override
    protected void finalizeConverter(char c) {
        switch (c) {
        case SYSTEM_CODE:
            currentLiteral.setLength(0);
            addConverter(new AppSystemCodePatternConverter());
            break;
        case TRANSACTION_ID:
            currentLiteral.setLength(0);
            addConverter(new AppTransactionIdPatternConverter());
            break;
        case SESSION_ID:
            currentLiteral.setLength(0);
            addConverter(new AppSessionIdPatternConverter());
            break;
        default:
            super.finalizeConverter(c);
        }
    }
} 