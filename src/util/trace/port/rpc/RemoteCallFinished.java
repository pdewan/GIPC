package util.trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RemoteCallFinished extends TraceableInfo {
	

	public RemoteCallFinished(String aMessage, Object aSource, Object aDestination, String aName, Method aMethod, Object[] args, Object aRetVal) {
		super(aMessage, aSource );
	}
	
	
	public static RemoteCallFinished newCase(Object aSource, Object aDestination, String aName, Method aMethod, Object[] args, Object aRetVal) {
    	String aMessage = "(" + aDestination + "," + aName + ")." +aMethod + "(" + Arrays.toString(args) + ")" + "=" + aRetVal;
		RemoteCallFinished retVal = new RemoteCallFinished(aMessage, aSource, aDestination, aName, aMethod, args, aRetVal);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
