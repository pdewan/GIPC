package inputport.rpc.group.mvc.collaborative.relaying.example;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class MVCTraceableInfo extends TraceableInfo{
	public MVCTraceableInfo(String aMessage, Object anAnnouncer) {
		super(aMessage, anAnnouncer);
		setDisplay(true);
		
	}
	public static MVCTraceableInfo newInfo(String aMessage, Object aFinder) {    	
		MVCTraceableInfo retVal = new MVCTraceableInfo(aMessage, aFinder);
   	    retVal.announce();
    	return retVal;
	}
}
