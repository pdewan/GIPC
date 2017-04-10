package port.trace.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RemoteCallInitiated extends TraceableInfo {
	

	public RemoteCallInitiated(String aMessage, Object aSource, Object aDestination, String aName, Method aMethod, Object[] args) {
		super(aMessage, aSource );
	}
	
	
	public static RemoteCallInitiated newCase(Object aSource, Object aDestination, String aName, Method aMethod, Object[] args) {
    	if (aDestination == null) {
    		System.out.println ("Null destination");
    	}
		String aMessage = "(" + aDestination + "," + aName + ")." +aMethod + "(" + Arrays.toString(args) + ")";
		RemoteCallInitiated retVal = new RemoteCallInitiated(aMessage, aSource, aDestination, aName, aMethod, args);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
