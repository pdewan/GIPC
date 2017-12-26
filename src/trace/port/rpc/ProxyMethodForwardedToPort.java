package trace.port.rpc;

import java.lang.reflect.Method;
import java.util.Arrays;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ProxyMethodForwardedToPort extends TraceableInfo {
	

	public ProxyMethodForwardedToPort(String aMessage, Object aSource, String aDestination, String aName, Method aMethod, Object[] args) {
		super(aMessage, aSource );
	}
	
	
	public static ProxyMethodForwardedToPort newCase(Object aSource, String aDestination, String aName, Method aMethod, Object[] args) {
    	String aMessage = "(" + aDestination + "," + aName + ")." +aMethod + "(" + Arrays.toString(args) + ")";
		ProxyMethodForwardedToPort retVal = new ProxyMethodForwardedToPort(aMessage, aSource, aDestination, aName, aMethod, args);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
